package Expo.util;

import java.util.HashMap;











public final class EnchantmentAbbreviations extends HashMap<Integer, EnchantmentAbbreviation> {
   private static long a;

   public EnchantmentAbbreviations() {
      this.put(0, new EnchantmentAbbreviation("Pr", 4));
      this.put(1, new EnchantmentAbbreviation("Fp", 4));
      this.put(2, new EnchantmentAbbreviation("Ff", 4));
      this.put(3, new EnchantmentAbbreviation("Bp", 4));
      this.put(4, new EnchantmentAbbreviation("Pp", 4));
      this.put(5, new EnchantmentAbbreviation("Re", 3));
      this.put(6, new EnchantmentAbbreviation("Aq", 1));
      this.put(7, new EnchantmentAbbreviation("Th", 3));
      this.put(8, new EnchantmentAbbreviation("Ds", 3));
      this.put(16, new EnchantmentAbbreviation("Sh", 5));
      this.put(17, new EnchantmentAbbreviation("Sm", 5));
      this.put(18, new EnchantmentAbbreviation("BoA", 5));
      this.put(19, new EnchantmentAbbreviation("Kb", 2));
      this.put(20, new EnchantmentAbbreviation("Fa", 2));
      this.put(21, new EnchantmentAbbreviation("Lo", 3));
      this.put(32, new EnchantmentAbbreviation("Ef", 5));
      this.put(33, new EnchantmentAbbreviation("St", 1));
      this.put(34, new EnchantmentAbbreviation("Ub", 3));
      this.put(35, new EnchantmentAbbreviation("Fo", 3));
      this.put(48, new EnchantmentAbbreviation("Po", 5));
      this.put(49, new EnchantmentAbbreviation("Pu", 2));
      this.put(50, new EnchantmentAbbreviation("Fl", 1));
      this.put(51, new EnchantmentAbbreviation("Inf", 1));
      this.put(61, new EnchantmentAbbreviation("LoS", 3));
      this.put(62, new EnchantmentAbbreviation("Lu", 3));
   }

   static {
      a = 36940233155126L;
   }


}
