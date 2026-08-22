package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.SetKeyBindStateEvent;
import Expo.event.events.UpdateWalkingPlayerEvent;
import Expo.event.invoker.AntiVoidSetKeyBindStateInvoker;
import Expo.event.invoker.AntiVoidUpdateWalkingPlayerInvoker;
import Expo.module.impl.world.AntiVoid;











public final class AntiVoidBinder {
   private static final long a = 29935093634162L;

   private AntiVoidBinder() {
   }

   public static void Q(EventBus var0, AntiVoid var3) {
      var0.R(var3, UpdateWalkingPlayerEvent.class, 3, new AntiVoidUpdateWalkingPlayerInvoker(var3));
      var0.R(var3, SetKeyBindStateEvent.class, 3, new AntiVoidSetKeyBindStateInvoker(var3));
   }
}
