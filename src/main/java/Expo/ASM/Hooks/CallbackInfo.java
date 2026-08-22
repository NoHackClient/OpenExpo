package Expo.ASM.Hooks;

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

public class CallbackInfo {
   private boolean p;
   private static long c;
   private static long a;

   public boolean isCancelled() {
      return this.p;
   }

   static {
      try {
         a = 80856353992445L;
         long var0 = a ^ 41065500721922L;
         Cipher var2;
         byte[] var10003 = new byte[]{(byte)(var0 >>> 56), 0, 0, 0, 0, 0, 0, 0};

         for (int var3 = 1; var3 < 8; var3++) {
            var10003[var3] = (byte)(var0 << var3 * 8 >>> 56);
         }

         (var2 = Cipher.getInstance("DES/CBC/NoPadding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
         long var4 = 1924175357439203220L;
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
         long var8 = (var6[0] & 255L) << 56
            | (var6[1] & 255L) << 48
            | (var6[2] & 255L) << 40
            | (var6[3] & 255L) << 32
            | (var6[4] & 255L) << 24
            | (var6[5] & 255L) << 16
            | (var6[6] & 255L) << 8
            | var6[7] & 255L;
         byte var10001 = -1;
         c = var8;
      } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException var7) {
         throw new RuntimeException(var7);
      }
   }

   public void cancel() {
      this.p = (c) != 0;
   }
}
