package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.Render2DEvent;
import Expo.module.impl.visual.BindGUI;











public final class BindGUIRender2DInvoker implements EventInvoker {
   final BindGUI v;

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 22858885616562L;
      this.v.onRender2D(var4, (Render2DEvent)var3);
   }

   public BindGUIRender2DInvoker(BindGUI var1) {
      this.v = var1;
   }
}
