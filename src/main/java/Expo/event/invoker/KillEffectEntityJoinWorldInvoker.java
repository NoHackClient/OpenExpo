package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.EntityJoinWorldEvent;
import Expo.module.impl.visual.KillEffect;

public final class KillEffectEntityJoinWorldInvoker implements EventInvoker {
   final KillEffect S;

   public void c(long var1, Object var3) {
      this.S.onEntityJoinWorld((EntityJoinWorldEvent)var3);
   }

   public KillEffectEntityJoinWorldInvoker(KillEffect var1) {
      this.S = var1;
   }
}
