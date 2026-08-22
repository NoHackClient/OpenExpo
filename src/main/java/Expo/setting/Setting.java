package Expo.setting;

import Expo.module.MacroModule;
import Expo.module.Module;
import Expo.module.impl.configuration.Language;
import Expo.util.MathUtil;











public class Setting {
   private static long b;
   public String q;



   public String e(byte var1, Module var2, long var3) {
      if (!Language.applyForSettings.c()) {
         return this.q;
      }

      if (Language.language.R("ENGLISH")) {
         return this.q;
      }

      int var9 = MathUtil.k(var2.x(this) + 1, 1, var2.w().size());
      return var2 instanceof MacroModule ? Language.z("setting.Macro." + var9,0L) : Language.z("setting." + var2.b() + "." + var9,0L);
   }

   static {
      b = 41118571274527L;
   }

   public String B() {
      return this.q;
   }

   public void H(String var1) {
      this.q = var1;
   }

}
