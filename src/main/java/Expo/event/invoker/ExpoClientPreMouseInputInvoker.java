package Expo.event.invoker;

import Expo.ExpoClient;
import Expo.event.EventInvoker;
import Expo.event.events.PreMouseInputEvent;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

public final class ExpoClientPreMouseInputInvoker implements EventInvoker {
   final ExpoClient q;

   public ExpoClientPreMouseInputInvoker(ExpoClient var1) {
      this.q = var1;
   }

   public void c(long var1, Object var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var4 = var1 ^ 6811547271782L;
      this.q.onPreMouseInput(var4, (PreMouseInputEvent)var3);
   }
}
