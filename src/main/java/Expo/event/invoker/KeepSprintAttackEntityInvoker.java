package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.AttackEntityEvent;
import Expo.module.impl.combat.KeepSprint;

public final class KeepSprintAttackEntityInvoker implements EventInvoker {
   final KeepSprint d;

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 134462476843811L;
      this.d.onAttackEntity(var4, (AttackEntityEvent)var3);
   }

   public KeepSprintAttackEntityInvoker(KeepSprint var1) {
      this.d = var1;
   }
}
