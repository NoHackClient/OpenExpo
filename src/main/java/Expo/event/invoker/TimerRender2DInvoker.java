package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.Render2DEvent;
import Expo.module.impl.misc.Timer;

public final class TimerRender2DInvoker implements EventInvoker {
   final Timer b;

   public TimerRender2DInvoker(Timer var1) {
      this.b = var1;
   }

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 115710268873695L;
      this.b.onRender2D(var4, (Render2DEvent)var3);
   }
}
