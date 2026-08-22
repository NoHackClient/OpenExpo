package Expo.ui;

import Expo.module.impl.configuration.Notifications;
import Expo.util.MathUtil;










public class NotificationToast {
   private final float M;
   private final String B;
   private float e = Float.NaN;
   private final float a;
   private final long E;
   private final float n;
   private final float V;
   private final float Q;

   public static float c(NotificationToast var0) {
      return var0.Q;
   }

   public static float e(NotificationToast var0) {
      return var0.M;
   }

   public static String m(NotificationToast var0) {
      return var0.B;
   }

   public float i(long var1, float var3, float var4, float var5) {
      float var6 = (float)(var1 - this.E);
      if (var6 <= var3) {
         return Notifications.u(var6 / var3);
      }

      float var7 = var3 + var4;
      if (var6 <= var7) {
         return 1.0F;
      }

      float var8 = MathUtil.q((var6 - var7) / Math.max(1.0F, var5), 0.0F, 1.0F);
      return 1.0F - Notifications.R(var8);
   }

   public static float O(NotificationToast var0) {
      return var0.a;
   }

   public static float R(NotificationToast var0) {
      return var0.e;
   }

   public float T() {
      float var1 = Notifications.Z();
      float var2 = (float)(System.currentTimeMillis() - this.E);
      if (var2 <= var1) {
         return 1.0F;
      }

      float var3 = var2 - var1;
      return 1.0F - MathUtil.q(var3 / Math.max(1.0F, this.V), 0.0F, 1.0F);
   }

   public NotificationToast(String var1, long var2, float var4, float var5, float var6, float var7, float var8) {
      this.B = var1;
      this.E = var2;
      this.M = var4;
      this.a = var5;
      this.n = var6;
      this.Q = var7;
      this.V = var8;
   }

   public static float f(NotificationToast var0) {
      return var0.n;
   }

   public boolean c(long var1, float var3, float var4, float var5) {
      return (float)(var1 - this.E) >= var3 + var4 + var5;
   }

   public static float K(NotificationToast var0, float var1) {
      return var0.e = var1;
   }
}
