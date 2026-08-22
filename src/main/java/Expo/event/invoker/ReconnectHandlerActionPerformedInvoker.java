package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.ActionPerformedEvent;
import Expo.ui.screen.ReconnectHandler;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;











public final class ReconnectHandlerActionPerformedInvoker implements EventInvoker {
   final ReconnectHandler P;

   public void c(long var1, Object var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var4 = var1 ^ 119921739738119L;
      this.P.onActionPerformed((ActionPerformedEvent)var3, var4);
   }

   public ReconnectHandlerActionPerformedInvoker(ReconnectHandler var1) {
      this.P = var1;
   }
}
