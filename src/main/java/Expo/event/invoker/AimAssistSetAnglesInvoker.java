package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.SetAnglesEvent;
import Expo.module.impl.combat.AimAssist;











public final class AimAssistSetAnglesInvoker implements EventInvoker {
   final AimAssist E;

   public AimAssistSetAnglesInvoker(AimAssist var1) {
      this.E = var1;
   }

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 51895932060223L;
      this.E.onSetAngles((SetAnglesEvent)var3, var4);
   }
}
