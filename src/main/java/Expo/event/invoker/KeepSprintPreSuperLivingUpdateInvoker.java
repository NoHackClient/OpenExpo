package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PreSuperLivingUpdateEvent;
import Expo.module.impl.combat.KeepSprint;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

public final class KeepSprintPreSuperLivingUpdateInvoker implements EventInvoker {
   final KeepSprint m;

   public KeepSprintPreSuperLivingUpdateInvoker(KeepSprint var1) {
      this.m = var1;
   }

   public void c(long var1, Object var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var4 = var1 ^ 55455920730301L;
      this.m.onPreSuperLivingUpdate((PreSuperLivingUpdateEvent)var3, var4);
   }
}
