package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PostTickEvent;
import Expo.module.impl.misc.AutoGG;











public final class AutoGGPostTickInvoker implements EventInvoker {
   final AutoGG X;

   public AutoGGPostTickInvoker(AutoGG var1) {
      this.X = var1;
   }

   public void c(long var1, Object var3) {
      int var4 = (int)((var1 ^ 128070319569472L) >>> 48);
      int var5 = (int)((var1 ^ 128070319569472L) << 16 >>> 48);
      int var6 = (int)((var1 ^ 128070319569472L) << 32 >>> 32);
      this.X.onPostTick((short)var4, (PostTickEvent)var3, (char)var5, var6);
   }
}
