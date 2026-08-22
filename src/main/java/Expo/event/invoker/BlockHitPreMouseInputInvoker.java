package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PreMouseInputEvent;
import Expo.module.impl.combat.BlockHit;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;











public final class BlockHitPreMouseInputInvoker implements EventInvoker {
   final BlockHit l;

   public void c(long var1, Object var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var4 = (var1 ^ 101328320593455L) >>> 16;
      int var6 = (int)((var1 ^ 101328320593455L) << 48 >>> 48);
      this.l.onPreMouseInput((PreMouseInputEvent)var3, var4, (short)var6);
   }

   public BlockHitPreMouseInputInvoker(BlockHit var1) {
      this.l = var1;
   }
}
