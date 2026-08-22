package Expo.ASM.Hooks.Block;

import Expo.module.ModuleManager;
import net.minecraft.util.EnumWorldBlockLayer;

public class BlockGrassHooks {
   public static EnumWorldBlockLayer getBlockLayer() {
      return ModuleManager.m != null && ModuleManager.m.o() ? EnumWorldBlockLayer.TRANSLUCENT : EnumWorldBlockLayer.CUTOUT_MIPPED;
   }
}
