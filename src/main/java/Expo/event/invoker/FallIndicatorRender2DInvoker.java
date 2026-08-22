package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.Render2DEvent;
import Expo.module.impl.visual_utility.FallIndicator;











public final class FallIndicatorRender2DInvoker implements EventInvoker {
   final FallIndicator l;

   public FallIndicatorRender2DInvoker(FallIndicator var1) {
      this.l = var1;
   }

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 41811789852936L;
      this.l.onRender2D((Render2DEvent)var3, var4);
   }
}
