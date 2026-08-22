package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PreLivingUpdateEvent;
import Expo.module.impl.combat.Velocity;











public final class VelocityPreLivingUpdateInvoker implements EventInvoker {
   final Velocity G;

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 26654376158874L;
      this.G.onPreLivingUpdate(var4, (PreLivingUpdateEvent)var3);
   }

   public VelocityPreLivingUpdateInvoker(Velocity var1) {
      this.G = var1;
   }
}
