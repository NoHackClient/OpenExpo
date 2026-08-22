package Expo.setting.settings;

import Expo.setting.Setting;
import Expo.util.MathUtil;
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

public class PercentageSetting extends Setting {
   private int n;
   private static long a;
   private static long f;

   public void d(int var1) {
      this.n = var1;
   }

   static {
      try {
         a = 49645478270159L;
         long var0 = a ^ 116504715470166L;
         Cipher var2;
         byte[] var10003 = new byte[]{(byte)(var0 >>> 56), 0, 0, 0, 0, 0, 0, 0};

         for (int var3 = 1; var3 < 8; var3++) {
            var10003[var3] = (byte)(var0 << var3 * 8 >>> 56);
         }

         (var2 = Cipher.getInstance("DES/CBC/NoPadding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
         long var4 = -525348990261965068L;
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
         f = var8;
      } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException var7) {
         throw new RuntimeException(var7);
      }
   }

   public PercentageSetting(String var1, int var2) {
      this.q = var1;
      this.n = var2;
   }

   public void b(int var1, long var2, int var4) {
      this.n = MathUtil.k(var4, 0, (int)f);
   }

   public int k() {
      return this.n;
   }
}
