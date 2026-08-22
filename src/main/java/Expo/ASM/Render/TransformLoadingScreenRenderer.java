package Expo.ASM.Render;

import Expo.ASM.TransformerBase;
import Expo.ASM.Util.BytecodeHelper;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;

public class TransformLoadingScreenRenderer extends TransformerBase {
   private static long d;

   public TransformLoadingScreenRenderer() {
      super("net/minecraft/client/LoadingScreenRenderer");
   }

   static {
      d = 133475593182422L;
   }

   public boolean s(ClassNode var1) {
      return BytecodeHelper.t(var1, "(I)V", (var0, var1x) -> {
         return TransformerBase.u(var1x, Type.VOID_TYPE, false, true, (var1xx, var2xx) -> {
            BytecodeHelper.n(var1xx, var1x, 0);
            BytecodeHelper.I(var1xx, var2xx);
            BytecodeHelper.Y(var1xx, R, "LoadingScreenRenderer$forSkipProgress", "(I" + z + ")V");
         });
      }, "setLoadingProgress", "setLoadingProgress");
   }
}
