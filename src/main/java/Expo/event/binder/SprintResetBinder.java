package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.AttackEntityEvent;
import Expo.event.events.AttackTargetEntityEvent;
import Expo.event.events.MoveInputEvent;
import Expo.event.events.PostTickEvent;
import Expo.event.events.PreSuperLivingUpdateEvent;
import Expo.event.events.PreUpdateEvent;
import Expo.event.events.ReceivePacketEvent;
import Expo.event.invoker.SprintResetAttackEntityInvoker;
import Expo.event.invoker.SprintResetAttackTargetEntityInvoker;
import Expo.event.invoker.SprintResetMoveInputInvoker;
import Expo.event.invoker.SprintResetPostTickInvoker;
import Expo.event.invoker.SprintResetPreSuperLivingUpdateInvoker;
import Expo.event.invoker.SprintResetPreUpdateInvoker;
import Expo.event.invoker.SprintResetReceivePacketInvoker;
import Expo.module.impl.combat.SprintReset;











public final class SprintResetBinder {
   private static final long a = 34596840566344L;

   private SprintResetBinder() {
   }

   public static void G(EventBus var0, SprintReset var1) {
      var0.R(var1, PreUpdateEvent.class, 3, new SprintResetPreUpdateInvoker(var1));
      var0.R(var1, AttackEntityEvent.class, 3, new SprintResetAttackEntityInvoker(var1));
      var0.R(var1, AttackTargetEntityEvent.class, 3, new SprintResetAttackTargetEntityInvoker(var1));
      var0.R(var1, PreSuperLivingUpdateEvent.class, 3, new SprintResetPreSuperLivingUpdateInvoker(var1));
      var0.R(var1, ReceivePacketEvent.class, 3, new SprintResetReceivePacketInvoker(var1));
      var0.R(var1, MoveInputEvent.class, 3, new SprintResetMoveInputInvoker(var1));
      var0.R(var1, PostTickEvent.class, 3, new SprintResetPostTickInvoker(var1));
   }
}
