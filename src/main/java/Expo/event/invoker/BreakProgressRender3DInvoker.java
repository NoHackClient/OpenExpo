package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.Render3DEvent;
import Expo.module.impl.visual.BreakProgress;











public final class BreakProgressRender3DInvoker implements EventInvoker {
   final BreakProgress T;

   public BreakProgressRender3DInvoker(BreakProgress var1) {
      this.T = var1;
   }

   public void c(long var1, Object var3) {
      this.T.onRender3D((Render3DEvent)var3);
   }
}
