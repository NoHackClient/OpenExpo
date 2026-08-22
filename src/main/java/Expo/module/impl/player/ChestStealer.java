package Expo.module.impl.player;

import Expo.module.Category;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.ChestStealerBinder;
import Expo.event.events.PreUpdateEvent;
import Expo.event.events.Render2DEvent;
import Expo.module.Module;
import Expo.module.impl.configuration.Font;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.HeaderSetting;
import Expo.setting.settings.NumberSetting;
import Expo.util.BlockUtil;
import Expo.util.ItemUtil;
import Expo.util.MathUtil;
import Expo.util.Pair;
import Expo.util.ScoreboardUtil;
import Expo.util.TimerUtil;
import Expo.util.render.CustomFont;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.init.Items;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemEgg;
import net.minecraft.item.ItemEnderPearl;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemSnowball;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;


public class ChestStealer extends Module implements EventSubscriber {
   private static long x;
   public static BooleanSetting projectiles;
   public static HeaderSetting itemsSettings;
   private static String[] h;
   public static BooleanSetting sword;
   public static NumberSetting startDelay;
   private static long[] o;
   public static BooleanSetting bow;
   public static BooleanSetting blocks;
   private static long d;
   private final TimerUtil p;
   public static NumberSetting minDelay;
   private static Integer[] u;
   public static BooleanSetting ignoreTrash;
   public static BooleanSetting chestIntegrityCheck;
   public static BooleanSetting silent;
   public static BooleanSetting food;
   public static boolean y;
   public static BooleanSetting armor;
   private static String[] g;
   public static BooleanSetting potions;
   private List<Integer> D;
   public static BooleanSetting tools;
   public static NumberSetting maxDelay;
   public static BooleanSetting autoClose;

   public ChestStealer(long var1) {
      super(((d ^ (var1)) ^ 70559156861327L));
      // add code
      this.declare("ChestStealer", Category.Player, "Steal items in the chest automatically");
      var1 = d ^ var1;
      this.p = new TimerUtil();
   }

   static {
      d = 28535189231375L;
      y = false;
   }

   private List z(long var1) {
      ArrayList var5 = new ArrayList();
      ContainerChest var6 = (ContainerChest)f.thePlayer.openContainer;
      IInventory var7 = var6.getLowerChestInventory();
      ArrayList var8 = new ArrayList();

      for (int var9 = 0; var9 < var7.getSizeInventory(); var9++) {
         ItemStack var10 = var7.getStackInSlot(var9);
         if (var10 != null && var10.getItem() != null && !var5.contains(var9)) {
            Item var11 = var10.getItem();
            boolean var12 = this.y(var10, var9, var7);
            if (!ignoreTrash.c() || !var12) {
               if (armor.c() && var11 instanceof ItemArmor) {
                  var5.add(var9);
               } else if (blocks.c() && ItemUtil.u(var10)) {
                  var5.add(var9);
               } else if (!bow.c() || !(var11 instanceof ItemBow) && var11 != Items.arrow) {
                  if (food.c() && var11 instanceof ItemFood) {
                     var5.add(var9);
                  } else if (potions.c() && var11 instanceof ItemPotion) {
                     var5.add(var9);
                  } else if (!projectiles.c()
                     || !(var11 instanceof ItemEnderPearl)
                        && !(var11 instanceof ItemEgg)
                        && !(var11 instanceof ItemSnowball)
                        && !(var11 instanceof ItemFishingRod)) {
                     if (sword.c() && var11 instanceof ItemSword) {
                        var5.add(var9);
                     } else if (!tools.c() || !(var11 instanceof ItemPickaxe) && !(var11 instanceof ItemAxe) && !(var11 instanceof ItemSpade)) {
                        if (!ignoreTrash.c()) {
                           var5.add(var9);
                        }
                     } else {
                        var5.add(var9);
                     }
                  } else {
                     var5.add(var9);
                  }
               } else {
                  var5.add(var9);
               }

               var8.add(var9);
            }
         }
      }

      return var8.isEmpty() ? var5 : var5;
   }


   public void onRender2D(long var1, Render2DEvent var3) {


      if (silent.c() && BlockUtil.o(chestIntegrityCheck.c()) && (f.currentScreen instanceof GuiChest || y)) {
         CustomFont var10 = Font.s(0L);
         ScaledResolution var11 = var3.C;
         var10.T(
            37697014677608L,
            "Stealing...",
            var11.getScaledWidth() / 2.0F - var10.R("Stealing...", 52019766876817L) / 2.0F,
            var11.getScaledHeight() / 2 + 75,
            16777215
         );
      }
   }

   public void A(long var1) {
      y = false;
   }



   public final void x(long var1, EventBus var3) {
      ChestStealerBinder.W(var3, this);
   }


   private boolean y(ItemStack var1, int var2, IInventory var5) {




      boolean var18 = false;
      Item var19 = var1.getItem();
      if (var19 instanceof ItemArmor) {
         if (((ItemArmor)var19).armorType == 0) {
            if (((Pair)ItemUtil.O(0L, var5).get(0)).p()
                  != null
               && (Integer)((Pair)ItemUtil.O(0L, var5).get(0)).p()
                  != var2) {
               var18 = true;
            }
         } else if (((ItemArmor)var19).armorType == 1) {
            if (((Pair)ItemUtil.O(0L, var5).get(1)).p()
                  != null
               && (Integer)((Pair)ItemUtil.O(0L, var5).get(1)).p()
                  != var2) {
               var18 = true;
            }
         } else if (((ItemArmor)var19).armorType == 2) {
            if (((Pair)ItemUtil.O(0L, var5).get(2)).p()
                  != null
               && (Integer)((Pair)ItemUtil.O(0L, var5).get(2)).p()
                  != var2) {
               var18 = true;
            }
         } else if (((ItemArmor)var19).armorType == 3
            && ((Pair)ItemUtil.O(0L, var5).get(3)).p()
               != null
            && (Integer)((Pair)ItemUtil.O(0L, var5).get(3)).p()
               != var2) {
            var18 = true;
         }
      } else if (var19 instanceof ItemSword) {
         if (((Pair)ItemUtil.q(45121668772412L, var5)).p()
               != null
            && (Integer)((Pair)ItemUtil.q(45121668772412L, var5)).p()
               != var2) {
            var18 = true;
         }
      } else if (var19 instanceof ItemFood) {
         if (((ItemStack)((Pair)ItemUtil.k((IInventory)var5)).a())
                  .getItem()
               == Items.golden_apple
            && var19 != Items.golden_apple) {
            var18 = true;
         }
      } else if (var19 instanceof ItemBow) {
         if (((Pair)ItemUtil.O(var5)).p()
               != null
            && (Integer)((Pair)ItemUtil.O(var5)).p()
               != var2) {
            var18 = true;
         }
      } else if (var19 instanceof ItemTool) {
         if (var19 instanceof ItemPickaxe) {
            if ((Integer)((Pair)ItemUtil.D(var5, (short)0).get(0)).p()
               != var2) {
               var18 = true;
            }
         } else if (var19 instanceof ItemAxe) {
            if ((Integer)((Pair)ItemUtil.D(var5, (short)0).get(1)).p()
               != var2) {
               var18 = true;
            }
         } else if (var19 instanceof ItemSpade
            && (Integer)((Pair)ItemUtil.D(var5, (short)0).get(2)).p()
               != var2) {
            var18 = true;
         }
      } else if (var19 instanceof ItemFishingRod && ItemUtil.X(Items.fishing_rod, var5)) {
         var18 = true;
      }

      for (int var20 = 0; var20 < 40; var20++) {
         ItemStack var21 = f.thePlayer.inventory.getStackInSlot(var20);
         if (var21 != null) {
            if (var19 instanceof ItemArmor && var21.getItem() instanceof ItemArmor) {
               if (ItemUtil.M(var1) <= ItemUtil.M(var21) && ((ItemArmor)var21.getItem()).armorType == ((ItemArmor)var19).armorType) {
                  var18 = true;
               }
            } else if (var19 instanceof ItemSword && var21.getItem() instanceof ItemSword) {
               if (ItemUtil.p((short)0, var1, (char)6735) <= ItemUtil.p((short)0, var21, (char)6735)) {
                  var18 = true;
               }
            } else if (var19 instanceof ItemFood && var21.getItem() instanceof ItemFood) {
               if (((Pair)ItemUtil.k((IInventory)f.thePlayer.inventory)).p()
                     != null
                  && ((ItemStack)((Pair)ItemUtil.k((IInventory)f.thePlayer.inventory)).a())
                        .getItem()
                     == Items.golden_apple
                  && var19 != Items.golden_apple) {
                  var18 = true;
               }
            } else if (var19 instanceof ItemBow && var21.getItem() instanceof ItemBow) {
               if (ItemUtil.b(var1) <= ItemUtil.b(var21)) {
                  var18 = true;
               }
            } else if (var19 instanceof ItemTool && var21.getItem() instanceof ItemTool) {
               if (var19 instanceof ItemPickaxe && var21.getItem() instanceof ItemPickaxe) {
                  if (((Pair)ItemUtil.D(f.thePlayer.inventory, (short)0).get(0)).p()
                        != null
                     && ItemUtil.Y(0L, var1) <= ItemUtil.Y(0L, var21)) {
                     var18 = true;
                  }
               } else if (var19 instanceof ItemAxe && var21.getItem() instanceof ItemAxe) {
                  if (((Pair)ItemUtil.D(f.thePlayer.inventory, (short)0).get(1)).p()
                        != null
                     && ItemUtil.Y(0L, var1) <= ItemUtil.Y(0L, var21)) {
                     var18 = true;
                  }
               } else if (var19 instanceof ItemSpade
                  && var21.getItem() instanceof ItemSpade
                  && ((Pair)ItemUtil.D(f.thePlayer.inventory, (short)0).get(2)).p()
                     != null
                  && ItemUtil.Y(0L, var1) <= ItemUtil.Y(0L, var21)) {
                  var18 = true;
               }
            }
         }
      }

      return var18;
   }


   public void onPreUpdate(long var1, PreUpdateEvent var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {

      if (f.thePlayer.openContainer instanceof ContainerChest) {
         if (!y) {
            y = true;
            if (this.p.L(x, true)) {
               if (chestIntegrityCheck.c()) {
                  if (ScoreboardUtil.x(0L)) {
                     y = false;
                     return;
                  }

                  if (!BlockUtil.o(true)) {
                     y = false;
                     return;
                  }
               }

               this.D = this.z(0L);
               if (this.D.isEmpty() || ItemUtil.H(51268148435703L)) {
                  if (autoClose.c()) {
                     f.thePlayer.closeScreen();
                  }

                  y = false;
                  return;
               }

               new Thread(() -> {

                  try {
                     Thread.sleep((long)startDelay.L());
                  } catch (InterruptedException var9) {
                     Expo.internal.restore.ExpoDiag.attribute(var9, "ChestStealer.b/2#0");
                     y = false;
                  }

                  for (Integer var6x : this.D) {
                     try {
                        Thread.sleep((long)MathUtil.h(minDelay.L(), maxDelay.L()));
                     } catch (InterruptedException var8x) {
                        Expo.internal.restore.ExpoDiag.attribute(var8x, "ChestStealer.b/2#1");
                        y = false;
                     }

                     if (this.V(0L)) {
                        return;
                     }

                     f.playerController.windowClick(f.thePlayer.openContainer.windowId, var6x, 0, 1, f.thePlayer);
                  }

                  y = false;
               }).start();
            } else {
               y = false;
            }
         }
      } else {
         y = false;
      }
   }


   private boolean V(long var1) {
      if (this.o() && f.thePlayer.openContainer instanceof ContainerChest) {
         return false;
      }

      y = false;
      return true;
   }

   public String g(long var1) {
      return minDelay.L() == maxDelay.L() ? String.valueOf((int)minDelay.L()) : (int)minDelay.L() + "-" + (int)maxDelay.L();
   }









   static {
      // add code
      autoClose = new BooleanSetting("Auto-close", true);
      ignoreTrash = new BooleanSetting("Ignore-trash", true);
      armor = new BooleanSetting("Armor", true);
      blocks = new BooleanSetting("Blocks", true);
      bow = new BooleanSetting("Bow", false);
      food = new BooleanSetting("Food", true);
      potions = new BooleanSetting("Potions", false);
      projectiles = new BooleanSetting("Projectiles", true);
      sword = new BooleanSetting("Sword", true);
      tools = new BooleanSetting("Tools", false);
   }
   static {
      // add code
      startDelay = new NumberSetting("Start-delay", 50.0F, 0.0F, 1000.0F, 1.0F);
      minDelay = new NumberSetting("Min-delay", 50.0F, 0.0F, 1000.0F, 1.0F);
      maxDelay = new NumberSetting("Max-delay", 50.0F, 0.0F, 1000.0F, 1.0F);
   }
   static {
      // add code
      itemsSettings = new HeaderSetting("Items settings");
   }
}
