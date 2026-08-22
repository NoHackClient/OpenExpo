package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.ReceivePacketEvent;
import Expo.module.impl.combat.SprintReset;











public final class SprintResetReceivePacketInvoker implements EventInvoker {
   final SprintReset I;

   public void c(long var1, Object var3) {
      int var4 = (int)((var1 ^ 130260830043517L) >>> 48);
      int var5 = (int)((var1 ^ 130260830043517L) << 16 >>> 32);
      int var6 = (int)((var1 ^ 130260830043517L) << 48 >>> 48);
      this.I.onReceivePacket((short)var4, var5, (ReceivePacketEvent)var3, (char)var6);
   }

   public SprintResetReceivePacketInvoker(SprintReset var1) {
      this.I = var1;
   }
}
