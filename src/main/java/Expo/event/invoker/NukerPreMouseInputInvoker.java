package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PreMouseInputEvent;
import Expo.module.impl.world.Nuker;

public final class NukerPreMouseInputInvoker implements EventInvoker {
   final Nuker n;

   public NukerPreMouseInputInvoker(Nuker var1) {
      this.n = var1;
   }

   public void c(long var1, Object var3) {
      int var4 = (int)((var1 ^ 105785694177555L) >>> 32);
      int var5 = (int)((var1 ^ 105785694177555L) << 32 >>> 56);
      int var6 = (int)((var1 ^ 105785694177555L) << 40 >>> 40);
      this.n.onPreMouseInput((PreMouseInputEvent)var3, var4, (byte)var5, var6);
   }
}
