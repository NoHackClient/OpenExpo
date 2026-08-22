package Expo.ASM.Block;

import Expo.ASM.TransformerBase;
import Expo.ASM.Util.BytecodeHelper;
import Expo.ASM.Util.SrgNames;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;


public class TransformBlockModelRenderer extends TransformerBase {
   private static long d;


   public boolean s(ClassNode var1) {
      return BytecodeHelper.t(
         var1,
         "("
            + SrgNames.X("net/minecraft/world/IBlockAccess")
            + SrgNames.X("net/minecraft/client/resources/model/IBakedModel")
            + SrgNames.X("net/minecraft/block/state/IBlockState")
            + SrgNames.X("net/minecraft/util/BlockPos")
            + SrgNames.X("net/minecraft/client/renderer/WorldRenderer")
            + "Z)Z",
         (var0, var1x) -> {
            return TransformerBase.u(
               var1x,
               Type.BOOLEAN_TYPE,
               true,
               true,
               (var1xx, var2xx) -> {
                  BytecodeHelper.J(var1xx, var1x);
                  BytecodeHelper.k(var1xx);
                  BytecodeHelper.I(var1xx, var2xx);
                  BytecodeHelper.Y(
                     var1xx,
                     g,
                     "renderModel",
                     "("
                        + SrgNames.X("net/minecraft/world/IBlockAccess")
                        + SrgNames.X("net/minecraft/client/resources/model/IBakedModel")
                        + SrgNames.X("net/minecraft/block/state/IBlockState")
                        + SrgNames.X("net/minecraft/util/BlockPos")
                        + SrgNames.X("net/minecraft/client/renderer/WorldRenderer")
                        + "Z"
                        + SrgNames.X("net/minecraft/client/renderer/BlockModelRenderer")
                        + I
                        + ")V"
                  );
               }
            );
         },
         "renderModel",
         "renderModel"
      );
   }

   static {
      d = 124368607563643L;
   }

   public TransformBlockModelRenderer() {
      super("net/minecraft/client/renderer/BlockModelRenderer");
   }

}
