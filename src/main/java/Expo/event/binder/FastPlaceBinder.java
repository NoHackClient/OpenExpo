package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.PostRightClickEvent;
import Expo.event.events.PreUpdateEvent;
import Expo.event.invoker.FastPlacePostRightClickInvoker;
import Expo.event.invoker.FastPlacePreUpdateInvoker;
import Expo.module.impl.world.FastPlace;











public final class FastPlaceBinder {
   private static final long a = 83807684013137L;

   public static void e(EventBus var0, FastPlace var1) {
      var0.R(var1, PreUpdateEvent.class, 3, new FastPlacePreUpdateInvoker(var1));
      var0.R(var1, PostRightClickEvent.class, 3, new FastPlacePostRightClickInvoker(var1));
   }

   private FastPlaceBinder() {
   }
}
