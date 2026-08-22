package Expo.ASM.Hooks.Block;

import Expo.ExpoClient;
import Expo.module.impl.visual_utility.BlocksESP;
import net.minecraft.block.BlockBed.EnumPartType;
import net.minecraft.block.BlockBed;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;

public class BlockRendererDispatcherHooks {
   public static void onRenderBlock(IBlockState var0, BlockPos var1) {
      if (var0.getBlock() instanceof BlockBed && var0.getValue(BlockBed.PART) == EnumPartType.HEAD) {
         ExpoClient.G.add(new BlockPos(var1));
      }

      if (BlocksESP.L(var0.getBlock()) && BlocksESP.y(var1)) {
         BlocksESP.L.add(new BlockPos(var1));
      }
   }
}
