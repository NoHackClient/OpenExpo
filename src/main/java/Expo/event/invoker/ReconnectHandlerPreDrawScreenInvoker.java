package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PreDrawScreenEvent;
import Expo.ui.screen.ReconnectHandler;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

public final class ReconnectHandlerPreDrawScreenInvoker implements EventInvoker {
   final ReconnectHandler K;

   public void c(long var1, Object var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var4 = var1 ^ 22012824357404L;
      this.K.onPreDrawScreen((PreDrawScreenEvent)var3, var4);
   }

   public ReconnectHandlerPreDrawScreenInvoker(ReconnectHandler var1) {
      this.K = var1;
   }
}
