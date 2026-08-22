package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.HeldItemChangeEvent;
import Expo.module.impl.world.Scaffold;

public final class ScaffoldHeldItemChangeInvoker implements EventInvoker {
   final Scaffold I;

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 131283140354548L;
      this.I.onHeldItemChange((HeldItemChangeEvent)var3, var4);
   }

   public ScaffoldHeldItemChangeInvoker(Scaffold var1) {
      this.I = var1;
   }
}
