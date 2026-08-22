package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PostTickEvent;
import Expo.module.impl.visual_utility.FallIndicator;

public final class FallIndicatorPostTickInvoker implements EventInvoker {
   final FallIndicator U;

   public FallIndicatorPostTickInvoker(FallIndicator var1) {
      this.U = var1;
   }

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 111225627602213L;
      this.U.onPostTick((PostTickEvent)var3, var4);
   }
}
