package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.MoveEntityWithHeadingEvent;
import Expo.module.impl.movement.Stuck;











public final class StuckMoveEntityWithHeadingInvoker implements EventInvoker {
   final Stuck U;

   public StuckMoveEntityWithHeadingInvoker(Stuck var1) {
      this.U = var1;
   }

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 436316417804L;
      this.U.onMoveEntityWithHeading(var4, (MoveEntityWithHeadingEvent)var3);
   }
}
