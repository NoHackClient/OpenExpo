package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.SetAnglesEvent;
import Expo.module.impl.combat.KillAura;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;











public final class KillAuraSetAnglesInvoker implements EventInvoker {
   final KillAura K;

   public KillAuraSetAnglesInvoker(KillAura var1) {
      this.K = var1;
   }

   public void c(long var1, Object var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var4 = var1 ^ 108234420882821L;
      this.K.onSetAngles(var4, (SetAnglesEvent)var3);
   }
}
