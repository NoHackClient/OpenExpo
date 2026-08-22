package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.Render3DEvent;
import Expo.module.impl.world.Scaffold;

public final class ScaffoldRender3DInvoker implements EventInvoker {
   final Scaffold o;

   public ScaffoldRender3DInvoker(Scaffold var1) {
      this.o = var1;
   }

   public void c(long var1, Object var3) {
      int var4 = (int)((var1 ^ 105666387853738L) >>> 32);
      int var5 = (int)((var1 ^ 105666387853738L) << 32 >>> 48);
      int var6 = (int)((var1 ^ 105666387853738L) << 48 >>> 48);
      this.o.onRender3D(var4, (short)var5, (Render3DEvent)var3, var6);
   }
}
