package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.Render3DEvent;
import Expo.module.impl.visual_utility.NameTags;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;











public final class NameTagsRender3DInvoker implements EventInvoker {
   final NameTags N;

   public NameTagsRender3DInvoker(NameTags var1) {
      this.N = var1;
   }

   public void c(long var1, Object var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var4 = var1 ^ 85028604330701L;
      this.N.onRender3D((Render3DEvent)var3, var4);
   }
}
