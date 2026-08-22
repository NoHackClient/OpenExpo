package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.SendPacketEvent;
import Expo.module.impl.misc.ContainerKeeper;











public final class ContainerKeeperSendPacketInvoker implements EventInvoker {
   final ContainerKeeper r;

   public void c(long var1, Object var3) {
      int var4 = (int)((var1 ^ 46172479764612L) >>> 32);
      int var5 = (int)((var1 ^ 46172479764612L) << 32 >>> 48);
      int var6 = (int)((var1 ^ 46172479764612L) << 48 >>> 48);
      this.r.onSendPacket((SendPacketEvent)var3, var4, (short)var5, var6);
   }

   public ContainerKeeperSendPacketInvoker(ContainerKeeper var1) {
      this.r = var1;
   }
}
