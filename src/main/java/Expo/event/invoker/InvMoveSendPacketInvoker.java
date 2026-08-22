package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.SendPacketEvent;
import Expo.module.impl.movement.InvMove;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;











public final class InvMoveSendPacketInvoker implements EventInvoker {
   final InvMove t;

   public void c(long var1, Object var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      this.t.onSendPacket((SendPacketEvent)var3);
   }

   public InvMoveSendPacketInvoker(InvMove var1) {
      this.t = var1;
   }
}
