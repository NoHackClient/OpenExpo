package Expo.ASM.Hooks.Player;

import Expo.ASM.Hooks.CallbackInfo;
import Expo.ExpoClient;
import Expo.event.events.MoveInputEvent;
import Expo.event.events.PostMoveInputEvent;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.MovementInput;

public class MovementInputHooks {
   private static final long a = 110068457662810L;

   public static void onUpdatePlayerMoveState(MovementInput var0, GameSettings var1, CallbackInfo var2) {
      var0.moveStrafe = 0.0F;
      var0.moveForward = 0.0F;
      if (var1.keyBindForward.isKeyDown()) {
         var0.moveForward++;
      }

      if (var1.keyBindBack.isKeyDown()) {
         var0.moveForward--;
      }

      if (var1.keyBindLeft.isKeyDown()) {
         var0.moveStrafe++;
      }

      if (var1.keyBindRight.isKeyDown()) {
         var0.moveStrafe--;
      }

      var0.jump = var1.keyBindJump.isKeyDown();
      var0.sneak = var1.keyBindSneak.isKeyDown();
      MoveInputEvent var11 = new MoveInputEvent(var0.moveForward, var0.moveStrafe, var0.jump, var0.sneak, 0.3);
      ExpoClient.w.e(var11, 18670087776179L);
      double var12 = var11.r();
      var0.moveForward = var11.t();
      var0.moveStrafe = var11.R();
      var0.jump = var11.d();
      var0.sneak = var11.b();
      if (var0.sneak) {
         var0.moveStrafe *= (float)var12;
         var0.moveForward *= (float)var12;
      }

      ExpoClient.w.e(new PostMoveInputEvent(11185, 1025946335), 18670087776179L);
      var2.cancel();
   }
}
