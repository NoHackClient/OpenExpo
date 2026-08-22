package Expo.internal.accessor;

import Expo.ASM.Util.AsmUtil;
import java.io.IOException;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.objectweb.asm.Type;

public class MethodAccessors {
   private static final byte T = 1;
   private static final Lookup G = MethodHandles.lookup();
   private static final byte t = 2;
   private static final byte y = 0;

   private static Accessor o(Class<?> var0, Class<?>[] var1, String... var2) {
      try {
         return T(var0, var1, var2);
      } catch (IllegalStateException var4) {
         return null;
      }
   }

   private static Set<String> x(Class<?> var0, Class<?>[] var1, String... var2) {
      LinkedHashSet var3 = new LinkedHashSet<>(Arrays.asList(var2));

      try {
         String var4 = Type.getInternalName(var0);
         String var5 = Type.getMethodDescriptor(Type.VOID_TYPE, m(var1));
         var3.addAll(AsmUtil.Q(var4, var5, var2));
      } catch (Throwable var6) {
      }

      return var3;
   }

   private MethodAccessors() {
   }

   private static MethodHandle C(Class<?> var0, String var1, Class<?>... var2) {
      if (var1 != null && !var1.isEmpty()) {
         Class var3 = var0;

         while (var3 != null) {
            try {
               Method var4 = var3.getDeclaredMethod(var1, var2);
               var4.setAccessible(true);
               return G.unreflect(var4);
            } catch (NoSuchMethodException var5) {
               var3 = var3.getSuperclass();
            } catch (IllegalAccessException var6) {
               throw new RuntimeException(var6);
            }
         }

         return null;
      } else {
         return null;
      }
   }

   public static boolean V(Item var0, EntityLivingBase var1, ItemStack var2) {
      return Accessor.A(ItemOnEntitySwingAccessor.S(), new Object[]{var0, var1, var2});
   }

   public static Accessor G(Class var0, String var1, String var2, Class[] var3) {
      return k(var0, var1, var2, var3);
   }

   static RuntimeException h(Throwable var0) {
      return G(var0);
   }

   public static int f(Item var0, ItemStack var1) {
      if (ItemStackAccessor.j() != null) {
         return Accessor.D(ItemStackAccessor.j(), new Object[]{var0, var1});
      } else if (ItemStackAccessor.q() != null) {
         return Accessor.D(ItemStackAccessor.q(), new Object[]{var0, var1 == null ? 0 : var1.getMetadata()});
      } else {
         return var1 == null ? 0 : var1.getMetadata();
      }
   }

   static IOException H(Throwable var0) throws IOException {
      return Y(var0);
   }

   static Accessor O(Class var0, Class[] var1, String[] var2) {
      return o(var0, var1, var2);
   }

   public static boolean o(Entity var0) {
      return Accessor.A(EntityCanRiderInteractAccessor.C(), new Object[]{var0});
   }

   private static Accessor T(Class<?> var0, Class<?>[] var1, String... var2) {
      MethodHandle var3 = null;
      MethodHandle var4 = null;
      Set var5 = x(var0, var1, var2);

      for (String var7 : (Iterable<String>)(var5)) {
         MethodHandle var8 = C(var0, var7, var1);
         if (var8 != null) {
            if (var3 != null) {
               var4 = var8;
               break;
            }

            var3 = var8;
         }
      }

      if (var3 == null) {
         throw new IllegalStateException("Unable to resolve method " + var0.getName() + " " + var5);
      } else {
         return var4 == null ? new Accessor(var3, null, (byte)1, null) : new Accessor(var3, var4, (byte)0, null);
      }
   }

   private static RuntimeException G(Throwable var0) {
      if (var0 instanceof InvocationTargetException && ((InvocationTargetException)var0).getCause() != null) {
         var0 = ((InvocationTargetException)var0).getCause();
      }

      if (var0 instanceof RuntimeException) {
         return (RuntimeException)var0;
      } else if (var0 instanceof Error) {
         throw (Error)var0;
      } else {
         return new RuntimeException(var0);
      }
   }

   public static Accessor C(Class var0, Class[] var1, String[] var2) {
      return T(var0, var1, var2);
   }

   private static IOException Y(Throwable var0) throws IOException {
      if (var0 instanceof InvocationTargetException && ((InvocationTargetException)var0).getCause() != null) {
         var0 = ((InvocationTargetException)var0).getCause();
      }

      if (var0 instanceof IOException) {
         throw (IOException)var0;
      } else if (var0 instanceof RuntimeException) {
         throw (RuntimeException)var0;
      } else if (var0 instanceof Error) {
         throw (Error)var0;
      } else {
         throw new RuntimeException(var0);
      }
   }

   public static boolean n(Item var0, ItemStack var1, ItemStack var2, boolean var3) {
      return Accessor.A(ItemAccessor.p(), new Object[]{var0, var1, var2, var3});
   }

   private static Type[] m(Class<?>[] var0) {
      Type[] var1 = new Type[var0.length];

      for (int var2 = 0; var2 < var0.length; var2++) {
         var1[var2] = Type.getType(var0[var2]);
      }

      return var1;
   }

   private static boolean Z(String var0, String var1) {
      return var0 != null && var0.equals(var1);
   }

   private static Accessor k(Class<?> var0, String var1, String var2, Class<?>... var3) {
      return T(var0, var3, var1, var2);
   }
}
