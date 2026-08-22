package Expo.ui.studio;

import Expo.setting.settings.BooleanSetting;
import Expo.util.Animation;
import Expo.util.render.CustomFont;
import Expo.util.render.FontUtil;
import Expo.util.render.RenderUtil;
import java.awt.Color;











public class BooleanSettingComponent extends AbstractSettingComponent<BooleanSetting> {
   private static long a;
   private final Animation c;
   private final Animation L;


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
      long var25 = var9 ^ 131370279024570L;
      var10001 = var9 ^ 101287543088699L;
      int var27 = (int)((var9 ^ 101287543088699L) >>> 32);
      int var28 = (int)((var9 ^ 101287543088699L) << 32 >>> 48);
      CustomFont var32 = FontUtil.n(var22, var24);
      this.L.d(this.I(var2, var4) ? 1.0F : 0.0F);
      this.L.y(0.28F, this.M.y());
      this.c.d(this.O.c() ? 1.0F : 0.0F);
      this.c.y(0.28F, this.M.y());
      RenderUtil.j(this.n, this.J, this.n + this.C, this.J + this.O(), 3.0F, var25, this.F(var8, (char)var16, (short)var17, this.L.b(var5), var6, var18));
      String var10002 = FontUtil.Q(var11, var32, this.O.e((byte)var19, this.S, var20), (short)var12, (char)var13, this.C - 24.0F, 0.66F);
      float var10003 = this.n + 6.0F;
      float var10004 = this.J + 3.15F;
      Color var10006 = new Color(239, 244, 251);
      float var30 = var6;
      Color var31 = var10006;
      FontUtil.N(var32, var14, var10002, var10003, var10004, 0.66F, FontUtil.a(var27, var28, var31, var30));
      float var33 = 13.0F;
      float var34 = 6.0F;
      float var35 = this.n + this.C - var33 - 6.0F;
      float var36 = this.J + this.O() / 2.0F - var34 / 2.0F;
      Color var37 = FontUtil.Y(
         new Color(57, 66, 84), var8.g, this.c.b(var5)
      );
      RenderUtil.j(var35, var36, var35 + var33, var36 + var34, 3.0F, var25, FontUtil.a(var27, var28, var37, var6));
      float var38 = 4.0F;
      float var39 = var35 + 1.0F + (var33 - var38 - 2.0F) * this.c.b(var5);
      float var44 = var36 + 1.0F;
      float var45 = var39 + var38;
      var10003 = var36 + 1.0F + var38;
      Color var10005 = new Color(248, 250, 255);
      var30 = var6;
      var31 = var10005;
      RenderUtil.j(var39, var44, var45, var10003, 2.0F, var25, FontUtil.a(var27, var28, var31, var30));
   }

   public BooleanSettingComponent(StudioClickGuiScreen var1, long var2, StudioModuleFrame var4, BooleanSetting var5) {
      super((var1), (var4), (var5), ((a ^ (var2)) ^ 18754876252789L));
      var2 = a ^ var2;
      this.L = new Animation(0.0F);
      this.c = new Animation(0.0F);
   }

   static {
      a = 79963295163333L;
   }

   public float O() {
      return 13.0F;
   }

   public boolean V(long var1, float var3, float var4, int var5) throws Throwable {
      long var6 = var1 ^ 9140251651898L;
      if (var5 == 0 && this.I(var3, var4)) {
         this.O.W(var6);
         return true;
      } else {
         return false;
      }
   }   }
