package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.UpdateWalkingPlayerEvent;
import Expo.event.invoker.FastFallUpdateWalkingPlayerInvoker;
import Expo.module.impl.movement.FastFall;

public final class FastFallBinder {
   private static final long a = 88653331019355L;

   public static void O(EventBus var0, FastFall var3) {
      var0.R(var3, UpdateWalkingPlayerEvent.class, 3, new FastFallUpdateWalkingPlayerInvoker(var3));
   }

   private FastFallBinder() {
   }
}
