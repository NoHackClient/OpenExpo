package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.EntityJoinWorldEvent;
import Expo.event.events.PlayerRightClickEvent;
import Expo.event.events.PostTickEvent;
import Expo.event.events.Render3DEvent;
import Expo.event.invoker.ChestESPEntityJoinWorldInvoker;
import Expo.event.invoker.ChestESPPlayerRightClickInvoker;
import Expo.event.invoker.ChestESPPostTickInvoker;
import Expo.event.invoker.ChestESPRender3DInvoker;
import Expo.module.impl.visual_utility.ChestESP;

public final class ChestESPBinder {
   private static final long a = 43376417199215L;

   public static void N(EventBus var2, ChestESP var3) {
      var2.R(var3, PostTickEvent.class, 3, new ChestESPPostTickInvoker(var3));
      var2.R(var3, Render3DEvent.class, 3, new ChestESPRender3DInvoker(var3));
      var2.R(var3, PlayerRightClickEvent.class, 3, new ChestESPPlayerRightClickInvoker(var3));
      var2.R(var3, EntityJoinWorldEvent.class, 3, new ChestESPEntityJoinWorldInvoker(var3));
   }

   private ChestESPBinder() {
   }
}
