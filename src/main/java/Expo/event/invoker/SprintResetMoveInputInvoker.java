package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.MoveInputEvent;
import Expo.module.impl.combat.SprintReset;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;











public final class SprintResetMoveInputInvoker implements EventInvoker {
   final SprintReset q;

   public SprintResetMoveInputInvoker(SprintReset var1) {
      this.q = var1;
   }

   public void c(long var1, Object var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      this.q.onMoveInput((MoveInputEvent)var3);
   }
}
