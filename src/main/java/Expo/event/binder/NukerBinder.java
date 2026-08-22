package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.HeldItemChangeEvent;
import Expo.event.events.PreMouseInputEvent;
import Expo.event.events.SendPacketEvent;
import Expo.event.invoker.NukerHeldItemChangeInvoker;
import Expo.event.invoker.NukerPreMouseInputInvoker;
import Expo.event.invoker.NukerSendPacketInvoker;
import Expo.module.impl.world.Nuker;

public final class NukerBinder {
   private static final long a = 16196326278722L;

   private NukerBinder() {
   }

   public static void p(EventBus var0, Nuker var3) {
      var0.R(var3, HeldItemChangeEvent.class, 3, new NukerHeldItemChangeInvoker(var3));
      var0.R(var3, PreMouseInputEvent.class, 3, new NukerPreMouseInputInvoker(var3));
      var0.R(var3, SendPacketEvent.class, 3, new NukerSendPacketInvoker(var3));
   }
}
