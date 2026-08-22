package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.Render2DEvent;
import Expo.module.impl.world.Scaffold;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;











public final class ScaffoldRender2DInvoker implements EventInvoker {
   final Scaffold b;

   public ScaffoldRender2DInvoker(Scaffold var1) {
      this.b = var1;
   }

   public void c(long var1, Object var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var4 = var1 ^ 29056566202816L;
      this.b.onRender2D(var4, (Render2DEvent)var3);
   }
}
