package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.AttackEntityEvent;
import Expo.module.impl.combat.BackTrack;











public final class BackTrackAttackEntityInvoker implements EventInvoker {
   final BackTrack O;

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 99144821882616L;
      this.O.onAttackEntity((AttackEntityEvent)var3, var4);
   }

   public BackTrackAttackEntityInvoker(BackTrack var1) {
      this.O = var1;
   }
}
