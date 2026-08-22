package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PreLivingUpdateEvent;
import Expo.module.impl.movement.Stuck;











public final class StuckPreLivingUpdateInvoker implements EventInvoker {
   final Stuck P;

   public StuckPreLivingUpdateInvoker(Stuck var1) {
      this.P = var1;
   }

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 119841750278170L;
      this.P.onPreLivingUpdate((PreLivingUpdateEvent)var3, var4);
   }
}
