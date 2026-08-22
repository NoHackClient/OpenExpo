package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PostTickEvent;
import Expo.internal.ChatInputHandler;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

public final class ChatInputHandlerPostTickInvoker implements EventInvoker {
   final ChatInputHandler C;

   public ChatInputHandlerPostTickInvoker(ChatInputHandler var1) {
      this.C = var1;
   }

   public void c(long var1, Object var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var4 = var1 ^ 72737227117278L;
      this.C.onPostTick(var4, (PostTickEvent)var3);
   }
}
