package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.Render2DEvent;
import Expo.ui.ModuleTagRenderer;











public final class ModuleTagRendererRender2DInvoker implements EventInvoker {
   final ModuleTagRenderer F;

   public ModuleTagRendererRender2DInvoker(ModuleTagRenderer var1) {
      this.F = var1;
   }

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 180121362648L;
      this.F.onRender2D(var4, (Render2DEvent)var3);
   }
}
