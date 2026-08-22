package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.PostRenderEvent;
import Expo.event.events.PreRenderEvent;
import Expo.event.invoker.ChamsPostRenderInvoker;
import Expo.event.invoker.ChamsPreRenderInvoker;
import Expo.module.impl.visual.Chams;











public final class ChamsBinder {
   private static final long a = 58488706269333L;

   public static void I(EventBus var0, Chams var1) {
      var0.R(var1, PreRenderEvent.class, 3, new ChamsPreRenderInvoker(var1));
      var0.R(var1, PostRenderEvent.class, 3, new ChamsPostRenderInvoker(var1));
   }

   private ChamsBinder() {
   }

}
