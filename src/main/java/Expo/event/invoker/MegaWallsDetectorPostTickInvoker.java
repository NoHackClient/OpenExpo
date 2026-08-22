package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PostTickEvent;
import Expo.module.impl.visual_utility.MegaWallsDetector;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;











public final class MegaWallsDetectorPostTickInvoker implements EventInvoker {
   final MegaWallsDetector A;

   public void c(long var1, Object var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var4 = var1 ^ 85898029764080L;
      this.A.onPostTick(var4, (PostTickEvent)var3);
   }

   public MegaWallsDetectorPostTickInvoker(MegaWallsDetector var1) {
      this.A = var1;
   }
}
