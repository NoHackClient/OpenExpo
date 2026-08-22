package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PreUpdateEvent;
import Expo.module.impl.combat.SprintReset;

public final class SprintResetPreUpdateInvoker implements EventInvoker {
   final SprintReset D;

   public SprintResetPreUpdateInvoker(SprintReset var1) {
      this.D = var1;
   }

   public void c(long var1, Object var3) {
      int var4 = (int)((var1 ^ 3989243895929L) >>> 32);
      int var5 = (int)((var1 ^ 3989243895929L) << 32 >>> 48);
      int var6 = (int)((var1 ^ 3989243895929L) << 48 >>> 48);
      this.D.onPreUpdate(var4, (PreUpdateEvent)var3, var5, var6);
   }
}
