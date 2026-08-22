package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PostTickEvent;
import Expo.module.impl.visual.BreakProgress;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

public final class BreakProgressPostTickInvoker implements EventInvoker {
   final BreakProgress A;

   public BreakProgressPostTickInvoker(BreakProgress var1) {
      this.A = var1;
   }

   public void c(long var1, Object var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      int var4 = (int)((var1 ^ 67801519292952L) >>> 32);
      int var5 = (int)((var1 ^ 67801519292952L) << 32 >>> 48);
      int var6 = (int)((var1 ^ 67801519292952L) << 48 >>> 48);
      this.A.onPostTick(var4, (PostTickEvent)var3, (short)var5, (short)var6);
   }
}
