package Expo.util.render;

import java.awt.Color;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;











public class ColorUtil {
   private static Map d;
   private static Map g;
   private static Map<Character, Color> P;
   private static Map<EnumChatFormatting, Color> B;
   private static long a;
   private static String[] b;



   public static Color M(char var0) {
      return P.getOrDefault(var0, Color.WHITE);
   }

   public static int s(int var0, int var1, long var2) {
      return O(var0, var0, var0, var1);
   }

   public static int O(int var0, int var1, int var2, int var5) {
      int var6 = MathHelper.clamp_int(var5, 0, 255) << 24;
      var6 |= MathHelper.clamp_int(var0, 0, 255) << 16;
      var6 |= MathHelper.clamp_int(var1, 0, 255) << 8;
      return var6 | MathHelper.clamp_int(var2, 0, 255);
   }

   public static Color I(EnumChatFormatting var0) {
      return P.getOrDefault(var0, Color.WHITE);
   }

   public static int U(long var0, int var2) {
      return var2 >> 8 & 255;
   }

   public static String A(EnumChatFormatting var0, long var1) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      Color var3 = I(var0);
      return String.format("#%02X%02X%02X", var3.getRed(), var3.getGreen(), var3.getBlue());
   }

   public static int d(long var0, int var2) {
      return var2 & 255;
   }

   public static int l(int var0, long var1) {
      return var0 >> 16 & 255;
   }

   public static int g(long var0, int var2) {
      return var2 >> 24 & 255;
   }

   public static int j(int var0, int var1, long var2) {
      var1 = Math.max(0, Math.min(255, var1));
      return var0 & 16777215 | var1 << 24;
   }

   static {
      a = 39555523141576L;
      P = new HashMap<>();
      B = new HashMap<>();
      P.put(Character.valueOf((char)48), new Color(0, 0, 0));
      P.put(Character.valueOf((char)49), new Color(0, 0, 170));
      P.put(Character.valueOf((char)50), new Color(0, 170, 0));
      P.put(
         Character.valueOf((char)51),
         new Color(0, 170, 170)
      );
      P.put(Character.valueOf((char)52), new Color(170, 0, 0));
      P.put(
         Character.valueOf((char)53),
         new Color(170, 0, 170)
      );
      P.put(
         Character.valueOf((char)54),
         new Color(255, 170, 0)
      );
      P.put(
         Character.valueOf((char)55),
         new Color(170, 170, 170)
      );
      P.put(
         Character.valueOf((char)56),
         new Color(85, 85, 85)
      );
      P.put(
         Character.valueOf((char)57),
         new Color(85, 85, 255)
      );
      P.put(
         Character.valueOf((char)97),
         new Color(85, 255, 85)
      );
      P.put(
         Character.valueOf((char)98),
         new Color(
            85, 255, 255
         )
      );
      P.put(
         Character.valueOf((char)99),
         new Color(255, 85, 85)
      );
      P.put(
         Character.valueOf((char)100),
         new Color(
            255, 85, 255
         )
      );
      P.put(
         Character.valueOf((char)101),
         new Color(
            255, 255, 85
         )
      );
      P.put(
         Character.valueOf((char)102),
         new Color(
            255, 255, 255
         )
      );
      B.put(EnumChatFormatting.BLACK, new Color(0, 0, 0));
      B.put(EnumChatFormatting.DARK_BLUE, new Color(0, 0, 170));
      B.put(EnumChatFormatting.DARK_GREEN, new Color(0, 170, 0));
      B.put(EnumChatFormatting.DARK_AQUA, new Color(0, 170, 170));
      B.put(EnumChatFormatting.DARK_RED, new Color(170, 0, 0));
      B.put(EnumChatFormatting.DARK_PURPLE, new Color(170, 0, 170));
      B.put(EnumChatFormatting.GOLD, new Color(255, 170, 0));
      B.put(
         EnumChatFormatting.GRAY,
         new Color(170, 170, 170)
      );
      B.put(
         EnumChatFormatting.DARK_GRAY,
         new Color(85, 85, 85)
      );
      B.put(
         EnumChatFormatting.BLUE,
         new Color(85, 85, 255)
      );
      B.put(
         EnumChatFormatting.GREEN,
         new Color(85, 255, 85)
      );
      B.put(
         EnumChatFormatting.AQUA,
         new Color(
            85, 255, 255
         )
      );
      B.put(
         EnumChatFormatting.RED,
         new Color(255, 85, 85)
      );
      B.put(
         EnumChatFormatting.LIGHT_PURPLE,
         new Color(
            255, 85, 255
         )
      );
      B.put(
         EnumChatFormatting.YELLOW,
         new Color(
            255, 255, 85
         )
      );
      B.put(
         EnumChatFormatting.WHITE,
         new Color(
            255, 255, 255
         )
      );
   }

   public static int Y(long var0, Color var2) {
      return O(var2.getRed(), var2.getGreen(), var2.getBlue(), var2.getAlpha());
   }

   public static Color Z(long var0, Color var2, float var3) {
      return b(var2,0L, var3, var2.getAlpha());
   }





   public static String R(long var0, char var2) {
      Color var3 = M(var2);
      return String.format("#%02X%02X%02X", var3.getRed(), var3.getGreen(), var3.getBlue());
   }

   public static int t(int var0, long var1, byte var3) {
      return O(var0, var0, var0, 255);
   }

   public static Color D(String var0) {
      return P.getOrDefault(var0.charAt(0), Color.WHITE);
   }

   public static Color b(Color var0, long var1, float var3, int var4) {
      return new Color(
         Math.min(Math.max((int)(var0.getRed() * var3), 0), 255),
         Math.min(Math.max((int)(var0.getGreen() * var3), 0), 255),
         Math.min(Math.max((int)(var0.getBlue() * var3), 0), 255),
         var4
      );
   }


}
