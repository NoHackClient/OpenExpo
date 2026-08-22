package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PreMouseInputEvent;
import Expo.module.impl.world.AutoDigPlace;

public final class AutoDigPlacePreMouseInputInvoker implements EventInvoker {
   final AutoDigPlace v;

   public AutoDigPlacePreMouseInputInvoker(AutoDigPlace var1) {
      this.v = var1;
   }

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 44553041640490L;
      this.v.onPreMouseInput((PreMouseInputEvent)var3, var4);
   }
}
