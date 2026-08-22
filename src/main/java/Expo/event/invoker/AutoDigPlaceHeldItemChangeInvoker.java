package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.HeldItemChangeEvent;
import Expo.module.impl.world.AutoDigPlace;

public final class AutoDigPlaceHeldItemChangeInvoker implements EventInvoker {
   final AutoDigPlace G;

   public void c(long var1, Object var3) {
      int var4 = (int)((var1 ^ 23601104127148L) >>> 32);
      int var5 = (int)((var1 ^ 23601104127148L) << 32 >>> 48);
      int var6 = (int)((var1 ^ 23601104127148L) << 48 >>> 48);
      this.G.onHeldItemChange((HeldItemChangeEvent)var3, var4, var5, var6);
   }

   public AutoDigPlaceHeldItemChangeInvoker(AutoDigPlace var1) {
      this.G = var1;
   }
}
