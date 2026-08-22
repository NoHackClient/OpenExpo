package Expo.module.impl.visual_utility;

import Expo.internal.synthetic.FireBallPredictCtorMarker;
import net.minecraft.util.BlockPos;











public class FireBallPredictImpact {
   private final int z;
   private final BlockPos h;

   private FireBallPredictImpact(BlockPos var1, int var2) {
      this.h = var1;
      this.z = var2;
   }

   public FireBallPredictImpact(BlockPos var1, int var2, FireBallPredictCtorMarker var3) {
      this(var1, var2);
   }

   public static BlockPos m(FireBallPredictImpact var0) {
      return var0.h;
   }

   public static int M(FireBallPredictImpact var0) {
      return var0.z;
   }
}
