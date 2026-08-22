package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.RedirectIsUsingItemEvent;
import Expo.module.impl.combat.AutoBlock;

public final class AutoBlockRedirectIsUsingItemInvoker implements EventInvoker {
   final AutoBlock u;

   public AutoBlockRedirectIsUsingItemInvoker(AutoBlock var1) {
      this.u = var1;
   }

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 120308274787719L;
      this.u.onRedirectIsUsingItem((RedirectIsUsingItemEvent)var3, var4);
   }
}
