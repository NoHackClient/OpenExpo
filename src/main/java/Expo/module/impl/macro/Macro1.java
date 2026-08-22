package Expo.module.impl.macro;

import Expo.module.Category;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.events.PreTickEvent;
import Expo.module.MacroModule;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.ModeSetting;
import Expo.setting.settings.NumberSetting;
import Expo.setting.settings.TextSetting;


public class Macro1 extends MacroModule implements EventSubscriber {
   public static BooleanSetting swapBack;
   private static final long b = 20717425069016L;
   public static ModeSetting mode;
   public static TextSetting chatMessage;
   public static NumberSetting minHealth;
   public static NumberSetting projectilesDuration;

   public Macro1(long var1) {
      super(((b ^ (var1)) ^ 69917904800213L));
      // add code
      this.declare("Macro1", Category.Macro, "Macro slot 1 (Must be bound to use)");
      var1 = b ^ var1;
   }


   public final void x(long var1, EventBus var3) {
      int var4 = (int)((var1 ^ 31761143411564L) >>> 48);
      int var5 = (int)((var1 ^ 31761143411564L) << 16 >>> 48);
      int var6 = (int)((var1 ^ 31761143411564L) << 32 >>> 32);
      Expo.event.binder.Macro1Binder.g(var3, (short)var4, (char)var5, var6, this);
   }

   public void onPreTick(PreTickEvent var1) {

      this.y(mode, projectilesDuration, 40065435448518L, minHealth, swapBack, chatMessage);
   }

   static {
      // add code
      minHealth = new NumberSetting("Min-health", 13.0F, 0.0F, 20.0F, 1.0F);
      mode = new ModeSetting("Mode", "PROJECTILES", "ROD", "POT", "GOLDEN_HEAD", "PEARL", "WATER_BUCKET", "LAVA_BUCKET", "CHAT");
      chatMessage = new TextSetting("Chat-message", "");
      swapBack = new BooleanSetting("Swap-back", true);
      projectilesDuration = new NumberSetting("Projectiles-duration", 100.0F, 30.0F, 1000.0F, 1.0F);
   }
}
