package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.AttackEntityEvent;
import Expo.event.events.HeldItemChangeEvent;
import Expo.event.events.PreMouseInputEvent;
import Expo.event.events.SendPacketEvent;
import Expo.event.invoker.AutoDigPlaceAttackEntityInvoker;
import Expo.event.invoker.AutoDigPlaceHeldItemChangeInvoker;
import Expo.event.invoker.AutoDigPlacePreMouseInputInvoker;
import Expo.event.invoker.AutoDigPlaceSendPacketInvoker;
import Expo.module.impl.world.AutoDigPlace;

public final class AutoDigPlaceBinder {
   private static final long a = 91532413199012L;

   public static void n(EventBus var0, AutoDigPlace var2) {
      var0.R(var2, HeldItemChangeEvent.class, 3, new AutoDigPlaceHeldItemChangeInvoker(var2));
      var0.R(var2, AttackEntityEvent.class, 3, new AutoDigPlaceAttackEntityInvoker(var2));
      var0.R(var2, PreMouseInputEvent.class, 3, new AutoDigPlacePreMouseInputInvoker(var2));
      var0.R(var2, SendPacketEvent.class, 3, new AutoDigPlaceSendPacketInvoker(var2));
   }

   private AutoDigPlaceBinder() {
   }
}
