package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.KnockbackEvent;
import Expo.event.events.PreLivingUpdateEvent;
import Expo.event.events.PreMouseInputEvent;
import Expo.event.events.PreTickEvent;
import Expo.event.events.ReceivePacketEvent;
import Expo.event.events.WorldLoadEvent;
import Expo.event.invoker.VelocityKnockbackInvoker;
import Expo.event.invoker.VelocityPreLivingUpdateInvoker;
import Expo.event.invoker.VelocityPreMouseInputInvoker;
import Expo.event.invoker.VelocityPreTickInvoker;
import Expo.event.invoker.VelocityReceivePacketInvoker;
import Expo.event.invoker.VelocityWorldLoadInvoker;
import Expo.module.impl.combat.Velocity;











public final class VelocityBinder {
   private static final long a = 92901142756133L;

   private VelocityBinder() {
   }

   public static void T(EventBus var0, Velocity var1) {
      var0.R(var1, KnockbackEvent.class, 3, new VelocityKnockbackInvoker(var1));
      var0.R(var1, PreMouseInputEvent.class, 4, new VelocityPreMouseInputInvoker(var1));
      var0.R(var1, ReceivePacketEvent.class, 3, new VelocityReceivePacketInvoker(var1));
      var0.R(var1, PreLivingUpdateEvent.class, 3, new VelocityPreLivingUpdateInvoker(var1));
      var0.R(var1, PreTickEvent.class, 3, new VelocityPreTickInvoker(var1));
      var0.R(var1, WorldLoadEvent.class, 3, new VelocityWorldLoadInvoker(var1));
   }
}
