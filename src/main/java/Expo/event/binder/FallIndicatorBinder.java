package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.PostTickEvent;
import Expo.event.events.Render2DEvent;
import Expo.event.invoker.FallIndicatorPostTickInvoker;
import Expo.event.invoker.FallIndicatorRender2DInvoker;
import Expo.module.impl.visual_utility.FallIndicator;











public final class FallIndicatorBinder {
   private static final long a = 139939118238575L;

   public static void r(EventBus var2, FallIndicator var3) {
      var2.R(var3, PostTickEvent.class, 3, new FallIndicatorPostTickInvoker(var3));
      var2.R(var3, Render2DEvent.class, 3, new FallIndicatorRender2DInvoker(var3));
   }

   private FallIndicatorBinder() {
   }

}
