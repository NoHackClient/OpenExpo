package Expo.enums;

public enum AccountType {
   OFFLINE("Offline"),
   MINECRAFT("Minecraft");

   private final String N;
   private static long a;

   AccountType(String var3) {
      this.N = var3;
   }

   public static AccountType E(String var0) {
      if (var0 == null) {
         return MINECRAFT;
      } else if ("CRACKED".equalsIgnoreCase(var0) || "OFFLINE".equalsIgnoreCase(var0)) {
         return OFFLINE;
      } else {
         return !"PREMIUM".equalsIgnoreCase(var0) && !"MINECRAFT".equalsIgnoreCase(var0) ? MINECRAFT : MINECRAFT;
      }
   }

   static {
      a = 123366051634382L;
   }

   public String S() {
      return this.N;
   }
}
