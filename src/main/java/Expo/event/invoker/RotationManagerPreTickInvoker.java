package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PreTickEvent;
import Expo.util.RotationManager;

public final class RotationManagerPreTickInvoker implements EventInvoker {
   final RotationManager P;

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 56874804424411L;
      this.P.onPreTick((PreTickEvent)var3, var4);
   }

   public RotationManagerPreTickInvoker(RotationManager var1) {
      this.P = var1;
   }
}
