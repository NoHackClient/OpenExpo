package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.Render3DEvent;
import Expo.module.impl.visual_utility.BlocksESP;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;











public final class BlocksESPRender3DInvoker implements EventInvoker {
   final BlocksESP L;

   public BlocksESPRender3DInvoker(BlocksESP var1) {
      this.L = var1;
   }

   public void c(long var1, Object var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var4 = var1 ^ 92358563786206L;
      this.L.onRender3D(var4, (Render3DEvent)var3);
   }
}
