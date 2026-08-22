package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PlayerGetNameEvent;
import Expo.module.impl.visual_utility.MegaWallsDetector;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

public final class MegaWallsDetectorPlayerGetNameInvoker implements EventInvoker {
   final MegaWallsDetector G;

   public void c(long var1, Object var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var4 = (var1 ^ 75405666090506L) >>> 16;
      int var6 = (int)((var1 ^ 75405666090506L) << 48 >>> 48);
      this.G.onPlayerGetName((PlayerGetNameEvent)var3, var4, (short)var6);
   }

   public MegaWallsDetectorPlayerGetNameInvoker(MegaWallsDetector var1) {
      this.G = var1;
   }
}
