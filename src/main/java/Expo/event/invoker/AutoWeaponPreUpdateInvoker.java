package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PreUpdateEvent;
import Expo.module.impl.player.AutoWeapon;











public final class AutoWeaponPreUpdateInvoker implements EventInvoker {
   final AutoWeapon y;

   public AutoWeaponPreUpdateInvoker(AutoWeapon var1) {
      this.y = var1;
   }

   public void c(long var1, Object var3) {
      this.y.onPreUpdate((PreUpdateEvent)var3);
   }
}
