package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.PreLivingUpdateEvent;
import Expo.event.invoker.AntiBotPreLivingUpdateInvoker;
import Expo.module.impl.misc.AntiBot;

public final class AntiBotBinder {
   private static final long a = 112795195412735L;

   private AntiBotBinder() {
   }

   public static void t(EventBus var0, AntiBot var1) {
      var0.R(var1, PreLivingUpdateEvent.class, 3, new AntiBotPreLivingUpdateInvoker(var1));
   }
}
