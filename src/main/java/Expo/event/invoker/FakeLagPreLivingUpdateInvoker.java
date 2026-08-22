package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PreLivingUpdateEvent;
import Expo.module.impl.combat.FakeLag;











public final class FakeLagPreLivingUpdateInvoker implements EventInvoker {
   final FakeLag j;

   public FakeLagPreLivingUpdateInvoker(FakeLag var1) {
      this.j = var1;
   }

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 38689498083621L;
      this.j.onPreLivingUpdate((PreLivingUpdateEvent)var3, var4);
   }
}
