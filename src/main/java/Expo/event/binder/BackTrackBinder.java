package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.AttackEntityEvent;
import Expo.event.events.PreUpdateEvent;
import Expo.event.events.Render2DEvent;
import Expo.event.invoker.BackTrackAttackEntityInvoker;
import Expo.event.invoker.BackTrackPreUpdateInvoker;
import Expo.event.invoker.BackTrackRender2DInvoker;
import Expo.module.impl.combat.BackTrack;











public final class BackTrackBinder {
   private static final long a = 135812800071493L;

   public static void P( EventBus var2, BackTrack var3) {
      var2.R(var3, PreUpdateEvent.class, 3, new BackTrackPreUpdateInvoker(var3));
      var2.R(var3, AttackEntityEvent.class, 3, new BackTrackAttackEntityInvoker(var3));
      var2.R(var3, Render2DEvent.class, 3, new BackTrackRender2DInvoker(var3));
   }

   private BackTrackBinder() {
   }
}
