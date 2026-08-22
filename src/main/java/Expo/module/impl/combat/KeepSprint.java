package Expo.module.impl.combat;

import Expo.module.Category;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.KeepSprintBinder;
import Expo.event.events.AttackEntityEvent;
import Expo.event.events.PreSuperLivingUpdateEvent;
import Expo.event.events.PreTickEvent;
import Expo.event.events.PreUpdateEvent;
import Expo.module.Module;
import Expo.module.impl.movement.Sprint;
import Expo.setting.settings.ModeSetting;
import Expo.setting.settings.PercentageSetting;
import Expo.util.MinecraftRef;
import Expo.util.ScoreboardReader;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;











public class KeepSprint extends Module implements EventSubscriber {
   private static long b;
   private static long[] g;
   private static Minecraft S;
   public static ModeSetting mode;
   private static String[] d;
   private boolean h;
   public static int t;
   public static int a;
   private static String[] c;
   public static PercentageSetting slowdown;

   public void onAttackEntity(long var1, AttackEntityEvent var3) {




      if (mode.R("PREDICTION") && ScoreboardReader.v(0L)) {
         if (!this.h) {
            if (var3.O() instanceof EntityPlayer) {
               switch (t) {
                  case 0:
                     if (S.thePlayer.isSprinting()) {
                        var3.I(21307, 3074332907L);
                        t = 1;
                        a = 0;
                     } else {
                        t = 2;
                        a = 0;
                     }
                     break;
                  case 1:
                     S.thePlayer.setSprinting(false);
                     a = 0;
                     t = 2;
               }
            }

            this.h = true;
         }
      } else {
         this.J((short)0);
      }
   }

   public void onPreTick(long var1, PreTickEvent var3) {
      this.h = false;
   }

   public static void k(long var0) {
      switch (mode.Y()) {
         case "PREDICTION":
            if (S.thePlayer.isSprinting()) {
               if (t == 2) {
                  S.thePlayer.motionX = S.thePlayer.motionX * (1.0 - 0.4 * slowdown.k() / 100.0);
                  S.thePlayer.motionZ = S.thePlayer.motionZ * (1.0 - 0.4 * slowdown.k() / 100.0);
                  if (slowdown.k() == 60) {
                     S.thePlayer.setSprinting(false);
                  }
               } else {
                  S.thePlayer.motionX *= 0.6;
                  S.thePlayer.motionZ *= 0.6;
                  S.thePlayer.setSprinting(false);
               }
            }
            break;
         default:
            if (S.thePlayer.isSprinting()) {
               S.thePlayer.motionX = S.thePlayer.motionX * (1.0 - 0.4 * slowdown.k() / 100.0);
               S.thePlayer.motionZ = S.thePlayer.motionZ * (1.0 - 0.4 * slowdown.k() / 100.0);
               if (slowdown.k() == 60) {
                  S.thePlayer.setSprinting(false);
               }
            }
      }
   }

   private void J(short var1) {
      t = 0;
      a = 0;
      this.h = false;
   }

   public void onPreUpdate(PreUpdateEvent var1, long var2) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {


      if (mode.R("PREDICTION") && ScoreboardReader.v(0L)) {
         if (a > 5) {
            this.J((short)0);
         }

         switch (t) {
            case 1:
               S.thePlayer.setSprinting(false);
               a++;
               break;
            case 2:
               // update new version
               if (!S.thePlayer.isUsingItem() || Sprint.U(0L)) {
                  S.thePlayer.setSprinting(true);
                  a = 0;
                  t = 0;
               } else {
                  a++;
               }
         }
      } else {
         this.J((short)0);
      }
   }

   public String g(long var1) {
      return mode.R("VANILLA") ? slowdown.k() + "%" : mode.Y();
   }

   static {
      b = 51037348104702L;
      t = 0;
      a = 0;
      S = MinecraftRef.c((byte)0, 0L);
   }

   public void A(long var1) {
      int var3 = (int)((var1 ^ 28866368670549L) >>> 48);
      this.J((short)var3);
   }

   public final void x(long var1, EventBus var3) {
      int var4 = (int)((var1 ^ 14013869980610L) >>> 48);
      int var5 = (int)((var1 ^ 14013869980610L) << 16 >>> 48);
      KeepSprintBinder.O(var3, (short)var4, (short)var5, this);
   }



   public void onPreSuperLivingUpdate(PreSuperLivingUpdateEvent var1, long var2) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {


      if (mode.R("PREDICTION") && ScoreboardReader.v(0L)) {
         switch (t) {
            case 1:
               S.thePlayer.setSprinting(false);
               break;
            case 2:
               if (S.thePlayer.isUsingItem()) {
                  if (Sprint.U(0L)) {
                     S.thePlayer.setSprinting(true);
                  }
               } else {
                  S.thePlayer.setSprinting(true);
               }
         }
      } else {
         this.J((short)0);
      }
   }

   private static void a() {
   }



   public KeepSprint(long var1) {
      super(((b ^ (var1)) ^ 18692820696337L));
      // add code
      this.declare("KeepSprint", Category.Combat, "Modify the slowdown while attacking");
      var1 = b ^ var1;
      this.h = false;
   }




   static {
      // add code
      mode = new ModeSetting("Mode", false, "PREDICTION", "VANILLA", "PREDICTION");
      slowdown = new PercentageSetting("Slowdown", 0);
   }
}
