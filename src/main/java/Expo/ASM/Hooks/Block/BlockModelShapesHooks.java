package Expo.ASM.Hooks.Block;

import Expo.module.ModuleManager;
import Expo.util.MinecraftRef;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.init.Blocks;

public class BlockModelShapesHooks {
   private static final Minecraft g;
   private static IBakedModel p;

   static {
      long var0 = 72764143890033L;
      int var2 = (int)((var0 ^ 12490221135805L) >>> 56);
      long var3 = (var0 ^ 12490221135805L) << 8 >>> 8;
      g = MinecraftRef.c((byte)var2,0L);
   }

   public static IBakedModel getModelForState(IBlockState var0, IBakedModel var1) {
      if (var0.getBlock() == Blocks.barrier && ModuleManager.W != null && ModuleManager.W.o()) {
         if (p == null) {
            p = g.getBlockRendererDispatcher().getBlockModelShapes().getModelForState(Blocks.stained_glass.getDefaultState());
         }

         return p;
      } else {
         return var1;
      }
   }
}
