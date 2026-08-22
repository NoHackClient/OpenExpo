package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.PostTickEvent;
import Expo.event.events.Render2DEvent;
import Expo.event.invoker.ArrayListPostTickInvoker;
import Expo.event.invoker.ArrayListRender2DInvoker;
import Expo.module.impl.visual.ArrayList;











public final class ArrayListBinder {
   private static final long a = 8442273873410L;

   public static void l(EventBus var2, ArrayList var3) {
      var2.R(var3, PostTickEvent.class, 3, new ArrayListPostTickInvoker(var3));
      var2.R(var3, Render2DEvent.class, 3, new ArrayListRender2DInvoker(var3));
   }

   private ArrayListBinder() {
   }

}
