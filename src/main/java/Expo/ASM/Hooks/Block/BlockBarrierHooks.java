package Expo.ASM.Hooks.Block;

import Expo.ASM.Hooks.CallbackInfoReturnable;
import Expo.module.ModuleManager;











public class BlockBarrierHooks {
   public static void getRenderType(CallbackInfoReturnable<Integer> var0) {
      if (ModuleManager.W != null && ModuleManager.W.o()) {
         var0.setReturnValue(3);
         var0.cancel();
      }
   }
}
