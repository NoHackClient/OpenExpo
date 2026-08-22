package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PreTickEvent;
import Expo.module.impl.combat.KeepSprint;











public final class KeepSprintPreTickInvoker implements EventInvoker {
   final KeepSprint V;

   public KeepSprintPreTickInvoker(KeepSprint var1) {
      this.V = var1;
   }

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 37220008165549L;
      this.V.onPreTick(var4, (PreTickEvent)var3);
   }
}
