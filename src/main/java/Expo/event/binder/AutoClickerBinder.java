package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.PreTickEvent;
import Expo.event.invoker.AutoClickerPreTickInvoker;
import Expo.module.impl.combat.AutoClicker;

public final class AutoClickerBinder {
   private static final long a = 96604313955593L;

   public static void N( EventBus var2, AutoClicker var3) {
      var2.R(var3, PreTickEvent.class, 3, new AutoClickerPreTickInvoker(var3));
   }

   private AutoClickerBinder() {
   }
}
