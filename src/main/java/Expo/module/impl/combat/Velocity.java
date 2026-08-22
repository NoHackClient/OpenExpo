package Expo.module.impl.combat;

import Expo.module.Category;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.VelocityBinder;
import Expo.event.events.KnockbackEvent;
import Expo.event.events.PreLivingUpdateEvent;
import Expo.event.events.PreMouseInputEvent;
import Expo.event.events.PreTickEvent;
import Expo.event.events.ReceivePacketEvent;
import Expo.event.events.WorldLoadEvent;
import Expo.module.Module;
import Expo.module.Modules;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.HeaderSetting;
import Expo.setting.settings.NumberSetting;
import Expo.setting.settings.PercentageSetting;
import Expo.util.EntityUtil;
import Expo.util.KeyBindUtil;
import Expo.util.MathUtil;
import Expo.util.MoveUtil;
import Expo.util.RaytraceUtil;
import Expo.util.packet.IncomingPacketHold;
import Expo.util.packet.OutgoingPacketState;
import Expo.util.packet.PacketManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C02PacketUseEntity.Action;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.network.play.server.S19PacketEntityStatus;
import net.minecraft.network.play.server.S27PacketExplosion;











public class Velocity extends Module implements EventSubscriber {
   public static BooleanSetting animals;
   private boolean k;
   public static HeaderSetting targetSettings;
   private boolean E;
   public static BooleanSetting bosses;
   public static NumberSetting minDelayTicks;
   public static BooleanSetting reduceVelocity;
   private boolean N;
   public static BooleanSetting enemies;
   private List<EntityLivingBase> O;
   private boolean I;
   public static BooleanSetting mobs;
   private int v;
   public static NumberSetting maxDelayTicks;
   private static long c;
   private static String[] o;
   public static BooleanSetting delayVelocity;
   private boolean t;
   private boolean a;
   public static NumberSetting reduceEffectTicks;
   public static BooleanSetting delayReleaseOnReduce;
   public static BooleanSetting teammates;
   public static BooleanSetting bots;
   public static PercentageSetting horizontal;
   public static BooleanSetting modifyVelocity;
   public static BooleanSetting players;
   public static BooleanSetting reverseVelocity;
   public static BooleanSetting friends;
   private double n;
   private static Map C;
   public static BooleanSetting delayReleaseOnGround;
   public static BooleanSetting disableWhileHoldingS;
   private double g;
   public static PercentageSetting vertical;
   public static NumberSetting fov;
   public static PercentageSetting chance;
   public static BooleanSetting requireMoving;



   public Velocity(int var1, byte var2, int var3) {
      super(((((((long)((var1)) << 32) | (((long)((var2)) << 56) >>> 32)) | (((long)((var3)) << 40) >>> 40)) ^ c) ^ 128865969691450L));
      // add code
      this.declare("Velocity", Category.Combat, "Modify the velocity received");
      this.E = false;
      this.I = false;
      this.g = 0.0;
      this.n = MathUtil.h(minDelayTicks.L(), maxDelayTicks.L());
      this.N = true;
      this.a = false;
      this.v = 0;
      this.k = false;
      this.t = false;
      this.O = new ArrayList<>();
   }

   public void onPreTick(PreTickEvent var1, long var2) {


      this.y(49587205232658L);
      if (this.k) {
         this.v++;
         if (this.v >= (int)reduceEffectTicks.L()) {
            this.k = false;
            this.a = false;
            this.v = 0;
         }
      }

      if (disableWhileHoldingS.c() && KeyBindUtil.V(f.gameSettings.keyBindBack.getKeyCode(), 64165991731362L)) {
         this.Q();
      }

      if (this.O.isEmpty()) {
         this.Q();
      }

      if (this.I) {
         this.g++;
         this.o(0L);
      }
   }

   public String g(long var1) {
      if (modifyVelocity.c()) {
         if (reverseVelocity.c()) {
            return "§m" + horizontal.k() + "%§r " + vertical.k() + "%";
         } else {
            return horizontal.k() != vertical.k() ? horizontal.k() + "% " + vertical.k() + "%" : horizontal.k() + "%";
         }
      } else if (delayVelocity.c() || reduceVelocity.c()) {
         StringBuilder var3 = new StringBuilder();
         boolean var4 = false;
         if (delayVelocity.c()) {
            if (minDelayTicks.L() == maxDelayTicks.L()) {
               var3.append((int)minDelayTicks.L());
            } else {
               var3.append((int)minDelayTicks.L()).append("-").append((int)maxDelayTicks.L());
            }

            if ((int)maxDelayTicks.L() <= 1) {
               var3.append("TICK");
            } else {
               var3.append("TICKS");
            }

            var4 = true;
         }

         if (reduceVelocity.c()) {
            if (var4) {
               var3.append(", REDUCE");
            } else {
               var3.append("REDUCE");
            }
         }

         return var3.toString();
      } else {
         return reverseVelocity.c() ? "§m" + horizontal.k() + "%§r " + vertical.k() + "%" : "NONE";
      }
   }

   public void onKnockback(long var1, KnockbackEvent var3) {

      if (this.E) {
         this.E = false;
      } else {
         if (this.isGetKeyCode(8945674770656L) && modifyVelocity.c()) {
            if (reverseVelocity.c()) {
               var3.P(-(var3.S() * horizontal.k() / 100.0));
               var3.A(-(var3.R() * horizontal.k() / 100.0));
            } else {
               if (horizontal.k() != 100) {
                  var3.P(var3.S() * horizontal.k() / 100.0);
                  var3.A(var3.R() * horizontal.k() / 100.0);
               }

               if (vertical.k() != 100) {
                  var3.O(var3.f() * vertical.k() / 100.0);
               }
            }
         }

         this.k = true;
         this.a = reduceVelocity.c();
         this.v = 0;
      }
   }

   private static void a() {
   }

   public void A(long var1) {
      this.D(0L);
   }

   private void o(long var1) {
      if (this.g >= this.n || delayReleaseOnGround.c() && f.thePlayer.onGround || delayReleaseOnReduce.c() && this.t || f.thePlayer.isInWater() || f.thePlayer.isInLava()) {
         this.Q();
      }
   }

   static {
      c = 138275096110154L;
   }

   public void onWorldLoad(WorldLoadEvent var1, long var2) {
      this.D(0L);
   }

   private boolean isGetKeyCode(long var1) {
      var1 = c ^ var1;
      long var5 = var1 ^ 97826838717624L;
      long var7 = var1 ^ 87667548924936L;
      this.y(var5);
      return MathUtil.Q(chance.k(),0L) && (!disableWhileHoldingS.c() || !KeyBindUtil.V(f.gameSettings.keyBindBack.getKeyCode(), var7)) && !this.O.isEmpty() && (!requireMoving.c() || MoveUtil.o());
   }



   public void onPreLivingUpdate(long var1, PreLivingUpdateEvent var3) {

      this.y(49587205232658L);
   }

   private void k(long var1, ReceivePacketEvent var3) {




      S12PacketEntityVelocity var9 = (S12PacketEntityVelocity)var3.d;
      if (this.I) {
         IncomingPacketHold.p().add(var9);
         var3.I(17581, 3624099827L);
      } else if (!Modules.J(JumpReset.class).o() || !JumpReset.C(132648017398215L) || !f.thePlayer.onGround) {
         if (!this.N) {
            if (this.E) {
               this.E = false;
            } else {
               IncomingPacketHold.p().add(var9);
               IncomingPacketHold.X(true);
               this.I = true;
               this.g = 0.0;
               var3.I(17581, 3624099827L);
            }
         }
      }
   }

   public final void x(long var1, EventBus var3) {
      VelocityBinder.T(var3, this);
   }

   private void D(long var1) {
      this.t = false;
      this.k = false;
      this.v = 0;
      this.N = true;
      this.a = false;
      this.E = false;
      this.Q();
   }

   public void onReceivePacket(int var1, char var2, int var3, ReceivePacketEvent var4) {
      long var5 = ((long)var1 << 32 | (long)var2 << 48 >>> 32 | (long)var3 << 48 >>> 48) ^ c;
      long var7 = var5 ^ 95536569560952L;
      int var9 = (int)((var5 ^ 30022235916125L) >>> 32);
      long var10 = (var5 ^ 30022235916125L) << 32 >>> 32;
      Packet var12 = var4.d;
      if (var12 instanceof S12PacketEntityVelocity) {
         S12PacketEntityVelocity var13 = (S12PacketEntityVelocity)var12;
         if (var13.getEntityID() != f.thePlayer.getEntityId()) {
            return;
         }

         if (horizontal.k() == 0 && vertical.k() == 0 && modifyVelocity.c()) {
            var4.I(var9, var10);
         }

         if (delayVelocity.c()) {
            this.k(var7, var4);
         }
      } else if (var12 instanceof S19PacketEntityStatus) {
         S19PacketEntityStatus var14 = (S19PacketEntityStatus)var12;
         if (var14.getEntity(f.theWorld) instanceof EntityPlayerSP && var14.getOpCode() == 2) {
            this.N = false;
         }
      } else if (var12 instanceof S27PacketExplosion) {
         S27PacketExplosion var15 = (S27PacketExplosion)var12;
         if (var15.func_149149_c() != 0.0F || var15.func_149144_d() != 0.0F || var15.func_149147_e() != 0.0F) {
            this.E = true;
         }
      }
   }



   private void y(long var1) {
      var1 = c ^ var1;
      long var3 = var1 ^ 32937153565671L;
      long var5 = var1 ^ 38950497122322L;
      this.O = EntityUtil.K(EntityUtil.F(10.0, var3, fov.L()), players.c(), var5, mobs.c(), animals.c(), bosses.c(), friends.c(), enemies.c(), teammates.c(), bots.c());
   }

   private void Q() {
      this.n = MathUtil.h(minDelayTicks.L(), maxDelayTicks.L());
      if (this.I) {
         IncomingPacketHold.m();
         IncomingPacketHold.X(false);
         this.I = false;
      }

      this.g = 0.0;
      this.N = true;
      this.E = false;
      this.t = false;
   }

   private EntityLivingBase isSprinting(long var1) {
      var1 = c ^ var1;
      long var3 = var1 ^ 65374042214382L;
      long var5 = var1 ^ 120732458344074L;
      EntityLivingBase var7 = null;
      List var8 = RaytraceUtil.j(3.0);
      int var9 = 0;

      for (int var10 = var8.size(); var9 < var10; var9++) {
         EntityLivingBase var11 = (EntityLivingBase)var8.get(var9);
         if (var11 instanceof EntityPlayer) {
            var7 = var11;
            break;
         }
      }

      if (var7 == null || !this.isGetKeyCode(var5) || !f.thePlayer.isSprinting()) {
         return null;
      } else if (Expo.internal.accessor.EntityAccessor.F(f.thePlayer, var3)) {
         return null;
      } else {
         return !OutgoingPacketState.f() ? null : var7;
      }
   }

   public void onPreMouseInput(int var1, PreMouseInputEvent var2, char var3, short var4) {
      long var5 = ((long)var1 << 32 | (long)var3 << 48 >>> 32 | (long)var4 << 48 >>> 48) ^ c;
      long var7 = var5 ^ 21578426542912L;
      if (this.a && this.k) {
         EntityLivingBase var9 = this.isSprinting(var7);
         if (var9 == null) {
            return;
         }

         f.thePlayer.swingItem();
         PacketManager.b(new C02PacketUseEntity(var9, Action.ATTACK));
         f.thePlayer.motionX *= 0.6;
         f.thePlayer.motionZ *= 0.6;
         f.thePlayer.setSprinting(false);
         this.t = true;
         var2.Q(true);
         var2.G(true);
         var2.M(true);
      }
   }


   static {
      // add code
      enemies = new BooleanSetting("Enemies", true);
      delayReleaseOnReduce = new BooleanSetting("Delay-release-on-reduce", true);
      disableWhileHoldingS = new BooleanSetting("Disable-while-holding-S", true);
      minDelayTicks = new NumberSetting("Min-delay-ticks", 3.0F, 1.0F, 20.0F, 1.0F);
      delayVelocity = new BooleanSetting("Delay-velocity", true);
      friends = new BooleanSetting("Friends", false);
      mobs = new BooleanSetting("Mobs", false);
      maxDelayTicks = new NumberSetting("Max-delay-ticks", 3.0F, 1.0F, 20.0F, 1.0F);
      reverseVelocity = new BooleanSetting("Reverse-velocity", false);
      reduceEffectTicks = new NumberSetting("Reduce-effect-ticks", 10.0F, 0.0F, 20.0F, 1.0F);
      requireMoving = new BooleanSetting("Require-moving", false);
      modifyVelocity = new BooleanSetting("Modify-velocity", false);
      bosses = new BooleanSetting("Bosses", false);
      reduceVelocity = new BooleanSetting("Reduce-velocity", false);
      players = new BooleanSetting("Players", true);
      horizontal = new PercentageSetting("Horizontal", 100);
      chance = new PercentageSetting("Chance", 100);
      animals = new BooleanSetting("Animals", false);
      teammates = new BooleanSetting("Teammates", false);
      fov = new NumberSetting("FOV", 360.0F, 0.0F, 360.0F, 1.0F);
      vertical = new PercentageSetting("Vertical", 100);
      bots = new BooleanSetting("Bots", false);
      delayReleaseOnGround = new BooleanSetting("Delay-release-on-ground", true);
   }
   static {
      // add code
      targetSettings = new HeaderSetting("Target settings");
   }
}
