package Expo.ASM.Util;

import Expo.ASM.Hooks.CallbackInfo;
import Expo.ASM.Hooks.CallbackInfoReturnable;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TypeInsnNode;
import org.objectweb.asm.tree.VarInsnNode;

public class BytecodeHelper implements Opcodes {
   private static long[] e;
   private static long a;
   public static String k;
   public static String P;

   public static int r(MethodNode var0, InsnList var1, Type var2, int var3) {
      int var6 = t(var0, Type.getObjectType(k));
      var1.add(new TypeInsnNode(187, k));
      var1.add(new InsnNode(89));
      var1.add(new VarInsnNode(R(var2), var3));
      I(var1, var2);
      var1.add(new MethodInsnNode(183, k, "<init>", "(Ljava/lang/Object;)V", false));
      var1.add(new VarInsnNode(58, var6));
      return var6;
   }

   public static void e(InsnList var0, Object var1) {
      var0.add(new LdcInsnNode(var1));
   }

   public static int R(Type var0) {
      switch (var0.getSort()) {
         case 1:
         case 2:
         case 3:
         case 4:
         case 5:
            return 21;
         case 6:
            return 23;
         case 7:
            return 22;
         case 8:
            return 24;
         default:
            return 25;
      }
   }

   public static MethodInsnMatcher s(String var0, String var1, String... var2) {
      return var3 -> {
         return AsmUtil.b(var3, var0, var1, var2);
      };
   }

   private BytecodeHelper() {
   }

   static {
      a = 61928475667890L;
      e = new long[]{-5429171985177711537L, 6187635497878269435L, -6581119459714187126L, 7107969073356968743L, 2820024596290804831L, 7337315615374374430L, 3706939413501503167L, -4317051446622828700L, -8999311426859473138L, -196078507611109492L, 8273881709042227883L, -8927539407399229563L, 1086641127646846090L, 5060027598279406223L, -6896644230366819460L, -7724244888761978027L, -8558951859111784474L, -8287679933341875785L, 4989937912086212935L, 1307340829104811771L, 1516848615258668850L, 8328543278176812516L, 566518700464235520L, 4386890919040497072L, -7452953066216032776L, -636690406231162664L, 1671852792684224212L, 140819537226725359L, -5699305053711291737L, 4219450700601667714L, 5240827103556161155L, 4446872206910589127L, -9213117635459321421L, 1735559972631884227L, -4570162767749151689L, 5687421691262585797L, 4730486941700652670L};
      P = Type.getInternalName(CallbackInfo.class);
      k = Type.getInternalName(CallbackInfoReturnable.class);
   }

   public static boolean a() {
      return false;
   }

   public static void x(InsnList var0, String var1, String var2, String var3) {
      boolean var6 = AsmUtil.y$r1(var1);
      ((InsnList)var0).add((AbstractInsnNode)new FieldInsnNode(181, var6 ? AsmUtil.D(var1) : var1, var6 ? AsmUtil.q(var1, var2) : var2, AsmUtil.v(var3)));
   }

   public static void n(InsnList var0, MethodNode var1, int var2) {
      Type[] var5 = Type.getArgumentTypes(var1.desc);
      int var6 = (var1.access & 8) == 0 ? 1 : 0;

      for (int var7 = 0; var7 < var2; var7++) {
         var6 += var5[var7].getSize();
      }

      var0.add(new VarInsnNode(R(var5[var2]), var6));
   }

   public static int D(Type var0) {
      switch (var0.getSort()) {
         case 1:
         case 2:
         case 3:
         case 4:
         case 5:
            return 54;
         case 6:
            return 56;
         case 7:
            return 55;
         case 8:
            return 57;
         default:
            return 58;
      }
   }

   public static boolean R(MethodNode var0, MethodInsnMatcher var1, MethodInsnAction var2) {
      boolean var5 = false;

      for (AbstractInsnNode var6 = var0.instructions.getFirst(); var6 != null; var6 = var6.getNext()) {
         if (var6 instanceof MethodInsnNode && var1.A((MethodInsnNode)var6)) {
            MethodInsnNode var7 = (MethodInsnNode)var6;
            InsnList var8 = new InsnList();
            var2.t(var8, var7);
            var0.instructions.insertBefore(var6, var8);
            var0.instructions.remove(var6);
            var5 = true;
         }
      }

      return var5;
   }

   public static void k(InsnList var0) {
      var0.add(new VarInsnNode(25, 0));
   }

   public static int t(MethodNode var0, Type var1) {
      int var4 = var0.maxLocals;
      var0.maxLocals = var0.maxLocals + var1.getSize();
      return var4;
   }

   public static boolean A(MethodNode var0, ReturnValueSiteEmitter var1) {
      boolean var4 = false;
      Type var5 = Type.getReturnType(var0.desc);

      for (AbstractInsnNode var6 = var0.instructions.getFirst(); var6 != null; var6 = var6.getNext()) {
         if (var6.getOpcode() == N(var5)) {
            InsnList var7 = new InsnList();
            if (var5.getSort() == 0) {
               int var8 = O(var0, var7);
               var1.V(var7, var8, -1);
            } else {
               int var10 = t(var0, var5);
               var7.add(new VarInsnNode(D(var5), var10));
               int var9 = r(var0, var7, var5, var10);
               var1.V(var7, var9, var10);
               E(var7, var9, var5);
               var7.add(new VarInsnNode(R(var5), var10));
            }

            var0.instructions.insertBefore(var6, var7);
            var4 = true;
         }
      }

      return var4;
   }

   public static boolean w(ClassNode var0, MethodPredicate var1, String... var2) {
      boolean var5 = false;

      for (Object var7 : var0.methods) {
         MethodNode var8 = (MethodNode)var7;
         if (AsmUtil.t(var0.name, var8, var2)) {
            var5 |= var1.x(var0, var8);
         }
      }

      return var5;
   }

   public static void y(InsnList var0, Type var1) {
      switch (var1.getSort()) {
         case 1:
            var0.add(new TypeInsnNode(192, "java/lang/Boolean"));
            var0.add(new MethodInsnNode(182, "java/lang/Boolean", "booleanValue", "()Z", false));
            return;
         case 2:
            var0.add(new TypeInsnNode(192, "java/lang/Character"));
            var0.add(new MethodInsnNode(182, "java/lang/Character", "charValue", "()C", false));
            return;
         case 3:
            var0.add(new TypeInsnNode(192, "java/lang/Byte"));
            var0.add(new MethodInsnNode(182, "java/lang/Byte", "byteValue", "()B", false));
            return;
         case 4:
            var0.add(new TypeInsnNode(192, "java/lang/Short"));
            var0.add(new MethodInsnNode(182, "java/lang/Short", "shortValue", "()S", false));
            return;
         case 5:
            var0.add(new TypeInsnNode(192, "java/lang/Integer"));
            var0.add(new MethodInsnNode(182, "java/lang/Integer", "intValue", "()I", false));
            return;
         case 6:
            var0.add(new TypeInsnNode(192, "java/lang/Float"));
            var0.add(new MethodInsnNode(182, "java/lang/Float", "floatValue", "()F", false));
            return;
         case 7:
            var0.add(new TypeInsnNode(192, "java/lang/Long"));
            var0.add(new MethodInsnNode(182, "java/lang/Long", "longValue", "()J", false));
            return;
         case 8:
            var0.add(new TypeInsnNode(192, "java/lang/Double"));
            var0.add(new MethodInsnNode(182, "java/lang/Double", "doubleValue", "()D", false));
            return;
         default:
            var0.add(new TypeInsnNode(192, var1.getInternalName()));
      }
   }

   private static byte[] i(byte[] var0, ClassNodeTransform var1, String var2) throws Throwable {
      ClassReader var5 = new ClassReader(var0);
      ClassNode var6 = new ClassNode();
      var5.accept(var6, 8);
      AsmUtil.r(var2);
      AsmUtil.L(var6);

      try {
         boolean var7 = var1.d(var6);
         return !var7 ? var0 : f(var5, var6);
      } finally {
         AsmUtil.a();
         AsmUtil.O();
      }
   }

   public static boolean B(MethodNode var0, Type var1, MethodHeadEmitter var2, boolean var3) {
      InsnList var6 = new InsnList();
      int var7 = var2.s() ? S(var0, var6) : O(var0, var6);
      var2.U(var6, var7);
      if (var3) {
         E(var6, var7, var1);
      }

      var0.instructions.insert(var6);
      return true;
   }

   public static byte[] f(ClassReader var0, ClassNode var1) {
      int var4 = a() ? 3 : 1;

      try {
         SafeClassWriter var5 = new SafeClassWriter(var0, var4);
         var1.accept(var5);
         return var5.toByteArray();
      } catch (Throwable var7) {
         SafeClassWriter var6 = new SafeClassWriter(var0, 1);
         var1.accept(var6);
         return var6.toByteArray();
      }
   }

   public static int O(MethodNode var0, InsnList var1) {
      int var4 = t(var0, Type.getObjectType(P));
      var1.add(new TypeInsnNode(187, P));
      var1.add(new InsnNode(89));
      var1.add(new MethodInsnNode(183, P, "<init>", "()V", false));
      var1.add(new VarInsnNode(58, var4));
      return var4;
   }

   public static boolean y(MethodNode var0, MethodInsnMatcher var1, int var2, MethodInsnAction var3) {
      int var6 = 0;
      AbstractInsnNode var7 = var0.instructions.getFirst();

      while (var7 != null) {
         AbstractInsnNode var8 = var7.getNext();
         if (var7 instanceof MethodInsnNode) {
            MethodInsnNode var9 = (MethodInsnNode)var7;
            if (var1.A(var9) && var6++ == var2) {
               InsnList var10 = new InsnList();
               var3.t(var10, var9);
               var0.instructions.insertBefore(var7, var10);
               var0.instructions.remove(var7);
               return true;
            }
         }

         var7 = var8;
      }

      return false;
   }

   public static boolean Q(MethodNode var0, MethodInsnMatcher var1, InsnEditor var2) {
      boolean var5 = false;

      for (AbstractInsnNode var6 = var0.instructions.getFirst(); var6 != null; var6 = var6.getNext()) {
         if (var6 instanceof MethodInsnNode && var1.A((MethodInsnNode)var6)) {
            InsnList var7 = new InsnList();
            var2.D(var7);
            var0.instructions.insert(var6, var7);
            var5 = true;
         }
      }

      return var5;
   }

   public static boolean t(ClassNode var0, String var1, MethodPredicate var2, String... var3) {
      boolean var6 = false;

      for (Object var8 : var0.methods) {
         MethodNode var9 = (MethodNode)var8;
         if (AsmUtil.B(var0.name, var9, var1, var3)) {
            var6 |= var2.x(var0, var9);
         }
      }

      return var6;
   }

   public static void J(InsnList var0, MethodNode var1) {
      N(var0, var1, Type.getArgumentTypes(var1.desc).length);
   }

   public static byte[] G(byte[] var0, ClassNodeTransform var1) throws Throwable {
      for (String var7 : AsmUtil.z(var0)) {
         byte[] var8 = i(var0, var1, var7);
         if (var8 != var0) {
            return var8;
         }
      }

      return var0;
   }

   public static void I(InsnList var0, Type var1) {
      switch (var1.getSort()) {
         case 1:
            var0.add(new MethodInsnNode(184, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;", false));
            return;
         case 2:
            var0.add(new MethodInsnNode(184, "java/lang/Character", "valueOf", "(C)Ljava/lang/Character;", false));
            return;
         case 3:
            var0.add(new MethodInsnNode(184, "java/lang/Byte", "valueOf", "(B)Ljava/lang/Byte;", false));
            return;
         case 4:
            var0.add(new MethodInsnNode(184, "java/lang/Short", "valueOf", "(S)Ljava/lang/Short;", false));
            return;
         case 5:
            var0.add(new MethodInsnNode(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;", false));
            return;
         case 6:
            var0.add(new MethodInsnNode(184, "java/lang/Float", "valueOf", "(F)Ljava/lang/Float;", false));
            return;
         case 7:
            var0.add(new MethodInsnNode(184, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;", false));
            return;
         case 8:
            var0.add(new MethodInsnNode(184, "java/lang/Double", "valueOf", "(D)Ljava/lang/Double;", false));
            return;
      }
   }

   public static void D(InsnList var0, String var1, String var2, String var3) {
      boolean var6 = AsmUtil.y$r1(var1);
      ((InsnList)var0).add((AbstractInsnNode)new FieldInsnNode(180, var6 ? AsmUtil.D(var1) : var1, var6 ? AsmUtil.q(var1, var2) : var2, AsmUtil.v(var3)));
   }

   public static void I(InsnList var0, int var1) {
      var0.add(new VarInsnNode(25, var1));
   }

   public static boolean H(MethodNode var0, MethodInsnMatcher var1, InsnEditor var2) {
      boolean var5 = false;

      for (AbstractInsnNode var6 = var0.instructions.getFirst(); var6 != null; var6 = var6.getNext()) {
         if (var6 instanceof MethodInsnNode && var1.A((MethodInsnNode)var6)) {
            InsnList var7 = new InsnList();
            var2.D(var7);
            var0.instructions.insertBefore(var6, var7);
            var5 = true;
         }
      }

      return var5;
   }

   public static void P(InsnList var0, String var1, String var2, String var3) {
      var0.add(new FieldInsnNode(180, var1, var2, AsmUtil.v(var3)));
   }

   public static MethodInsnMatcher W(String var0, String var1, String var2) {
      return var3 -> {
         return AsmUtil.b(var3, var0, var2, var1);
      };
   }

   public static boolean M(MethodNode var0, FieldInsnMatcher var1, InsnEditor var2) {
      boolean var5 = false;

      for (AbstractInsnNode var6 = var0.instructions.getFirst(); var6 != null; var6 = var6.getNext()) {
         if (var6 instanceof FieldInsnNode && var6.getOpcode() == 180 && var1.D((FieldInsnNode)var6)) {
            InsnList var7 = new InsnList();
            var2.D(var7);
            var0.instructions.insertBefore(var6, var7);
            var0.instructions.remove(var6);
            var5 = true;
         }
      }

      return var5;
   }

   public static boolean L(String var0, String... var1) {
      for (String var5 : var1) {
         if (var0.equals(var5)) {
            return true;
         }
      }

      return false;
   }

   public static int N(Type var0) {
      switch (var0.getSort()) {
         case 0:
            return 177;
         case 1:
         case 2:
         case 3:
         case 4:
         case 5:
            return 172;
         case 6:
            return 174;
         case 7:
            return 173;
         case 8:
            return 175;
         default:
            return 176;
      }
   }

   public static void e(InsnList var0, String var1, String var2, String var3) {
      var0.add(new FieldInsnNode(181, var1, var2, AsmUtil.v(var3)));
   }

   public static void N(InsnList var0, MethodNode var1, int var2) {
      for (int var5 = 0; var5 < var2; var5++) {
         n(var0, var1, var5);
      }
   }

   public static int S(MethodNode var0, InsnList var1) {
      int var4 = t(var0, Type.getObjectType(k));
      var1.add(new TypeInsnNode(187, k));
      var1.add(new InsnNode(89));
      var1.add(new MethodInsnNode(183, k, "<init>", "()V", false));
      var1.add(new VarInsnNode(58, var4));
      return var4;
   }

   public static FieldInsnMatcher r(String var0, String var1, String... var2) {
      return var3 -> {
         return AsmUtil.v(var3, var0, var1, var2);
      };
   }

   public static void U(InsnList var0, String var1, String var2, String var3) {
      boolean var6 = AsmUtil.y$r1(var1);
      ((InsnList)var0).add((AbstractInsnNode)new MethodInsnNode(182, var6 ? AsmUtil.D(var1) : var1, var6 ? AsmUtil.O(var1, var2, var3) : var2, AsmUtil.v(var3), false));
   }

   public static void E(InsnList var0, int var1, Type var2) {
      LabelNode var5 = new LabelNode();
      var0.add(new VarInsnNode(25, var1));
      var0.add(new MethodInsnNode(182, P, "isCancelled", "()Z", false));
      var0.add(new JumpInsnNode(153, var5));
      if (var2.getSort() != 0) {
         var0.add(new VarInsnNode(25, var1));
         var0.add(new MethodInsnNode(182, k, "getReturnValue", "()Ljava/lang/Object;", false));
         y(var0, var2);
      }

      var0.add(new InsnNode(N(var2)));
      var0.add(var5);
   }

   public static void n(InsnList var0, Class<?> var1, String var2, String var3) {
      Y(var0, Type.getInternalName(var1), var2, var3);
   }

   public static void Y(InsnList var0, String var1, String var2, String var3) {
      boolean var6 = AsmUtil.y$r1(var1);
      ((InsnList)var0).add((AbstractInsnNode)new MethodInsnNode(184, var6 ? AsmUtil.D(var1) : var1, var6 ? AsmUtil.O(var1, var2, var3) : var2, AsmUtil.v(var3), false));
   }
}
