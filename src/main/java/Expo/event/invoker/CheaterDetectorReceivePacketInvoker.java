package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.ReceivePacketEvent;
import Expo.internal.CheaterDetector;











public final class CheaterDetectorReceivePacketInvoker implements EventInvoker {
   final CheaterDetector C;

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 53222667029042L;
      this.C.onReceivePacket((ReceivePacketEvent)var3, var4);
   }

   public CheaterDetectorReceivePacketInvoker(CheaterDetector var1) {
      this.C = var1;
   }
}
