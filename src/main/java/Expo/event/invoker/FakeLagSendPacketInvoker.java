package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.SendPacketEvent;
import Expo.module.impl.combat.FakeLag;

public final class FakeLagSendPacketInvoker implements EventInvoker {
   final FakeLag O;

   public FakeLagSendPacketInvoker(FakeLag var1) {
      this.O = var1;
   }

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 127650273833940L;
      this.O.onSendPacket(var4, (SendPacketEvent)var3);
   }
}
