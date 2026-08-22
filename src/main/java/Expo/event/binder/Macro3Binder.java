package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.PreTickEvent;
import Expo.event.invoker.Macro3PreTickInvoker;
import Expo.module.impl.macro.Macro3;











public final class Macro3Binder {
   private static final long a = 105394970255724L;

   public static void V(EventBus var0, Macro3 var1) {
      var0.R(var1, PreTickEvent.class, 3, new Macro3PreTickInvoker(var1));
   }

   private Macro3Binder() {
   }

}
