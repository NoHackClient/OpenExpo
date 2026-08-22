package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.ReceivePacketEvent;
import Expo.module.impl.visual_utility.LeapModeHUD;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;











public final class LeapModeHUDReceivePacketInvoker implements EventInvoker {
   final LeapModeHUD l;

   public void c(long var1, Object var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var4 = var1 ^ 5035096831527L;
      this.l.onReceivePacket(var4, (ReceivePacketEvent)var3);
   }

   public LeapModeHUDReceivePacketInvoker(LeapModeHUD var1) {
      this.l = var1;
   }
}
