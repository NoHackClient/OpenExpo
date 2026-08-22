package Expo.module.impl.combat;

import Expo.module.Category;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.FakeLagBinder;
import Expo.event.events.PostTickEvent;
import Expo.event.events.PreLivingUpdateEvent;
import Expo.event.events.Render2DEvent;
import Expo.event.events.SendPacketEvent;
import Expo.module.Module;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.HeaderSetting;
import Expo.setting.settings.NumberSetting;
import Expo.util.EntityUtil;
import Expo.util.ItemUtil;
import Expo.util.MathUtil;
import Expo.util.RaytraceUtil;
import Expo.util.RotationUtil;
import Expo.util.TimerUtil;
import Expo.util.packet.PacketManager;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C07PacketPlayerDigging.Action;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;

public class FakeLag extends Module implements EventSubscriber {
   public static BooleanSetting bots;
   public static HeaderSetting targetSettings;
   private long b;
   public static NumberSetting disableRange;
   public static NumberSetting maxInterval;
   public static BooleanSetting swordOnly;
   public static NumberSetting maxDuration;
   private static boolean K;
   public static BooleanSetting friends;
   public static NumberSetting targetRange;
   private long k;
   private final Set<EntityLivingBase> m;
   public static NumberSetting minDuration;
   public static NumberSetting minInterval;
   public static BooleanSetting enemies;
   public static NumberSetting allowedTargetsAmount;
   private final TimerUtil s;
   public static BooleanSetting teammates;
   private final TimerUtil M;
   public static BooleanSetting players;
   public static NumberSetting fov;
   private static boolean G;
   public static BooleanSetting bosses;
   public static BooleanSetting animals;
   private static long c;
   public static BooleanSetting mobs;
   private static boolean t;

   public long A() {
      return this.k;
   }

   public void A(long var1) {
      this.W();
   }

   public final void x(long var1, EventBus var3) {
      FakeLagBinder.S(var3, this);
   }

   public void onSendPacket(long var1, SendPacketEvent var3) {
      if (this.Z(var3.B)) {
         this.W();
      }
   }

   private boolean Z(Packet<?> var1) {
      if (var1 instanceof C02PacketUseEntity) {
         return true;
      }

      if (var1 instanceof C07PacketPlayerDigging) {
         return ((C07PacketPlayerDigging)var1).getStatus() != Action.RELEASE_USE_ITEM;
      }

      if (!(var1 instanceof C08PacketPlayerBlockPlacement)) {
         return false;
      }

      ItemStack var2 = ((C08PacketPlayerBlockPlacement)var1).getStack();
      return var2 == null || !(var2.getItem() instanceof ItemSword);
   }

   static {
      c = 61756971341787L;
      t = false;
      G = false;
      K = false;
   }

   public String g(long var1) {
      return minDuration.L() == maxDuration.L() ? String.valueOf((int)minDuration.L()) : (int)minDuration.L() + "-" + (int)maxDuration.L();
   }

   public void onRender2D(long var1, Render2DEvent var3) {
      if (swordOnly.c() && !ItemUtil.d()) {
         this.W();
      } else if (this.m.isEmpty()) {
         this.W();
      } else if (this.m.size() >= allowedTargetsAmount.L()) {
         this.W();
      } else {
         if (!G && this.s.L(this.b, true)) {
            this.M.W();
            PacketManager.M(true);
            K = true;
            t = true;
            G = true;
            this.b = (long)MathUtil.h(minInterval.L(), maxInterval.L());
         }

         if (G && this.M.L(this.k, true)) {
            G = false;
            this.W();
            this.k = (long)MathUtil.h(minDuration.L(), maxDuration.L());
            this.s.W();
         }
      }
   }

   public FakeLag(int var1, int var2, short var3) {
      super(((((((long)((var1)) << 32) | (((long)((var2)) << 48) >>> 32)) | (((long)((var3)) << 48) >>> 48)) ^ c) ^ 51643362766489L));
      this.declare("FakeLag", Category.Combat, "Simulate network lags to get advantages during combat");
      this.M = new TimerUtil();
      this.s = new TimerUtil();
      this.m = new HashSet<>();
      this.k = (long)MathUtil.h(minDuration.L(), maxDuration.L());
      this.b = (long)MathUtil.h(minInterval.L(), maxInterval.L());
   }

   private void W() {
      if (t) {
         PacketManager.j();
         PacketManager.M(false);
         t = false;
         this.M.W();
      }

      K = false;
      G = false;
   }

   public void onPreLivingUpdate(PreLivingUpdateEvent var1, long var2) {
      this.m.clear();
      boolean var14 = players.c();
      boolean var15 = mobs.c();
      boolean var16 = animals.c();
      boolean var17 = bosses.c();
      boolean var18 = friends.c();
      boolean var19 = enemies.c();
      boolean var20 = teammates.c();
      boolean var21 = bots.c();
      boolean var22 = var14 && !var15 && !var16 && !var17;
      int var23 = (int)targetRange.L();
      int var24 = (int)disableRange.L();
      int var25 = (int)fov.L();
      List var26 = EntityUtil.U( var22);
      int var27 = 0;

      for (int var28 = var26.size(); var27 < var28; var27++) {
         EntityLivingBase var29 = (EntityLivingBase)var26.get(var27);
         if ((
               var22
                  ? EntityUtil.c(30808997819832L, (EntityPlayer)var29, var18, var19, var20, var21)
                  : EntityUtil.q(var29, var14, var15, var16, var17, var18, var19, var20, var21, 21816078198602L)
            )
            && RaytraceUtil.q(50051018191872L, var29, var23)
            && !RaytraceUtil.q(50051018191872L, var29, var24)
            && RotationUtil.b(126426268413036L, var29, var25)) {
            this.m.add(var29);
         }
      }
   }

   public void onPostTick(PostTickEvent var1, long var2) {
      if (f.thePlayer.isDead) {
         this.W();
      }
   }

   static {
      targetRange = new NumberSetting("Target-range", 10.0F, 0.0F, 20.0F, 0.1F);
      bots = new BooleanSetting("Bots", false);
      animals = new BooleanSetting("Animals", false);
      swordOnly = new BooleanSetting("Sword-only", true);
      enemies = new BooleanSetting("Enemies", true);
      allowedTargetsAmount = new NumberSetting("Allowed-targets-amount", 3.0F, 0.0F, 20.0F, 1.0F);
      maxInterval = new NumberSetting("Max-interval", 100.0F, 0.0F, 1000.0F, 1.0F);
      disableRange = new NumberSetting("Disable-range", 3.0F, 0.0F, 15.0F, 0.1F);
      minInterval = new NumberSetting("Min-interval", 50.0F, 0.0F, 1000.0F, 1.0F);
      mobs = new BooleanSetting("Mobs", false);
      teammates = new BooleanSetting("Teammates", false);
      players = new BooleanSetting("Players", true);
      bosses = new BooleanSetting("Bosses", false);
      fov = new NumberSetting("FOV", 180.0F, 0.0F, 360.0F, 1.0F);
      friends = new BooleanSetting("Friends", false);
      minDuration = new NumberSetting("Min-duration", 50.0F, 0.0F, 500.0F, 1.0F);
      maxDuration = new NumberSetting("Max-duration", 100.0F, 0.0F, 500.0F, 1.0F);
   }
   static {
      targetSettings = new HeaderSetting("Target settings");
   }
}
