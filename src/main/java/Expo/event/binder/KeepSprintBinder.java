package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.AttackEntityEvent;
import Expo.event.events.PreSuperLivingUpdateEvent;
import Expo.event.events.PreTickEvent;
import Expo.event.events.PreUpdateEvent;
import Expo.event.invoker.KeepSprintAttackEntityInvoker;
import Expo.event.invoker.KeepSprintPreSuperLivingUpdateInvoker;
import Expo.event.invoker.KeepSprintPreTickInvoker;
import Expo.event.invoker.KeepSprintPreUpdateInvoker;
import Expo.module.impl.combat.KeepSprint;

public final class KeepSprintBinder {
   private static final long a = 109516607754392L;

   private KeepSprintBinder() {
   }

   public static void O(EventBus var0, short var1, short var2, KeepSprint var4) {
      var0.R(var4, PreTickEvent.class, 3, new KeepSprintPreTickInvoker(var4));
      var0.R(var4, AttackEntityEvent.class, 5, new KeepSprintAttackEntityInvoker(var4));
      var0.R(var4, PreSuperLivingUpdateEvent.class, 3, new KeepSprintPreSuperLivingUpdateInvoker(var4));
      var0.R(var4, PreUpdateEvent.class, 1, new KeepSprintPreUpdateInvoker(var4));
   }
}
