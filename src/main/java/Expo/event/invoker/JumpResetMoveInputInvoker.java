package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.MoveInputEvent;
import Expo.module.impl.combat.JumpReset;











public final class JumpResetMoveInputInvoker implements EventInvoker {
   final JumpReset x;

   public JumpResetMoveInputInvoker(JumpReset var1) {
      this.x = var1;
   }

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 88291719685871L;
      this.x.onMoveInput(var4, (MoveInputEvent)var3);
   }
}
