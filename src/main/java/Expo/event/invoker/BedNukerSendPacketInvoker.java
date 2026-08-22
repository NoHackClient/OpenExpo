package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.SendPacketEvent;
import Expo.module.impl.world.BedNuker;











public final class BedNukerSendPacketInvoker implements EventInvoker {
   final BedNuker P;

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 52335340497147L;
      this.P.onSendPacket((SendPacketEvent)var3, var4);
   }

   public BedNukerSendPacketInvoker(BedNuker var1) {
      this.P = var1;
   }
}
