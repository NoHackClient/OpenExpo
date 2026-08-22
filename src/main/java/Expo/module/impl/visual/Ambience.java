package Expo.module.impl.visual;

import Expo.module.Category;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.AmbienceBinder;
import Expo.event.events.ReceivePacketEvent;
import Expo.event.events.Render2DEvent;
import Expo.event.events.UpdateWalkingPlayerEvent;
import Expo.module.Module;
import Expo.setting.settings.ModeSetting;
import Expo.setting.settings.NumberSetting;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import net.minecraft.network.play.server.S03PacketTimeUpdate;
import net.minecraft.network.play.server.S2BPacketChangeGameState;











public class Ambience extends Module implements EventSubscriber {
   public static NumberSetting time;
   public static ModeSetting mode;
   public static NumberSetting speed;
   private static long a;

   public void onUpdateWalkingPlayer(long var1, UpdateWalkingPlayerEvent var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {

      if (f.thePlayer.ticksExisted % 20 == 0) {
         switch (mode.Y()) {
            case "CLEAR":
               this.D((byte)139);
               break;
            case "RAIN":
               f.theWorld.setRainStrength(1.0F);
               f.theWorld.getWorldInfo().setCleanWeatherTime(0);
               f.theWorld.getWorldInfo().setRainTime(2147483647);
               f.theWorld.getWorldInfo().setThunderTime(2147483647);
               f.theWorld.getWorldInfo().setRaining(true);
               f.theWorld.getWorldInfo().setThundering(false);
         }
      }
   }

   static {
      a = 81694087725472L;
   }


   public void A(long var1) {
      int var5 = (int)((var1 ^ 125021944116750L) << 56 >>> 56);
      this.D((byte)var5);
   }

   public Ambience(long var1) {
      super(((a ^ (var1)) ^ 34096516885598L));
      // add code
      this.declare("Ambience", Category.Visual, "Change the environment rendering");
      var1 = a ^ var1;
   }

   public void onRender2D(Render2DEvent var1) {
      f.theWorld.setWorldTime((long)(time.L() + (float)System.currentTimeMillis() * speed.L()));
   }

   public String g(long var1) {
      if (mode.R("NONE")) {
         return time.L() % 24000.0F >= 12000.0F ? "NIGHT" : "DAY";
      } else {
         return mode.Y();
      }
   }


   public void onReceivePacket(ReceivePacketEvent var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {


      if (var3.d instanceof S03PacketTimeUpdate) {
         var3.I(21307, 3074332907L);
      } else if (var3.d instanceof S2BPacketChangeGameState && !mode.R("NONE")) {
         S2BPacketChangeGameState var7 = (S2BPacketChangeGameState)var3.d;
         if (var7.getGameState() == 1 || var7.getGameState() == 2) {
            var3.I(21307, 3074332907L);
         }
      }
   }

   public final void x(long var1, EventBus var3) {
      AmbienceBinder.K(var3, this);
   }

   private void D(byte var3) {
      f.theWorld.setRainStrength(0.0F);
      f.theWorld.getWorldInfo().setCleanWeatherTime(2147483647);
      f.theWorld.getWorldInfo().setRainTime(0);
      f.theWorld.getWorldInfo().setThunderTime(0);
      f.theWorld.getWorldInfo().setRaining(false);
      f.theWorld.getWorldInfo().setThundering(false);
   }


   static {
      // add code
      time = new NumberSetting("Time", 0.0F, 0.0F, 24000.0F, 10.0F);
      speed = new NumberSetting("Speed", 0.0F, 0.0F, 100.0F, 1.0F);
   }
   static {
      // add code
      mode = new ModeSetting("Mode", "NONE", "RAIN", "CLEAR");
   }
}
