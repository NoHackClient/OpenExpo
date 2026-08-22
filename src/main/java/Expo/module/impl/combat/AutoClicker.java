package Expo.module.impl.combat;

import Expo.module.Category;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.AutoClickerBinder;
import Expo.event.events.PreTickEvent;
import Expo.module.Module;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.NumberSetting;
import Expo.util.KeyBindUtil;
import Expo.util.MathUtil;
import java.util.Map;
import net.minecraft.item.ItemSword;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;











public class AutoClicker extends Module implements EventSubscriber {
   private static Map g;
   private static long[] c;
   public static boolean I;
   private long o;
   public static NumberSetting sagUnblockDuration;
   public static NumberSetting maxcps;
   public static NumberSetting sagBlockingTicks;
   private static long a;
   private static Integer[] d;
   public static BooleanSetting sag;
   private boolean J;
   private long M;
   public static NumberSetting mincps;
   public static BooleanSetting breakBlocks;
   private long e;

   public final void x(long var1, EventBus var3) {
      AutoClickerBinder.N( var3, this);
   }

   public void A(long var1) {
      I = false;
      this.o = 0L;
      this.M = 0L;
      this.e = 0L;
      this.J = false;
   }

   public AutoClicker(short var1, char var2, int var3) {
      super(((((((long)((var1)) << 48) | (((long)((var2)) << 48) >>> 16)) | (((long)((var3)) << 32) >>> 32)) ^ a) ^ 82626899401928L));
      // add code
      this.declare("AutoClicker", Category.Combat, "Automatically left click");
      this.o = 0L;
      this.M = 0L;
      this.e = 0L;
      this.J = false;
   }

   public void onPreTick(long var1, PreTickEvent var3) {





      if (this.o > 0L) {
         this.o = this.o - 50L;
      }

      if (this.M > 0L) {
         this.M = this.M - 50L;
      }

      if (this.e > 0L) {
         this.e = this.e - 50L;
      }

      if (KillAura.a || !KeyBindUtil.V(f.gameSettings.keyBindAttack.getKeyCode(), 64165991731362L) || f.currentScreen != null) {
         I = false;
         this.J = false;
      } else if (breakBlocks.c() && f.objectMouseOver != null && f.objectMouseOver.typeOfHit == MovingObjectType.BLOCK && f.objectMouseOver.entityHit == null) {
         KeyBindUtil.A(82009306480869L, f.gameSettings.keyBindAttack.getKeyCode(), true);
         this.J = false;
         I = false;
      } else {
         I = true;
         if (!sag.c()
            || !KeyBindUtil.V(f.gameSettings.keyBindUseItem.getKeyCode(), 64165991731362L)
            || f.thePlayer.getHeldItem() == null
            || !(f.thePlayer.getHeldItem().getItem() instanceof ItemSword)) {
            this.J = false;
            if (this.o <= 0L) {
               this.o = this.o + MathUtil.e(mincps.L(), maxcps.L());
               KeyBindUtil.A(82009306480869L, f.gameSettings.keyBindAttack.getKeyCode(), false);
               KeyBindUtil.T(27332, (short)51113, f.gameSettings.keyBindAttack.getKeyCode(), (short)43042);
            }
         } else if (!this.J && this.M <= 0L) {
            KeyBindUtil.T(27332, (short)51113, f.gameSettings.keyBindAttack.getKeyCode(), (short)43042);
            KeyBindUtil.A(82009306480869L, f.gameSettings.keyBindUseItem.getKeyCode(), true);
            this.J = true;
            this.e = this.e + (long)sagBlockingTicks.L() * 50L;
         } else if (this.J && this.e <= 0L) {
            KeyBindUtil.A(82009306480869L, f.gameSettings.keyBindUseItem.getKeyCode(), false);
            this.J = false;
            this.M = this.M + (long)sagUnblockDuration.L() * 50L;
         }
      }
   }



   public String g(long var1) {
      if (mincps.L() != maxcps.L()) {
         return Math.round(mincps.L()) == mincps.L() && Math.round(maxcps.L()) == maxcps.L() ? Math.round(mincps.L()) + "-" + Math.round(maxcps.L()) : mincps.L() + "-" + maxcps.L();
      } else {
         return Math.round(maxcps.L()) == maxcps.L() ? String.valueOf(Math.round(maxcps.L())) : mincps.L() + "-" + maxcps.L();
      }
   }

   static {
      a = 116760755216853L;
      I = false;
   }

   private static void a() {
   }




   static {
      // add code
      sagBlockingTicks = new NumberSetting("Sag-blocking-ticks", 4.0F, 0.0F, 20.0F, 1.0F);
      sag = new BooleanSetting("Sag", false);
      sagUnblockDuration = new NumberSetting("Sag-unblock-duration", 0.0F, 0.0F, 20.0F, 1.0F);
      mincps = new NumberSetting("MinCPS", 13.0F, 1.0F, 20.0F, 0.1F);
      breakBlocks = new BooleanSetting("Break-blocks", true);
      maxcps = new NumberSetting("MaxCPS", 15.0F, 1.0F, 20.0F, 0.1F);
   }
}
