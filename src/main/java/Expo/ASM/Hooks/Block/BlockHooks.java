package Expo.ASM.Hooks.Block;

import Expo.ASM.Hooks.CallbackInfo;
import Expo.ASM.Hooks.CallbackInfoReturnable;
import Expo.ExpoClient;
import Expo.event.events.AddCollisionBoxesToListEvent;
import Expo.module.ModuleManager;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBarrier;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockHooks {
   private static long b;
   private static long a;

   public static void getMixedBrightnessForBlock(IBlockAccess var0, BlockPos var1, CallbackInfoReturnable<Integer> var2) {
      if (ModuleManager.m != null && ModuleManager.m.o()) {
         var2.setReturnValue((int)b);
      } else {
         Block var5 = var0.getBlockState(var1).getBlock();
         int var6 = var0.getCombinedLight(var1, var5.getLightValue());
         if (var6 == 0 && var5 instanceof BlockSlab) {
            var1 = var1.down();
            var5 = var0.getBlockState(var1).getBlock();
            var2.setReturnValue(var0.getCombinedLight(var1, var5.getLightValue()));
         } else {
            var2.setReturnValue(var6);
         }
      }

      var2.cancel();
   }

   public static void addCollisionBoxesToList(
      World var0, BlockPos var1, IBlockState var2, AxisAlignedBB var3, List<AxisAlignedBB> var4, Entity var5, BlockState var6, Block var7, CallbackInfo var8
   ) {
      if (ExpoClient.w != null) {
         if (var3 != null) {
            AxisAlignedBB var15 = var7.getCollisionBoundingBox(var0, var1, var2);
            if (var15 != null) {
               AddCollisionBoxesToListEvent var16 = new AddCollisionBoxesToListEvent(var6.getBlock(), var1);
               ExpoClient.w.e(var16, 18670087776179L);
               if (var16.a()) {
                  var8.cancel();
               } else {
                  if (var3.intersectsWith(var15)) {
                     var4.add(var15);
                  }

                  var8.cancel();
               }
            }
         }
      }
   }

   static {
      a = 121488631994650L;
      b = 3851402091784306888L;
   }

   public static void shouldSideBeRendered(Block var0, IBlockAccess var1, BlockPos var2, EnumFacing var3, CallbackInfoReturnable<Boolean> var4) {
      if (var0 instanceof BlockBarrier) {
         var4.setReturnValue(var1.getBlockState(var2).getBlock() != var0);
         var4.cancel();
      }
   }

   public static void getBlockLayer(Block var0, CallbackInfoReturnable<EnumWorldBlockLayer> var1) {
      if (ModuleManager.m != null) {
         if (!ModuleManager.m.o() && var0 != Blocks.barrier) {
            var1.setReturnValue(EnumWorldBlockLayer.SOLID);
         } else {
            var1.setReturnValue(EnumWorldBlockLayer.TRANSLUCENT);
         }

         var1.cancel();
      }
   }
}
