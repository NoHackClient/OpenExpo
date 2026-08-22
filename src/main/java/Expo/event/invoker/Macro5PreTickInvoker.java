package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PreTickEvent;
import Expo.module.impl.macro.Macro5;

public final class Macro5PreTickInvoker implements EventInvoker {
   final Macro5 X;

   public void c(long var1, Object var3) {
      this.X.onPreTick((PreTickEvent)var3);
   }

   public Macro5PreTickInvoker(Macro5 var1) {
      this.X = var1;
   }
}
