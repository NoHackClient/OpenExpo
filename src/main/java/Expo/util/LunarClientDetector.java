package Expo.util;

public class LunarClientDetector {
   private static long a;
   private static Boolean P;

   public static boolean q(long var0) {
      if (P == null) {
         P = z("com.moonsworth.lunar.genesis.Genesis") || z("lunar.GenesisLauncher");
      }

      return P;
   }

   private static boolean isAttribute(String var0, ClassLoader var1) {
      if (var1 == null) {
         return false;
      }

      try {
         Class.forName(var0, false, var1);
         return true;
      } catch (Throwable var3) {
         return false;
      }
   }

   private static boolean z(String var0) {
      ClassLoader var1 = Thread.currentThread().getContextClassLoader();
      if (isAttribute(var0, var1)) {
         return true;
      } else {
         return isAttribute(var0, LunarClientDetector.class.getClassLoader()) ? true : isAttribute(var0, ClassLoader.getSystemClassLoader());
      }
   }

   static {
      a = 18858808947312L;
   }

   private LunarClientDetector() {
   }
}
