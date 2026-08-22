package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.GetDisplayNameEvent;
import Expo.internal.CheaterDetector;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;











public final class CheaterDetectorGetDisplayNameInvoker implements EventInvoker {
   final CheaterDetector s;

   public CheaterDetectorGetDisplayNameInvoker(CheaterDetector var1) {
      this.s = var1;
   }

   public void c(long var1, Object var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var4 = var1 ^ 74771126396659L;
      this.s.onGetDisplayName((GetDisplayNameEvent)var3, var4);
   }
}
