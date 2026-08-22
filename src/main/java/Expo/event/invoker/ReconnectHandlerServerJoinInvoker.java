package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.ServerJoinEvent;
import Expo.ui.screen.ReconnectHandler;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;











public final class ReconnectHandlerServerJoinInvoker implements EventInvoker {
   final ReconnectHandler B;

   public ReconnectHandlerServerJoinInvoker(ReconnectHandler var1) {
      this.B = var1;
   }

   public void c(long var1, Object var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var4 = var1 ^ 16788697753282L;
      this.B.onServerJoin(var4, (ServerJoinEvent)var3);
   }
}
