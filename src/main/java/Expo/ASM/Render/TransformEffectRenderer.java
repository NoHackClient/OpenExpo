package Expo.ASM.Render;

import Expo.ASM.TransformerBase;
import Expo.ASM.Util.BytecodeHelper;
import Expo.ASM.Util.SrgNames;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;

public class TransformEffectRenderer extends TransformerBase {
   private static long d;

   public boolean s(ClassNode var1) {
      boolean var4 = false;
      var4 |= BytecodeHelper.t(var1, "(" + SrgNames.X("net/minecraft/util/BlockPos") + SrgNames.X("net/minecraft/util/EnumFacing") + ")V", (var0, var1x) -> {
         return TransformerBase.u(var1x, Type.VOID_TYPE, false, true, (var0x, var1xx) -> {
            BytecodeHelper.I(var0x, var1xx);
            BytecodeHelper.Y(var0x, b, "cancelHitParticles", "(" + z + ")V");
         });
      }, "addBlockHitEffects", "addBlockHitEffects");
      return var4 | BytecodeHelper.t(var1, "(" + SrgNames.X("net/minecraft/util/BlockPos") + SrgNames.X("net/minecraft/block/state/IBlockState") + ")V", (var0, var1x) -> {
         return TransformerBase.u(var1x, Type.VOID_TYPE, false, true, (var0x, var1xx) -> {
            BytecodeHelper.I(var0x, var1xx);
            BytecodeHelper.Y(var0x, b, "cancelDestroyParticles", "(" + z + ")V");
         });
      }, "addBlockDestroyEffects", "addBlockDestroyEffects");
   }

   public TransformEffectRenderer() {
      super("net/minecraft/client/particle/EffectRenderer");
   }

   static {
      d = 72291789126318L;
   }
}
