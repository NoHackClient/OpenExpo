package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.ReceivePacketEvent;
import Expo.util.RotationManager;

public final class RotationManagerReceivePacketInvoker implements EventInvoker {
   final RotationManager W;

   public RotationManagerReceivePacketInvoker(RotationManager var1) {
      this.W = var1;
   }

   public void c(long var1, Object var3) {
      this.W.onReceivePacket((ReceivePacketEvent)var3);
   }
}
