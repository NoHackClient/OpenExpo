package Expo.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.Minecraft;

public class MinecraftRef {
   private static volatile Minecraft z;
   private static String[] q;
   private static String[] w;
   private static Map d;
   private static String[] b;
   private static long a;
   private static String[] c;

   private MinecraftRef() {
   }

   static {
      a = 86858850250517L;
      d = new HashMap(13);
      b = new String[]{"\u0093\u00a3EZ\u008f\u00b9\u00b0\u00a8\u000c\u008e\u0016`&\u00a4\u00d6\u001b", ";\u00ecas\u00d8s8\u009c\u00b4-\u0016\u00853f\u0004\u00993\u0081\u00a5\u0013\u00b9\u00be\u009b\u00c3\u0087c\u008b\u00a8 Zr9_/\u00a1.\u00da\u0086\u00b5z", "\u0085!\u00f2\u0019O\u0089\\\tT\u00ce\u00ec4o\u009b\u001f\u001e", "\u00f7\u0007o\u009f\u00fb\u00ac\u00b5 vT`|}\u000f\u00081", "U\u00de\u0085\u00aa\u00bc\u0095e\u00d8s\u00a9:\u00c4wF\u00b4\u00d1"};
      c = new String[5];
      w = new String[]{"getMinecraft", "getMinecraft", "A"};
      q = new String[]{"theMinecraft", "theMinecraft", "S"};
   }

   private static Minecraft d() {
      Class<Minecraft> var0 = Minecraft.class;

      for (String var4 : q) {
         try {
            Field var5 = var0.getDeclaredField(var4);
            var5.setAccessible(true);
            Object var6 = var5.get(null);
            if (var6 instanceof Minecraft) {
               return (Minecraft)var6;
            }
         } catch (Throwable var8) {
         }
      }

      for (Field var12 : var0.getDeclaredFields()) {
         if (Modifier.isStatic(var12.getModifiers()) && Minecraft.class.isAssignableFrom(var12.getType())) {
            try {
               var12.setAccessible(true);
               Object var13 = var12.get(null);
               if (var13 instanceof Minecraft) {
                  return (Minecraft)var13;
               }
            } catch (Throwable var7) {
            }
         }
      }

      return null;
   }

   public static Minecraft c(byte var0, long var1) {
      Minecraft var5 = z;
      if (var5 != null) {
         return var5;
      }

      Minecraft var6 = D();
      if (var6 == null) {
         var6 = d();
      }

      if (var6 == null) {
         throw new IllegalStateException(why());
      }

      z = var6;
      return var6;
   }

   private static String why() {
      StringBuilder var0 = new StringBuilder("Cannot resolve Minecraft singleton: ");
      var0.append(Minecraft.class.getName()).append(" was reached, but ");
      append(var0, "accessor", w, methods());
      var0.append("; ");
      append(var0, "field", q, fields());
      var0.append("; and the reflective sweep of every static accessor and every static field of")
          .append(" that class produced no instance either.  MinecraftRef.c was asked for the")
          .append(" singleton before Minecraft had been constructed, or the class on the classpath")
          .append(" is not the one the game runs.");
      return var0.toString();
   }

   private static void append(StringBuilder var0, String var1, String[] var2,
                              java.util.Set<String> var3) {
      var0.append("no named ").append(var1).append(" of ").append(java.util.Arrays.toString(var2))
          .append(" yielded one (");
      int var4 = 0;

      for (String var6 : var2) {
         var0.append(var4++ == 0 ? "" : ", ").append(var6)
             .append(var3.contains(var6) ? " present but empty" : " absent");
      }

      var0.append(')');
   }

   private static java.util.Set<String> methods() {
      java.util.Set<String> var0 = new java.util.HashSet<String>();

      for (Method var2 : Minecraft.class.getDeclaredMethods()) {
         if (Modifier.isStatic(var2.getModifiers()) && var2.getParameterTypes().length == 0) {
            var0.add(var2.getName());
         }
      }

      return var0;
   }

   private static java.util.Set<String> fields() {
      java.util.Set<String> var0 = new java.util.HashSet<String>();

      for (Field var2 : Minecraft.class.getDeclaredFields()) {
         if (Modifier.isStatic(var2.getModifiers())) {
            var0.add(var2.getName());
         }
      }

      return var0;
   }

   private static Minecraft D() {
      Class<Minecraft> var0 = Minecraft.class;

      for (String var4 : w) {
         try {
            Method var5 = var0.getDeclaredMethod(var4);
            var5.setAccessible(true);
            Object var6 = var5.invoke(null);
            if (var6 instanceof Minecraft) {
               return (Minecraft)var6;
            }
         } catch (Throwable var8) {
         }
      }

      for (Method var12 : var0.getDeclaredMethods()) {
         if (Modifier.isStatic(var12.getModifiers()) && var12.getParameterTypes().length == 0 && Minecraft.class.isAssignableFrom(var12.getReturnType())) {
            try {
               var12.setAccessible(true);
               Object var13 = var12.invoke(null);
               if (var13 instanceof Minecraft) {
                  return (Minecraft)var13;
               }
            } catch (Throwable var7) {
            }
         }
      }

      return null;
   }
}
