package Expo.ui.swing;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.swing.JPanel;











class VisualSpoofPreviewPanel extends JPanel {
   private static long[] c;
   private static String b;
   private volatile BufferedImage g;
   private static long a;
   private static Map e;

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

   VisualSpoofPreviewPanel(VisualSpoofWindowCloseListener var1) {
      this();
   }

   public void N(BufferedImage var1) {
      this.g = var1;
   }

   protected void paintComponent(Graphics var1) {
      super.paintComponent(var1);
      Graphics2D var4 = (Graphics2D)var1;
      var4.setColor(Color.BLACK);
      var4.fillRect(0, 0, this.getWidth(), this.getHeight());
      BufferedImage var5 = this.g;
      if (var5 == null) {
         var4.setColor(Color.DARK_GRAY);
         var4.drawString(b, 16, 24);
      } else {
         int var6 = this.getWidth();
         int var7 = this.getHeight();
         int var8 = var5.getWidth();
         int var9 = var5.getHeight();
         if (Math.abs(var6 - var8) <= 2 && Math.abs(var7 - var9) <= 2) {
            var4.drawImage(var5, 0, 0, var6, var7, null);
         } else {
            double var10 = Math.min((double)var6 / var8, (double)var7 / var9);
            int var12 = Math.max(1, (int)Math.round(var8 * var10));
            int var13 = Math.max(1, (int)Math.round(var9 * var10));
            int var14 = (var6 - var12) / 2;
            int var15 = (var7 - var13) / 2;
            var4.drawImage(var5, var14, var15, var12, var13, null);
         }
      }
   }

   static {
      // add code
      try {
         a = 44558175713610L;
         long var11 = a ^ 10678190580043L;
         Cipher var13;
         Cipher var10000 = var13 = Cipher.getInstance("DES/CBC/PKCS5Padding");
         SecretKeyFactory var10002 = SecretKeyFactory.getInstance("DES");
         byte[] var10003 = new byte[]{(byte)(var11 >>> 56), 0, 0, 0, 0, 0, 0, 0};

         for (int var14 = 1; var14 < 8; var14++) {
            var10003[var14] = (byte)(var11 << var14 * 8 >>> 56);
         }

         var10000.init(2, var10002.generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
         byte[] var15 = var13.doFinal(
            "vT\u0089ÌQÕ\u008dq\u0001;·\u008d«H§.&\u0011ÂéB\u0013\u0007/÷\u0098\n9\u0012ÿÁî\u0006\u001eÑ«¯\u0099?a".getBytes("ISO-8859-1")
         );
         String var20 = a(var15).intern();
         int var10001 = -1;
         b = var20;
         e = new HashMap(13);
         Cipher var0;
         var10000 = var0 = Cipher.getInstance("DES/CBC/NoPadding");
         var10002 = SecretKeyFactory.getInstance("DES");
         var10003 = new byte[]{(byte)(var11 >>> 56), 0, 0, 0, 0, 0, 0, 0};

         for (int var1 = 1; var1 < 8; var1++) {
            var10003[var1] = (byte)(var11 << var1 * 8 >>> 56);
         }

         var10000.init(2, var10002.generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
         long[] var6 = new long[2];
         int var3 = 0;
         String var4 = "+\u008a\u0086ûýÎl\u0082j\u008f\u0010Üø%!a";
         int var5 = "+\u008a\u0086ûýÎl\u0082j\u008f\u0010Üø%!a".length();
         int var2 = 0;

         do {
            var10001 = var2;
            var2 += 8;
            byte[] var7 = var4.substring(var10001, var2).getBytes("ISO-8859-1");
            long var8 = (var7[0] & 255L) << 56
               | (var7[1] & 255L) << 48
               | (var7[2] & 255L) << 40
               | (var7[3] & 255L) << 32
               | (var7[4] & 255L) << 24
               | (var7[5] & 255L) << 16
               | (var7[6] & 255L) << 8
               | var7[7] & 255L;
            byte[] var10 = var0.doFinal(
               new byte[]{
                  (byte)(var8 >>> 56),
                  (byte)(var8 >>> 48),
                  (byte)(var8 >>> 40),
                  (byte)(var8 >>> 32),
                  (byte)(var8 >>> 24),
                  (byte)(var8 >>> 16),
                  (byte)(var8 >>> 8),
                  (byte)var8
               }
            );
            long var10004 = (var10[0] & 255L) << 56
               | (var10[1] & 255L) << 48
               | (var10[2] & 255L) << 40
               | (var10[3] & 255L) << 32
               | (var10[4] & 255L) << 24
               | (var10[5] & 255L) << 16
               | (var10[6] & 255L) << 8
               | var10[7] & 255L;
            int var23 = -1;
            var6[(var3++)] = var10004;
         } while (var2 < var5);

         c = var6;
      } catch (UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException var16) {
         throw new RuntimeException(var16);
      }
   }

   private VisualSpoofPreviewPanel() {
   }

}
