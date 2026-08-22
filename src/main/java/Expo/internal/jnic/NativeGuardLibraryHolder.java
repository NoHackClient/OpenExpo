package Expo.internal.jnic;

import com.sun.jna.FunctionMapper;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Collections;
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













class NativeGuardLibraryHolder {
   public static Map<String, Object> J;
   public static String[] c;
   public static Map d;
   public static FunctionMapper P;
   public static long a;
   public static String[] b;
   public static NativeGuardLibrary v;

   static String a(byte[] var0) {
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

   static String lambda$static$0(NativeLibrary var0, Method var1) {
      return "MessageBoxW";
   }

   static String a(int var0, long var1) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      int var3 = var0 ^ (int)(var1 & 32767L) ^ 21725;
      if (c[var3] == null) {
         Object[] var5;
         try {
            Long var4 = Thread.currentThread().getId();
            var5 = (Object[])d.get(var4);
            if (var5 == null) {
               var5 = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
               d.put(var4, var5);
            }
         } catch (Exception var10) {
            throw new RuntimeException("Expo/internal/jnic/NativeGuardLibrary", var10);
         }

         byte[] var7 = new byte[8];
         var7[0] = (byte)(var1 >>> 56);

         for (int var8 = 1; var8 < 8; var8++) {
            var7[var8] = (byte)(var1 << var8 * 8 >>> 56);
         }

         DESKeySpec var11 = new DESKeySpec(var7);
         SecretKey var9 = ((SecretKeyFactory)var5[1]).generateSecret(var11);
         ((Cipher)var5[0]).init(2, var9, (IvParameterSpec)var5[2]);
         byte[] var6 = b[var3].getBytes("ISO-8859-1");
         c[var3] = a(((Cipher)var5[0]).doFinal(var6));
      }

      return c[var3];
   }

   private static void zkm$clinit() {
      try {
         long var0 = a ^ 8433688713687L;
         long var2 = var0 ^ 117761483867314L;
         d = new HashMap(13);
         Cipher var4;
         byte[] var10003 = new byte[]{(byte)(var0 >>> 56), 0, 0, 0, 0, 0, 0, 0};

         for (int var5 = 1; var5 < 8; var5++) {
            var10003[var5] = (byte)(var0 << var5 * 8 >>> 56);
         }

         (var4 = Cipher.getInstance("DES/CBC/PKCS5Padding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
         String[] var6 = new String[2];
         int var7 = 0;
         String var8 = "\u0098®\u0084 \u0080±\u008c¢MÁÓ\u008c\u0005ÄèY\u0018\u009a¹Ëço:\u0014¿±ë\u0016Gtk\fJý»(\u001d'bù=";
         int var9 = "\u0098®\u0084 \u0080±\u008c¢MÁÓ\u008c\u0005ÄèY\u0018\u009a¹Ëço:\u0014¿±ë\u0016Gtk\fJý»(\u001d'bù=".length();
         char var10 = 16;
         int var11 = -1;

         while (true) {
            byte[] var12 = var4.doFinal(var8.substring(++var11, var11 + var10).getBytes("ISO-8859-1"));
            String var16 = a(var12).intern();
            var6[var7++] = var16;
            if ((var11 += var10) >= var9) {
               b = var6;
               c = new String[2];
               P = NativeGuardLibraryHolder::lambda$static$0;
               J = Collections.unmodifiableMap(new NativeGuardLibraryOptions(var2));
               v = (NativeGuardLibrary)Native.loadLibrary("user32", NativeGuardLibrary.class, J);
               return;
            }

            var10 = var8.charAt(var11);
         }
      } catch (UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException var13) {
         throw new RuntimeException(var13);
      }
   }

   static {
      a = 25664339679373L;
      zkm$clinit();
   }
}
