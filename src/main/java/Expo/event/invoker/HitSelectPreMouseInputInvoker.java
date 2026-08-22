package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PreMouseInputEvent;
import Expo.module.impl.combat.HitSelect;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

public final class HitSelectPreMouseInputInvoker implements EventInvoker {
   final HitSelect Q;

   public HitSelectPreMouseInputInvoker(HitSelect var1) {
      this.Q = var1;
   }

   public void c(long var1, Object var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var4 = var1 ^ 9079526722190L;
      this.Q.onPreMouseInput(var4, (PreMouseInputEvent)var3);
   }
}
