package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.PostTickEvent;
import Expo.event.events.Render3DEvent;
import Expo.event.invoker.FireBallPredictPostTickInvoker;
import Expo.event.invoker.FireBallPredictRender3DInvoker;
import Expo.module.impl.visual_utility.FireBallPredict;

public final class FireBallPredictBinder {
   private static final long a = 119981692591083L;

   private FireBallPredictBinder() {
   }

   public static void P(EventBus var2, FireBallPredict var3) {
      var2.R(var3, PostTickEvent.class, 3, new FireBallPredictPostTickInvoker(var3));
      var2.R(var3, Render3DEvent.class, 3, new FireBallPredictRender3DInvoker(var3));
   }
}
