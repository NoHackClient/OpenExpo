package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PreMouseInputEvent;
import Expo.module.impl.combat.AutoBlock;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;











public final class AutoBlockPreMouseInputInvoker implements EventInvoker {
   final AutoBlock c;

   public AutoBlockPreMouseInputInvoker(AutoBlock var1) {
      this.c = var1;
   }

   public void c(long var1, Object var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var4 = var1 ^ 62438110951182L;
      this.c.onPreMouseInput((PreMouseInputEvent)var3, var4);
   }
}
