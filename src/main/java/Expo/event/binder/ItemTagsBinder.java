package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.PostTickEvent;
import Expo.event.events.Render3DEvent;
import Expo.event.invoker.ItemTagsPostTickInvoker;
import Expo.event.invoker.ItemTagsRender3DInvoker;
import Expo.module.impl.visual_utility.ItemTags;











public final class ItemTagsBinder {
   private static final long a = 12501548942805L;

   public static void b(EventBus var0, char var1, short var2, ItemTags var3) {
      var0.R(var3, PostTickEvent.class, 3, new ItemTagsPostTickInvoker(var3));
      var0.R(var3, Render3DEvent.class, 3, new ItemTagsRender3DInvoker(var3));
   }


   private ItemTagsBinder() {
   }
}
