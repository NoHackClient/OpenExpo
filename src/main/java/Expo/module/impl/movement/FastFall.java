package Expo.module.impl.movement;

import Expo.module.Category;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.FastFallBinder;
import Expo.event.events.UpdateWalkingPlayerEvent;
import Expo.module.Module;
import Expo.module.ModuleManager;
import Expo.setting.settings.BooleanSetting;
import Expo.util.KeyBindUtil;
import Expo.util.MoveUtil;


public class FastFall extends Module implements EventSubscriber {
   public static BooleanSetting requireScaffold;
   public static BooleanSetting horizontalSpeedRestriction;
   private static final long a = 116342949770806L;


   public void onUpdateWalkingPlayer(UpdateWalkingPlayerEvent var1) {

      if (!requireScaffold.c() || ModuleManager.I.o()) {
         if (KeyBindUtil.V(f.gameSettings.keyBindJump.getKeyCode(), 64165991731362L) && (!horizontalSpeedRestriction.c() || MoveUtil.V() <= 0.02)) {
            if (f.thePlayer.onGround) {
               f.thePlayer.motionY = 0.42F;
            }

            if (f.thePlayer.motionY <= 0.0 && f.thePlayer.motionY >= -0.09) {
               f.thePlayer.motionY = -1.0;
            }
         }
      }
   }

   public final void x(long var1, EventBus var3) {
      FastFallBinder.O(var3, this);
   }

   public FastFall(long var1) {
      super(((a ^ (var1)) ^ 41113094215273L));
      // add code
      this.declare("FastFall", Category.Movement, "Fall faster");
      var1 = a ^ var1;
   }

   static {
      // add code
      horizontalSpeedRestriction = new BooleanSetting("Horizontal-speed-restriction", true);
      requireScaffold = new BooleanSetting("Require-scaffold", true);
   }
}
