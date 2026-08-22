package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.PostTickEvent;
import Expo.event.events.PreLivingUpdateEvent;
import Expo.event.events.Render2DEvent;
import Expo.event.events.SendPacketEvent;
import Expo.event.invoker.FakeLagPostTickInvoker;
import Expo.event.invoker.FakeLagPreLivingUpdateInvoker;
import Expo.event.invoker.FakeLagRender2DInvoker;
import Expo.event.invoker.FakeLagSendPacketInvoker;
import Expo.module.impl.combat.FakeLag;

public final class FakeLagBinder {
   private static final long a = 115000528628494L;

   public static void S(EventBus var2, FakeLag var3) {
      var2.R(var3, PreLivingUpdateEvent.class, 3, new FakeLagPreLivingUpdateInvoker(var3));
      var2.R(var3, PostTickEvent.class, 3, new FakeLagPostTickInvoker(var3));
      var2.R(var3, Render2DEvent.class, 3, new FakeLagRender2DInvoker(var3));
      var2.R(var3, SendPacketEvent.class, 3, new FakeLagSendPacketInvoker(var3));
   }

   private FakeLagBinder() {
   }
}
