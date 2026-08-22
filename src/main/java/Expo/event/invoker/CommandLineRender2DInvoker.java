package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.Render2DEvent;
import Expo.module.impl.misc.CommandLine;











public final class CommandLineRender2DInvoker implements EventInvoker {
   final CommandLine y;

   public void c(long var1, Object var3) {
      int var4 = (int)((var1 ^ 99491052889301L) >>> 32);
      int var5 = (int)((var1 ^ 99491052889301L) << 32 >>> 48);
      int var6 = (int)((var1 ^ 99491052889301L) << 48 >>> 48);
      this.y.onRender2D(var4, var5, (Render2DEvent)var3, var6);
   }

   public CommandLineRender2DInvoker(CommandLine var1) {
      this.y = var1;
   }
}
