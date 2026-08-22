package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.HandleChatEvent;
import Expo.event.events.PostTickEvent;
import Expo.event.invoker.AutoGGHandleChatInvoker;
import Expo.event.invoker.AutoGGPostTickInvoker;
import Expo.module.impl.misc.AutoGG;

public final class AutoGGBinder {
   private static final long a = 9398358130312L;

   public static void X(EventBus var0, AutoGG var3) {
      var0.R(var3, HandleChatEvent.class, 3, new AutoGGHandleChatInvoker(var3));
      var0.R(var3, PostTickEvent.class, 3, new AutoGGPostTickInvoker(var3));
   }

   private AutoGGBinder() {
   }
}
