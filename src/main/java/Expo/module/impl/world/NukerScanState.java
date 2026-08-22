package Expo.module.impl.world;

import Expo.internal.synthetic.NukerCtorMarker;








public class NukerScanState {
   private final int F;
   private final int B;
   private final boolean W;
   private final int X;
   private final int a;

   public NukerScanState(boolean var1, int var2, int var3, int var4, int var5, NukerCtorMarker var6) {
      this(var1, var2, var3, var4, var5);
   }

   public static int n(NukerScanState var0) {
      return var0.a;
   }

   public static int m(NukerScanState var0) {
      return var0.X;
   }

   public static boolean w(NukerScanState var0) {
      return var0.W;
   }

   public static int x(NukerScanState var0) {
      return var0.B;
   }

   private NukerScanState(boolean var1, int var2, int var3, int var4, int var5) {
      this.W = var1;
      this.a = var2;
      this.B = var3;
      this.X = var4;
      this.F = var5;
   }

   public static int Y(NukerScanState var0) {
      return var0.F;
   }
}
