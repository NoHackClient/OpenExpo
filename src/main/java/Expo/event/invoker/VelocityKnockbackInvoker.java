package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.KnockbackEvent;
import Expo.module.impl.combat.Velocity;











public final class VelocityKnockbackInvoker implements EventInvoker {
   final Velocity J;

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 71078359641696L;
      this.J.onKnockback(var4, (KnockbackEvent)var3);
   }

   public VelocityKnockbackInvoker(Velocity var1) {
      this.J = var1;
   }
}
