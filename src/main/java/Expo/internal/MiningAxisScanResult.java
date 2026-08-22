package Expo.internal;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.util.BlockPos;


public final class MiningAxisScanResult {
   private static long a;
   public List<BlockPos> z;
   public int S;
   public int Z;
   public int c;
   public BlockPos x;
   public List<BlockPos> n;
   public int t;
   public BlockPos C;

   public MiningAxisScanResult(long var1) {
      var1 = a ^ var1;
      this.n = new ArrayList<>();
      this.z = new ArrayList<>();
      this.S = -1;
      this.c = -1;
   }


   static {

      a = 55070367350149L;
   }

}
