package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.ReceivePacketEvent;
import Expo.module.impl.visual_utility.BlocksESP;

public final class BlocksESPReceivePacketInvoker implements EventInvoker {
   final BlocksESP n;

   public BlocksESPReceivePacketInvoker(BlocksESP var1) {
      this.n = var1;
   }

   public void c(long var1, Object var3) {
      this.n.onReceivePacket((ReceivePacketEvent)var3);
   }
}
