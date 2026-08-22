package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.SendPacketEvent;
import Expo.module.impl.combat.LagRange;











public final class LagRangeSendPacketInvoker implements EventInvoker {
   final LagRange y;

   public LagRangeSendPacketInvoker(LagRange var1) {
      this.y = var1;
   }

   public void c(long var1, Object var3) {
      long var4 = (var1 ^ 60067389269276L) >>> 32;
      int var6 = (int)((var1 ^ 60067389269276L) << 32 >>> 32);
      this.y.onSendPacket(var4, var6, (SendPacketEvent)var3);
   }
}
