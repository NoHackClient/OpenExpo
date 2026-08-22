package Expo.module.impl.macro;

import Expo.module.Category;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.Macro5Binder;
import Expo.event.events.PreTickEvent;
import Expo.module.MacroModule;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.ModeSetting;
import Expo.setting.settings.NumberSetting;
import Expo.setting.settings.TextSetting;

public class Macro5 extends MacroModule implements EventSubscriber {
   public static NumberSetting minHealth;
   public static TextSetting chatMessage;
   public static NumberSetting projectilesDuration;
   public static BooleanSetting swapBack;
   private static final long a = 54231158181834L;
   public static ModeSetting mode;

   public Macro5(long var1) {
      super(((a ^ (var1)) ^ 47786366378549L));
      this.declare("Macro5", Category.Macro, "Macro slot 5 (Must be bound to use)");
      var1 = a ^ var1;
   }

   public void onPreTick(PreTickEvent var1) {
      this.y(mode, projectilesDuration, 40065435448518L, minHealth, swapBack, chatMessage);
   }

   public final void x(long var1, EventBus var3) {
      Macro5Binder.j(var3, this);
   }

   static {
      projectilesDuration = new NumberSetting("Projectiles-duration", 100.0F, 30.0F, 1000.0F, 1.0F);
      swapBack = new BooleanSetting("Swap-back", true);
      mode = new ModeSetting("Mode", "PROJECTILES", "ROD", "POT", "GOLDEN_HEAD", "PEARL", "WATER_BUCKET", "LAVA_BUCKET", "CHAT");
      minHealth = new NumberSetting("Min-health", 13.0F, 0.0F, 20.0F, 1.0F);
      chatMessage = new TextSetting("Chat-message", "");
   }
}
