package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.PostTickEvent;
import Expo.event.events.Render2DEvent;
import Expo.event.events.Render3DEvent;
import Expo.event.invoker.TracersPostTickInvoker;
import Expo.event.invoker.TracersRender2DInvoker;
import Expo.event.invoker.TracersRender3DInvoker;
import Expo.module.impl.visual_utility.Tracers;











public final class TracersBinder {
   private static final long a = 110263243401207L;

   private TracersBinder() {
   }

   public static void Z(EventBus var0, Tracers var1) {
      var0.R(var1, PostTickEvent.class, 3, new TracersPostTickInvoker(var1));
      var0.R(var1, Render3DEvent.class, 3, new TracersRender3DInvoker(var1));
      var0.R(var1, Render2DEvent.class, 3, new TracersRender2DInvoker(var1));
   }

}
