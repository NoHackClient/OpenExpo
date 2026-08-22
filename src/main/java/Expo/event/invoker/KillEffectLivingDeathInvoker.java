package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.LivingDeathEvent;
import Expo.module.impl.visual.KillEffect;

public final class KillEffectLivingDeathInvoker implements EventInvoker {
   final KillEffect j;

   public KillEffectLivingDeathInvoker(KillEffect var1) {
      this.j = var1;
   }

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 1065049575370L;
      this.j.onLivingDeath((LivingDeathEvent)var3, var4);
   }
}
