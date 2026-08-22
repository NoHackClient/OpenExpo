package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.MoveInputEvent;
import Expo.module.impl.world.Scaffold;

public final class ScaffoldMoveInputInvoker implements EventInvoker {
   final Scaffold H;

   public ScaffoldMoveInputInvoker(Scaffold var1) {
      this.H = var1;
   }

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 47614214846102L;
      this.H.onMoveInput(var4, (MoveInputEvent)var3);
   }
}
