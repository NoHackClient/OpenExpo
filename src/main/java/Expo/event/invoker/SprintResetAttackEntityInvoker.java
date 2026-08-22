package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.AttackEntityEvent;
import Expo.module.impl.combat.SprintReset;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

public final class SprintResetAttackEntityInvoker implements EventInvoker {
   final SprintReset s;

   public void c(long var1, Object var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      this.s.onAttackEntity((AttackEntityEvent)var3);
   }

   public SprintResetAttackEntityInvoker(SprintReset var1) {
      this.s = var1;
   }
}
