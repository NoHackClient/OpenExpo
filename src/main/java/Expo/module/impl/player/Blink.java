package Expo.module.impl.player;

import Expo.module.Category;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.BlinkBinder;
import Expo.event.events.AttackTargetEntityEvent;
import Expo.event.events.PostUpdateWalkingPlayerEvent;
import Expo.event.events.Render2DEvent;
import Expo.module.Module;
import Expo.module.impl.configuration.Font;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.ModeSetting;
import Expo.setting.settings.NumberSetting;
import Expo.util.Sneaky;
import Expo.util.TimerUtil;
import Expo.util.packet.PacketManager;
import Expo.util.render.CustomFont;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;


public class Blink extends Module implements EventSubscriber {
   public static NumberSetting pulseTicks;
   private static String[] x;
   private static long a;
   public static ModeSetting mode;
   private static Object[] v;
   private static Map m;
   public static BooleanSetting showDelay;
   private static long[] n;
   private final TimerUtil t;
   public static BooleanSetting autoDisable;
   private static Map e;
   private static String[] d;
   public static NumberSetting autoDisableTicks;
   private long C;
   private boolean J;
   private long s;
   public static BooleanSetting turnOffOnHit;
   private static Map r;
   private long y;
   private boolean k;

   static {
      a = 30074228456379L;
      v = new Object[8];
      x = new String[8];
      e = new HashMap(13);
      d = new String[3];
      m = new HashMap(13);
      r = new HashMap(13);
      n = new long[]{5475581527265492953L, 1411577678159929649L};
   }

   public void onAttackTargetEntity(AttackTargetEntityEvent var1, long var2) {
       try {var2 = a ^ var2;
      int var4 = (int)((var2 ^ 58902076452738L) >>> 48);
      long var5 = (var2 ^ 58902076452738L) << 16 >>> 16;
      if (turnOffOnHit.c() && this.J) {
         this.u((short)var4, var5);
      }
   } catch (Throwable zkm$t) { throw Sneaky.rethrow(zkm$t); } }

   public void onPostUpdateWalkingPlayer(PostUpdateWalkingPlayerEvent var1, long var2) {
       try {var2 = a ^ var2;
      int var4 = (int)((var2 ^ 127162168556112L) >>> 48);
      long var5 = (var2 ^ 127162168556112L) << 16 >>> 16;
      if (this.s > 0L) {
         this.s = this.s - 50L;
      }

      if (autoDisable.c() && (float)(System.currentTimeMillis() - this.y) > autoDisableTicks.L() * 50.0F) {
         this.u((short)var4, var5);
      } else {
         switch (mode.Y()) {
            case "NORMAL":
               PacketManager.M(true);
               this.J = true;
               break;
            case "PULSE":
               if (!this.J) {
                  PacketManager.M(true);
                  this.s = this.s + (long)(pulseTicks.L() * 50.0F);
                  this.J = true;
               }

               if (this.s <= 0L) {
                  PacketManager.j();
                  PacketManager.M(false);
                  this.J = false;
                  this.s = 0L;
               }
         }
      }
   } catch (Throwable zkm$t) { throw Sneaky.rethrow(zkm$t); } }



   public Blink(long var1) {
      super(((a ^ (var1)) ^ 133916730177788L));
      // add code
      this.declare("Blink", Category.Player, "Stop outgoing packet and release them at one time");
      var1 = a ^ var1;
      this.t = new TimerUtil();
      this.s = 0L;
      this.C = System.currentTimeMillis();
      this.J = false;
      this.k = true;
      this.y = System.currentTimeMillis();
   }

   public void i(long var1) {
      this.y = System.currentTimeMillis();
   }

   private static void a() {
      v[0] = "%V@5W\u001d";
      v[1] = short.class;
      x[1] = "java/lang/Short";
      v[2] = int.class;
      x[2] = "java/lang/Integer";
      v[3] = ".#1GQc\u001945M\u001cG\u000e?oQ";
      v[4] = "]J\u0011kn\r+";
      v[5] = void.class;
      x[5] = "java/lang/Void";
      v[6] = "VS+0gx]\\:\u007f\u0006vVW>%";
      v[7] = "\n\u001cIQ\u00145S]W.ME\u000e\\\nQ\u0017.^\t\rP*|J\tT^@uY\r\b.\u0010/F\\\u000bUE#N\u00185\u0017W/V\u0013_\u001eD+\nc\u000e\u0016\u0011!\\\u001bMQE,7XZTM)H[^\u0013NE";
   }

   public void A(long var1) {
      this.s = 0L;
      this.k = true;
      if (this.J) {
         PacketManager.j();
         PacketManager.M(false);
         this.J = false;
      }
   }


   public String g(long var1) {
      return mode.Y();
   }


   public final void x(long var1, EventBus var3) {
      int var4 = (int)((var1 ^ 46458091922866L) >>> 48);
      BlinkBinder.o((short)var4, var3, this);
   }

   public void onRender2D(long var1, Render2DEvent var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {


      if (showDelay.c() && this.J) {
         CustomFont var10 = Font.s(0L);
         if (this.k && this.t.L(10L, true)) {
            this.C = System.currentTimeMillis();
            this.k = false;
         }

         String var11 = String.format("%.1f", (System.currentTimeMillis() - this.C) / 1000.0);
         var10.T(
            37697014677608L, var11, var3.C.getScaledWidth() / 2.0F - var10.R(var11, 52019766876817L) / 2.0F, var3.C.getScaledHeight() / 2.0F + 75.0F, 16777215
         );
      } else {
         this.k = true;
      }
   }
   static {
      // add code
      showDelay = new BooleanSetting("Show-delay", true);
      turnOffOnHit = new BooleanSetting("Turn-off-on-hit", false);
      autoDisable = new BooleanSetting("Auto-disable", false);
   }
   static {
      // add code
      pulseTicks = new NumberSetting("Pulse-ticks", 20.0F, 1.0F, 100.0F, 1.0F);
      autoDisableTicks = new NumberSetting("Auto-disable-ticks", 20.0F, 1.0F, 100.0F, 1.0F);
   }
   static {
      // add code
      mode = new ModeSetting("Mode", "NORMAL", "PULSE");
   }
}
