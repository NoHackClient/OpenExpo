package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.HeldItemChangeEvent;
import Expo.module.impl.world.Nuker;











public final class NukerHeldItemChangeInvoker implements EventInvoker {
   final Nuker D;

   public NukerHeldItemChangeInvoker(Nuker var1) {
      this.D = var1;
   }

   public void c(long var1, Object var3) {
      int var4 = (int)((var1 ^ 11559511728750L) >>> 32);
      int var5 = (int)((var1 ^ 11559511728750L) << 32 >>> 48);
      int var6 = (int)((var1 ^ 11559511728750L) << 48 >>> 48);
      this.D.onHeldItemChange(var4, var5, (char)var6, (HeldItemChangeEvent)var3);
   }
}
