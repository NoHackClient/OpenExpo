package Expo.ASM.Block;

import Expo.ASM.TransformerBase;
import Expo.ASM.Util.BytecodeHelper;
import Expo.ASM.Util.SrgNames;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;


public class TransformBlockRendererDispatcher extends TransformerBase {
   private static long d;

   public boolean s(ClassNode var1) {
      return BytecodeHelper.t(
         var1,
         "("
            + SrgNames.X("net/minecraft/block/state/IBlockState")
            + SrgNames.X("net/minecraft/util/BlockPos")
            + SrgNames.X("net/minecraft/world/IBlockAccess")
            + SrgNames.X("net/minecraft/client/renderer/WorldRenderer")
            + ")Z",
         (var0, var1x) -> {
            return TransformerBase.u(var1x, Type.BOOLEAN_TYPE, false, false, (var1xx, var2xx) -> {
               BytecodeHelper.n(var1xx, var1x, 0);
               BytecodeHelper.n(var1xx, var1x, 1);
               BytecodeHelper.Y(var1xx, r, "onRenderBlock", "(" + SrgNames.X("net/minecraft/block/state/IBlockState") + SrgNames.X("net/minecraft/util/BlockPos") + ")V");
            });
         },
         "renderBlock",
         "renderBlock"
      );
   }


   public TransformBlockRendererDispatcher() {
      super("net/minecraft/client/renderer/BlockRendererDispatcher");
   }


   static {
      d = 38228327796908L;
   }
}
