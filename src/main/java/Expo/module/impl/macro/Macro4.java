package Expo.module.impl.macro;

import Expo.module.Category;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.Macro4Binder;
import Expo.event.events.PreTickEvent;
import Expo.module.MacroModule;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.ModeSetting;
import Expo.setting.settings.NumberSetting;
import Expo.setting.settings.TextSetting;

public class Macro4 extends MacroModule implements EventSubscriber {
   public static NumberSetting projectilesDuration;
   public static BooleanSetting swapBack;
   private static final long b = 52488137415843L;
   public static NumberSetting minHealth;
   public static ModeSetting mode;
   public static TextSetting chatMessage;

   public final void x(long var1, EventBus var3) {
      Macro4Binder.o( var3, this);
   }

   private static void c() {
   }

   public Macro4(long var1) {
      super(((b ^ (var1)) ^ 78355914447470L));
      this.declare("Macro4", Category.Macro, "Macro slot 4 (Must be bound to use)");
      var1 = b ^ var1;
   }

   public void onPreTick(PreTickEvent var3) {
      this.y(mode, projectilesDuration, 40065435448518L, minHealth, swapBack, chatMessage);
   }

   static {
      c();
   }
   static {
      mode = new ModeSetting("Mode", "PROJECTILES", "ROD", "POT", "GOLDEN_HEAD", "PEARL", "WATER_BUCKET", "LAVA_BUCKET", "CHAT");
      minHealth = new NumberSetting("Min-health", 13.0F, 0.0F, 20.0F, 1.0F);
      projectilesDuration = new NumberSetting("Projectiles-duration", 100.0F, 30.0F, 1000.0F, 1.0F);
      swapBack = new BooleanSetting("Swap-back", true);
      chatMessage = new TextSetting("Chat-message", "");
   }
}
