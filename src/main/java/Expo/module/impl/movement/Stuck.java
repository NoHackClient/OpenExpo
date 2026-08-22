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

   private static void a() {
      g[0] = "z0\u001eB;!t";
      g[1] = float.class;
      h[1] = "java/lang/Float";
      g[2] = "\u001d\u0016\u001a\u001ab: ";
      g[3] = "\u0003rx&yY\b}ii\u0004A\u001bz` ";
      g[4] = boolean.class;
      h[4] = "java/lang/Boolean";
      g[5] = "TT)xc+TT>$o$N\u001f>:g'TEs3`6SE$xK,NX)/^.[H8$]\u0012";
      g[6] = void.class;
      h[6] = "java/lang/Void";
      g[7] = "SGOx_\u0007dPKr\u0012#s[\u0011c\u000b";
      g[8] = int.class;
      h[8] = "java/lang/Integer";
      g[9] = long.class;
      h[9] = "java/lang/Long";
      g[10] = "KYx1|.y";
      g[11] = "Gt:M_\u0011pc>G\u00125ghd[";
      g[12] = "\t_\u000e/?\u000f\u001f";
      g[13] = "\u001b\u00183\u001b\u001ff\u0010\u0017\"T~h\u001b\u001c&\u000e";
      g[14] = "wT!\u00001io\u001b 0\u0019PoHn@/2\u007fD*0";
      g[15] = ">\\\u000fP+;l\tRU@=\u0001_F\u000e$<=\u000bB\u0000%V1\u001eS\t=m\u007f\u0001\\\u0005@hz\u001bQ\u0017\"mc[Gi|*aP\u000eS&ld\u001d>";
      g[16] = "W\u000fo\u0010m[V\n)Q\u0016fnS9_vD\u0010\u0013'\u001ep<URfCwU\u0001\fm\\\u0016";
      g[17] = "\u0013\"\u001aJ9\tG#FZ[r.rU\\bC_-\u001aI23\u00104_^%Q\u0015-\u001fH[\u000fR/\u0014\u0001aU\u0014*Y1";
      g[18] = "D\u001d&r[\u007f\r\n3\u007f&c\u0002\u0003'A\u00195G^uAL\rD^|uGd\u0010\u0000wj&1\u0003\u0005|&\u001ckE\u00001\u0016";
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
      // add code
      this.declare("Stuck", Category.Movement, "Stuck you and disable movement");
      this.s = 0;
      this.U = false;
   }

   static {
      // add code
      mode = new ModeSetting("Mode", "PULSE", "NORMAL");
      pulseDelay = new NumberSetting("Pulse-delay", 20.0F, 0.0F, 200.0F, 1.0F);
   }
}
