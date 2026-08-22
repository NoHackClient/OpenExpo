package Expo.ASM.Hooks.Block;

import Expo.module.ModuleManager;
import net.minecraft.util.EnumWorldBlockLayer;

public class BlockLeavesHooks {
   public static EnumWorldBlockLayer getBlockLayer(boolean var0) {
      if (ModuleManager.m != null && ModuleManager.m.o()) {
         return EnumWorldBlockLayer.TRANSLUCENT;
      } else {
         return var0 ? EnumWorldBlockLayer.CUTOUT_MIPPED : EnumWorldBlockLayer.SOLID;
      }
   }
}
