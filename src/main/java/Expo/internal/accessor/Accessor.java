package Expo.internal.accessor;

import Expo.internal.synthetic.AccessorCtorMarker;
import java.lang.invoke.MethodHandle;











public final class Accessor {
   private final MethodHandle L;
   private final MethodHandle k;
   private byte r;

   public static int D(Accessor var0, Object[] var1) {
      return var0.x(var1);
   }

   private MethodHandle P() {
      return this.r == 2 ? this.L : this.k;
   }

   private float B(Object... var1) {
      return (Float)this.R(var1);
   }

   private Object R(Object... var1) {
      MethodHandle var2 = this.P();

      try {
         return var2.invokeWithArguments(var1);
      } catch (Throwable var8) {
         MethodHandle var4 = this.G();
         if (var4 == null) {
            throw MethodAccessors.h(var8);
         }

         try {
            Object var5 = var4.invokeWithArguments(var1);
            this.R(var4);
            return var5;
         } catch (Throwable var7) {
            RuntimeException var6 = MethodAccessors.h(var7);
            var6.addSuppressed(var8);
            throw var6;
         }
      }
   }

   static boolean A(Accessor var0, Object[] var1) {
      return var0.E(var1);
   }

   public static float t(Accessor var0, Object[] var1) {
      return var0.B(var1);
   }

   static void v(Accessor var0, Object[] var1) {
      var0.M(var1);
   }

   private MethodHandle G() {
      return this.r == 2 ? this.k : this.L;
   }

   private void R(MethodHandle var1) {
      if (var1 != null) {
         this.r = (byte)(var1 == this.k ? 1 : 2);
      }
   }

   private boolean E(Object... var1) {
      return (Boolean)this.R(var1);
   }

   private Accessor(MethodHandle var1, MethodHandle var2, byte var3) {
      this.k = var1;
      this.L = var2;
      this.r = var3;
   }

   Accessor(MethodHandle var1, MethodHandle var2, byte var3, AccessorCtorMarker var4) {
      this(var1, var2, var3);
   }

   private void M(Object... var1) {
      this.R(var1);
   }

   private int x(Object... var1) {
      return (Integer)this.R(var1);
   }
}
