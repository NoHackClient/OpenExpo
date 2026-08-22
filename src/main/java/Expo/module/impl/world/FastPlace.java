package Expo.module.impl.world;

import Expo.module.Category;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.FastPlaceBinder;
import Expo.event.events.PostRightClickEvent;
import Expo.event.events.PreUpdateEvent;
import Expo.internal.accessor.MinecraftAccessor;
import Expo.module.Module;
import Expo.setting.settings.NumberSetting;
import Expo.util.TimerUtil;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import java.util.concurrent.atomic.AtomicReference;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemEgg;
import net.minecraft.item.ItemSnowball;
import net.minecraft.util.BlockPos;











public class FastPlace extends Module implements EventSubscriber {
   private static Object[] d;
   private static String[] e;
   public static NumberSetting projectilesDelay;
   private final TimerUtil H;
   private static long c;
   public static NumberSetting blockDelay;
   private static String b;
   private BlockPos U;
   public static NumberSetting disableWhenBedInRange;
   private static long a;

   public FastPlace(long var1) {
      super(((a ^ (var1)) ^ 1985667824122L));
      // add code
      this.declare("FastPlace", Category.World, "Change the block placing delay when holding RMB");
      var1 = a ^ var1;
      this.H = new TimerUtil();
      this.U = null;
   }

   public void onPreUpdate(PreUpdateEvent var1, long var2) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      if (!(disableWhenBedInRange.L() <= 0.0F) && this.H.L(c, true)) {
         AtomicReference var8 = new AtomicReference(null);
         new Thread(
               () -> {
                  int var2x;
                  for (int var3 = var2x = (int)disableWhenBedInRange.L(); var2x >= -var3; var2x--) {
                     for (int var4x = -var3; var4x <= var3; var4x++) {
                        for (int var5 = -var3; var5 <= var3; var5++) {
                           BlockPos var6x = new BlockPos(
                              f.thePlayer.posX + var4x, f.thePlayer.posY + var2x, f.thePlayer.posZ + var5
                           );
                           IBlockState var7 = f.theWorld.getBlockState(var6x);
                           if (var7.getBlock() == Blocks.bed) {
                              var8.set(var6x);
                           }
                        }
                     }
                  }

                  this.U = (BlockPos)var8.get();
               }
            )
            .start();
      }

      try {
         if (f.inGameHasFocus) {
            if (f.thePlayer.getHeldItem() != null && f.thePlayer.getHeldItem().getItem() instanceof ItemBlock) {
               if (this.U != null) {
                  return;
               }

               int var13 = (int)blockDelay.L();
               if (var13 == 0) {
                  MinecraftAccessor.j(0L, f, 0);
               } else {
                  if (var13 == 4) {
                     return;
                  }

                  int var14 = MinecraftAccessor.C(f);
                  if (var14 == 4) {
                     MinecraftAccessor.j(0L, f, var13);
                  }
               }
            } else if (f.thePlayer.getHeldItem() != null
               && (f.thePlayer.getHeldItem().getItem() instanceof ItemSnowball || f.thePlayer.getHeldItem().getItem() instanceof ItemEgg)
               )
             {
               if (this.U != null) {
                  return;
               }

               int var12 = (int)projectilesDelay.L();
               if (var12 == 0) {
                  MinecraftAccessor.j(0L, f, 0);
               } else {
                  if (var12 == 4) {
                     return;
                  }

                  int var9 = MinecraftAccessor.C(f);
                  if (var9 == 4) {
                     MinecraftAccessor.j(0L, f, var12);
                  }
               }
            }
         }
      } catch (IndexOutOfBoundsException var10) {
         Expo.internal.restore.ExpoDiag.attribute(var10, "FastPlace.x/2#0");
      }
   }

   public String g(long var1) {
      return blockDelay.L() != projectilesDelay.L() ? (int)blockDelay.L() + b + (int)projectilesDelay.L() : String.valueOf((int)blockDelay.L());
   }

   static {
      a = 93753003014134L;
      // add code
      d = new Object[7];
      e = new String[7];
      b = ", ";
      c = 500L;
   }



   public final void x(long var1, EventBus var3) {
      FastPlaceBinder.e(var3, this);
   }

   private static void a() {
      d[0] = "<\u00141\u000erc\b";
      d[1] = "b+ v\u0001gU<$|LCB7~`";
      d[2] = "\u0004\"fG!s0";
      d[3] = long.class;
      e[3] = "java/lang/Long";
      d[4] = void.class;
      e[4] = "java/lang/Void";
      d[5] = "2W\u0000\u0015\u001d]9X\u0011Z|S2S\u0015\u0000";
      d[6] = "4K\u000b,\u000eI0YNCX&(\u0019V8\u0005Hl@\u000eC\f\\1MF%DY6R4yP\u001f6LV/VG7 \u000e<\b\u00161\\[-\bOW";
   }

   public void onPostRightClick(PostRightClickEvent var1, long var2) {

      try {
         if (f.thePlayer.getHeldItem() != null && f.thePlayer.getHeldItem().getItem() instanceof ItemBlock) {
            if (this.U != null) {
               return;
            }

            int var9 = (int)blockDelay.L();
            if (var9 == 0) {
               MinecraftAccessor.j(0L, f, 0);
            }
         } else if (f.thePlayer.getHeldItem() != null
            && (f.thePlayer.getHeldItem().getItem() instanceof ItemSnowball || f.thePlayer.getHeldItem().getItem() instanceof ItemEgg)) {
            if (this.U != null) {
               return;
            }

            int var6 = (int)projectilesDelay.L();
            if (var6 == 0) {
               MinecraftAccessor.j(0L, f, 0);
            }
         }
      } catch (IndexOutOfBoundsException var7) {
         Expo.internal.restore.ExpoDiag.attribute(var7, "FastPlace.K/2#0");
      }
   }

   static {
      // add code
      projectilesDelay = new NumberSetting("Projectiles-Delay", 2.0F, 0.0F, 4.0F, 1.0F);
      blockDelay = new NumberSetting("Block-Delay", 1.0F, 0.0F, 4.0F, 1.0F);
      disableWhenBedInRange = new NumberSetting("Disable-when-bed-in-range", -1.0F, -1.0F, 20.0F, 1.0F);
   }
}
