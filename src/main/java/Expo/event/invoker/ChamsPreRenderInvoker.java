package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PreRenderEvent;
import Expo.module.impl.visual.Chams;

public final class ChamsPreRenderInvoker implements EventInvoker {
   final Chams j;

   public ChamsPreRenderInvoker(Chams var1) {
      this.j = var1;
   }

   public void c(long var1, Object var3) {
      this.j.onPreRender((PreRenderEvent)var3);
   }
}
