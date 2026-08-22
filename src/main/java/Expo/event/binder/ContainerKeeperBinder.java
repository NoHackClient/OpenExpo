package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.PreUpdateEvent;
import Expo.event.events.ReceivePacketEvent;
import Expo.event.events.Render2DEvent;
import Expo.event.events.SendPacketEvent;
import Expo.event.events.WorldLoadEvent;
import Expo.event.invoker.ContainerKeeperPreUpdateInvoker;
import Expo.event.invoker.ContainerKeeperReceivePacketInvoker;
import Expo.event.invoker.ContainerKeeperRender2DInvoker;
import Expo.event.invoker.ContainerKeeperSendPacketInvoker;
import Expo.event.invoker.ContainerKeeperWorldLoadInvoker;
import Expo.module.impl.misc.ContainerKeeper;

public final class ContainerKeeperBinder {
   private static final long a = 35367237427810L;

   private ContainerKeeperBinder() {
   }

   public static void Q(EventBus var0, int var1, ContainerKeeper var2) {
      var0.R(var2, WorldLoadEvent.class, 3, new ContainerKeeperWorldLoadInvoker(var2));
      var0.R(var2, PreUpdateEvent.class, 3, new ContainerKeeperPreUpdateInvoker(var2));
      var0.R(var2, SendPacketEvent.class, 3, new ContainerKeeperSendPacketInvoker(var2));
      var0.R(var2, ReceivePacketEvent.class, 3, new ContainerKeeperReceivePacketInvoker(var2));
      var0.R(var2, Render2DEvent.class, 3, new ContainerKeeperRender2DInvoker(var2));
   }
}
