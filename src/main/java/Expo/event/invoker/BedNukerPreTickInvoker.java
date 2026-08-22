package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PreTickEvent;
import Expo.module.impl.world.BedNuker;

public final class BedNukerPreTickInvoker implements EventInvoker {
   final BedNuker e;

   public BedNukerPreTickInvoker(BedNuker var1) {
      this.e = var1;
   }

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 17373848150309L;
      this.e.onPreTick(var4, (PreTickEvent)var3);
   }
}
