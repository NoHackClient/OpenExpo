package Expo.internal.accessor;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import net.minecraft.client.renderer.entity.RenderManager;











public final class RenderManagerAccessor {
   private static TypedValueStore F;
   private static TypedValueStore t;
   private static TypedValueStore O;

   public static double W(long var0, RenderManager var2) {
      return t.w(var2);
   }

   public static double y(int var0, RenderManager var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      return F.w(var3);
   }



   public static double k(long var0, RenderManager var2) {
      return O.w(var2);
   }

   static {
      O = FieldAccessors.X(RenderManager.class, "renderPosX", "renderPosX");
      F = FieldAccessors.X(RenderManager.class, "renderPosY", "renderPosY");
      t = FieldAccessors.X(RenderManager.class, "renderPosZ", "renderPosZ");
   }

}
