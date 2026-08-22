package Expo.util;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.AutoToolServiceBinder;
import Expo.event.events.PreTickEvent;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.MovingObjectPosition;











public class AutoToolService implements EventSubscriber {
   private boolean U;
   public static AutoToolService K;
   private static Integer[] c;
   private static long a;
   private static Minecraft f;

   private AutoToolService() {
      this.U = false;
   }

   public boolean f() {
      this.U = !this.U;
      return this.U;
   }

   public boolean K() {
      return this.U;
   }

   private static void a() {
   }

   public final void x(long var1, EventBus var3) {
      AutoToolServiceBinder.z(var3, this);
   }

   public void I(long var1) {
      this.U = true;
   }



   static {
      a = 62220428746021L;
      K = new AutoToolService();
      f = MinecraftRef.c((byte)0, 0L);
   }

   public void p(int var1, char var2, char var3) {
      this.U = false;
   }

   public int Q(long var1, EntityPlayerSP var3, Block var4) {
      float var5 = 1.0F;
      int var6 = -1;

      for (int var7 = 0; var7 < 9; var7++) {
         ItemStack var8 = var3.inventory.getStackInSlot(var7);
         if (var8 != null) {
            float var9 = var8.getStrVsBlock(var4);
            if (var8.getItem() instanceof ItemTool) {
               if (var9 > var5) {
                  var5 = var9;
                  var6 = var7;
               }
            } else if (var9 > var5) {
               var6 = var7;
            }
         }
      }

      return var6;
   }

   public void onPreTick(long var1, PreTickEvent var3) {
      if (this.U) {
         MovingObjectPosition var6 = f.objectMouseOver;
         if (var6 != null && var6.typeOfHit == MovingObjectType.BLOCK) {
            BlockPos var7 = var6.getBlockPos();
            Block var8 = f.theWorld.getBlockState(var7).getBlock();
            EntityPlayerSP var9 = f.thePlayer;
            int var10 = this.Q(0L, var9, var8);
            if (var10 != -1 && var10 != var9.inventory.currentItem) {
               var9.inventory.currentItem = var10;
            }
         }
      }
   }


}
