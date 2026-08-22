package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.WorldLoadEvent;
import Expo.module.impl.combat.AntiFireball;

public final class AntiFireballWorldLoadInvoker implements EventInvoker {
   final AntiFireball w;

   public AntiFireballWorldLoadInvoker(AntiFireball var1) {
      this.w = var1;
   }

   public void c(long var1, Object var3) {
      this.w.onWorldLoad((WorldLoadEvent)var3);
   }
}
