package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.AttackTargetEntityEvent;
import Expo.module.impl.visual_utility.TargetHUD;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;











public final class TargetHUDAttackTargetEntityInvoker implements EventInvoker {
   final TargetHUD g;

   public TargetHUDAttackTargetEntityInvoker(TargetHUD var1) {
      this.g = var1;
   }

   public void c(long var1, Object var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var4 = var1 ^ 86425735347904L;
      this.g.onAttackTargetEntity(var4, (AttackTargetEntityEvent)var3);
   }
}
