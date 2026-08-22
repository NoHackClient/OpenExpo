package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.Render2DEvent;
import Expo.module.impl.visual_utility.InventoryHUD;

public final class InventoryHUDRender2DInvoker implements EventInvoker {
   final InventoryHUD t;

   public void c(long var1, Object var3) {
      int var4 = (int)((var1 ^ 49415326951521L) >>> 48);
      int var5 = (int)((var1 ^ 49415326951521L) << 16 >>> 32);
      int var6 = (int)((var1 ^ 49415326951521L) << 48 >>> 48);
      this.t.onRender2D((Render2DEvent)var3, (char)var4, var5, var6);
   }

   public InventoryHUDRender2DInvoker(InventoryHUD var1) {
      this.t = var1;
   }
}
