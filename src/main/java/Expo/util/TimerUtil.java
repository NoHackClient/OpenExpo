package Expo.util;

public class TimerUtil {
   public long I = System.currentTimeMillis();

   public void p(long var1) {
      this.I = var1;
   }

   public boolean A(double var1) {
      return this.Q((long)var1);
   }

   public boolean Q(long var1) {
      return System.currentTimeMillis() - this.I > var1;
   }

   public void F(long var1) {
      this.I = var1;
   }

   private double N(double var1, double var3) {
      double var5 = 1.0 / var3;
      return Math.round(var1 * var5) / var5;
   }

   public void W() {
      this.I = System.currentTimeMillis();
   }

   public boolean P(float var1) {
      return (float)this.N(this.p() - this.I, 50.0) >= var1;
   }

   public boolean L(long var1, boolean var3) {
      if (System.currentTimeMillis() - this.I > var1) {
         if (var3) {
            this.W();
         }

         return true;
      } else {
         return false;
      }
   }

   public long s() {
      return System.currentTimeMillis() - this.I;
   }

   public long p() {
      return System.currentTimeMillis() - this.I;
   }
}
