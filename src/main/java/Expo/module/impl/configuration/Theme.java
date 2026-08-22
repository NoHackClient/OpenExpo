package Expo.module.impl.configuration;

import Expo.module.Category;

import Expo.enums.GradientPalette;
import Expo.module.Module;
import Expo.setting.settings.ColorSetting;
import Expo.setting.settings.ModeSetting;
import Expo.setting.settings.NumberSetting;
import Expo.util.render.ColorUtil;
import java.awt.Color;
import java.util.Arrays;
import java.util.List;

public class Theme extends Module {
   public static ColorSetting customColor1;
   private static final double N = 12.0;
   public static ModeSetting customTheme;
   public static ColorSetting customColor2;
   public static NumberSetting timerMultiplier;
   public static NumberSetting offset;
   public static ColorSetting customColor3;
   public static ModeSetting theme;
   private static long b;

   public static int M(double var0, double var2, int var4, long var5) {
      long var13 = var5 ^ 55716916432493L;
      int var15 = L(var0, var13, var2);
      int var16 = ColorUtil.l(var15,0L);
      int var17 = ColorUtil.U(0L, var15);
      int var18 = ColorUtil.d(0L, var15);
      return new Color(var16, var17, var18, var4).getRGB();
   }

   public static int S(double var0, long var2) {
      return c(var0, 96536354457767L, theme.Y());
   }

   public static int Z(double var0, long var2, double var4) {
      long var6 = var2 ^ 110483295233000L;
      return F(var0, var4, var6, customTheme.Y());
   }

   public static int e(double var0, int var2, long var3) {
      int var13 = X(65301174328177L, var0);
      int var14 = ColorUtil.l(var13,0L);
      int var15 = ColorUtil.U(0L, var13);
      int var16 = ColorUtil.d(0L, var13);
      return new Color(var14, var15, var16, var2).getRGB();
   }

   public static int L(double var0, long var2, double var4) {
      return F(var0, var4, 125154644418230L, theme.Y());
   }

   public static int n(long var0, double var2, int var4) {
      int var13 = S(var2, 35338930340239L);
      int var14 = ColorUtil.l(var13,0L);
      int var15 = ColorUtil.U(0L, var13);
      int var16 = ColorUtil.d(0L, var13);
      return new Color(var14, var15, var16, var4).getRGB();
   }

   public static List w(long var0, double var2, double var4, String var6) {
      int var16;
      int var17;
      int var18;
      switch (var6.toUpperCase()) {
         case "RAINBOW":
            var16 = GradientPalette.R(0.0, var4);
            var17 = GradientPalette.R(var2, var4);
            var18 = GradientPalette.R(2.0 * var2, var4);
            break;
         case "CUSTOM":
            var16 = new Color(customColor1.k(96531491288662L)).getRGB();
            var17 = new Color(customColor2.k(96531491288662L)).getRGB();
            var18 = new Color(customColor2.k(96531491288662L)).getRGB();
            break;
         default:
            var16 = GradientPalette.valueOf(var6.toUpperCase()).m(289536317541L, (byte)4, var2, var4);
            var17 = GradientPalette.valueOf(var6.toUpperCase()).M(var2, 113014618771944L, var4);
            var18 = GradientPalette.valueOf(var6.toUpperCase()).H(var2, var4, 19693308353391L);
      }

      return Arrays.asList(var16, var17, var18);
   }

   static {
      b = 15870130175804L;
   }

   public static List k(int var0, int var1, short var2) {
      long var3 = ((long)var0 << 32 | (long)var1 << 48 >>> 32 | (long)var2 << 48 >>> 48) ^ b;
      long var5 = var3 ^ 31634709555319L;
      return w(var5, offset.L(), timerMultiplier.L(), theme.Y());
   }

   public static int c(double var0, long var2, String var4) {
      switch (var4.toUpperCase()) {
         case "RAINBOW":
            return GradientPalette.R(var0, timerMultiplier.L());
         case "CUSTOM":
            return GradientPalette.J( var0, timerMultiplier.L(), new Color(customColor1.k(96531491288662L)), new Color(customColor2.k(96531491288662L)), new Color(customColor3.k(96531491288662L)));
         default:
            GradientPalette var11 = GradientPalette.valueOf(var4.toUpperCase());
            return GradientPalette.J( var0, timerMultiplier.L(), var11.s(), var11.p(), var11.a());
      }
   }

   public static int F(double var0, double var2, long var4, String var6) {
      double var9 = Math.max(0.0, Math.min(1.0, var2));
      return c(var0 + var9 * 12.0, 96536354457767L, var6);
   }

   public static int E(long var0, double var2, double var4, int var6) {
      long var13 = var0 ^ 130077259484612L;
      int var15 = Z(var2, var13, var4);
      int var16 = ColorUtil.l(var15,0L);
      int var17 = ColorUtil.U(0L, var15);
      int var18 = ColorUtil.d(0L, var15);
      return new Color(var16, var17, var18, var6).getRGB();
   }

   public static int X(long var0, double var2) {
      return c(var2, 96536354457767L, customTheme.Y());
   }

   public Theme(int var1, char var2, int var3) {
      super(((((((long)((var1)) << 32) | (((long)((var2)) << 48) >>> 32)) | (((long)((var3)) << 48) >>> 48)) ^ b) ^ 30936422044319L));
      this.declare("Theme", Category.Configuration, "The color theme of the client");
   }

   static {
      customColor3 = new ColorSetting("Custom-color-3", "FFFFFF");
      customColor2 = new ColorSetting("Custom-color-2", "FFFFFF");
      customTheme = new ModeSetting("Custom-theme", "CUSTOM", "RAINBOW", "SUNSET", "OCEAN", "FIRE", "ICE", "PURPLE_DREAM", "FOREST", "CYBER", "ROSE", "GOLD", "LAVA", "NIGHT", "AQUA", "MAGMA", "TOXIC", "NEON_PINK", "CYBER_BLUE", "MATRIX", "LASER", "ULTRAVIOLET", "PEACH", "LAVENDER", "MINT", "SAND", "OBSIDIAN", "MIDNIGHT", "CARBON", "DEEP_SEA", "BLOOD", "ELECTRIC", "SOLAR");
      theme = new ModeSetting("Theme", false, "AQUA", "CUSTOM", "RAINBOW", "SUNSET", "OCEAN", "FIRE", "ICE", "PURPLE_DREAM", "FOREST", "CYBER", "ROSE", "GOLD", "LAVA", "NIGHT", "AQUA", "MAGMA", "TOXIC", "NEON_PINK", "CYBER_BLUE", "MATRIX", "LASER", "ULTRAVIOLET", "PEACH", "LAVENDER", "MINT", "SAND", "OBSIDIAN", "MIDNIGHT", "CARBON", "DEEP_SEA", "BLOOD", "ELECTRIC", "SOLAR");
      offset = new NumberSetting("Offset", 2.0F, -10.0F, 10.0F, 0.1F);
      timerMultiplier = new NumberSetting("Timer-multiplier", 1.0F, 0.1F, 4.0F, 0.1F);
      customColor1 = new ColorSetting("Custom-color-1", "FFFFFF");
   }
}
