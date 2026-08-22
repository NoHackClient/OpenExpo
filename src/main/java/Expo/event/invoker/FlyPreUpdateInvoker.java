package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PreUpdateEvent;
import Expo.module.impl.movement.Fly;

public final class FlyPreUpdateInvoker implements EventInvoker {
   final Fly O;

   public FlyPreUpdateInvoker(Fly var1) {
      this.O = var1;
   }

   public void c(long var1, Object var3) {
      this.O.onPreUpdate((PreUpdateEvent)var3);
   }
}
