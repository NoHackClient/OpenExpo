package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.PreUpdateEvent;
import Expo.event.invoker.InvClickerPreUpdateInvoker;
import Expo.module.impl.player.InvClicker;

public final class InvClickerBinder {
   private static final long a = 79536411043381L;

   private InvClickerBinder() {
   }

   public static void T(EventBus var2, InvClicker var3) {
      var2.R(var3, PreUpdateEvent.class, 3, new InvClickerPreUpdateInvoker(var3));
   }
}
