package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PreMouseInputEvent;
import Expo.module.impl.combat.Velocity;











public final class VelocityPreMouseInputInvoker implements EventInvoker {
   final Velocity w;

   public void c(long var1, Object var3) {
      int var4 = (int)((var1 ^ 113735458880614L) >>> 32);
      int var5 = (int)((var1 ^ 113735458880614L) << 32 >>> 48);
      int var6 = (int)((var1 ^ 113735458880614L) << 48 >>> 48);
      this.w.onPreMouseInput(var4, (PreMouseInputEvent)var3, (char)var5, (short)var6);
   }

   public VelocityPreMouseInputInvoker(Velocity var1) {
      this.w = var1;
   }
}
