package Expo.ASM.Render;

import Expo.ASM.TransformerBase;
import Expo.ASM.Util.BytecodeHelper;
import Expo.ASM.Util.SrgNames;
import org.objectweb.asm.tree.ClassNode;


public class TransformModelPlayer extends TransformerBase {
   private static long d;


   public TransformModelPlayer() {
      super("net/minecraft/client/model/ModelPlayer");
   }


   static {
      d = 134426372867376L;
   }

   public boolean s(ClassNode var1) {
      return BytecodeHelper.t(
         var1,
         "(" + SrgNames.X("net/minecraft/entity/Entity") + "FFFFFF)V",
         (var0, var1x) -> {
            boolean var4 = TransformerBase.Q(
               var1x, BytecodeHelper.s("net/minecraft/client/renderer/GlStateManager", "()V", "pushMatrix", "pushMatrix"), 0, var1xx -> {
                  BytecodeHelper.n(var1xx, var1x, 0);
                  BytecodeHelper.Y(var1xx, h, "onRenderPre", "(" + SrgNames.X("net/minecraft/entity/Entity") + ")V");
               }
            );
            return var4
               | TransformerBase.v(var1x, BytecodeHelper.s("net/minecraft/client/renderer/GlStateManager", "()V", "popMatrix", "popMatrix"), 0, var1xx -> {
                  BytecodeHelper.n(var1xx, var1x, 0);
                  BytecodeHelper.Y(var1xx, h, "onRenderPost", "(" + SrgNames.X("net/minecraft/entity/Entity") + ")V");
               });
         },
         "render",
         "render"
      );
   }

}
