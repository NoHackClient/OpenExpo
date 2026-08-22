package Expo.module.impl.combat;

import Expo.module.Category;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.SprintResetBinder;
import Expo.event.events.AttackEntityEvent;
import Expo.event.events.AttackTargetEntityEvent;
import Expo.event.events.MoveInputEvent;
import Expo.event.events.PostTickEvent;
import Expo.event.events.PreSuperLivingUpdateEvent;
import Expo.event.events.PreUpdateEvent;
import Expo.event.events.ReceivePacketEvent;
import Expo.module.Module;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.ModeSetting;
import Expo.setting.settings.NumberSetting;
import Expo.util.KeyBindUtil;
import Expo.util.RaytraceUtil;
import Expo.util.TimerUtil;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.server.S19PacketEntityStatus;











public class SprintReset extends Module implements EventSubscriber {
   private boolean K;
   private final TimerUtil o;
   private boolean u;
   private EntityLivingBase E;
   public static NumberSetting duration;
   public static ModeSetting mode;
   private boolean C;
   public static BooleanSetting requireTargetDamage;
   private static long a;
   private final TimerUtil U;
   private boolean h;
   private boolean g;
   public static NumberSetting interval;
   private boolean H;


   public void A(long var1) {
      long var3 = var1 ^ 17179273251418L;
      this.g = false;
      this.C = false;
      this.E = null;
      if (this.H) {
         KeyBindUtil.o(var3, f.gameSettings.keyBindForward.getKeyCode());
         this.H = false;
      }

      this.h = false;
   }

   static {
      a = 135756777388346L;
   }

   public void onPreUpdate(int var1, PreUpdateEvent var2, int var3, int var4) {
      long var5 = ((long)var1 << 32 | (long)var3 << 48 >>> 32 | (long)var4 << 48 >>> 48) ^ a;
      long var7 = var5 ^ 75179329960975L;
      if (this.E != null && RaytraceUtil.q(var7, this.E, 6.0)) {
         this.E = null;
      }
   }

   public void onPostTick(PostTickEvent var1) {

      if (this.H && this.o.A(duration.L())) {
         KeyBindUtil.o(99363263780575L, f.gameSettings.keyBindForward.getKeyCode());
         this.H = false;
      }
   }

   public final void x(long var1, EventBus var3) {
      SprintResetBinder.G(var3, this);
   }


   public void onReceivePacket(short var1, int var2, ReceivePacketEvent var3, char var4) {
      if (this.C && this.E != null && var3.d instanceof S19PacketEntityStatus) {
         S19PacketEntityStatus var7 = (S19PacketEntityStatus)var3.d;
         if (var7.getEntity(f.theWorld) == this.E && var7.getOpCode() == 2) {
            this.g = true;
            this.C = false;
         }
      }
   }

   public void onAttackEntity(AttackEntityEvent var1) {

      if (this.U.A(interval.L())) {
         Entity var6 = var1.O();
         if (var6 instanceof EntityLivingBase) {
            this.E = (EntityLivingBase)var1.O();
            switch (mode.Y()) {
               case "LEGIT":
                  if (!this.H && f.thePlayer.isSprinting()) {
                     if (requireTargetDamage.c()) {
                        if (!this.g) {
                           this.C = true;
                        } else {
                           this.g = false;
                           this.u = true;
                        }
                     } else {
                        this.o.W();
                        KeyBindUtil.A(82009306480869L, f.gameSettings.keyBindForward.getKeyCode(), false);
                        f.thePlayer.movementInput.moveForward = 0.0F;
                        f.thePlayer.movementInput.moveStrafe = 0.0F;
                        this.U.W();
                        this.H = true;
                     }
                  }
                  break;
               case "NO_STOP":
                  if (f.thePlayer.isSprinting()) {
                     if (requireTargetDamage.c()) {
                        if (!this.g) {
                           this.C = true;
                        } else {
                           this.g = false;
                           this.h = false;
                           this.K = true;
                        }
                     } else {
                        this.h = false;
                        this.K = true;
                     }
                  }
            }
         }
      }
   }


   public SprintReset(int var1, char var2, short var3) {
      super(((((((long)((var1)) << 32) | (((long)((var2)) << 48) >>> 32)) | (((long)((var3)) << 48) >>> 48)) ^ a) ^ 73452483024062L));
      // add code
      this.declare("SprintReset", Category.Combat, "Reset sprint state during combat to give more knockback to opponent");
      this.o = new TimerUtil();
      this.U = new TimerUtil();
      this.H = false;
      this.E = null;
      this.C = false;
      this.g = false;
      this.u = false;
      this.K = false;
      this.h = false;
   }

   public void onMoveInput(MoveInputEvent var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {

      if (mode.R("LEGIT") && this.u) {
         this.o.W();
         KeyBindUtil.A(82009306480869L, f.gameSettings.keyBindForward.getKeyCode(), false);
         var3.i(0.0F);
         var3.A(0.0F);
         this.U.W();
         this.H = true;
         this.u = false;
      }
   }

   public void onPreSuperLivingUpdate(short var1, PreSuperLivingUpdateEvent var2, int var3, short var4) {
      if (this.h) {
         this.h = false;
         f.thePlayer.setSprinting(false);
      }
   }

   public void onAttackTargetEntity(AttackTargetEntityEvent var3) {
      if (var3.w instanceof EntityPlayer && this.K) {
         this.K = false;
         this.h = true;
      }
   }


   public String g(long var1) {
      return mode.Y();
   }

   static {
      // add code
      mode = new ModeSetting("Mode", "NO_STOP", "LEGIT");
      interval = new NumberSetting("Interval", 400.0F, 0.0F, 2000.0F, 1.0F);
      requireTargetDamage = new BooleanSetting("Require-target-damage", true);
      duration = new NumberSetting("Duration", 50.0F, 0.0F, 200.0F, 1.0F);
   }
}
