package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PostTickEvent;
import Expo.module.impl.player.NoHitDelay;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;











public final class NoHitDelayPostTickInvoker implements EventInvoker {
   final NoHitDelay A;

   public void c(long var1, Object var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var4 = var1 ^ 37140636176711L;
      this.A.onPostTick(var4, (PostTickEvent)var3);
   }

   public NoHitDelayPostTickInvoker(NoHitDelay var1) {
      this.A = var1;
   }
}
