package Expo.module.impl.combat;

import Expo.module.Category;

import Expo.module.Module;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.ModeSetting;
import Expo.setting.settings.NumberSetting;
import Expo.util.TimerUtil;











public class AutoProjectiles extends Module {
   public static NumberSetting disableRange;
   public static BooleanSetting allowAutoblock;
   private final TimerUtil x;
   public static NumberSetting range;
   public static BooleanSetting onlyUsePacketWhileAutoblocking;
   public static NumberSetting holdItemDelay;
   public static NumberSetting throwInterval;
   public static NumberSetting throwAmounts;
   public static ModeSetting mode;
   private static long a;
   private final TimerUtil c;

   public AutoProjectiles(long var1, short var3) {
      super((((0L | (((long)((var3)) << 48) >>> 48)) ^ a) ^ 109062919174723L));
      // add code
      this.declare("AutoProjectiles", Category.Combat, "This module is currently disabled");
      this.x = new TimerUtil();
      this.c = new TimerUtil();
   }

   static {
      // add code

      a = 75155036336937L;
   }

   static {
      // add code
      allowAutoblock = new BooleanSetting("Allow-autoblock", true);
      onlyUsePacketWhileAutoblocking = new BooleanSetting("Only-use-packet-while-autoblocking", true);
   }
   static {
      // add code
      disableRange = new NumberSetting("Disable-range", 3.0F, 0.0F, 800.0F, 0.050000004F);
      range = new NumberSetting("Range", 5.0F, 0.0F, 800.0F, 0.050000004F);
      holdItemDelay = new NumberSetting("Hold-item-delay", 100.0F, 0.0F, 800.0F, 0.050000004F);
      throwInterval = new NumberSetting("Throw-interval", 400.0F, 0.0F, 800.0F, 0.050000004F);
      throwAmounts = new NumberSetting("Throw-amounts", 1.0F, 0.0F, 800.0F, 0.050000004F);
   }
   static {
      // add code
      mode = new ModeSetting("Mode", "PACKET", "LEGIT");
   }
}
