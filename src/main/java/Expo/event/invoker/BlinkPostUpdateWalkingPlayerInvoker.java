package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PostUpdateWalkingPlayerEvent;
import Expo.module.impl.player.Blink;











public final class BlinkPostUpdateWalkingPlayerInvoker implements EventInvoker {
   final Blink F;

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 8237694399884L;
      this.F.onPostUpdateWalkingPlayer((PostUpdateWalkingPlayerEvent)var3, var4);
   }

   public BlinkPostUpdateWalkingPlayerInvoker(Blink var1) {
      this.F = var1;
   }
}
