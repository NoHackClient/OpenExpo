package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.HandleChatEvent;
import Expo.module.impl.visual_utility.FKCounter;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;











public final class FKCounterHandleChatInvoker implements EventInvoker {
   final FKCounter R;

   public FKCounterHandleChatInvoker(FKCounter var1) {
      this.R = var1;
   }

   public void c(long var1, Object var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var4 = var1 ^ 113707324613221L;
      this.R.onHandleChat((HandleChatEvent)var3, var4);
   }
}
