package Expo.ui.studio;

import Expo.setting.settings.PercentageSetting;
import Expo.util.Animation;
import Expo.util.MathUtil;
import Expo.util.render.CustomFont;
import Expo.util.render.FontUtil;
import Expo.util.render.RenderUtil;
import java.awt.Color;
import org.lwjgl.input.Mouse;











public class PercentageSettingComponent extends AbstractSettingComponent<PercentageSetting> {
   private static long a;
   private boolean g;
   private final Animation H;
   private final Animation r;

   public float O() {
      return 14.8F;
   }

   public void A(float var1, float var2) {
      this.g = false;
   }

   public void k(long var1) {
      this.g = false;
   }

   public void Z(int var1, float var2, char var3, float var4, float var5, float var6, short var7, CategoryPalette var8) {
      long var9 = (long)var1 << 32 | (long)var3 << 48 >>> 32 | (long)var7 << 48 >>> 48;
      long var10001 = var9 ^ 8261096794773L;
      int var11 = (int)((var9 ^ 8261096794773L) >>> 32);
      int var12 = (int)((var9 ^ 8261096794773L) << 32 >>> 48);
      int var13 = (int)(var10001 << 48 >>> 48);
      long var14 = var9 ^ 100216898476969L;
      var10001 = var9 ^ 42529401121054L;
      int var16 = (int)((var9 ^ 42529401121054L) >>> 48);
      int var17 = (int)((var9 ^ 42529401121054L) << 16 >>> 48);
      int var18 = (int)(var10001 << 32 >>> 32);
      int var19 = (int)((var9 ^ 28952866874662L) >>> 56);
      long var20 = (var9 ^ 28952866874662L) << 8 >>> 8;
      long var22 = (var9 ^ 24899146723189L) >>> 32;
      int var24 = (int)((var9 ^ 24899146723189L) << 32 >>> 32);
      int var25 = (int)((var9 ^ 140660820458959L) >>> 32);
      long var26 = (var9 ^ 140660820458959L) << 32 >>> 32;
      long var28 = var9 ^ 131370279024570L;
      var10001 = var9 ^ 101287543088699L;
      int var30 = (int)((var9 ^ 101287543088699L) >>> 32);
      int var31 = (int)((var9 ^ 101287543088699L) << 32 >>> 48);
      long var33 = var9 ^ 131994158343963L;
      long var35 = var9 ^ 118135367009713L;
      CustomFont var39 = FontUtil.n(var22, var24);
      this.r.d(this.I(var2, var4) ? 1.0F : 0.0F);
      this.r.y(0.28F, this.M.y());
      if (this.g && Mouse.isButtonDown(0)) {
         float var40 = this.n + 8.0F;
         float var41 = this.C - 16.0F;
         float var42 = MathUtil.q((var2 - var40) / var41, 0.0F, 1.0F);
         this.O.b(var25, var26, Math.round(var42 * 100.0F));
      }

      float var60 = this.O.k() / 100.0F;
      this.H.d(var60);
      this.H.y(0.28F, this.M.y());
      String var61 = this.O.k() + "%";
      float var62 = Math.max(18.0F, FontUtil.A(var39, var61, 0.66F, var33) + 8.0F);
      float var43 = this.n + this.C - var62 - 6.0F;
      RenderUtil.j(this.n, this.J, this.n + this.C, this.J + this.O(), 3.0F, var28, this.F(var8, (char)var16, (short)var17, this.r.b(var5), var6, var18));
      String var10002 = FontUtil.Q(var11, var39, this.O.e((byte)var19, this.S, var20), (short)var12, (char)var13, var43 - this.n - 10.0F, 0.66F);
      float var10003 = this.n + 6.0F;
      float var10004 = this.J + 3.0F;
      Color var10006 = new Color(239, 244, 251);
      float var37 = var6;
      Color var38 = var10006;
      FontUtil.N(var39, var14, var10002, var10003, var10004, 0.66F, FontUtil.a(var30, var31, var38, var37));
      float var44 = this.J + 1.35F;
      float var45 = 6.0F;
      String var46 = FontUtil.Q(var11, var39, var61, (short)var12, (char)var13, var62 - 4.0F, 0.66F);
      float var66 = var43 + var62;
      var10003 = var44 + var45;
      Color var10005 = new Color(27, 34, 49);
      var37 = var6;
      var38 = var10005;
      RenderUtil.j(var43, var44, var66, var10003, 2.6F, var28, FontUtil.a(var30, var31, var38, var37));
      float var67 = var43 + 1.0F;
      var10004 = var62 - 2.0F;
      Color var10008 = new Color(193, 207, 234);
      var37 = var6;
      var38 = var10008;
      FontUtil.S(var39, var46, var67, var44, var10004, var35, var45, 0.66F, FontUtil.a(var30, var31, var38, var37));
      float var47 = this.n + 8.0F;
      float var48 = this.C - 16.0F;
      float var49 = this.J + this.O() - 3.35F;
      float var68 = var47 + var48;
      var10003 = var49 + 1.6F;
      var10005 = new Color(38, 46, 60);
      var37 = var6;
      var38 = var10005;
      RenderUtil.j(var47, var49, var68, var10003, 0.8F, var28, FontUtil.a(var30, var31, var38, var37));
      float var50 = var48 * this.H.b(var5);
      RenderUtil.j(var47, var49, var47 + var50, var49 + 1.6F, 0.8F, var28, FontUtil.a(var30, var31, FontUtil.Y(var8.i, var8.g, 0.38F), var6));
      float var51 = MathUtil.q(var47 + var50 - 1.7F, var47, var47 + var48 - 3.4F);
      float var65 = var49 - 0.65F;
      float var69 = var51 + 3.4F;
      var10003 = var49 + 2.45F;
      var10005 = new Color(246, 248, 253);
      var37 = var6;
      var38 = var10005;
      RenderUtil.j(var51, var65, var69, var10003, 1.7F, var28, FontUtil.a(var30, var31, var38, var37));
   }

   public PercentageSettingComponent(StudioClickGuiScreen var1, long var2, StudioModuleFrame var4, PercentageSetting var5) {
      super((var1), (var4), (var5), ((a ^ (var2)) ^ 15058347388630L));
      var2 = a ^ var2;
      this.r = new Animation(0.0F);
      this.H = new Animation(0.0F);
   }


   static {
      a = 133150059817494L;
   }

   public boolean V(long var1, float var3, float var4, int var5) {
      float var6 = this.n + 8.0F;
      float var7 = this.C - 16.0F;
      float var8 = this.J + this.O() - 3.35F;
      if (var5 == 0 && this.G(var3, var4, var6, var8 - 1.2F, var7, 4.0F)) {
         this.g = true;
         return true;
      } else {
         return false;
      }
   }   }
