package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PostTickEvent;
import Expo.module.impl.visual_utility.InventoryHUD;











public final class InventoryHUDPostTickInvoker implements EventInvoker {
   final InventoryHUD f;

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 82860057939980L;
      this.f.onPostTick(var4, (PostTickEvent)var3);
   }

   public InventoryHUDPostTickInvoker(InventoryHUD var1) {
      this.f = var1;
   }
}
