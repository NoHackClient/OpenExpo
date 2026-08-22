package Expo.ui.studio;

import Expo.setting.settings.ModeSetting;
import Expo.util.Animation;
import Expo.util.render.CustomFont;
import Expo.util.render.FontUtil;
import Expo.util.render.RenderUtil;
import java.awt.Color;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

public class ModeSettingComponent extends AbstractSettingComponent<ModeSetting> {
   private static long a;
   private final Animation E;
   private boolean V;
   private static final float f = 8.0F;
   private final Animation p;
   private static final float s = 0.8F;

   public void k(long var1) {
      this.V = false;
   }

   public boolean V(long var1, float var3, float var4, int var5) {
      if (this.I(var3, var4)) {
         if ((var5 == 0 || var5 == 1) && this.O.S().size() > 1) {
            this.V = !this.V;
         }

         return true;
      } else {
         if (this.E.E() > 0.01F) {
            float var6 = this.n + 5.0F;
            float var7 = this.J + this.O() + 1.9F;
            float var8 = this.C - 10.0F;
            float var9 = var7 + 2.0F;

            for (String var11 : this.Q()) {
               if (this.G(var3, var4, var6 + 2.0F, var9, var8 - 4.0F, 8.0F)) {
                  this.O.i(var11);
                  this.V = false;
                  return true;
               }

               var9 += 8.8F;
            }
         }

         this.V = false;
         return false;
      }
   }

   public float O() {
      return 13.0F;
   }

   private List<String> Q() {
      return new ArrayList<>(this.O.S());
   }

   private String K(String var1, long var2) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      var2 = a ^ var2;
      int var4 = (int)((var2 ^ 47966506453505L) >>> 48);
      int var5 = (int)((var2 ^ 47966506453505L) << 16 >>> 32);
      int var6 = (int)((var2 ^ 47966506453505L) << 48 >>> 48);
      List var7 = this.O.o((char)var4, var5, this.S, var6);
      int var8 = this.O.S().indexOf(var1);
      return var8 >= 0 && var8 < var7.size() ? (String)var7.get(var8) : var1;
   }

   public float L(float var1) {
      return (this.Q().size() * 8.8F + 6.0F) * this.E.b(var1);
   }

   static {
      a = 116011316814672L;
   }

   public void Z(int var1, float var2, char var3, float var4, float var5, float var6, short var7, CategoryPalette var8) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var9 = (long)var1 << 32 | (long)var3 << 48 >>> 32 | (long)var7 << 48 >>> 48;
      long var10001 = var9 ^ 8261096794773L;
      int var11 = (int)((var9 ^ 8261096794773L) >>> 32);
      int var12 = (int)((var9 ^ 8261096794773L) << 32 >>> 48);
      int var13 = (int)(var10001 << 48 >>> 48);
      long var14 = var9 ^ 3028978820903L;
      int var16 = (int)((var9 ^ 28952866874662L) >>> 56);
      long var17 = (var9 ^ 28952866874662L) << 8 >>> 8;
      long var19 = (var9 ^ 24899146723189L) >>> 32;
      int var21 = (int)((var9 ^ 24899146723189L) << 32 >>> 32);
      long var22 = var9 ^ 131994158343963L;
      long var24 = var9 ^ 118135367009713L;
      var10001 = var9 ^ 21120043425942L;
      int var26 = (int)((var9 ^ 21120043425942L) >>> 48);
      int var27 = (int)((var9 ^ 21120043425942L) << 16 >>> 48);
      int var28 = (int)(var10001 << 32 >>> 32);
      long var29 = var9 ^ 100216898476969L;
      var10001 = var9 ^ 42529401121054L;
      int var31 = (int)((var9 ^ 42529401121054L) >>> 48);
      int var32 = (int)((var9 ^ 42529401121054L) << 16 >>> 48);
      int var33 = (int)(var10001 << 32 >>> 32);
      long var34 = var9 ^ 131370279024570L;
      var10001 = var9 ^ 101287543088699L;
      int var36 = (int)((var9 ^ 101287543088699L) >>> 32);
      int var37 = (int)((var9 ^ 101287543088699L) << 32 >>> 48);
      long var43 = var9 ^ 98431377028806L;
      CustomFont var50 = FontUtil.n(var19, var21);
      this.p.d(this.I(var2, var4) ? 1.0F : 0.0F);
      this.p.y(0.28F, this.M.y());
      this.E.d(this.V ? 1.0F : 0.0F);
      this.E.y(0.26F, this.M.y());
      String var51 = this.O.a((char)var26, (short)var27, this.S, var28);
      float var52 = Math.min(this.C * 0.6F, Math.max(42.0F, FontUtil.A(var50, var51, 0.66F, var22) + 14.0F));
      float var53 = this.n + this.C - var52 - 6.0F;
      float var54 = Math.max(24.0F, var53 - this.n - 9.0F);
      RenderUtil.j(this.n, this.J, this.n + this.C, this.J + this.O(), 3.0F, var34, this.F(var8, (char)var31, (short)var32, this.p.b(var5), var6, var33));
      String var10002 = FontUtil.Q(var11, var50, this.O.e((byte)var16, this.S, var17), (short)var12, (char)var13, var54, 0.66F);
      float var10003 = this.n + 6.0F;
      float var10004 = this.J + 3.12F;
      Color var10006 = new Color(239, 244, 251);
      float var45 = var6;
      Color var46 = var10006;
      FontUtil.N(var50, var29, var10002, var10003, var10004, 0.66F, FontUtil.a(var36, var37, var46, var45));
      float var55 = this.J + 1.35F;
      float var56 = this.O() - 2.7F;
      float var57 = 8.5F;
      float var58 = var52 - var57 - 6.5F;
      float var59 = FontUtil.w(var50, var51, var58, 0.66F, 0.52F, var14);
      RenderUtil.j(
         var53,
         var55,
         var53 + var52,
         var55 + var56,
         2.6F,
         var34,
         FontUtil.a(
            var36,
            var37,
            FontUtil.Y(new Color(27, 35, 52), var8.i, 0.17F),
            var6

)
      );
      float var87 = var53 + 2.5F;
      var10004 = var52 - var57 - 3.0F;
      Color var10008 = new Color(191, 207, 234);
      var45 = var6;
      var46 = var10008;
      FontUtil.S(var50, var51, var87, var55, var10004, var24, var56, var59, FontUtil.a(var36, var37, var46, var45));
      float var10000 = var53 + var52 - 4.6F;
      float var85 = var55 + var56 / 2.0F + 0.1F;
      var10004 = this.E.b(var5);
      Color var10005 = new Color(240, 245, 252);
      var45 = var6;
      var46 = var10005;
      FontUtil.u(var10000, var85, 2.8F, var10004, FontUtil.a(var36, var37, var46, var45));
      if (!(this.E.b(var5) <= 0.01F)) {
         float var60 = this.n + 5.0F;
         float var61 = this.J + this.O() + 1.9F;
         float var62 = this.C - 10.0F;
         float var63 = this.Q().size() * 8.8F + 4.0F;
         float var64 = var6 * this.E.b(var5);
         var10006 = new Color(
            18,
            23,
            34,
            238
         );
         var45 = var64;
         var46 = var10006;
         int var96 = FontUtil.a(var36, var37, var46, var45);
         Color var10007 = new Color(
            76,
            88,
            112,
            160
         );
         var45 = var64;
         var46 = var10007;
         FontUtil.W(var60, var61, (var60 + var62), (var61 + var63), 3.0F, var96, FontUtil.a(var36, var37, var46, var45));
         float var65 = var61 + 2.0F;

         for (String var67 : this.Q()) {
            boolean var68 = this.G(var2, var4, var60 + 2.0F, var65, var62 - 4.0F, 8.0F);
            boolean var69 = var67.equalsIgnoreCase(this.O.Y());
            RenderUtil.j(
               var60 + 2.0F,
               var65,
               var60 + var62 - 2.0F,
               var65 + 8.0F,
               2.2F,
               var34,
               FontUtil.a(
                  var36,
                  var37,
                  FontUtil.Y(
                     new Color(18, 23, 35),
                     var69
                        ? var8.D
                        : new Color(44, 53, 70),
                     var69 ? 0.32F : (var68 ? 0.16F : 0.0F)
                  ),
                  var64

)
            );
            String var86 = FontUtil.Q(var11, var50, this.K(var67, var43), (short)var12, (char)var13, var62 - 10.0F, 0.66F);
            float var88 = var60 + 4.0F;
            var10005 = var69
               ? var8.g
               : new Color(231, 236, 245);
            var45 = var64;
            var46 = var10005;
            int var75 = FontUtil.a(var36, var37, var46, var45);
            float var81 = 0.66F;
            float var47 = (var65 + 1.25F);
            float var48 = var88;
            String var49 = var86;
            FontUtil.N(var50, var29, var49, var48, var47, var81, var75);
            var65 += 8.8F;
         }
      }
   }

   public ModeSettingComponent(int var1, StudioClickGuiScreen var2, StudioModuleFrame var3, int var4, byte var5, ModeSetting var6) {
      super(
         (var2),
         (var3),
         (var6),
         ((((((long)((var1)) << 32) | (((long)((var4)) << 40) >>> 32)) | (((long)((var5)) << 56) >>> 56)) ^ a) ^ 80735523549708L)
      );
      this.p = new Animation(0.0F);
      this.E = new Animation(0.0F);
   }
}
