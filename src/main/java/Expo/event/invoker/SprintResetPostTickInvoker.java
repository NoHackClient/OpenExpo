package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PostTickEvent;
import Expo.module.impl.combat.SprintReset;











public final class SprintResetPostTickInvoker implements EventInvoker {
   final SprintReset x;

   public SprintResetPostTickInvoker(SprintReset var1) {
      this.x = var1;
   }

   public void c(long var1, Object var3) {
      this.x.onPostTick((PostTickEvent)var3);
   }
}
