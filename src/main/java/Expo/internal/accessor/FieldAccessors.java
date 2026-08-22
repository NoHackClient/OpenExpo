package Expo.internal.accessor;

import Expo.ASM.Util.AsmUtil;
import java.lang.reflect.Field;
import java.util.LinkedHashSet;
import java.util.Set;
import org.objectweb.asm.Type;

public class FieldAccessors {
   private static final byte h = 2;
   private static final byte C = 0;
   private static final byte V = 1;

   static RuntimeException j(String var0, Throwable var1) {
      return x(var0, var1);
   }

   private FieldAccessors() {
   }

   private static boolean F(String var0, String var1) {
      return var0 != null && var0.equals(var1);
   }

   private static Set<String> Z(Class<?> var0, String... var1) {
      LinkedHashSet var2 = new LinkedHashSet();

      for (String var6 : var1) {
         var2.add(var6);
      }

      try {
         var2.addAll(AsmUtil.i(Type.getInternalName(var0), var1));
      } catch (Throwable var7) {
      }

      return var2;
   }

   private static Field r(Class<?> var0, String var1) {
      if (var1 != null && !var1.isEmpty()) {
         for (Class var2 = var0; var2 != null; var2 = var2.getSuperclass()) {
            try {
               Field var3 = var2.getDeclaredField(var1);
               var3.setAccessible(true);
               return var3;
            } catch (NoSuchFieldException var4) {
            }
         }

         return null;
      } else {
         return null;
      }
   }

   private static TypedValueStore g(Class<?> var0, String var1, String var2) {
      Field var3 = null;
      Field var4 = null;

      for (String var6 : Z(var0, var1, var2)) {
         Field var7 = r(var0, var6);
         if (var7 != null) {
            if (var3 != null) {
               var4 = var7;
               break;
            }

            var3 = var7;
         }
      }

      if (var3 == null && var4 == null) {
         throw new IllegalStateException("Unable to resolve field " + var0.getName() + " [" + var1 + ", " + var2 + "]");
      } else if (var3 == null) {
         return new TypedValueStore(var4, null, (byte)2, null);
      } else {
         return var4 == null ? new TypedValueStore(var3, null, (byte)1, null) : new TypedValueStore(var3, var4, (byte)0, null);
      }
   }

   static TypedValueStore X(Class var0, String var1, String var2) {
      return g(var0, var1, var2);
   }

   private static RuntimeException x(String var0, Throwable var1) {
      return var1 instanceof RuntimeException ? (RuntimeException)var1 : new RuntimeException("Unable to access reflected " + var0, var1);
   }
}
