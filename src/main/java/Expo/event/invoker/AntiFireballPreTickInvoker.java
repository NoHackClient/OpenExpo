package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PreTickEvent;
import Expo.module.impl.combat.AntiFireball;

public final class AntiFireballPreTickInvoker implements EventInvoker {
   final AntiFireball e;

   public AntiFireballPreTickInvoker(AntiFireball var1) {
      this.e = var1;
   }

   public void c(long var1, Object var3) {
      int var4 = (int)((var1 ^ 24082773930020L) >>> 48);
      int var5 = (int)((var1 ^ 24082773930020L) << 16 >>> 32);
      int var6 = (int)((var1 ^ 24082773930020L) << 48 >>> 48);
      this.e.onPreTick((char)var4, var5, (short)var6, (PreTickEvent)var3);
   }
}
