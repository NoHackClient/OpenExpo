package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PreMouseInputEvent;
import Expo.module.impl.combat.WTap;











public final class WTapPreMouseInputInvoker implements EventInvoker {
   final WTap S;

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 35781461964755L;
      this.S.onPreMouseInput(var4, (PreMouseInputEvent)var3);
   }

   public WTapPreMouseInputInvoker(WTap var1) {
      this.S = var1;
   }
}
