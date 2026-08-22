import org.objectweb.asm.*;

import java.io.*;
import java.util.*;
import java.util.zip.*;

/**
 * Routes the ZKM reflective class resolver through {@code Expo.internal.restore.ExpoNameMap}
 * in classes that cannot be fixed in source.
 *
 * <p><b>Status of the original justification.</b>  This pass was introduced because 71
 * files did not compile, were listed in {@code excluded_sources.txt}, and had their
 * bytecode supplied by {@code prebuilt/expo-prebuilt.jar} -- editing those .java was a
 * silent no-op, so 18 of the resolvers were unreachable from source.  <b>That reason no
 * longer holds.</b>  The exclusion list is empty, the overlay is gone, and
 * {@code checkNoExcludedSources} asserts that every .java under {@code src/main/java}
 * produces a .class.  The 18 rewrites below are therefore now a source-level TODO: each
 * one could be expressed as an {@code ExpoNameMap.map(...)} call in the .java itself, and
 * this pass deleted.  Until someone does that and re-measures, the pass stays, because
 * removing it without moving the sites would silently drop 18 live rewrites -- the count
 * gate below is what makes that statement checkable rather than assumed.
 *
 * <p><b>What it does.</b>  In each whitelisted class it inserts one instruction before
 * every {@code Class.forName(String)}:
 * <pre>
 *   INVOKESTATIC Expo/internal/restore/ExpoNameMap.map (Ljava/lang/String;)Ljava/lang/String;
 * </pre>
 * The inserted call is String to String, so operand-stack depth, max stack and local
 * slots are all unchanged and the existing StackMapTable stays valid.  That is why the
 * writer is constructed with flags 0 -- frames are copied, never recomputed.  Frame
 * recomputation would need to resolve the obfuscated class hierarchy, which is exactly
 * the operation known to misbehave here.
 *
 * <p><b>The gate.</b>  The pass counts the sites it rewrote and fails the build unless
 * the count equals the expected value passed on the command line, then re-reads its own
 * output and asserts every whitelisted site now routes through the map.  A pass whose
 * count can silently drift to zero would be indistinguishable from a pass that did
 * nothing.
 *
 * <p>Reads and writes zip entries directly.  It never unpacks to a directory: this tree
 * has case-colliding class names and Windows would silently drop one of each pair.
 *
 * <p>Usage: {@code NameMapPass <jar> <expectedCount> <class> [<class> ...]}
 */
public final class NameMapPass {

    private static final String MAP_OWNER = "Expo/internal/restore/ExpoNameMap";
    private static final String MAP_NAME = "map";
    private static final String MAP_DESC = "(Ljava/lang/String;)Ljava/lang/String;";
    private static final String FN_OWNER = "java/lang/Class";
    private static final String FN_NAME = "forName";
    private static final String FN_DESC = "(Ljava/lang/String;)Ljava/lang/Class;";

    private static int patched;

    public static void main(String[] args) throws Exception {
        if (args.length < 3) {
            throw new IllegalArgumentException(
                    "usage: NameMapPass <jar> <expectedCount> <class> [<class> ...]");
        }
        File jar = new File(args[0]);
        int expected = Integer.parseInt(args[1]);
        Set<String> targets = new LinkedHashSet<String>();
        for (int i = 2; i < args.length; i++) {
            targets.add(args[i].replace('.', '/'));
        }

        Map<String, byte[]> out = new LinkedHashMap<String, byte[]>();
        List<String> order = new ArrayList<String>();
        Set<String> seen = new LinkedHashSet<String>();

        ZipFile z = new ZipFile(jar);
        try {
            for (Enumeration<? extends ZipEntry> e = z.entries(); e.hasMoreElements(); ) {
                ZipEntry en = e.nextElement();
                if (en.isDirectory()) {
                    continue;
                }
                byte[] data = readAll(z.getInputStream(en));
                String name = en.getName();
                if (name.endsWith(".class")) {
                    String cls = name.substring(0, name.length() - 6);
                    if (targets.contains(cls)) {
                        seen.add(cls);
                        data = transform(data);
                    }
                }
                order.add(name);
                out.put(name, data);
            }
        } finally {
            z.close();
        }

        Set<String> missing = new LinkedHashSet<String>(targets);
        missing.removeAll(seen);
        if (!missing.isEmpty()) {
            throw new IllegalStateException(
                    "whitelisted classes absent from the jar: " + missing);
        }
        if (patched != expected) {
            throw new IllegalStateException("NameMapPass rewrote " + patched
                    + " Class.forName site(s) but expected exactly " + expected
                    + ".  Refusing to ship: a drifting count means the pass silently"
                    + " stopped matching.");
        }

        // Every entry is already in memory, so rewrite the archive in place.  Windows
        // refuses the delete+rename dance while the Jar task still holds the file.
        ZipOutputStream zo = new ZipOutputStream(new BufferedOutputStream(
                new FileOutputStream(jar)));
        try {
            for (String name : order) {
                ZipEntry ne = new ZipEntry(name);
                ne.setTime(0L);
                zo.putNextEntry(ne);
                zo.write(out.get(name));
                zo.closeEntry();
            }
        } finally {
            zo.close();
        }

        int verified = verify(jar, targets);
        if (verified != expected) {
            throw new IllegalStateException("post-write verification found " + verified
                    + " mapped site(s), expected " + expected);
        }
        System.out.println("[NameMapPass] rewrote and verified " + patched
                + " Class.forName site(s) across " + targets.size() + " class(es)");
    }

    private static byte[] transform(byte[] data) {
        ClassReader cr = new ClassReader(data);
        // flags 0: copy frames verbatim.  The inserted call does not change stack depth,
        // so recomputation is unnecessary and would require hierarchy resolution.
        final ClassWriter cw = new ClassWriter(cr, 0);
        cr.accept(new ClassVisitor(Opcodes.ASM5, cw) {
            @Override
            public MethodVisitor visitMethod(int a, String n, String d, String s, String[] x) {
                MethodVisitor mv = super.visitMethod(a, n, d, s, x);
                if (mv == null) {
                    return null;
                }
                return new MethodVisitor(Opcodes.ASM5, mv) {
                    private boolean lastWasMap;

                    @Override
                    public void visitMethodInsn(int op, String owner, String name,
                                                String desc, boolean itf) {
                        boolean isForName = op == Opcodes.INVOKESTATIC
                                && FN_OWNER.equals(owner) && FN_NAME.equals(name)
                                && FN_DESC.equals(desc);
                        if (isForName && !lastWasMap) {
                            super.visitMethodInsn(Opcodes.INVOKESTATIC, MAP_OWNER,
                                    MAP_NAME, MAP_DESC, false);
                            patched++;
                        }
                        lastWasMap = op == Opcodes.INVOKESTATIC && MAP_OWNER.equals(owner)
                                && MAP_NAME.equals(name);
                        super.visitMethodInsn(op, owner, name, desc, itf);
                    }
                };
            }
        }, 0);
        return cw.toByteArray();
    }

    /** Re-read the written jar and count sites that now route through the map. */
    private static int verify(File jar, Set<String> targets) throws IOException {
        final int[] n = {0};
        ZipFile z = new ZipFile(jar);
        try {
            for (String cls : targets) {
                ZipEntry en = z.getEntry(cls + ".class");
                if (en == null) {
                    throw new IOException("missing after write: " + cls);
                }
                new ClassReader(readAll(z.getInputStream(en)))
                        .accept(new ClassVisitor(Opcodes.ASM5) {
                            @Override
                            public MethodVisitor visitMethod(int a, String nm, String d,
                                                             String s, String[] x) {
                                return new MethodVisitor(Opcodes.ASM5) {
                                    private boolean lastWasMap;

                                    @Override
                                    public void visitMethodInsn(int op, String owner,
                                                                String name, String desc,
                                                                boolean itf) {
                                        if (op == Opcodes.INVOKESTATIC
                                                && FN_OWNER.equals(owner)
                                                && FN_NAME.equals(name)
                                                && FN_DESC.equals(desc) && lastWasMap) {
                                            n[0]++;
                                        }
                                        lastWasMap = op == Opcodes.INVOKESTATIC
                                                && MAP_OWNER.equals(owner)
                                                && MAP_NAME.equals(name);
                                    }
                                };
                            }
                        }, 0);
            }
        } finally {
            z.close();
        }
        return n[0];
    }

    private static byte[] readAll(InputStream in) throws IOException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        byte[] buf = new byte[8192];
        int k;
        while ((k = in.read(buf)) > 0) {
            bo.write(buf, 0, k);
        }
        in.close();
        return bo.toByteArray();
    }

    private NameMapPass() {
    }
}
