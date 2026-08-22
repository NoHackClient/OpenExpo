package Expo.internal;

import Expo.enums.MiningRegionState;
import Expo.util.MinecraftRef;
import Expo.util.MiningConstants;
import Expo.util.RotationManager;
import Expo.util.render.BoxRenderer;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;

public class MiningBlockScanner {
   private static int o;
   private static List<BlockPos> v;
   private static long[] f;
   private static List<BlockPos> e;
   private static List<BlockPos> x;
   private static Map h;
   private static long H;
   private static boolean w;
   private static BlockPos A;
   private static long a;
   private static long M;
   private static Integer[] c;
   private static int L;
   private static List<BlockPos> Y;
   private static List<BlockPos> W;
   private static List<BlockPos> S;
   private static BlockPos u;
   private static long V;
   private static Map d;
   private static List<BlockPos> s;
   private static Minecraft R;
   private static long[] b;

   public static MiningRegionScanResult b(long var0, int var2) {
      long var3 = var0 ^ 106262835953758L;
      EntityPlayerSP var5 = R.thePlayer;
      MiningRegionScanResult var6 = new MiningRegionScanResult();
      double var7 = E(A(var5));
      int var9 = var7 >= 45.0 && var7 < 135.0 ? 3 : (var7 >= 135.0 && var7 < 225.0 ? 2 : (var7 >= 225.0 && var7 < 315.0 ? 1 : 0));
      int var10 = (int)Math.floor(var5.posX);
      int var11 = (int)Math.floor(var5.posY);
      int var12 = (int)Math.floor(var5.posZ);
      int[][] var13 = new int[var2][2];
      int[][] var14 = new int[var2][2];
      int[][] var15 = new int[var2][2];
      int[][] var16 = new int[var2][2];
      double var17 = Math.toRadians(A(var5));
      int var19 = (int)Math.round(-Math.sin(var17));
      int var20 = (int)Math.round(Math.cos(var17));

      for (int var21 = 1; var21 <= var2; var21++) {
         switch (var9) {
            case 0:
               var14[var21 - 1][0] = -var21;
               var14[var21 - 1][1] = 0;
               var13[var21 - 1][0] = var21;
               var13[var21 - 1][1] = 0;
               break;
            case 1:
               var14[var21 - 1][0] = 0;
               var14[var21 - 1][1] = var21;
               var13[var21 - 1][0] = 0;
               var13[var21 - 1][1] = -var21;
               break;
            case 2:
               var14[var21 - 1][0] = var21;
               var14[var21 - 1][1] = 0;
               var13[var21 - 1][0] = -var21;
               var13[var21 - 1][1] = 0;
               break;
            case 3:
            default:
               var14[var21 - 1][0] = 0;
               var14[var21 - 1][1] = -var21;
               var13[var21 - 1][0] = 0;
               var13[var21 - 1][1] = var21;
         }

         var15[var21 - 1][0] = var19 * var21;
         var15[var21 - 1][1] = var20 * var21;
         var16[var21 - 1][0] = -var19 * var21;
         var16[var21 - 1][1] = -var20 * var21;
      }

      int var44 = 0;
      int var22 = 0;
      int var23 = 0;
      int var24 = 0;
      int var25 = 0;
      int var26 = 0;
      int var27 = 0;
      int var28 = 0;
      int var29 = 0;
      int var30 = 0;
      int var31 = 0;
      int var32 = 0;

      for (int var33 = 0; var33 < var2; var33++) {
         for (int var34 = 0; var34 < 2; var34++) {
            BlockPos var35 = new BlockPos(var10 + var13[var33][0], var11 + var34, var12 + var13[var33][1]);
            Block var36 = R.theWorld.getBlockState(var35).getBlock();
            var6.D.add(var35);
            var23++;
            if (var36 == Blocks.air) {
               var44++;
            }

            if (var36.getBlockHardness(R.theWorld, var35) < 0.0F || BrokenBlockTracker.m.k(var35)) {
               var22++;
            }

            BlockPos var37 = new BlockPos(var10 + var14[var33][0], var11 + var34, var12 + var14[var33][1]);
            Block var38 = R.theWorld.getBlockState(var37).getBlock();
            var6.b.add(var37);
            var26++;
            if (var38 == Blocks.air) {
               var24++;
            }

            if (var38.getBlockHardness(R.theWorld, var37) < 0.0F || BrokenBlockTracker.m.k(var37)) {
               var25++;
            }

            BlockPos var39 = new BlockPos(var10 + var15[var33][0], var11 + var34, var12 + var15[var33][1]);
            Block var40 = R.theWorld.getBlockState(var39).getBlock();
            var6.P.add(var39);
            var29++;
            if (var40 == Blocks.air) {
               var27++;
            }

            if (var40.getBlockHardness(R.theWorld, var39) < 0.0F || BrokenBlockTracker.m.k(var39)) {
               var28++;
            }

            BlockPos var41 = new BlockPos(var10 + var16[var33][0], var11 + var34, var12 + var16[var33][1]);
            Block var42 = R.theWorld.getBlockState(var41).getBlock();
            var6.O.add(var41);
            var32++;
            if (var42 == Blocks.air) {
               var30++;
            }

            if (var42.getBlockHardness(R.theWorld, var41) < 0.0F || BrokenBlockTracker.m.k(var41)) {
               var31++;
            }
         }
      }

      var6.L = A(var44, var22, var23);
      var6.j = A(var24, var25, var26);
      var6.Z = A(var27, var28, var29);
      var6.N = A(var30, var31, var32);
      Color var45 = new Color(
         180,
         255,
         255,
         30
      );
      Color var46 = new Color(
         255,
         180,
         180,
         30
      );
      Color var47 = new Color(
         180,
         220,
         255,
         30
      );
      Color var48 = new Color(
         220,
         180,
         255,
         30
      );

      for (BlockPos var53 : var6.D) {
         BoxRenderer.p(var53, var3, var45);
      }

      for (BlockPos var54 : var6.b) {
         BoxRenderer.p(var54, var3, var46);
      }

      for (BlockPos var55 : var6.P) {
         BoxRenderer.p(var55, var3, var47);
      }

      for (BlockPos var56 : var6.O) {
         BoxRenderer.p(var56, var3, var48);
      }

      S = new ArrayList<>(var6.D);
      v = new ArrayList<>(var6.b);
      e = new ArrayList<>(var6.P);
      x = new ArrayList<>(var6.O);
      H = System.currentTimeMillis() + 10000L;
      return var6;
   }

   static {
      a = 333798174003L;
      V = 10000L;
      Y = new ArrayList<>();
      s = new ArrayList<>();
      W = new ArrayList<>();
      M = 0L;
      w = ((0 & 1) != 0);
      S = new ArrayList<>();
      v = new ArrayList<>();
      e = new ArrayList<>();
      x = new ArrayList<>();
      H = 0L;
      R = MinecraftRef.c((byte)0, 0L);
      d = new HashMap(13);
      b = new long[]{-4128954116085921191L, 9127308813734247957L, -7795866038483338044L, 161050512326738911L, -4456774085678627344L, -532509361263648783L, -5079127491705702146L, -2882331713467457997L, -1314151032979773530L, 7849170061990849934L, -2554110990526535005L, 6276730102795876741L, -6135715365130650753L, -1417103858064828188L, -5336891743343270917L, 7864041711030241902L, -7779025978935804739L, -3943364298098078564L, 638442570281926718L, 7958267467101948319L, 3737354319034995103L, 5452313435816621224L, -5192469020445928928L, 8414174073720189878L};
      c = new Integer[24];
      L = 8;
      o = 128;
      h = new HashMap(13);
      f = new long[]{5420028875466184167L, 8843669655198616441L, -718371891521002907L};
   }

   private static double N(double var0, double var2, double var4, BlockPos var6) {
      double var7 = var6.getX() + 0.5 - var0;
      double var9 = var6.getZ() + 0.5 - var2;
      double var11 = Math.toDegrees(Math.atan2(var9, var7)) - 90.0;
      return Math.abs(U(var11 - var4));
   }

   private static boolean j(Block var0) {
      return var0 == Blocks.iron_ore || var0 == Blocks.coal_ore;
   }

   private static boolean B(Block var0, BlockPos var1, Set<BlockPos> var2, boolean var3) {
      if (BrokenBlockTracker.m.k(var1)) {
         return false;
      } else if (var0 == Blocks.stone) {
         return true;
      } else if (var3 && j(var0)) {
         return true;
      } else {
         return !l(var0) ? false : !MiningConstants.k || var2.contains(var1);
      }
   }

   public static void j(long var0) {
      if (System.currentTimeMillis() <= M) {
         Color var4 = new Color(0, 0, 128, 180);
         Color var5 = new Color(128, 0, 0, 180);

         for (BlockPos var7 : s) {
            BoxRenderer.I( var7, var4);
         }

         for (BlockPos var10 : W) {
            BoxRenderer.I( var10, var5);
         }

         if (A != null) {
            BoxRenderer.I( A, new Color(0, 0, 0, 200));
         }

         if (u != null) {
            BoxRenderer.I( u, new Color(80, 40, 0, 200));
         }
      }
   }

   private static double U(double var0) {
      while (var0 > 180.0) {
         var0 -= 360.0;
      }

      while (var0 < -180.0) {
         var0 += 360.0;
      }

      return var0;
   }

   public static List<BlockPos> W() {
      return Y;
   }

   private static float A(EntityPlayerSP var0) {
      if (var0 == null) {
         return 0.0F;
      } else {
         return MiningEngine.uq.h() ? RotationManager.r : var0.rotationYaw;
      }
   }

   private static MiningRegionState A(int var0, int var1, int var2) {
      if (var1 > 0) {
         return MiningRegionState.FILLED_WITH_UNBREAKABLE;
      } else if (var0 == var2) {
         return MiningRegionState.EMPTY;
      } else {
         return var0 >= 2 ? MiningRegionState.PARTIALLY_EMPTY : MiningRegionState.FILLED;
      }
   }

   public static void F(int var0, int var1, int var2) {
      long var3 = ((long)var0 << 32 | (long)var1 << 48 >>> 32 | (long)var2 << 48 >>> 48) ^ a;
      Y.clear();
      w = (((a(175, 4831160014666523328L ^ var3)) & 1) != 0);
   }

   public static void r( Set var2, boolean var3, boolean var4) {
      Y.clear();
      EntityPlayerSP var5 = R.thePlayer;
      WorldClient var6 = R.theWorld;
      double var7 = var5.posX;
      double var9 = var5.posY;
      double var11 = var5.posZ;
      double var13 = E(A(var5));
      int var15 = (int)Math.floor(var7);
      int var16 = (int)Math.floor(var9);
      int var17 = (int)Math.floor(var11);
      ArrayList<BlockPos> var18 = new ArrayList<>();

      for (int var19 = -8; var19 <= 8; var19++) {
         for (int var20 = -8; var20 <= 8; var20++) {
            for (int var21 = 0; var21 <= 1; var21++) {
               BlockPos var22 = new BlockPos(var15 + var19, var16 + var21, var17 + var20);
               Block var23 = var6.getBlockState(var22).getBlock();
               if (B(var23, var22, var2, var3)) {
                  var18.add(var22);
               }
            }
         }
      }

      var18.sort((var11x, var12) -> {
         Block var13x = var6.getBlockState(var11x).getBlock();
         Block var14 = var6.getBlockState(var12).getBlock();
         int var15x = B(var13x, var3, var4);
         int var16x = B(var14, var3, var4);
         if (var15x != var16x) {
            return Integer.compare(var15x, var16x);
         }

         double var17x = N(var7, var11, var13, var11x);
         double var19x = N(var7, var11, var13, var12);
         int var21x = Double.compare(var17x, var19x);
         if (var21x != 0) {
            return var21x;
         }

         double var22x = var11x.distanceSq(var7, var9, var11);
         double var24x = var12.distanceSq(var7, var9, var11);
         return Double.compare(var22x, var24x);
      });

      for (int var25 = 0; var25 < Math.min(128, var18.size()); var25++) {
         Y.add((BlockPos)var18.get(var25));
      }
   }

   private static int a(int var0, long var1) {
      int var3 = var0 ^ (int)(var1 & 32767L) ^ 13455;
      if (c[var3] == null) {
         byte[] var4 = new byte[]{
            (byte)(var1 >>> 56),
            (byte)(var1 >>> 48),
            (byte)(var1 >>> 40),
            (byte)(var1 >>> 32),
            (byte)(var1 >>> 24),
            (byte)(var1 >>> 16),
            (byte)(var1 >>> 8),
            (byte)var1
         };
         long var5 = b[var3];
         byte[] var7 = new byte[]{
            (byte)(var5 >>> 56),
            (byte)(var5 >>> 48),
            (byte)(var5 >>> 40),
            (byte)(var5 >>> 32),
            (byte)(var5 >>> 24),
            (byte)(var5 >>> 16),
            (byte)(var5 >>> 8),
            (byte)var5
         };
         Long var8 = Thread.currentThread().getId();
         Object[] var9 = (Object[])d.get(var8);

         byte[] var10;
         try {
            if (var9 == null) {
               var9 = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
               d.put(var8, var9);
            }

            DESKeySpec var11 = new DESKeySpec(var4);
            SecretKey var12 = ((SecretKeyFactory)var9[1]).generateSecret(var11);
            Cipher var13 = (Cipher)var9[0];
            var13.init(2, var12, (IvParameterSpec)var9[2]);
            var10 = var13.doFinal(var7);
         } catch (Exception var14) {
            throw new RuntimeException("Expo/internal/MiningBlockScanner", var14);
         }

         int var15 = (var10[4] & 255) << 24 | (var10[5] & 255) << 16 | (var10[6] & 255) << 8 | var10[7] & 255;
         c[var3] = var15;
      }

      return c[var3];
   }

   public static MiningAxisScanResult l(long var0, int var2) {
      long var3 = var0 ^ 128616321187327L;
      EntityPlayerSP var7 = R.thePlayer;
      MiningAxisScanResult var8 = new MiningAxisScanResult(var3);
      double var9 = E(A(var7));
      int var11 = var9 >= 45.0 && var9 < 135.0 ? 3 : (var9 >= 135.0 && var9 < 225.0 ? 2 : (var9 >= 225.0 && var9 < 315.0 ? 1 : 0));
      int var12 = (int)Math.floor(var7.posX);
      int var13 = (int)Math.floor(var7.posY);
      int var14 = (int)Math.floor(var7.posZ);
      int var15 = -1;
      int var16 = -1;
      BlockPos var17 = null;
      BlockPos var18 = null;

      for (int var19 = 1; var19 <= var2; var19++) {
         BlockPos var20;
         BlockPos var21;
         switch (var11) {
            case 0:
               var20 = new BlockPos(var12 - var19, var13, var14);
               var21 = new BlockPos(var12 + var19, var13, var14);
               break;
            case 1:
               var20 = new BlockPos(var12, var13, var14 + var19);
               var21 = new BlockPos(var12, var13, var14 - var19);
               break;
            case 2:
               var20 = new BlockPos(var12 + var19, var13, var14);
               var21 = new BlockPos(var12 - var19, var13, var14);
               break;
            case 3:
            default:
               var20 = new BlockPos(var12, var13, var14 - var19);
               var21 = new BlockPos(var12, var13, var14 + var19);
         }

         Block var22 = R.theWorld.getBlockState(var20).getBlock();
         if (var22 == Blocks.stone) {
            var8.t++;
            var8.n.add(var20);
         }

         if (var22 == Blocks.bedrock && var15 == -1) {
            var15 = var19;
            var17 = var20;
         }

         Block var23 = R.theWorld.getBlockState(var21).getBlock();
         if (var23 == Blocks.stone) {
            var8.Z++;
            var8.z.add(var21);
         }

         if (var23 == Blocks.bedrock && var16 == -1) {
            var16 = var19;
            var18 = var21;
         }
      }

      s = new ArrayList<>(var8.n);
      W = new ArrayList<>(var8.z);
      M = System.currentTimeMillis() + 10000L;
      A = var17;
      u = var18;
      if (var17 != null) {
         BoxRenderer.I( var17, new Color(0, 0, 0, 200));
      }

      if (var18 != null) {
         BoxRenderer.I( var18, new Color(80, 40, 0, 200));
      }

      var8.S = var15;
      var8.c = var16;
      var8.C = var17;
      var8.x = var18;
      return var8;
   }

   public static void h(long var0) {
      if (w) {
         Color var4 = new Color(0, 255, 255, 180);

         for (BlockPos var6 : Y) {
            BoxRenderer.I( var6, var4);
         }
      }
   }

   public static void l(int var0, short var1, char var2) {
      long var3 = ((long)var0 << 32 | (long)var1 << 48 >>> 32 | (long)var2 << 48 >>> 48) ^ a;
      long var5 = var3 ^ 105869387401141L;
      if (System.currentTimeMillis() <= H) {
         Color var7 = new Color(
            180,
            255,
            255,
            30
         );
         Color var8 = new Color(
            255,
            180,
            180,
            30
         );
         Color var9 = new Color(
            180,
            220,
            255,
            30
         );
         Color var10 = new Color(
            220,
            180,
            255,
            30
         );

         for (BlockPos var12 : S) {
            BoxRenderer.p(var12, var5, var7);
         }

         for (BlockPos var16 : v) {
            BoxRenderer.p(var16, var5, var8);
         }

         for (BlockPos var17 : e) {
            BoxRenderer.p(var17, var5, var9);
         }

         for (BlockPos var18 : x) {
            BoxRenderer.p(var18, var5, var10);
         }
      }
   }

   private static boolean l(Block var0) {
      return var0 == Blocks.chest || var0 == Blocks.trapped_chest;
   }

   private static int B(Block var0, boolean var1, boolean var2) {
      if (l(var0)) {
         return 0;
      } else if (var0 == Blocks.stone) {
         return 1;
      } else if (var1 && j(var0)) {
         return var2 ? 1 : 2;
      } else {
         return 3;
      }
   }

   private static double E(float var0) {
      double var1 = var0 % 360.0;
      if (var1 < 0.0) {
         var1 += 360.0;
      }

      return var1;
   }

   private MiningBlockScanner() {
   }
}
