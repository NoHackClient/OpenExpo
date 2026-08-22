package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.Render2DEvent;
import Expo.module.impl.configuration.Notifications;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;











public final class NotificationsRender2DInvoker implements EventInvoker {
   final Notifications d;

   public void c(long var1, Object var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      int var4 = (int)((var1 ^ 96445500104799L) >>> 32);
      int var5 = (int)((var1 ^ 96445500104799L) << 32 >>> 48);
      int var6 = (int)((var1 ^ 96445500104799L) << 48 >>> 48);
      this.d.onRender2D(var4, (Render2DEvent)var3, var5, (char)var6);
   }

   public NotificationsRender2DInvoker(Notifications var1) {
      this.d = var1;
   }
}
