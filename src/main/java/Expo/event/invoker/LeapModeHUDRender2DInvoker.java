package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.Render2DEvent;
import Expo.module.impl.visual_utility.LeapModeHUD;











public final class LeapModeHUDRender2DInvoker implements EventInvoker {
   final LeapModeHUD J;

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 43631300207892L;
      this.J.onRender2D(var4, (Render2DEvent)var3);
   }

   public LeapModeHUDRender2DInvoker(LeapModeHUD var1) {
      this.J = var1;
   }
}
