package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.PostTickEvent;
import Expo.event.invoker.SpeedMinePostTickInvoker;
import Expo.module.impl.world.SpeedMine;

public final class SpeedMineBinder {
   private static final long a = 98100373438868L;

   public static void H(EventBus var2, SpeedMine var3) {
      var2.R(var3, PostTickEvent.class, 3, new SpeedMinePostTickInvoker(var3));
   }

   private SpeedMineBinder() {
   }
}
