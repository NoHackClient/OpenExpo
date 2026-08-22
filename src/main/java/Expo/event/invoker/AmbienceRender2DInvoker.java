package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.Render2DEvent;
import Expo.module.impl.visual.Ambience;

public final class AmbienceRender2DInvoker implements EventInvoker {
   final Ambience w;

   public void c(long var1, Object var3) {
      this.w.onRender2D((Render2DEvent)var3);
   }

   public AmbienceRender2DInvoker(Ambience var1) {
      this.w = var1;
   }
}
