package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.AttackEntityEvent;
import Expo.event.events.MoveInputEvent;
import Expo.event.events.PreMouseInputEvent;
import Expo.event.events.ReceivePacketEvent;
import Expo.event.invoker.WTapAttackEntityInvoker;
import Expo.event.invoker.WTapMoveInputInvoker;
import Expo.event.invoker.WTapPreMouseInputInvoker;
import Expo.event.invoker.WTapReceivePacketInvoker;
import Expo.module.impl.combat.WTap;

public final class WTapBinder {
   private static final long a = 425175557905L;

   public static void U( EventBus var1, WTap var3) {
      var1.R(var3, AttackEntityEvent.class, 3, new WTapAttackEntityInvoker(var3));
      var1.R(var3, PreMouseInputEvent.class, 3, new WTapPreMouseInputInvoker(var3));
      var1.R(var3, MoveInputEvent.class, 3, new WTapMoveInputInvoker(var3));
      var1.R(var3, ReceivePacketEvent.class, 3, new WTapReceivePacketInvoker(var3));
   }

   private WTapBinder() {
   }
}
