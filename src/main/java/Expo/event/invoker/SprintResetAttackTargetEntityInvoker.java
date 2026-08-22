package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.AttackTargetEntityEvent;
import Expo.module.impl.combat.SprintReset;











public final class SprintResetAttackTargetEntityInvoker implements EventInvoker {
   final SprintReset H;

   public SprintResetAttackTargetEntityInvoker(SprintReset var1) {
      this.H = var1;
   }

   public void c(long var1, Object var3) {
      this.H.onAttackTargetEntity((AttackTargetEntityEvent)var3);
   }
}
