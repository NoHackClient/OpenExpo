package Expo.module.impl.world;

import Expo.module.Category;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.SpeedMineBinder;
import Expo.event.events.PostTickEvent;
import Expo.internal.accessor.PlayerControllerStateAccessor;
import Expo.module.Module;
import Expo.setting.settings.ModeSetting;
import Expo.setting.settings.NumberSetting;
import Expo.setting.settings.PercentageSetting;
import Expo.util.BlockUtil;
import Expo.util.KeyBindUtil;
import Expo.util.MathUtil;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import net.minecraft.util.BlockPos;

public class SpeedMine extends Module implements EventSubscriber {
   public static PercentageSetting delayChance;
   private static long a;
   private BlockPos b;
   private float J;
   public static ModeSetting mode;
   public static NumberSetting delay;
   public static PercentageSetting speedChance;
   public static PercentageSetting increaseSpeed;

   public final void x(long var1, EventBus var3) {
      SpeedMineBinder.H(var3, this);
   }

   static {
      a = 40047299343839L;
   }

   public SpeedMine(short var1, long var2) {
      super((((((long)((var1)) << 48) | 0L) ^ a) ^ 69186206453329L));
      this.declare("SpeedMine", Category.World, "Increase your mining speed");
      this.b = null;
   }

   public String g(long var1) {
      if (increaseSpeed.k() != 0) {
         return increaseSpeed.k() + 100 + "%";
      } else {
         return delay.L() != 5.0F ? String.valueOf((int)delay.L()) : "";
      }
   }

   public void onPostTick(PostTickEvent var1, long var2) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      BlockPos var19 = PlayerControllerStateAccessor.Z(f.playerController);
      if (f.inGameHasFocus) {
         int var20 = (int)delay.L();
         if (var20 < 5.0 && (delayChance.k() == 100 || MathUtil.Q(delayChance.k(),0L) && var19 != this.b)) {
            if (var20 == 0.0) {
               PlayerControllerStateAccessor.w((byte)0, 7374982, 11824981, f.playerController, 0);
            } else if (PlayerControllerStateAccessor.W(f.playerController) > var20) {
               PlayerControllerStateAccessor.w((byte)0, 7374982, 11824981, f.playerController, var20);
            }
         }

         double var21 = 1.0 + increaseSpeed.k() / 100.0;
         if (var21 > 1.0) {
            if (!f.thePlayer.capabilities.isCreativeMode && KeyBindUtil.V(f.gameSettings.keyBindAttack.getKeyCode(), 64165991731362L)) {
               float var23 = PlayerControllerStateAccessor.s(0L, f.playerController);
               switch (mode.Y()) {
                  case "PRE":
                     if (MathUtil.Q(speedChance.k(),0L)) {
                        float var26 = (float)(1.0 - 1.0 / var21);
                        if (var23 > 0.0F && var23 < var26) {
                           PlayerControllerStateAccessor.e(0L, f.playerController, var26);
                        }
                     }
                     break;
                  case "POST":
                     if (MathUtil.Q(speedChance.k(),0L)) {
                        double var27 = 1.0 / var21;
                        if (var23 < 1.0F && var23 >= var27) {
                           PlayerControllerStateAccessor.e(0L, f.playerController, 1.0F);
                        }
                     }
                     break;
                  case "INCREASE":
                     float var29 = -1.0F;
                     if (MathUtil.Q(speedChance.k(),0L) && var23 < 1.0F) {
                        if (f.objectMouseOver != null && var23 > this.J) {
                           var29 = (float)(
                              this.J
                                 + BlockUtil.g(
                                       f.theWorld.getBlockState(f.objectMouseOver.getBlockPos()).getBlock(),
                                       f.thePlayer.inventory.getStackInSlot(f.thePlayer.inventory.currentItem),
                                       false,
                                       false
                                    )
                                    * (var21 - 0.2152857 * (var21 - 1.0))
                           );
                        }

                        if (var29 != -1.0F && var23 > 0.0F) {
                           PlayerControllerStateAccessor.e(0L, f.playerController, var29);
                        }
                     }

                     this.J = var23;
               }
            } else if (mode.R("INCREASE")) {
               this.J = 0.0F;
            }
         }

         this.b = var19;
      }
   }
   static {
      delayChance = new PercentageSetting("Delay-chance", 100);
      increaseSpeed = new PercentageSetting("Increase-speed", 10);
      speedChance = new PercentageSetting("Speed-chance", 100);
   }
   static {
      delay = new NumberSetting("Delay", 0.0F, 0.0F, 5.0F, 1.0F);
   }
   static {
      mode = new ModeSetting("Mode", "POST", "PRE", "INCREASE");
   }
}
