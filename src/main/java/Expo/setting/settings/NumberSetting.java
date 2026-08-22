package Expo.setting.settings;

import Expo.setting.Setting;
import Expo.util.MathUtil;


public class NumberSetting extends Setting {
   private float z;
   private final float Z;
   private final float l;
   private static long a;
   private final float o;

   private int b(float var1, long var2) {
      String var4 = Float.toString(var1);
      int var5 = var4.indexOf(46);
      if (var5 == -1) {
         return 1;
      }

      int var6 = var4.length() - var5 - 1;
      int var7 = 1;

      for (int var8 = 0; var8 < var6; var8++) {
         var7 *= 10;
      }

      return var7;
   }

   static {

      a = 96988644486315L;
   }

   public float L() {
      return this.z;
   }

   public float U() {
      return this.Z;
   }

   // add code
   public NumberSetting(String var1, float var2, float var3, float var4, float var5) {
      this.q = var1;
      this.o = var3;
      this.l = var4;
      this.Z = var5;
      this.z = var2;
   }

   public NumberSetting(String var1, float var2, float var3, long var4, float var6, float var7) {
      var4 = a ^ var4;
      int var8 = (int)((var4 ^ 101023858567445L) >>> 56);
      long var9 = (var4 ^ 101023858567445L) << 8 >>> 8;
      if (var7 <= 0.0F) {
         throw new IllegalArgumentException("increment must be > 0");
      }

      if (var6 < var3) {
         throw new IllegalArgumentException("maxValue < minValue");
      }

      this.q = var1;
      this.o = var3;
      this.l = var6;
      this.Z = var7;
      this.o((byte)var8, var9, var2);
   }

   public float F() {
      return this.l;
   }

   private float E(long var1, float var3) {
      double var6 = MathUtil.q(var3, this.o, this.l);
      int var8 = this.b(this.Z,0L);
      long var9 = Math.round(this.o * var8);
      long var11 = Math.round(this.l * var8);
      long var13 = Math.round(this.Z * var8);
      long var15 = Math.round(var6 * var8);
      long var17 = Math.round((double)(var15 - var9) / var13);
      long var19 = var9 + var17 * var13;
      if (var19 < var9) {
         var19 = var9;
      }

      if (var19 > var11) {
         var19 = var11;
      }

      return (float)var19 / var8;
   }

   public float i() {
      return this.o;
   }


   public void o(byte var1, long var2, float var4) {
      this.z = this.E(0L, var4);
   }

}
