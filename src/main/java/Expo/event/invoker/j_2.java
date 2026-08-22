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











public final class j_2 implements EventInvoker {
   final ExpoClient C;

   public void c(long var1, Object var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var4 = var1 ^ 111600932496670L;
      this.C.d(var4, (PreUpdateEvent)var3);
   }

   public j_2(ExpoClient var1) {
      this.C = var1;
   }
}
