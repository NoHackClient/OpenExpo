package Expo.module.impl.visual_utility;

import Expo.internal.synthetic.TrajectoriesSwitchMapAxis;
import net.minecraft.entity.Entity;
import net.minecraft.util.MovingObjectPosition;

public class TrajectoryEntityHit {
   private final MovingObjectPosition I;
   private final Entity k;

   public static Entity d(TrajectoryEntityHit var0) {
      return var0.k;
   }

   public static MovingObjectPosition r(TrajectoryEntityHit var0) {
      return var0.I;
   }

   private TrajectoryEntityHit(Entity var1, MovingObjectPosition var2) {
      this.k = var1;
      this.I = var2;
   }

   public TrajectoryEntityHit(Entity var1, MovingObjectPosition var2, TrajectoriesSwitchMapAxis var3) {
      this(var1, var2);
   }
}
