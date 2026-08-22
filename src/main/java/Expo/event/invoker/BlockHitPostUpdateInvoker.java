package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PostUpdateEvent;
import Expo.module.impl.combat.BlockHit;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;











public final class BlockHitPostUpdateInvoker implements EventInvoker {
   final BlockHit W;

   public BlockHitPostUpdateInvoker(BlockHit var1) {
      this.W = var1;
   }

   public void c(long var1, Object var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var4 = var1 ^ 111112209769853L;
      this.W.onPostUpdate(var4, (PostUpdateEvent)var3);
   }
}
