package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.Render2DEvent;
import Expo.module.impl.player.Blink;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;











public final class BlinkRender2DInvoker implements EventInvoker {
   final Blink h;

   public BlinkRender2DInvoker(Blink var1) {
      this.h = var1;
   }

   public void c(long var1, Object var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var4 = var1 ^ 93748278930992L;
      this.h.onRender2D(var4, (Render2DEvent)var3);
   }
}
