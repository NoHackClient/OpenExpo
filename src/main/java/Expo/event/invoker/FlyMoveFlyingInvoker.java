package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.MoveFlyingEvent;
import Expo.module.impl.movement.Fly;











public final class FlyMoveFlyingInvoker implements EventInvoker {
   final Fly x;

   public void c(long var1, Object var3) {
      int var4 = (int)((var1 ^ 386309866780L) >>> 48);
      int var5 = (int)((var1 ^ 386309866780L) << 16 >>> 32);
      int var6 = (int)((var1 ^ 386309866780L) << 48 >>> 48);
      this.x.onMoveFlying((char)var4, var5, (short)var6, (MoveFlyingEvent)var3);
   }

   public FlyMoveFlyingInvoker(Fly var1) {
      this.x = var1;
   }
}
