package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.PreMouseInputEvent;
import Expo.event.invoker.AutoToolPreMouseInputInvoker;
import Expo.module.impl.world.AutoTool;











public final class AutoToolBinder {
   private static final long a = 8124821190601L;

   private AutoToolBinder() {
   }

   public static void v(EventBus var0, AutoTool var3) {
      var0.R(var3, PreMouseInputEvent.class, 3, new AutoToolPreMouseInputInvoker(var3));
   }
}
