package Expo.internal.synthetic;

import Expo.module.Category;
import java.io.UnsupportedEncodingException;
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


public class CategoryPaletteSwitchMapCategory {
   public static int[] S;

   static {
      try {
         long var11 = 50358455277795L;
         Cipher var1;
         byte[] var10003 = new byte[]{(byte)(var11 >>> 56), 0, 0, 0, 0, 0, 0, 0};

         for (int var2 = 1; var2 < 8; var2++) {
            var10003[var2] = (byte)(var11 << var2 * 8 >>> 56);
         }

         (var1 = Cipher.getInstance("DES/CBC/NoPadding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
         long[] var0 = new long[3];
         int var4 = 0;
         String var5 = "\u0082L\b\n#\u0005\u0012Å\u000fgÎ\t}¹@Ù\u0017\u0096ó¦\u0094£q\u0090";
         int var6 = "\u0082L\b\n#\u0005\u0012Å\u000fgÎ\t}¹@Ù\u0017\u0096ó¦\u0094£q\u0090".length();
         int var3 = 0;

         do {
            int var10001 = var3;
            var3 += 8;
            byte[] var7 = var5.substring(var10001, var3).getBytes("ISO-8859-1");
            long var8 = (var7[0] & 255L) << 56
               | (var7[1] & 255L) << 48
               | (var7[2] & 255L) << 40
               | (var7[3] & 255L) << 32
               | (var7[4] & 255L) << 24
               | (var7[5] & 255L) << 16
               | (var7[6] & 255L) << 8
               | var7[7] & 255L;
            byte[] var10 = var1.doFinal(
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
            long var10004 = (var10[0] & 255L) << 56
               | (var10[1] & 255L) << 48
               | (var10[2] & 255L) << 40
               | (var10[3] & 255L) << 32
               | (var10[4] & 255L) << 24
               | (var10[5] & 255L) << 16
               | (var10[6] & 255L) << 8
               | var10[7] & 255L;
            int var24 = -1;
            var0[(var4++)] = var10004;
         } while (var3 < var6);

         S = new int[Category.values().length];

         try {
            S[Category.Combat.ordinal()] = 1;
         } catch (NoSuchFieldError var21) {
         }

         try {
            S[Category.Movement.ordinal()] = 2;
         } catch (NoSuchFieldError var20) {
         }

         try {
            S[Category.Player.ordinal()] = 3;
         } catch (NoSuchFieldError var19) {
         }

         try {
            S[Category.World.ordinal()] = 4;
         } catch (NoSuchFieldError var18) {
         }

         try {
            S[Category.Visual.ordinal()] = 5;
         } catch (NoSuchFieldError var17) {
         }

         try {
            S[Category.Visual_utility.ordinal()] = (int)var0[2];
         } catch (NoSuchFieldError var16) {
         }

         try {
            S[Category.Misc.ordinal()] = (int)var0[1];
         } catch (NoSuchFieldError var15) {
         }

         try {
            S[Category.Configuration.ordinal()] = (int)var0[0];
         } catch (NoSuchFieldError var14) {
         }
      } catch (UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException var22) {
         throw new RuntimeException(var22);
      }
   }

}
