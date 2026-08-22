package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.PreTickEvent;
import Expo.event.invoker.Macro1PreTickInvoker;
import Expo.module.impl.macro.Macro1;

public final class Macro1Binder {
   private static final long a = 13348691935616L;

   public static void g(EventBus var0, short var1, char var2, int var3, Macro1 var4) {
      var0.R(var4, PreTickEvent.class, 3, new Macro1PreTickInvoker(var4));
   }

   private Macro1Binder() {
   }
}
