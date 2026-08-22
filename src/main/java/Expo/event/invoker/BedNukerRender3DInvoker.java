package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.Render3DEvent;
import Expo.module.impl.world.BedNuker;











public final class BedNukerRender3DInvoker implements EventInvoker {
   final BedNuker H;

   public BedNukerRender3DInvoker(BedNuker var1) {
      this.H = var1;
   }

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 81796567226653L;
      this.H.onRender3D(var4, (Render3DEvent)var3);
   }
}
