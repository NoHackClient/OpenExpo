package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PreUpdateEvent;
import Expo.module.impl.movement.Speed;

public final class SpeedPreUpdateInvoker implements EventInvoker {
   final Speed W;

   public SpeedPreUpdateInvoker(Speed var1) {
      this.W = var1;
   }

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 66726729104784L;
      this.W.onPreUpdate(var4, (PreUpdateEvent)var3);
   }
}
