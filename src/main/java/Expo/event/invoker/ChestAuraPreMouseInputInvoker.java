package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PreMouseInputEvent;
import Expo.module.impl.player.ChestAura;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

public final class ChestAuraPreMouseInputInvoker implements EventInvoker {
   final ChestAura V;

   public void c(long var1, Object var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var4 = var1 ^ 105131832675393L;
      this.V.onPreMouseInput(var4, (PreMouseInputEvent)var3);
   }

   public ChestAuraPreMouseInputInvoker(ChestAura var1) {
      this.V = var1;
   }
}
