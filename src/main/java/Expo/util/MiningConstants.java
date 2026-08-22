package Expo.util;

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











public class MiningConstants {
   public static boolean G;
   public static float s;
   public static float e;
   public static boolean r;
   public static boolean z;
   public static boolean q;
   // update new version
   public static boolean gapAltOnlyStone;
   // update new version
   public static boolean userManualScreenMove;
   public static float X;
   public static int w;
   public static boolean A;
   public static boolean k;
   public static int F;
   public static int h;
   public static float H;
   public static int n;
   public static boolean o;
   public static float g;
   public static float K;
   public static int I;
   public static float Q;
   public static boolean T;
   public static boolean Z;
   public static boolean x;
   public static int D;
   public static float c;
   public static float C;
   public static int L;
   public static int J;
   public static int B;
   public static boolean j;
   public static boolean v;

   static {
      zkm$clinit();
   }

   private static void zkm$clinit() {
      try {

         Cipher var1;
         byte[] var10003 = new byte[]{(byte)0L, 0, 0, 0, 0, 0, 0, 0};

         for (int var2 = 1; var2 < 8; var2++) {
            var10003[var2] = (byte)(91462574718829L << var2 * 8 >>> 56);
         }

         (var1 = Cipher.getInstance("DES/CBC/NoPadding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
         long[] var0 = new long[8];
         int var4 = 0;
         String var5 = "\u008f·úÅ\u0097¤C^ÃÓ\u0098\u0001\u007f\"\u009ds\u009b`Ø~wòçcF'-Bì\u0086Sä\u001cI²3ÆuØÑg;ÌS´õ\u008e.";
         int var6 = "\u008f·úÅ\u0097¤C^ÃÓ\u0098\u0001\u007f\"\u009ds\u009b`Ø~wòçcF'-Bì\u0086Sä\u001cI²3ÆuØÑg;ÌS´õ\u008e.".length();
         int var3 = 0;

         label27:
         while (true) {
            int var10001 = var3;
            var3 += 8;
            byte[] var7 = var5.substring(var10001, var3).getBytes("ISO-8859-1");
            long[] var15 = var0;
            var10001 = var4++;
            long var18 = (var7[0] & 255L) << 56
               | (var7[1] & 255L) << 48
               | (var7[2] & 255L) << 40
               | (var7[3] & 255L) << 32
               | (var7[4] & 255L) << 24
               | (var7[5] & 255L) << 16
               | (var7[6] & 255L) << 8
               | var7[7] & 255L;
            int var20 = -1;

            while (true) {
               long var8 = var18;
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
               long var22 = (var10[0] & 255L) << 56
                  | (var10[1] & 255L) << 48
                  | (var10[2] & 255L) << 40
                  | (var10[3] & 255L) << 32
                  | (var10[4] & 255L) << 24
                  | (var10[5] & 255L) << 16
                  | (var10[6] & 255L) << 8
                  | var10[7] & 255L;
               switch (var20) {
                  case 0:
                     var15[var10001] = var22;
                     if (var3 >= var6) {
                        D = (int)var0[2];
                        n = (int)var0[0];
                        h = (int)var0[3];
                        B = (int)var0[2];
                        F = (int)var0[1];
                        L = (int)var0[3];
                        I = (int)var0[0];
                        j = (var0[5]) != 0;
                        T = (var0[7]) != 0;
                        X = 7.0F;
                        s = 7.0F;
                        w = (int)var0[6];
                        Q = 30.0F;
                        e = 150.0F;
                        r = (var0[4]) != 0;
                        z = (var0[7]) != 0;
                        A = (var0[4]) != 0;
                        C = 10.0F;
                        K = 3.0F;
                        g = 100.0F;
                        v = (var0[4]) != 0;
                        J = (int)var0[4];
                        H = 50.0F;
                        x = (var0[7]) != 0;
                        Z = (var0[7]) != 0;
                        o = (var0[4]) != 0;
                        G = (var0[4]) != 0;
                        c = 2.0F;
                        q = (var0[7]) != 0;
                        k = (var0[4]) != 0;
                        return;
                     }
                     break;
                  default:
                     var15[var10001] = var22;
                     if (var3 < var6) {
                        continue label27;
                     }

                     var5 = "UÛáþ\u0002Å®â\u0010[\u0087xö)\u007fÆ";
                     var6 = "UÛáþ\u0002Å®â\u0010[\u0087xö)\u007fÆ".length();
                     var3 = 0;
               }

               int var17 = var3;
               var3 += 8;
               var7 = var5.substring(var17, var3).getBytes("ISO-8859-1");
               var15 = var0;
               var10001 = var4++;
               var18 = (var7[0] & 255L) << 56
                  | (var7[1] & 255L) << 48
                  | (var7[2] & 255L) << 40
                  | (var7[3] & 255L) << 32
                  | (var7[4] & 255L) << 24
                  | (var7[5] & 255L) << 16
                  | (var7[6] & 255L) << 8
                  | var7[7] & 255L;
               var20 = 0;
            }
         }
      } catch (UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException var13) {
         throw new RuntimeException(var13);
      }
   }
}
