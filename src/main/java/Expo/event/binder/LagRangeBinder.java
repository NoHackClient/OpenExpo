package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.PostTickEvent;
import Expo.event.events.PreLivingUpdateEvent;
import Expo.event.events.Render2DEvent;
import Expo.event.events.SendPacketEvent;
import Expo.event.invoker.LagRangePostTickInvoker;
import Expo.event.invoker.LagRangePreLivingUpdateInvoker;
import Expo.event.invoker.LagRangeRender2DInvoker;
import Expo.event.invoker.LagRangeSendPacketInvoker;
import Expo.module.impl.combat.LagRange;











public final class LagRangeBinder {
   private static final long a = 117507985977009L;

   private LagRangeBinder() {
   }

   public static void D(EventBus var2, LagRange var3) {
      var2.R(var3, PreLivingUpdateEvent.class, 3, new LagRangePreLivingUpdateInvoker(var3));
      var2.R(var3, Render2DEvent.class, 3, new LagRangeRender2DInvoker(var3));
      var2.R(var3, PostTickEvent.class, 3, new LagRangePostTickInvoker(var3));
      var2.R(var3, SendPacketEvent.class, 3, new LagRangeSendPacketInvoker(var3));
   }
}
