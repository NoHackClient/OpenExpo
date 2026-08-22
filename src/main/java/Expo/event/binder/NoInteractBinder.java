package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.PlayerRightClickEvent;
import Expo.event.invoker.NoInteractPlayerRightClickInvoker;
import Expo.module.impl.player.NoInteract;

public final class NoInteractBinder {
   private static final long a = 30507731447471L;

   private NoInteractBinder() {
   }

   public static void y(EventBus var2, NoInteract var3) {
      var2.R(var3, PlayerRightClickEvent.class, 3, new NoInteractPlayerRightClickInvoker(var3));
   }
}
