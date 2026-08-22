package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.Render2DEvent;
import Expo.module.impl.visual_utility.FKCounter;

public final class FKCounterRender2DInvoker implements EventInvoker {
   final FKCounter B;

   public FKCounterRender2DInvoker(FKCounter var1) {
      this.B = var1;
   }

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 3165742029369L;
      this.B.onRender2D((Render2DEvent)var3, var4);
   }
}
