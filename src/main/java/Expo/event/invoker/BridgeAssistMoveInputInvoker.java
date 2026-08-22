package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.MoveInputEvent;
import Expo.module.impl.world.BridgeAssist;











public final class BridgeAssistMoveInputInvoker implements EventInvoker {
   final BridgeAssist N;

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 47529560130461L;
      this.N.onMoveInput((MoveInputEvent)var3, var4);
   }

   public BridgeAssistMoveInputInvoker(BridgeAssist var1) {
      this.N = var1;
   }
}
