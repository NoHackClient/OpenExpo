package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PostTickEvent;
import Expo.module.impl.combat.LagRange;











public final class LagRangePostTickInvoker implements EventInvoker {
   final LagRange n;

   public void c(long var1, Object var3) {
      int var4 = (int)((var1 ^ 45450357766804L) >>> 32);
      int var5 = (int)((var1 ^ 45450357766804L) << 32 >>> 48);
      int var6 = (int)((var1 ^ 45450357766804L) << 48 >>> 48);
      this.n.onPostTick(var4, var5, (char)var6, (PostTickEvent)var3);
   }

   public LagRangePostTickInvoker(LagRange var1) {
      this.n = var1;
   }
}
