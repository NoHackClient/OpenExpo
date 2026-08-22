package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.HandleChatEvent;
import Expo.event.events.PostTickEvent;
import Expo.event.events.Render2DEvent;
import Expo.event.invoker.FKCounterHandleChatInvoker;
import Expo.event.invoker.FKCounterPostTickInvoker;
import Expo.event.invoker.FKCounterRender2DInvoker;
import Expo.module.impl.visual_utility.FKCounter;

public final class FKCounterBinder {
   private static final long a = 38152282049884L;

   private FKCounterBinder() {
   }

   public static void p(EventBus var0, FKCounter var1) {
      var0.R(var1, PostTickEvent.class, 3, new FKCounterPostTickInvoker(var1));
      var0.R(var1, HandleChatEvent.class, 3, new FKCounterHandleChatInvoker(var1));
      var0.R(var1, Render2DEvent.class, 3, new FKCounterRender2DInvoker(var1));
   }
}
