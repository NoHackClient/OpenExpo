package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.module.impl.visual.KeyStrokes;
import java.awt.event.MouseEvent;

public final class KeyStrokesMouseInvoker implements EventInvoker {
   final KeyStrokes b;

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 56373307993631L;
      this.b.onMouse((MouseEvent)var3, var4);
   }

   public KeyStrokesMouseInvoker(KeyStrokes var1) {
      this.b = var1;
   }
}
