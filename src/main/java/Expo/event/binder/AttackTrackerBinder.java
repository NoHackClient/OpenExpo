package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.AttackEntityEvent;
import Expo.event.invoker.AttackTrackerAttackEntityInvoker;
import Expo.util.AttackTracker;











public final class AttackTrackerBinder {
   private static final long a = 117798708674610L;

   private AttackTrackerBinder() {
   }

   public static void D(EventBus var0, AttackTracker var1) {
      var0.R(var1, AttackEntityEvent.class, 1, new AttackTrackerAttackEntityInvoker(var1));
   }
}
