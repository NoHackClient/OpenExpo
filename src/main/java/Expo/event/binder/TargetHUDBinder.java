package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.AttackTargetEntityEvent;
import Expo.event.events.PreUpdateEvent;
import Expo.event.events.Render2DEvent;
import Expo.event.invoker.TargetHUDAttackTargetEntityInvoker;
import Expo.event.invoker.TargetHUDPreUpdateInvoker;
import Expo.event.invoker.TargetHUDRender2DInvoker;
import Expo.module.impl.visual_utility.TargetHUD;











public final class TargetHUDBinder {
   private static final long a = 138990443720805L;

   private TargetHUDBinder() {
   }

   public static void Y(EventBus var0, TargetHUD var1) {
      var0.R(var1, Render2DEvent.class, 3, new TargetHUDRender2DInvoker(var1));
      var0.R(var1, PreUpdateEvent.class, 3, new TargetHUDPreUpdateInvoker(var1));
      var0.R(var1, AttackTargetEntityEvent.class, 3, new TargetHUDAttackTargetEntityInvoker(var1));
   }
}
