package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PreLivingUpdateEvent;
import Expo.module.impl.combat.LagRange;

public final class LagRangePreLivingUpdateInvoker implements EventInvoker {
   final LagRange p;

   public LagRangePreLivingUpdateInvoker(LagRange var1) {
      this.p = var1;
   }

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 11317907799337L;
      this.p.onPreLivingUpdate((PreLivingUpdateEvent)var3, var4);
   }
}
