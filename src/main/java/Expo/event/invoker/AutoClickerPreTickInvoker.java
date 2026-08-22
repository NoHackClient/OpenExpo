package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PreTickEvent;
import Expo.module.impl.combat.AutoClicker;

public final class AutoClickerPreTickInvoker implements EventInvoker {
   final AutoClicker k;

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 57122495588779L;
      this.k.onPreTick(var4, (PreTickEvent)var3);
   }

   public AutoClickerPreTickInvoker(AutoClicker var1) {
      this.k = var1;
   }
}
