package Expo.ui.studio;

import Expo.setting.settings.HeaderSetting;
import Expo.util.render.CustomFont;
import Expo.util.render.FontUtil;
import Expo.util.render.RenderUtil;
import java.awt.Color;

public class HeaderSettingComponent extends AbstractSettingComponent<HeaderSetting> {
   private static long a;

   public void Z(int var1, float var2, char var3, float var4, float var5, float var6, short var7, CategoryPalette var8) {
      long var9 = (long)var1 << 32 | (long)var3 << 48 >>> 32 | (long)var7 << 48 >>> 48;
      long var10001 = var9 ^ 8261096794773L;
      int var11 = (int)((var9 ^ 8261096794773L) >>> 32);
      int var12 = (int)((var9 ^ 8261096794773L) << 32 >>> 48);
      int var13 = (int)(var10001 << 48 >>> 48);
      long var14 = var9 ^ 100216898476969L;
      long var16 = (var9 ^ 24899146723189L) >>> 32;
      int var18 = (int)((var9 ^ 24899146723189L) << 32 >>> 32);
      var10001 = var9 ^ 101287543088699L;
      int var19 = (int)((var9 ^ 101287543088699L) >>> 32);
      int var20 = (int)((var9 ^ 101287543088699L) << 32 >>> 48);
      long var22 = var9 ^ 131370279024570L;
      long var24 = var9 ^ 14734118612447L;
      CustomFont var28 = FontUtil.n(var16, var18);
      float var10000 = this.n;
      float var32 = this.J;
      float var10002 = this.n + this.C;
      float var10003 = this.J + this.O();
      Color var10005 = new Color(
         15,
         18,
         27,
         215
      );
      float var26 = var6;
      Color var27 = var10005;
      RenderUtil.j(var10000, var32, var10002, var10003, 2.6F, var22, FontUtil.a(var19, var20, var27, var26));
      String var33 = FontUtil.Q(var11, var28, this.O.U(var24, this.S), (short)var12, (char)var13, this.C - 10.0F, 0.66F);
      var10003 = this.n + 6.0F;
      float var10004 = this.J + 2.35F;
      Color var10006 = new Color(165, 176, 194);
      var26 = var6;
      var27 = var10006;
      FontUtil.N(var28, var14, var33, var10003, var10004, 0.66F, FontUtil.a(var19, var20, var27, var26));
   }

   public HeaderSettingComponent(StudioClickGuiScreen var1, StudioModuleFrame var2, long var3, HeaderSetting var5) {
      super((var1), (var2), (var5), ((a ^ (var3)) ^ 4673722109110L));
      var3 = a ^ var3;
   }

   static {
      a = 71972391866718L;
   }

   public float O() {
      return 10.5F;
   }

   public boolean V(long var1, float var3, float var4, int var5) {
      return false;
   }   }
