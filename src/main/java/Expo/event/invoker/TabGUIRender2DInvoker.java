package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.Render2DEvent;
import Expo.module.impl.visual.TabGUI;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;











public final class TabGUIRender2DInvoker implements EventInvoker {
   final TabGUI I;

   public void c(long var1, Object var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var4 = var1 ^ 6387986500946L;
      this.I.onRender2D(var4, (Render2DEvent)var3);
   }

   public TabGUIRender2DInvoker(TabGUI var1) {
      this.I = var1;
   }
}
