package Expo.ASM.World;

import Expo.ASM.TransformerBase;
import Expo.ASM.Util.BytecodeHelper;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;

public class TransformWorldClient extends TransformerBase {
   private static long d;

   public TransformWorldClient() {
      super("net/minecraft/client/multiplayer/WorldClient");
   }

   static {
      d = 32551282002734L;
   }

   public boolean s(ClassNode var1) {
      return BytecodeHelper.t(var1, "(III)V", (var0, var1x) -> {
         return TransformerBase.u(var1x, Type.VOID_TYPE, false, true, (var0x, var1xx) -> {
            BytecodeHelper.I(var0x, var1xx);
            BytecodeHelper.Y(var0x, T, "onDoVoidFogParticles", "(" + z + ")V");
         });
      }, "doVoidFogParticles", "doVoidFogParticles");
   }
}
