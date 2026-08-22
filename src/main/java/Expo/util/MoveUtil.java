package Expo.util;

import Expo.ExpoClient;
import Expo.event.events.JumpEvent;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;

public class MoveUtil {
   private static Map d;
   private static long a;
   public static final double n = 0.42F;
   private static Integer[] c;
   private static Object[] e;
   public static final double p = 0.5203620003898759;
   private static String[] f;
   private static Minecraft z;
   private static long[] b;
   public static final double Y = 0.221;

   public static boolean i(double var0, double var2) {
      return I(var0, var2, -1.0);
   }

   public static double A(double var0) {
      return z.thePlayer.isPotionActive(Potion.jump) ? var0 + (z.thePlayer.getActivePotionEffect(Potion.jump).getAmplifier() + 1) * 0.1F : var0;
   }

   public static boolean a(EntityLivingBase var0) {
      return var0 != null && (var0.moveForward != 0.0F || var0.moveStrafing != 0.0F);
   }

   public static double I(EntityPlayer var0) {
      double var1 = 0.2873;
      if (var0.isPotionActive(Potion.moveSpeed)) {
         int var3 = var0.getActivePotionEffect(Potion.moveSpeed).getAmplifier();
         var1 *= 1.0 + 0.2 * (var3 + 1);
      }

      return var1;
   }

   private static int a(int var0, long var1) {
      int var3 = var0 ^ (int)(var1 & 32767L) ^ 16306;
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
            throw new RuntimeException("Expo/util/MoveUtil", var14);
         }

         int var15 = (var10[4] & 255) << 24 | (var10[5] & 255) << 16 | (var10[6] & 255) << 8 | var10[7] & 255;
         c[var3] = var15;
      }

      return c[var3];
   }

   public static int K() {
      int var0 = 0;
      if (z.gameSettings.keyBindLeft.isKeyDown()) {
         var0++;
      }

      if (z.gameSettings.keyBindRight.isKeyDown()) {
         var0--;
      }

      return var0;
   }

   public static void h(float var0, char var1, boolean var2, long var3) {
      long var5 = ((long)var1 << 48 | var3 << 16 >>> 16) ^ a;
      long var9 = var5 ^ 3770881145512L;
      JumpEvent var11 = new JumpEvent((float)P(), var0);
      ExpoClient.w.e(var11, var9);
      if (!var11.a()) {
         z.thePlayer.motionY = var11.o();
         if (z.thePlayer.isPotionActive(Potion.jump)) {
            z.thePlayer.motionY = z.thePlayer.motionY + (z.thePlayer.getActivePotionEffect(Potion.jump).getAmplifier() + 1) * 0.1F;
         }

         if (var2) {
            float var12 = var11.j() * (float) (Math.PI / 180.0);
            z.thePlayer.motionX = z.thePlayer.motionX - MathHelper.sin(var12) * 0.2F;
            z.thePlayer.motionZ = z.thePlayer.motionZ + MathHelper.cos(var12) * 0.2F;
         }

         z.thePlayer.isAirBorne = true;
      }
   }

   public static boolean A$r1() {
      return Math.abs(z.thePlayer.moveForward) >= 0.8F || Math.abs(z.thePlayer.moveStrafing) >= 0.8F;
   }

   public static double y() {
      float var2 = RotationManager.p();
      if (z.thePlayer.movementInput.moveForward < 0.0F) {
         var2 += 180.0F;
      }

      float var3 = 1.0F;
      if (z.thePlayer.movementInput.moveForward < 0.0F) {
         var3 = -0.5F;
      } else if (z.thePlayer.movementInput.moveForward > 0.0F) {
         var3 = 0.5F;
      }

      if (z.thePlayer.movementInput.moveStrafe > 0.0F) {
         var2 -= 90.0F * var3;
      }

      if (z.thePlayer.movementInput.moveStrafe < 0.0F) {
         var2 += 90.0F * var3;
      }

      return Math.toRadians(var2);
   }

   public static double k(long var0) {
      return U(29842568986254L, z.thePlayer);
   }

   public static boolean V(int var0, char var1, int var2, boolean var3) {
      long var4 = ((long)var0 << 32 | (long)var1 << 48 >>> 32 | (long)var2 << 48 >>> 48) ^ a;
      return var3
         ? z.thePlayer.moveForward >= 0.8F
            && !z.thePlayer.isCollidedHorizontally
            && (z.thePlayer.getFoodStats().getFoodLevel() > a(6529, 7358673102870626750L ^ var4) || z.thePlayer.capabilities.allowFlying)
            && !z.thePlayer.isPotionActive(Potion.blindness)
            && !z.thePlayer.isUsingItem()
            && !z.thePlayer.isSneaking()
         : A$r1();
   }

   public static double P() {
      return A(0.42F);
   }

   public static double U(long var0, EntityPlayer var2) {
      return var2 != null && var2.ticksExisted >= 1 ? F(var2.lastTickPosX, var2.lastTickPosZ) * (20.0F * ClientUtil.b(75703014522979L).timerSpeed) : 0.0;
   }

   public static double H(int var0, int var1) {
      return Math.sqrt(var0 * var0 + var1 * var1);
   }

   public static boolean I(double var0, double var2, double var4) {
      AxisAlignedBB var6 = z.thePlayer.getEntityBoundingBox().offset(var0, var4, var2);
      return z.theWorld.getCollidingBoundingBoxes(z.thePlayer, var6).isEmpty();
   }

   public static float X(int var0, short var1) {
      return i(RotationManager.p(), f(), K());
   }

   public static double u(EntityPlayer var0) {
      return Math.sqrt(var0.motionX * var0.motionX + var0.motionZ * var0.motionZ);
   }

   public static void y(double var0, long var2) {
      var2 = a ^ var2;
      int var4 = (int)((var2 ^ 6776122025060L) >>> 56);
      double var7 = MinecraftRef.c((byte)var4,0L).gameSettings.keyBindForward.isKeyDown()
         ? 1.0
         : (MinecraftRef.c((byte)var4,0L).gameSettings.keyBindBack.isKeyDown() ? -1.0 : 0.0);
      double var9 = MinecraftRef.c((byte)var4,0L).gameSettings.keyBindLeft.isKeyDown()
         ? 1.0
         : (MinecraftRef.c((byte)var4,0L).gameSettings.keyBindRight.isKeyDown() ? -1.0 : 0.0);
      float var11 = RotationManager.p();
      if (o()) {
         if (var7 != 0.0) {
            if (var9 > 0.0) {
               var11 += var7 > 0.0 ? -45 : 45;
            } else if (var9 < 0.0) {
               var11 += var7 > 0.0 ? 45 : -45;
            }

            var9 = 0.0;
            if (var7 > 0.0) {
               var7 = 1.0;
            } else if (var7 < 0.0) {
               var7 = -1.0;
            }
         }

         double var12 = Math.cos(Math.toRadians(var11 + 89.5F));
         double var14 = Math.sin(Math.toRadians(var11 + 89.5F));
         z.thePlayer.motionX = var7 * var0 * var12 + var9 * var0 * var14;
         z.thePlayer.motionZ = var7 * var0 * var14 - var9 * var0 * var12;
      } else {
         z.thePlayer.motionX = 0.0;
         z.thePlayer.motionZ = 0.0;
      }
   }

   public static boolean o() {
      return a(z.thePlayer);
   }

   public static void m() {
      z.thePlayer.motionX = z.thePlayer.motionY = z.thePlayer.motionZ = 0.0;
   }

   public static double p(float var0, double var1, double var3) {
      if (var1 < 0.0) {
         var0 += 180.0F;
      }

      float var5 = 1.0F;
      if (var1 < 0.0) {
         var5 = -0.5F;
      } else if (var1 > 0.0) {
         var5 = 0.5F;
      }

      if (var3 > 0.0) {
         var0 -= 90.0F * var5;
      }

      if (var3 < 0.0) {
         var0 += 90.0F * var5;
      }

      return Math.toRadians(var0);
   }

   public static double V(Entity var0) {
      return Math.sqrt(var0.motionX * var0.motionX + var0.motionZ * var0.motionZ);
   }

   public static boolean v(long var0) {
      return z.thePlayer.moveForward >= 0.8F
         && !z.thePlayer.isCollidedHorizontally
         && (z.thePlayer.getFoodStats().getFoodLevel() > a(27762, 6312494855400308921L) || z.thePlayer.capabilities.allowFlying)
         && !z.thePlayer.isPotionActive(Potion.blindness)
         && !z.thePlayer.isSneaking();
   }

   public static boolean Q() {
      AxisAlignedBB var0 = z.thePlayer.getEntityBoundingBox();
      AxisAlignedBB var1 = var0.offset(0.0, -0.01, 0.0);
      return !z.thePlayer.worldObj.getCollidingBoundingBoxes(z.thePlayer, var1).isEmpty();
   }

   private static void a() {
      e[0] = "QY\r\rEUZ";
      e[1] = float.class;
      f[1] = "java/lang/Float";
      e[2] = "p\"^%7b{-OjVlp&K0";
      e[3] = "\u0016\u0019*d\bpYF:\u0005\u0013\u0000\u0012\u001a`g\u0000aPO->k";
   }

   public static void L() {
      z.thePlayer.motionX = z.thePlayer.motionZ = 0.0;
   }

   public static double V() {
      return V(z.thePlayer);
   }

   public static double F(double var0, double var2) {
      double var4 = z.thePlayer.posX - var0;
      double var6 = z.thePlayer.posZ - var2;
      return MathHelper.sqrt_double(var4 * var4 + var6 * var6);
   }

   public static double A() {
      return I(z.thePlayer);
   }

   public static int f() {
      int var0 = 0;
      if (z.gameSettings.keyBindForward.isKeyDown()) {
         var0++;
      }

      if (z.gameSettings.keyBindBack.isKeyDown()) {
         var0--;
      }

      return var0;
   }

   public static void r(double var0) {
      if (o()) {
         double var9 = y();
         z.thePlayer.motionX = -Math.sin(var9) * var0 / 4.0;
         z.thePlayer.motionZ = Math.cos(var9) * var0 / 4.0;
      }
   }

   public static float i(float var0, float var1, float var2) {
      if (var1 < 0.0F) {
         var0 += 180.0F;
      }

      if (var2 != 0.0F) {
         float var3 = var1 == 0.0F ? 1.0F : 0.5F * Math.signum(var1);
         var0 += -90.0F * var3 * Math.signum(var2);
      }

      return MathHelper.wrapAngleTo180_float(var0);
   }

   public static double r() {
      return u(z.thePlayer);
   }

   static {
      a = 118218968134521L;
      zkm$clinit();
      z = MinecraftRef.c((byte)0, 0L);
   }

   private static void zkm$clinit() {
      try {
         long var11 = a ^ 104661204716309L;
         e = new Object[4];
         f = new String[4];
         a();
         d = new HashMap(13);
         Cipher var0;
         byte[] var10003 = new byte[]{(byte)(var11 >>> 56), 0, 0, 0, 0, 0, 0, 0};

         for (int var1 = 1; var1 < 8; var1++) {
            var10003[var1] = (byte)(var11 << var1 * 8 >>> 56);
         }

         (var0 = Cipher.getInstance("DES/CBC/NoPadding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
         long[] var6 = new long[6];
         int var3 = 0;
         String var4 = "Vò9\u000f\u0000s±D\u0098æ\u009fÿW\u0019\u0017vlm\u001eæ=\u0010\u0003e\b\u0090¿£'\u0005\u0013W";
         int var5 = "Vò9\u000f\u0000s±D\u0098æ\u009fÿW\u0019\u0017vlm\u001eæ=\u0010\u0003e\b\u0090¿£'\u0005\u0013W".length();
         int var2 = 0;

         label27:
         while (true) {
            int var10001 = var2;
            var2 += 8;
            byte[] var7 = var4.substring(var10001, var2).getBytes("ISO-8859-1");
            long[] var18 = var6;
            var10001 = var3++;
            long var21 = (var7[0] & 255L) << 56
               | (var7[1] & 255L) << 48
               | (var7[2] & 255L) << 40
               | (var7[3] & 255L) << 32
               | (var7[4] & 255L) << 24
               | (var7[5] & 255L) << 16
               | (var7[6] & 255L) << 8
               | var7[7] & 255L;
            int var23 = -1;

            while (true) {
               long var8 = var21;
               byte[] var10 = var0.doFinal(
                  new byte[]{
                     (byte)(var8 >>> 56),
                     (byte)(var8 >>> 48),
                     (byte)(var8 >>> 40),
                     (byte)(var8 >>> 32),
                     (byte)(var8 >>> 24),
                     (byte)(var8 >>> 16),
                     (byte)(var8 >>> 8),
                     (byte)var8
                  }
               );
               long var25 = (var10[0] & 255L) << 56
                  | (var10[1] & 255L) << 48
                  | (var10[2] & 255L) << 40
                  | (var10[3] & 255L) << 32
                  | (var10[4] & 255L) << 24
                  | (var10[5] & 255L) << 16
                  | (var10[6] & 255L) << 8
                  | var10[7] & 255L;
               switch (var23) {
                  case 0:
                     var18[var10001] = var25;
                     if (var2 >= var5) {
                        b = var6;
                        c = new Integer[6];
                        return;
                     }
                     break;
                  default:
                     var18[var10001] = var25;
                     if (var2 < var5) {
                        continue label27;
                     }

                     var4 = "vxÍ×e\u000b¦ã\u008b#]\u000b¥\u007f]©";
                     var5 = "vxÍ×e\u000b¦ã\u008b#]\u000b¥\u007f]©".length();
                     var2 = 0;
               }

               int var20 = var2;
               var2 += 8;
               var7 = var4.substring(var20, var2).getBytes("ISO-8859-1");
               var18 = var6;
               var10001 = var3++;
               var21 = (var7[0] & 255L) << 56
                  | (var7[1] & 255L) << 48
                  | (var7[2] & 255L) << 40
                  | (var7[3] & 255L) << 32
                  | (var7[4] & 255L) << 24
                  | (var7[5] & 255L) << 16
                  | (var7[6] & 255L) << 8
                  | var7[7] & 255L;
               var23 = 0;
            }
         }
      } catch (UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException var16) {
         throw new RuntimeException(var16);
      }
   }
}
