package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.Render3DEvent;
import Expo.module.impl.combat.KillAura;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;











public final class KillAuraRender3DInvoker implements EventInvoker {
   final KillAura I;

   public KillAuraRender3DInvoker(KillAura var1) {
      this.I = var1;
   }

   public void c(long var1, Object var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var4 = var1 ^ 10920297056034L;
      this.I.onRender3D((Render3DEvent)var3, var4);
   }
}
