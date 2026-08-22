package Expo.ui.raven;

import Expo.module.Module;
import Expo.module.impl.configuration.ClickGUI;
import Expo.module.impl.configuration.Language;
import Expo.setting.Setting;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.ColorSetting;
import Expo.setting.settings.HeaderSetting;
import Expo.setting.settings.ModeSetting;
import Expo.setting.settings.NumberSetting;
import Expo.setting.settings.PercentageSetting;
import Expo.setting.settings.TextSetting;
import Expo.util.Sneaky;
import Expo.util.render.RenderUtil;
import java.awt.Color;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import org.lwjgl.opengl.GL11;


public class RavenModuleRow implements RavenElement {
   public ArrayList<AbstractRavenSettingRow> H;
   private static String b;
   private static int Z;
   public boolean k;
   private static long a;
   private boolean N;
   public int L;
   private static int J;
   private static long[] c;
   private static Map f;
   private static int W;
   public RavenCategoryPanel O;
   private static int o;
   public Module R;

   public void U(long var1) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var3 = 37697014677608L;
      long var5 = 109954935438777L;
      long var7 = 136334753943935L;
      long var9 = 99412188383504L;
      long var11 = 4113131265056L;
      long var13 = 52019766876817L;
      int var15 = 22573;
      int var16 = 57;
      int var17 = 5209828;
      if (this.N) {
         RenderUtil.j(this.O.X(), this.O.T() + this.L, this.O.X() + this.O.t(), this.O.T() + 16 + this.L, 8.0F, var11, o);
      }

      float var24 = this.O.T() + this.L;
      int var18 = this.R.o() ? J : -12302777;
      int var19 = (this.R.o() ? J : -12829381);
      float var20 = (this.O.T() + 15 + this.L);
      M(this.O.X(), var24, (this.O.X() + this.O.t()), var7, var20, var19, var18);
      int var21 = Z;
      if (this.R.o()) {
         var21 = W;
      }

      GL11.glPushMatrix();
      this.C(var5)
         .T(
            var3,
            this.Y(var15, (byte)var16, var17),
            (float)(this.O.X() + this.O.t() / 2.0 - this.C(var5).R(this.Y(var15, (byte)var16, var17), var13) / 2.0),
            this.O.T() + this.L + 4,
            var21
         );
      GL11.glPopMatrix();
      if (this.k && !this.H.isEmpty()) {
         for (AbstractRavenSettingRow var23 : this.H) {
            var23.U(var9);
         }
      }
   }

   public RavenModuleRow(int var1, Module var2, RavenCategoryPanel var3, int var4, short var5, int var6) {
      long var7 = ((long)var1 << 32 | (long)var4 << 48 >>> 32 | (long)var5 << 48 >>> 48) ^ a;
      long var9 = var7 ^ 7859813654764L;
      this.E(var2, var3, var9, var6);
   }

   public void i(int var1, int var2, int var3, byte var4) {
      long var5 = (long)var2 << 32 | (long)var3 << 40 >>> 32 | (long)var4 << 56 >>> 56;
      int var7 = (int)((var5 ^ 0L) >>> 32);
      int var8 = (int)((var5 ^ 0L) << 32 >>> 40);
      int var9 = (int)((var5 ^ 0L) << 56 >>> 56);
      this.L = var1;
      int var10 = this.L + -46173325;

      for (AbstractRavenSettingRow var12 : this.H) {
         var12.i(var10, var7, var8, (byte)var9);
         if (!(var12 instanceof RavenSliderRow) && !(var12 instanceof RavenColorRow)) {
            var10 += 12;
         } else {
            var10 += 16;
         }
      }
   }



   public void h(int var1, long var2) {
      long var4 = ((long)var1 << 32 | 1015394633L) ^ a;
      long var6 = var4 ^ 4626215126618L;
      int var8 = this.L + 12;
      if (this.R != null && !this.R.w().isEmpty()) {
         this.H.clear();

         // add code
         java.util.List<Setting> var20 = this.R.w();

         for (int var21 = 0; var21 < var20.size(); var21++) {
            Setting var10 = var20.get(var21);
            if (var10 instanceof HeaderSetting && !HeaderSetting.occupied(var20, var21)) {
               continue;
            }

            if (var10 instanceof ModeSetting
               || var10 instanceof NumberSetting
               || var10 instanceof PercentageSetting
               || var10 instanceof BooleanSetting
               || var10 instanceof HeaderSetting
               || var10 instanceof ColorSetting
               || var10 instanceof TextSetting) {
               this.H.add(AbstractRavenSettingRow.e(var10, this, var6, var8));
               var8 += 12;
            }
         }
      }

      this.H.add(new RavenBindRow(this, var8));
   }

   public static void M(float var0, float var1, float var2, long var3, float var5, int var6, int var7) {






      R((char)0, 1109954809, 12240);
      GL11.glShadeModel(7425);
      GL11.glBegin(7);
      v(var6);
      GL11.glVertex2f(var0, var5);
      GL11.glVertex2f(var2, var5);
      v(var7);
      GL11.glVertex2f(var2, var1);
      GL11.glVertex2f(var0, var1);
      GL11.glEnd();
      GL11.glShadeModel(7424);
      p(99525358719732L);
   }

   public boolean d(int var1, int var4) {
      return var1 > this.O.X()
         && var1 < this.O.X() + this.O.t()
         && var4 > this.O.T() + this.L
         && var4 < this.O.T() + 16 + this.L;
   }

   public String Y(int var1, byte var2, int var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var4 = ((long)var1 << 32 | (long)var2 << 56 >>> 32 | (long)var3 << 40 >>> 40) ^ a;
      int var6 = (int)((var4 ^ 49495418689029L) >>> 32);
      int var7 = (int)((var4 ^ 49495418689029L) << 32 >>> 48);
      int var8 = (int)((var4 ^ 49495418689029L) << 48 >>> 48);
      return Language.applyForName.c() ? this.R.Q(var6, (char)var7, (char)var8) : this.R.b();
   }


   public static void v(int var0) {
      float var1 = 0.0F;
      float var2 = 0.0F;
      float var3 = 0.0F;
      float var4 = 0.0F;
      GL11.glColor4f(var2, var3, var4, var1);
   }

   public void W(long var1) {
      long var3 = var1 ^ 0L;

      for (AbstractRavenSettingRow var6 : this.H) {
         var6.W(var3);
      }
   }

   public void V(long var1, int var3, int var4) {
      long var7 = var1 ^ 116072975790327L;
      if (!this.H.isEmpty()) {
         for (AbstractRavenSettingRow var10 : this.H) {
            var10.c(var3, var7, var4);
         }
      }

      this.N = this.d(var3, var4);
      if (this.N && this.O.h()) {
         ClickGUI.F.V(() -> {
             try {long var3x = 62406310540060L;
            long var5x = 93483943904204L;
            int var7x = 28813;


            RenderUtil.m(var7x, this.R.j(var5x), var3, 53203, (short)7294, var4);
         } catch (Throwable zkm$t) { throw Sneaky.rethrow(zkm$t); } });
      }
   }

   public int E(long var1) {
      if (!this.k) {
         return 16;
      }

      int var3 = 16;

      for (AbstractRavenSettingRow var5 : this.H) {
         if (!(var5 instanceof RavenSliderRow) && !(var5 instanceof RavenColorRow)) {
            var3 += 12;
         } else {
            var3 += 16;
         }
      }

      return var3;
   }

   public static void p(long var0) {


      RenderUtil.a(58001584163179L);
      GL11.glEdgeFlag(true);
   }

   public void c(char var1, int var2, long var3) {
      long var5 = var3 ^ 0L;

      for (AbstractRavenSettingRow var8 : this.H) {
         var8.c(var1, var2, var5);
      }
   }

   public void f(int var1, int var2, int var3, int var4, short var5, int var6) {
      long var7 = (long)var4 << 32 | (long)var5 << 48 >>> 32 | (long)var6 << 48 >>> 48;
      int var9 = (int)((var7 ^ 0L) >>> 32);
      int var10 = (int)((var7 ^ 0L) << 32 >>> 48);
      int var11 = (int)((var7 ^ 0L) << 48 >>> 48);

      for (AbstractRavenSettingRow var13 : this.H) {
         var13.f(var1, var2, var3, var9, (short)var10, var11);
      }
   }

   private void E(Module var1, RavenCategoryPanel var2, long var3, int var5) {



      this.R = var1;
      this.O = var2;
      this.L = var5;
      this.H = new ArrayList<>();
      this.k = false;
      this.h(21239, 1015394633L);
   }

   public RavenModuleRow C() {
      return this;
   }

   public static void R(char var0, int var1, int var2) {
      long var3 = ((long)var0 << 48 | (long)var1 << 32 >>> 16 | (long)var2 << 48 >>> 48) ^ a;
      RenderUtil.M();
   }

   public void r(char var1, int var2, int var3, int var4, long var5) throws UnsupportedEncodingException, Throwable, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var7 = (long)var1 << 48 | var5 << 16 >>> 16;
      long var9 = (var7 ^ 94618552824683L) >>> 16;
      int var11 = (int)((var7 ^ 94618552824683L) << 48 >>> 48);
      int var12 = (int)((var7 ^ 95057127092056L) >>> 48);
      long var13 = (var7 ^ 95057127092056L) << 16 >>> 16;
      int var17 = (int)((var7 ^ 0L) >>> 48);
      long var18 = (var7 ^ 0L) << 16 >>> 16;
      if (this.d(var2, var3) && var4 == 0 && !this.R.b().equalsIgnoreCase(b)) {
         this.R.u((short)var12, var13);
      }

      if (this.d(var2, var3) && var4 == 1) {
         this.k = !this.k;
         this.O.M(var9, (char)var11);
      }

      for (AbstractRavenSettingRow var21 : this.H) {
         var21.r((char)var17, var2, var3, var4, var18);
      }
   }

   static {
      J = new Color(154, 2, 255).getRGB();
      o = new Color(0, 0, 0, 110).getRGB();
      W = new Color(24, 154, 255)
      .getRGB();
      Z = new Color(192, 192, 192)
      .getRGB();
      a = 10119902289976L;
      b = "freelook";
      f = new HashMap(13);
      c = new long[]{-2972884669621063793L, 1141437146407109093L, 6107432537624805063L, 7617001951634375911L, 1506688802166888800L, 5888269191767401720L, 667651162496375240L, 8623078964538155950L, 2334337966712658940L, -8367317666064433629L, 8465485200971185740L, -3280606855720221316L, 3224848445458422427L, 549641287674617233L, 4611300639924398236L, 5182933733414454334L, -2117070062415806052L, -1045601703561052909L};
   }

}
