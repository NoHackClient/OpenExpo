package Expo.ASM.Network;

import Expo.ASM.TransformerBase;
import Expo.ASM.Util.BytecodeHelper;
import Expo.ASM.Util.SrgNames;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;

public class TransformNetHandlerPlayClient extends TransformerBase {
   private static long d;

   public boolean s(ClassNode var1) {
      boolean var4 = false;
      var4 |= BytecodeHelper.t(
         var1,
         "(" + SrgNames.X("net/minecraft/network/play/server/S12PacketEntityVelocity") + ")V",
         (var0, var1x) -> {
            return TransformerBase.u(
               var1x,
               Type.VOID_TYPE,
               false,
               true,
               (var1xx, var2xx) -> {
                  BytecodeHelper.k(var1xx);
                  BytecodeHelper.n(var1xx, var1x, 0);
                  BytecodeHelper.I(var1xx, var2xx);
                  BytecodeHelper.Y(
                     var1xx,
                     A,
                     "onHandleEntityVelocity",
                     "("
                        + SrgNames.X("net/minecraft/client/network/NetHandlerPlayClient")
                        + SrgNames.X("net/minecraft/network/play/server/S12PacketEntityVelocity")
                        + z
                        + ")V"
                  );
               }
            );
         },
         "handleEntityVelocity",
         "handleEntityVelocity"
      );
      return var4 | BytecodeHelper.t(var1, "(" + SrgNames.X("net/minecraft/network/play/server/S02PacketChat") + ")V", (var0, var1x) -> {
         return TransformerBase.u(var1x, Type.VOID_TYPE, false, false, (var1xx, var2xx) -> {
            BytecodeHelper.n(var1xx, var1x, 0);
            BytecodeHelper.Y(var1xx, A, "handleChat", "(" + SrgNames.X("net/minecraft/network/play/server/S02PacketChat") + ")V");
         });
      }, "handleChat", "handleChat");
   }

   static {
      d = 44525063271188L;
   }

   public TransformNetHandlerPlayClient() {
      super("net/minecraft/client/network/NetHandlerPlayClient");
   }
}
