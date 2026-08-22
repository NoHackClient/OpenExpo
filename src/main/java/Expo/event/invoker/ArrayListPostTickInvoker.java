package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PostTickEvent;
import Expo.module.impl.visual.ArrayList;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;











public final class ArrayListPostTickInvoker implements EventInvoker {
   final ArrayList v;

   public ArrayListPostTickInvoker(ArrayList var1) {
      this.v = var1;
   }

   public void c(long var1, Object var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      int var4 = (int)((var1 ^ 42487487034519L) >>> 32);
      long var5 = (var1 ^ 42487487034519L) << 32 >>> 32;
      this.v.onPostTick((PostTickEvent)var3, var4, var5);
   }
}
