package Expo.module.impl.movement;

import Expo.module.Category;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.SpeedBinder;
import Expo.event.events.PreUpdateEvent;
import Expo.module.Module;
import Expo.setting.settings.ModeSetting;
import Expo.setting.settings.NumberSetting;
import Expo.util.MoveUtil;
import java.util.HashMap;
import java.util.Map;

public class Speed extends Module implements EventSubscriber {
   public static ModeSetting mode;
   private static String[] g;
   private static Map d;
   private static long a;
   public static NumberSetting speed;
   private static String[] c;
   private static Object[] e;

   public final void x(long var1, EventBus var3) {
      SpeedBinder.V(var3, this);
   }

   public String g(long var1) {
      return mode.Y();
   }

   public void onPreUpdate(long var1, PreUpdateEvent var3) {
      if (f.gameSettings.keyBindForward.isKeyDown()
         || f.gameSettings.keyBindLeft.isKeyDown()
         || f.gameSettings.keyBindRight.isKeyDown()
         || f.gameSettings.keyBindBack.isKeyDown()) {
         switch (mode.Y()) {
            case "GROUND_STRAFE":
               if (f.thePlayer.onGround) {
                  MoveUtil.r(speed.L());
                  f.thePlayer.jump();
               }
               break;
            case "AUTO_JUMP":
               if (!f.thePlayer.onGround) {
                  f.thePlayer.motionX = f.thePlayer.motionX * speed.L();
                  f.thePlayer.motionZ = f.thePlayer.motionZ * speed.L();
               } else {
                  f.thePlayer.jump();
               }
               break;
            case "VANILLA":
               if (!f.thePlayer.onGround) {
                  MoveUtil.r(speed.L() * 4.0F / Math.PI);
               } else {
                  f.thePlayer.jump();
               }
         }
      }
   }

   static {
      a = 128241496468786L;
      e = new Object[7];
      g = new String[7];
      d = new HashMap(13);
      c = new String[3];
   }

   public Speed(long var1) {
      super(((a ^ (var1)) ^ 44423979700539L));
      this.declare("Speed", Category.Movement, "Move faster");
      var1 = a ^ var1;
   }
   static {
      mode = new ModeSetting("Mode", "GROUND_STRAFE", "AUTO_JUMP", "VANILLA");
      speed = new NumberSetting("Speed", 1.0F, 0.0F, 5.0F, 0.01F);
   }
}
