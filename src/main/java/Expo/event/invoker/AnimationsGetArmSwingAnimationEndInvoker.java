package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.GetArmSwingAnimationEndEvent;
import Expo.module.impl.visual.Animations;











public final class AnimationsGetArmSwingAnimationEndInvoker implements EventInvoker {
   final Animations N;

   public void c(long var1, Object var3) {
      this.N.onGetArmSwingAnimationEnd((GetArmSwingAnimationEndEvent)var3);
   }

   public AnimationsGetArmSwingAnimationEndInvoker(Animations var1) {
      this.N = var1;
   }
}
