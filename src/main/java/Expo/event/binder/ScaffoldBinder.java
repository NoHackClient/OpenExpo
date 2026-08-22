package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.HeldItemChangeEvent;
import Expo.event.events.MoveInputEvent;
import Expo.event.events.PreMouseInputEvent;
import Expo.event.events.Render2DEvent;
import Expo.event.events.Render3DEvent;
import Expo.event.invoker.ScaffoldHeldItemChangeInvoker;
import Expo.event.invoker.ScaffoldMoveInputInvoker;
import Expo.event.invoker.ScaffoldPreMouseInputInvoker;
import Expo.event.invoker.ScaffoldRender2DInvoker;
import Expo.event.invoker.ScaffoldRender3DInvoker;
import Expo.module.impl.world.Scaffold;











public final class ScaffoldBinder {
   private static final long a = 62520427670610L;

   public static void h(EventBus var0, byte var1, Scaffold var4) {
      var0.R(var4, HeldItemChangeEvent.class, 3, new ScaffoldHeldItemChangeInvoker(var4));
      var0.R(var4, MoveInputEvent.class, 3, new ScaffoldMoveInputInvoker(var4));
      var0.R(var4, PreMouseInputEvent.class, 3, new ScaffoldPreMouseInputInvoker(var4));
      var0.R(var4, Render2DEvent.class, 3, new ScaffoldRender2DInvoker(var4));
      var0.R(var4, Render3DEvent.class, 3, new ScaffoldRender3DInvoker(var4));
   }

   private ScaffoldBinder() {
   }

}
