package Expo.ASM;

import Expo.ASM.Util.BytecodeHelper;
import Expo.ASM.Util.SrgNames;
import org.objectweb.asm.tree.ClassNode;

public class TransformLayerCape extends TransformerBase {
   private static long d;

   public boolean s(ClassNode var1) {
      return BytecodeHelper.t(
         var1,
         "(" + SrgNames.X("net/minecraft/client/entity/AbstractClientPlayer") + "FFFFFFF)V",
         (var0, var1x) -> {
            boolean var4 = TransformerBase.Q(
               var1x, BytecodeHelper.s("net/minecraft/client/renderer/GlStateManager", "()V", "pushMatrix", "pushMatrix"), 0, var1xx -> {
                  BytecodeHelper.n(var1xx, var1x, 0);
                  BytecodeHelper.Y(var1xx, n, "onRenderPre", "(" + SrgNames.X("net/minecraft/client/entity/AbstractClientPlayer") + ")V");
               }
            );
            return var4
               | TransformerBase.v(var1x, BytecodeHelper.s("net/minecraft/client/renderer/GlStateManager", "()V", "popMatrix", "popMatrix"), 0, var1xx -> {
                  BytecodeHelper.n(var1xx, var1x, 0);
                  BytecodeHelper.Y(var1xx, n, "onRenderPost", "(" + SrgNames.X("net/minecraft/client/entity/AbstractClientPlayer") + ")V");
               });
         },
         "doRenderLayer",
         "doRenderLayer"
      );
   }

   static {
      d = 99281639214915L;
   }

   public TransformLayerCape() {
      super("net/minecraft/client/renderer/entity/layers/LayerCape");
   }
}
