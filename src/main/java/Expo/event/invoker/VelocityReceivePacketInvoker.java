package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.ReceivePacketEvent;
import Expo.module.impl.combat.Velocity;











public final class VelocityReceivePacketInvoker implements EventInvoker {
   final Velocity n;

   public VelocityReceivePacketInvoker(Velocity var1) {
      this.n = var1;
   }

   public void c(long var1, Object var3) {
      int var4 = (int)((var1 ^ 40178900282032L) >>> 32);
      int var5 = (int)((var1 ^ 40178900282032L) << 32 >>> 48);
      int var6 = (int)((var1 ^ 40178900282032L) << 48 >>> 48);
      this.n.onReceivePacket(var4, (char)var5, var6, (ReceivePacketEvent)var3);
   }
}
