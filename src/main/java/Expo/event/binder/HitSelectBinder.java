package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.AttackEntityEvent;
import Expo.event.events.PreMouseInputEvent;
import Expo.event.events.ReceivePacketEvent;
import Expo.event.invoker.HitSelectAttackEntityInvoker;
import Expo.event.invoker.HitSelectPreMouseInputInvoker;
import Expo.event.invoker.HitSelectReceivePacketInvoker;
import Expo.module.impl.combat.HitSelect;











public final class HitSelectBinder {
   private static final long a = 134656957190345L;

   public static void J(EventBus var0, HitSelect var3) {
      var0.R(var3, AttackEntityEvent.class, 3, new HitSelectAttackEntityInvoker(var3));
      var0.R(var3, PreMouseInputEvent.class, 3, new HitSelectPreMouseInputInvoker(var3));
      var0.R(var3, ReceivePacketEvent.class, 3, new HitSelectReceivePacketInvoker(var3));
   }

   private HitSelectBinder() {
   }

}
