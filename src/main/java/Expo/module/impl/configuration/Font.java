package Expo.module.impl.configuration;

import Expo.module.Category;

import Expo.module.Module;
import Expo.setting.settings.ModeSetting;
import Expo.util.render.CustomFont;
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


public class Font extends Module {
   private static CustomFont H;
   private static long a;
   public static ModeSetting scoreboardFont;
   private static String[] e;
   public static ModeSetting othersFont;
   private static CustomFont c;
   private static String[] b;
   private static CustomFont J;
   public static ModeSetting clickguiFont;
   private static CustomFont S;
   public static ModeSetting notificationsFont;
   private static Map h;
   private static CustomFont d;
   public static ModeSetting hudFont;
   public static ModeSetting arraylistFont;
   private static CustomFont n;

   static {
      a = 51596533601259L;
      zkm$clinit();
   }

   public static CustomFont s(long var0) {
      return Q(othersFont.Y());
   }

   public static CustomFont J() {
      return Q(scoreboardFont.Y());
   }

   public static CustomFont O(short var0, int var1) {
      return Q(notificationsFont.Y());
   }

   public Font(long var1) {
      super(((a ^ (var1)) ^ 34253150402247L));
      // add code
      this.declare("Font", Category.Configuration, "Manage font rendering");
      var1 = a ^ var1;
   }

   public static CustomFont Q(long var0) {
      return Q(arraylistFont.Y());
   }

   public static CustomFont F(long var0) {
      return Q(hudFont.Y());
   }

   private static CustomFont Q(String var2) {
      switch (var2.toUpperCase()) {
         case "PRODUCT_SANS":
            return J;
         case "INTER":
            return d;
         case "PING_FANG":
            return H;
         case "ROBOTO":
            return c;
         case "TAHOMA":
            return n;
         default:
            return S;
      }
   }

   public static CustomFont m(long var0) {
      return Q(clickguiFont.Y());
   }

   private static String b(byte[] var0) {
      int var1 = 0;
      int var2;
      char[] var3 = new char[var2 = var0.length];

      for (int var4 = 0; var4 < var2; var4++) {
         int var5;
         if ((var5 = 255 & var0[var4]) < 192) {
            var3[var1++] = (char)var5;
         } else if (var5 < 224) {
            char var6 = (char)((char)(var5 & 31) << 6);
            byte var8 = var0[++var4];
            var6 = (char)(var6 | (char)(var8 & 63));
            var3[var1++] = var6;
         } else if (var4 < var2 - 2) {
            char var12 = (char)((char)(var5 & 15) << '\f');
            byte var9 = var0[++var4];
            var12 = (char)(var12 | (char)(var9 & 63) << 6);
            var9 = var0[++var4];
            var12 = (char)(var12 | (char)(var9 & 63));
            var3[var1++] = var12;
         }
      }

      return new String(var3, 0, var1);
   }

   private static void zkm$clinit() {
      try {
         long var9 = a ^ 135337908579670L;
         long var11 = var9 ^ 27393810676644L;
         h = new HashMap(13);
         Cipher var0;
         byte[] var10003 = new byte[]{(byte)(var9 >>> 56), 0, 0, 0, 0, 0, 0, 0};

         for (int var1 = 1; var1 < 8; var1++) {
            var10003[var1] = (byte)(var9 << var1 * 8 >>> 56);
         }

         (var0 = Cipher.getInstance("DES/CBC/PKCS5Padding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
         String[] var7 = new String[11];
         int var5 = 0;
         String var4 = "F2o\u0082bù®ÓxIñcO\u0083u\u008f\u008bóuÒ^ta¶UJ\u001aã\u0015I\u008e\u009a\u0010\u0015\u001cãrÌ\u009cÁ\u0095á\u0097%\u0099µ¼\u0090|\u0010ÅÂ£ ·Ö\u0080\u000e@\u0099l«q·è¡\u0010,´ »W\u008dIûfúa:e5M+\u0010#õd\u0093\u000b?Ëh\fçÆËº¦Ýn É\u0010\u0005\u008dL#\u0099\u0016Ö\rÂfqh\u008a\u0095££\u008a\u008cz\u0010RÈ\u0098%ÏôwùÁ\r Ç@\u007f_7ûÁå\u0013\u001cë\u008c\"g\u0097Ë`6r;Ù\u0098}»Õâ\u0081!kz`\u0017\u00109rðÔnò\\÷\u00910ÖzGZ¸´\u0010Ý)K¼v\u0085#»:\u0096¥4:Y\u000fi";
         int var6 = "F2o\u0082bù®ÓxIñcO\u0083u\u008f\u008bóuÒ^ta¶UJ\u001aã\u0015I\u008e\u009a\u0010\u0015\u001cãrÌ\u009cÁ\u0095á\u0097%\u0099µ¼\u0090|\u0010ÅÂ£ ·Ö\u0080\u000e@\u0099l«q·è¡\u0010,´ »W\u008dIûfúa:e5M+\u0010#õd\u0093\u000b?Ëh\fçÆËº¦Ýn É\u0010\u0005\u008dL#\u0099\u0016Ö\rÂfqh\u008a\u0095££\u008a\u008cz\u0010RÈ\u0098%ÏôwùÁ\r Ç@\u007f_7ûÁå\u0013\u001cë\u008c\"g\u0097Ë`6r;Ù\u0098}»Õâ\u0081!kz`\u0017\u00109rðÔnò\\÷\u00910ÖzGZ¸´\u0010Ý)K¼v\u0085#»:\u0096¥4:Y\u000fi"
            .length();
         char var3 = ' ';
         int var15 = -1;

         label31:
         while (true) {
            String var16 = var4.substring(++var15, var15 + var3);
            byte var10001 = -1;

            while (true) {
               byte[] var8 = var0.doFinal(var16.getBytes("ISO-8859-1"));
               String var22 = b(var8).intern();
               switch (var10001) {
                  case 0:
                     var7[var5++] = var22;
                     if ((var15 += var3) >= var6) {
                        b = var7;
                        e = new String[11];
                        S = new CustomFont(var11, "NONE");
                        J = new CustomFont(var11, "productsans");
                        d = new CustomFont(var11, "inter");
                        H = new CustomFont(var11, "pingfang");
                        c = new CustomFont(var11, "roboto");
                        n = new CustomFont(var11, "tahoma");
                        return;
                     }

                     var3 = var4.charAt(var15);
                     break;
                  default:
                     var7[var5++] = var22;
                     if ((var15 += var3) < var6) {
                        var3 = var4.charAt(var15);
                        continue label31;
                     }

                     var4 = "\u009b\u0013`Ü7ú\u000eÍl\u0017Ìù\u0005`ö\u008e\u001b\u009e*!Yíz^c¢}n\fâ\u0011\u001c\u0010\u0094\u0087\u0004àµkÛ±¯4sød\u0010\u009d\u0089";
                     var6 = "\u009b\u0013`Ü7ú\u000eÍl\u0017Ìù\u0005`ö\u008e\u001b\u009e*!Yíz^c¢}n\fâ\u0011\u001c\u0010\u0094\u0087\u0004àµkÛ±¯4sød\u0010\u009d\u0089"
                        .length();
                     var3 = ' ';
                     var15 = -1;
               }

               var16 = var4.substring(++var15, var15 + var3);
               var10001 = 0;
            }
         }
      } catch (UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException var13) {
         throw new RuntimeException(var13);
      }
   }

   static {
      // add code
      clickguiFont = new ModeSetting("ClickGUI-font", "NONE", "PRODUCT_SANS", "INTER", "PING_FANG", "ROBOTO", "TAHOMA");
      hudFont = new ModeSetting("HUD-font", "NONE", "PRODUCT_SANS", "INTER", "PING_FANG", "ROBOTO", "TAHOMA");
      scoreboardFont = new ModeSetting("Scoreboard-font", "NONE", "PRODUCT_SANS", "INTER", "PING_FANG", "ROBOTO", "TAHOMA");
      notificationsFont = new ModeSetting("Notifications-font", false, "PING_FANG", "NONE", "PRODUCT_SANS", "INTER", "PING_FANG", "ROBOTO", "TAHOMA");
      othersFont = new ModeSetting("Others-font", "NONE", "PRODUCT_SANS", "INTER", "PING_FANG", "ROBOTO", "TAHOMA");
      arraylistFont = new ModeSetting("ArrayList-font", "NONE", "PRODUCT_SANS", "INTER", "PING_FANG", "ROBOTO", "TAHOMA");
   }
}
