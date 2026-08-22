package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.Render2DEvent;
import Expo.module.impl.visual.KeyStrokes;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

public final class KeyStrokesRender2DInvoker implements EventInvoker {
   final KeyStrokes A;

   public void c(long var1, Object var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var4 = var1 ^ 78937369838641L;
      this.A.onRender2D(var4, (Render2DEvent)var3);
   }

   public KeyStrokesRender2DInvoker(KeyStrokes var1) {
      this.A = var1;
   }
}
