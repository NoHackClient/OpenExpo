package Expo.util;

import Expo.internal.accessor.MethodAccessors;
import Expo.internal.synthetic.RaytraceUtilSwitchMapEnumFacing;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;


public class RaytraceUtil {
   private static String[] e;
   private static final double c = 0.15;
   private static long a;
   private static final double d = 1.0;
   private static Map i;
   private static String[] b;
   private static Object[] j;
   private static Minecraft F;
   private static String[] l;
   private static long[] g;
   private static int V;
   private static final double k = 1.0E-6;
   private static List<Vec3> q;
   private static List<Vec3> p;
   private static Integer[] h;
   private static Map f;

   private static double D(AxisAlignedBB var0, Vec3 var1, boolean var2, double var3) {
      double var5 = var0.minY;
      double var7 = var0.maxY;
      double var9 = (var0.minX + var0.maxX) * 0.5;
      double var11 = (var0.minZ + var0.maxZ) * 0.5;
      double var13 = (var5 + var7) * 0.5;
      double var15 = var2 ? MathUtil.R(var3, var5, var7) : var13;
      double var17 = Math.sqrt((var1.xCoord - var9) * (var1.xCoord - var9) + (var1.zCoord - var11) * (var1.zCoord - var11));
      double var19 = var15 - var13;
      double var21 = Math.min(var13 + var17 * (var19 * 0.35), var15);
      double var23;
      if (var1.yCoord <= var5) {
         var23 = (var5 + var13) * 0.5;
      } else {
         var23 = MathUtil.R(var1.yCoord, (var5 + var13) * 0.5, var21);
      }

      return MathUtil.R(var23, var5, var7);
   }

   public static MovingObjectPosition k(AxisAlignedBB var0, double var1) {
      return k(var0, RotationManager.r, RotationManager.G, var1);
   }

   private static Vec3 U(AxisAlignedBB var0, long var1, Vec3 var3, double var4) {
      long var6 = var1 ^ 57802740672762L;
      double var8 = var4 > 0.0 ? var4 : Double.POSITIVE_INFINITY;
      double var10 = var0.minX;
      double var12 = var0.maxX;
      double var14 = var0.minY;
      double var16 = var0.maxY;
      double var18 = var0.minZ;
      double var20 = var0.maxZ;
      double var22 = (var10 + var12) * 0.5;
      double var24 = (var14 + var16) * 0.5;
      double var26 = (var18 + var20) * 0.5;
      Vec3 var28 = l(var0, var3);
      q.clear();
      if (!var0.isVecInside(var3)) {
         q.add(var28);
      }

      q.add(new Vec3(var22, var24, var26));
      q.add(new Vec3(var22, var14 + (var16 - var14) * 0.3, var26));
      q.add(new Vec3(var22, var14 + (var16 - var14) * 0.45, var26));
      q.add(new Vec3(var22, var14 + (var16 - var14) * 0.6, var26));
      q.add(new Vec3(var22, var14 + (var16 - var14) * 0.75, var26));
      q.add(new Vec3(var10, var24, var26));
      q.add(new Vec3(var12, var24, var26));
      q.add(new Vec3(var22, var24, var18));
      q.add(new Vec3(var22, var24, var20));
      q.add(new Vec3(var10, var14 + (var16 - var14) * 0.35, var26));
      q.add(new Vec3(var12, var14 + (var16 - var14) * 0.35, var26));
      q.add(new Vec3(var22, var14 + (var16 - var14) * 0.35, var18));
      q.add(new Vec3(var22, var14 + (var16 - var14) * 0.35, var20));
      q.add(new Vec3(var10, var14 + (var16 - var14) * 0.65, var26));
      q.add(new Vec3(var12, var14 + (var16 - var14) * 0.65, var26));
      q.add(new Vec3(var22, var14 + (var16 - var14) * 0.65, var18));
      q.add(new Vec3(var22, var14 + (var16 - var14) * 0.65, var20));
      q.addAll(A(var0, var6));
      Vec3 var29 = null;
      double var30 = Double.MAX_VALUE;
      int var32 = 0;

      for (int var33 = q.size(); var32 < var33; var32++) {
         Vec3 var34 = h(q.get(var32), var0);
         double var35 = var3.distanceTo(var34);
         if (!(var35 > var8) && F.theWorld.rayTraceBlocks(var3, var34) == null) {
            double var37 = Math.abs(var34.yCoord - var24);
            double var39 = d(var34.xCoord, var34.zCoord, var22, var26);
            double var41 = var34.distanceTo(var28);
            double var43 = var35 * 1.5 + var41 * 0.55 + var37 * 0.8 + var39 * 0.65;
            if (var43 < var30) {
               var29 = var34;
            }
         }
      }

      return var29;
   }

   public static Vec3 R(long var0, AxisAlignedBB var2) {
      var0 = a ^ var0;
      int var3 = (int)((var0 ^ 14311153185602L) >>> 48);
      int var4 = (int)((var0 ^ 14311153185602L) << 16 >>> 48);
      int var5 = (int)((var0 ^ 14311153185602L) << 32 >>> 32);
      return Q((short)var3, var2, 0.0, (short)var4, true, var5);
   }

   public static double z(Vec3 var0) {
      return f().distanceTo(var0);
   }

   public static boolean H(Vec3 var0, double var1) {
      Vec3 var3 = f();
      return var3.distanceTo(var0) > var1 ? true : F.theWorld.rayTraceBlocks(var3, var0) != null;
   }

   public static boolean q(long var0, Entity var2, double var3) {
      long var5 = var0 ^ 27539288905818L;
      return i(var2, var3, var5, false);
   }

   public static double y(AxisAlignedBB var0, long var1) {
      long var3 = var1 ^ 110880553025630L;
      return f().distanceTo(f(var0, var3));
   }

   public static double M(long var0, Entity var2, double var3, boolean var5) {
      long var6 = var0 ^ 131316543872721L;
      return f().distanceTo(R(var2, var6, var3, var5));
   }

   public static double n(long var0, BlockPos var2, EnumFacing var3) {
      long var4 = var0 ^ 87539308272515L;
      return f().distanceTo(o(var2, var3, var4));
   }

   private static void s(BlockPos var0, long var1, EnumFacing var3) {
      if (var0 == null) {
         throw new IllegalArgumentException("BlockPos cannot be null");
      }

      if (var3 == null) {
         throw new IllegalArgumentException("EnumFacing is required for block targeting");
      }
   }

   public static double Z(double var0, double var2, double var4, double var6, double var8, double var10) {
      double var12 = var6 - var0;
      double var14 = var8 - var2;
      double var16 = var10 - var4;
      return Math.sqrt(var12 * var12 + var14 * var14 + var16 * var16);
   }

   public static Vec3 M(AxisAlignedBB var0, short var1, int var2, Vec3 var3, char var4) {
      long var5 = ((long)var1 << 48 | (long)var2 << 32 >>> 16 | (long)var4 << 48 >>> 48) ^ a;
      long var7 = var5 ^ 220368532800L;
      return d(var0, var3, 0.0, true, var7);
   }

   private static Vec3 S(BlockPos var0, EnumFacing var1) {
      return new Vec3(
         var0.getX() + 0.5 + var1.getFrontOffsetX() * 0.5,
         var0.getY() + 0.5 + var1.getFrontOffsetY() * 0.5,
         var0.getZ() + 0.5 + var1.getFrontOffsetZ() * 0.5
      );
   }

   public static MovingObjectPosition J(double var0) {
      Vec3 var2 = f();
      Vec3 var3 = F.thePlayer.getLookVec();
      Vec3 var4 = var2.addVector(var3.xCoord * var0, var3.yCoord * var0, var3.zCoord * var0);
      return F.thePlayer.worldObj.rayTraceBlocks(var2, var4, false, false, false);
   }

   public static MovingObjectPosition M() {
      return f(RotationManager.r, RotationManager.G, F.playerController.getBlockReachDistance(), 1.0F);
   }

   public static Vec3 S(AxisAlignedBB var0, long var1, double var3, short var5) {
      long var6 = (var1 << 16 | (long)var5 << 48 >>> 48) ^ a;
      int var8 = (int)((var6 ^ 132484683780157L) >>> 48);
      int var9 = (int)((var6 ^ 132484683780157L) << 16 >>> 48);
      int var10 = (int)((var6 ^ 132484683780157L) << 32 >>> 32);
      return Q((short)var8, var0, var3, (short)var9, true, var10);
   }

   public static Vec3 f() {
      return F.thePlayer.getPositionEyes(1.0F);
   }

   public static Vec3 w(long var0, AxisAlignedBB var2, Vec3 var3, double var4) {
      long var6 = var0 ^ 7441853264842L;
      return d(var2, var3, var4, true, var6);
   }

   public static Vec3 t(BlockPos var0, long var1, EnumFacing var3, double var4) {
      long var6 = var1 ^ 9061816117853L;
      return Z(var0, var6, var3, var4, false);
   }

   public static Vec3 f(AxisAlignedBB var0, long var1) {
      var1 = a ^ var1;
      int var3 = (int)((var1 ^ 125428208133194L) >>> 48);
      int var4 = (int)((var1 ^ 125428208133194L) << 16 >>> 48);
      int var5 = (int)((var1 ^ 125428208133194L) << 32 >>> 32);
      return Q((short)var3, var0, 0.0, (short)var4, false, var5);
   }

   public static AxisAlignedBB J(BlockPos var0) {
      return new AxisAlignedBB(
         var0.getX(), var0.getY(), var0.getZ(), var0.getX() + 1, var0.getY() + 1, var0.getZ() + 1
      );
   }

   public static double t(Vec3 var0, Vec3 var1) {
      return var0.distanceTo(var1);
   }

   public static MovingObjectPosition v(long var0, BlockPos var2, EnumFacing var3) {
      long var4 = var0 ^ 12105203431285L;
      Vec3 var6 = Z(var2, var4, var3, F.playerController.getBlockReachDistance(), true);
      return H(var6);
   }

   public static MovingObjectPosition f(float var0, float var1, double var2, float var4) {
      Vec3 var5 = F.thePlayer.getPositionEyes(var4);
      Vec3 var6 = RotationUtil.d(var1, var0);
      Vec3 var7 = var5.addVector(var6.xCoord * var2, var6.yCoord * var2, var6.zCoord * var2);
      return F.theWorld.rayTraceBlocks(var5, var7);
   }

   private static Vec3 s(AxisAlignedBB var0, Vec3 var1, double var2) {
      double var4 = var0.minX;
      double var6 = var0.maxX;
      double var8 = var0.minZ;
      double var10 = var0.maxZ;
      double var12 = (var4 + var6) * 0.5;
      double var14 = (var8 + var10) * 0.5;
      boolean var16 = var0.minX - 1.0 < var1.xCoord
         && var0.maxX + 1.0 > var1.xCoord
         && var0.minZ - 1.0 < var1.zCoord
         && var0.maxZ + 1.0 > var1.zCoord;
      if (var16) {
         return new Vec3(var12, var2, var14);
      }

      double var17 = MathUtil.R(var1.xCoord, var4, var6);
      double var19 = MathUtil.R(var1.zCoord, var8, var10);
      double var21 = 0.3;
      double var23 = MathUtil.I(var17, var12, var21);
      double var25 = MathUtil.I(var19, var14, var21);
      double var27 = 0.06;
      return new Vec3(MathUtil.R(var23, var4 + var27, var6 - var27), var2, MathUtil.R(var25, var8 + var27, var10 - var27));
   }

   public static EntityLivingBase Z(double var0) {
      Entity var2 = null;
      MovingObjectPosition var3 = F.thePlayer.rayTrace(var0, 1.0F);
      Vec3 var4 = f();
      float var5 = RotationManager.r;
      float var6 = RotationManager.G;
      float var7 = MathHelper.cos(-var5 * (float) (Math.PI / 180.0) - (float) Math.PI);
      float var8 = MathHelper.sin(-var5 * (float) (Math.PI / 180.0) - (float) Math.PI);
      float var9 = -MathHelper.cos(-var6 * (float) (Math.PI / 180.0));
      Vec3 var10 = new Vec3(var8 * var9, MathHelper.sin(-var6 * (float) (Math.PI / 180.0)), var7 * var9);
      Vec3 var11 = var4.addVector(var10.xCoord * var0, var10.yCoord * var0, var10.zCoord * var0);
      Vec3 var12 = null;
      List var13 = F.theWorld
         .getEntitiesWithinAABBExcludingEntity(
            F.getRenderViewEntity(),
            F.getRenderViewEntity()
               .getEntityBoundingBox()
               .addCoord(var10.xCoord * var0, var10.yCoord * var0, var10.zCoord * var0)
               .expand(1.0, 1.0, 1.0)
         );
      double var14 = var0;
      int var16 = 0;

      for (int var17 = var13.size(); var16 < var17; var16++) {
         Entity var18 = (Entity)var13.get(var16);
         if (var18.canBeCollidedWith()) {
            AxisAlignedBB var19 = S(var18);
            MovingObjectPosition var20 = var19.calculateIntercept(var4, var11);
            if (var19.isVecInside(var4)) {
               if (0.0 < var14 || var14 == 0.0) {
                  var2 = var18;
                  var12 = var20 == null ? var4 : var20.hitVec;
                  var14 = 0.0;
               }
            } else if (var20 != null) {
               double var21 = var4.distanceTo(var20.hitVec);
               if (var21 < var14 || var14 == 0.0) {
                  if (var18 != F.getRenderViewEntity().ridingEntity || MethodAccessors.o(var18)) {
                     var2 = var18;
                     var12 = var20.hitVec;
                     var14 = var21;
                  } else if (var14 == 0.0) {
                     var2 = var18;
                     var12 = var20.hitVec;
                  }
               }
            }
         }
      }

      if (var2 != null && (var14 < var0 || var3 == null)) {
         var3 = new MovingObjectPosition(var2, var12);
      }

      return var3 != null && var3.typeOfHit == MovingObjectType.ENTITY && var3.entityHit instanceof EntityLivingBase
         ? (EntityLivingBase)var3.entityHit
         : null;
   }

   public static AxisAlignedBB S(Entity var0) {
      float var1 = var0.getCollisionBorderSize();
      return var0.getEntityBoundingBox().expand(var1, var1, var1);
   }

   public static Vec3 e(Entity var0, long var1) {
      long var3 = var1 ^ 105625816197464L;
      return R(var0, var3, 0.0, false);
   }

   public static Vec3 R(Entity var0, long var1, double var3, boolean var5) {
      var1 = a ^ var1;
      int var6 = (int)((var1 ^ 108295173285533L) >>> 32);
      int var7 = (int)((var1 ^ 108295173285533L) << 32 >>> 40);
      int var8 = (int)((var1 ^ 108295173285533L) << 56 >>> 56);
      AxisAlignedBB var9 = S(var0);
      Vec3 var10 = f();
      double var11 = var9.minY + var0.getCollisionBorderSize() + var0.getEyeHeight();
      return distanceTo(var9, var10, var3, var5, var6, true, var7, (byte)var8, var11);
   }

   public static double p(BlockPos var0, long var1) {
      long var3 = var1 ^ 20854630582128L;
      return n(var3, var0, BlockUtil.D(var0));
   }

   static {
      a = 101704553611049L;
      V = 4;
      p = new ArrayList<>(256);
      q = new ArrayList<>(256);
      F = MinecraftRef.c((byte)0, 0L);
   }

   private static Vec3 i(Vec3 var0, Vec3 var1, double var2) {
      return new Vec3(
         MathUtil.I(var0.xCoord, var1.xCoord, var2),
         MathUtil.I(var0.yCoord, var1.yCoord, var2),
         MathUtil.I(var0.zCoord, var1.zCoord, var2)
      );
   }

   public static boolean Y(BlockPos var0, double var1, long var3) {
      long var5 = var3 ^ 114033478596286L;
      return p(var0, var5) <= var1;
   }

   public static Vec3 e(long var0, BlockPos var2, EnumFacing var3) {
      long var4 = var0 ^ 94566646313012L;
      return Z(var2, var4, var3, 0.0, true);
   }

   public static Vec3 U(long var0, AxisAlignedBB var2, Vec3 var3) {
      long var4 = var0 ^ 95364440374258L;
      return d(var2, var3, 0.0, false, var4);
   }

   public static Vec3 w(Entity var0, int var1, short var2, double var3, char var5) {
      long var6 = ((long)var1 << 32 | (long)var2 << 48 >>> 32 | (long)var5 << 48 >>> 48) ^ a;
      long var8 = var6 ^ 60854124342603L;
      return R(var0, var8, var3, false);
   }

   public static double t(Entity var0, int var1, double var2, long var4) {
      long var6 = ((long)var1 << 32 | var4 << 32 >>> 32) ^ a;
      long var8 = var6 ^ 109802464219707L;
      return M(var8, var0, var2, false);
   }

   public static Vec3 o(BlockPos var0, EnumFacing var1, long var2) {
      long var4 = var2 ^ 25349294246222L;
      return Z(var0, var4, var1, 0.0, false);
   }

   public static MovingObjectPosition H(Vec3 var0) {
      return F.theWorld.rayTraceBlocks(f(), var0);
   }

   public static Vec3 O(Entity var0, long var1) {
      long var3 = var1 ^ 98713421966854L;
      return R(var0, var3, 0.0, true);
   }

   public static boolean i(Entity var0, double var1, long var3, boolean var5) {
      var3 = a ^ var3;
      long var6 = var3 ^ 25426472229423L;
      long var8 = var3 ^ 91478317938003L;
      return var5 && V(var0, var6, var1) ? false : M(var8, var0, var1, var5) <= var1;
   }

   private static Vec3 i(AxisAlignedBB var0, Vec3 var1, Vec3 var2) {
      boolean var3 = var0.minX - 1.0 < var1.xCoord
         && var0.maxX + 1.0 > var1.xCoord
         && var0.minZ - 1.0 < var1.zCoord
         && var0.maxZ + 1.0 > var1.zCoord;
      if (var3) {
         double var4 = (var0.minX + var0.maxX) * 0.5;
         double var6 = (var0.minZ + var0.maxZ) * 0.5;
         return new Vec3(var4, var2.yCoord, var6);
      } else {
         return l(var0, var1);
      }
   }

   private static boolean M(BlockPos var0, EnumFacing var1, Vec3 var2, Vec3 var3, double var4) {
      if (var4 > 0.0 && var3.distanceTo(var2) > var4) {
         return false;
      }

      MovingObjectPosition var6 = F.theWorld.rayTraceBlocks(var3, var2);
      return var6 != null && var6.typeOfHit == MovingObjectType.BLOCK && var0.equals(var6.getBlockPos()) && var1 == var6.sideHit;
   }

   public static double d(double var0, double var2, double var4, double var6) {
      double var8 = var4 - var0;
      double var10 = var6 - var2;
      return Math.sqrt(var8 * var8 + var10 * var10);
   }

   private static Vec3 e(AxisAlignedBB var0, Vec3 var1) {
      Vec3 var2 = l(var0, var1);
      return var2.distanceTo(var1) > 1.0E-6
         ? var2
         : new Vec3(
            (var0.minX + var0.maxX) * 0.5, (var0.minY + var0.maxY) * 0.5, (var0.minZ + var0.maxZ) * 0.5
         );
   }

   public static List<EntityLivingBase> j(double var0) {
      WorldClient var2 = F.theWorld;
      Entity var3 = F.getRenderViewEntity();
      ArrayList var4 = new ArrayList();
      Vec3 var5 = f();
      float var6 = RotationManager.r;
      float var7 = RotationManager.G;
      float var8 = MathHelper.cos(-var6 * (float) (Math.PI / 180.0) - (float) Math.PI);
      float var9 = MathHelper.sin(-var6 * (float) (Math.PI / 180.0) - (float) Math.PI);
      float var10 = -MathHelper.cos(-var7 * (float) (Math.PI / 180.0));
      float var11 = MathHelper.sin(-var7 * (float) (Math.PI / 180.0));
      Vec3 var12 = new Vec3(var9 * var10, var11, var8 * var10);
      Vec3 var13 = var5.addVector(var12.xCoord * var0, var12.yCoord * var0, var12.zCoord * var0);
      AxisAlignedBB var14 = var3.getEntityBoundingBox()
         .addCoord(var12.xCoord * var0, var12.yCoord * var0, var12.zCoord * var0)
         .expand(1.0, 1.0, 1.0);
      List var15 = var2.getEntitiesWithinAABBExcludingEntity(var3, var14);
      int var16 = 0;

      for (int var17 = var15.size(); var16 < var17; var16++) {
         Entity var18 = (Entity)var15.get(var16);
         if (var18 instanceof EntityLivingBase && var18.canBeCollidedWith()) {
            AxisAlignedBB var19 = S(var18);
            MovingObjectPosition var20 = var19.calculateIntercept(var5, var13);
            if (var19.isVecInside(var5) || var20 != null) {
               var4.add((EntityLivingBase)var18);
            }
         }
      }

      return var4;
   }

   public static boolean r(short var0, long var1, BlockPos var3, EnumFacing var4, double var5) {
      long var7 = ((long)var0 << 48 | var1 << 16 >>> 16) ^ a;
      long var9 = var7 ^ 98502816857194L;
      long var11 = var7 ^ 43254703832494L;
      s(var3, var11, var4);
      Vec3 var13 = f();
      return !M(var3, var4, g(var3, var4, var9, var13), var13, var5) && !M(var3, var4, S(var3, var4), var13, var5);
   }

   public static MovingObjectPosition h(AxisAlignedBB var0, float var1, float var2, double var3) {
      return k(var0, var1, var2, var3);
   }

   public static boolean V(Entity var0, long var1, double var3) {
      long var5 = var1 ^ 19354569615127L;
      AxisAlignedBB var7 = S(var0);
      Vec3 var8 = f();
      return U(var7, var5, var8, var3) == null;
   }

   private static List A(AxisAlignedBB var0, long var1) {
      p.clear();

      double var4 = 5.0;
      double var6 = (var0.maxX - var0.minX) / var4;
      double var8 = (var0.maxY - var0.minY) / var4;
      double var10 = (var0.maxZ - var0.minZ) / var4;

      for (int var12 = 0; var12 < 6; var12++) {
         double var13 = var0.minX + var12 * var6;

         for (int var15 = 0; var15 < 6; var15++) {
            double var16 = var0.minY + var15 * var8;
            p.add(new Vec3(var13, var16, var0.minZ));
            p.add(new Vec3(var13, var16, var0.maxZ));
         }
      }

      for (int var19 = 0; var19 < 6; var19++) {
         double var21 = var0.minX + var19 * var6;

         for (int var23 = 0; var23 < 6; var23++) {
            double var25 = var0.minZ + var23 * var10;
            p.add(new Vec3(var21, var0.minY, var25));
            p.add(new Vec3(var21, var0.maxY, var25));
         }
      }

      for (int var20 = 0; var20 < 6; var20++) {
         double var22 = var0.minY + var20 * var8;

         for (int var24 = 0; var24 < 6; var24++) {
            double var26 = var0.minZ + var24 * var10;
            p.add(new Vec3(var0.minX, var22, var26));
            p.add(new Vec3(var0.maxX, var22, var26));
         }
      }

      return p;
   }

   public static Vec3 h(Vec3 var0, AxisAlignedBB var1) {
      double var2 = MathHelper.clamp_double(var0.xCoord, var1.minX, var1.maxX);
      double var4 = MathHelper.clamp_double(var0.yCoord, var1.minY, var1.maxY);
      double var6 = MathHelper.clamp_double(var0.zCoord, var1.minZ, var1.maxZ);
      return new Vec3(var2, var4, var6);
   }


   public static Vec3 p(AxisAlignedBB var0, long var1, double var3) {
      var1 = a ^ var1;
      int var5 = (int)((var1 ^ 69768517882322L) >>> 48);
      int var6 = (int)((var1 ^ 69768517882322L) << 16 >>> 48);
      int var7 = (int)((var1 ^ 69768517882322L) << 32 >>> 32);
      return Q((short)var5, var0, var3, (short)var6, false, var7);
   }

   public static Vec3 g(long var0, Entity var2, double var3) {
      long var5 = var0 ^ 46153656889811L;
      return R(var2, var5, var3, true);
   }

   public static double i(Entity var0) {

      return M(65711117411872L, var0, 0.0, false);
   }

   private static boolean g(Vec3 var0, Vec3 var1, double var2, boolean var4) {
      if (var0 == null) {
         return false;
      } else {
         return var1.distanceTo(var0) > var2 ? false : !var4 || F.theWorld.rayTraceBlocks(var1, var0) == null;
      }
   }

   public static Vec3 v(BlockPos var0, EnumFacing var1, long var2, double var4) {
      long var6 = var2 ^ 356214330172L;
      return Z(var0, var6, var1, var4, true);
   }

   public static boolean u(short var0, int var1, BlockPos var2, EnumFacing var3, int var4, double var5) {
      long var7 = ((long)var0 << 48 | (long)var1 << 32 >>> 16 | (long)var4 << 48 >>> 48) ^ a;
      long var9 = var7 ^ 66693924059942L;
      return n(var9, var2, var3) <= var5;
   }

   public static MovingObjectPosition F(float var0, float var1) {
      return f(var0, var1, F.playerController.getBlockReachDistance(), 1.0F);
   }

   public static Vec3 J(AxisAlignedBB var0, char var1, Vec3 var2, double var3, short var5, int var6) {
      long var7 = ((long)var1 << 48 | (long)var5 << 48 >>> 16 | (long)var6 << 32 >>> 32) ^ a;
      long var9 = var7 ^ 125985818759426L;
      return d(var0, var2, var3, false, var9);
   }

   private static Vec3 g(BlockPos var0, EnumFacing var1, long var2, Vec3 var4) {
      Vec3 var5 = S(var0, var1);
      double var6 = var0.getX() + 0.15;
      double var8 = var0.getX() + 1.0 - 0.15;
      double var10 = var0.getZ() + 0.15;
      double var12 = var0.getZ() + 1.0 - 0.15;
      double var14 = Math.toRadians(RotationManager.r);
      double var16 = -Math.sin(var14);
      double var18 = Math.cos(var14);
      double var20 = var5.xCoord;
      double var22 = var5.zCoord;
      switch (RaytraceUtilSwitchMapEnumFacing.b[var1.ordinal()]) {
         case 1:
         case 2:
            if (Math.abs(var16) > 1.0E-6) {
               double var29 = (var5.xCoord - var4.xCoord) / var16;
               if (var29 >= 0.0) {
                  var22 = MathUtil.R(var4.zCoord + var18 * var29, var10, var12);
               }
            }
            break;
         case 3:
         case 4:
            if (Math.abs(var18) > 1.0E-6) {
               double var28 = (var5.zCoord - var4.zCoord) / var18;
               if (var28 >= 0.0) {
                  var20 = MathUtil.R(var4.xCoord + var16 * var28, var6, var8);
               }
            }
            break;
         case 5:
         case 6:
            double var24 = (var5.xCoord - var4.xCoord) * var16 + (var5.zCoord - var4.zCoord) * var18;
            if (var24 >= 0.0) {
               var20 = MathUtil.R(var4.xCoord + var16 * var24, var6, var8);
               var22 = MathUtil.R(var4.zCoord + var18 * var24, var10, var12);
            }
      }

      Vec3 var30 = new Vec3(var20, var5.yCoord, var22);
      float var25 = Math.abs(MathUtil.M(RotationManager.r, RotationUtil.W(var30, var4)[0]));
      float var26 = Math.abs(MathUtil.M(RotationManager.r, RotationUtil.W(var5, var4)[0]));
      return var25 <= var26 ? var30 : var5;
   }

   public static Vec3 Q(short var0, AxisAlignedBB var1, double var2, short var4, boolean var5, int var6) {
      long var7 = ((long)var0 << 48 | (long)var4 << 48 >>> 16 | (long)var6 << 32 >>> 32) ^ a;
      long var9 = var7 ^ 4893334898285L;
      return d(var1, f(), var2, var5, var9);
   }


   public static Vec3 d(AxisAlignedBB var0, Vec3 var1, double var2, boolean var4, long var5) {
      var5 = a ^ var5;
      int var7 = (int)((var5 ^ 104618223725756L) >>> 32);
      int var8 = (int)((var5 ^ 104618223725756L) << 32 >>> 40);
      int var9 = (int)((var5 ^ 104618223725756L) << 56 >>> 56);
      return distanceTo(var0, var1, var2, var4, var7, false, var8, (byte)var9, 0.0);
   }

   private static Vec3 l(AxisAlignedBB var0, Vec3 var1) {
      return new Vec3(
         MathUtil.R(var1.xCoord, var0.minX, var0.maxX),
         MathUtil.R(var1.yCoord, var0.minY, var0.maxY),
         MathUtil.R(var1.zCoord, var0.minZ, var0.maxZ)
      );
   }

   public static Vec3 Z(BlockPos var0, long var1, EnumFacing var3, double var4, boolean var6) {
      var1 = a ^ var1;
      long var7 = var1 ^ 45301652731358L;
      long var9 = var1 ^ 96700664575002L;
      s(var0, var9, var3);
      Vec3 var11 = f();
      Vec3 var12 = g(var0, var3, var7, var11);
      if (var6 && !M(var0, var3, var12, var11, var4)) {
         Vec3 var13 = S(var0, var3);
         return M(var0, var3, var13, var11, var4) ? var13 : var12;
      } else {
         return var12;
      }
   }

   public static MovingObjectPosition k(AxisAlignedBB var0, float var1, float var2, double var3) {
      Vec3 var5 = f();
      Vec3 var6 = RotationUtil.d(var2, var1);
      Vec3 var7 = var5.addVector(var6.xCoord * var3, var6.yCoord * var3, var6.zCoord * var3);
      return var0.calculateIntercept(var5, var7);
   }

   public static boolean x(AxisAlignedBB var0, long var1, double var3) {
      long var5 = var1 ^ 137664095542292L;
      return y(var0, var5) <= var3;
   }

   private static Vec3 distanceTo(AxisAlignedBB var0, Vec3 var1, double var2, boolean var4, int var5, boolean var6, int var7, byte var8, double var9) {
      long var11 = ((long)var5 << 32 | (long)var7 << 40 >>> 32 | (long)var8 << 56 >>> 56) ^ a;
      long var13 = var11 ^ 71022684712999L;
      double var15 = var2 > 0.0 ? var2 : Double.POSITIVE_INFINITY;
      Vec3 var17 = a(var0, var1, var6, var9);
      if (g(var17, var1, var15, var4)) {
         return var17;
      }

      Vec3 var18 = i(var0, var1, var17);
      if (g(var18, var1, var15, var4)) {
         double var19 = var1.distanceTo(var18);
         double var21 = 1.0 - MathUtil.R(var19 / var15, 0.0, 1.0);
         double var23 = MathUtil.R(0.35 + var21 * 0.5, 0.35, 0.85);
         Vec3 var25 = h(i(var18, var17, var23), var0);
         if (g(var25, var1, var15, var4)) {
            return var25;
         }
      }

      if (var4) {
         Vec3 var26 = U(var0, var13, var1, var2);
         if (var26 != null) {
            return var26;
         }
      }

      return e(var0, var1);
   }

   public static MovingObjectPosition A(long var0, Entity var2) {
      long var3 = var0 ^ 92235532979768L;
      Vec3 var5 = R(var2, var3, F.playerController.getBlockReachDistance(), true);
      return H(var5);
   }

   private static Vec3 a(AxisAlignedBB var0, Vec3 var1, boolean var2, double var3) {
      double var5 = D(var0, var1, var2, var3);
      return s(var0, var1, var5);
   }


}
