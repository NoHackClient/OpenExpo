package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.KnockbackEvent;
import Expo.module.impl.combat.JumpReset;

public final class JumpResetKnockbackInvoker implements EventInvoker {
   final JumpReset X;

   public JumpResetKnockbackInvoker(JumpReset var1) {
      this.X = var1;
   }

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 113496739923286L;
      this.X.onKnockback((KnockbackEvent)var3, var4);
   }
}
