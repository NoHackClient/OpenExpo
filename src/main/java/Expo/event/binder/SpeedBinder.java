package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.PreUpdateEvent;
import Expo.event.invoker.SpeedPreUpdateInvoker;
import Expo.module.impl.movement.Speed;











public final class SpeedBinder {
   private static final long a = 126658714063862L;

   private SpeedBinder() {
   }

   public static void V(EventBus var2, Speed var3) {
      var2.R(var3, PreUpdateEvent.class, 3, new SpeedPreUpdateInvoker(var3));
   }

}
