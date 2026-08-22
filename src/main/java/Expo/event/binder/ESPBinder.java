package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.PostTickEvent;
import Expo.event.events.Render3DEvent;
import Expo.event.invoker.ESPPostTickInvoker;
import Expo.event.invoker.ESPRender3DInvoker;
import Expo.module.impl.visual_utility.ESP;











public final class ESPBinder {
   private static final long a = 24443157828286L;

   private ESPBinder() {
   }

   public static void Y(EventBus var0, ESP var3) {
      var0.R(var3, PostTickEvent.class, 3, new ESPPostTickInvoker(var3));
      var0.R(var3, Render3DEvent.class, 1, new ESPRender3DInvoker(var3));
   }
}
