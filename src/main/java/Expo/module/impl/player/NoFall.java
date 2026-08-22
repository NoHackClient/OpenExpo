package Expo.module.impl.player;

import Expo.module.Category;

import Expo.module.Module;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.ModeSetting;
import Expo.setting.settings.NumberSetting;
import Expo.util.TimerUtil;

public class NoFall extends Module {
   private static long[] b;
   public static BooleanSetting alwaysGroundSpoof;
   private final TimerUtil o;
   private static long a;
   public static ModeSetting mode;
   public static NumberSetting fallDistance;
   private final boolean n;
   private final boolean K;
   public static NumberSetting timerSpeed;
   private final boolean L;
   private final boolean C;
   private final boolean x;
   private float S;
   private final boolean r;
   public static NumberSetting groundSpoofTicks;

   public NoFall(byte var1, long var2) {
      super((((((long)((var1)) << 56) | (((var2) << 8) >>> 8)) ^ a) ^ 45423438015589L));
      this.declare("NoFall", Category.Player, "This module is currently disabled");
      this.o = new TimerUtil();
      this.r = false;
      this.K = false;
      this.C = false;
      this.n = false;
      this.L = false;
      this.x = false;
   }

   static {
      a = 5137402864309L;
   }

   static {
      alwaysGroundSpoof = new BooleanSetting("Always-ground-spoof", true);
   }
   static {
      fallDistance = new NumberSetting("Fall-distance", 3.0F, 0.0F, 6.0F, 0.020000001F);
      timerSpeed = new NumberSetting("Timer-speed", 0.7F, 0.0F, 6.0F, 0.020000001F);
      groundSpoofTicks = new NumberSetting("Ground-spoof-ticks", 0.0F, 0.0F, 6.0F, 0.020000001F);
   }
   static {
      mode = new ModeSetting("Mode", "NO_GROUND", "ON_GROUND", "JUMP", "TIMER");
   }
}
