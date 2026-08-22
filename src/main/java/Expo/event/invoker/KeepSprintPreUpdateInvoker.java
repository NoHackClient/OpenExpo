package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PreUpdateEvent;
import Expo.module.impl.combat.KeepSprint;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;











public final class KeepSprintPreUpdateInvoker implements EventInvoker {
   final KeepSprint N;

   public void c(long var1, Object var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var4 = var1 ^ 73788592135339L;
      this.N.onPreUpdate((PreUpdateEvent)var3, var4);
   }

   public KeepSprintPreUpdateInvoker(KeepSprint var1) {
      this.N = var1;
   }
}
