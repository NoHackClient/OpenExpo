package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.TickEvent;
import Expo.module.impl.combat.AutoBlock;











public final class AutoBlockTickInvoker implements EventInvoker {
   final AutoBlock g;

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 42073119542120L;
      this.g.onTick((TickEvent)var3, var4);
   }

   public AutoBlockTickInvoker(AutoBlock var1) {
      this.g = var1;
   }
}
