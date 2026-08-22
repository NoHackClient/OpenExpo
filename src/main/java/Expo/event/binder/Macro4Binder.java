package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.PreTickEvent;
import Expo.event.invoker.Macro4PreTickInvoker;
import Expo.module.impl.macro.Macro4;











public final class Macro4Binder {
   private static final long a = 99838166720388L;

   private Macro4Binder() {
   }

   public static void o( EventBus var1, Macro4 var4) {
      var1.R(var4, PreTickEvent.class, 3, new Macro4PreTickInvoker(var4));
   }
}
