package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.PostTickEvent;
import Expo.event.events.Render3DEvent;
import Expo.event.invoker.BreakProgressPostTickInvoker;
import Expo.event.invoker.BreakProgressRender3DInvoker;
import Expo.module.impl.visual.BreakProgress;











public final class BreakProgressBinder {
   private static final long a = 40957638788398L;

   public static void Z(EventBus var0, BreakProgress var3) {
      var0.R(var3, Render3DEvent.class, 3, new BreakProgressRender3DInvoker(var3));
      var0.R(var3, PostTickEvent.class, 3, new BreakProgressPostTickInvoker(var3));
   }

   private BreakProgressBinder() {
   }
}
