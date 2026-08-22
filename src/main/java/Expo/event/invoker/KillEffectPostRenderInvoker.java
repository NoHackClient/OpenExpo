package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PostRenderEvent;
import Expo.module.impl.visual.KillEffect;











public final class KillEffectPostRenderInvoker implements EventInvoker {
   final KillEffect z;

   public KillEffectPostRenderInvoker(KillEffect var1) {
      this.z = var1;
   }

   public void c(long var1, Object var3) {
      this.z.onPostRender((PostRenderEvent)var3);
   }
}
