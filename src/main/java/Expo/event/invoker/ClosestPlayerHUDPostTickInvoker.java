package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PostTickEvent;
import Expo.module.impl.visual_utility.ClosestPlayerHUD;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

public final class ClosestPlayerHUDPostTickInvoker implements EventInvoker {
   final ClosestPlayerHUD z;

   public ClosestPlayerHUDPostTickInvoker(ClosestPlayerHUD var1) {
      this.z = var1;
   }

   public void c(long var1, Object var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      int var4 = (int)((var1 ^ 108541377919226L) >>> 56);
      long var5 = (var1 ^ 108541377919226L) << 8 >>> 8;
      this.z.onPostTick((byte)var4, var5, (PostTickEvent)var3);
   }
}
