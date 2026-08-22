package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.SendPacketEvent;
import Expo.module.impl.world.BridgeAssist;











public final class BridgeAssistSendPacketInvoker implements EventInvoker {
   final BridgeAssist T;

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 134031344004175L;
      this.T.onSendPacket(var4, (SendPacketEvent)var3);
   }

   public BridgeAssistSendPacketInvoker(BridgeAssist var1) {
      this.T = var1;
   }
}
