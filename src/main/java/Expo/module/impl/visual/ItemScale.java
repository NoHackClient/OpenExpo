package Expo.module.impl.visual;

import Expo.module.Category;

import Expo.module.Module;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.NumberSetting;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.StringUtils;

public class ItemScale extends Module {
   private static long b;
   public static BooleanSetting bedwarsResources;
   public static BooleanSetting nbtOnly;
   public static BooleanSetting renderGoldenApples;
   public static BooleanSetting megawallsItems;
   public static BooleanSetting renderSwordsAndBows;
   public static NumberSetting scale;
   public static BooleanSetting renderALL;

   private static boolean f(Item var0) {
      return var0 == Items.diamond_boots || var0 == Items.diamond_leggings || var0 == Items.diamond_helmet || var0 == Items.diamond_chestplate;
   }

   public ItemScale(long var1) {
      super(((b ^ (var1)) ^ 105075184668941L));
      this.declare("ItemScale", Category.Visual, "Scale the dropped items");
      var1 = b ^ var1;
   }

   static {
      b = 86762146743802L;
   }

   static {
      scale = new NumberSetting("Scale", 3.0F, 0.01F, 5.0F, 0.01F);
   }

   public String g(long var1) {
      if (nbtOnly.c()) {
         return "NBT";
      } else if (renderALL.c()) {
         return "ALL";
      } else if (megawallsItems.c()) {
         return "MEGAWALLS";
      } else if (bedwarsResources.c()) {
         return "BEDWARS";
      } else if (renderGoldenApples.c()) {
         return "GAPPLES";
      } else {
         return renderSwordsAndBows.c() ? "WEAPONS" : "NONE";
      }
   }

   public static boolean c(ItemStack var0) {
      Item var3 = var0.getItem();
      String var4 = var0.getDisplayName();
      String var5 = StringUtils.stripControlCodes(var4);
      if (renderALL.c()) {
         return !nbtOnly.c() || var0.hasTagCompound();
      }

      if (nbtOnly.c()) {
         return var0.hasTagCompound();
      }

      if (!megawallsItems.c()
         || !var5.startsWith("Phoenix's Tears of Regen")
            && !var5.startsWith("Squid's Absorption")
            && !var5.startsWith("Matey")
            && !var5.startsWith("Regen-Ade")
            && !var5.startsWith("Ultra Pasteurized Milk Bucket")
            && !var5.startsWith("Junk Apple")
            && var3 != Items.pumpkin_pie
            && var3 != Items.golden_apple
            && var3 != Items.diamond
            && var3 != Items.diamond_sword
            && !f(var3)) {
         if (!bedwarsResources.c() || var3 != Items.diamond && var3 != Items.gold_ingot && var3 != Items.iron_ingot && var3 != Items.emerald) {
            return !renderSwordsAndBows.c() || !(var3 instanceof ItemSword) && !(var3 instanceof ItemBow) ? renderGoldenApples.c() && var3 == Items.golden_apple : true;
         } else {
            return true;
         }
      } else {
         return true;
      }
   }

   static {
      nbtOnly = new BooleanSetting("NBT-only", false);
      megawallsItems = new BooleanSetting("Megawalls-items", true);
      renderSwordsAndBows = new BooleanSetting("Render-swords-and-bows", false);
      bedwarsResources = new BooleanSetting("Bedwars-resources", false);
      renderGoldenApples = new BooleanSetting("Render-golden-apples", false);
      renderALL = new BooleanSetting("Render-ALL", false);
   }
}
