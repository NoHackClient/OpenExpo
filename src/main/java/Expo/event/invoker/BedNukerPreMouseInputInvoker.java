package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PreMouseInputEvent;
import Expo.module.impl.world.BedNuker;

public final class BedNukerPreMouseInputInvoker implements EventInvoker {
   final BedNuker i;

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 10667451756670L;
      this.i.onPreMouseInput((PreMouseInputEvent)var3, var4);
   }

   public BedNukerPreMouseInputInvoker(BedNuker var1) {
      this.i = var1;
   }
}
