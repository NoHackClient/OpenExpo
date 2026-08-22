package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.Render2DEvent;
import Expo.module.impl.combat.FakeLag;











public final class FakeLagRender2DInvoker implements EventInvoker {
   final FakeLag l;

   public FakeLagRender2DInvoker(FakeLag var1) {
      this.l = var1;
   }

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 16662789712135L;
      this.l.onRender2D(var4, (Render2DEvent)var3);
   }
}
