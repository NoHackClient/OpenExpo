package Expo.module.impl.configuration;

import Expo.module.Category;

import Expo.module.Module;
import Expo.setting.settings.ModeSetting;
import Expo.setting.settings.TextSetting;
import Expo.util.EntityUtil;
import Expo.util.MinecraftRef;
import Expo.util.render.ColorUtil;
import java.awt.Color;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.init.Items;
import net.minecraft.item.ItemArmor;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.util.IChatComponent;

public class Teams extends Module {
   private static LinkedHashSet<String> x;
   private static String[] b;
   public static TextSetting customPatternRegex;
   private static long a;
   private static String[] c;
   private static LinkedHashSet<String> s;
   private static Map d;
   private static Minecraft t;
   public static ModeSetting sortMode;

   public static boolean y(EntityLivingBase var0) {
      if (t.isSingleplayer()) {
         return false;
      }

      if (var0 instanceof EntityPlayerSP) {
         return true;
      }

      NetworkPlayerInfo var1 = t.getNetHandler().getPlayerInfo(t.thePlayer.getUniqueID());
      if (var1 == null) {
         return false;
      }

      ScorePlayerTeam var2 = var1.getPlayerTeam();
      if (var2 == null) {
         return false;
      }

      NetworkPlayerInfo var3 = t.getNetHandler().getPlayerInfo(var0.getUniqueID());
      if (var3 == null) {
         return false;
      }

      ScorePlayerTeam var4 = var3.getPlayerTeam();
      return var4 == null ? false : var2.getColorPrefix().equals(var4.getColorPrefix());
   }

   public static boolean g(long var0, Entity var2) {
      if (!EntityUtil.B(0L, var2)) {
         return false;
      }

      if (sortMode.R("NONE")) {
         return false;
      }

      EntityLivingBase var7 = (EntityLivingBase)var2;
      if (a(0L, var7)) {
         return true;
      }

      switch (sortMode.Y()) {
         case "PATTERN":
            Pattern var10 = Pattern.compile(customPatternRegex.X());
            Matcher var11 = var10.matcher(var2.getDisplayName().getFormattedText());
            if (var11.find()) {
               Matcher var12 = var10.matcher(t.thePlayer.getDisplayName().getFormattedText());
               if (var12.find()) {
                  return var12.group().equalsIgnoreCase(var11.group());
               }

               return false;
            }

            return false;
         case "NAME_COLOR":
            return y(var7);
         case "ARMOR_COLOR":
            if (t.thePlayer.getEquipmentInSlot(4) != null
               && t.thePlayer.getEquipmentInSlot(4).getItem() == Items.leather_helmet
               && var7.getEquipmentInSlot(4) != null
               && var7.getEquipmentInSlot(4).getItem() == Items.leather_helmet
               && ((ItemArmor)var7.getEquipmentInSlot(4).getItem()).getColor(var7.getEquipmentInSlot(4))
                  == ((ItemArmor)var7.getEquipmentInSlot(4).getItem()).getColor(t.thePlayer.getEquipmentInSlot(4))) {
               return true;
            }

            return false;
         default:
            return var7.isOnSameTeam(t.thePlayer);
      }
   }

   public static void C(String var0) {
      if (var0 != null) {
         if (!var0.trim().isEmpty()) {
            if (!a().contains(var0)) {
               B().add(var0);
            }
         }
      }
   }

   static {
      a = 125213457127301L;
      x = new LinkedHashSet<>();
      s = new LinkedHashSet<>();
      t = MinecraftRef.c((byte)0, 0L);
   }

   public static void E(String var0) {
      if (var0 != null) {
         if (!var0.trim().isEmpty()) {
            if (!B().contains(var0)) {
               a().add(var0);
            }
         }
      }
   }

   public static Set<String> a() {
      return x;
   }

   public static boolean l(Entity var0) {
      return var0 == null ? false : a().contains(var0.getName());
   }

   public static boolean Y(Entity var0) {
      return var0 == null ? false : B().contains(var0.getName());
   }

   public static int d(short var0, EntityLivingBase var3) {
      if (a().contains(var3.getName())) {
         return ColorUtil.D("2").getRGB();
      }

      if (B().contains(var3.getName())) {
         return ColorUtil.D("4").getRGB();
      }

      switch (sortMode.Y()) {
         case "ARMOR_COLOR":
            if (var3.getEquipmentInSlot(4) != null
               && var3.getEquipmentInSlot(4).getItem() == Items.leather_helmet
               && Items.leather_helmet.hasColor(var3.getEquipmentInSlot(4))) {
               return Items.leather_helmet.getColor(var3.getEquipmentInSlot(4));
            }
         default:
            return u(var3,0L, 1.0F);
      }
   }

   public Teams(long var1, short var3) {
      super((((0L | (((long)((var3)) << 48) >>> 48)) ^ a) ^ 87664267557833L));
      this.declare("Teams", Category.Configuration, "Manage the teaming system");
   }

   public static int u(EntityLivingBase var0, long var1, float var3) {
      int var4 = 16777215;
      ScorePlayerTeam var5 = (ScorePlayerTeam)var0.getTeam();
      if (var5 != null) {
         String var6 = FontRenderer.getFormatFromString(var5.getColorPrefix());
         if (var6.length() >= 2) {
            var4 = t.fontRendererObj.getColorCode(var6.charAt(1));
         }
      }

      return new Color(
            (var4 >> 16 & 255) / 255.0F,
            (var4 >> 8 & 255) / 255.0F,
            (var4 & 255) / 255.0F,
            var3
         )
         .getRGB();
   }

   public static void W() {
      B().clear();
   }

   private static boolean a(long var0, EntityLivingBase var2) {
      if (!(var2 instanceof EntityIronGolem) && !(var2 instanceof EntitySilverfish)) {
         return false;
      }

      char var5 = getFormattedText(t.thePlayer.getDisplayName());
      char var6 = getFormattedText(var2.getDisplayName());
      return var5 != 0 && var5 == var6;
   }

   public static void r$r1() {
      a().clear();
   }

   public static Set<String> B() {
      return s;
   }

   private static char getFormattedText(IChatComponent var0) {
      if (var0 == null) {
         return '\u0000';
      }

      String var3 = var0.getFormattedText();
      if (var3 == null) {
         return '\u0000';
      }

      for (int var4 = 0; var4 < var3.length() - 1; var4++) {
         if (var3.charAt(var4) == 167) {
            char var5 = Character.toLowerCase(var3.charAt(var4 + 1));
            if (var5 >= 48 && var5 <= 57
               || var5 >= 97 && var5 <= 102) {
               return var5;
            }
         }
      }

      return '\u0000';
   }

   static {
      customPatternRegex = new TextSetting("Custom-pattern-regex", "\\[[A-Z]\\]");
      sortMode = new ModeSetting("Sort-mode", "NAME_COLOR", "PATTERN", "ARMOR_COLOR", "VANILLA", "NONE");
   }
}
