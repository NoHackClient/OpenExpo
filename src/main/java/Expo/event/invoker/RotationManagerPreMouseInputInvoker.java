package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PreMouseInputEvent;
import Expo.util.RotationManager;

public final class RotationManagerPreMouseInputInvoker implements EventInvoker {
   final RotationManager d;

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 580674948907L;
      this.d.onPreMouseInput(var4, (PreMouseInputEvent)var3);
   }

   public RotationManagerPreMouseInputInvoker(RotationManager var1) {
      this.d = var1;
   }
}
