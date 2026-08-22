package Expo.ui.studio;

import Expo.setting.settings.NumberSetting;
import Expo.util.Animation;
import Expo.util.MathUtil;
import Expo.util.render.CustomFont;
import Expo.util.render.FontUtil;
import Expo.util.render.RenderUtil;
import java.awt.Color;
import org.lwjgl.input.Mouse;











public class NumberSettingComponent extends AbstractSettingComponent<NumberSetting> {
   private final Animation B;
   private static long a;
   private boolean W;
   private final Animation E;

   public float O() {
      return 14.8F;
   }

   public void Z(int var1, float var2, char var3, float var4, float var5, float var6, short var7, CategoryPalette var8) {
      long var9 = (long)var1 << 32 | (long)var3 << 48 >>> 32 | (long)var7 << 48 >>> 48;
      long var10001 = var9 ^ 8261096794773L;
      int var11 = (int)((var9 ^ 8261096794773L) >>> 32);
      int var12 = (int)((var9 ^ 8261096794773L) << 32 >>> 48);
      int var13 = (int)(var10001 << 48 >>> 48);
      int var14 = (int)((var9 ^ 100063070040007L) >>> 56);
      long var15 = (var9 ^ 100063070040007L) << 8 >>> 8;
      long var17 = var9 ^ 100216898476969L;
      var10001 = var9 ^ 42529401121054L;
      int var21 = (int)((var9 ^ 42529401121054L) >>> 48);
      int var22 = (int)((var9 ^ 42529401121054L) << 16 >>> 48);
      int var23 = (int)(var10001 << 32 >>> 32);
      int var24 = (int)((var9 ^ 28952866874662L) >>> 56);
      long var25 = (var9 ^ 28952866874662L) << 8 >>> 8;
      long var27 = (var9 ^ 24899146723189L) >>> 32;
      int var29 = (int)((var9 ^ 24899146723189L) << 32 >>> 32);
      long var30 = var9 ^ 131370279024570L;
      var10001 = var9 ^ 101287543088699L;
      int var32 = (int)((var9 ^ 101287543088699L) >>> 32);
      int var33 = (int)((var9 ^ 101287543088699L) << 32 >>> 48);
      long var35 = var9 ^ 131994158343963L;
      long var37 = var9 ^ 118135367009713L;
      CustomFont var41 = FontUtil.n(var27, var29);
      this.E.d(this.I(var2, var4) ? 1.0F : 0.0F);
      this.E.y(0.28F, this.M.y());
      if (this.W && Mouse.isButtonDown(0)) {
         float var42 = this.n + 8.0F;
         float var43 = this.C - 16.0F;
         float var44 = MathUtil.q((var2 - var42) / var43, 0.0F, 1.0F);
         this.O.o((byte)var14, var15, this.O.i() + var44 * (this.O.F() - this.O.i()));
      }

      float var62 = (this.O.L() - this.O.i()) / (this.O.F() - this.O.i());
      this.B.d(var62);
      this.B.y(0.28F, this.M.y());
      String var63 = FontUtil.l(this.O.L(),0L);
      float var64 = Math.max(18.0F, FontUtil.A(var41, var63, 0.66F, var35) + 8.0F);
      float var45 = this.n + this.C - var64 - 6.0F;
      RenderUtil.j(this.n, this.J, this.n + this.C, this.J + this.O(), 3.0F, var30, this.F(var8, (char)var21, (short)var22, this.E.b(var5), var6, var23));
      String var10002 = FontUtil.Q(var11, var41, this.O.e((byte)var24, this.S, var25), (short)var12, (char)var13, var45 - this.n - 10.0F, 0.66F);
      float var10003 = this.n + 6.0F;
      float var10004 = this.J + 3.0F;
      Color var10006 = new Color(239, 244, 251);
      float var39 = var6;
      Color var40 = var10006;
      FontUtil.N(var41, var17, var10002, var10003, var10004, 0.66F, FontUtil.a(var32, var33, var40, var39));
      float var46 = this.J + 1.35F;
      float var47 = 6.0F;
      String var48 = FontUtil.Q(var11, var41, var63, (short)var12, (char)var13, var64 - 4.0F, 0.66F);
      float var68 = var45 + var64;
      var10003 = var46 + var47;
      Color var10005 = new Color(27, 34, 49);
      var39 = var6;
      var40 = var10005;
      RenderUtil.j(var45, var46, var68, var10003, 2.6F, var30, FontUtil.a(var32, var33, var40, var39));
      float var69 = var45 + 1.0F;
      var10004 = var64 - 2.0F;
      Color var10008 = new Color(193, 207, 234);
      var39 = var6;
      var40 = var10008;
      FontUtil.S(var41, var48, var69, var46, var10004, var37, var47, 0.66F, FontUtil.a(var32, var33, var40, var39));
      float var49 = this.n + 8.0F;
      float var50 = this.C - 16.0F;
      float var51 = this.J + this.O() - 3.35F;
      float var70 = var49 + var50;
      var10003 = var51 + 1.6F;
      var10005 = new Color(38, 46, 60);
      var39 = var6;
      var40 = var10005;
      RenderUtil.j(var49, var51, var70, var10003, 0.8F, var30, FontUtil.a(var32, var33, var40, var39));
      float var52 = var50 * this.B.b(var5);
      RenderUtil.j(var49, var51, var49 + var52, var51 + 1.6F, 0.8F, var30, FontUtil.a(var32, var33, FontUtil.Y(var8.i, var8.g, 0.38F), var6));
      float var53 = MathUtil.q(var49 + var52 - 1.7F, var49, var49 + var50 - 3.4F);
      float var67 = var51 - 0.65F;
      float var71 = var53 + 3.4F;
      var10003 = var51 + 2.45F;
      var10005 = new Color(246, 248, 253);
      var39 = var6;
      var40 = var10005;
      RenderUtil.j(var53, var67, var71, var10003, 1.7F, var30, FontUtil.a(var32, var33, var40, var39));
   }

   static {
      a = 19434520715838L;
   }

   public NumberSettingComponent(long var1, StudioClickGuiScreen var3, StudioModuleFrame var4, NumberSetting var5) {
      super((var3), (var4), (var5), ((a ^ (var1)) ^ 116685794788764L));
      var1 = a ^ var1;
      this.E = new Animation(0.0F);
      this.B = new Animation(0.0F);
   }

   public void A(float var1, float var2) {
      this.W = false;
   }

   public void k(long var1) {
      this.W = false;
   }


   public boolean V(long var1, float var3, float var4, int var5) {
      float var6 = this.n + 8.0F;
      float var7 = this.C - 16.0F;
      float var8 = this.J + this.O() - 3.35F;
      if (var5 == 0 && this.G(var3, var4, var6, var8 - 1.2F, var7, 4.0F)) {
         this.W = true;
         return true;
      } else {
         return false;
      }
   }   }
