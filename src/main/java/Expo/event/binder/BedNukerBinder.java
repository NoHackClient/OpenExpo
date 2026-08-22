package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.ClickMouseEvent;
import Expo.event.events.IsPressedEvent;
import Expo.event.events.PreMouseInputEvent;
import Expo.event.events.PreTickEvent;
import Expo.event.events.ReceivePacketEvent;
import Expo.event.events.Render2DEvent;
import Expo.event.events.Render3DEvent;
import Expo.event.events.SendPacketEvent;
import Expo.event.invoker.BedNukerClickMouseInvoker;
import Expo.event.invoker.BedNukerIsPressedInvoker;
import Expo.event.invoker.BedNukerPreMouseInputInvoker;
import Expo.event.invoker.BedNukerPreTickInvoker;
import Expo.event.invoker.BedNukerReceivePacketInvoker;
import Expo.event.invoker.BedNukerRender2DInvoker;
import Expo.event.invoker.BedNukerRender3DInvoker;
import Expo.event.invoker.BedNukerSendPacketInvoker;
import Expo.module.impl.world.BedNuker;

public final class BedNukerBinder {
   private static final long a = 29300658956394L;

   public static void d(EventBus var2, BedNuker var3) {
      var2.R(var3, ClickMouseEvent.class, 3, new BedNukerClickMouseInvoker(var3));
      var2.R(var3, IsPressedEvent.class, 3, new BedNukerIsPressedInvoker(var3));
      var2.R(var3, PreMouseInputEvent.class, 4, new BedNukerPreMouseInputInvoker(var3));
      var2.R(var3, PreTickEvent.class, 3, new BedNukerPreTickInvoker(var3));
      var2.R(var3, ReceivePacketEvent.class, 3, new BedNukerReceivePacketInvoker(var3));
      var2.R(var3, SendPacketEvent.class, 3, new BedNukerSendPacketInvoker(var3));
      var2.R(var3, Render3DEvent.class, 3, new BedNukerRender3DInvoker(var3));
      var2.R(var3, Render2DEvent.class, 3, new BedNukerRender2DInvoker(var3));
   }

   private BedNukerBinder() {
   }
}
