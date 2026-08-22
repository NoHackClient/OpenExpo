package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.Render2DEvent;
import Expo.module.impl.player.ChestStealer;

public final class ChestStealerRender2DInvoker implements EventInvoker {
   final ChestStealer q;

   public ChestStealerRender2DInvoker(ChestStealer var1) {
      this.q = var1;
   }

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 70498314719327L;
      this.q.onRender2D(var4, (Render2DEvent)var3);
   }
}
