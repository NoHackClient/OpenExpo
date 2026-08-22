package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PostTickEvent;
import Expo.module.impl.world.SpeedMine;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;











public final class SpeedMinePostTickInvoker implements EventInvoker {
   final SpeedMine F;

   public void c(long var1, Object var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var4 = var1 ^ 41276351198749L;
      this.F.onPostTick((PostTickEvent)var3, var4);
   }

   public SpeedMinePostTickInvoker(SpeedMine var1) {
      this.F = var1;
   }
}
