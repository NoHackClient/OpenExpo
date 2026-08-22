package Expo.module.impl.misc;

import Expo.module.Category;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.TimerBinder;
import Expo.event.events.Render2DEvent;
import Expo.internal.accessor.MinecraftAccessor;
import Expo.module.Module;
import Expo.setting.settings.NumberSetting;
import Expo.util.ClientUtil;
import Expo.util.KeyBindUtil;
import Expo.util.Sneaky;
import java.util.HashMap;
import java.util.Map;


public class Timer extends Module implements EventSubscriber {
   private static Map g;
   private boolean a;
   private static long[] c;
   private float s;
   private static Integer[] e;
   private static long b;
   private static String[] k;
   private static Object[] h;
   private boolean d;
   public static NumberSetting speed;


   public final void x(long var1, EventBus var3) {
      TimerBinder.C(var3, this);
   }

   private static void a() {
      h[0] = "9\u001e;\u0004\u001415";
      h[1] = long.class;
      k[1] = "java/lang/Long";
      h[2] = "\u0018`=6\u000b\u000e/w9<F*8|c ";
      h[3] = "lSQ\u001f\u0005D|";
      h[4] = void.class;
      k[4] = "java/lang/Void";
      h[5] = "\fjs\u0002;\u0004\u0007ebMZ\n\fnf\u0017";
      h[6] = "R+>\u000eha[-1?E\u0002Ey`\u0003p~\u00054/?7lG*9O4gTv^\u0005nxFy.B1bUHeRwr\u0004.9T1p?";
   }

   static {
      b = 125743794480863L;
      // add code
      h = new Object[7];
      k = new String[7];
      g = new HashMap(13);
      c = new long[]{-221930958687381324L, 1319250214599420535L, 4199866987675195430L};
      e = new Integer[3];
   }

   public void onRender2D(long var1, Render2DEvent var3) {
       try {var1 = b ^ var1;
      int var6 = (int)((var1 ^ 128510115268967L) >>> 48);
      long var7 = (var1 ^ 128510115268967L) << 16 >>> 16;
      long var9 = var1 ^ 52810029579502L;
      boolean var11 = ClientUtil.I() && f.currentScreen == null && this.h() != 0 && KeyBindUtil.V(this.h(), var9);
      if (var11 && !this.a) {
         this.u((short)var6, var7);
      }

      this.a = var11;
      if (this.o() && ClientUtil.I()) {
         if (!this.d) {
            this.s = MinecraftAccessor.o( f).timerSpeed;
            this.d = true;
         }

         MinecraftAccessor.o( f).timerSpeed = speed.L();
      } else if (this.d) {
         MinecraftAccessor.o( f).timerSpeed = this.s;
         this.d = false;
      }
   } catch (Throwable zkm$t) { throw Sneaky.rethrow(zkm$t); } }

   public Timer(long var1) {
      super(((b ^ (var1)) ^ 21543830494215L));
      // add code
      this.declare("Timer", Category.Misc, "Modify your game running speed");
      var1 = b ^ var1;
      this.d = false;
      this.s = 1.0F;
      this.a = false;
   }

   static {
      // add code
      speed = new NumberSetting("Speed", 1.0F, 0.0F, 5.0F, 0.01F);
   }
}
