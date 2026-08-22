package Expo.internal.accessor;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;











public final class EntityPlayerSPAccessor {
   private static TypedValueStore Q;
   private static TypedValueStore j;
   private static TypedValueStore g;
   private static TypedValueStore b;
   private static TypedValueStore r;
   private static TypedValueStore V;
   private static TypedValueStore d;
   private static TypedValueStore F;
   private static TypedValueStore H;

   public static void S( EntityPlayerSP var2, float var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      r.C(var2, var3);
   }

   public static boolean f( EntityPlayerSP var1) {
      return d.n(var1);
   }

   public static double C(EntityPlayerSP var0) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      return Q.w(var0);
   }

   public static void q(EntityPlayerSP var0, float var1) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      j.C(var0, var1);
   }

   public static void s(char var0, EntityPlayerSP var1, double var2, int var4, char var5) {
      Q.f( var1, var2);
   }

   public static void K(EntityPlayerSP var0, int var3) {
      H.T(var0, var3);
   }

   static {
      g = FieldAccessors.X(EntityPlayerSP.class, "lastReportedPosX", "lastReportedPosX");
      V = FieldAccessors.X(EntityPlayerSP.class, "lastReportedPosY", "lastReportedPosY");
      Q = FieldAccessors.X(EntityPlayerSP.class, "lastReportedPosZ", "lastReportedPosZ");
      j = FieldAccessors.X(EntityPlayerSP.class, "lastReportedYaw", "lastReportedYaw");
      r = FieldAccessors.X(EntityPlayerSP.class, "lastReportedPitch", "lastReportedPitch");
      H = FieldAccessors.X(EntityPlayerSP.class, "positionUpdateTicks", "positionUpdateTicks");
      F = FieldAccessors.X(EntityPlayerSP.class, "serverSprintState", "serverSprintState");
      d = FieldAccessors.X(EntityPlayerSP.class, "serverSneakState", "serverSneakState");
      b = FieldAccessors.X(EntityPlayerSP.class, "mc", "mc");
   }

   public static void l( EntityPlayerSP var2, boolean var3) {
      d.O(var2, var3);
   }

   public static double I(long var0, EntityPlayerSP var2) {
      return g.w(var2);
   }

   public static float n(EntityPlayerSP var0, long var1) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      return j.V(var0);
   }

   public static void i(EntityPlayerSP var0, double var1, long var3) {

      g.f( var0, var1);
   }

   public static double M( EntityPlayerSP var2) {
      return V.w(var2);
   }

   public static boolean N( EntityPlayerSP var2) {
      return F.n(var2);
   }

   public static void N(EntityPlayerSP var0, boolean var1, short var2) {
      F.O(var0, var1);
   }

   public static void z(long var0, EntityPlayerSP var2, double var3) {

      V.f( var2, var3);
   }

   public static int L( EntityPlayerSP var2) {
      return H.m(var2);
   }

   public static float Q(EntityPlayerSP var0) {
      return r.V(var0);
   }



   public static Minecraft U(EntityPlayerSP var0, char var1, int var2, int var3) {
      return (Minecraft)b.v(var0);
   }

}
