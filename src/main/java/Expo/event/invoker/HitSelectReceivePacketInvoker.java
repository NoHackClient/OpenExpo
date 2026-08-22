package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.ReceivePacketEvent;
import Expo.module.impl.combat.HitSelect;











public final class HitSelectReceivePacketInvoker implements EventInvoker {
   final HitSelect t;

   public void c(long var1, Object var3) {
      int var4 = (int)((var1 ^ 46739103357854L) >>> 48);
      int var5 = (int)((var1 ^ 46739103357854L) << 16 >>> 32);
      int var6 = (int)((var1 ^ 46739103357854L) << 48 >>> 48);
      this.t.onReceivePacket((char)var4, (ReceivePacketEvent)var3, var5, (short)var6);
   }

   public HitSelectReceivePacketInvoker(HitSelect var1) {
      this.t = var1;
   }
}
