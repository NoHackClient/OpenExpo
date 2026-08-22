package Expo.util.render;

import Expo.module.impl.configuration.Font;
import Expo.module.impl.configuration.Language;
import Expo.util.MathUtil;
import Expo.util.MinecraftRef;
import java.awt.Color;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.GL11;


public class FontUtil {
   private static Map g;
   private static Integer[] f;
   private static long[] e;
   private static String[] b;
   public static final float x = 0.66F;
   private static String[] c;
   private static Map d;
   private static long a;

   public static float J(FontRenderer var0, float var1) {
      return var0.FONT_HEIGHT * var1;
   }

   public static void R(long var0) {
      GL11.glDisable(3089);
   }

   private static String a(int var0, long var1) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      int var5 = var0 ^ (int)(var1 & 32767L) ^ 30329;
      if (c[var5] == null) {
         Object[] var4;
         try {
            Long var3 = Thread.currentThread().getId();
            var4 = (Object[])d.get(var3);
            if (var4 == null) {
               var4 = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
               d.put(var3, var4);
            }
         } catch (Exception var10) {
            throw new RuntimeException("Expo/util/render/FontUtil", var10);
         }

         byte[] var6 = new byte[8];
         var6[0] = (byte)(var1 >>> 56);

         for (int var7 = 1; var7 < 8; var7++) {
            var6[var7] = (byte)(var1 << var7 * 8 >>> 56);
         }

         DESKeySpec var11 = new DESKeySpec(var6);
         SecretKey var8 = ((SecretKeyFactory)var4[1]).generateSecret(var11);
         ((Cipher)var4[0]).init(2, var8, (IvParameterSpec)var4[2]);
         byte[] var9 = b[var5].getBytes("ISO-8859-1");
         c[var5] = a(((Cipher)var4[0]).doFinal(var9));
      }

      return c[var5];
   }

   public static void F(FontRenderer var0, String var1, float var2, float var3, float var4, int var5) {
      GL11.glPushMatrix();
      GL11.glScalef(var4, var4, 1.0F);
      var0.drawStringWithShadow(var1, var2 / var4, var3 / var4, var5);
      GL11.glPopMatrix();
   }

   public static void u(float var0, float var1, float var4, float var5, int var6) {
      GL11.glPushMatrix();
      GL11.glTranslatef(var0, var1, 0.0F);
      GL11.glRotatef(-90.0F + var5 * 90.0F, 0.0F, 0.0F, 1.0F);
      GL11.glTranslatef(-var0, -var1, 0.0F);
      GL11.glDisable(3553);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      GL11.glLineWidth(1.8F);
      RenderUtil.P(0L, var6);
      GL11.glBegin(3);
      GL11.glVertex2f(var0 - var4 / 2.0F, var1 - var4 / 2.0F + 1.0F);
      GL11.glVertex2f(var0, var1 + var4 / 2.0F - 1.0F);
      GL11.glVertex2f(var0 + var4 / 2.0F, var1 - var4 / 2.0F + 1.0F);
      GL11.glEnd();
      GL11.glDisable(3042);
      GL11.glEnable(3553);
      GL11.glPopMatrix();
   }


   private static String a(byte[] var0) {
      int var1 = 0;
      int var2;
      char[] var3 = new char[var2 = var0.length];

      for (int var4 = 0; var4 < var2; var4++) {
         int var5;
         if ((var5 = 255 & var0[var4]) < 192) {
            var3[var1++] = (char)var5;
         } else if (var5 < 224) {
            char var6 = (char)((char)(var5 & 31) << 6);
            int var8 = var0[++var4];
            var6 = (char)(var6 | (char)(var8 & 63));
            var3[var1++] = var6;
         } else if (var4 < var2 - 2) {
            char var12 = (char)((char)(var5 & 15) << '\f');
            int var9 = var0[++var4];
            var12 = (char)(var12 | (char)(var9 & 63) << 6);
            var9 = var0[++var4];
            var12 = (char)(var12 | (char)(var9 & 63));
            var3[var1++] = var12;
         }
      }

      return new String(var3, 0, var1);
   }

   public static float m(FontRenderer var0, float var1, float var2) {
      return var1 + (var2 - var0.FONT_HEIGHT) / 2.0F;
   }

   public static float I(long var0, CustomFont var2, float var3, float var4, float var5) {
      long var6 = var0 ^ 40156453075033L;
      return var3 + (var4 - B(var2, var5, var6)) / 2.0F;
   }

   public static void D(long var0, float var2, float var3, float var4, float var5, float var6) {
      var0 = a ^ var0;
      int var7 = (int)((var0 ^ 118776387564556L) >>> 56);
      Minecraft var10 = MinecraftRef.c((byte)var7,0L);
      ScaledResolution var11 = new ScaledResolution(var10);
      int var12 = var11.getScaleFactor();
      int var13 = Math.round(var2 * var12 * var6);
      int var14 = Math.round((var11.getScaledHeight() - (var3 + var5) * var6) * var12);
      int var15 = Math.round(var4 * var12 * var6);
      int var16 = Math.round(var5 * var12 * var6);
      GL11.glEnable(3089);
      GL11.glScissor(var13, var14, var15, var16);
   }

   public static float B(CustomFont var0, float var1, long var2) {
      long var4 = var2 ^ 115828686605425L;
      return var0.o(var4) * var1;
   }

   public static void N(CustomFont var0, long var1, String var3, float var4, float var5, float var6, int var7) {


      GL11.glPushMatrix();
      GL11.glScalef(var6, var6, 1.0F);
      var0.T(37697014677608L, var3, var4 / var6, var5 / var6, var7);
      GL11.glPopMatrix();
   }

   public static String O(int var0, FontRenderer var1, String var2, int var3, float var4, short var5) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var6 = ((long)var0 << 32 | (long)var3 << 48 >>> 32 | (long)var5 << 48 >>> 48) ^ a;
      if (var2 == null) {
         return "";
      }

      if (var1.getStringWidth(var2) <= var4) {
         return var2;
      }

      String var8 = a(32506, 8244383727619224661L ^ var6);
      String var9 = var2;

      while (!var9.isEmpty() && var1.getStringWidth(var9 + var8) > var4) {
         var9 = var9.substring(0, var9.length() - 1);
      }

      return var9 + var8;
   }

   public static CustomFont G() {
      return Font.m(0L);
   }

   public static int y( int var2, float var3) {
      var3 = MathUtil.q(var3, 0.0F, 1.0F);
      int var4 = Math.round((var2 >> 24 & 255) * var3);
      return var2 & 16777215 | var4 << 24;
   }

   public static float P(long var0, short var2, CustomFont var3, float var4, float var5) {
      long var6 = (var0 << 16 | (long)var2 << 48 >>> 48) ^ a;
      long var8 = var6 ^ 125973604465894L;
      return var4 + (var5 - var3.o(var8)) / 2.0F;
   }

   public static void S(CustomFont var0, String var1, float var2, float var3, float var4, long var5, float var7, float var8, int var9) {
      var5 = a ^ var5;
      long var10 = var5 ^ 35792876429264L;
      long var12 = var5 ^ 89123294185278L;
      long var14 = var5 ^ 4004887569250L;
      float var16 = var2 + (var4 - A(var0, var1, var8, var14)) / 2.0F;
      float var17 = var3 + (var7 - B(var0, var8, var12)) / 2.0F;
      N(var0, var10, var1, var16, var17, var8, var9);
   }

   public static String p(FontRenderer var0, String var1, float var2, float var3, long var4) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      var4 = a ^ var4;
      int var6 = (int)((var4 ^ 9563793411971L) >>> 32);
      int var7 = (int)((var4 ^ 9563793411971L) << 32 >>> 48);
      int var8 = (int)((var4 ^ 9563793411971L) << 48 >>> 48);
      return O(var6, var0, var1, var7, var2 / var3, (short)var8);
   }

   public static String l(double var0, long var2) {
      String var4 = String.format("%.2f", var0);
      if (var4.contains(".")) {
         var4 = var4.replaceAll("0+$", "").replaceAll("\\.$", "");
      }

      return var4;
   }

   public static String s(CustomFont var0, long var1, String var3, float var4) {


      if (var3 == null) {
         return "";
      }

      if (var0.R(var3, 52019766876817L) <= var4) {
         return var3;
      }

      String var7 = "...";
      String var8 = var3;

      while (!var8.isEmpty() && var0.R(var8 + var7, 52019766876817L) > var4) {
         var8 = var8.substring(0, var8.length() - 1);
      }

      return var8 + var7;
   }

   public static float c(FontRenderer var0, String var1, float var2, float var3, float var4) {
      if (var1 != null && !var1.isEmpty()) {
         float var5 = var0.getStringWidth(var1);
         if (var5 <= 0.0F) {
            return var3;
         }

         float var6 = Math.min(var3, var2 / var5);
         return Math.max(var4, var6);
      } else {
         return var3;
      }
   }

   public static int a(int var0, int var1, Color var2, float var3) {
      return y( var2.getRGB(), var3);
   }

   public static String Q(int var0, CustomFont var1, String var2, short var3, char var4, float var5, float var6) {
      long var7 = ((long)var0 << 32 | (long)var3 << 48 >>> 32 | (long)var4 << 48 >>> 48) ^ a;
      long var9 = var7 ^ 37069290432496L;
      return s(var1, var9, var2, var5 / var6);
   }

   public static float Q(FontRenderer var0, float var1, float var2, float var3) {
      return var1 + (var2 - J(var0, var3)) / 2.0F;
   }

   public static void L(FontRenderer var0, String var1, float var2, float var3, float var4, float var5, float var6, int var7) {
      float var8 = var2 + (var4 - M(var0, var1, var6)) / 2.0F;
      float var9 = var3 + (var5 - J(var0, var6)) / 2.0F;
      F(var0, var1, var8, var9, var6, var7);
   }

   public static float M(FontRenderer var0, String var1, float var2) {
      return var0.getStringWidth(var1) * var2;
   }

   public static float w(CustomFont var0, String var1, float var2, float var3, float var4, long var5) {
      long var7 = var5 ^ 98167280883244L;
      if (var1 != null && !var1.isEmpty()) {
         float var9 = var0.R(var1, var7);
         if (var9 <= 0.0F) {
            return var3;
         }

         float var10 = Math.min(var3, var2 / var9);
         return Math.max(var4, var10);
      } else {
         return var3;
      }
   }

   public static float A(CustomFont var0, String var1, float var2, long var3) {


      return var0.R(var1, 52019766876817L) * var2;
   }

   public static CustomFont n(long var0, int var2) {
      return Font.m(0L);
   }

   public static String G(String var0, String var1) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      String var6 = Language.z(var0,0L);
      return var6 != null && !var6.isEmpty() && !var6.equals("LANGUAGE_ERROR") ? var6 : var1;
   }

   static {
      a = 18527240782280L;
      d = new HashMap(13);
      b = new String[]{"}\u009a Z\u000bgAs", "[\u00a4\u0097a\u00d4\u0016\u00bb[", "\u00d9\u00b7Z\u00fa\u008bVk\u0099", "O\u00cb\u00e44\u00cd\u00c4\u00bb\u00ab7\u00a3\u00d5\u00af\u00cb\u00bdZV", "\u00a9^uN\u00a8\u008c\u00ea\u00f7", "(\u0092\u00bfz\u009f\u0081\u00a0\u0093"};
      c = new String[6];
      g = new HashMap(13);
      e = new long[]{1301708283197147249L, -1541604716268540942L, 6379316386513373933L, 4355031182947347440L, 4165885267922111809L, 6434429687449019973L, -4802263111103286353L, 7726072117318443924L, 7157246289987260658L, 2471304832662197487L, -6871509033680594436L, 8276650313308325762L};
      f = new Integer[12];
   }

   private FontUtil() {
   }

   public static void W(float var0, float var3, float var4, float var5, float var6, int var7, int var8) {
      RenderUtil.J( var0, var3, var4, var5, var6, var7, var8, var8);
   }

   public static Color Y(Color var0, Color var1, float var2) {
      var2 = MathUtil.q(var2, 0.0F, 1.0F);
      float var3 = 1.0F - var2;
      return new Color(
         Math.round(var0.getRed() * var3 + var1.getRed() * var2),
         Math.round(var0.getGreen() * var3 + var1.getGreen() * var2),
         Math.round(var0.getBlue() * var3 + var1.getBlue() * var2),
         Math.round(var0.getAlpha() * var3 + var1.getAlpha() * var2)
      );
   }

}
