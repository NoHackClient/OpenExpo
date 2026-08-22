package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.Render2DEvent;
import Expo.event.invoker.TimerRender2DInvoker;
import Expo.module.impl.misc.Timer;

public final class TimerBinder {
   private static final long a = 34938838245369L;

   private TimerBinder() {
   }

   public static void C(EventBus var2, Timer var3) {
      var2.R(var3, Render2DEvent.class, 3, new TimerRender2DInvoker(var3));
   }
}
