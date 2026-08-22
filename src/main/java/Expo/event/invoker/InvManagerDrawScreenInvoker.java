package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.DrawScreenEvent;
import Expo.module.impl.player.InvManager;











public final class InvManagerDrawScreenInvoker implements EventInvoker {
   final InvManager y;

   public void c(long var1, Object var3) {
      int var4 = (int)((var1 ^ 29538362714694L) >>> 48);
      int var5 = (int)((var1 ^ 29538362714694L) << 16 >>> 32);
      int var6 = (int)((var1 ^ 29538362714694L) << 48 >>> 48);
      this.y.onDrawScreen((char)var4, var5, var6, (DrawScreenEvent)var3);
   }

   public InvManagerDrawScreenInvoker(InvManager var1) {
      this.y = var1;
   }
}
