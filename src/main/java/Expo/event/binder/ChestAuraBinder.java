package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.CloseScreenEvent;
import Expo.event.events.EntityJoinWorldEvent;
import Expo.event.events.PlayerRightClickEvent;
import Expo.event.events.PreMouseInputEvent;
import Expo.event.invoker.ChestAuraCloseScreenInvoker;
import Expo.event.invoker.ChestAuraEntityJoinWorldInvoker;
import Expo.event.invoker.ChestAuraPlayerRightClickInvoker;
import Expo.event.invoker.ChestAuraPreMouseInputInvoker;
import Expo.module.impl.player.ChestAura;











public final class ChestAuraBinder {
   private static final long a = 63789128011315L;

   private ChestAuraBinder() {
   }

   public static void E(EventBus var0, ChestAura var1) {
      var0.R(var1, CloseScreenEvent.class, 3, new ChestAuraCloseScreenInvoker(var1));
      var0.R(var1, PreMouseInputEvent.class, 3, new ChestAuraPreMouseInputInvoker(var1));
      var0.R(var1, PlayerRightClickEvent.class, 3, new ChestAuraPlayerRightClickInvoker(var1));
      var0.R(var1, EntityJoinWorldEvent.class, 3, new ChestAuraEntityJoinWorldInvoker(var1));
   }
}
