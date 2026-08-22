package Expo.module.impl.world;

import Expo.module.Category;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.AntiVoidBinder;
import Expo.event.events.SetKeyBindStateEvent;
import Expo.event.events.UpdateWalkingPlayerEvent;
import Expo.module.Module;
import Expo.module.ModuleManager;
import Expo.module.Modules;
import Expo.module.impl.movement.Stuck;
import Expo.setting.settings.ModeSetting;
import Expo.setting.settings.NumberSetting;
import Expo.util.ClientUtil;
import Expo.util.CombatUtil;
import Expo.util.MathUtil;
import Expo.util.Sneaky;
import Expo.util.packet.PacketManager;
import java.util.Map;
import net.minecraft.item.ItemEnderPearl;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C03PacketPlayer.C04PacketPlayerPosition;
import net.minecraft.util.AxisAlignedBB;

public class AntiVoid extends Module implements EventSubscriber {
   private double[] s;
   private boolean D;
   private boolean k;
   private boolean t;
   private static long a;
   public static ModeSetting mode;
   private boolean U;
   public static NumberSetting blinkFallDistance;

   private void j(int var1, short var2, short var3) {
      if (this.U) {
         PacketManager.j();
         PacketManager.M(false);
         this.U = false;
      }

      this.s = null;
   }

   public void onSetKeyBindState(SetKeyBindStateEvent var1, long var2, char var4) {
      long var5 = (var2 << 16 | (long)var4 << 48 >>> 48) ^ a;
      int var7 = (int)((var5 ^ 6419624339077L) >>> 32);
      int var8 = (int)((var5 ^ 6419624339077L) << 32 >>> 48);
      int var9 = (int)((var5 ^ 6419624339077L) << 48 >>> 48);
      if (var1.R == f.gameSettings.keyBindUseItem.getKeyCode()) {
         ItemStack var10 = f.thePlayer.inventory.getCurrentItem();
         if (var10 != null && var10.getItem() instanceof ItemEnderPearl) {
            this.j(var7, (short)var8, (short)var9);
         }
      }
   }

   public void onUpdateWalkingPlayer(UpdateWalkingPlayerEvent var1, long var2) {
       try {var2 = a ^ var2;
      int var4 = (int)((var2 ^ 3572517338279L) >>> 48);
      long var5 = (var2 ^ 3572517338279L) << 16 >>> 16;
      long var7 = var2 ^ 94798988160916L;
      int var9 = (int)((var2 ^ 22916981496296L) >>> 32);
      int var10 = (int)((var2 ^ 22916981496296L) << 32 >>> 48);
      int var11 = (int)((var2 ^ 22916981496296L) << 48 >>> 48);
      this.D = !f.thePlayer.capabilities.allowFlying && CombatUtil.u();
      if (!this.D) {
         this.j(var9, (short)var10, (short)var11);
      }

      switch (mode.Y()) {
         case "TOGGLE_STUCK":
            Stuck var19 = Modules.J(Stuck.class);
            if (var19.h() == 0) {
               ClientUtil.t(
                  var7,
                  "You must bind module \"\u00a7l" + var19.b() + "\u00a7r\" to a key to keep using " + mode.Y() + " mode"
               );
               this.u((short)var4, var5);
               return;
            }

            if (!this.k && this.D) {
               if (!var19.o()) {
                  var19.u((short)var4, var5);
               }

               this.t = true;
            } else if (this.t && (!this.D || f.thePlayer.onGround)) {
               if (var19.o()) {
                  var19.u((short)var4, var5);
               }

               this.t = false;
            }
            break;
         case "TOGGLE_SCAFFOLD":
            Scaffold var18 = ModuleManager.I;
            if (var18.h() == 0) {
               ClientUtil.t(
                  var7,
                  "You must bind module \"\u00a7l" + var18.b() + "\u00a7r\" to a key to keep using " + mode.Y() + " mode"
               );
               this.u((short)var4, var5);
               return;
            }

            if (!this.k && this.D) {
               if (!var18.o()) {
                  var18.u((short)var4, var5);
               }

               this.t = true;
            } else if (this.t && f.thePlayer.onGround) {
               if (var18.o()) {
                  var18.u((short)var4, var5);
               }

               this.t = false;
            }
            break;
         case "BLINK":
            if (this.s != null) {
               float var14 = f.thePlayer.width / 2.0F;
               float var15 = f.thePlayer.height;
               if (CombatUtil.T(new AxisAlignedBB(this.s[0] - var14, this.s[1], this.s[2] - var14, this.s[0] + var14, this.s[1] + var15, this.s[2] + var14))) {
                  this.j(var9, (short)var10, (short)var11);
               }
            }

            if (!this.k && this.D) {
               PacketManager.M(true);
               this.U = true;
               this.s = new double[]{f.thePlayer.prevPosX, f.thePlayer.prevPosY, f.thePlayer.prevPosZ};
            }

            if (PacketManager.Z && this.s != null && this.s[1] - blinkFallDistance.L() > f.thePlayer.posY) {
               double var17 = this.s[1] - MathUtil.h(10.0F, 20.0F);
               PacketManager.u.add(0, new C04PacketPlayerPosition(this.s[0], var17, this.s[2], false));
               this.j(var9, (short)var10, (short)var11);
            }
      }

      this.k = this.D;
   } catch (Throwable zkm$t) { throw Sneaky.rethrow(zkm$t); } }

   public String g(long var1) {
      return String.valueOf(blinkFallDistance.L());
   }

   public final void x(long var1, EventBus var3) {
      AntiVoidBinder.Q(var3, this);
   }

   public AntiVoid(long var1) {
      super(((a ^ (var1)) ^ 95315799941131L));
      this.declare("AntiVoid", Category.World, "Prevent you from falling into the void");
      var1 = a ^ var1;
      this.D = false;
      this.k = false;
      this.s = null;
      this.U = false;
      this.t = false;
   }

   static {
      a = 54765974288452L;
   }

   public void A(long var1) {
      int var3 = (int)((var1 ^ 66606683561185L) >>> 32);
      int var4 = (int)((var1 ^ 66606683561185L) << 32 >>> 48);
      int var5 = (int)((var1 ^ 66606683561185L) << 48 >>> 48);
      this.D = false;
      this.k = false;
      this.t = false;
      this.j(var3, (short)var4, (short)var5);
   }

   static {
      blinkFallDistance = new NumberSetting("Blink-fall-distance", 4.0F, 0.0F, 8.0F, 0.1F);
   }
   static {
      mode = new ModeSetting("Mode", false, "TOGGLE_STUCK", "TOGGLE_SCAFFOLD", "TOGGLE_STUCK", "BLINK");
   }
}
