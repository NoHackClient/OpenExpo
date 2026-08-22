package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.PostTickEvent;
import Expo.event.events.Render2DEvent;
import Expo.event.invoker.InventoryHUDPostTickInvoker;
import Expo.event.invoker.InventoryHUDRender2DInvoker;
import Expo.module.impl.visual_utility.InventoryHUD;

public final class InventoryHUDBinder {
   private static final long a = 69365049311914L;

   public static void j(EventBus var0, InventoryHUD var1, byte var2, int var3, int var4) {
      var0.R(var1, PostTickEvent.class, 3, new InventoryHUDPostTickInvoker(var1));
      var0.R(var1, Render2DEvent.class, 3, new InventoryHUDRender2DInvoker(var1));
   }

   private InventoryHUDBinder() {
   }
}
