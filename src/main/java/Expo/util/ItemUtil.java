package Expo.util;

import Expo.internal.accessor.BlockAccessor;
import Expo.internal.accessor.GuiScreenAccessor;
import Expo.internal.accessor.MethodAccessors;
import Expo.internal.accessor.PlayerControllerAccessor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemEgg;
import net.minecraft.item.ItemEnderPearl;
import net.minecraft.item.ItemFireball;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemSnowball;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import org.lwjgl.input.Mouse;











public class ItemUtil {
   private static long a;
   private static String[] c;
   private static String[] i;
   private static String[] b;
   private static Minecraft z;
   private static long[] e;
   private static Integer[] f;
   private static Object[] h;
   private static Map d;

   // add code
   public static boolean d() {
      return z.thePlayer != null
             && z.thePlayer.getHeldItem() != null
             && z.thePlayer.getHeldItem().getItem() instanceof ItemSword;
   }

   public static boolean X(Item var0, IInventory var1) {
      for (int var2 = 0; var2 < var1.getSizeInventory(); var2++) {
         ItemStack var3 = var1.getStackInSlot(var2);
         if (var3 != null && var0 != null && var0 == var3.getItem()) {
            return true;
         }
      }

      return false;
   }

   public static int K(int var0) {
      if (var0 >= 36) {
         return 8 - (var0 - 36);
      } else {
         return var0 < 9 ? var0 + 36 : var0;
      }
   }

   public static float Y(long var0, ItemStack var2) {
      if (var2 == null) {
         return 0.0F;
      }

      if ((double)var2.getItemDamage() / var2.getMaxDamage() >= 0.7) {
         return 0.0F;
      }

      Item var3 = var2.getItem();
      int var4 = EnchantmentHelper.getEnchantmentLevel(Enchantment.efficiency.effectId, var2);
      switch (var4) {
         case 1:
            break;
         case 2:
            break;
         case 3:
            break;
         case 4:
            break;
         case 5:
            break;
         default:
            var4 = 0;
      }

      if (var3 instanceof ItemPickaxe) {
         ItemPickaxe var14 = (ItemPickaxe)var3;
         return var14.getToolMaterial().getEfficiencyOnProperMaterial() + var4;
      } else if (var3 instanceof ItemSpade) {
         ItemSpade var13 = (ItemSpade)var3;
         return var13.getToolMaterial().getEfficiencyOnProperMaterial() + var4;
      } else if (var3 instanceof ItemAxe) {
         ItemAxe var12 = (ItemAxe)var3;
         return var12.getToolMaterial().getEfficiencyOnProperMaterial() + var4;
      } else {
         return 0.0F;
      }
   }

   public static int M(long var0) {

      for (int var2 = 0; var2 < 9; var2++) {
         ItemStack var3 = z.thePlayer.inventory.mainInventory[var2];
         if (var3 != null && var3.getItem() instanceof ItemPotion) {
            var3.getItem();
            if (ItemPotion.isSplash(MethodAccessors.f(var3.getItem(), var3))) {
               return var2;
            }
         }
      }

      return -1;
   }

   public static void c(int var0, int var1, char var2, int var3) {
      z.playerController.windowClick(z.thePlayer.inventoryContainer.windowId, K(var3), 1, 4, z.thePlayer);
   }

   public static int M(ItemStack var0) {
      if (var0 == null) {
         return 0;
      }

      boolean var1 = (double)var0.getItemDamage() / var0.getMaxDamage() >= 0.7;
      int var2 = 0;
      Item var3 = var0.getItem();
      if (var3 == Items.diamond_helmet || var3 == Items.diamond_chestplate || var3 == Items.diamond_leggings || var3 == Items.diamond_boots) {
         var2 += 15;
      } else if (var3 == Items.iron_helmet || var3 == Items.iron_chestplate || var3 == Items.iron_leggings || var3 == Items.iron_boots) {
         var2 += 10;
      } else if (var3 == Items.golden_helmet || var3 == Items.golden_chestplate || var3 == Items.golden_leggings || var3 == Items.golden_boots) {
         var2 += 5;
      } else if (var3 == Items.chainmail_helmet || var3 == Items.chainmail_chestplate || var3 == Items.chainmail_leggings || var3 == Items.chainmail_boots) {
         var2 += 5;
      }

      var2 += X(var0);
      if (var1) {
         var2 = (int)(var2 * 0.5F);
      }

      return var2;
   }

   public static boolean y(ItemStack var0) {
      List var1 = ((ItemPotion)var0.getItem()).getEffects(var0);
      if (var1 == null) {
         return false;
      }

      int var2 = 0;

      for (int var3 = var1.size(); var2 < var3; var2++) {
         PotionEffect var4 = (PotionEffect)var1.get(var2);
         if (var4.getPotionID() == Potion.heal.id) {
            return true;
         }
      }

      return false;
   }

   public static int b(long var0) {
      long var2 = 42909758276587L;

      for (int var4 = 0; var4 < 9; var4++) {
         ItemStack var5 = z.thePlayer.inventory.mainInventory[var4];
         if (k(var2, var5)) {
            return var4;
         }
      }

      return -1;
   }

   public static double p(short var0, ItemStack var1, char var2) {
      if (var1 == null) {
         return 0.0;
      }

      boolean var6 = (double)var1.getItemDamage() / var1.getMaxDamage() >= 0.7;
      double var7 = 0.0;

      for (Entry var10 : var1.getAttributeModifiers().entries()) {
         if (((String)var10.getKey()).equals("generic.attackDamage")) {
            var7 = ((AttributeModifier)var10.getValue()).getAmount();
            break;
         }
      }

      var7 += EnchantmentHelper.getEnchantmentLevel(Enchantment.fireAspect.effectId, var1) * 1.25
         + EnchantmentHelper.getEnchantmentLevel(Enchantment.sharpness.effectId, var1) * 1.25;
      if (var6) {
         var7 *= 0.5;
      }

      return var7;
   }

   public static Pair<ItemStack, Integer> H(IInventory var0) {
      Pair var1 = new Pair(null, null);

      for (int var2 = 0; var2 < var0.getSizeInventory(); var2++) {
         ItemStack var3 = var0.getStackInSlot(var2);
         if (var3 != null && var3.getItem() instanceof ItemPotion && var1.a() == null) {
            var1 = new Pair<>(var3, var2);
         }
      }

      return var1;
   }

   public static boolean f(ItemStack var0) {
      if (var0 == null) {
         return false;
      } else if (var0.getItem() instanceof ItemBlock) {
         Block var1 = ((ItemBlock)var0.getItem()).getBlock();
         return var1 == Blocks.ladder;
      } else {
         return false;
      }
   }

   public static List O(long var0, IInventory var2) {
      ArrayList var3 = new ArrayList();
      var3.add(new Pair<>(null, 39));
      var3.add(new Pair<>(null, 38));
      var3.add(new Pair<>(null, 37));
      var3.add(new Pair<>(null, 36));
      ItemStack var4 = null;
      ItemStack var5 = null;
      ItemStack var6 = null;
      ItemStack var7 = null;

      for (int var8 = 0; var8 < var2.getSizeInventory(); var8++) {
         ItemStack var9 = var2.getStackInSlot(var8);
         if (var9 != null && var9.getItem() instanceof ItemArmor) {
            if (((ItemArmor)var9.getItem()).armorType == 0) {
               if (M(var9) > M(var4)) {
                  var4 = var9;
                  var3.set(0, new Pair<>(var4, var8));
               }
            } else if (((ItemArmor)var9.getItem()).armorType == 1) {
               if (M(var9) > M(var5)) {
                  var5 = var9;
                  var3.set(1, new Pair<>(var5, var8));
               }
            } else if (((ItemArmor)var9.getItem()).armorType == 2) {
               if (M(var9) > M(var6)) {
                  var6 = var9;
                  var3.set(2, new Pair<>(var6, var8));
               }
            } else if (((ItemArmor)var9.getItem()).armorType == 3 && M(var9) > M(var7)) {
               var7 = var9;
               var3.set(3, new Pair<>(var7, var8));
            }
         }
      }

      return var3;
   }

   private static void a() {
      h[0] = ";Fjr\u0019@\fQnxTd\u001bZ4it";
      h[1] = ">Z\u0017>\nA>Z\u0000b\u0006N$\u0011\u0000|\u000eM>KM}\u0012D$V\u0013|\u0006Q5MM@\u000bI)Z\u0011S\bF$M\f|\u000bM\"r3";
      h[2] = void.class;
      i[2] = "java/lang/Void";
      h[3] = "v`:L\u0005ej";
      h[4] = long.class;
      i[4] = "java/lang/Long";
      h[5] = int.class;
      i[5] = "java/lang/Integer";
      h[6] = "Z\u0007=RetQ\b,\u001d\u0004zZ\u0003(G";
      h[7] = "q\u0015d\u0000\u001c\u0012o\nex(b.\u00036\u0018\u000f\u0003e\u0011*\u001aq[j\u0012e\u0006\u0018S'\u0004&x";
      h[8] = "Y<1B'\u000e\u0007=5X\u001f8c6tO \u000f\u0000b?Qn`X|~\u0001$\u0004\r`!X\u001fY\u001euq@vQSc2>";
   }

   public static int j(int var0, int var1, char var2, boolean var3) {

      for (int var8 = 0; var8 < 9; var8++) {
         ItemStack var9 = z.thePlayer.inventory.mainInventory[var8];
         if (var9 != null
            && (var3 ? var9.getItem() instanceof ItemFishingRod : var9.getItem() instanceof ItemSnowball || var9.getItem() instanceof ItemEgg)) {
            P( var8);
            return var8;
         }
      }

      return -1;
   }

   public static Pair<ItemStack, Integer> o(IInventory var0) {
      Pair var1 = new Pair(null, null);

      for (int var2 = 0; var2 < var0.getSizeInventory(); var2++) {
         ItemStack var3 = var0.getStackInSlot(var2);
         if (var3 != null
            && (var3.getItem() instanceof ItemSnowball || var3.getItem() instanceof ItemEgg || var3.getItem() instanceof ItemFishingRod)) {
            if (var1.a() == null) {
               var1 = new Pair<>(var3, var2);
            } else if (!(((ItemStack)var1.a()).getItem() instanceof ItemFishingRod)
               || !(var3.getItem() instanceof ItemSnowball) && !(var3.getItem() instanceof ItemEgg)) {
               if ((((ItemStack)var1.a()).getItem() instanceof ItemEgg || ((ItemStack)var1.a()).getItem() instanceof ItemEgg)
                  && (var3.getItem() instanceof ItemSnowball || var3.getItem() instanceof ItemEgg)
                  && var3.stackSize > ((ItemStack)var1.a()).stackSize) {
                  var1 = new Pair<>(var3, var2);
               }
            } else {
               var1 = new Pair<>(var3, var2);
            }
         }
      }

      return var1;
   }

   static {
      a = 42568341579408L;
      z = MinecraftRef.c((byte)0, 0L);
   }

   public static boolean u(ItemStack var0) {
      if (var0 != null && var0.stackSize >= 1) {
         Item var1 = var0.getItem();
         return var1 instanceof ItemBlock ? A((ItemBlock)var1) : false;
      } else {
         return false;
      }
   }

   public static Pair<ItemStack, Integer> F(IInventory var0) {
      Pair var1 = new Pair(null, null);

      for (int var2 = 0; var2 < var0.getSizeInventory(); var2++) {
         ItemStack var3 = var0.getStackInSlot(var2);
         if (var3 != null && var3.getItem() instanceof ItemEnderPearl && (var1.a() == null || var3.stackSize > ((ItemStack)var1.a()).stackSize)) {
            var1 = new Pair<>(var3, var2);
         }
      }

      return var1;
   }

   public static Pair q(long var0, IInventory var2) {
      var0 = a ^ var0;
      int var3 = (int)((var0 ^ 24128392081656L) >>> 48);
      int var4 = (int)((var0 ^ 24128392081656L) << 16 >>> 48);
      Pair var6 = new Pair(null, null);

      for (int var7 = 0; var7 < var2.getSizeInventory(); var7++) {
         ItemStack var8 = var2.getStackInSlot(var7);
         if (var8 != null
            && var8.getItem() instanceof ItemSword
            && p((short)var3, var8, (char)var4) > p((short)var3, (ItemStack)var6.a(), (char)var4)) {
            var6 = new Pair<>(var8, var7);
         }
      }

      return var6;
   }

   public static boolean k(long var0, ItemStack var2) {
      var0 = a ^ var0;
      int var3 = (int)((var0 ^ 23820402072392L) >>> 32);
      int var4 = (int)((var0 ^ 23820402072392L) << 32 >>> 48);
      return var2 != null
         && var2.getItem() instanceof ItemBucket
         && BlockAccessor.o(var3, (ItemBucket)var2.getItem(), (short)var4) == Blocks.flowing_lava;
   }

   public static boolean A(ItemBlock var0) {
      Block var1 = var0.getBlock();
      return BlockUtil.p(var1) ? false : BlockUtil.i(var1);
   }

   public static void P( int var2) {
      if (var2 >= 0 && var2 <= 8) {
         if (z.thePlayer.inventory.currentItem != var2) {
            z.thePlayer.inventory.currentItem = var2;
            PlayerControllerAccessor.Q(z.playerController);
         }
      }
   }

   public static void Q(long var0, int var2, int var3) {
      z.playerController.windowClick(z.thePlayer.inventoryContainer.windowId, K(var2), K(var3), 2, z.thePlayer);
   }

   public static Pair<ItemStack, Integer> i(IInventory var0) {
      Pair var1 = new Pair(null, null);

      for (int var2 = 0; var2 < var0.getSizeInventory(); var2++) {
         ItemStack var3 = var0.getStackInSlot(var2);
         if (var3 != null && var3.getItem() instanceof ItemFireball && (var1.a() == null || var3.stackSize > ((ItemStack)var1.a()).stackSize)) {
            var1 = new Pair<>(var3, var2);
         }
      }

      return var1;
   }

   public static int l(long var0) {
      long var2 = 2324698497256L;

      for (int var4 = 0; var4 < 9; var4++) {
         ItemStack var5 = z.thePlayer.inventory.mainInventory[var4];
         if (c(var5, var2)) {
            return var4;
         }
      }

      return -1;
   }

   public static boolean H(long var0) {

      for (int var2 = 9; var2 < 45; var2++) {
         if (z.thePlayer.inventoryContainer.getSlot(var2).getStack() == null) {
            return false;
         }
      }

      return true;
   }

   public static int N(long var0) {

      for (int var2 = 0; var2 < 9; var2++) {
         ItemStack var3 = z.thePlayer.inventory.mainInventory[var2];
         if (var3 != null && var3.getItem() instanceof ItemEnderPearl) {
            return var2;
         }
      }

      return -1;
   }



   public static List D(IInventory var0, short var1) {
      ArrayList var8 = new ArrayList();
      var8.add(new Pair(null, null));
      var8.add(new Pair(null, null));
      var8.add(new Pair(null, null));
      ItemStack var9 = null;
      ItemStack var10 = null;
      ItemStack var11 = null;

      for (int var12 = 0; var12 < var0.getSizeInventory(); var12++) {
         ItemStack var13 = var0.getStackInSlot(var12);
         if (var13 != null && var13.getItem() instanceof ItemTool) {
            if (var13.getItem() instanceof ItemPickaxe && Y(0L, var9) < Y(0L, var13)) {
               var9 = var13;
               var8.set(0, new Pair<>(var9, var12));
            } else if (var13.getItem() instanceof ItemAxe && Y(0L, var10) < Y(0L, var13)) {
               var10 = var13;
               var8.set(1, new Pair<>(var10, var12));
            } else if (var13.getItem() instanceof ItemSpade && Y(0L, var11) < Y(0L, var13)) {
               var11 = var13;
               var8.set(2, new Pair<>(var11, var12));
            }
         }
      }

      return var8;
   }

   public static Pair<ItemStack, Integer> k(IInventory var0) {
      Pair var1 = new Pair(null, null);

      for (int var2 = 0; var2 < var0.getSizeInventory(); var2++) {
         ItemStack var3 = var0.getStackInSlot(var2);
         if (var3 != null && var3.getItem() instanceof ItemFood) {
            if (var1.a() == null) {
               var1 = new Pair<>(var3, var2);
            } else if (((ItemStack)var1.a()).getItem() != Items.golden_apple && var3.getItem() == Items.golden_apple) {
               var1 = new Pair<>(var3, var2);
            } else if (((ItemStack)var1.a()).getItem() == Items.golden_apple
               && var3.getItem() == Items.golden_apple
               && !((ItemStack)var1.a()).isItemEnchanted()
               && var3.isItemEnchanted()) {
               var1 = new Pair<>(var3, var2);
            } else if (((ItemStack)var1.a()).getItem() == Items.golden_apple
               && var3.getItem() == Items.golden_apple
               && ((ItemStack)var1.a()).isItemEnchanted()
               && var3.isItemEnchanted()
               && var3.stackSize > ((ItemStack)var1.a()).stackSize) {
               var1 = new Pair<>(var3, var2);
            } else if (((ItemStack)var1.a()).getItem() == Items.golden_apple
               && var3.getItem() == Items.golden_apple
               && var3.stackSize > ((ItemStack)var1.a()).stackSize
               && !((ItemStack)var1.a()).isItemEnchanted()
               && !var3.isItemEnchanted()) {
               var1 = new Pair<>(var3, var2);
            } else if (((ItemStack)var1.a()).getItem() != Items.golden_apple
               && var3.getItem() != Items.golden_apple
               && var3.stackSize > ((ItemStack)var1.a()).stackSize) {
               var1 = new Pair<>(var3, var2);
            }
         }
      }

      return var1;
   }

   public static Pair<ItemStack, Integer> Y(IInventory var0) {
      Pair var1 = new Pair(null, null);

      for (int var2 = 0; var2 < var0.getSizeInventory(); var2++) {
         ItemStack var3 = var0.getStackInSlot(var2);
         if (u(var3)) {
            if (var1.a() == null) {
               var1 = new Pair<>(var3, var2);
            } else if (var3.stackSize > ((ItemStack)var1.a()).stackSize) {
               var1 = new Pair<>(var3, var2);
            }
         }
      }

      return var1;
   }

   public static void e(GuiScreen var0) {
      int var1 = Mouse.getX() * var0.width / z.displayWidth;
      int var2 = var0.height - Mouse.getY() * var0.height / z.displayHeight - 1;
      GuiScreenAccessor.c(var0, var1, var2, 0);
   }

   public static float l(ItemStack var0, Block var1) {
      if (var0 == null) {
         return 0.0F;
      }

      float var2 = var0.getStrVsBlock(var1);
      if (var2 > 1.0F) {
         int var3 = EnchantmentHelper.getEnchantmentLevel(Enchantment.efficiency.effectId, var0);
         if (var3 > 0) {
            var2 += var3 * var3 + 1;
         }
      }

      return var2;
   }

   public static boolean c(ItemStack var0, long var1) {
      var1 = a ^ var1;
      int var3 = (int)((var1 ^ 53540670724171L) >>> 32);
      int var4 = (int)((var1 ^ 53540670724171L) << 32 >>> 48);
      return var0 != null
         && var0.getItem() instanceof ItemBucket
         && BlockAccessor.o(var3, (ItemBucket)var0.getItem(), (short)var4) == Blocks.flowing_water;
   }

   public static void B(long var0, int var2) {
      z.playerController.windowClick(z.thePlayer.inventoryContainer.windowId, K(var2), 0, 1, z.thePlayer);
   }

   public static int e(long var0, Block var2) {
      float var3 = 1.0F;
      int var4 = -1;

      for (int var5 = 0; var5 < 9; var5++) {
         ItemStack var6 = z.thePlayer.inventory.getStackInSlot(var5);
         if (var6 != null) {
            float var7 = l(var6, var2);
            if (var7 > var3) {
               var4 = var5;
            }
         }
      }

      return var4;
   }



   public static Pair<ItemStack, Integer> W(IInventory var0) {
      Pair var1 = new Pair(null, null);

      for (int var2 = 0; var2 < var0.getSizeInventory(); var2++) {
         ItemStack var3 = var0.getStackInSlot(var2);
         if (var3 != null && var3.getItem() instanceof ItemShears && (var1.a() == null || var3.stackSize > ((ItemStack)var1.a()).stackSize)) {
            var1 = new Pair<>(var3, var2);
         }
      }

      return var1;
   }

   public static int w(int var0, char var1, short var2) {

      for (int var5 = 0; var5 < 9; var5++) {
         ItemStack var6 = z.thePlayer.inventory.mainInventory[var5];
         if (var6 != null && var6.getDisplayName().toLowerCase().contains("golden head")) {
            return var5;
         }
      }

      return -1;
   }

   public static Pair<ItemStack, Integer> O(IInventory var0) {
      Pair var1 = new Pair(null, null);

      for (int var2 = 0; var2 < var0.getSizeInventory(); var2++) {
         ItemStack var3 = var0.getStackInSlot(var2);
         if (var3 != null && var3.getItem() instanceof ItemBow && b(var3) > b((ItemStack)var1.a())) {
            var1 = new Pair<>(var3, var2);
         }
      }

      return var1;
   }

   public static float b(ItemStack var0) {
      if (var0 == null) {
         return 0.0F;
      }

      if ((double)var0.getItemDamage() / var0.getMaxDamage() >= 0.7) {
         return 0.0F;
      }

      float var1 = 0.0F;
      Item var2 = var0.getItem();
      if (var2 instanceof ItemBow) {
         var1 += EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, var0);
         var1 += EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, var0);
         var1 += EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, var0) * 0.5F;
         var1 += EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, var0) * 0.1F;
      }

      return var1;
   }

   public static int X(ItemStack var0) {
      boolean var1 = (double)var0.getItemDamage() / var0.getMaxDamage() >= 0.7;
      int var2 = ((ItemArmor)var0.getItem()).damageReduceAmount + EnchantmentHelper.getEnchantmentModifierDamage(new ItemStack[]{var0}, DamageSource.generic);
      if (var1) {
         var2 = (int)(var2 * 0.5F);
      }

      return var2;
   }


}
