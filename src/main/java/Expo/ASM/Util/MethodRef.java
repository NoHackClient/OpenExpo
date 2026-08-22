package Expo.ASM.Util;

public final class MethodRef {
   private final String i;
   private final String v;
   private static long a;
   private final String F;

   static String O(MethodRef var0) {
      return var0.i;
   }

   public MethodRef(String var1, String var2, String var3) {
      this.i = var1;
      this.F = var2;
      this.v = var3;
   }

   public int hashCode() {
      int var3 = this.i.hashCode();
      var3 = 31 * var3 + this.F.hashCode();
      return 31 * var3 + this.v.hashCode();
   }

   static {
      a = 94750527607482L;
   }

   static String Q(MethodRef var0) {
      return var0.F;
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      }

      if (!(var1 instanceof MethodRef)) {
         return false;
      }

      MethodRef var2 = (MethodRef)var1;
      return this.i.equals(var2.i) && this.F.equals(var2.F) && this.v.equals(var2.v);
   }

   static String V(MethodRef var0) {
      return var0.v;
   }
}
