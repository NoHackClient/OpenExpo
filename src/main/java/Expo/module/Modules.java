package Expo.module;

import Expo.internal.jnic.StockConfigStore;
import java.util.HashMap;
import java.util.Map;

public class Modules {
   private static Map e;
   private static long a;
   private static String b;
   private static long[] c;
   private static boolean G;

   public static <T extends Module> T J(Class<T> var0) {
      return (T)ModuleManager.o.get(var0);
   }

   public static Module I(String var0) {
      if (var0 == null) {
         return null;
      }

      for (Module var2 : ModuleManager.S) {
         if (var2.b().equalsIgnoreCase(var0)) {
            return var2;
         }
      }

      return null;
   }

   public static volatile boolean gatesweep$configSaveUnavailable;

   public static void c(long var0) {
      if (!G) {
         G = true;
         new Thread(() -> {
            try {
               StockConfigStore.o(b);
            } catch (UnsatisfiedLinkError var5x) {
               gatesweep$configSaveUnavailable = true;
            } finally {
               G = false;
            }
         }).start();
      }
   }

   static {
      a = 1034250695874L;
      G = false;
      b = "current";
      e = new HashMap(13);
      c = new long[]{-7749740435155550923L, -7864304823227945179L, 3071478632644015716L};
   }
}
