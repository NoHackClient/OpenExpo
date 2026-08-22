package Expo.module.impl.combat;

import Expo.module.Category;

import Expo.module.Module;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.HeaderSetting;
import Expo.setting.settings.NumberSetting;
import Expo.util.EntityUtil;
import net.minecraft.entity.EntityLivingBase;











public class HitBox extends Module {
   public static HeaderSetting targetSettings;
   public static BooleanSetting friends;
   public static NumberSetting expand;
   public static BooleanSetting enemies;
   private static final long c = 77663890389010L;
   public static BooleanSetting bosses;
   public static BooleanSetting players;
   public static BooleanSetting animals;
   public static BooleanSetting bots;
   public static BooleanSetting teammates;
   public static BooleanSetting mobs;

   private static void a() {
   }

   public HitBox(long var1) {
      super(((c ^ (var1)) ^ 37508820998615L));
      // add code
      this.declare("HitBox", Category.Combat, "Modify entities hitbox to help reach target easier");
      var1 = c ^ var1;
   }

   public String g(long var1) {
      return "+" + expand.L();
   }

   public static boolean k(byte var0, EntityLivingBase var1, long var2) {
      long var4 = ((long)var0 << 56 | 95546070903943L) ^ c;
      long var6 = var4 ^ 3934031967199L;
      return EntityUtil.q(var1, players.c(), mobs.c(), animals.c(), bosses.c(), friends.c(), enemies.c(), teammates.c(), bots.c(), var6);
   }

   static {
      a();
   }

   static {
      // add code
      expand = new NumberSetting("Expand", 0.1F, 0.0F, 1.0F, 0.01F);
   }
   static {
      // add code
      animals = new BooleanSetting("Animals", false);
      enemies = new BooleanSetting("Enemies", true);
      mobs = new BooleanSetting("Mobs", false);
      bots = new BooleanSetting("Bots", false);
      bosses = new BooleanSetting("Bosses", false);
      friends = new BooleanSetting("Friends", false);
      players = new BooleanSetting("Players", true);
      teammates = new BooleanSetting("Teammates", false);
   }
   static {
      // add code
      targetSettings = new HeaderSetting("Target settings");
   }
}
