package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PreTickEvent;
import Expo.module.impl.macro.Macro3;











public final class Macro3PreTickInvoker implements EventInvoker {
   final Macro3 Y;

   public Macro3PreTickInvoker(Macro3 var1) {
      this.Y = var1;
   }

   public void c(long var1, Object var3) {
      this.Y.onPreTick((PreTickEvent)var3);
   }
}
