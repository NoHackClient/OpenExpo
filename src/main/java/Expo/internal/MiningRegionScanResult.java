package Expo.internal;

import Expo.enums.MiningRegionState;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.util.BlockPos;











public final class MiningRegionScanResult {
   public List<BlockPos> P;
   public MiningRegionState Z;
   public List<BlockPos> O;
   public MiningRegionState N;
   public MiningRegionState j;
   public List<BlockPos> b;
   public List<BlockPos> D = new ArrayList<>();
   public MiningRegionState L;

   public MiningRegionScanResult() {
      this.b = new ArrayList<>();
      this.P = new ArrayList<>();
      this.O = new ArrayList<>();
   }
}
