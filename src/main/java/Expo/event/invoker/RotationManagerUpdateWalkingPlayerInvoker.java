package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.UpdateWalkingPlayerEvent;
import Expo.util.RotationManager;

public final class RotationManagerUpdateWalkingPlayerInvoker implements EventInvoker {
   final RotationManager k;

   public void c(long var1, Object var3) {
      int var4 = (int)((var1 ^ 15875587686136L) >>> 32);
      int var5 = (int)((var1 ^ 15875587686136L) << 32 >>> 48);
      int var6 = (int)((var1 ^ 15875587686136L) << 48 >>> 48);
      this.k.onUpdateWalkingPlayer(var4, (char)var5, (char)var6, (UpdateWalkingPlayerEvent)var3);
   }

   public RotationManagerUpdateWalkingPlayerInvoker(RotationManager var1) {
      this.k = var1;
   }
}
