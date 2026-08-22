package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PickUpItemEvent;
import Expo.module.impl.player.InvManager;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

public final class InvManagerPickUpItemInvoker implements EventInvoker {
   final InvManager q;

   public InvManagerPickUpItemInvoker(InvManager var1) {
      this.q = var1;
   }

   public void c(long var1, Object var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var4 = var1 ^ 104576693037726L;
      this.q.onPickUpItem((PickUpItemEvent)var3, var4);
   }
}
