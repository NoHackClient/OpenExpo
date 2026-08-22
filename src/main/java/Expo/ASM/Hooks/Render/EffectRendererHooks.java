package Expo.ASM.Hooks.Render;

import Expo.ASM.Hooks.CallbackInfo;
import Expo.module.impl.configuration.Gadgets;











public class EffectRendererHooks {
   public static void cancelDestroyParticles(CallbackInfo var0) {
      if (Gadgets.noMiningParticles.c()) {
         var0.cancel();
      }
   }

   public static void cancelHitParticles(CallbackInfo var0) {
      if (Gadgets.noMiningParticles.c()) {
         var0.cancel();
      }
   }
}
