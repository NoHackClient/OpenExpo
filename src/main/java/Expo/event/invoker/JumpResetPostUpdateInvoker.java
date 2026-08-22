package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PostUpdateEvent;
import Expo.module.impl.combat.JumpReset;

public final class JumpResetPostUpdateInvoker implements EventInvoker {
   final JumpReset J;

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 45194173069389L;
      this.J.onPostUpdate(var4, (PostUpdateEvent)var3);
   }

   public JumpResetPostUpdateInvoker(JumpReset var1) {
      this.J = var1;
   }
}
