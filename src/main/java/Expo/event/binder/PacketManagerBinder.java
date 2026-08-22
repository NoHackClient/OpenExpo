package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.SendPacketEvent;
import Expo.event.invoker.PacketManagerSendPacketInvoker;
import Expo.util.packet.PacketManager;

public final class PacketManagerBinder {
   private static final long a = 118831071249546L;

   public static void N(EventBus var0, PacketManager var3) {
      var0.R(var3, SendPacketEvent.class, 3, new PacketManagerSendPacketInvoker(var3));
   }

   private PacketManagerBinder() {
   }
}
