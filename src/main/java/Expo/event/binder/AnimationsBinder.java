package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.GetArmSwingAnimationEndEvent;
import Expo.event.events.RenderItemInFirstPersonEvent;
import Expo.event.invoker.AnimationsGetArmSwingAnimationEndInvoker;
import Expo.event.invoker.AnimationsRenderItemInFirstPersonInvoker;
import Expo.module.impl.visual.Animations;











public final class AnimationsBinder {
   private static final long a = 30234545867378L;

   private AnimationsBinder() {
   }

   public static void A(EventBus var0, Animations var3) {
      var0.R(var3, GetArmSwingAnimationEndEvent.class, 3, new AnimationsGetArmSwingAnimationEndInvoker(var3));
      var0.R(var3, RenderItemInFirstPersonEvent.class, 3, new AnimationsRenderItemInFirstPersonInvoker(var3));
   }

}
