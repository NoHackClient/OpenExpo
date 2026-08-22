package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PreUpdateEvent;
import Expo.module.impl.player.InvManager;











public final class InvManagerPreUpdateInvoker implements EventInvoker {
   final InvManager y;

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 19057012125853L;
      this.y.onPreUpdate((PreUpdateEvent)var3, var4);
   }

   public InvManagerPreUpdateInvoker(InvManager var1) {
      this.y = var1;
   }
}
