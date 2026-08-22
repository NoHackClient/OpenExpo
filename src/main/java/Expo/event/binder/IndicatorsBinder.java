package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.PostTickEvent;
import Expo.event.events.Render2DEvent;
import Expo.event.invoker.IndicatorsPostTickInvoker;
import Expo.event.invoker.IndicatorsRender2DInvoker;
import Expo.module.impl.visual_utility.Indicators;











public final class IndicatorsBinder {
   private static final long a = 61334249458124L;

   private IndicatorsBinder() {
   }

   public static void J(EventBus var0, Indicators var3) {
      var0.R(var3, PostTickEvent.class, 3, new IndicatorsPostTickInvoker(var3));
      var0.R(var3, Render2DEvent.class, 3, new IndicatorsRender2DInvoker(var3));
   }

}
