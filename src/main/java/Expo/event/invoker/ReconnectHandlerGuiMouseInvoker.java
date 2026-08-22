package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.GuiMouseEvent;
import Expo.ui.screen.ReconnectHandler;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

public final class ReconnectHandlerGuiMouseInvoker implements EventInvoker {
   final ReconnectHandler h;

   public ReconnectHandlerGuiMouseInvoker(ReconnectHandler var1) {
      this.h = var1;
   }

   public void c(long var1, Object var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var4 = var1 ^ 42761512323556L;
      this.h.onGuiMouse((GuiMouseEvent)var3, var4);
   }
}
