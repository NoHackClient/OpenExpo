package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.WorldLoadEvent;
import Expo.module.impl.combat.JumpReset;

public final class JumpResetWorldLoadInvoker implements EventInvoker {
   final JumpReset n;

   public JumpResetWorldLoadInvoker(JumpReset var1) {
      this.n = var1;
   }

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 32341045678306L;
      this.n.onWorldLoad(var4, (WorldLoadEvent)var3);
   }
}
