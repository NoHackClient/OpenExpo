package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.AttackTargetEntityEvent;
import Expo.module.impl.player.Blink;











public final class BlinkAttackTargetEntityInvoker implements EventInvoker {
   final Blink P;

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 71781627748446L;
      this.P.onAttackTargetEntity((AttackTargetEntityEvent)var3, var4);
   }

   public BlinkAttackTargetEntityInvoker(Blink var1) {
      this.P = var1;
   }
}
