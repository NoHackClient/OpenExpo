package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.PostTickEvent;
import Expo.event.events.WorldLoadEvent;
import Expo.event.invoker.HypixelGameStatePostTickInvoker;
import Expo.event.invoker.HypixelGameStateWorldLoadInvoker;
import Expo.util.HypixelGameState;











public final class HypixelGameStateBinder {
   private static final long a = 12737479543531L;

   public static void F(EventBus var2, HypixelGameState var3) {
      var2.R(var3, PostTickEvent.class, 3, new HypixelGameStatePostTickInvoker(var3));
      var2.R(var3, WorldLoadEvent.class, 3, new HypixelGameStateWorldLoadInvoker(var3));
   }

   private HypixelGameStateBinder() {
   }
}
