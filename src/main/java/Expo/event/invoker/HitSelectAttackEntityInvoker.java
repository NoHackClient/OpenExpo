package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.AttackEntityEvent;
import Expo.module.impl.combat.HitSelect;











public final class HitSelectAttackEntityInvoker implements EventInvoker {
   final HitSelect N;

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 92641127807029L;
      this.N.onAttackEntity(var4, (AttackEntityEvent)var3);
   }

   public HitSelectAttackEntityInvoker(HitSelect var1) {
      this.N = var1;
   }
}
