package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.PostTickEvent;
import Expo.event.events.PreTickEvent;
import Expo.event.invoker.MainMenuThemePostTickInvoker;
import Expo.event.invoker.MainMenuThemePreTickInvoker;
import Expo.ui.screen.MainMenuTheme;











public final class MainMenuThemeBinder {
   private static final long a = 115498613439651L;

   private MainMenuThemeBinder() {
   }

   public static void D(EventBus var0, MainMenuTheme var1) {
      var0.R(var1, PreTickEvent.class, 3, new MainMenuThemePreTickInvoker(var1));
      var0.R(var1, PostTickEvent.class, 3, new MainMenuThemePostTickInvoker(var1));
   }

}
