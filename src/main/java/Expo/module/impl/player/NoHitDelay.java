package Expo.module.impl.player;

import Expo.module.Category;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.NoHitDelayBinder;
import Expo.event.events.ClickMouseEvent;
import Expo.event.events.PostClickMouseEvent;
import Expo.event.events.PostTickEvent;
import Expo.internal.accessor.MinecraftAccessor;
import Expo.internal.accessor.PlayerControllerStateAccessor;
import Expo.module.Module;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;











public class NoHitDelay extends Module implements EventSubscriber {
   private static long a;
   private int S;

   public void onPostTick(long var1, PostTickEvent var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {



      if (PlayerControllerStateAccessor.q( f.playerController) || PlayerControllerStateAccessor.s(0L, f.playerController) != 0.0F || f.playerController.getCurrentGameType().isCreative()) {
         this.S = 5;
      } else if (this.S <= 0) {
         PlayerControllerStateAccessor.w((byte)0, 7374982, 11824981, f.playerController, 0);
      } else {
         this.S--;
      }
   }


   public void onPostClickMouse(PostClickMouseEvent var1) {
      MinecraftAccessor.c(f, 0,0L);
   }

   public void onClickMouse(ClickMouseEvent var3) {
      MinecraftAccessor.c(f, 0,0L);
   }


   public void P(long var1) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      this.S = PlayerControllerStateAccessor.W(f.playerController);
   }

   public NoHitDelay(int var1, char var2, char var3) {
      super(((((((long)((var1)) << 32) | (((long)((var2)) << 48) >>> 32)) | (((long)((var3)) << 48) >>> 48)) ^ a) ^ 102076238707453L));
      // add code
      this.declare("NoHitDelay", Category.Player, "Remove 10 ticks hit delay");
      this.S = 0;
   }

   public final void x(long var1, EventBus var3) {
      NoHitDelayBinder.k(var3, this);
   }

   static {
      a = 52192099837058L;
   }
}
