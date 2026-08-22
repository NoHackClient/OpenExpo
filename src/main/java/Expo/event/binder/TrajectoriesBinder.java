package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.Render3DEvent;
import Expo.event.invoker.TrajectoriesRender3DInvoker;
import Expo.module.impl.visual_utility.Trajectories;

public final class TrajectoriesBinder {
   private static final long a = 81604992367734L;

   private TrajectoriesBinder() {
   }

   public static void U( EventBus var1, Trajectories var4) {
      var1.R(var4, Render3DEvent.class, 3, new TrajectoriesRender3DInvoker(var4));
   }
}
