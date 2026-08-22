package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.ClickBlockReturnEvent;
import Expo.event.events.PostTickEvent;
import Expo.event.events.TryHarvestBlockHeadEvent;
import Expo.event.invoker.BrokenBlockTrackerClickBlockReturnInvoker;
import Expo.event.invoker.BrokenBlockTrackerPostTickInvoker;
import Expo.event.invoker.BrokenBlockTrackerTryHarvestBlockHeadInvoker;
import Expo.internal.BrokenBlockTracker;

public final class BrokenBlockTrackerBinder {
   private static final long a = 43283131921251L;

   private BrokenBlockTrackerBinder() {
   }

   public static void z(EventBus var0, BrokenBlockTracker var1) {
      var0.R(var1, ClickBlockReturnEvent.class, 3, new BrokenBlockTrackerClickBlockReturnInvoker(var1));
      var0.R(var1, TryHarvestBlockHeadEvent.class, 3, new BrokenBlockTrackerTryHarvestBlockHeadInvoker(var1));
      var0.R(var1, PostTickEvent.class, 3, new BrokenBlockTrackerPostTickInvoker(var1));
   }
}
