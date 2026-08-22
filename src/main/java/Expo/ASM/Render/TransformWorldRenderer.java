package Expo.ASM.Render;

import Expo.ASM.TransformerBase;
import Expo.ASM.Util.BytecodeHelper;
import Expo.ASM.Util.SrgNames;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;


public class TransformWorldRenderer extends TransformerBase {
   private static long d;

   static {
      d = 101653029728035L;
   }

   public TransformWorldRenderer() {
      super("net/minecraft/client/renderer/WorldRenderer");
   }

   public boolean s(ClassNode var1) {
      return BytecodeHelper.t(
         var1,
         "(FFFI)V",
         (var1x, var2x) -> {
            return TransformerBase.u(
               var2x,
               Type.VOID_TYPE,
               false,
               true,
               (var2xx, var3x) -> {
                  BytecodeHelper.k(var2xx);
                  BytecodeHelper.J(var2xx, var2x);
                  BytecodeHelper.k(var2xx);
                  BytecodeHelper.P(var2xx, var1.name, TransformerBase.j(var1, "Z", "noColor", "noColor", "h"), "Z");
                  BytecodeHelper.k(var2xx);
                  BytecodeHelper.P(
                     var2xx, var1.name, TransformerBase.j(var1, "Ljava/nio/IntBuffer;", "rawIntBuffer", "rawIntBuffer", "b"), "Ljava/nio/IntBuffer;"
                  );
                  BytecodeHelper.I(var2xx, var3x);
                  BytecodeHelper.Y(
                     var2xx, e, "putColorMultiplier", "(" + SrgNames.X("net/minecraft/client/renderer/WorldRenderer") + "FFFIZLjava/nio/IntBuffer;" + z + ")V"
                  );
               }
            );
         },
         "putColorMultiplier",
         "putColorMultiplier"
      );
   }


}
