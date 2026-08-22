package Expo.internal.accessor;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.util.BlockPos;

public final class PlayerControllerStateAccessor {
   private static TypedValueStore D;
   private static TypedValueStore h;
   private static TypedValueStore d;
   private static TypedValueStore O;
   private static TypedValueStore H;

   public static void W(long var0, PlayerControllerMP var2, float var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      D.C(var2, var3);
   }

   static {
      O = FieldAccessors.X(PlayerControllerMP.class, "blockHitDelay", "blockHitDelay");
      H = FieldAccessors.X(PlayerControllerMP.class, "curBlockDamageMP", "curBlockDamageMP");
      D = FieldAccessors.X(PlayerControllerMP.class, "stepSoundTickCounter", "stepSoundTickCounter");
      d = FieldAccessors.X(PlayerControllerMP.class, "currentBlock", "currentBlock");
      h = FieldAccessors.X(PlayerControllerMP.class, "isHittingBlock", "isHittingBlock");
   }

   public static float s(long var0, PlayerControllerMP var2) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      return H.V(var2);
   }

   public static float v(PlayerControllerMP var0, long var1) {
      return D.V(var0);
   }

   public static int W(PlayerControllerMP var0) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      return O.m(var0);
   }

   public static boolean q( PlayerControllerMP var2) {
      return h.n(var2);
   }

   public static void w(byte var0, int var1, int var2, PlayerControllerMP var3, int var4) {
      O.T(var3, var4);
   }

   public static void e(long var0, PlayerControllerMP var2, float var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      H.C(var2, var3);
   }

   public static void Q(long var0, PlayerControllerMP var2, boolean var3) {
      h.O(var2, var3);
   }

   public static BlockPos Z(PlayerControllerMP var0) {
      return (BlockPos)d.v(var0);
   }
}
