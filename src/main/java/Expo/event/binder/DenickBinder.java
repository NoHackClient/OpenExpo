package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.PlayerGetNameEvent;
import Expo.event.events.PostTickEvent;
import Expo.event.invoker.DenickPlayerGetNameInvoker;
import Expo.event.invoker.DenickPostTickInvoker;
import Expo.module.impl.misc.Denick;











public final class DenickBinder {
   private static final long a = 29576312540654L;

   private DenickBinder() {
   }

   public static void Z(char var0, EventBus var3, Denick var4) {
      var3.R(var4, PostTickEvent.class, 3, new DenickPostTickInvoker(var4));
      var3.R(var4, PlayerGetNameEvent.class, 3, new DenickPlayerGetNameInvoker(var4));
   }
}
