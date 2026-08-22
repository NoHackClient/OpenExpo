package Expo.module.impl.misc;

import Expo.module.Category;

import Expo.module.Module;
import Expo.module.ModuleManager;

public class NoObfuscation extends Module {
   private static long a;
   private static String b;

   static {
      a = 82802124200324L;
      b = "\u00a7k";
   }

   public static String f( String var2) {
      if (var2 == null || ModuleManager.k == null) {
         return var2;
      } else {
         return ModuleManager.k.o() ? var2.replace(b, "") : var2;
      }
   }

   public NoObfuscation(long var1) {
      super(((a ^ (var1)) ^ 93628666755137L));
      this.declare("NoObfuscation", Category.Misc, "Remove the obfuscation minecraft chat code");
      var1 = a ^ var1;
   }
}
