package Expo.util;

import Expo.internal.synthetic.RotationUtilSwitchMapEnumFacing;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class RotationUtil {
   private static final long a = 116334093987398L;
   private static final Minecraft j;

   public static Vec3 T(float var0) {
      if (var0 == 1.0F) {
         return d(RotationManager.G, RotationManager.r);
      }

      float var1 = RotationManager.h + (RotationManager.G - RotationManager.h) * var0;
      float var2 = RotationManager.b + (RotationManager.r - RotationManager.b) * var0;
      return d(var1, var2);
   }

   public static Vec3 h(BlockPos var0, EnumFacing var1, double var2) {
      double var4 = RaytraceUtil.f().xCoord;
      double var6 = RaytraceUtil.f().zCoord;
      double var8 = var0.getX() + 0.05;
      double var10 = var0.getX() + 0.95;
      double var12 = var0.getY() + 0.05;
      double var14 = var0.getZ() + 0.05;
      double var16 = var0.getZ() + 0.95;
      double var18;
      double var20;
      switch (RotationUtilSwitchMapEnumFacing.m[var1.ordinal()]) {
         case 1:
            var18 = MathUtil.R(RaytraceUtil.f().xCoord, var8, var10);
            var12 = Math.max(var0.getY() + 0.05, Math.min(RaytraceUtil.f().yCoord, var0.getY() + 1 - 0.05));
            var20 = MathUtil.R(RaytraceUtil.f().zCoord, var14, var16);
            break;
         case 2:
            var20 = var14;
            if (var4 <= var10) {
               if (var8 + var2 > var4) {
                  var18 = MathUtil.R(RaytraceUtil.f().xCoord + var2, var8, var10);
               } else {
                  var18 = MathUtil.R(RaytraceUtil.f().xCoord - var2, var8, var10);
               }
            } else {
               var18 = MathUtil.R(RaytraceUtil.f().xCoord - var2, var8, var10);
            }
            break;
         case 3:
            var20 = var16;
            if (var4 >= var8) {
               if (var10 - var2 < var4) {
                  var18 = MathUtil.R(RaytraceUtil.f().xCoord - var2, var8, var10);
               } else {
                  var18 = MathUtil.R(RaytraceUtil.f().xCoord + var2, var8, var10);
               }
            } else {
               var18 = MathUtil.R(RaytraceUtil.f().xCoord + var2, var8, var10);
            }
            break;
         case 4:
            var18 = var8;
            if (var6 >= var14) {
               if (var16 - var2 < var6) {
                  var20 = MathUtil.R(RaytraceUtil.f().zCoord - var2, var14, var16);
               } else {
                  var20 = MathUtil.R(RaytraceUtil.f().zCoord + var2, var14, var16);
               }
            } else {
               var20 = MathUtil.R(RaytraceUtil.f().zCoord + var2, var14, var16);
            }
            break;
         case 5:
            var18 = var10;
            if (var6 <= var16) {
               if (var14 + var2 > var6) {
                  var20 = MathUtil.R(RaytraceUtil.f().zCoord + var2, var14, var16);
               } else {
                  var20 = MathUtil.R(RaytraceUtil.f().zCoord - var2, var14, var16);
               }
            } else {
               var20 = MathUtil.R(RaytraceUtil.f().zCoord - var2, var14, var16);
            }
            break;
         default:
            var18 = Math.max(var0.getX() + 0.05, Math.min(RaytraceUtil.f().xCoord + var2, var0.getX() + 1 - 0.05));
            var12 = Math.max(var0.getY() + 0.05, Math.min(RaytraceUtil.f().yCoord, var0.getY() + 1 - 0.05));
            var20 = Math.max(var0.getZ() + 0.05, Math.min(RaytraceUtil.f().zCoord + var2, var0.getZ() + 1 - 0.05));
      }

      return new Vec3(var18, var12, var20);
   }

   public static float[] u(long var0, BlockPos var2, EnumFacing var3, double var4) {
      var0 = a ^ var0;
      long var6 = var0 ^ 8400881462379L;
      return L( RaytraceUtil.t(var2, var6, var3, var4));
   }

   public static float[] W(long var0, Entity var2) {
      var0 = a ^ var0;
      long var3 = var0 ^ 27794271208097L;
      return J(var2, var3, 0.0);
   }

   public static boolean J(long var0, Vec3 var2, double var3) {
      return n(0L, var2) <= var3;
   }

   public static float n(long var0, Vec3 var2) {
      Vec3 var3 = RaytraceUtil.f();
      if (j.thePlayer.getEntityBoundingBox().isVecInside(var2)) {
         return 0.0F;
      }

      double var4 = var2.xCoord - var3.xCoord;
      double var6 = var2.zCoord - var3.zCoord;
      return Math.abs(MathHelper.wrapAngleTo180_float((float)(Math.atan2(var6, var4) * 180.0 / Math.PI) - 90.0F - RotationManager.p())) * 2.0F;
   }

   public static boolean B(char var0, int var1, BlockPos var2, short var3, EnumFacing var4, double var5) {
      long var7 = ((long)var0 << 48 | (long)var1 << 32 >>> 16 | (long)var3 << 48 >>> 48) ^ a;
      long var9 = var7 ^ 63445506751439L;
      return L(var2, var9, var4) <= var5;
   }

   public static float[] J(Entity var0, long var1, double var3) {
      var1 = a ^ var1;
      int var5 = (int)((var1 ^ 44510346491753L) >>> 32);
      int var6 = (int)((var1 ^ 44510346491753L) << 32 >>> 48);
      int var7 = (int)((var1 ^ 44510346491753L) << 48 >>> 48);
      return L( RaytraceUtil.w(var0, var5, (short)var6, var3, (char)var7));
   }

   public static boolean b(long var0, Entity var2, double var3) {
      var0 = a ^ var0;
      long var5 = var0 ^ 45667781347317L;
      return e(var2, var5) <= var3;
   }

   public static float[] W(Vec3 var0, Vec3 var1) {
      double var2 = var1.xCoord;
      double var4 = var1.yCoord;
      double var6 = var1.zCoord;
      double var8 = var0.xCoord - var2;
      double var10 = var0.yCoord - var4;
      double var12 = var0.zCoord - var6;
      double var14 = Math.sqrt(var8 * var8 + var12 * var12);
      float var16 = (float)(Math.toDegrees(Math.atan2(var12, var8)) - 90.0);
      float var17 = (float)(-Math.toDegrees(Math.atan2(var10, var14)));
      if (var17 > 90.0F) {
         var17 = 90.0F;
      }

      if (var17 < -90.0F) {
         var17 = -90.0F;
      }

      return new float[]{var16, var17};
   }

   public static float[] b(long var0, AxisAlignedBB var2, double var3) {
      var0 = a ^ var0;
      int var5 = (int)((var0 ^ 40657676086248L) >>> 48);
      int var6 = (int)((var0 ^ 40657676086248L) << 16 >>> 48);
      int var7 = (int)((var0 ^ 40657676086248L) << 32 >>> 32);
      return L( RaytraceUtil.Q((short)var5, var2, var3, (short)var6, true, var7));
   }

   public static float[] U(long var0, AxisAlignedBB var2) {
      var0 = a ^ var0;
      long var3 = var0 ^ 6339182331406L;
      return b(var3, var2, 0.0);
   }

   public static Vec3 d(float var0, float var1) {
      float var2 = MathHelper.cos(-var1 * (float) (Math.PI / 180.0) - (float) Math.PI);
      float var3 = MathHelper.sin(-var1 * (float) (Math.PI / 180.0) - (float) Math.PI);
      float var4 = -MathHelper.cos(-var0 * (float) (Math.PI / 180.0));
      float var5 = MathHelper.sin(-var0 * (float) (Math.PI / 180.0));
      return new Vec3(var3 * var4, var5, var2 * var4);
   }

   public static float[] y(long var0, AxisAlignedBB var2, double var3) {
      var0 = a ^ var0;
      long var5 = var0 ^ 94980592195592L;
      return L( RaytraceUtil.p(var2, var5, var3));
   }

   public static float L(BlockPos var0, long var1, EnumFacing var3) {
      var1 = a ^ var1;
      long var4 = var1 ^ 129274305025917L;
      return n(0L, RaytraceUtil.Z(var0, var4, var3, 2.147483647E9, false));
   }

   public static float[] S(char var0, int var1, char var2, BlockPos var3, EnumFacing var4) {
      long var5 = ((long)var0 << 48 | (long)var1 << 32 >>> 16 | (long)var2 << 48 >>> 48) ^ a;
      long var7 = var5 ^ 15304447653518L;
      return L( RaytraceUtil.e(var7, var3, var4));
   }

   public static float V(AxisAlignedBB var0, long var1) {
      var1 = a ^ var1;
      int var3 = (int)((var1 ^ 87251360255973L) >>> 48);
      int var4 = (int)((var1 ^ 87251360255973L) << 16 >>> 48);
      int var5 = (int)((var1 ^ 87251360255973L) << 32 >>> 32);
      return n(0L, RaytraceUtil.Q((short)var3, var0, 2.147483647E9, (short)var4, false, var5));
   }

   public static double g(Entity var0) {
      float[] var6 = F(var0, (byte)0, 2595745, 2287551);
      float var7 = MathUtil.M(RotationManager.p(), var6[0]);
      float var8 = MathUtil.M(RotationManager.s(), var6[1]);
      return Math.sqrt(var7 * var7 + var8 * var8);
   }

   public static float[] F(Entity var0, byte var1, int var2, int var3) {
      long var4 = ((long)var1 << 56 | (long)var2 << 32 >>> 8 | (long)var3 << 40 >>> 40) ^ a;
      long var6 = var4 ^ 60493007760609L;
      return p(var0, 0.0, var6);
   }

   static {
      int var2 = 0;
      j = MinecraftRef.c((byte)var2,0L);
   }

   public static float[] P(BlockPos var0, long var1, EnumFacing var3) {
      var1 = a ^ var1;
      long var6 = var1 ^ 106750255356140L;
      return L( RaytraceUtil.o(var0, var3, var6));
   }

   public static float[] L( Vec3 var2) {
      return W(var2, RaytraceUtil.f());
   }

   public static boolean L(long var0, AxisAlignedBB var2, double var3) {
      var0 = a ^ var0;
      long var5 = var0 ^ 10857265435724L;
      return V(var2, var5) <= var3;
   }

   public static float[] h(int var0, AxisAlignedBB var1, byte var2, int var3) {
      long var4 = ((long)var0 << 32 | (long)var2 << 56 >>> 32 | (long)var3 << 40 >>> 40) ^ a;
      long var6 = var4 ^ 61153326020597L;
      return y(var6, var1, 0.0);
   }

   public static float e(Entity var0, long var1) {
      var1 = a ^ var1;
      long var5 = var1 ^ 26243308180328L;
      return n(0L, RaytraceUtil.R(var0, var5, 2.147483647E9, false));
   }

   public static float[] V(long var0, BlockPos var2, EnumFacing var3, double var4) {
      var0 = a ^ var0;
      long var6 = var0 ^ 19006223141732L;
      return L( RaytraceUtil.v(var2, var3, var6, var4));
   }

   public static float[] p(Entity var0, double var1, long var3) {
      var3 = a ^ var3;
      long var7 = var3 ^ 101370038530991L;
      return L( RaytraceUtil.R(var0, var7, var1, true));
   }
}
