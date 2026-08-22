package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PreSuperLivingUpdateEvent;
import Expo.module.impl.combat.SprintReset;

public final class SprintResetPreSuperLivingUpdateInvoker implements EventInvoker {
   final SprintReset K;

   public void c(long var1, Object var3) {
      int var4 = (int)((var1 ^ 29968690985797L) >>> 48);
      int var5 = (int)((var1 ^ 29968690985797L) << 16 >>> 32);
      int var6 = (int)((var1 ^ 29968690985797L) << 48 >>> 48);
      this.K.onPreSuperLivingUpdate((short)var4, (PreSuperLivingUpdateEvent)var3, var5, (short)var6);
   }

   public SprintResetPreSuperLivingUpdateInvoker(SprintReset var1) {
      this.K = var1;
   }
}
