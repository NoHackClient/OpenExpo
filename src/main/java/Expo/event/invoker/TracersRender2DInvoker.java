package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.Render2DEvent;
import Expo.module.impl.visual_utility.Tracers;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;











public final class TracersRender2DInvoker implements EventInvoker {
   final Tracers B;

   public TracersRender2DInvoker(Tracers var1) {
      this.B = var1;
   }

   public void c(long var1, Object var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var4 = var1 ^ 60600320305873L;
      this.B.onRender2D(var4, (Render2DEvent)var3);
   }
}
