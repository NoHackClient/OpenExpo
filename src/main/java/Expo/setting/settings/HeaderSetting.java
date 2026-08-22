package Expo.setting.settings;

import Expo.module.MacroModule;
import Expo.module.Module;
import Expo.module.impl.configuration.Language;
import Expo.setting.Setting;
import Expo.util.MathUtil;











public class HeaderSetting extends Setting {
   private static long a;
   public String I;

   public HeaderSetting(String var1) {
      // add code
      this.q = var1;
      this.I = var1;
   }

   public String L() {
      return this.I;
   }

   static {
      a = 109733489650987L;
   }

   public String U(long var1, Module var3) {
      if (!Language.applyForSettings.c()) {
         return this.I;
      }

      if (Language.language.R("ENGLISH")) {
         return this.I;
      }

      int var6 = MathUtil.k(var3.x(this) + 1, 1, var3.w().size());
      return var3 instanceof MacroModule ? Language.z("setting.Macro." + var6,0L) : Language.z("setting." + var3.b() + "." + var6,0L);
   }


   public void B(String var1) {
      this.I = var1;
   }


}
