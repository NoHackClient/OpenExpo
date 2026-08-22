package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.ClickMouseEvent;
import Expo.module.impl.world.BedNuker;

public final class BedNukerClickMouseInvoker implements EventInvoker {
   final BedNuker t;

   public BedNukerClickMouseInvoker(BedNuker var1) {
      this.t = var1;
   }

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 43266484157431L;
      this.t.onClickMouse(var4, (ClickMouseEvent)var3);
   }
}
