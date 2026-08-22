package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.LivingDeathEvent;
import Expo.module.impl.visual.KillEffect;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;











public final class Zc_2 implements EventInvoker {
   final KillEffect M;

   public Zc_2(KillEffect var1) {
      this.M = var1;
   }

   public void c(long var1, Object var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var4 = var1 ^ 110907836307488L;
      this.M.t((LivingDeathEvent)var3, var4);
   }
}
