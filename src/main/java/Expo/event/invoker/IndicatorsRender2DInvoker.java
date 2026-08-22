package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.Render2DEvent;
import Expo.module.impl.visual_utility.Indicators;

public final class IndicatorsRender2DInvoker implements EventInvoker {
   final Indicators Z;

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 79020789218462L;
      this.Z.onRender2D(var4, (Render2DEvent)var3);
   }

   public IndicatorsRender2DInvoker(Indicators var1) {
      this.Z = var1;
   }
}
