package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.UpdateWalkingPlayerEvent;
import Expo.module.impl.movement.FastFall;

public final class FastFallUpdateWalkingPlayerInvoker implements EventInvoker {
   final FastFall Z;

   public void c(long var1, Object var3) {
      this.Z.onUpdateWalkingPlayer((UpdateWalkingPlayerEvent)var3);
   }

   public FastFallUpdateWalkingPlayerInvoker(FastFall var1) {
      this.Z = var1;
   }
}
