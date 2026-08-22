package Expo.module.impl.combat;

import Expo.module.Category;

import Expo.enums.RotationMode;
import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.AntiFireballBinder;
import Expo.event.events.PreMouseInputEvent;
import Expo.event.events.PreTickEvent;
import Expo.event.events.WorldLoadEvent;
import Expo.module.PriorityModule;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.ModeSetting;
import Expo.setting.settings.NumberSetting;
import Expo.util.CombatUtil;
import Expo.util.RaytraceUtil;
import Expo.util.RotationManager;
import Expo.util.RotationUtil;
import Expo.util.packet.OutgoingPacketState;
import Expo.util.packet.PacketManager;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.network.play.client.C0APacketAnimation;

public class AntiFireball extends PriorityModule implements EventSubscriber {
   private EntityFireball G;
   public static NumberSetting fov;
   public static ModeSetting moveFix;
   private static long a;
   private final List<EntityFireball> r;
   private final List<EntityFireball> K;
   public static BooleanSetting swing;
   public static NumberSetting range;
   private boolean N;

   private void m(long var1) {
      this.T(false);
      if (this.N) {
         RotationManager.O(123115463851087L);
         this.N = false;
      }
   }

   public void onPreTick(char var1, int var2, short var3, PreTickEvent var4) {
      long var5 = ((long)var1 << 48 | (long)var2 << 32 >>> 16 | (long)var3 << 48 >>> 48) ^ a;
      long var7 = (var5 ^ 116474791401801L) >>> 32;
      int var9 = (int)((var5 ^ 116474791401801L) << 32 >>> 32);
      ArrayList var10 = new ArrayList();
      List var11 = f.theWorld.loadedEntityList;
      int var12 = 0;

      for (int var13 = var11.size(); var12 < var13; var12++) {
         Entity var14 = (Entity)var11.get(var12);
         if (var14 instanceof EntityFireball) {
            var10.add((EntityFireball)var14);
         }
      }

      this.K
         .removeIf(var1x -> !var10.contains(var1x));
      this.r.removeIf(var1x -> !var10.contains(var1x));
      var12 = 0;

      for (int var20 = var10.size(); var12 < var20; var12++) {
         EntityFireball var22 = (EntityFireball)var10.get(var12);
         if (!this.K
               .contains(var22)
            && !this.r.contains(var22)) {
            if (RaytraceUtil.i(var22) > 3.0) {
               this.K
                  .add(var22);
            } else {
               this.r.add(var22);
            }
         }
      }

      if (f.thePlayer.capabilities.allowFlying) {
         this.G = null;
      } else {
         boolean var19 = false;
         EntityFireball var21 = null;
         Comparator<EntityFireball> var23 = Comparator.comparingDouble(RaytraceUtil::i);
         int var15 = 0;

         for (int var16 = this.K
               .size();
            var15 < var16;
            var15++
         ) {
            EntityFireball var17 = (EntityFireball)this.K
               .get(var15);
            if (this.V(var7, var9, var17) && (!var19 || var23.compare(var17, var21) < 0)) {
               var19 = true;
               var21 = var17;
            }
         }

         this.G = var19 ? var21 : null;
      }
   }

   private void swingItem() {
      if (swing.c()) {
         f.thePlayer.swingItem();
      } else {
         PacketManager.b(new C0APacketAnimation());
      }
   }

   public final void x(long var1, EventBus var3) {
      AntiFireballBinder.r(var3, this);
   }

   public void onWorldLoad(WorldLoadEvent var3) {
      this.K.clear();
      this.r.clear();
   }

   public AntiFireball(long var1) {
      super((((a ^ (var1)) ^ 77523410250733L) >>> 16), (char)((int)(((((a ^ (var1)) ^ 77523410250733L) << 48) >>> 48))));
      this.declare("AntiFireball", Category.Combat, "Hit fireballs back");
      var1 = a ^ var1;
      this.K = new ArrayList<>();
      this.r = new ArrayList<>();
      this.G = null;
      this.N = false;
   }

   private boolean V(long var1, int var3, EntityFireball var4) {
      long var5 = (var1 << 32 | (long)var3 << 32 >>> 32) ^ a;
      long var7 = var5 ^ 71130462620705L;
      long var9 = var5 ^ 34977543377997L;
      return var4 != null && RaytraceUtil.q(var7, var4, range.L() + 3.0) && RotationUtil.b(var9, var4, fov.L());
   }

   static {
      a = 119630054251485L;
   }

   public void A(long var1) {
      this.m(0L);
   }

   public void onPreMouseInput(PreMouseInputEvent var1, long var2) {
      EntityFireball var16 = this.G;
      if (this.G != null && f.theWorld.loadedEntityList.contains(var16) && this.Y() && OutgoingPacketState.f()) {
         this.T(true);
         switch (moveFix.Y()) {
            case "SILENT":
               RotationManager.n(RotationMode.SILENT);
               break;
            case "STRICT":
               RotationManager.n(RotationMode.STRICT);
               break;
            case "NONE":
               RotationManager.n(RotationMode.NONE);
         }

         float[] var20 = RotationUtil.h(11022, this.G.getEntityBoundingBox(), (byte)99, 9521810);
         RotationManager.N(71285564916286L, var20[0], var20[1]);
         this.N = true;
         if (RaytraceUtil.q(50051018191872L, this.G, range.L())) {
            this.swingItem();
            CombatUtil.I(this.G, 10456, 1760016611L);
            return;
         }
      }

      this.m(0L);
   }

   public String g(long var1) {
      return String.valueOf(range.L());
   }

   static {
      swing = new BooleanSetting("Swing", true);
   }
   static {
      range = new NumberSetting("Range", 5.0F, 0.0F, 10.0F, 0.1F);
      fov = new NumberSetting("FOV", 180.0F, 0.0F, 360.0F, 1.0F);
   }
   static {
      moveFix = new ModeSetting("Move-fix", "SILENT", "STRICT", "NONE");
   }
}
