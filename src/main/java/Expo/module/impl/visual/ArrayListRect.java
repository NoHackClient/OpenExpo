package Expo.module.impl.visual;

import Expo.internal.synthetic.ArrayListModuleCtorMarker;

public class ArrayListRect {
   private final float A;
   private final float n;
   private final float o;
   private final float W;

   private ArrayListRect(float var1, float var2, float var3, float var4) {
      this.o = var1;
      this.n = var2;
      this.W = var3;
      this.A = var4;
   }

   public static float H(ArrayListRect var0) {
      return var0.o;
   }

   public static float N(ArrayListRect var0) {
      return var0.W;
   }

   public ArrayListRect(float var1, float var2, float var3, float var4, ArrayListModuleCtorMarker var5) {
      this(var1, var2, var3, var4);
   }

   public static float J(ArrayListRect var0) {
      return var0.A;
   }

   public static float e(ArrayListRect var0) {
      return var0.n;
   }
}
