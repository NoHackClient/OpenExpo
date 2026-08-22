package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.Render2DEvent;
import Expo.module.impl.combat.LagRange;

public final class LagRangeRender2DInvoker implements EventInvoker {
   final LagRange v;

   public void c(long var1, Object var3) {
      int var4 = (int)((var1 ^ 104863093907819L) >>> 48);
      long var5 = (var1 ^ 104863093907819L) << 16 >>> 16;
      this.v.onRender2D((short)var4, (Render2DEvent)var3, var5);
   }

   public LagRangeRender2DInvoker(LagRange var1) {
      this.v = var1;
   }
}
