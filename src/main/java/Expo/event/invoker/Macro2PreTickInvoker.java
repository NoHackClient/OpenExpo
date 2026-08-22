package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PreTickEvent;
import Expo.module.impl.macro.Macro2;

public final class Macro2PreTickInvoker implements EventInvoker {
   final Macro2 S;

   public void c(long var1, Object var3) {
      this.S.onPreTick((PreTickEvent)var3);
   }

   public Macro2PreTickInvoker(Macro2 var1) {
      this.S = var1;
   }
}
