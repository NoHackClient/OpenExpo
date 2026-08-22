package Expo.event.invoker;

import Expo.ExpoClient;
import Expo.event.EventInvoker;
import Expo.event.events.ReceivePacketEvent;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

public final class ExpoClientReceivePacketInvoker implements EventInvoker {
   final ExpoClient q;

   public void c(long var1, Object var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var4 = var1 ^ 122745969475139L;
      this.q.onReceivePacket((ReceivePacketEvent)var3, var4);
   }

   public ExpoClientReceivePacketInvoker(ExpoClient var1) {
      this.q = var1;
   }
}
