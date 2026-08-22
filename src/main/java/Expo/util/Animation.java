package Expo.util;

public class Animation {
   private float T;
   private float Z;
   private float f;

   public void U(float var1) {
      this.Z = var1;
      this.T = var1;
      this.f = var1;
   }

   public Animation(float var1) {
      this.U(var1);
   }

   public float b(float var1) {
      return MathUtil.k(this.T, this.Z, var1);
   }

   public float r() {
      return this.f;
   }

   public void y(float var1, float var2) {
      this.Z = this.T;
      float var3 = MathUtil.q(var1 * var2, 0.0F, 1.0F);
      this.T = this.T + (this.f - this.T) * var3;
      if (Math.abs(this.f - this.T) < 5.0E-4F) {
         this.T = this.f;
      }
   }

   public void d(float var1) {
      this.f = var1;
   }

   public float E() {
      return this.T;
   }
}
