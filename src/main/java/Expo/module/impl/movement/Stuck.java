package Expo.module.impl.movement;

import Expo.module.Category;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.StuckBinder;
import Expo.event.events.MoveEntityEvent;
import Expo.event.events.MoveEntityWithHeadingEvent;
import Expo.event.events.MoveInputEvent;
import Expo.event.events.PreLivingUpdateEvent;
import Expo.module.Module;
import Expo.setting.settings.ModeSetting;
import Expo.setting.settings.NumberSetting;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.entity.EntityPlayerSP;

public class Stuck extends Module implements EventSubscriber {
   private static Object[] g;
   private static Map e;
   private static long[] c;
   private static String[] h;
   public static ModeSetting mode;
   public static NumberSetting pulseDelay;
   private int s;
   private static String b;
   private static long a;
   private boolean U;

   public void onMoveEntityWithHeading(long var1, MoveEntityWithHeadingEvent var3) {
      if (!this.U && var3.a instanceof EntityPlayerSP) {
         var3.I(21307, 3074332907L);
      }
   }

   public void onMoveInput(MoveInputEvent var1) {
      var1.i(0.0F);
      var1.A(0.0F);
   }

   public void onPreLivingUpdate(PreLivingUpdateEvent var1, long var2) {
      this.U = false;
      f.thePlayer.setSprinting(false);
      if (this.s > 0) {
         this.s = this.s - 50;
      }

      if (f.thePlayer.hurtTime != 0) {
         this.U = true;
      } else {
         if (mode.R(b) && this.s <= 0) {
            this.s = this.s + (int)(pulseDelay.L() * 50.0F);
            this.U = true;
         }
      }
   }

   public void h(long var1) {
      f.thePlayer.setSprinting(false);
      this.U = false;
      this.s = 0;
   }

   static {
      a = 23420914338661L;
      g = new Object[19];
      h = new String[19];
      b = "PULSE";
      e = new HashMap(13);
      c = new long[]{-3466031898384909254L, 5021153881801915561L, -1966682088493162257L, -9193661644592037382L, 8391141268143262382L};
   }

   public void A(long var1) {
      this.U = false;
      this.s = 0;
   }

   public void onMoveEntity(long var1, MoveEntityEvent var3) {
      if (!this.U && var3.D instanceof EntityPlayerSP) {
         var3.I(21307, 3074332907L);
      }
   }

   public final void x(long var1, EventBus var3) {
      StuckBinder.c(var3, this);
   }

   public Stuck(long var1, int var3) {
      super((((((var1) << 32) | (((long)((var3)) << 32) >>> 32)) ^ a) ^ 26711529975909L));
      this.declare("Stuck", Category.Movement, "Stuck you and disable movement");
      this.s = 0;
      this.U = false;
   }

   static {
      mode = new ModeSetting("Mode", "PULSE", "NORMAL");
      pulseDelay = new NumberSetting("Pulse-delay", 20.0F, 0.0F, 200.0F, 1.0F);
   }
}
