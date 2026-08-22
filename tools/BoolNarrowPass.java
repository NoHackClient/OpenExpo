import org.objectweb.asm.*;
import org.objectweb.asm.tree.*;
import java.io.*; import java.util.*; import java.util.zip.*;

/**
 * Restores ZKM's boolean materialisation semantics.
 *
 * ZKM encodes a boolean in BIT 0 of a DES-decrypted value; the upper bits are
 * padding (measured: Expo/ASM/Hooks/I.c = 0x7AFA6BFA00000001).  Upstream relied on
 * the JVM narrowing rule for `putstatic/putfield <f>:Z` (JVMS: value & 1), reaching
 * it as `l2i; putstatic :Z` or `<int>; putstatic :Z`.
 *
 * Java cannot express that, so the decompiler had to invent a boolean expression and
 * chose `!= 0`; javac baked it in.  `!= 0` and `& 1` agree only when the intended
 * value is true, so every ZKM boolean whose real value is FALSE reads TRUE.
 * Proven by tools/ctl (ASM-generated probe): putstatic Z of 42 -> false, 43 -> true.
 *
 *   A) 85 x  zkm$z(I)Z : `var0 != 0`      -> `(var0 & 1) != 0`   (765 call sites)
 *   B) 32 x  long form : `(x) != 0`       -> `(x & 1L) != 0L`    (insert LCONST_1;LAND)
 *
 * `& 1` is exactly what the JVM did upstream, so this is a zero-semantic-change
 * restoration, same family as the `byte varN -> int varN` cursor fix.
 *
 * Counts must match exactly: a pass that silently drifts to 0 is indistinguishable
 * from a pass that does nothing.  Operates on the product jar; inputs are read-only.
 */
public class BoolNarrowPass {
  static int zFixed = 0, bFixed = 0;
  static Set<String> allow = new HashSet<String>();
  static Set<String> hit = new HashSet<String>();

  static boolean fixZkmZ(MethodNode m) {
    if (!"zkm$z".equals(m.name) || !"(I)Z".equals(m.desc)) return false;
    InsnList n = new InsnList();
    LabelNode f = new LabelNode();
    n.add(new VarInsnNode(Opcodes.ILOAD, 0));
    n.add(new InsnNode(Opcodes.ICONST_1));
    n.add(new InsnNode(Opcodes.IAND));
    n.add(new JumpInsnNode(Opcodes.IFEQ, f));
    n.add(new InsnNode(Opcodes.ICONST_1));
    n.add(new InsnNode(Opcodes.IRETURN));
    n.add(f);
    n.add(new FrameNode(Opcodes.F_SAME, 0, null, 0, null));
    n.add(new InsnNode(Opcodes.ICONST_0));
    n.add(new InsnNode(Opcodes.IRETURN));
    m.instructions.clear(); m.tryCatchBlocks.clear(); m.localVariables = null;
    m.instructions.add(n); m.maxStack = 2; m.maxLocals = 1;
    zFixed++; return true;
  }

  static void fixFormB(String owner, MethodNode m) {
    for (AbstractInsnNode p = m.instructions.getFirst(); p != null; p = p.getNext()) {
      if (p.getOpcode() != Opcodes.PUTSTATIC && p.getOpcode() != Opcodes.PUTFIELD) continue;
      FieldInsnNode fi = (FieldInsnNode) p;
      if (!"Z".equals(fi.desc)) continue;
      if (!allow.contains(fi.owner + " " + fi.name)) continue;
      AbstractInsnNode lcmp = null; int hops = 0;
      for (AbstractInsnNode q = p.getPrevious(); q != null && hops < 14; q = q.getPrevious()) {
        if (q.getOpcode() < 0) continue;               // labels / frames / line numbers
        hops++;
        if (q.getOpcode() == Opcodes.LCMP) { lcmp = q; break; }
      }
      if (lcmp == null) continue;
      AbstractInsnNode z = null;
      for (AbstractInsnNode q = lcmp.getPrevious(); q != null; q = q.getPrevious()) {
        if (q.getOpcode() < 0) continue;
        if (q.getOpcode() == Opcodes.LCONST_0) { z = q; }
        break;
      }
      if (z == null) continue;
      AbstractInsnNode before = z.getPrevious();
      while (before != null && before.getOpcode() < 0) before = before.getPrevious();
      if (before != null && before.getOpcode() == Opcodes.LAND) continue;   // idempotent
      InsnList ins = new InsnList();
      ins.add(new InsnNode(Opcodes.LCONST_1));
      ins.add(new InsnNode(Opcodes.LAND));
      m.instructions.insertBefore(z, ins);
      if (m.maxStack < 6) m.maxStack += 2;
      hit.add(fi.owner + " " + fi.name);
      bFixed++;
    }
  }

  public static void main(String[] a) throws Exception {
    File jar = new File(a[0]);
    int expZ = Integer.parseInt(a[1]), expB = Integer.parseInt(a[2]);
    for (int i = 3; i < a.length; i++) allow.add(a[i]);
    // Buffer every entry, then rewrite the archive IN PLACE.  The previous
    // temp-file + delete + renameTo dance fails on Windows while the Jar task still
    // holds the file, and the failure is worse than it looks: NameMapPass has already
    // rewritten the jar by then, so the artifact ships with the reflective-name fix
    // applied and the 796-site boolean fix MISSING -- and Gradle then reports the jar
    // task UP-TO-DATE and never re-applies it.  That is a silent correctness hole.
    // NameMapPass already writes in place for exactly this reason; this pass had not
    // been updated to match.  Marker for any artifact: Expo/yT.zkm$z must contain
    // `iconst_1; iand`.
    java.util.LinkedHashMap<String, byte[]> buffered = new java.util.LinkedHashMap<String, byte[]>();
    java.util.LinkedHashMap<String, Long> times = new java.util.LinkedHashMap<String, Long>();
    ZipFile in = new ZipFile(jar);
    for (Enumeration<? extends ZipEntry> e = in.entries(); e.hasMoreElements();) {
      ZipEntry ze = e.nextElement();
      byte[] data = read(in.getInputStream(ze));
      if (ze.getName().endsWith(".class")) {
        ClassReader cr = new ClassReader(data);
        ClassNode cn = new ClassNode();
        cr.accept(cn, ClassReader.EXPAND_FRAMES);
        boolean touched = false;
        for (Object o : cn.methods) {
          MethodNode m = (MethodNode) o;
          if (m.instructions == null || m.instructions.size() == 0) continue;
          int z0 = zFixed, b0 = bFixed;
          fixZkmZ(m); fixFormB(cn.name, m);
          if (zFixed != z0 || bFixed != b0) touched = true;
        }
        if (touched) { ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS); cn.accept(cw); data = cw.toByteArray(); }
      }
      buffered.put(ze.getName(), data);
      times.put(ze.getName(), Long.valueOf(ze.getTime()));
    }
    in.close();
    List<String> miss = new ArrayList<String>(allow);
    miss.removeAll(hit);
    Collections.sort(miss);
    // Refuse BEFORE touching the artifact, so a count mismatch cannot leave a
    // half-patched jar behind.
    if (zFixed != expZ || bFixed != expB) {
      throw new IllegalStateException(
          "BoolNarrowPass count mismatch: zkm$z " + zFixed + "/" + expZ + ", formB " + bFixed + "/" + expB);
    }

    ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(jar)));
    try {
      for (java.util.Map.Entry<String, byte[]> en : buffered.entrySet()) {
        ZipEntry ne = new ZipEntry(en.getKey());
        ne.setTime(times.get(en.getKey()).longValue());
        out.putNextEntry(ne);
        out.write(en.getValue());
        out.closeEntry();
      }
    } finally {
      out.close();
    }

    // Read the artifact back and confirm the fix is actually in it.  A pass that
    // reports a count it did not ship is the failure mode this replaces.
    int seen = verifyShipped(jar);
    if (seen != expZ) {
      throw new IllegalStateException("post-write verification found " + seen
          + " narrowed zkm$z body/bodies in the artifact, expected " + expZ);
    }
    if (expZ == 0) {
      int left = countZkmZ(jar);
      if (left != 0) {
        throw new IllegalStateException("expected zkm$z to be gone from the artifact, "
            + "found " + left + " remaining declaration(s)");
      }
    }
  }

  static int countZkmZ(File jar) throws IOException {
    int n = 0;
    ZipFile z = new ZipFile(jar);
    try {
      for (Enumeration<? extends ZipEntry> e = z.entries(); e.hasMoreElements();) {
        ZipEntry ze = e.nextElement();
        if (!ze.getName().endsWith(".class")) continue;
        ClassNode cn = new ClassNode();
        new ClassReader(read(z.getInputStream(ze))).accept(cn, ClassReader.SKIP_CODE);
        for (Object o : cn.methods) if ("zkm$z".equals(((MethodNode) o).name)) n++;
      }
    } finally {
      z.close();
    }
    return n;
  }

  /** Counts {@code zkm$z} bodies that actually contain the {@code & 1} mask. */
  static int verifyShipped(File jar) throws IOException {
    int n = 0;
    ZipFile z = new ZipFile(jar);
    try {
      for (Enumeration<? extends ZipEntry> e = z.entries(); e.hasMoreElements();) {
        ZipEntry ze = e.nextElement();
        if (!ze.getName().endsWith(".class")) continue;
        ClassNode cn = new ClassNode();
        new ClassReader(read(z.getInputStream(ze))).accept(cn, ClassReader.EXPAND_FRAMES);
        for (Object o : cn.methods) {
          MethodNode m = (MethodNode) o;
          if (!"zkm$z".equals(m.name) || m.instructions == null) continue;
          for (int i = 0; i < m.instructions.size(); i++) {
            if (m.instructions.get(i).getOpcode() == org.objectweb.asm.Opcodes.IAND) { n++; break; }
          }
        }
      }
    } finally {
      z.close();
    }
    return n;
  }

  static byte[] read(InputStream is) throws IOException {
    ByteArrayOutputStream b = new ByteArrayOutputStream(); byte[] k = new byte[8192]; int n;
    while ((n = is.read(k)) > 0) b.write(k, 0, n); is.close(); return b.toByteArray();
  }
}
