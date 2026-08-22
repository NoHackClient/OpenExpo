package Expo.module.impl.movement;

import Expo.module.Category;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.FlyBinder;
import Expo.event.events.MoveFlyingEvent;
import Expo.event.events.PreUpdateEvent;
import Expo.module.Module;
import Expo.setting.settings.NumberSetting;
import Expo.util.KeyBindUtil;
import Expo.util.MoveUtil;











public class Fly extends Module implements EventSubscriber {
   private double K;
   private static final long a = 125513684974462L;
   public static NumberSetting horizontalSpeed;
   public static NumberSetting verticalSpeed;

   private static void a() {
   }

   public void onMoveFlying(char var1, int var2, short var3, MoveFlyingEvent var4) {
      long var5 = ((long)var1 << 48 | (long)var2 << 32 >>> 16 | (long)var3 << 48 >>> 48) ^ a;
      long var7 = var5 ^ 78750643794431L;
      if (this.o()) {
         if (f.thePlayer.posY % 1.0 != 0.0) {
            f.thePlayer.motionY = this.K;
         }

         MoveUtil.y(0.0, var7);
         var4.H((float)MoveUtil.A() * horizontalSpeed.L());
      }
   }

   public void A(long var1) {
      long var3 = var1 ^ 124373133574740L;
      long var5 = var1 ^ 17179273251418L;
      f.thePlayer.motionY = 0.0;
      MoveUtil.y(0.0, var3);
      KeyBindUtil.o(var5, f.gameSettings.keyBindSneak.getKeyCode());
   }

   public Fly(long var1) {
      super(((a ^ (var1)) ^ 23319704636145L));
      // add code
      this.declare("Fly", Category.Movement, "Allows you to fly without creative");
      var1 = a ^ var1;
      this.K = 0.0;
   }

   public final void x(long var1, EventBus var3) {
      FlyBinder.x(var3, this);
   }

   static {
      a();
   }

   public void onPreUpdate(PreUpdateEvent var3) {


      this.K = 0.0;
      if (f.currentScreen == null) {
         if (KeyBindUtil.V(f.gameSettings.keyBindJump.getKeyCode(), 64165991731362L)) {
            this.K = this.K + verticalSpeed.L() * 0.42F;
         }

         if (KeyBindUtil.V(f.gameSettings.keyBindSneak.getKeyCode(), 64165991731362L)) {
            this.K = this.K - verticalSpeed.L() * 0.42F;
         }

         KeyBindUtil.A(82009306480869L, f.gameSettings.keyBindSneak.getKeyCode(), false);
      }
   }
   static {
      // add code
      horizontalSpeed = new NumberSetting("Horizontal-speed", 1.0F, 0.0F, 20.0F, 0.01F);
      verticalSpeed = new NumberSetting("Vertical-speed", 1.0F, 0.0F, 20.0F, 0.01F);
   }
}
