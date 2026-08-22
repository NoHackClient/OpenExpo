package Expo.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.util.Vec3;

public class MathUtil {
   private static long b;
   private static long a;
   private static Random N;

   public static float c(float var0, float var1) {
      return var1 == 0.0F ? var0 : var0 * (0.5F + 0.5F * (1.0F - Math.max(0.0F, Math.min(1.0F, var1 + h(-0.1F, 0.1F)))));
   }

   public static float q(float var0, float var1, float var2) {
      return var0 < var1 ? var1 : Math.min(var0, var2);
   }

   public static long Y(double var0, double var2) {
      double var4;
      if (!(var2 > var0)) {
         var4 = var2;
      } else {
         var4 = ThreadLocalRandom.current().nextDouble(var0, var2);
      }

      return (long)var4;
   }

   public static double x(double var0) {
      return Math.round(var0 * 2.0) / 2.0;
   }

   public static Vec3 Z(Vec3 var0, Vec3 var1, float var2) {
      return new Vec3(
         (float)j(var0.xCoord, var1.xCoord, var2),
         (float)j(var0.yCoord, var1.yCoord, var2),
         (float)j(var0.zCoord, var1.zCoord, var2)
      );
   }

   public static boolean B() {
      return ThreadLocalRandom.current().nextBoolean();
   }

   public static double Z(double var0, double var2) {
      return Math.abs(var2 - var0);
   }

   public static float s(float var0, float var1) {
      var1 = Math.max(0.0F, Math.min(180.0F, var1));
      if (var0 > var1) {
         var0 = var1;
      } else if (var0 < -var1) {
         var0 = -var1;
      }

      return var0;
   }

   public static float T(float var0, float var1, float var2) {
      var0 %= 360.0F;
      if (var0 >= var2) {
         var0 -= 360.0F;
      }

      if (var0 < var1) {
         var0 += 360.0F;
      }

      return var0;
   }

   public static double I(double var0, double var2, double var4) {
      return (var2 - var0) * var4 + var0;
   }

   public static boolean Q(int var0, long var1) {
      return ThreadLocalRandom.current().nextInt((int)b) < var0;
   }

   public static float k(float var0, float var1, float var2) {
      return (var0 - var1) * var2 + var1;
   }

   public static float h(float var0, float var1) {
      return N.nextFloat() * (var1 - var0) + var0;
   }

   public static double V(double var0, long var2, double var4) {
      var2 = a ^ var2;
      return j(var0, var4, ClientUtil.H(0L));
   }

   public static long e(double var0, double var2) {
      double var4;
      if (!(var2 > var0)) {
         var4 = var2;
      } else {
         var4 = ThreadLocalRandom.current().nextDouble(var0, var2);
      }

      return (long)(1000.0 / var4);
   }

   public static double j(double var0, double var2, float var4) {
      return var4 * (var2 - var0) + var0;
   }

   public static double R(double var0, double var2, double var4) {
      return var0 < var2 ? var2 : Math.min(var0, var4);
   }

   public static float r(float var0, float var1, float var2) {
      return (var1 - var0) * var2 + var0;
   }

   public static float M(float var0, float var1) {
      float var2 = var1 - var0;

      while (var2 < -180.0F) {
         var2 += 360.0F;
      }

      while (var2 > 180.0F) {
         var2 -= 360.0F;
      }

      return var2;
   }

   public static int k(int var0, int var1, int var2) {
      return var0 < var1 ? var1 : Math.min(var0, var2);
   }

   public static double h(double var0, double var2, double var4) {
      return (var0 - var2) * var4 + var2;
   }

   public static float H(float var0) {
      return (float)(var0 - var0 % 0.0096F);
   }

   public static float z(float var0, float var1) {
      return Math.abs(var1 - var0);
   }

   public static double W(double var0) {
      BigDecimal var2 = new BigDecimal(var0);
      var2 = var2.setScale(1, RoundingMode.HALF_UP);
      return var2.doubleValue();
   }

   static {
      N = new Random();
      a = 54732061872882L;
      b = -8432971632028417948L;
   }
}
