package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.MoveInputEvent;
import Expo.util.RotationManager;











public final class RotationManagerMoveInputInvoker implements EventInvoker {
   final RotationManager S;

   public RotationManagerMoveInputInvoker(RotationManager var1) {
      this.S = var1;
   }

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 22170166670317L;
      this.S.onMoveInput((MoveInputEvent)var3, var4);
   }
}
