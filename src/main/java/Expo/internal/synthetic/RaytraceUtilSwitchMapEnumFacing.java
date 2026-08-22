package Expo.internal.synthetic;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import net.minecraft.util.EnumFacing;


public class RaytraceUtilSwitchMapEnumFacing {
   public static int[] b;

   static {
      try {
         long var7 = 54062273509852L;
         Cipher var2;
         byte[] var10003 = new byte[]{(byte)(var7 >>> 56), 0, 0, 0, 0, 0, 0, 0};

         for (int var3 = 1; var3 < 8; var3++) {
            var10003[var3] = (byte)(var7 << var3 * 8 >>> 56);
         }

         (var2 = Cipher.getInstance("DES/CBC/NoPadding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
         long var4 = -2525119406038195691L;
         byte[] var6 = var2.doFinal(
            new byte[]{
               (byte)(var4 >>> 56),
               (byte)(var4 >>> 48),
               (byte)(var4 >>> 40),
               (byte)(var4 >>> 32),
               (byte)(var4 >>> 24),
               (byte)(var4 >>> 16),
               (byte)(var4 >>> 8),
               (byte)var4
            }
         );
         long var17 = (var6[0] & 255L) << 56
            | (var6[1] & 255L) << 48
            | (var6[2] & 255L) << 40
            | (var6[3] & 255L) << 32
            | (var6[4] & 255L) << 24
            | (var6[5] & 255L) << 16
            | (var6[6] & 255L) << 8
            | var6[7] & 255L;
         byte var10001 = -1;
         long var0 = var17;
         b = new int[EnumFacing.values().length];

         try {
            b[EnumFacing.WEST.ordinal()] = 1;
         } catch (NoSuchFieldError var15) {
         }

         try {
            b[EnumFacing.EAST.ordinal()] = 2;
         } catch (NoSuchFieldError var14) {
         }

         try {
            b[EnumFacing.NORTH.ordinal()] = 3;
         } catch (NoSuchFieldError var13) {
         }

         try {
            b[EnumFacing.SOUTH.ordinal()] = 4;
         } catch (NoSuchFieldError var12) {
         }

         try {
            b[EnumFacing.UP.ordinal()] = 5;
         } catch (NoSuchFieldError var11) {
         }

         try {
            b[EnumFacing.DOWN.ordinal()] = (int)var0;
         } catch (NoSuchFieldError var10) {
         }
      } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException var16) {
         throw new RuntimeException(var16);
      }
   }

}
