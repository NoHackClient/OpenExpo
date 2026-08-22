package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.Render2DEvent;
import Expo.event.invoker.ModuleTagRendererRender2DInvoker;
import Expo.ui.ModuleTagRenderer;

public final class ModuleTagRendererBinder {
   private static final long a = 47730061521115L;

   private ModuleTagRendererBinder() {
   }

   public static void Q(int var0, EventBus var1, ModuleTagRenderer var2) {
      var1.R(var2, Render2DEvent.class, 3, new ModuleTagRendererRender2DInvoker(var2));
   }
}
