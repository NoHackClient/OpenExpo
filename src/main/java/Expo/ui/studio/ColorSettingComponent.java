package Expo.ui.studio;

import Expo.setting.settings.ColorSetting;
import Expo.util.Animation;
import Expo.util.MathUtil;
import Expo.util.render.CustomFont;
import Expo.util.render.FontUtil;
import Expo.util.render.RenderUtil;
import java.awt.Color;
import org.lwjgl.input.Mouse;











public class ColorSettingComponent extends AbstractSettingComponent<ColorSetting> {
   private final Animation r;
   private static long a;
   private boolean i;
   private boolean G;
   private boolean K;
   private final Animation c;

   public void Z(int var1, float var2, char var3, float var4, float var5, float var6, short var7, CategoryPalette var8) {
      long var9 = (long)var1 << 32 | (long)var3 << 48 >>> 32 | (long)var7 << 48 >>> 48;
      long var10001 = var9 ^ 8261096794773L;
      int var11 = (int)((var9 ^ 8261096794773L) >>> 32);
      int var12 = (int)((var9 ^ 8261096794773L) << 32 >>> 48);
      int var13 = (int)(var10001 << 48 >>> 48);
      int var16 = (int)((var9 ^ 28952866874662L) >>> 56);
      long var17 = (var9 ^ 28952866874662L) << 8 >>> 8;
      long var19 = (var9 ^ 24899146723189L) >>> 32;
      int var21 = (int)((var9 ^ 24899146723189L) << 32 >>> 32);
      long var24 = var9 ^ 131994158343963L;
      long var26 = var9 ^ 118135367009713L;
      long var28 = var9 ^ 100216898476969L;
      var10001 = var9 ^ 42529401121054L;
      int var32 = (int)((var9 ^ 42529401121054L) >>> 48);
      int var33 = (int)((var9 ^ 42529401121054L) << 16 >>> 48);
      int var34 = (int)(var10001 << 32 >>> 32);
      long var35 = var9 ^ 131370279024570L;
      var10001 = var9 ^ 101287543088699L;
      int var37 = (int)((var9 ^ 101287543088699L) >>> 32);
      int var38 = (int)((var9 ^ 101287543088699L) << 32 >>> 48);
      CustomFont var44 = FontUtil.n(var19, var21);
      this.r.d(this.I(var2, var4) ? 1.0F : 0.0F);
      this.r.y(0.28F, this.M.y());
      this.c.d(this.K ? 1.0F : 0.0F);
      this.c.y(0.24F, this.M.y());
      RenderUtil.j(this.n, this.J, this.n + this.C, this.J + this.O(), 3.0F, var35, this.F(var8, (char)var32, (short)var33, this.r.b(var5), var6, var34));
      String var10002 = FontUtil.Q(var11, var44, this.O.e((byte)var16, this.S, var17), (short)var12, (char)var13, this.C - 48.0F, 0.66F);
      float var10003 = this.n + 6.0F;
      float var10004 = this.J + 3.05F;
      Color var10006 = new Color(239, 244, 251);
      float var42 = var6;
      Color var43 = var10006;
      FontUtil.N(var44, var28, var10002, var10003, var10004, 0.66F, FontUtil.a(var37, var38, var43, var42));
      Color var45 = this.T();
      String var46 = "#" + this.O.Q().toUpperCase();
      float var47 = Math.max(36.0F, FontUtil.A(var44, var46, 0.66F, var24) + 15.0F);
      float var48 = this.n + this.C - var47 - 6.0F;
      float var49 = this.J + 1.35F;
      float var50 = this.O() - 2.7F;
      float var111 = var48 + var47;
      var10003 = var49 + var50;
      Color var10005 = new Color(24, 31, 45);
      var42 = var6;
      var43 = var10005;
      RenderUtil.j(var48, var49, var111, var10003, 2.6F, var35, FontUtil.a(var37, var38, var43, var42));
      RenderUtil.j(var48 + 3.0F, var49 + 2.1F, var48 + 8.5F, var49 + var50 - 2.1F, 1.8F, var35, FontUtil.a(var37, var38, var45, var6));
      String var104 = FontUtil.Q(var11, var44, var46, (short)var12, (char)var13, var47 - 12.5F, 0.66F);
      float var112 = var48 + 9.5F;
      var10004 = var47 - 10.5F;
      Color var10008 = new Color(193, 207, 234);
      var42 = var6;
      var43 = var10008;
      FontUtil.S(var44, var104, var112, var49, var10004, var26, var50, 0.66F, FontUtil.a(var37, var38, var43, var42));
      if (!(this.c.b(var5) <= 0.01F)) {
         float var51 = this.J + this.O() - 0.25F;
         float var52 = 31.8F;
         float var53 = this.n + 5.0F;
         float var54 = var51 + 3.2F;
         float var55 = this.C - 10.0F;
         float var56 = 20.0F;
         float var57 = var54 + var56 + 1.9F;
         float var58 = 4.0F;
         float var59 = var6 * this.c.b(var5);
         Color var10000 = new Color(
            20,
            26,
            38,
            224
         );
         var42 = var59;
         var43 = var10000;
         int var60 = FontUtil.a(var37, var38, var43, var42);
         var10000 = new Color(
            20,
            26,
            38,
            224
         );
         var42 = var59;
         var43 = var10000;
         int var61 = FontUtil.a(var37, var38, var43, var42);
         RenderUtil.j(this.n, var51, this.n + this.C, var51 + var52, 3.0F, var35, var60);
         RenderUtil.c(0L, this.n + 1.0F, var51, this.n + this.C - 1.0F, var51 + 3.0F, var61);
         double var105 = this.n + 1.0F;
         double var113 = var51 + var52 - 0.8F;
         double var120 = this.n + this.C - 1.0F;
         double var127 = var51 + var52;
         var10005 = new Color(
            44,
            55,
            75,
            70
         );
         var42 = var59;
         var43 = var10005;
         RenderUtil.c(0L, var105, var113, var120, var127, FontUtil.a(var37, var38, var43, var42));
         if (this.G && Mouse.isButtonDown(0)) {
            this.s(0L, var2, var4, var53, var54, var55, var56);
         }

         if (this.i && Mouse.isButtonDown(0)) {
            this.Q(var2, var53, var55,0L);
         }

         float[] var62 = Color.RGBtoHSB(var45.getRed(), var45.getGreen(), var45.getBlue(), null);
         float var63 = var59;
         float var99 = var53 + 0.5F;
         float var106 = var54 + 0.5F;
         float var114 = var53 + var55 - 0.5F;
         var10003 = var54 + var56 - 0.5F;
         var10005 = new Color(
            26,
            31,
            43,
            210
         );
         var42 = var63;
         var43 = var10005;
         RenderUtil.j(var99, var106, var114, var10003, 2.2F, var35, FontUtil.a(var37, var38, var43, var42));
         int var64 = Math.max(1, (int)var55 - 2);

         for (int var65 = 0; var65 < var64; var65++) {
            float var66 = (float)var65 / Math.max(1, var64 - 1);
            Color var67 = Color.getHSBColor(var62[0], var66, 1.0F);
            RenderUtil.c(0L, var53 + 1.0F + var65, var54 + 1.0F, var53 + 2.0F + var65, var54 + var56 - 1.0F, FontUtil.a(var37, var38, var67, var63));
         }

         int var91 = Math.max(1, (int)var56 - 2);

         for (int var92 = 0; var92 < var91; var92++) {
            float var94 = (float)var92 / Math.max(1, var91 - 1);
            int var68 = Math.min(255, Math.round(var94 * 255.0F));
            double var107 = var53 + 1.0F;
            double var115 = var54 + 1.0F + var92;
            double var122 = var53 + var55 - 1.0F;
            double var128 = var54 + 2.0F + var92;
            var10005 = new Color(0, 0, 0, var68);
            var42 = var63;
            var43 = var10005;
            RenderUtil.c(0L, var107, var115, var122, var128, FontUtil.a(var37, var38, var43, var42));
         }

         float var93 = var53 + 1.0F + var62[1] * (var55 - 2.0F);
         float var95 = var54 + 1.0F + (1.0F - var62[2]) * (var56 - 2.0F);
         float var100 = var93 - 1.5F;
         float var108 = var95 - 1.5F;
         float var116 = var93 + 1.5F;
         var10003 = var95 + 1.5F;
         var10005 = new Color(232, 237, 245);
         var42 = var63;
         var43 = var10005;
         RenderUtil.j(var100, var108, var116, var10003, 1.5F, var35, FontUtil.a(var37, var38, var43, var42));
         RenderUtil.j(var93 - 0.8F, var95 - 0.8F, var93 + 0.8F, var95 + 0.8F, 0.8F, var35, FontUtil.a(var37, var38, var45, var63));
         int var96 = Math.max(1, (int)var55 - 2);

         for (int var69 = 0; var69 < var96; var69++) {
            float var70 = (float)var69 / Math.max(1, var96 - 1);
            double var109 = var53 + 1.0F + var69;
            double var117 = var57 + 0.8F;
            double var124 = var53 + 2.0F + var69;
            double var129 = var57 + var58 - 0.8F;
            var10005 = new Color(Color.HSBtoRGB(var70, 0.9F, 1.0F));
            var42 = var63;
            var43 = var10005;
            RenderUtil.c(0L, var109, var117, var124, var129, FontUtil.a(var37, var38, var43, var42));
         }

         float var97 = var53 + 1.0F + var62[0] * (var55 - 2.0F);
         float var101 = var97 - 1.1F;
         float var110 = var57 + 0.2F;
         float var118 = var97 + 1.1F;
         var10003 = var57 + var58 - 0.2F;
         var10005 = new Color(232, 237, 245);
         var42 = var63;
         var43 = var10005;
         RenderUtil.j(var101, var110, var118, var10003, 1.0F, var35, FontUtil.a(var37, var38, var43, var42));
      }
   }

   private Color T() {

      try {
         return new Color(Integer.parseInt(this.O.Q(), 16));
      } catch (Exception var4) {
         Expo.internal.restore.ExpoDiag.attribute(var4, "ColorSettingComponent.T/1#0");
         return Color.WHITE;
      }
   }

   public void k(long var1) {
      this.G = false;
      this.i = false;
      this.K = false;
   }

   static {
      a = 25808236507165L;
   }

   public float L(float var1) {
      return 33.0F * this.c.b(var1);
   }

   public ColorSettingComponent(long var1, StudioClickGuiScreen var3, StudioModuleFrame var4, ColorSetting var5) {
      super((var3), (var4), (var5), ((a ^ (var1)) ^ 12666516285967L));
      var1 = a ^ var1;
      this.r = new Animation(0.0F);
      this.c = new Animation(0.0F);
   }

   public boolean V(long var1, float var3, float var4, int var5) {
      float var10 = this.J + this.O() - 0.25F;
      float var11 = this.n + 5.0F;
      float var12 = var10 + 3.2F;
      float var13 = this.C - 10.0F;
      float var14 = 20.0F;
      float var15 = var12 + var14 + 1.9F;
      if (!this.I(var3, var4)) {
         if (this.c.E() > 0.01F && var5 == 0) {
            if (this.G(var3, var4, var11, var12, var13, var14)) {
               this.G = true;
               this.s(0L, var3, var4, var11, var12, var13, var14);
               return true;
            }

            if (this.G(var3, var4, var11, var15, var13, 4.0F)) {
               this.i = true;
               this.Q(var3, var11, var13,0L);
               return true;
            }
         }

         return false;
      } else {
         if (var5 == 0 || var5 == 1) {
            this.K = !this.K;
         }

         return true;
      }
   }

   public void A(float var1, float var2) {
      this.G = false;
      this.i = false;
   }

   public float O() {
      return 13.5F;
   }

   private void s(long var1, float var3, float var4, float var5, float var6, float var7, float var8) {
      Color var11 = this.T();
      float[] var12 = Color.RGBtoHSB(var11.getRed(), var11.getGreen(), var11.getBlue(), null);
      float var13 = MathUtil.q((var3 - var5) / var7, 0.0F, 1.0F);
      float var14 = 1.0F - MathUtil.q((var4 - var6) / var8, 0.0F, 1.0F);
      this.O.e(String.format("%06X", Color.HSBtoRGB(var12[0], var13, var14) & 16777215));
   }

   private void Q(float var1, float var2, float var3, long var4) {
      Color var8 = this.T();
      float[] var9 = Color.RGBtoHSB(var8.getRed(), var8.getGreen(), var8.getBlue(), null);
      float var10 = MathUtil.q((var1 - var2) / var3, 0.0F, 1.0F);
      this.O
         .e(
            String.format(
               "%06X",
               Color.HSBtoRGB(var10, Math.max(var9[1], 0.01F), Math.max(var9[2], 0.01F)) & 16777215
            )
         );
   }


}
