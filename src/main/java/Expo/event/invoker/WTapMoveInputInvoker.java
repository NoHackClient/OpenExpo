package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.MoveInputEvent;
import Expo.module.impl.combat.WTap;

public final class WTapMoveInputInvoker implements EventInvoker {
   final WTap M;

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 70978358598092L;
      this.M.onMoveInput((MoveInputEvent)var3, var4);
   }

   public WTapMoveInputInvoker(WTap var1) {
      this.M = var1;
   }
}
