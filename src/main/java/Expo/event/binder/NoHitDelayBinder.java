package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.ClickMouseEvent;
import Expo.event.events.PostClickMouseEvent;
import Expo.event.events.PostTickEvent;
import Expo.event.invoker.NoHitDelayClickMouseInvoker;
import Expo.event.invoker.NoHitDelayPostClickMouseInvoker;
import Expo.event.invoker.NoHitDelayPostTickInvoker;
import Expo.module.impl.player.NoHitDelay;

public final class NoHitDelayBinder {
   private static final long a = 131580852294153L;

   public static void k(EventBus var2, NoHitDelay var3) {
      var2.R(var3, ClickMouseEvent.class, 3, new NoHitDelayClickMouseInvoker(var3));
      var2.R(var3, PostClickMouseEvent.class, 3, new NoHitDelayPostClickMouseInvoker(var3));
      var2.R(var3, PostTickEvent.class, 3, new NoHitDelayPostTickInvoker(var3));
   }

   private NoHitDelayBinder() {
   }
}
