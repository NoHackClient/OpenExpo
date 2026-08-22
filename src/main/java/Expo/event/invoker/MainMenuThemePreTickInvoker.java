package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PreTickEvent;
import Expo.ui.screen.MainMenuTheme;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;











public final class MainMenuThemePreTickInvoker implements EventInvoker {
   final MainMenuTheme g;

   public MainMenuThemePreTickInvoker(MainMenuTheme var1) {
      this.g = var1;
   }

   public void c(long var1, Object var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var4 = var1 ^ 9138435898210L;
      this.g.onPreTick(var4, (PreTickEvent)var3);
   }
}
