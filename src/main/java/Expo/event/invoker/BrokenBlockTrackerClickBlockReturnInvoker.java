package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.ClickBlockReturnEvent;
import Expo.internal.BrokenBlockTracker;

public final class BrokenBlockTrackerClickBlockReturnInvoker implements EventInvoker {
   final BrokenBlockTracker Z;

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 114373254784550L;
      this.Z.onClickBlockReturn((ClickBlockReturnEvent)var3, var4);
   }

   public BrokenBlockTrackerClickBlockReturnInvoker(BrokenBlockTracker var1) {
      this.Z = var1;
   }
}
