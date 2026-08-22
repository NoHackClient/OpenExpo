package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.SendPacketEvent;
import Expo.util.packet.IncomingPacketHold;











public final class IncomingPacketHoldSendPacketInvoker implements EventInvoker {
   final IncomingPacketHold p;

   public IncomingPacketHoldSendPacketInvoker(IncomingPacketHold var1) {
      this.p = var1;
   }

   public void c(long var1, Object var3) {
      this.p.onSendPacket((SendPacketEvent)var3);
   }
}
