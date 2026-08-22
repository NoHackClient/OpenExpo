package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.MoveInputEvent;
import Expo.module.impl.movement.Stuck;

public final class StuckMoveInputInvoker implements EventInvoker {
   final Stuck b;

   public void c(long var1, Object var3) {
      this.b.onMoveInput((MoveInputEvent)var3);
   }

   public StuckMoveInputInvoker(Stuck var1) {
      this.b = var1;
   }
}
