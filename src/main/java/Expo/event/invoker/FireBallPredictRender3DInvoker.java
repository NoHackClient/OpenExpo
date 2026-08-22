package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.Render3DEvent;
import Expo.module.impl.visual_utility.FireBallPredict;











public final class FireBallPredictRender3DInvoker implements EventInvoker {
   final FireBallPredict r;

   public FireBallPredictRender3DInvoker(FireBallPredict var1) {
      this.r = var1;
   }

   public void c(long var1, Object var3) throws Throwable {
      long var4 = var1 ^ 107646597613757L;
      this.r.onRender3D(var4, (Render3DEvent)var3);
   }
}
