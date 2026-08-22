package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.MoveInputEvent;
import Expo.event.events.PreMouseInputEvent;
import Expo.event.events.PreTickEvent;
import Expo.event.events.ReceivePacketEvent;
import Expo.event.events.UpdateWalkingPlayerEvent;
import Expo.event.invoker.RotationManagerMoveInputInvoker;
import Expo.event.invoker.RotationManagerPreMouseInputInvoker;
import Expo.event.invoker.RotationManagerPreTickInvoker;
import Expo.event.invoker.RotationManagerReceivePacketInvoker;
import Expo.event.invoker.RotationManagerUpdateWalkingPlayerInvoker;
import Expo.util.RotationManager;











public final class RotationManagerBinder {
   private static final long a = 89290623735844L;

   private RotationManagerBinder() {
   }

   public static void M(EventBus var0, RotationManager var1) {
      var0.R(var1, PreTickEvent.class, 5, new RotationManagerPreTickInvoker(var1));
      var0.R(var1, UpdateWalkingPlayerEvent.class, 3, new RotationManagerUpdateWalkingPlayerInvoker(var1));
      var0.R(var1, MoveInputEvent.class, 3, new RotationManagerMoveInputInvoker(var1));
      var0.R(var1, PreMouseInputEvent.class, 5, new RotationManagerPreMouseInputInvoker(var1));
      var0.R(var1, ReceivePacketEvent.class, 3, new RotationManagerReceivePacketInvoker(var1));
   }
}
