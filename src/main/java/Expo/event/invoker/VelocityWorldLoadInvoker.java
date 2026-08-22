package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.WorldLoadEvent;
import Expo.module.impl.combat.Velocity;

public final class VelocityWorldLoadInvoker implements EventInvoker {
   final Velocity O;

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 75551643170312L;
      this.O.onWorldLoad((WorldLoadEvent)var3, var4);
   }

   public VelocityWorldLoadInvoker(Velocity var1) {
      this.O = var1;
   }
}
