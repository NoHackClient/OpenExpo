package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.AttackEntityEvent;
import Expo.util.AttackTracker;











public final class AttackTrackerAttackEntityInvoker implements EventInvoker {
   final AttackTracker v;

   public AttackTrackerAttackEntityInvoker(AttackTracker var1) {
      this.v = var1;
   }

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 31072897928001L;
      this.v.onAttackEntity((AttackEntityEvent)var3, var4);
   }
}
