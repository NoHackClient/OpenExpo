package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.PreTickEvent;
import Expo.event.invoker.AutoToolServicePreTickInvoker;
import Expo.util.AutoToolService;











public final class AutoToolServiceBinder {
   private static final long a = 121902501407793L;

   public static void z(EventBus var2, AutoToolService var3) {
      var2.R(var3, PreTickEvent.class, 3, new AutoToolServicePreTickInvoker(var3));
   }

   private AutoToolServiceBinder() {
   }

}
