package Expo.module.impl.combat;

import Expo.module.Category;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.HitSelectBinder;
import Expo.event.events.AttackEntityEvent;
import Expo.event.events.PreMouseInputEvent;
import Expo.event.events.ReceivePacketEvent;
import Expo.module.Module;
import Expo.setting.settings.ModeSetting;
import Expo.setting.settings.NumberSetting;
import Expo.setting.settings.PercentageSetting;
import Expo.util.AttackTracker;
import Expo.util.MathUtil;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.server.S19PacketEntityStatus;
import net.minecraft.potion.Potion;











public class HitSelect extends Module implements EventSubscriber {
   private int L;
   public static ModeSetting strategy;
   public static NumberSetting minPauseTick;
   private EntityPlayer D;
   private int m;
   public static NumberSetting maxPauseTick;
   private static long a;
   public static PercentageSetting chance;

   public String g(long var1) {
      return strategy.Y();
   }

   public HitSelect(long var1) {
      super(((a ^ (var1)) ^ 101385361259381L));
      // add code
      this.declare("HitSelect", Category.Combat, "Modify your attacking strategy to get more hits in combat");
      var1 = a ^ var1;
      this.D = null;
      this.m = 0;
      this.L = 0;
   }

   public final void x(long var1, EventBus var3) {
      HitSelectBinder.J(var3, this);
   }

   public void onPreMouseInput(long var1, PreMouseInputEvent var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      if (this.m > 0) {
         this.m--;
      }

      if (this.m <= 0) {
         this.D = null;
      }

      if (this.D != null) {
         switch (strategy.Y()) {
            case "NORMAL":
               AttackTracker.Z(this.L <= 0);
               break;
            case "CRITICALS":
               if (f.thePlayer.onGround) {
                  AttackTracker.Z(true);
               } else {
                  boolean var6 = f.thePlayer.fallDistance > 0.0F
                     && !f.thePlayer.isOnLadder()
                     && !f.thePlayer.isInWater()
                     && !f.thePlayer.isPotionActive(Potion.blindness)
                     && f.thePlayer.ridingEntity == null;
                  if (var6) {
                     AttackTracker.Z(this.L <= 0);
                  } else {
                     AttackTracker.Z(false);
                  }
               }
         }
      } else {
         AttackTracker.Z(true);
      }

      if (this.L > 0) {
         this.L--;
      }
   }





   public void P(long var1) {
      AttackTracker.Z(true);
      this.m = 0;
      this.L = 0;
      this.D = null;
   }

   public void onReceivePacket(char var1, ReceivePacketEvent var2, int var3, short var4) {
      if (var2.d instanceof S19PacketEntityStatus) {
         S19PacketEntityStatus var9 = (S19PacketEntityStatus)var2.d;
         if (var9.getEntity(f.theWorld) instanceof EntityPlayerSP && var9.getOpCode() == 2 && MathUtil.Q(chance.k(),0L)) {
            this.L = (int)MathUtil.h(minPauseTick.L(), maxPauseTick.L());
         }
      }
   }

   public void onAttackEntity(long var1, AttackEntityEvent var3) {
      if (var3.O() instanceof EntityPlayer) {
         this.D = (EntityPlayer)var3.O();
         this.m = 60;
      }
   }



   static {
      a = 137764223568364L;
   }

   static {
      // add code
      chance = new PercentageSetting("Chance", 100);
   }
   static {
      // add code
      minPauseTick = new NumberSetting("Min-pause-tick", 5.0F, 1.0F, 20.0F, 1.0F);
      maxPauseTick = new NumberSetting("Max-pause-tick", 6.0F, 1.0F, 20.0F, 1.0F);
   }
   static {
      // add code
      strategy = new ModeSetting("Strategy", "NORMAL", "CRITICALS");
   }
}
