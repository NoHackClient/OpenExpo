package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.ReceivePacketEvent;
import Expo.module.impl.misc.ContainerKeeper;











public final class ContainerKeeperReceivePacketInvoker implements EventInvoker {
   final ContainerKeeper u;

   public ContainerKeeperReceivePacketInvoker(ContainerKeeper var1) {
      this.u = var1;
   }

   public void c(long var1, Object var3) {
      this.u.onReceivePacket((ReceivePacketEvent)var3);
   }
}
