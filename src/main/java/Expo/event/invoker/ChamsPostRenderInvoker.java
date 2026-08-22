package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PostRenderEvent;
import Expo.module.impl.visual.Chams;

public final class ChamsPostRenderInvoker implements EventInvoker {
   final Chams R;

   public void c(long var1, Object var3) {
      this.R.onPostRender((PostRenderEvent)var3);
   }

   public ChamsPostRenderInvoker(Chams var1) {
      this.R = var1;
   }
}
