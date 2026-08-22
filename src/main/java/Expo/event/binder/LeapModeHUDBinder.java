package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.ReceivePacketEvent;
import Expo.event.events.Render2DEvent;
import Expo.event.invoker.LeapModeHUDReceivePacketInvoker;
import Expo.event.invoker.LeapModeHUDRender2DInvoker;
import Expo.module.impl.visual_utility.LeapModeHUD;











public final class LeapModeHUDBinder {
   private static final long a = 38923866201901L;

   public static void k(EventBus var0, LeapModeHUD var1) {
      var0.R(var1, ReceivePacketEvent.class, 3, new LeapModeHUDReceivePacketInvoker(var1));
      var0.R(var1, Render2DEvent.class, 3, new LeapModeHUDRender2DInvoker(var1));
   }

   private LeapModeHUDBinder() {
   }

}
