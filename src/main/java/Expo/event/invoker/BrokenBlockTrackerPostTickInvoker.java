package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PostTickEvent;
import Expo.internal.BrokenBlockTracker;











public final class BrokenBlockTrackerPostTickInvoker implements EventInvoker {
   final BrokenBlockTracker i;

   public BrokenBlockTrackerPostTickInvoker(BrokenBlockTracker var1) {
      this.i = var1;
   }

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 63513228372228L;
      this.i.onPostTick(var4, (PostTickEvent)var3);
   }
}
