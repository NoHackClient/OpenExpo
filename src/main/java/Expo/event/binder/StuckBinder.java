package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.MoveEntityEvent;
import Expo.event.events.MoveEntityWithHeadingEvent;
import Expo.event.events.MoveInputEvent;
import Expo.event.events.PreLivingUpdateEvent;
import Expo.event.invoker.StuckMoveEntityInvoker;
import Expo.event.invoker.StuckMoveEntityWithHeadingInvoker;
import Expo.event.invoker.StuckMoveInputInvoker;
import Expo.event.invoker.StuckPreLivingUpdateInvoker;
import Expo.module.impl.movement.Stuck;











public final class StuckBinder {
   private static final long a = 31411806791669L;


   public static void c(EventBus var0, Stuck var1) {
      var0.R(var1, MoveInputEvent.class, 3, new StuckMoveInputInvoker(var1));
      var0.R(var1, PreLivingUpdateEvent.class, 3, new StuckPreLivingUpdateInvoker(var1));
      var0.R(var1, MoveEntityWithHeadingEvent.class, 3, new StuckMoveEntityWithHeadingInvoker(var1));
      var0.R(var1, MoveEntityEvent.class, 3, new StuckMoveEntityInvoker(var1));
   }

   private StuckBinder() {
   }
}
