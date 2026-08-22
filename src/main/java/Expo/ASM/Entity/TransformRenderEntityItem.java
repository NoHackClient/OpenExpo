package Expo.ASM.Entity;

import Expo.ASM.TransformerBase;
import Expo.ASM.Util.BytecodeHelper;
import Expo.ASM.Util.SrgNames;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;

public class TransformRenderEntityItem extends TransformerBase {
   private static long d;

   public boolean s(ClassNode var1) {
      return BytecodeHelper.t(
         var1,
         "(" + SrgNames.X("net/minecraft/entity/item/EntityItem") + "DDDF" + SrgNames.X("net/minecraft/client/resources/model/IBakedModel") + ")I",
         (var0, var1x) -> {
            return TransformerBase.u(
               var1x,
               Type.INT_TYPE,
               true,
               true,
               (var1xx, var2xx) -> {
                  BytecodeHelper.k(var1xx);
                  BytecodeHelper.J(var1xx, var1x);
                  BytecodeHelper.I(var1xx, var2xx);
                  BytecodeHelper.Y(
                     var1xx,
                     u,
                     "onRenderEntityItem",
                     "("
                        + SrgNames.X("net/minecraft/client/renderer/entity/RenderEntityItem")
                        + SrgNames.X("net/minecraft/entity/item/EntityItem")
                        + "DDDF"
                        + SrgNames.X("net/minecraft/client/resources/model/IBakedModel")
                        + I
                        + ")V"
                  );
               }
            );
         },
         "func_177077_a"
      );
   }

   static {
      d = 99512118562804L;
   }

   public TransformRenderEntityItem() {
      super("net/minecraft/client/renderer/entity/RenderEntityItem");
   }
}
