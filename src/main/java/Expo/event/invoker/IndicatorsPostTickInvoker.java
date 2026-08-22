package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PostTickEvent;
import Expo.module.impl.visual_utility.Indicators;

public final class IndicatorsPostTickInvoker implements EventInvoker {
   final Indicators C;

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 28439512310249L;
      this.C.onPostTick(var4, (PostTickEvent)var3);
   }

   public IndicatorsPostTickInvoker(Indicators var1) {
      this.C = var1;
   }
}
