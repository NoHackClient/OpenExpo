package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.PreTickEvent;
import Expo.event.invoker.Macro5PreTickInvoker;
import Expo.module.impl.macro.Macro5;











public final class Macro5Binder {
   private static final long a = 42229845778317L;

   private Macro5Binder() {
   }

   public static void j(EventBus var0, Macro5 var3) {
      var0.R(var3, PreTickEvent.class, 3, new Macro5PreTickInvoker(var3));
   }
}
