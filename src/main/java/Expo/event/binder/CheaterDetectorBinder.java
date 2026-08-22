package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.EntityJoinWorldEvent;
import Expo.event.events.GetDisplayNameEvent;
import Expo.event.events.PlayerGetNameEvent;
import Expo.event.events.PostTickEvent;
import Expo.event.events.ReceivePacketEvent;
import Expo.event.events.WorldLoadEvent;
import Expo.event.invoker.CheaterDetectorEntityJoinWorldInvoker;
import Expo.event.invoker.CheaterDetectorGetDisplayNameInvoker;
import Expo.event.invoker.CheaterDetectorPlayerGetNameInvoker;
import Expo.event.invoker.CheaterDetectorPostTickInvoker;
import Expo.event.invoker.CheaterDetectorReceivePacketInvoker;
import Expo.event.invoker.CheaterDetectorWorldLoadInvoker;
import Expo.internal.CheaterDetector;

public final class CheaterDetectorBinder {
   private static final long a = 118608653688864L;

   public static void M(EventBus var0, CheaterDetector var1) {
      var0.R(var1, WorldLoadEvent.class, 3, new CheaterDetectorWorldLoadInvoker(var1));
      var0.R(var1, PostTickEvent.class, 3, new CheaterDetectorPostTickInvoker(var1));
      var0.R(var1, GetDisplayNameEvent.class, 3, new CheaterDetectorGetDisplayNameInvoker(var1));
      var0.R(var1, PlayerGetNameEvent.class, 3, new CheaterDetectorPlayerGetNameInvoker(var1));
      var0.R(var1, EntityJoinWorldEvent.class, 3, new CheaterDetectorEntityJoinWorldInvoker(var1));
      var0.R(var1, ReceivePacketEvent.class, 3, new CheaterDetectorReceivePacketInvoker(var1));
   }

   private CheaterDetectorBinder() {
   }
}
