package Expo.ASM.Player;

import Expo.ASM.TransformerBase;
import Expo.ASM.Util.BytecodeHelper;
import Expo.ASM.Util.SrgNames;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;

public class TransformMovementInputFromOptions extends TransformerBase {
   private static long d;

   public TransformMovementInputFromOptions() {
      super("net/minecraft/util/MovementInputFromOptions");
   }

   static {
      d = 10135976976134L;
   }

   public boolean s(ClassNode var1) {
      return BytecodeHelper.t(
         var1,
         "()V",
         (var1x, var2x) -> {
            return TransformerBase.u(
               var2x,
               Type.VOID_TYPE,
               false,
               true,
               (var1xx, var2xx) -> {
                  BytecodeHelper.k(var1xx);
                  BytecodeHelper.k(var1xx);
                  BytecodeHelper.P(
                     var1xx,
                     var1.name,
                     TransformerBase.j(var1, SrgNames.X("net/minecraft/client/settings/GameSettings"), "gameSettings", "gameSettings", "e"),
                     SrgNames.X("net/minecraft/client/settings/GameSettings")
                  );
                  BytecodeHelper.I(var1xx, var2xx);
                  BytecodeHelper.Y(
                     var1xx,
                     R,
                     "MovementInputFromOptions$onUpdatePlayerMoveState",
                     "(" + SrgNames.X("net/minecraft/util/MovementInput") + SrgNames.X("net/minecraft/client/settings/GameSettings") + z + ")V"
                  );
               }
            );
         },
         "updatePlayerMoveState",
         "updatePlayerMoveState"
      );
   }
}
