package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.EntityJoinWorldEvent;
import Expo.internal.CheaterDetector;











public final class CheaterDetectorEntityJoinWorldInvoker implements EventInvoker {
   final CheaterDetector W;

   public CheaterDetectorEntityJoinWorldInvoker(CheaterDetector var1) {
      this.W = var1;
   }

   public void c(long var1, Object var3) {
      this.W.onEntityJoinWorld((EntityJoinWorldEvent)var3);
   }
}
