import org.objectweb.asm.*;

import java.io.*;
import java.util.*;
import java.util.zip.*;

/**
 * Applies a Forge access-transformer config to a jar (compile classpath only).
 *
 * Expo's source reads net.minecraft.util.EnumFacing.field_82609_l and
 * net.minecraft.client.gui.GuiButton.field_146120_f/g directly.  Those are private /
 * protected in vanilla, so javac rejects the source -- but forge_at.cfg lines 142, 200
 * and 201 make all three public, so under Forge they ARE accessible at runtime and the
 * ORIGINAL bytecode reaches them with a plain getstatic/getfield.  The defect is in the
 * compile classpath (build/mapped/minecraft-srg.jar is remapped from vanilla, with vanilla
 * access flags), not in the source.
 *
 * usage: ApplyAT <inJar> <outJar> <at.cfg> [<at.cfg> ...]
 */
public final class ApplyAT {

    public static void main(String[] args) throws Exception {
        // owner -> member ("name" for fields, "name+desc" for methods, "*" for all)
        final Map<String, Set<String>> want = new HashMap<String, Set<String>>();
        for (int i = 2; i < args.length; i++) {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(args[i]), "UTF-8"));
            for (String l = br.readLine(); l != null; l = br.readLine()) {
                int h = l.indexOf('#');
                if (h >= 0) l = l.substring(0, h);
                l = l.trim();
                if (l.isEmpty()) continue;
                String[] p = l.split("\\s+");
                if (p.length < 2 || !p[0].startsWith("public")) continue;
                String owner = p[1].replace('.', '/');
                String member = p.length > 2 ? p[2] : "*";
                if (!want.containsKey(owner)) want.put(owner, new HashSet<String>());
                want.get(owner).add(member);
            }
            br.close();
        }

        final int[] n = {0};
        ZipFile z = new ZipFile(args[0]);
        ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(
                new FileOutputStream(args[1])));
        for (Enumeration<? extends ZipEntry> e = z.entries(); e.hasMoreElements(); ) {
            ZipEntry en = e.nextElement();
            byte[] data = read(z.getInputStream(en));
            if (en.getName().endsWith(".class")) {
                final String cls = en.getName().substring(0, en.getName().length() - 6);
                final Set<String> m = want.get(cls);
                if (m != null) {
                    ClassReader cr = new ClassReader(data);
                    ClassWriter cw = new ClassWriter(cr, 0);
                    cr.accept(new ClassVisitor(Opcodes.ASM5, cw) {
                        @Override
                        public FieldVisitor visitField(int a, String nm, String d, String s, Object v) {
                            if (m.contains("*") || m.contains(nm)) { a = pub(a); n[0]++; }
                            return super.visitField(a, nm, d, s, v);
                        }

                        @Override
                        public MethodVisitor visitMethod(int a, String nm, String d, String s, String[] x) {
                            if (m.contains("*") || m.contains(nm) || m.contains(nm + d)) { a = pub(a); n[0]++; }
                            return super.visitMethod(a, nm, d, s, x);
                        }
                    }, 0);
                    data = cw.toByteArray();
                }
            }
            ZipEntry ne = new ZipEntry(en.getName());
            ne.setTime(0L);
            out.putNextEntry(ne);
            out.write(data);
            out.closeEntry();
        }
        z.close();
        out.close();
        System.out.println("[ApplyAT] widened " + n[0] + " member(s) across "
                + want.size() + " declared owner(s)");
    }

    static int pub(int a) {
        return (a & ~(Opcodes.ACC_PRIVATE | Opcodes.ACC_PROTECTED)) | Opcodes.ACC_PUBLIC;
    }

    static byte[] read(InputStream in) throws IOException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        byte[] buf = new byte[8192];
        int k;
        while ((k = in.read(buf)) > 0) bo.write(buf, 0, k);
        in.close();
        return bo.toByteArray();
    }

    private ApplyAT() { }
}
