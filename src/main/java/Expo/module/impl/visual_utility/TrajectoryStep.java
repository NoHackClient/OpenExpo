package Expo.module.impl.visual_utility;

import Expo.internal.synthetic.TrajectoriesSwitchMapAxis;
import net.minecraft.util.Vec3;











public class TrajectoryStep {
   private final double q;
   private final double J;
   private final double b;
   private Vec3 G;
   private final Vec3 I;

   public TrajectoryStep(Vec3 var1, Vec3 var2, double var3, double var5, double var7, TrajectoriesSwitchMapAxis var9) {
      this(var1, var2, var3, var5, var7);
   }

   private TrajectoryStep(Vec3 var1, Vec3 var2, double var3, double var5, double var7) {
      this.I = var1;
      this.G = var2;
      this.J = var3;
      this.b = var5;
      this.q = var7;
   }

   public static double n(TrajectoryStep var0) {
      return var0.q;
   }

   public static double L(TrajectoryStep var0) {
      return var0.J;
   }

   public static Vec3 g(TrajectoryStep var0) {
      return var0.G;
   }

   public static Vec3 P(TrajectoryStep var0) {
      return var0.I;
   }

   public static double x(TrajectoryStep var0) {
      return var0.b;
   }

   public static Vec3 y(TrajectoryStep var0, Vec3 var1) {
      return var0.G = var1;
   }
}
