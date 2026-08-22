package Expo.ASM.Hooks.World;

import Expo.ASM.Hooks.CallbackInfo;
import Expo.module.ModuleManager;

public class WorldClientHooks {
   public static void onDoVoidFogParticles(CallbackInfo var0) {
      if (ModuleManager.W != null && ModuleManager.W.o()) {
         var0.cancel();
      }
   }
}
