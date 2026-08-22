package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.PlayerGetNameEvent;
import Expo.event.invoker.AntiNickPlayerGetNameInvoker;
import Expo.module.impl.misc.AntiNick;

public final class AntiNickBinder {
   private static final long a = 71364328745957L;

   private AntiNickBinder() {
   }

   public static void I(EventBus var2, AntiNick var3) {
      var2.R(var3, PlayerGetNameEvent.class, 3, new AntiNickPlayerGetNameInvoker(var3));
   }
}
