package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.CloseScreenEvent;
import Expo.event.events.PostTickEvent;
import Expo.event.events.PreUpdateEvent;
import Expo.event.events.SendPacketEvent;
import Expo.event.invoker.InvMoveCloseScreenInvoker;
import Expo.event.invoker.InvMovePostTickInvoker;
import Expo.event.invoker.InvMovePreUpdateInvoker;
import Expo.event.invoker.InvMoveSendPacketInvoker;
import Expo.module.impl.movement.InvMove;

public final class InvMoveBinder {
   private static final long a = 62649266949183L;

   public static void I(EventBus var0, InvMove var3) {
      var0.R(var3, CloseScreenEvent.class, 3, new InvMoveCloseScreenInvoker(var3));
      var0.R(var3, PreUpdateEvent.class, 3, new InvMovePreUpdateInvoker(var3));
      var0.R(var3, SendPacketEvent.class, 3, new InvMoveSendPacketInvoker(var3));
      var0.R(var3, PostTickEvent.class, 3, new InvMovePostTickInvoker(var3));
   }

   private InvMoveBinder() {
   }
}
