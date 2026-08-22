package Expo.ASM;

import net.minecraft.launchwrapper.IClassTransformer;
import org.apache.logging.log4j.LogManager;

public class EncryptedClassMarkerHook implements IClassTransformer {
   private static final String U = "Expo.";
   private static Class<?> o;
   private static final byte[] m = new byte[]{-54, -2, -70, -66};
   private static final byte[] V = new byte[]{69, 88, 80, 79, 69, 78, 67, 49};
   private static final String e = "EXPOSTUB1";

   public byte[] transform(String var1, String var2, byte[] var3) {
      if (var3 == null) {
         return null;
      }

      String var4 = x(var1, var2);
      if (var4 == null || !var4.startsWith("Expo.") || f(var4)) {
         return var3;
      }

      if (!f(var3)) {
         return var3;
      }

      return var3;
   }

   private static int i(byte[] var0, int var1) {
      return (var0[var1] & 0xFF) << 8 | var0[var1 + 1] & 0xFF;
   }

   private static String x(String var0, String var1) {
      String var2 = var1 != null && !var1.isEmpty() ? var1 : var0;
      return var2 == null ? null : var2.replace('/', '.');
   }

   private static boolean f(String var0) {
      return var0.startsWith("Expo.Protected.")
         || var0.startsWith("Expo.Injection.")
         || var0.startsWith("Expo.Obfuscation.")
         || var0.startsWith("Expo.ASM.Transformers.")
         || T(var0)
         || "Expo.util.BuildProfile".equals(var0)
         || "Expo.util.BuildInfo".equals(var0)
         || "Expo.ui.swing.Notification".equals(var0);
   }

   private static boolean h(byte[] var0, byte[] var1) {
      if (var0 != null && var0.length >= var1.length) {
         for (int var2 = 0; var2 < var1.length; var2++) {
            if (var0[var2] != var1[var2]) {
               return false;
            }
         }

         return true;
      } else {
         return false;
      }
   }

   public EncryptedClassMarkerHook() {
      LogManager.getLogger("Expo Class Transformer").info("Class Transformer initialized");
   }

   private static boolean A(byte[] var0, int var1, int var2) {
      if (var2 != "EXPOSTUB1".length()) {
         return false;
      }

      for (int var3 = 0; var3 < var2; var3++) {
         if (var0[var1 + var3] != "EXPOSTUB1".charAt(var3)) {
            return false;
         }
      }

      return true;
   }

   private static boolean f(byte[] var0) {
      if (h(var0, V)) {
         return true;
      }

      if (h(var0, m) && var0.length >= 10) {
         int var1 = i(var0, 8);
         int var2 = 10;

         for (int var3 = 1; var3 < var1 && var2 < var0.length; var3++) {
            int var4 = var0[var2++] & 255;
            switch (var4) {
               case 1:
                  if (var2 + 2 > var0.length) {
                     return false;
                  }

                  int var5 = i(var0, var2);
                  var2 += 2;
                  if (var5 < 0 || var2 + var5 > var0.length) {
                     return false;
                  }

                  if (A(var0, var2, var5)) {
                     return true;
                  }

                  var2 += var5;
                  break;
               case 2:
               case 13:
               case 14:
               default:
                  return false;
               case 3:
               case 4:
                  var2 += 4;
                  break;
               case 5:
               case 6:
                  var2 += 8;
                  var3++;
                  break;
               case 7:
               case 8:
               case 16:
               case 19:
               case 20:
                  var2 += 2;
                  break;
               case 9:
               case 10:
               case 11:
               case 12:
               case 17:
               case 18:
                  var2 += 4;
                  break;
               case 15:
                  var2 += 3;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   private static boolean T(String var0) {
      int var1 = "Expo.ASM.".length();
      return var0.startsWith("Expo.ASM.") && var0.indexOf(46, var1) < 0;
   }
}
