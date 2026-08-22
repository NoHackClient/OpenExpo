package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PreUpdateEvent;
import Expo.module.impl.misc.ContainerKeeper;

public final class ContainerKeeperPreUpdateInvoker implements EventInvoker {
   final ContainerKeeper r;

   public ContainerKeeperPreUpdateInvoker(ContainerKeeper var1) {
      this.r = var1;
   }

   public void c(long var1, Object var3) {
      int var4 = (int)((var1 ^ 46228865813315L) >>> 32);
      int var5 = (int)((var1 ^ 46228865813315L) << 32 >>> 40);
      int var6 = (int)((var1 ^ 46228865813315L) << 56 >>> 56);
      this.r.onPreUpdate(var4, var5, (byte)var6, (PreUpdateEvent)var3);
   }
}
