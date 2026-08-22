package Expo.module.impl.combat;

import Expo.module.Category;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.WTapBinder;
import Expo.event.events.AttackEntityEvent;
import Expo.event.events.MoveInputEvent;
import Expo.event.events.PreMouseInputEvent;
import Expo.event.events.ReceivePacketEvent;
import Expo.module.Module;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.NumberSetting;
import Expo.setting.settings.PercentageSetting;
import Expo.util.KeyBindUtil;
import Expo.util.MathUtil;
import Expo.util.TimerUtil;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.server.S19PacketEntityStatus;


public class WTap extends Module implements EventSubscriber {
   private static String[] o;
   private static long a;
   public static NumberSetting interval;
   public static BooleanSetting useBlockInstead;
   private static String d;
   private int G;
   private boolean p;
   private boolean L;
   private static long[] e;
   private static Map m;
   private final TimerUtil r;
   private boolean u;
   public static NumberSetting minPauseTick;
   private int T;
   public static NumberSetting maxPauseTick;
   private EntityPlayer h;
   private static Object[] n;
   public static PercentageSetting chance;
   public static BooleanSetting requireOnGround;
   public static BooleanSetting requireTargetDamage;
   private boolean J;



   public final void x(long var1, EventBus var3) {
      WTapBinder.U( var3, this);
   }

   public String g(long var1) {
      StringBuilder var3 = new StringBuilder();
      if (minPauseTick.L() == maxPauseTick.L()) {
         var3.append((int)minPauseTick.L() * 50);
      } else {
         var3.append((int)minPauseTick.L() * 50).append("-").append((int)maxPauseTick.L() * 50);
      }

      var3.append(d);
      return var3.toString();
   }

   public void onMoveInput(MoveInputEvent var1, long var2) {
      if (this.L) {
         var1.i(0.0F);
         var1.A(0.0F);
         this.L = false;
      }
   }

   public void onReceivePacket(ReceivePacketEvent var1, long var2) {
      if (requireTargetDamage.c()) {
         if (var1.d instanceof S19PacketEntityStatus) {
            S19PacketEntityStatus var6 = (S19PacketEntityStatus)var1.d;
            if (!(var6.getEntity(f.theWorld) instanceof EntityPlayerSP)
               && var6.getEntity(f.theWorld) instanceof EntityPlayer
               && var6.getOpCode() == 2
               && MathUtil.Q(chance.k(),0L)
               && this.r.L((long)interval.L(), true)) {
               this.G = (int)MathUtil.h(minPauseTick.L(), maxPauseTick.L());
               this.J = false;
            }
         }
      }
   }

   private static void a() {
      n[0] = "s~L\u001fK7o";
      n[1] = short.class;
      o[1] = "java/lang/Short";
      n[2] = "\\VW\rM\u0013kAS\u0007\u00007|J\t\u001b";
      n[3] = int.class;
      o[3] = "java/lang/Integer";
      n[4] = ";\u0013XO|3;";
      n[5] = void.class;
      o[5] = "java/lang/Void";
      n[6] = "E\u0000\u0002\u0016\u0015aN\u000f\u0013YtoE\u0004\u0017\u0003";
      n[7] = "^\u0010\u0001IU\u000e\u001c\u0018\f0nvZ\u0001\nZ\n\u001d\nV\u000eS3O\u001d\f\u0013\u000bON[\u000e\u00100\tG]\u0002\u0007KZ\u0012\u001f\nk\u000b\u000f\u001c[\u0005\u001b]Y\n\thQ\u0001\r\u001c\u000f\u0013\u0002TO\u0014cS\u0016W\b\r\u001d\u0010[YQv";
   }

   public void onAttackEntity(long var1, AttackEntityEvent var3) {
      if (var3.O() instanceof EntityPlayer) {
         this.J = this.h == null;

         this.h = (EntityPlayer)var3.O();
         this.T = 60;
      }
   }

   public void onPreMouseInput(long var1, PreMouseInputEvent var3) {


      this.u = false;
      this.L = false;
      if (this.T > 0) {
         this.T--;
      }

      if (this.T <= 0) {
         this.h = null;
      }

      if (this.h != null) {
         if ((!requireTargetDamage.c() || this.J) && this.r.L((long)interval.L(), true)) {
            this.G = (int)MathUtil.h(minPauseTick.L(), maxPauseTick.L());
            this.J = false;
         }

         if ((!requireOnGround.c() || f.thePlayer.onGround) && this.G > 0) {
            if (useBlockInstead.c()) {
               this.u = true;
            } else {
               this.L = true;
            }
         }
      }

      if (this.G > 0) {
         this.G--;
      }

      if (!this.u && this.p) {
         KeyBindUtil.o(99363263780575L, f.gameSettings.keyBindUseItem.getKeyCode());
         this.p = false;
      }

      if (this.u) {
         KeyBindUtil.A(82009306480869L, f.gameSettings.keyBindUseItem.getKeyCode(), true);
         this.u = false;
         this.p = true;
      }
   }

   public WTap(long var1) {
      super(((a ^ (var1)) ^ 46295047080693L));
      // add code
      this.declare("WTap", Category.Combat, "Pause moving during combat to help combo");
      var1 = a ^ var1;
      this.h = null;
      this.r = new TimerUtil();
      this.T = 0;
      this.G = 0;
      this.u = false;
      this.L = false;
      this.J = false;
      this.p = false;
   }

   public void A(long var1) {
      long var3 = var1 ^ 17179273251418L;
      this.u = false;
      this.L = false;
      this.G = 0;
      this.T = 0;
      this.h = null;
      this.J = false;
      if (this.p) {
         KeyBindUtil.o(var3, f.gameSettings.keyBindUseItem.getKeyCode());
         this.p = false;
      }
   }


   static {
      a = 9433001507230L;
      n = new Object[8];
      o = new String[8];
      d = "ms";
      m = new HashMap(13);
      e = new long[]{3266210825553073490L, 5314742259686245813L, 4719741884499584443L, 6622068100265281457L, -2199634987435189256L, -9111339067620825184L, -2825596532523530443L};
   }
   static {
      // add code
      useBlockInstead = new BooleanSetting("Use-block-instead", false);
      requireOnGround = new BooleanSetting("Require-on-ground", true);
      chance = new PercentageSetting("Chance", 100);
      minPauseTick = new NumberSetting("Min-pause-tick", 2.0F, 0.0F, 10.0F, 1.0F);
      maxPauseTick = new NumberSetting("Max-pause-tick", 3.0F, 0.0F, 10.0F, 1.0F);
      interval = new NumberSetting("Interval", 500.0F, 0.0F, 2000.0F, 50.0F);
      requireTargetDamage = new BooleanSetting("Require-target-damage", true);
   }
}
