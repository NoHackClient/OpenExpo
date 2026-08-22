package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.WorldLoadEvent;
import Expo.module.impl.misc.ContainerKeeper;

public final class ContainerKeeperWorldLoadInvoker implements EventInvoker {
   final ContainerKeeper f;

   public ContainerKeeperWorldLoadInvoker(ContainerKeeper var1) {
      this.f = var1;
   }

   public void c(long var1, Object var3) {
      this.f.onWorldLoad((WorldLoadEvent)var3);
   }
}
