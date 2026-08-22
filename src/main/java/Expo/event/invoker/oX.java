package Expo.event.invoker;

import Expo.ExpoClient;
import Expo.event.EventInvoker;
import Expo.event.events.PreUpdateEvent;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;











public final class oX implements EventInvoker {
   final ExpoClient h;

   public void c(long var1, Object var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var4 = var1 ^ 46496743844447L;
      this.h.onPreUpdate(var4, (PreUpdateEvent)var3);
   }

   public oX(ExpoClient var1) {
      this.h = var1;
   }
}
