package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PreTickEvent;
import Expo.internal.MiningEngine;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

public final class MiningEnginePreTickInvoker implements EventInvoker {
   final MiningEngine A;

   public void c(long var1, Object var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var4 = var1 ^ 135481606068007L;
      this.A.onPreTick((PreTickEvent)var3, var4);
   }

   public MiningEnginePreTickInvoker(MiningEngine var1) {
      this.A = var1;
   }
}
