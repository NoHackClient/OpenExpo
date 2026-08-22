package Expo.ASM.Hooks.Render;

import Expo.ASM.Hooks.CallbackInfo;
import Expo.module.impl.configuration.Gadgets;











public class LoadingScreenRendererHooks {
   public static void forSkipProgress(int var0, CallbackInfo var1) {
      if (var0 < 0 || Gadgets.betterWorldSwapping.c()) {
         var1.cancel();
      }
   }
}
