package Expo.internal.auth;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

public class TrustAllSslContext {
   private static SSLContext O;

   static {
      zkm$clinit();
   }

   private static String a(byte[] var0) {
      int var1 = 0;
      int var2;
      char[] var3 = new char[var2 = var0.length];

      for (int var4 = 0; var4 < var2; var4++) {
         int var5;
         if ((var5 = 255 & var0[var4]) < 192) {
            var3[var1++] = (char)var5;
         } else if (var5 < 224) {
            char var6 = (char)((char)(var5 & 31) << 6);
            byte var8 = var0[++var4];
            var6 = (char)(var6 | (char)(var8 & 63));
            var3[var1++] = var6;
         } else if (var4 < var2 - 2) {
            char var12 = (char)((char)(var5 & 15) << '\f');
            byte var9 = var0[++var4];
            var12 = (char)(var12 | (char)(var9 & 63) << 6);
            var9 = var0[++var4];
            var12 = (char)(var12 | (char)(var9 & 63));
            var3[var1++] = var12;
         }
      }

      return new String(var3, 0, var1);
   }

   public static SSLContext j() {
      return O;
   }

   private static void zkm$clinit() {
      try {
         Cipher var1;
         byte[] var10003 = new byte[]{(byte)0L, 0, 0, 0, 0, 0, 0, 0};

         for (int var2 = 1; var2 < 8; var2++) {
            var10003[var2] = (byte)(69834604646357L << var2 * 8 >>> 56);
         }

         (var1 = Cipher.getInstance("DES/CBC/PKCS5Padding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
         String[] var0 = new String[6];
         int var6 = 0;
         String var5 = "\u0091ïäÃ%\u0007tÏxéý\u0019¯9\u009eó\u001b\u001aJ´ñ[½aì^\u000bù3ø_Q\u0011×ó\u0018\u0094hÏÿf©15zÖûZÙ\u0005ÙÝÙ×Ì§\u0010')\u0007\u009eÕ½\u001cÔÊ+Ög\u008f%\u001da\u0018ÌüÍå\u001a\u0082\u0088\u0081XÚèÚ\u009f\u0006J¸Ü9M{U\u0094\u0091¯\b\u009dr\u009bÜ\u0088É\u00961";
         int var7 = "\u0091ïäÃ%\u0007tÏxéý\u0019¯9\u009eó\u001b\u001aJ´ñ[½aì^\u000bù3ø_Q\u0011×ó\u0018\u0094hÏÿf©15zÖûZÙ\u0005ÙÝÙ×Ì§\u0010')\u0007\u009eÕ½\u001cÔÊ+Ög\u008f%\u001da\u0018ÌüÍå\u001a\u0082\u0088\u0081XÚèÚ\u009f\u0006J¸Ü9M{U\u0094\u0091¯\b\u009dr\u009bÜ\u0088É\u00961"
            .length();
         char var4 = '8';
         int var17 = -1;

         label40:
         while (true) {
            String var18 = var5.substring(++var17, var17 + var4);
            byte var10001 = -1;

            while (true) {
               byte[] var8 = var1.doFinal(var18.getBytes("ISO-8859-1"));
               String var24 = a(var8).intern();
               switch (var10001) {
                  case 0:
                     var0[var6++] = var24;
                     if ((var17 += var4) >= var7) {
                        try {
                           KeyStore var11 = KeyStore.getInstance(var0[4]);
                           InputStream var12 = TrustAllSslContext.class.getResourceAsStream(var0[2]);
                           if (var12 == null) {
                              throw new RuntimeException(var0[0]);
                           }

                           var11.load(var12, var0[1].toCharArray());
                           TrustManagerFactory var13 = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                           var13.init(var11);
                           O = SSLContext.getInstance(var0[3]);
                           O.init(null, var13.getTrustManagers(), null);
                           HttpsURLConnection.setDefaultSSLSocketFactory(O.getSocketFactory());
                           return;
                        } catch (Exception var14) {
                           throw new RuntimeException(var0[5], var14);
                        }
                     }

                     var4 = var5.charAt(var17);
                     break;
                  default:
                     var0[var6++] = var24;
                     if ((var17 += var4) < var7) {
                        var4 = var5.charAt(var17);
                        continue label40;
                     }

                     var5 = "(\u008b\u009bÒñ(¢ã(e8Ð [\r\u0089i\u0014\u0000\b5²\u0012uQ/a¾2 º¢*\u0002\u0001uX\u00997ìU\u00adZ\u001f\u0093ÍÜ \u0003";
                     var7 = "(\u008b\u009bÒñ(¢ã(e8Ð [\r\u0089i\u0014\u0000\b5²\u0012uQ/a¾2 º¢*\u0002\u0001uX\u00997ìU\u00adZ\u001f\u0093ÍÜ \u0003".length();
                     var4 = '\b';
                     var17 = -1;
               }

               var18 = var5.substring(++var17, var17 + var4);
               var10001 = 0;
            }
         }
      } catch (UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException var15) {
         throw new RuntimeException(var15);
      }
   }
}
