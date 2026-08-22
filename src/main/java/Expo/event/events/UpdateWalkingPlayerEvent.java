package Expo.event.events;

import Expo.event.Event;

public class UpdateWalkingPlayerEvent extends Event {
   private boolean E;
   private double B;
   private boolean g;
   private boolean t;
   private float o;
   private static final long a = 128974226114187L;
   private double G;
   private double J;
   private boolean z;
   private float V;

   public void b(boolean var1) {
      this.g = var1;
   }

   public void u(boolean var1) {
      this.z = var1;
   }

   public void l(float var1) {
      this.V = var1;
   }

   public float P() {
      return this.V;
   }

   public void i(boolean var1) {
      this.E = var1;
   }

   public boolean d() {
      return this.g;
   }

   public void O(double var1) {
      this.G = var1;
   }

   public double s() {
      return this.G;
   }

   public void N(double var1) {
      this.B = var1;
   }

   public boolean f() {
      return this.E;
   }

   public double F() {
      return this.B;
   }

   public void E(float var1) {
      this.o = var1;
   }

   public void L(double var1) {
      this.J = var1;
   }

   public float O() {
      return this.o;
   }

   public double U() {
      return this.J;
   }

   public void X(boolean var1) {
      this.t = var1;
   }

   public UpdateWalkingPlayerEvent(double var1, double var3, double var5, float var7, float var8, boolean var9, boolean var10, boolean var11, boolean var14) {
      super();
      this.B = var1;
      this.G = var3;
      this.J = var5;
      this.o = var7;
      this.V = var8;
      this.E = var9;
      this.z = var10;
      this.g = var11;
      this.t = var14;
   }

   public boolean V() {
      return this.z;
   }

   public boolean I() {
      return this.t;
   }}
