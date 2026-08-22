package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.Render3DEvent;
import Expo.module.impl.visual_utility.ESP;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

public final class ESPRender3DInvoker implements EventInvoker {
   final ESP j;

   public void c(long var1, Object var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      int var4 = (int)((var1 ^ 140081732458433L) >>> 48);
      int var5 = (int)((var1 ^ 140081732458433L) << 16 >>> 32);
      int var6 = (int)((var1 ^ 140081732458433L) << 48 >>> 48);
      this.j.onRender3D((char)var4, var5, (Render3DEvent)var3, (short)var6);
   }

   public ESPRender3DInvoker(ESP var1) {
      this.j = var1;
   }
}
