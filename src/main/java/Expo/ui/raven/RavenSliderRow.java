package Expo.ui.raven;

import Expo.module.Module;
import Expo.module.impl.configuration.Theme;
import Expo.setting.Setting;
import Expo.setting.settings.NumberSetting;
import Expo.setting.settings.PercentageSetting;
import Expo.util.ClientUtil;
import Expo.util.render.RenderUtil;
import java.util.Map;
import org.lwjgl.opengl.GL11;

public class RavenSliderRow extends AbstractRavenSettingRow {
   private static long[] j;
   private PercentageSetting v;
   private int z;
   private static Map i;
   private Module l;
   private int J;
   private double I;
   private int Y;
   private static String[] f;
   private NumberSetting n;
   private boolean X;
   private static long c;

   public void f(int var1, int var2, int var3, int var4, short var5, int var6) {
      this.X = ((0 & 1) != 0);
   }

   public RavenSliderRow(short var1, PercentageSetting var2, RavenModuleRow var3, char var4, int var5, int var6) {
      super((var3));
      long var7 = ((long)var1 << 48 | (long)var4 << 48 >>> 16 | (long)var6 << 32 >>> 32) ^ c;
      this.X = ((0 & 1) != 0);
      this.Q(var2, var3, var5);
   }

   public boolean U( int var3, int var4) {
      return var3 > this.J && var3 < this.J + this.O.O.t() / 2 + 1 && var4 > this.Y && var4 < this.Y + 16;
   }

   public void U(long var1) {
      long var3 = var1 ^ 11699446097668L;
      long var5 = var1 ^ 134457311824031L;
      long var7 = var1 ^ 68628560147625L;
      int var9 = (int)((var1 ^ 58246301632428L) >>> 56);
      long var10 = (var1 ^ 58246301632428L) << 8 >>> 8;
      long var12 = var1 ^ 98782596732208L;
      RenderUtil.j(
         this.O.O.X() + 4,
         this.O.O.T() + this.z + 11,
         this.O.O.X() + 4 + this.O.O.t() - 8,
         this.O.O.T() + this.z + 15,
         3.0F,
         var12,
         -12302777
      );
      int var14 = this.O.O.X() + 4;
      int var15 = this.O.O.X() + 4 + (int)this.I;
      if (var15 - var14 > 84) {
         var15 = var14 + 84;
      }

      RenderUtil.j(
         var14,
         this.O.O.T() + this.z + 11,
         var15,
         this.O.O.T() + this.z + 15,
         3.0F,
         var12,
         Theme.S(Theme.offset.L(), var5)
      );
      GL11.glPushMatrix();
      GL11.glScaled(0.5, 0.5, 0.5);
      String var16;
      if (this.n != null) {
         float var17 = this.n.L();
         var16 = this.n.e((byte)var9, this.l, var10).replaceAll("-", " ")
            + ": "
            + (ClientUtil.I(var17) ? (int)var17 + "" : String.valueOf(var17));
      } else {
         int var18 = this.v.k();
         var16 = this.v.e((byte)var9, this.l, var10).replaceAll("-", " ") + ": " + var18 + "%";
      }

      this.C(var7).v(var16, (int)((this.O.O.X() + 4) * 2.0F), (int)((this.O.O.T() + this.z + 3) * 2.0F), this.P, var3, true);
      GL11.glPopMatrix();
   }

   private void H(NumberSetting var1, RavenModuleRow var2, int var3) {
      this.v = null;
      this.n = var1;
      this.l = var2.R;
      this.J = var2.O.X() + var2.O.t();
      this.Y = var2.O.T() + var2.L;
      this.z = var3;
   }

   public void V(long var1, int var3, int var4) {
      long var5 = (var1 ^ 7526129516048L) >>> 16;
      int var7 = (int)((var1 ^ 7526129516048L) << 48 >>> 48);
      int var8 = (int)((var1 ^ 96587305911125L) >>> 56);
      long var9 = (var1 ^ 96587305911125L) << 8 >>> 8;
      int var11 = (int)((var1 ^ 126683911623005L) >>> 32);
      long var12 = (var1 ^ 126683911623005L) << 32 >>> 32;
      this.Y = this.O.O.T() + this.z;
      this.J = this.O.O.X();
      float var14 = Math.min(this.O.O.t() - 8, Math.max(0, var3 - this.J));
      if (this.n != null) {
         this.I = (double)(this.O.O.t() - 8) * (this.n.L() - this.n.i()) / (this.n.F() - this.n.i());
      } else {
         this.I = (double)(this.O.O.t() - 8) * this.v.k() / 100.0;
      }

      if (this.X) {
         if (var14 == 0.0) {
            if (this.n != null) {
               this.n.o((byte)var8, var9, this.n.i());
            } else {
               this.v.b(var11, var12, 0);
            }

            this.O.O.M(var5, (char)var7);
         } else {
            if (this.n != null) {
               float var15 = var14 / (this.O.O.t() - 8.0F) * (this.n.F() - this.n.i()) + this.n.i();
               this.n.o((byte)var8, var9, var15);
            } else {
               float var16 = var14 / (this.O.O.t() - 8.0F) * 100.0F;
               this.v.b(var11, var12, (int)var16);
            }

            this.O.O.M(var5, (char)var7);
         }
      }
   }

   public Setting f() {
      return this.n != null ? this.n : this.v;
   }

   static {
      c = 61800043307808L;
   }

   public boolean N(int var1, int var4) {
      return var1 > this.J + this.O.O.t() / 2 && var1 < this.J + this.O.O.t() && var4 > this.Y && var4 < this.Y + 16;
   }

   public void W(long var1) {
      this.X = ((0 & 1) != 0);
   }

   public RavenSliderRow(NumberSetting var1, long var2, RavenModuleRow var4, int var5) {
      super(var4);
      var2 = c ^ var2;
      this.X = ((0 & 1) != 0);
      this.H(var1, var4, var5);
   }

   public void r(char var1, int var2, int var3, int var4, long var5) {
      if (this.f() != null) {
         if (this.U( var2, var3) && var4 == 0 && this.O.k) {
            this.X = ((1 & 1) != 0);
         }

         if (this.N(var2, var3) && var4 == 0 && this.O.k) {
            this.X = ((1 & 1) != 0);
         }
      }
   }

   private void Q(PercentageSetting var1, RavenModuleRow var2, int var3) {
      this.n = null;
      this.v = var1;
      this.l = var2.R;
      this.J = var2.O.X() + var2.O.t();
      this.Y = var2.O.T() + var2.L;
      this.z = var3;
   }

   public void i(int var1, int var2, int var3, byte var4) {
      this.z = var1;
   }
}
