package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.SendPacketEvent;
import Expo.module.impl.world.Nuker;











public final class NukerSendPacketInvoker implements EventInvoker {
   final Nuker M;

   public NukerSendPacketInvoker(Nuker var1) {
      this.M = var1;
   }

   public void c(long var1, Object var3) {
      long var4 = (var1 ^ 28108916299868L) >>> 16;
      int var6 = (int)((var1 ^ 28108916299868L) << 48 >>> 48);
      this.M.onSendPacket((SendPacketEvent)var3, var4, (short)var6);
   }
}
