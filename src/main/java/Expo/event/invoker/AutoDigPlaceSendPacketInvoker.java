package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.SendPacketEvent;
import Expo.module.impl.world.AutoDigPlace;

public final class AutoDigPlaceSendPacketInvoker implements EventInvoker {
   final AutoDigPlace w;

   public void c(long var1, Object var3) {
      int var4 = (int)((var1 ^ 91582858884075L) >>> 32);
      int var5 = (int)((var1 ^ 91582858884075L) << 32 >>> 48);
      int var6 = (int)((var1 ^ 91582858884075L) << 48 >>> 48);
      this.w.onSendPacket(var4, (char)var5, (char)var6, (SendPacketEvent)var3);
   }

   public AutoDigPlaceSendPacketInvoker(AutoDigPlace var1) {
      this.w = var1;
   }
}
