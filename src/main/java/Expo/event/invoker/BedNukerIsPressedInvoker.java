package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.IsPressedEvent;
import Expo.module.impl.world.BedNuker;

public final class BedNukerIsPressedInvoker implements EventInvoker {
   final BedNuker N;

   public BedNukerIsPressedInvoker(BedNuker var1) {
      this.N = var1;
   }

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 135820109568688L;
      this.N.onIsPressed(var4, (IsPressedEvent)var3);
   }
}
