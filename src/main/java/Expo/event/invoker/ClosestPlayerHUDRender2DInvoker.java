package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.Render2DEvent;
import Expo.module.impl.visual_utility.ClosestPlayerHUD;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

public final class ClosestPlayerHUDRender2DInvoker implements EventInvoker {
   final ClosestPlayerHUD B;

   public ClosestPlayerHUDRender2DInvoker(ClosestPlayerHUD var1) {
      this.B = var1;
   }

   public void c(long var1, Object var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var4 = var1 ^ 76065751896766L;
      this.B.onRender2D((Render2DEvent)var3, var4);
   }
}
