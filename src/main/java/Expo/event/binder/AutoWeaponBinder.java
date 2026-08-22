package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.PreUpdateEvent;
import Expo.event.invoker.AutoWeaponPreUpdateInvoker;
import Expo.module.impl.player.AutoWeapon;

public final class AutoWeaponBinder {
   private static final long a = 90205362079952L;

   private AutoWeaponBinder() {
   }

   public static void N(EventBus var0, AutoWeapon var1) {
      var0.R(var1, PreUpdateEvent.class, 3, new AutoWeaponPreUpdateInvoker(var1));
   }
}
