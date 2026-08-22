package Expo.module.impl.macro;

import Expo.module.Category;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.Macro3Binder;
import Expo.event.events.PreTickEvent;
import Expo.module.MacroModule;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.ModeSetting;
import Expo.setting.settings.NumberSetting;
import Expo.setting.settings.TextSetting;

public class Macro3 extends MacroModule implements EventSubscriber {
   private static final long a = 91800027890256L;
   public static NumberSetting minHealth;
   public static BooleanSetting swapBack;
   public static NumberSetting projectilesDuration;
   public static TextSetting chatMessage;
   public static ModeSetting mode;

   public void onPreTick(PreTickEvent var3) {
      this.y(mode, projectilesDuration, 40065435448518L, minHealth, swapBack, chatMessage);
   }

   public final void x(long var1, EventBus var3) {
      Macro3Binder.V(var3, this);
   }

   public Macro3(long var1) {
      super(((a ^ (var1)) ^ 58651550368948L));
      this.declare("Macro3", Category.Macro, "Macro slot 3 (Must be bound to use)");
      var1 = a ^ var1;
   }

   static {
      mode = new ModeSetting("Mode", "PROJECTILES", "ROD", "POT", "GOLDEN_HEAD", "PEARL", "WATER_BUCKET", "LAVA_BUCKET", "CHAT");
      projectilesDuration = new NumberSetting("Projectiles-duration", 100.0F, 30.0F, 1000.0F, 1.0F);
      chatMessage = new TextSetting("Chat-message", "");
      swapBack = new BooleanSetting("Swap-back", true);
      minHealth = new NumberSetting("Min-health", 13.0F, 0.0F, 20.0F, 1.0F);
   }
}
