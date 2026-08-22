package Expo.ASM.Util;

public final class OwnerNamePair {
   private final String S;
   private final String n;

   static String U(OwnerNamePair var0) {
      return var0.S;
   }

   public OwnerNamePair(String var1, String var2) {
      this.S = var1;
      this.n = var2;
   }

   static String A(OwnerNamePair var0) {
      return var0.n;
   }
}
