package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.PlayerGetNameEvent;
import Expo.event.events.PostTickEvent;
import Expo.event.invoker.MegaWallsDetectorPlayerGetNameInvoker;
import Expo.event.invoker.MegaWallsDetectorPostTickInvoker;
import Expo.module.impl.visual_utility.MegaWallsDetector;











public final class MegaWallsDetectorBinder {
   private static final long a = 80543477038354L;

   private MegaWallsDetectorBinder() {
   }

   public static void X(EventBus var0, int var1, MegaWallsDetector var4) {
      var0.R(var4, PostTickEvent.class, 3, new MegaWallsDetectorPostTickInvoker(var4));
      var0.R(var4, PlayerGetNameEvent.class, 3, new MegaWallsDetectorPlayerGetNameInvoker(var4));
   }

}
