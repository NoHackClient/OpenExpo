package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.UpdateCameraAndRenderEvent;
import Expo.module.impl.combat.AutoBlock;











public final class AutoBlockUpdateCameraAndRenderInvoker implements EventInvoker {
   final AutoBlock B;

   public AutoBlockUpdateCameraAndRenderInvoker(AutoBlock var1) {
      this.B = var1;
   }

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 93095070447340L;
      this.B.onUpdateCameraAndRender(var4, (UpdateCameraAndRenderEvent)var3);
   }
}
