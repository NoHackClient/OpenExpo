package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PreUpdateEvent;
import Expo.module.impl.player.ChestStealer;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;











public final class ChestStealerPreUpdateInvoker implements EventInvoker {
   final ChestStealer e;

   public ChestStealerPreUpdateInvoker(ChestStealer var1) {
      this.e = var1;
   }

   public void c(long var1, Object var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var4 = var1 ^ 49055270495416L;
      this.e.onPreUpdate(var4, (PreUpdateEvent)var3);
   }
}
