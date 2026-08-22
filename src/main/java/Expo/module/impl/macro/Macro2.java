package Expo.module.impl.macro;

import Expo.module.Category;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.Macro2Binder;
import Expo.event.events.PreTickEvent;
import Expo.module.MacroModule;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.ModeSetting;
import Expo.setting.settings.NumberSetting;
import Expo.setting.settings.TextSetting;


public class Macro2 extends MacroModule implements EventSubscriber {
   public static BooleanSetting swapBack;
   public static ModeSetting mode;
   private static final long a = 122087896662423L;
   public static TextSetting chatMessage;
   public static NumberSetting minHealth;
   public static NumberSetting projectilesDuration;


   public Macro2(int var1, int var2, short var3) {
      super(((((((long)((var1)) << 32) | (((long)((var2)) << 48) >>> 32)) | (((long)((var3)) << 48) >>> 48)) ^ a) ^ 132327635857393L));
      // add code
      this.declare("Macro2", Category.Macro, "Macro slot 2 (Must be bound to use)");
   }

   public void onPreTick(PreTickEvent var3) {

      this.y(mode, projectilesDuration, 40065435448518L, minHealth, swapBack, chatMessage);
   }

   public final void x(long var1, EventBus var3) {
      Macro2Binder.d(var3, this);
   }

   static {
      // add code
      mode = new ModeSetting("Mode", "PROJECTILES", "ROD", "POT", "GOLDEN_HEAD", "PEARL", "WATER_BUCKET", "LAVA_BUCKET", "CHAT");
      projectilesDuration = new NumberSetting("Projectiles-duration", 100.0F, 30.0F, 1000.0F, 1.0F);
      swapBack = new BooleanSetting("Swap-back", true);
      minHealth = new NumberSetting("Min-health", 13.0F, 0.0F, 20.0F, 1.0F);
      chatMessage = new TextSetting("Chat-message", "");
   }
}
