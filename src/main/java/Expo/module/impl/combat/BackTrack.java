package Expo.module.impl.combat;

import Expo.module.Category;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.BackTrackBinder;
import Expo.event.events.AttackEntityEvent;
import Expo.event.events.PreUpdateEvent;
import Expo.event.events.Render2DEvent;
import Expo.module.Module;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.HeaderSetting;
import Expo.setting.settings.NumberSetting;
import Expo.util.EntityUtil;
import Expo.util.MathUtil;
import Expo.util.RaytraceUtil;
import Expo.util.TimerUtil;
import Expo.util.packet.IncomingPacketHold;
import java.util.Map;
import net.minecraft.entity.EntityLivingBase;

public class BackTrack extends Module implements EventSubscriber {
   public static EntityLivingBase N;
   private static long[] m;
   public static HeaderSetting targetSettings;
   public static NumberSetting minDelay;
   private double K;
   private static Map h;
   public static NumberSetting maxRange;
   public static BooleanSetting friends;
   private static String[] g;
   public static NumberSetting minRange;
   private boolean E;
   private double u;
   public static NumberSetting minInterval;
   public static BooleanSetting animals;
   public static BooleanSetting teammates;
   private final TimerUtil p;
   public static BooleanSetting bots;
   public static BooleanSetting players;
   public static BooleanSetting bosses;
   private static long b;
   private final TimerUtil x;
   public static BooleanSetting mobs;
   private static String[] d;
   public static NumberSetting maxInterval;
   public static BooleanSetting enemies;
   public static NumberSetting maxDelay;

   public void onPreUpdate(char var1, int var2, PreUpdateEvent var3, short var4) {
      long var5 = ((long)var1 << 48 | (long)var2 << 32 >>> 16 | (long)var4 << 48 >>> 48) ^ b;
      long var7 = var5 ^ 42964908091563L;
      if (N != null && (RaytraceUtil.q(var7, N, minRange.L()) || !RaytraceUtil.q(var7, N, maxRange.L()))) {
         N = null;
         this.Y();
      }
   }

   public void A(long var1) {
      this.Y();
   }

   public void onRender2D(char var1, int var2, Render2DEvent var3, char var4) {
      if (N != null) {
         if (!this.E && this.x.A(0L)) {
            IncomingPacketHold.X(true);
            this.E = true;
            this.u = MathUtil.h(minDelay.L(), maxDelay.L());
            this.p.W();
         } else if (this.E && this.p.A(0L)) {
            this.Y();
            this.K = MathUtil.h(minInterval.L(), maxInterval.L());
            this.x.W();
         }
      }
   }

   public final void x(long var1, EventBus var3) {
      BackTrackBinder.P( var3, this);
   }

   public String g(long var1) {
      return (int)minDelay.L() == (int)maxDelay.L() ? (int)minDelay.L() + "ms" : (int)minDelay.L() + "-" + (int)maxDelay.L() + "ms";
   }

   public void onAttackEntity(AttackEntityEvent var1, long var2) {
      if (N == null
         && var1.O() instanceof EntityLivingBase
         && EntityUtil.q(var1.O(), players.c(), mobs.c(), animals.c(), bosses.c(), friends.c(), enemies.c(), teammates.c(), bots.c(), 21816078198602L)
         && !RaytraceUtil.q(50051018191872L, var1.O(), minRange.L())
         && RaytraceUtil.q(50051018191872L, var1.O(), maxRange.L())) {
         N = (EntityLivingBase)var1.O();
      }
   }

   public BackTrack(long var1) {
      super(((b ^ (var1)) ^ 136002511659636L));
      this.declare("BackTrack", Category.Combat, "Simulate network lags to get advantage at reaching enemies");
      var1 = b ^ var1;
      this.p = new TimerUtil();
      this.x = new TimerUtil();
      this.E = false;
      this.u = MathUtil.h(minDelay.L(), maxDelay.L());
      this.K = MathUtil.h(minInterval.L(), maxInterval.L());
   }

   private void Y() {
      if (this.E) {
         IncomingPacketHold.m();
         IncomingPacketHold.X(false);
         this.E = false;
      }
   }

   static {
      b = 110463194085250L;
      N = null;
   }

   static {
      minInterval = new NumberSetting("Min-interval", 0.0F, 0.0F, 500.0F, 1.0F);
      maxInterval = new NumberSetting("Max-interval", 0.0F, 0.0F, 500.0F, 1.0F);
      players = new BooleanSetting("Players", true);
      maxDelay = new NumberSetting("Max-delay", 100.0F, 0.0F, 500.0F, 1.0F);
      enemies = new BooleanSetting("Enemies", true);
      minDelay = new NumberSetting("Min-delay", 100.0F, 0.0F, 500.0F, 1.0F);
      minRange = new NumberSetting("Min-range", 1.0F, 0.0F, 10.0F, 0.1F);
      bosses = new BooleanSetting("Bosses", false);
      maxRange = new NumberSetting("Max-range", 5.0F, 0.0F, 10.0F, 0.1F);
      mobs = new BooleanSetting("Mobs", false);
      bots = new BooleanSetting("Bots", false);
      animals = new BooleanSetting("Animals", false);
      teammates = new BooleanSetting("Teammates", false);
      friends = new BooleanSetting("Friends", false);
   }
   static {
      targetSettings = new HeaderSetting("Target settings");
   }
}
