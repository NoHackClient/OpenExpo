package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.Render2DEvent;
import Expo.module.impl.visual.HUD;

public final class HUDRender2DInvoker implements EventInvoker {
   final HUD H;

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 91305122725278L;
      this.H.onRender2D((Render2DEvent)var3, var4);
   }

   public HUDRender2DInvoker(HUD var1) {
      this.H = var1;
   }
}
