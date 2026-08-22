package Expo.util;

import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class PlacementTarget {
   public final BlockPos q;
   public final boolean o;
   public final EnumFacing Z;

   public PlacementTarget(BlockPos var1, EnumFacing var2, boolean var3) {
      this.q = var1;
      this.Z = var2;
      this.o = var2 == EnumFacing.DOWN || var3;
   }
}
