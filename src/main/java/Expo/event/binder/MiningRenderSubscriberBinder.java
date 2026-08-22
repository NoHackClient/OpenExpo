package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.Render3DEvent;
import Expo.event.invoker.MiningRenderSubscriberRender3DInvoker;
import Expo.internal.MiningRenderSubscriber;











public final class MiningRenderSubscriberBinder {
   private static final long a = 84621309874761L;

   private MiningRenderSubscriberBinder() {
   }

   public static void n(EventBus var0, MiningRenderSubscriber var3) {
      var0.R(var3, Render3DEvent.class, 3, new MiningRenderSubscriberRender3DInvoker(var3));
   }
}
