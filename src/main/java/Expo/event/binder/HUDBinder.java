package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.Render2DEvent;
import Expo.event.invoker.HUDRender2DInvoker;
import Expo.module.impl.visual.HUD;

public final class HUDBinder {
   private static final long a = 107181550728945L;

   private HUDBinder() {
   }

   public static void H(EventBus var0, HUD var3) {
      var0.R(var3, Render2DEvent.class, 3, new HUDRender2DInvoker(var3));
   }
}
