package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.PostTickEvent;
import Expo.event.events.Render3DEvent;
import Expo.event.invoker.ItemESPPostTickInvoker;
import Expo.event.invoker.ItemESPRender3DInvoker;
import Expo.module.impl.visual_utility.ItemESP;

public final class ItemESPBinder {
   private static final long a = 6182073483998L;

   public static void v(EventBus var2, ItemESP var3) {
      var2.R(var3, PostTickEvent.class, 3, new ItemESPPostTickInvoker(var3));
      var2.R(var3, Render3DEvent.class, 3, new ItemESPRender3DInvoker(var3));
   }

   private ItemESPBinder() {
   }
}
