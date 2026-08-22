package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PreMouseInputEvent;
import Expo.module.impl.combat.KillAura;

public final class KillAuraPreMouseInputInvoker implements EventInvoker {
   final KillAura b;

   public KillAuraPreMouseInputInvoker(KillAura var1) {
      this.b = var1;
   }

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 21329616232220L;
      this.b.onPreMouseInput(var4, (PreMouseInputEvent)var3);
   }
}
