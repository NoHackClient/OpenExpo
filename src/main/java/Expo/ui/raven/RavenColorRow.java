package Expo.ui.raven;

import Expo.module.Module;
import Expo.setting.Setting;
import Expo.setting.settings.ColorSetting;
import java.awt.Color;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import org.lwjgl.opengl.GL11;

public class RavenColorRow extends AbstractRavenSettingRow {
   private ColorSetting Q;
   private Module J;
   private int G;
   private int V;
   private static long d;
   private boolean c;
   private int l;

   public void r(char var1, int var2, int var3, int var4, long var5) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      if (this.O.k && var4 == 0) {
         int var11 = this.O.O.t();
         int var12 = this.V + 4;
         int var13 = this.l + 11;
         int var15 = var11 - 8;
         if (var2 >= var12 && var2 <= var12 + var15 && var3 >= var13 && var3 <= var13 + 4) {
            this.c = true;
            this.V(0L, var2, var3);
         }
      }
   }

   private static int Q(String var0) {
      try {
         return Integer.parseInt(var0.replace("#", ""), 16) & 16777215;
      } catch (Exception var4) {
         return 16777215;
      }
   }

   private static int a(int var0,int var3, float var4) {
      float var5 = (var0 >> 16 & 255) / 255.0F;
      float var6 = (var0 >> 8 & 255) / 255.0F;
      float var7 = (var0 & 255) / 255.0F;
      float var8 = (var3 >> 16 & 255) / 255.0F;
      float var9 = (var3 >> 8 & 255) / 255.0F;
      float var10 = (var3 & 255) / 255.0F;
      int var11 = Math.round((var5 * (1.0F - var4) + var8 * var4) * 255.0F);
      int var12 = Math.round((var6 * (1.0F - var4) + var9 * var4) * 255.0F);
      int var13 = Math.round((var7 * (1.0F - var4) + var10 * var4) * 255.0F);
      return var11 << 16 | var12 << 8 | var13;
   }

   public void U(long var1) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var3 = var1 ^ 11699446097668L;
      long var9 = var1 ^ 68628560147625L;
      int var11 = (int)((var1 ^ 58246301632428L) >>> 56);
      long var12 = (var1 ^ 58246301632428L) << 8 >>> 8;
      long var14 = var1 ^ 98782596732208L;
      if (this.O.k) {
         this.V = this.O.O.X();
         this.l = this.O.O.T() + this.G;
         int var18 = this.O.O.t();
         int var19 = this.V + 4;
         int var20 = this.l + 11;
         int var22 = var18 - 8;
         GL11.glPushMatrix();
         GL11.glScaled(0.5, 0.5, 0.5);
         this.C(var9)
            .v(
               this.Q.e((byte)var11, this.J, var12) + ": #" + W(this.Q.Q()),
               (this.V + 4) * 2,
               (this.l + 3) * 2,
               this.P,
               var3,
               true
            );
         GL11.glPopMatrix();
         GL11.glPushAttrib(57344);
         boolean var23 = GL11.glIsEnabled(3042);
         boolean var24 = GL11.glIsEnabled(3553);
         int var25 = GL11.glGetInteger(2900);
         GL11.glEnable(3042);
         GL11.glBlendFunc(770, 771);
         GL11.glShadeModel(7425);
         if (var24) {
            GL11.glDisable(3553);
         }

         GL11.glBegin(7);
         int var26 = Math.max(128, var22);

         for (int var27 = 0; var27 < var26; var27++) {
            float var28 = (float)var27 / var26;
            float var29 = (float)(var27 + 1) / var26;
            int var30 = Color.HSBtoRGB(var28, 1.0F, 1.0F);
            int var31 = Color.HSBtoRGB(var29, 1.0F, 1.0F);
            G(var30);
            GL11.glVertex2f(var19 + var28 * var22, var20);
            GL11.glVertex2f(var19 + var28 * var22, var20 + 4);
            G(var31);
            GL11.glVertex2f(var19 + var29 * var22, var20 + 4);
            GL11.glVertex2f(var19 + var29 * var22, var20);
         }

         GL11.glEnd();
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         if (var24) {
            GL11.glEnable(3553);
         }

         GL11.glShadeModel(var25);
         if (!var23) {
            GL11.glDisable(3042);
         }

         GL11.glPopAttrib();
         int var32 = Q(this.Q.Q());
         float[] var33 = Color.RGBtoHSB(
            var32 >> 16 & 255,
            var32 >> 8 & 255,
            var32 & 255,
            null
         );
         int var34 = var19 + Math.round(var33[0] * (var22 - 1));
         int var35 = a(var32,-1, 0.35F);
         Expo.util.render.RenderUtil.j(var34 - 3, var20 - 2, var34 + 3, var20 + 4 + 2, 2.0F, var14, -16777216 | var35);
         Expo.util.render.RenderUtil.j(var34 - 2, var20 - 1, var34 + 2, var20 + 4 + 1, 2.0F, var14, -16777216 | var32);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      }
   }

   public void f(int var1, int var2, int var3, int var4, short var5, int var6) {
      this.c = false;
   }

   private void f(ColorSetting var1, RavenModuleRow var2, int var3) {
      this.Q = var1;
      this.J = var2.R;
      this.G = var3;
   }

   public void W(long var1) {
      this.c = false;
   }

   private static String W(String var0) {
      var0 = var0 == null ? "" : var0.trim();
      return var0.startsWith("#") ? var0.substring(1).toUpperCase() : var0.toUpperCase();
   }

   public Setting f() {
      return this.Q;
   }

   private static void G(int var0) {
      GL11.glColor3f(
         (var0 >> 16 & 255) / 255.0F,
         (var0 >> 8 & 255) / 255.0F,
         (var0 & 255) / 255.0F
      );
   }

   static {
      d = 34400892697301L;
   }

   public RavenColorRow(ColorSetting var1, RavenModuleRow var2, int var3, long var4) {
      super((var2));
      this.c = false;
      this.f(var1, var2, var3);
   }

   public void i(int var1, int var2, int var3, byte var4) {
      this.G = var1;
   }

   public void V(long var1, int var3, int var4) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      if (this.O.k && this.c) {
         int var7 = this.O.O.t();
         int var8 = this.V + 4;
         int var9 = var7 - 8;
         int var10 = Math.max(var8, Math.min(var3, var8 + var9 - 1));
         float var11 = (float)(var10 - var8) / (var9 - 1);
         int var12 = Color.HSBtoRGB(var11, 1.0F, 1.0F) & 16777215;
         this.Q.e(L(var12));
      }
   }

   public int E(long var1) {
      return 16;
   }

   private static String L(int var0) {
      return String.format("%06X", var0 & 16777215);
   }
}
