package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PreMouseInputEvent;
import Expo.module.impl.world.AutoTool;

public final class AutoToolPreMouseInputInvoker implements EventInvoker {
   final AutoTool g;

   public AutoToolPreMouseInputInvoker(AutoTool var1) {
      this.g = var1;
   }

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 111044828220479L;
      this.g.onPreMouseInput(var4, (PreMouseInputEvent)var3);
   }
}
