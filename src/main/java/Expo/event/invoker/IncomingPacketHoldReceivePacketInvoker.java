package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.ReceivePacketEvent;
import Expo.util.packet.IncomingPacketHold;

public final class IncomingPacketHoldReceivePacketInvoker implements EventInvoker {
   final IncomingPacketHold J;

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 84791699403143L;
      this.J.onReceivePacket((ReceivePacketEvent)var3, var4);
   }

   public IncomingPacketHoldReceivePacketInvoker(IncomingPacketHold var1) {
      this.J = var1;
   }
}
