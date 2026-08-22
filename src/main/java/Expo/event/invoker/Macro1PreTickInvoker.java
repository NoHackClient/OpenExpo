package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PreTickEvent;
import Expo.module.impl.macro.Macro1;

public final class Macro1PreTickInvoker implements EventInvoker {
   final Macro1 P;

   public Macro1PreTickInvoker(Macro1 var1) {
      this.P = var1;
   }

   public void c(long var1, Object var3) {
      this.P.onPreTick((PreTickEvent)var3);
   }
}
