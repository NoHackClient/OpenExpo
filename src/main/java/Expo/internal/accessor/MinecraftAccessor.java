package Expo.internal.accessor;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.MusicTicker;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.util.Timer;











public final class MinecraftAccessor {
   private static TypedValueStore X;
   private static TypedValueStore y;
   private static TypedValueStore z;
   private static TypedValueStore B;
   private static TypedValueStore V;

   public static Timer o( Minecraft var2) {
      return (Timer)V.v(var2);
   }

   public static void c(Minecraft var0, int var1, long var2) {
      B.T(var0, var1);
   }

   public static void K(Minecraft var0, Framebuffer var1) {
      X.d(var0, var1);
   }

   public static int C(Minecraft var0) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      return y.m(var0);
   }

   public static Framebuffer N(long var0, Minecraft var2) {
      return (Framebuffer)X.v(var2);
   }

   public static MusicTicker S(char var0, int var1, Minecraft var3) {
      return (MusicTicker)z.v(var3);
   }



   public static void j(long var0, Minecraft var2, int var3) {
      y.T(var2, var3);
   }

   static {
      V = FieldAccessors.X(Minecraft.class, "timer", "timer");
      B = FieldAccessors.X(Minecraft.class, "leftClickCounter", "leftClickCounter");
      y = FieldAccessors.X(Minecraft.class, "rightClickDelayTimer", "rightClickDelayTimer");
      z = FieldAccessors.X(Minecraft.class, "mcMusicTicker", "mcMusicTicker");
      X = FieldAccessors.X(Minecraft.class, "framebufferMc", "framebufferMc");
   }

}
