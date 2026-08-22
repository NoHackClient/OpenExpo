package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.AttackEntityEvent;
import Expo.module.impl.combat.WTap;

public final class WTapAttackEntityInvoker implements EventInvoker {
   final WTap b;

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 798347915983L;
      this.b.onAttackEntity(var4, (AttackEntityEvent)var3);
   }

   public WTapAttackEntityInvoker(WTap var1) {
      this.b = var1;
   }
}
