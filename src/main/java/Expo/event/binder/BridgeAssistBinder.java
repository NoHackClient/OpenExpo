package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.MoveInputEvent;
import Expo.event.events.PreMouseInputEvent;
import Expo.event.events.SendPacketEvent;
import Expo.event.invoker.BridgeAssistMoveInputInvoker;
import Expo.event.invoker.BridgeAssistPreMouseInputInvoker;
import Expo.event.invoker.BridgeAssistSendPacketInvoker;
import Expo.module.impl.world.BridgeAssist;











public final class BridgeAssistBinder {
   private static final long a = 132989643439386L;

   public static void v(EventBus var0, BridgeAssist var3) {
      var0.R(var3, MoveInputEvent.class, 3, new BridgeAssistMoveInputInvoker(var3));
      var0.R(var3, SendPacketEvent.class, 3, new BridgeAssistSendPacketInvoker(var3));
      var0.R(var3, PreMouseInputEvent.class, 3, new BridgeAssistPreMouseInputInvoker(var3));
   }

   private BridgeAssistBinder() {
   }
}
