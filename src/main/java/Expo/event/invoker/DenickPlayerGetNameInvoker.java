package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PlayerGetNameEvent;
import Expo.module.impl.misc.Denick;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;











public final class DenickPlayerGetNameInvoker implements EventInvoker {
   final Denick N;

   public void c(long var1, Object var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var4 = var1 ^ 80601745491694L;
      this.N.onPlayerGetName(var4, (PlayerGetNameEvent)var3);
   }

   public DenickPlayerGetNameInvoker(Denick var1) {
      this.N = var1;
   }
}
