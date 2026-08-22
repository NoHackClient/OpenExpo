package Expo.module.impl.visual_utility;

import Expo.internal.synthetic.TrajectoriesSwitchMapAxis;








public class TrajectoriesViewerOffset {
   private final double K;
   private final double L;
   private final double s;

   public static double L(TrajectoriesViewerOffset var0) {
      return var0.s;
   }

   public static double q(TrajectoriesViewerOffset var0) {
      return var0.K;
   }

   private TrajectoriesViewerOffset(double var1, double var3, double var5) {
      this.K = var1;
      this.s = var3;
      this.L = var5;
   }

   public static double p(TrajectoriesViewerOffset var0) {
      return var0.L;
   }

   public TrajectoriesViewerOffset(double var1, double var3, double var5, TrajectoriesSwitchMapAxis var7) {
      this(var1, var3, var5);
   }
}
