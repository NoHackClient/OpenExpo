package Expo.module.impl.configuration;

import Expo.module.Category;

import Expo.module.Module;
import Expo.setting.settings.ModeSetting;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.util.ResourceLocation;

public class CustomCape extends Module {
   private static Map d;
   private static String[] b;
   private static long a;
   public static HashMap<String, String> O;
   public static ModeSetting cape;

   public static ResourceLocation d(long var0) {
      return new ResourceLocation("minecraft", "capes/" + O.get(cape.Y()) + ".png");
   }

   static {
      a = 38052850158322L;
      O = new HashMap<>();
   }

   static {
      cape = new ModeSetting("Cape", "NONE", "2011", "2012", "2013", "2015", "2016", "MJ",
                          "MJ_STUDIOS", "MJ_CLASSIC", "REALMS", "TRANSLATOR", "MOJIRA",
                          "COBALT", "SCROLLS", "BIRTHDAY", "MILLIONTH", "DB", "OXEYE",
                          "PRISMARINE", "SIZE_M", "SNOWMAN", "SPADE", "TURTLE", "VALENTINE");
   }

   public CustomCape(char var1, long var2) {
      super((((((long)((var1)) << 48) | 0L) ^ a) ^ 58003269117160L));
      this.declare("CustomCape", Category.Configuration, "Get a fake better cape");
   }
}
