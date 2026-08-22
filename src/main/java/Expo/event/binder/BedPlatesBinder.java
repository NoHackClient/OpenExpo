package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.PreUpdateEvent;
import Expo.event.events.Render3DEvent;
import Expo.event.invoker.BedPlatesPreUpdateInvoker;
import Expo.event.invoker.BedPlatesRender3DInvoker;
import Expo.module.impl.visual_utility.BedPlates;











public final class BedPlatesBinder {
   private static final long a = 72714004120520L;

   public static void y(EventBus var0, BedPlates var3) {
      var0.R(var3, Render3DEvent.class, 3, new BedPlatesRender3DInvoker(var3));
      var0.R(var3, PreUpdateEvent.class, 3, new BedPlatesPreUpdateInvoker(var3));
   }

   private BedPlatesBinder() {
   }
}
