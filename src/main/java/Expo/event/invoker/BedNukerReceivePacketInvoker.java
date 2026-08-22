package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.ReceivePacketEvent;
import Expo.module.impl.world.BedNuker;

public final class BedNukerReceivePacketInvoker implements EventInvoker {
   final BedNuker c;

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 44413359950638L;
      this.c.onReceivePacket((ReceivePacketEvent)var3, var4);
   }

   public BedNukerReceivePacketInvoker(BedNuker var1) {
      this.c = var1;
   }
}
