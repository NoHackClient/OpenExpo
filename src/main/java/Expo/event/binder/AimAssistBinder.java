package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.PostTickEvent;
import Expo.event.events.SetAnglesEvent;
import Expo.event.invoker.AimAssistPostTickInvoker;
import Expo.event.invoker.AimAssistSetAnglesInvoker;
import Expo.module.impl.combat.AimAssist;











public final class AimAssistBinder {
   private static final long a = 58905755702421L;

   private AimAssistBinder() {
   }

   public static void u(EventBus var0, AimAssist var3) {
      var0.R(var3, PostTickEvent.class, 3, new AimAssistPostTickInvoker(var3));
      var0.R(var3, SetAnglesEvent.class, 3, new AimAssistSetAnglesInvoker(var3));
   }
}
