package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.ReceivePacketEvent;
import Expo.event.events.SendPacketEvent;
import Expo.event.invoker.IncomingPacketHoldReceivePacketInvoker;
import Expo.event.invoker.IncomingPacketHoldSendPacketInvoker;
import Expo.util.packet.IncomingPacketHold;











public final class IncomingPacketHoldBinder {
   private static final long a = 130626195930143L;

   public static void z(EventBus var0, IncomingPacketHold var3) {
      var0.R(var3, ReceivePacketEvent.class, 5, new IncomingPacketHoldReceivePacketInvoker(var3));
      var0.R(var3, SendPacketEvent.class, 3, new IncomingPacketHoldSendPacketInvoker(var3));
   }

   private IncomingPacketHoldBinder() {
   }

}
