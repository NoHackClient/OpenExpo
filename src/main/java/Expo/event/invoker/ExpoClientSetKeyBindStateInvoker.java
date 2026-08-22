package Expo.event.invoker;

import Expo.ExpoClient;
import Expo.event.EventInvoker;
import Expo.event.events.SetKeyBindStateEvent;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

public final class ExpoClientSetKeyBindStateInvoker implements EventInvoker {
   final ExpoClient W;

   public void c(long var1, Object var3) throws UnsupportedEncodingException, Throwable, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var4 = var1 ^ 92820027371704L;
      this.W.onSetKeyBindState((SetKeyBindStateEvent)var3, var4);
   }

   public ExpoClientSetKeyBindStateInvoker(ExpoClient var1) {
      this.W = var1;
   }
}
