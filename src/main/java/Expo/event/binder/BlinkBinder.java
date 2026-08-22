package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.AttackTargetEntityEvent;
import Expo.event.events.PostUpdateWalkingPlayerEvent;
import Expo.event.events.Render2DEvent;
import Expo.event.invoker.BlinkAttackTargetEntityInvoker;
import Expo.event.invoker.BlinkPostUpdateWalkingPlayerInvoker;
import Expo.event.invoker.BlinkRender2DInvoker;
import Expo.module.impl.player.Blink;

public final class BlinkBinder {
   private static final long a = 109928004025865L;

   private BlinkBinder() {
   }

   public static void o(short var0, EventBus var2, Blink var4) {
      var2.R(var4, PostUpdateWalkingPlayerEvent.class, 3, new BlinkPostUpdateWalkingPlayerInvoker(var4));
      var2.R(var4, AttackTargetEntityEvent.class, 3, new BlinkAttackTargetEntityInvoker(var4));
      var2.R(var4, Render2DEvent.class, 3, new BlinkRender2DInvoker(var4));
   }
}
