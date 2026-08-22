package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.MoveEntityEvent;
import Expo.module.impl.movement.Stuck;

public final class StuckMoveEntityInvoker implements EventInvoker {
   final Stuck B;

   public StuckMoveEntityInvoker(Stuck var1) {
      this.B = var1;
   }

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 110460490090577L;
      this.B.onMoveEntity(var4, (MoveEntityEvent)var3);
   }
}
