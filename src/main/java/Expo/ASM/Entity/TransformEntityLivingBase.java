package Expo.ASM.Entity;

import Expo.ASM.TransformerBase;
import Expo.ASM.Util.BytecodeHelper;
import Expo.ASM.Util.SrgNames;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;


public class TransformEntityLivingBase extends TransformerBase {
   private static long d;

   public TransformEntityLivingBase() {
      super("net/minecraft/entity/EntityLivingBase");
   }

   static {
      d = 53417989733027L;
   }


   public boolean s(ClassNode var1) {
      boolean var4 = false;
      var4 |= BytecodeHelper.t(var1, "(FF)F", (var0, var1x) -> {
         return TransformerBase.u(var1x, Type.FLOAT_TYPE, true, true, (var1xx, var2xx) -> {
            BytecodeHelper.k(var1xx);
            BytecodeHelper.J(var1xx, var1x);
            BytecodeHelper.I(var1xx, var2xx);
            BytecodeHelper.Y(var1xx, m, "EntityLivingBase$onFunc_110146_f", "(" + SrgNames.X("net/minecraft/entity/EntityLivingBase") + "FF" + I + ")V");
         });
      }, "updateDistance", "updateDistance");
      var4 |= BytecodeHelper.t(var1, "()V", (var0, var1x) -> {
         return TransformerBase.u(var1x, Type.VOID_TYPE, false, true, (var0x, var1xx) -> {
            long var2xx = d ^ 20824177982336L;
            BytecodeHelper.k(var0x);
            BytecodeHelper.I(var0x, var1xx);
            BytecodeHelper.Y(var0x, m, "EntityLivingBase$onJump", "(" + SrgNames.X("net/minecraft/entity/EntityLivingBase") + z + ")V");
         });
      }, "jump", "jump");
      var4 |= BytecodeHelper.t(
         var1,
         "(FF)V",
         (var0, var1x) -> {
            boolean var4x = TransformerBase.u(var1x, Type.VOID_TYPE, false, true, (var0x, var1xx) -> {
               long var2xx = d ^ 73506373684277L;
               BytecodeHelper.k(var0x);
               BytecodeHelper.I(var0x, var1xx);
               BytecodeHelper.Y(var0x, m, "EntityLivingBase$onMoveEntityWithHeading", "(" + SrgNames.X("net/minecraft/entity/EntityLivingBase") + z + ")V");
            });
            return var4x
               | BytecodeHelper.R(
                  var1x,
                  BytecodeHelper.s("net/minecraft/entity/EntityLivingBase", "(FFF)V", "moveFlying", "moveFlying"),
                  (var0x, var1xx) -> {
                     long var2xx = d ^ 35721781835591L;
                     BytecodeHelper.k(var0x);
                     BytecodeHelper.Y(
                        var0x,
                        m,
                        "EntityLivingBase$onMoveFlying",
                        "(" + SrgNames.X("net/minecraft/entity/EntityLivingBase") + "FFF" + SrgNames.X("net/minecraft/entity/EntityLivingBase") + ")V"
                     );
                  }
               );
         },
         "moveEntityWithHeading",
         "moveEntityWithHeading"
      );
      var4 |= BytecodeHelper.t(
         var1,
         "(" + SrgNames.X("net/minecraft/util/DamageSource") + ")V",
         (var0, var1x) -> {
            return TransformerBase.u(
               var1x,
               Type.VOID_TYPE,
               false,
               false,
               (var1xx, var2xx) -> {
                  BytecodeHelper.k(var1xx);
                  BytecodeHelper.n(var1xx, var1x, 0);
                  BytecodeHelper.Y(
                     var1xx,
                     m,
                     "EntityLivingBase$onLivingDeath",
                     "(" + SrgNames.X("net/minecraft/entity/EntityLivingBase") + SrgNames.X("net/minecraft/util/DamageSource") + ")V"
                  );
               }
            );
         },
         "onDeath",
         "onDeath"
      );
      return var4 | BytecodeHelper.t(var1, "()I", (var0, var1x) -> {
         return TransformerBase.M(var1x, Type.INT_TYPE, (var0x, var1xx) -> {
            long var2xx = d ^ 140144821571328L;
            BytecodeHelper.k(var0x);
            BytecodeHelper.I(var0x, var1xx);
            BytecodeHelper.Y(var0x, m, "EntityLivingBase$onGetArmSwingAnimationEnd", "(" + SrgNames.X("net/minecraft/entity/EntityLivingBase") + I + ")V");
         });
      }, "getArmSwingAnimationEnd", "getArmSwingAnimationEnd");
   }


}
