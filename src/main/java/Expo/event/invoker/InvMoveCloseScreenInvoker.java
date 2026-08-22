package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.CloseScreenEvent;
import Expo.module.impl.movement.InvMove;











public final class InvMoveCloseScreenInvoker implements EventInvoker {
   final InvMove V;

   public InvMoveCloseScreenInvoker(InvMove var1) {
      this.V = var1;
   }

   public void c(long var1, Object var3) {
      this.V.onCloseScreen((CloseScreenEvent)var3);
   }
}
