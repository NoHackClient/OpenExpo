package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PreUpdateEvent;
import Expo.module.impl.player.InvClicker;

public final class InvClickerPreUpdateInvoker implements EventInvoker {
   final InvClicker a;

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 26615441337405L;
      this.a.onPreUpdate(var4, (PreUpdateEvent)var3);
   }

   public InvClickerPreUpdateInvoker(InvClicker var1) {
      this.a = var1;
   }
}
