package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.RedirectIsUsingItemEvent;
import Expo.event.invoker.NoSlowRedirectIsUsingItemInvoker;
import Expo.module.impl.movement.NoSlow;











public final class NoSlowBinder {
   private static final long a = 41986967085982L;

   private NoSlowBinder() {
   }

   public static void G(EventBus var0, NoSlow var1) {
      var0.R(var1, RedirectIsUsingItemEvent.class, 3, new NoSlowRedirectIsUsingItemInvoker(var1));
   }
}
