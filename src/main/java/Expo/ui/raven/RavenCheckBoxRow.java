package Expo.ui.raven;

import Expo.module.Module;
import Expo.setting.Setting;
import Expo.setting.settings.BooleanSetting;
import org.lwjgl.opengl.GL11;











public class RavenCheckBoxRow extends AbstractRavenSettingRow {
   private Module d;
   private static long c;
   private static String[] i;
   private BooleanSetting u;
   private static String[] f;



   public static void h(long var0) {
      GL11.glEnable(3553);
      GL11.glEnable(2929);
      GL11.glDisable(2848);
      GL11.glHint(3154, 4352);
      GL11.glHint(3155, 4352);
   }

   static {
      c = 131974201764505L;
   }

   public void U(long var1) {
      long var3 = var1 ^ 11699446097668L;
      long var5 = var1 ^ 68628560147625L;
      int var7 = (int)((var1 ^ 58246301632428L) >>> 56);
      long var8 = (var1 ^ 58246301632428L) << 8 >>> 8;
      GL11.glPushMatrix();
      GL11.glScaled(0.5, 0.5, 0.5);
      this.C(var5)
         .v(
            (this.u.c() ? "[+]  " : "[-]  ") + this.u.e((byte)var7, this.d, var8).replace("-", " "),
            (this.O.O.X() + 4) * 2,
            (this.O.O.T() + this.y + 4) * 2,
            this.u.c() ? this.N : this.P,
            var3,
            false
         );
      GL11.glPopMatrix();
   }



   public static void a(float var0, float var1, float var2, float var3, long var4, int var6) {
      var4 = c ^ var4;
      int var7 = (int)((var4 ^ 29706002051423L) >>> 56);
      int var10 = (int)((var4 ^ 31953491051437L) >>> 32);
      int var11 = (int)((var4 ^ 31953491051437L) << 32 >>> 48);
      int var12 = (int)((var4 ^ 31953491051437L) << 48 >>> 48);
      t();
      E(var6, var10, (short)var11, (char)var12);
      h(var0, (byte)var7, var1, var2, var3);
      h(0L);
   }

   public static void h(float var0, byte var1, float var2, float var3, float var6) {
      GL11.glBegin(7);
      GL11.glVertex2f(var0, var6);
      GL11.glVertex2f(var3, var6);
      GL11.glVertex2f(var3, var2);
      GL11.glVertex2f(var0, var2);
      GL11.glEnd();
   }

   public static void t() {
      GL11.glDisable(2929);
      GL11.glDisable(3553);
      GL11.glBlendFunc(770, 771);
      GL11.glDepthMask(true);
      GL11.glEnable(2848);
      GL11.glHint(3154, 4354);
      GL11.glHint(3155, 4354);
   }

   public static void E(int var0, int var1, short var2, char var3) {
      float var6 = (var0 >> 24 & 255) / 350.0F;
      GL11.glColor4f(0.0F, 0.0F, 0.0F, var6);
   }

   public boolean G(int var1, int var4) {
      return var1 > this.h && var1 < this.h + this.O.O.t() && var4 > this.g && var4 < this.g + 11;
   }

   public void i(int var1, int var2, int var3, byte var4) {
      this.y = var1;
   }

   private void V(Module var1, BooleanSetting var2, RavenModuleRow var3, int var4) {
      this.d = var1;
      this.u = var2;
      this.h = var3.O.X() + var3.O.t();
      this.g = var3.O.T() + var3.L;
      this.y = var4;
   }

   public RavenCheckBoxRow(Module var1, BooleanSetting var2, RavenModuleRow var3, int var4) {
      super(var3);
      this.V(var1, var2, var3, var4);
   }

   public void r(char var1, int var2, int var3, int var4, long var5) throws Throwable {
      long var7 = (long)var1 << 48 | var5 << 16 >>> 16;
      long var9 = (var7 ^ 94618552824683L) >>> 16;
      int var11 = (int)((var7 ^ 94618552824683L) << 48 >>> 48);
      long var14 = var7 ^ 86837578251337L;
      if (this.G(var2, var3) && var4 == 0 && this.O.k) {
         this.u.W(var14);
         this.O.O.M(var9, (char)var11);
      }
   }

   public Setting f() {
      return this.u;
   }

   public void V(long var1, int var3, int var4) {
      this.g = this.O.O.T() + this.y;
      this.h = this.O.O.X();
   }


}
