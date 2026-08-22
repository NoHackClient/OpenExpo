package Expo.internal.accessor;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import net.minecraft.entity.EntityLivingBase;











public final class EntityLivingBaseStateAccessor {
   private static TypedValueStore P;



   public static void x(int var0, EntityLivingBase var2, int var3) {
      P.T(var2, var3);
   }

   static {
      P = FieldAccessors.X(EntityLivingBase.class, "jumpTicks", "jumpTicks");
   }

   public static int C( EntityLivingBase var2) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      return P.m(var2);
   }

}
