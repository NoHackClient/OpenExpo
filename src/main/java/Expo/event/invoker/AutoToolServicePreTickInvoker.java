package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PreTickEvent;
import Expo.util.AutoToolService;











public final class AutoToolServicePreTickInvoker implements EventInvoker {
   final AutoToolService U;

   public AutoToolServicePreTickInvoker(AutoToolService var1) {
      this.U = var1;
   }

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 12862396936086L;
      this.U.onPreTick(var4, (PreTickEvent)var3);
   }
}
