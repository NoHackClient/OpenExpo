package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PreUpdateEvent;
import Expo.module.impl.movement.Sprint;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

public final class SprintPreUpdateInvoker implements EventInvoker {
   final Sprint h;

   public SprintPreUpdateInvoker(Sprint var1) {
      this.h = var1;
   }

   public void c(long var1, Object var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      int var4 = (int)((var1 ^ 88265907375448L) >>> 48);
      int var5 = (int)((var1 ^ 88265907375448L) << 16 >>> 48);
      int var6 = (int)((var1 ^ 88265907375448L) << 32 >>> 32);
      this.h.onPreUpdate((short)var4, (PreUpdateEvent)var3, (char)var5, var6);
   }
}
