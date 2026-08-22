package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PostTickEvent;
import Expo.module.impl.visual_utility.FireBallPredict;











public final class FireBallPredictPostTickInvoker implements EventInvoker {
   final FireBallPredict L;

   public FireBallPredictPostTickInvoker(FireBallPredict var1) {
      this.L = var1;
   }

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 60536227480978L;
      this.L.onPostTick(var4, (PostTickEvent)var3);
   }
}
