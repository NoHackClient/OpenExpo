package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PreTickEvent;
import Expo.module.impl.combat.Velocity;











public final class VelocityPreTickInvoker implements EventInvoker {
   final Velocity J;

   public VelocityPreTickInvoker(Velocity var1) {
      this.J = var1;
   }

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 50344044789915L;
      this.J.onPreTick((PreTickEvent)var3, var4);
   }
}
