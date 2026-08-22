package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PostClickMouseEvent;
import Expo.module.impl.player.NoHitDelay;











public final class NoHitDelayPostClickMouseInvoker implements EventInvoker {
   final NoHitDelay p;

   public NoHitDelayPostClickMouseInvoker(NoHitDelay var1) {
      this.p = var1;
   }

   public void c(long var1, Object var3) {
      this.p.onPostClickMouse((PostClickMouseEvent)var3);
   }
}
