package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.Render3DEvent;
import Expo.module.impl.visual_utility.ItemESP;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;











public final class ItemESPRender3DInvoker implements EventInvoker {
   final ItemESP U;

   public void c(long var1, Object var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var4 = var1 ^ 86641135826423L;
      this.U.onRender3D((Render3DEvent)var3, var4);
   }

   public ItemESPRender3DInvoker(ItemESP var1) {
      this.U = var1;
   }
}
