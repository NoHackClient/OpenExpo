package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PostTickEvent;
import Expo.ui.screen.MainMenuTheme;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;











public final class MainMenuThemePostTickInvoker implements EventInvoker {
   final MainMenuTheme M;

   public MainMenuThemePostTickInvoker(MainMenuTheme var1) {
      this.M = var1;
   }

   public void c(long var1, Object var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var4 = var1 ^ 54008769069855L;
      this.M.onPostTick(var4, (PostTickEvent)var3);
   }
}
