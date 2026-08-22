package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.WorldLoadEvent;
import Expo.util.HypixelGameState;

public final class HypixelGameStateWorldLoadInvoker implements EventInvoker {
   final HypixelGameState c;

   public HypixelGameStateWorldLoadInvoker(HypixelGameState var1) {
      this.c = var1;
   }

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 64151450934732L;
      this.c.onWorldLoad((WorldLoadEvent)var3, var4);
   }
}
