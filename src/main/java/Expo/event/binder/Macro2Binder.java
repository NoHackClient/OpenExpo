package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.PreTickEvent;
import Expo.event.invoker.Macro2PreTickInvoker;
import Expo.module.impl.macro.Macro2;











public final class Macro2Binder {
   private static final long a = 93205702104173L;

   private Macro2Binder() {
   }

   public static void d(EventBus var0, Macro2 var3) {
      var0.R(var3, PreTickEvent.class, 3, new Macro2PreTickInvoker(var3));
   }

}
