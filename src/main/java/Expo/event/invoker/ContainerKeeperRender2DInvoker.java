package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.Render2DEvent;
import Expo.module.impl.misc.ContainerKeeper;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;











public final class ContainerKeeperRender2DInvoker implements EventInvoker {
   final ContainerKeeper D;

   public ContainerKeeperRender2DInvoker(ContainerKeeper var1) {
      this.D = var1;
   }

   public void c(long var1, Object var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var4 = (var1 ^ 44130090855939L) >>> 32;
      int var6 = (int)((var1 ^ 44130090855939L) << 32 >>> 32);
      this.D.onRender2D(var4, var6, (Render2DEvent)var3);
   }
}
