package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.PreTickEvent;
import Expo.event.invoker.NoJumpDelayPreTickInvoker;
import Expo.module.impl.movement.NoJumpDelay;











public final class NoJumpDelayBinder {
   private static final long a = 66578939372017L;

   public static void T(EventBus var0, NoJumpDelay var3) {
      var0.R(var3, PreTickEvent.class, 3, new NoJumpDelayPreTickInvoker(var3));
   }

   private NoJumpDelayBinder() {
   }

}
