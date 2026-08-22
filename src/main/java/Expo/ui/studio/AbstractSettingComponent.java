package Expo.ui.studio;

import Expo.module.Module;
import Expo.setting.Setting;
import Expo.util.MinecraftRef;
import Expo.util.render.FontUtil;
import java.awt.Color;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import net.minecraft.client.Minecraft;

public abstract class AbstractSettingComponent<T extends Setting> {
   private static long b;
   protected float n;
   protected float J;
   protected float C;
   protected final Module S;
   protected final StudioClickGuiScreen M;
   protected float R;
   protected final Minecraft l;
   protected final StudioModuleFrame d;
   protected final T O;

   protected AbstractSettingComponent(StudioClickGuiScreen var1, StudioModuleFrame var2, Setting var3, long var4) {
      var4 = b ^ var4;
      int var6 = (int)((var4 ^ 3527305808268L) >>> 56);
      long var7 = (var4 ^ 3527305808268L) << 8 >>> 8;
      this.l = MinecraftRef.c((byte)var6,0L);
      this.M = var1;
      this.d = var2;
      this.S = var2.E();
      this.O = (T)var3;
   }

   public T y() {
      return this.O;
   }

   public abstract boolean V(long var1, float var3, float var4, int var5) throws UnsupportedEncodingException, Throwable, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException;

   protected boolean G(float var1, float var2, float var3, float var4, float var5, float var6) {
      return var1 >= var3 && var1 <= var3 + var5 && var2 >= var4 && var2 <= var4 + var6;
   }

   protected boolean I(float var1, float var2) {
      return this.G(var1, var2, this.n, this.J, this.C, this.O());
   }

   public float i(float var1) {
      return this.O() + this.L(var1);
   }

   public void t(char var1) {
   }

   public float L(float var1) {
      return 0.0F;
   }

   public void k(long var1) {
   }

   public abstract void Z(int var1, float var2, char var3, float var4, float var5, float var6, short var7, CategoryPalette var8) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException;

   protected int F(CategoryPalette var1, char var2, short var3, float var4, float var5, int var6) {
      long var7 = ((long)var2 << 48 | (long)var3 << 48 >>> 16 | (long)var6 << 32 >>> 32) ^ b;
      int var9 = (int)((var7 ^ 87884672217341L) >>> 32);
      int var10 = (int)((var7 ^ 87884672217341L) << 32 >>> 48);
      Color var12 = new Color(
         16, 20, 31, 218
      );
      Color var13 = FontUtil.Y(
         new Color(
            36,
            44,
            61,
            224
         ),
         var1.D,
         0.15F
      );
      return FontUtil.a(var9, var10, FontUtil.Y(var12, var13, var4), var5);
   }

   public void A(float var1, float var2) {
   }

   public abstract float O();

   public void H(float var1, float var2, float var3) {
      this.n = var1;
      this.J = var2;
      this.C = var3;
      this.R = this.O();
   }

   static {
      b = 58681122439128L;
   }
}
