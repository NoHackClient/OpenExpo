package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PostTickEvent;
import Expo.util.HypixelGameState;











public final class HypixelGameStatePostTickInvoker implements EventInvoker {
   final HypixelGameState i;

   public HypixelGameStatePostTickInvoker(HypixelGameState var1) {
      this.i = var1;
   }

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 108876111373752L;
      this.i.onPostTick(var4, (PostTickEvent)var3);
   }
}
