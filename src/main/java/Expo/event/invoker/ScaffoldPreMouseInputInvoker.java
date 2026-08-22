package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PreMouseInputEvent;
import Expo.module.impl.world.Scaffold;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;











public final class ScaffoldPreMouseInputInvoker implements EventInvoker {
   final Scaffold R;

   public ScaffoldPreMouseInputInvoker(Scaffold var1) {
      this.R = var1;
   }

   public void c(long var1, Object var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var4 = var1 ^ 30134361293727L;
      this.R.onPreMouseInput(var4, (PreMouseInputEvent)var3);
   }
}
