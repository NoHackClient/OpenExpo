package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PreTickEvent;
import Expo.module.impl.movement.NoJumpDelay;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;











public final class NoJumpDelayPreTickInvoker implements EventInvoker {
   final NoJumpDelay V;

   public NoJumpDelayPreTickInvoker(NoJumpDelay var1) {
      this.V = var1;
   }

   public void c(long var1, Object var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var4 = var1 ^ 41087808387787L;
      this.V.onPreTick((PreTickEvent)var3, var4);
   }
}
