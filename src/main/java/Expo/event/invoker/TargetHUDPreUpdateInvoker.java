package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PreUpdateEvent;
import Expo.module.impl.visual_utility.TargetHUD;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;











public final class TargetHUDPreUpdateInvoker implements EventInvoker {
   final TargetHUD x;

   public TargetHUDPreUpdateInvoker(TargetHUD var1) {
      this.x = var1;
   }

   public void c(long var1, Object var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var4 = var1 ^ 42580339441872L;
      this.x.onPreUpdate((PreUpdateEvent)var3, var4);
   }
}
