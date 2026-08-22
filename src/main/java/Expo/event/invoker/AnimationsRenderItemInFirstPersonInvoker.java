package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.RenderItemInFirstPersonEvent;
import Expo.module.impl.visual.Animations;

public final class AnimationsRenderItemInFirstPersonInvoker implements EventInvoker {
   final Animations u;

   public AnimationsRenderItemInFirstPersonInvoker(Animations var1) {
      this.u = var1;
   }

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 99742510836442L;
      this.u.onRenderItemInFirstPerson(var4, (RenderItemInFirstPersonEvent)var3);
   }
}
