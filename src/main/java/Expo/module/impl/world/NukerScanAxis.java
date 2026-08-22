package Expo.module.impl.world;

import Expo.internal.synthetic.NukerCtorMarker;

public class NukerScanAxis {
   private final int J;
   private final int S;
   private final int K;
   private final int x;

   public static int y(NukerScanAxis var0) {
      return var0.x;
   }

   public static int a(NukerScanAxis var0) {
      return var0.J;
   }

   public static int T(NukerScanAxis var0) {
      return var0.S;
   }

   public static int Y(NukerScanAxis var0) {
      return var0.K;
   }

   private NukerScanAxis(int var1, int var2, int var3, int var4) {
      this.S = var1;
      this.K = var2;
      this.J = var3;
      this.x = var4;
   }

   public NukerScanAxis(int var1, int var2, int var3, int var4, NukerCtorMarker var5) {
      this(var1, var2, var3, var4);
   }
}
