package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.WorldLoadEvent;
import Expo.internal.CheaterDetector;











public final class CheaterDetectorWorldLoadInvoker implements EventInvoker {
   final CheaterDetector E;

   public void c(long var1, Object var3) {
      this.E.onWorldLoad((WorldLoadEvent)var3);
   }

   public CheaterDetectorWorldLoadInvoker(CheaterDetector var1) {
      this.E = var1;
   }
}
