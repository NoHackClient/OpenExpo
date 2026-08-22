package Expo.util;

import Expo.enums.Easing;

public class Animator {
   private long P;
   private static long b;
   private long f;
   private double T;
   private static long a;
   private long H;
   private boolean U;
   private Easing D;
   private double u;
   private double C;

   public double f() {
      return this.T;
   }

   public void G(long var1) {
      this.P = var1;
   }

   public void u(boolean var1) {
      this.U = var1;
   }

   public void e(double var1) {
      this.u = var1;
   }

   public void z(double var1) {
      this.C = var1;
   }

   public void i(long var1, double var3) {
      this.P = System.currentTimeMillis();
      if (this.T != var3) {
         this.T = var3;
         this.O(0L);
      } else {
         this.U = this.P - this.H > this.f;
         if (this.U) {
            this.u = var3;
            return;
         }
      }

      double var7 = this.D.C().apply(this.L());
      if (this.u > var3) {
         this.u = this.C - (this.C - var3) * var7;
      } else {
         this.u = this.C + (var3 - this.C) * var7;
      }
   }

   public void c(long var1) {
      this.H = var1;
   }

   static {
      a = 108012701916305L;
      b = -58884813376978944L;
   }

   public long I() {
      return this.f;
   }

   public Animator(Easing var1, long var2) {
      this.D = var1;
      this.f = System.currentTimeMillis();
      this.H = var2;
   }

   public void O(long var1) {
      this.f = System.currentTimeMillis();
      this.C = this.u;
      this.U = (b) != 0;
   }

   public long c() {
      return this.P;
   }

   public void i(Easing var1) {
      this.D = var1;
   }

   public long Y() {
      return this.H;
   }

   public double L() {
      return (double)(System.currentTimeMillis() - this.f) / this.H;
   }

   public boolean p() {
      return this.U;
   }

   public void y(long var1) {
      this.f = var1;
   }

   public void C(double var1) {
      this.T = var1;
   }

   public double y() {
      return this.C;
   }

   public Easing i() {
      return this.D;
   }

   public double Z() {
      return this.u;
   }
}
