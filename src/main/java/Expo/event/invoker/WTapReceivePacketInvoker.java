package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.ReceivePacketEvent;
import Expo.module.impl.combat.WTap;











public final class WTapReceivePacketInvoker implements EventInvoker {
   final WTap H;

   public WTapReceivePacketInvoker(WTap var1) {
      this.H = var1;
   }

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 40983612860846L;
      this.H.onReceivePacket((ReceivePacketEvent)var3, var4);
   }
}
