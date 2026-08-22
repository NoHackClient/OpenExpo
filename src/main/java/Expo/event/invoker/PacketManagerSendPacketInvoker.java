package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.SendPacketEvent;
import Expo.util.packet.PacketManager;











public final class PacketManagerSendPacketInvoker implements EventInvoker {
   final PacketManager U;

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 127596269980740L;
      this.U.onSendPacket(var4, (SendPacketEvent)var3);
   }

   public PacketManagerSendPacketInvoker(PacketManager var1) {
      this.U = var1;
   }
}
