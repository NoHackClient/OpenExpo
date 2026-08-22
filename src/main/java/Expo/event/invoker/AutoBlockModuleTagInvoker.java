package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.ModuleTagEvent;
import Expo.module.impl.combat.AutoBlock;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;











public final class AutoBlockModuleTagInvoker implements EventInvoker {
   final AutoBlock t;

   public AutoBlockModuleTagInvoker(AutoBlock var1) {
      this.t = var1;
   }

   public void c(long var1, Object var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var4 = var1 ^ 83772496216132L;
      this.t.onModuleTag((ModuleTagEvent)var3, var4);
   }
}
