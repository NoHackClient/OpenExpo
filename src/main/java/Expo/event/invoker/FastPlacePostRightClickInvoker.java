package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PostRightClickEvent;
import Expo.module.impl.world.FastPlace;

public final class FastPlacePostRightClickInvoker implements EventInvoker {
   final FastPlace J;

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 78464421740926L;
      this.J.onPostRightClick((PostRightClickEvent)var3, var4);
   }

   public FastPlacePostRightClickInvoker(FastPlace var1) {
      this.J = var1;
   }
}
