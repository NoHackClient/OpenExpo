package Expo.ASM.Network;

import Expo.ASM.TransformerBase;
import Expo.ASM.Util.BytecodeHelper;
import Expo.ASM.Util.SrgNames;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;


public class TransformS12PacketEntityVelocity extends TransformerBase {
   private static long d;


   static {
      d = 83282681363546L;
   }

   public boolean s(ClassNode var1) {
      return BytecodeHelper.t(
         var1,
         "(" + SrgNames.X("net/minecraft/network/play/INetHandlerPlayClient") + ")V",
         (var0, var1x) -> {
            return TransformerBase.u(
               var1x,
               Type.VOID_TYPE,
               false,
               true,
               (var1xx, var2xx) -> {
                  BytecodeHelper.n(var1xx, var1x, 0);
                  BytecodeHelper.k(var1xx);
                  BytecodeHelper.I(var1xx, var2xx);
                  BytecodeHelper.Y(
                     var1xx,
                     A,
                     "onProcessEntityVelocity",
                     "("
                        + SrgNames.X("net/minecraft/network/play/INetHandlerPlayClient")
                        + SrgNames.X("net/minecraft/network/play/server/S12PacketEntityVelocity")
                        + z
                        + ")V"
                  );
               }
            );
         },
         "processPacket",
         "processPacket"
      );
   }


   public TransformS12PacketEntityVelocity() {
      super("net/minecraft/network/play/server/S12PacketEntityVelocity");
   }
}
