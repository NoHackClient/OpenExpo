package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PostTickEvent;
import Expo.module.impl.misc.Denick;

public final class DenickPostTickInvoker implements EventInvoker {
   final Denick l;

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 132885893961669L;
      this.l.onPostTick((PostTickEvent)var3, var4);
   }

   public DenickPostTickInvoker(Denick var1) {
      this.l = var1;
   }
}
