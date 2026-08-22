package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PreMouseInputEvent;
import Expo.module.impl.combat.AntiFireball;











public final class AntiFireballPreMouseInputInvoker implements EventInvoker {
   final AntiFireball e;

   public AntiFireballPreMouseInputInvoker(AntiFireball var1) {
      this.e = var1;
   }

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 41589248430560L;
      this.e.onPreMouseInput((PreMouseInputEvent)var3, var4);
   }
}
