package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.PreUpdateEvent;
import Expo.event.events.Render2DEvent;
import Expo.event.invoker.ChestStealerPreUpdateInvoker;
import Expo.event.invoker.ChestStealerRender2DInvoker;
import Expo.module.impl.player.ChestStealer;











public final class ChestStealerBinder {
   private static final long a = 30793623120997L;

   private ChestStealerBinder() {
   }

   public static void W(EventBus var2, ChestStealer var3) {
      var2.R(var3, PreUpdateEvent.class, 3, new ChestStealerPreUpdateInvoker(var3));
      var2.R(var3, Render2DEvent.class, 3, new ChestStealerRender2DInvoker(var3));
   }
}
