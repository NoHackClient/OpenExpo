package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.AttackEntityEvent;
import Expo.module.impl.world.AutoDigPlace;











public final class AutoDigPlaceAttackEntityInvoker implements EventInvoker {
   final AutoDigPlace V;

   public AutoDigPlaceAttackEntityInvoker(AutoDigPlace var1) {
      this.V = var1;
   }

   public void c(long var1, Object var3) {
      this.V.onAttackEntity((AttackEntityEvent)var3);
   }
}
