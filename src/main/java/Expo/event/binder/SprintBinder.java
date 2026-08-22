package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.PreUpdateEvent;
import Expo.event.invoker.SprintPreUpdateInvoker;
import Expo.module.impl.movement.Sprint;











public final class SprintBinder {
   private static final long a = 96578155195197L;

   private SprintBinder() {
   }

   public static void J(EventBus var2, Sprint var3) {
      var2.R(var3, PreUpdateEvent.class, 3, new SprintPreUpdateInvoker(var3));
   }
}
