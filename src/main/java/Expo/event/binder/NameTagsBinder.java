package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.PostTickEvent;
import Expo.event.events.Render3DEvent;
import Expo.event.invoker.NameTagsPostTickInvoker;
import Expo.event.invoker.NameTagsRender3DInvoker;
import Expo.module.impl.visual_utility.NameTags;

public final class NameTagsBinder {
   private static final long a = 77438592124070L;

   private NameTagsBinder() {
   }

   public static void A(EventBus var0, NameTags var1) {
      var0.R(var1, PostTickEvent.class, 3, new NameTagsPostTickInvoker(var1));
      var0.R(var1, Render3DEvent.class, 3, new NameTagsRender3DInvoker(var1));
   }
}
