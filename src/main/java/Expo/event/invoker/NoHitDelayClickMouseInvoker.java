package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.ClickMouseEvent;
import Expo.module.impl.player.NoHitDelay;











public final class NoHitDelayClickMouseInvoker implements EventInvoker {
   final NoHitDelay l;

   public NoHitDelayClickMouseInvoker(NoHitDelay var1) {
      this.l = var1;
   }

   public void c(long var1, Object var3) {
      this.l.onClickMouse((ClickMouseEvent)var3);
   }
}
