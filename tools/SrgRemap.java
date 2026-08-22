import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.commons.Remapper;
import org.objectweb.asm.commons.RemappingClassAdapter;

import java.io.*;
import java.util.*;
import java.util.zip.*;

public class SrgRemap {

    static final Map<String, String> CL = new HashMap<String, String>();
    static final Map<String, String> FD = new HashMap<String, String>();
    static final Map<String, String> MD = new HashMap<String, String>();

    public static void main(String[] args) throws Exception {
        if (args.length < 4) {
            System.out.println("usage: SrgRemap <in.jar> <out.jar> <mapping.srg> <forward|reverse>");
            return;
        }
        boolean reverse = "reverse".equalsIgnoreCase(args[3]);
        load(new File(args[2]), reverse);
        System.out.println("[*] CL=" + CL.size() + " FD=" + FD.size() + " MD=" + MD.size());

        Remapper rm = new Remapper() {
            @Override public String map(String t) {
                String r = CL.get(t);
                return r != null ? r : t;
            }
            @Override public String mapFieldName(String owner, String name, String desc) {
                String r = FD.get(owner + "/" + name);
                if (r == null) r = FD.get(name);
                return r != null ? r : name;
            }
            @Override public String mapMethodName(String owner, String name, String desc) {
                String r = MD.get(owner + "/" + name + " " + desc);
                if (r == null) r = MD.get(name + " " + desc);
                if (r == null) r = MD.get(name);
                return r != null ? r : name;
            }
        };

        ZipFile zf = new ZipFile(args[0]);
        ZipOutputStream zos = new ZipOutputStream(
                new BufferedOutputStream(new FileOutputStream(args[1])));
        int cls = 0, res = 0, fail = 0, sig = 0;
        for (Enumeration<? extends ZipEntry> e = zf.entries(); e.hasMoreElements(); ) {
            ZipEntry ze = e.nextElement();
            if (ze.isDirectory()) continue;
            byte[] data = read(zf.getInputStream(ze));
            String name = ze.getName();
            // 每个类都被重映射过，所以 Mojang 的签名摘要必然全部失效。原样带过来的
            // META-INF/*.SF|.DSA|.RSA 会让任何装了 SecurityManager 的工具在触碰
            // net/minecraft/** 时抛 SecurityException("SHA-256 digest error")。
            // 实测代价：<clinit> 闸门里 165 个类被记成初始化失败，掩盖了真实根因。
            String U = name.toUpperCase();
            if (U.startsWith("META-INF/")
                    && (U.endsWith(".SF") || U.endsWith(".DSA")
                        || U.endsWith(".RSA") || U.endsWith(".EC"))) {
                sig++;
                continue;
            }
            if (U.equals("META-INF/MANIFEST.MF")) {
                data = stripDigests(data);
            }
            if (name.endsWith(".class")) {
                try {
                    ClassReader cr = new ClassReader(data);
                    ClassWriter cw = new ClassWriter(0);
                    cr.accept(new InnerAwareAdapter(cw, rm), ClassReader.EXPAND_FRAMES);
                    data = cw.toByteArray();
                    String owner = name.substring(0, name.length() - 6);
                    String mapped = CL.containsKey(owner) ? CL.get(owner) : owner;
                    name = mapped + ".class";
                    cls++;
                } catch (Throwable t) {
                    fail++;
                }
            } else {
                res++;
            }
            try {
                zos.putNextEntry(new ZipEntry(name));
                zos.write(data);
                zos.closeEntry();
            } catch (ZipException dup) {
                fail++;
            }
        }
        zos.close();
        zf.close();
        System.out.println("[*] classes=" + cls + " resources=" + res
                + " signatures-stripped=" + sig + " failed=" + fail);
        if (fail > 0) System.out.println("[!] " + fail + " entries failed, see above");
    }

    static void load(File f, boolean reverse) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(
                new FileInputStream(f), "UTF-8"));
        String line;
        while ((line = r.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;
            int c = line.indexOf(':');
            if (c < 0) continue;
            String kind = line.substring(0, c);
            String[] p = line.substring(c + 1).trim().split("\\s+");
            if ("CL".equals(kind) && p.length == 2) {
                put(CL, p[0], p[1], reverse);
            } else if ("FD".equals(kind) && p.length == 2) {
                String a = p[0], b = p[1];
                String from = reverse ? b : a, to = reverse ? a : b;
                FD.put(from, simple(to));
                FD.put(simple(from), simple(to));
            } else if ("MD".equals(kind) && p.length == 4) {
                String a = p[0], ad = p[1], b = p[2], bd = p[3];
                String from = reverse ? b : a, fd = reverse ? bd : ad, to = reverse ? a : b;
                MD.put(from + " " + fd, simple(to));
                MD.put(simple(from) + " " + fd, simple(to));
                MD.put(simple(from), simple(to));
            }
        }
        r.close();
    }

    static void put(Map<String, String> m, String a, String b, boolean rev) {
        if (rev) m.put(b, a); else m.put(a, b);
    }

    static String simple(String path) {
        int i = path.lastIndexOf('/');
        return i < 0 ? path : path.substring(i + 1);
    }

    /**
     * Drop per-entry Digest attributes from the manifest. They are stale the moment
     * classes are remapped, and a stale digest makes JarVerifier throw
     * SecurityException("SHA-256 digest error") for that entry -- but only under a
     * SecurityManager, so it presents as the class itself being broken.
     */
    static byte[] stripDigests(byte[] mf) {
        final char NL = (char) 10, CR = (char) 13;
        java.nio.charset.Charset U8 = java.nio.charset.Charset.forName("UTF-8");
        String[] lines = new String(mf, U8).split(String.valueOf(NL));
        StringBuilder out = new StringBuilder();
        List<String> block = new ArrayList<String>();
        boolean hasDigest = false;
        for (int i = 0; i <= lines.length; i++) {
            String l = i < lines.length ? lines[i].replace(String.valueOf(CR), "") : "";
            if (i == lines.length || l.trim().isEmpty()) {
                if (!block.isEmpty() && !hasDigest) {
                    for (String b : block) out.append(b).append(NL);
                    out.append(NL);
                }
                block.clear();
                hasDigest = false;
            } else {
                block.add(l);
                if (l.indexOf("Digest") >= 0) hasDigest = true;
            }
        }
        return out.toString().getBytes(U8);
    }

    static byte[] read(InputStream is) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[8192];
        int n;
        while ((n = is.read(buf)) > 0) bos.write(buf, 0, n);
        is.close();
        return bos.toByteArray();
    }
}
