package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.SetKeyBindStateEvent;
import Expo.module.impl.visual.TabGUI;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;











public final class TabGUISetKeyBindStateInvoker implements EventInvoker {
   final TabGUI S;

   public void c(long var1, Object var3) throws UnsupportedEncodingException, Throwable, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var4 = var1 ^ 63483287372039L;
      this.S.onSetKeyBindState((SetKeyBindStateEvent)var3, var4);
   }

   public TabGUISetKeyBindStateInvoker(TabGUI var1) {
      this.S = var1;
   }
}
