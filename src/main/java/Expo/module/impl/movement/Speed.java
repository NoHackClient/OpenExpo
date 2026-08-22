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

   private static void a() {
      e[0] = "n:\\sj4H";
      e[1] = long.class;
      g[1] = "java/lang/Long";
      e[2] = "<\u0019}8*\u0015\u000b\u000ey2g1\u001c\u0005#.";
      e[3] = "2\\&!\u007f\r6";
      e[4] = void.class;
      g[4] = "java/lang/Void";
      e[5] = "a'\u0014\u000e*\u0010j(\u0005AK\u001ea#\u0001\u001b";
      e[6] = "d1\u0005CtP8-\u0017#Ro`q]\\`\u0000'8\u0005\u001f\fV9y\\\u0019u^&'_#6\u0001 s\u001cCv\u001fh$f\u0018aR #\u0006\u001d7T4I";
   }




   public Speed(long var1) {
      super(((a ^ (var1)) ^ 44423979700539L));
      // add code
      this.declare("Speed", Category.Movement, "Move faster");
      var1 = a ^ var1;
   }
   static {
      // add code
      mode = new ModeSetting("Mode", "GROUND_STRAFE", "AUTO_JUMP", "VANILLA");
      speed = new NumberSetting("Speed", 1.0F, 0.0F, 5.0F, 0.01F);
   }
}
