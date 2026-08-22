package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.Render3DEvent;
import Expo.event.invoker.BedESPRender3DInvoker;
import Expo.module.impl.visual_utility.BedESP;

public final class BedESPBinder {
   private static final long a = 70686049206666L;

   public static void Q(EventBus var0, BedESP var3) {
      var0.R(var3, Render3DEvent.class, 3, new BedESPRender3DInvoker(var3));
   }

   private BedESPBinder() {
   }
}
