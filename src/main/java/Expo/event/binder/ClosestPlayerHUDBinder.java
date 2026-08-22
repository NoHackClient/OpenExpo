package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.PostTickEvent;
import Expo.event.events.Render2DEvent;
import Expo.event.invoker.ClosestPlayerHUDPostTickInvoker;
import Expo.event.invoker.ClosestPlayerHUDRender2DInvoker;
import Expo.module.impl.visual_utility.ClosestPlayerHUD;

public final class ClosestPlayerHUDBinder {
   private static final long a = 63021179349595L;

   private ClosestPlayerHUDBinder() {
   }

   public static void k(EventBus var0, ClosestPlayerHUD var3) {
      var0.R(var3, PostTickEvent.class, 3, new ClosestPlayerHUDPostTickInvoker(var3));
      var0.R(var3, Render2DEvent.class, 3, new ClosestPlayerHUDRender2DInvoker(var3));
   }
}
