package Expo.ASM.Block;

import Expo.ASM.TransformerBase;
import Expo.ASM.Util.BytecodeHelper;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;

public class TransformBlockBarrier extends TransformerBase {
   private static long d;

   public boolean s(ClassNode var1) {
      boolean var4 = false;
      return var4 | BytecodeHelper.t(var1, "()I", (var0, var1x) -> {
         return TransformerBase.u(var1x, Type.INT_TYPE, true, true, (var0x, var1xx) -> {
            BytecodeHelper.I(var0x, var1xx);
            BytecodeHelper.Y(var0x, B, "getRenderType", "(" + I + ")V");
         });
      }, "getRenderType", "getRenderType");
   }

   static {
      d = 117845053442826L;
   }

   public TransformBlockBarrier() {
      super("net/minecraft/block/BlockBarrier");
   }
}
