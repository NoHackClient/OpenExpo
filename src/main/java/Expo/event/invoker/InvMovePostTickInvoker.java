package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PostTickEvent;
import Expo.module.impl.movement.InvMove;











public final class InvMovePostTickInvoker implements EventInvoker {
   final InvMove S;

   public InvMovePostTickInvoker(InvMove var1) {
      this.S = var1;
   }

   public void c(long var1, Object var3) {
      this.S.onPostTick((PostTickEvent)var3);
   }
}
