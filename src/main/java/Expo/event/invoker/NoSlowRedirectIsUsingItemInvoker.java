package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.RedirectIsUsingItemEvent;
import Expo.module.impl.movement.NoSlow;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;











public final class NoSlowRedirectIsUsingItemInvoker implements EventInvoker {
   final NoSlow w;

   public NoSlowRedirectIsUsingItemInvoker(NoSlow var1) {
      this.w = var1;
   }

   public void c(long var1, Object var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      int var4 = (int)((var1 ^ 9053933011925L) >>> 56);
      int var5 = (int)((var1 ^ 9053933011925L) << 8 >>> 32);
      int var6 = (int)((var1 ^ 9053933011925L) << 40 >>> 40);
      this.w.onRedirectIsUsingItem((byte)var4, var5, var6, (RedirectIsUsingItemEvent)var3);
   }
}
