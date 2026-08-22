package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.TryHarvestBlockHeadEvent;
import Expo.internal.BrokenBlockTracker;

public final class BrokenBlockTrackerTryHarvestBlockHeadInvoker implements EventInvoker {
   final BrokenBlockTracker u;

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 102490109291600L;
      this.u.onTryHarvestBlockHead((TryHarvestBlockHeadEvent)var3, var4);
   }

   public BrokenBlockTrackerTryHarvestBlockHeadInvoker(BrokenBlockTracker var1) {
      this.u = var1;
   }
}
