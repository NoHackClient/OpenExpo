package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.ReceivePacketEvent;
import Expo.module.impl.combat.AutoBlock;











public final class AutoBlockReceivePacketInvoker implements EventInvoker {
   final AutoBlock c;

   public AutoBlockReceivePacketInvoker(AutoBlock var1) {
      this.c = var1;
   }

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 79568930087429L;
      this.c.onReceivePacket((ReceivePacketEvent)var3, var4);
   }
}
