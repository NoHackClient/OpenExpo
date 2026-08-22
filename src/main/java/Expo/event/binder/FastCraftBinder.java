package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.PostDrawScreenEvent;
import Expo.event.events.PreUpdateEvent;
import Expo.event.invoker.FastCraftPostDrawScreenInvoker;
import Expo.event.invoker.FastCraftPreUpdateInvoker;
import Expo.module.impl.player.FastCraft;

public final class FastCraftBinder {
   private static final long a = 127573475271188L;

   private FastCraftBinder() {
   }

   public static void t(EventBus var0, FastCraft var3) {
      var0.R(var3, PreUpdateEvent.class, 3, new FastCraftPreUpdateInvoker(var3));
      var0.R(var3, PostDrawScreenEvent.class, 3, new FastCraftPostDrawScreenInvoker(var3));
   }
}
