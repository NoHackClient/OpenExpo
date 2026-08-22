package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PreUpdateEvent;
import Expo.module.impl.player.FastCraft;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

public final class FastCraftPreUpdateInvoker implements EventInvoker {
   final FastCraft m;

   public void c(long var1, Object var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var4 = var1 ^ 85560131654300L;
      this.m.onPreUpdate(var4, (PreUpdateEvent)var3);
   }

   public FastCraftPreUpdateInvoker(FastCraft var1) {
      this.m = var1;
   }
}
