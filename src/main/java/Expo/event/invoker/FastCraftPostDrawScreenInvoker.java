package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PostDrawScreenEvent;
import Expo.module.impl.player.FastCraft;

public final class FastCraftPostDrawScreenInvoker implements EventInvoker {
   final FastCraft z;

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 59805651871773L;
      this.z.onPostDrawScreen((PostDrawScreenEvent)var3, var4);
   }

   public FastCraftPostDrawScreenInvoker(FastCraft var1) {
      this.z = var1;
   }
}
