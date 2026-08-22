package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.UpdateWalkingPlayerEvent;
import Expo.module.impl.visual.Ambience;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

public final class AmbienceUpdateWalkingPlayerInvoker implements EventInvoker {
   final Ambience S;

   public void c(long var1, Object var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var4 = var1 ^ 21873096945558L;
      this.S.onUpdateWalkingPlayer(var4, (UpdateWalkingPlayerEvent)var3);
   }

   public AmbienceUpdateWalkingPlayerInvoker(Ambience var1) {
      this.S = var1;
   }
}
