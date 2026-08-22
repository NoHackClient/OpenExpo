package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.Render3DEvent;
import Expo.internal.MiningRenderSubscriber;











public final class MiningRenderSubscriberRender3DInvoker implements EventInvoker {
   final MiningRenderSubscriber s;

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 89412572697541L;
      this.s.onRender3D(var4, (Render3DEvent)var3);
   }

   public MiningRenderSubscriberRender3DInvoker(MiningRenderSubscriber var1) {
      this.s = var1;
   }
}
