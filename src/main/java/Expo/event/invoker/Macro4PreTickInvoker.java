package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PreTickEvent;
import Expo.module.impl.macro.Macro4;

public final class Macro4PreTickInvoker implements EventInvoker {
   final Macro4 W;

   public Macro4PreTickInvoker(Macro4 var1) {
      this.W = var1;
   }

   public void c(long var1, Object var3) {
      this.W.onPreTick((PreTickEvent)var3);
   }
}
