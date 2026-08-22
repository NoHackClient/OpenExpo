package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.UpdateCameraAndRenderEvent;
import Expo.module.impl.combat.BlockHit;

// update new version
public final class BlockHitUpdateCameraAndRenderInvoker implements EventInvoker {
   final BlockHit W;

   public BlockHitUpdateCameraAndRenderInvoker(BlockHit var1) {
      this.W = var1;
   }

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 93095070447340L;
      this.W.onUpdateCameraAndRender(var4, (UpdateCameraAndRenderEvent)var3);
   }
}
