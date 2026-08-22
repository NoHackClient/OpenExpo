package Expo.module;

import Expo.module.impl.configuration.Language;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.util.ResourceLocation;

public enum Category {
   Combat,
   Movement,
   Player,
   World,
   Visual,
   Visual_utility,
   Misc,
   Configuration,
   Macro;

   private static final ResourceLocation n = new ResourceLocation(zkm$g11()[17], zkm$g11()[15]);
   private static final ResourceLocation m = new ResourceLocation(zkm$g11()[18], zkm$g11()[2]);
   private static final ResourceLocation u = new ResourceLocation(zkm$g11()[17], zkm$g11()[10]);
   private static final ResourceLocation J = new ResourceLocation(zkm$g11()[17], zkm$g11()[7]);
   private static final ResourceLocation V = new ResourceLocation(zkm$g11()[17], zkm$g11()[4]);
   private static final ResourceLocation q = new ResourceLocation(zkm$g11()[17], zkm$g11()[16]);
   private static final ResourceLocation a = new ResourceLocation(zkm$g11()[17], zkm$g11()[6]);
   private static final ResourceLocation L = new ResourceLocation(zkm$g11()[17], zkm$g11()[19]);
   private static final ResourceLocation o = new ResourceLocation(zkm$g11()[17], zkm$g11()[5]);

   static {
      Category[] var10000 = new Category[(int)zkm$g0()[6]];
      var10000[0] = Combat;
      var10000[1] = Movement;
      var10000[2] = Player;
      var10000[3] = World;
      var10000[4] = Visual;
      var10000[5] = Visual_utility;
      var10000[(int)zkm$g0()[5]] = Misc;
      var10000[(int)zkm$g0()[1]] = Configuration;
      var10000[(int)zkm$g0()[0]] = Macro;
   }

   public String c() {
      return this.name();
   }

   public static List<Category> j() {
      ArrayList var0 = new ArrayList();

      for (Category var4 : values()) {
         if (var4 != Configuration && var4 != Macro) {
            var0.add(var4);
         }
      }

      return var0;
   }

   public static ResourceLocation n(Category var0) {
      if (var0 == Combat) {
         return m;
      } else if (var0 == Movement) {
         return q;
      } else if (var0 == Visual) {
         return V;
      } else if (var0 == Visual_utility) {
         return u;
      } else if (var0 == Player) {
         return a;
      } else if (var0 == World) {
         return J;
      } else if (var0 == Misc) {
         return o;
      } else {
         return var0 == Configuration ? L : n;
      }
   }

   public String x(int var1, int var2, short var3) {
      return Language.Y( this.name());
   }

   private static long[] zkm$g0() {
      return new long[]{37596606110892040L, -5414625232382066681L, -737978787085418489L, 5613391014556336136L, 19819809487192070L, 6162584402476924934L, 3655614568782102537L};
   }

   private static String[] zkm$g11() {
      return new String[]{"Visual", "World", "icons/combat.png", "Misc", "icons/visual.png", "icons/misc.png", "icons/player.png", "icons/world.png", "Macro", "Configuration", "icons/visual_utility.png", "Combat", "Movement", "Visual_utility", "Player", "icons/macro.png", "icons/movement.png", "minecraft", "minecraft", "icons/configuration.png"};
   }
}
