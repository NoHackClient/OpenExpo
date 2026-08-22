package Expo.module.impl.configuration;

import Expo.module.Category;

import Expo.module.Module;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.HeaderSetting;
import Expo.setting.settings.ModeSetting;
import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;











public class Language extends Module {
   public static BooleanSetting applyForArraylist;
   public static BooleanSetting applyForCategory;
   private static long b;
   public static HeaderSetting H;
   public static ModeSetting language;
   public static HeaderSetting t;
   public static BooleanSetting applyForDescriptions;
   public static BooleanSetting applyForName;
   public static BooleanSetting applyForSettings;

   public static String z(String var0, long var1) {
      String var8 = s(var0);
      // add code
      return var8 != null ? var8 : var0;
   }

   public static String Y( String var2) {
      if (!applyForCategory.c()) {
         return var2;
      } else {
         return language.R("ENGLISH") ? var2 : z("category." + var2,0L);
      }
   }

   public Language(char var1, int var2, int var3) {
      super(((((((long)((var1)) << 48) | (((long)((var2)) << 32) >>> 16)) | (((long)((var3)) << 48) >>> 48)) ^ b) ^ 58318042895466L));
      // add code
      this.declare("Language", Category.Configuration, "Configuration of language");
   }

   public static String Z(long var0, String var2) {
      if (language.R("ENGLISH")) {
         return var2;
      } else if (var2.startsWith("Macro")) {
         return language.R("ENGLISH") ? var2 : MessageFormat.format(z("module.Macro.name",0L), var2.charAt(var2.length() - 1));
      } else {
         return z("module." + var2 + ".name",0L);
      }
   }

   static {
      b = 47620395300042L;
   }

   private static String s(String var2) {
      switch (var2.toLowerCase()) {
         case "clickgui.bind.press":
            return "Press a key...";
         case "clickgui.bind.current":
            return "Current bind: '§e";
         case "clickgui.description.edit":
            return "Edit \"{0}\" using command";
         case "clickgui.studio.set":
            return "Set";
         case "clickgui.studio.visible":
            return "Visible";
         case "clickgui.studio.suffix":
            return "Suffix";
         default:
            return null;
      }
   }



   public static void G(String var0) {
      var0 = var0.toUpperCase();
      language.i(var0);
   }

   public static String o(String var0) {
      if (language.R("ENGLISH")) {
         return var0;
      } else if (!applyForDescriptions.c()) {
         return var0;
      } else {
         return var0.startsWith("Macro")
            ? MessageFormat.format(z("module.Macro.description",0L), var0.charAt(var0.length() - 1))
            : z("module." + var0 + ".description",0L);
      }
   }

   static {
      // add code
      applyForArraylist = new BooleanSetting("Apply-for-arraylist", false);
      // update new version
      H = new HeaderSetting("These options only affect ClickGui");
      // update new version
      t = new HeaderSetting("This option only affects ArrayList");
      applyForCategory = new BooleanSetting("Apply-for-category", true);
      applyForSettings = new BooleanSetting("Apply-for-settings", true);
      applyForName = new BooleanSetting("Apply-for-name", true);
      applyForDescriptions = new BooleanSetting("Apply-for-descriptions", true);
      // add code
      language = new ModeSetting("Language", "ENGLISH");
   }
}
