package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PostTickEvent;
import Expo.internal.CheaterDetector;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;











public final class CheaterDetectorPostTickInvoker implements EventInvoker {
   final CheaterDetector N;

   public CheaterDetectorPostTickInvoker(CheaterDetector var1) {
      this.N = var1;
   }

   public void c(long var1, Object var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var4 = var1 ^ 64526463733234L;
      this.N.onPostTick(var4, (PostTickEvent)var3);
   }
}
