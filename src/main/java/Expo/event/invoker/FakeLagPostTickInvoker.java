package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PostTickEvent;
import Expo.module.impl.combat.FakeLag;

public final class FakeLagPostTickInvoker implements EventInvoker {
   final FakeLag N;

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 108776047534947L;
      this.N.onPostTick((PostTickEvent)var3, var4);
   }

   public FakeLagPostTickInvoker(FakeLag var1) {
      this.N = var1;
   }
}
