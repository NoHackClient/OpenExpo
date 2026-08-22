package Expo.util;

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


public enum ChatFormatting {
   BLACK(0, (char)48, -16777216, (char)zkm$g24()),
   DARK_BLUE(1, (char)49, -16777046, (char)zkm$g24()),
   DARK_GREEN(2, (char)50, -16733696, (char)zkm$g24()),
   DARK_AQUA(3, (char)51, -16733526, (char)zkm$g24()),
   DARK_RED(4, (char)52, -5636096, (char)zkm$g24()),
   DARK_PURPLE(5, (char)53, -5635926, (char)zkm$g24()),
   GOLD(
      6, (char)54, -22016, (char)zkm$g24()
   ),
   GRAY(7, (char)55, -5592406, (char)zkm$g24()),
   DARK_GRAY(
      8, (char)56, -11184811, (char)zkm$g24()
   ),
   BLUE(9, (char)57, -11184641, (char)zkm$g24()),
   GREEN(10, (char)97, -11141291, (char)zkm$g24()),
   AQUA(11, (char)98, -11141121, (char)zkm$g24()),
   RED(12, (char)99, -43691, (char)zkm$g24()),
   LIGHT_PURPLE(
      13, (char)100, -43521, (char)zkm$g24()
   ),
   YELLOW(
      14, (char)101, -171, (char)zkm$g24()
   ),
   WHITE(15, (char)102, -1, (char)zkm$g24()),
   MAGIC(16, (char)107, 0, (char)zkm$g24()),
   BOLD(17, (char)108, 0, (char)zkm$g24()),
   STRIKETHROUGH(18, (char)109, 0, (char)zkm$g24()),
   UNDERLINE(19, (char)110, 0, (char)zkm$g24()),
   ITALIC(20, (char)111, 0, (char)zkm$g24()),
   RESET(21, (char)114, 0, (char)zkm$g24());

   private static String[] d;
   private static long[] g;
   private static String[] c;
   private final int b;
   public static char COLOR_CHAR;
   private final String e;
   private static Map i;
   private static Map f;
   private static long a;
   private static boolean zkm$done;
   private static long zkm$v20;
   private static long zkm$v22;
   private static int zkm$v24;


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

   ChatFormatting(int var3, char var4, int var5, char var6) {
      long var8 = (zkm$g22() << 16 | (long)var6 << 48 >>> 48) ^ zkm$ga();   // add code
      this.b = var5;
      this.e = new String(new char[]{(char)167, var4});
   }

   public int r() {
      return this.b;
   }

   public String toString() {
      return this.e;
   }

   static {
      ChatFormatting[] var10000 = new ChatFormatting[22];
      var10000[0] = BLACK;
      var10000[1] = DARK_BLUE;
      var10000[2] = DARK_GREEN;
      var10000[3] = DARK_AQUA;
      var10000[4] = DARK_RED;
      var10000[5] = DARK_PURPLE;
      var10000[6] = GOLD;
      var10000[7] = GRAY;
      var10000[8] = DARK_GRAY;
      var10000[9] = BLUE;
      var10000[10] = GREEN;
      var10000[11] = AQUA;
      var10000[12] = RED;
      var10000[13] = LIGHT_PURPLE;
      var10000[14] = YELLOW;
      var10000[15] = WHITE;
      var10000[16] = MAGIC;
      var10000[17] = BOLD;
      var10000[18] = STRIKETHROUGH;
      var10000[19] = UNDERLINE;
      var10000[20] = ITALIC;
      var10000[21] = RESET;
   }

   public static String y(String var0) {
      char[] var6 = var0.toCharArray();

      for (int var7 = 0; var7 < var6.length - 1; var7++) {
         if (var6[var7] == 38 && "0123456789AaBbCcDdEeFfKkLlMmNnOoRr".indexOf(var6[var7 + 1]) > -1) {
            var6[var7] = (char)167;
            var6[var7 + 1] = Character.toLowerCase(var6[var7 + 1]);
         }
      }

      return new String(var6);
   }

   private static void zkm$pre() {
      try {
         a = 13249631676262L;
         long var20 = a ^ 16214520472296L;
         long var22 = (var20 ^ 14477811661532L) >>> 16;
         int var24 = (int)((var20 ^ 14477811661532L) << 48 >>> 48);
         f = new HashMap(13);
         Cipher var11;
         byte[] var10003 = new byte[]{(byte)(var20 >>> 56), 0, 0, 0, 0, 0, 0, 0};

         for (int var12 = 1; var12 < 8; var12++) {
            var10003[var12] = (byte)(var20 << var12 * 8 >>> 56);
         }

         (var11 = Cipher.getInstance("DES/CBC/PKCS5Padding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
         String[] var18 = new String[23];
         int var16 = 0;
         String var15 = "ÿ¼Ý4f°ØÞ9Åª\u008atÝ¿\u0085\u0010\u0088Ìk×TÖ1û÷R(]N{oø\u0018å~$·\u009f/D\\é'î)Scü¾*\u0097á2¾pÞý qIÅ\u0013TIÊ\u008b\u0085\u0083²þ_\u008cm·\u0001Ø³Äj[ðÙñ\u007f@¤\u0003\u009b2s\u0010¡À{\u0093\u0005eÈ.¯»\u00ad\u0089\u008e/\u0092!\u0010\u0005\u008fd\u0092ü\u009dÌ1\u0080\u0011W¨h¢Kñ8¢í¢¼\u0007ë\u001a3xÄínek%8\b:¤ ºÂÝ\u0019'\u0097WE'i&\u008d\u0005\u00ad\u0096\u009d´\u00042o\u0097ÖEË\u001aÜ\u008dõÉ*Ç*3ë?b\u0010>\u0005\u009cç>\u0000\f4è®\u00918\u000fôa\u009b\u0010¨\u001f«+CçÒ\u0096¿c\u0082\u0006î1¹¡\u0010{ÚmÃ\u008b{¾\u0000ó\u0010)\u0088}\u001fßÊ\u0010¬¶Wj\u0088XMÍ\u0091y\u001bV8 «Ö\u0010Ô\u001eØÑI #\u0087[L&/Ó\u0092ª\f º®ýy2'ûU*á\u00958lÛ\u0096}0=í\u0081Æ\u0010PI|\u0006 \u000bÃzÖÃ fñÇ\"ô«9\u001e1·\u0094xÈ\u0099\r(Ô|\u0080\rÁ*Y0çÚ¶MCÃrB\u0010\u0005Ë\u001eÙ0ÃOç<\u0086\u0005\u0019\u0092\u0007/Æ\u0010SQû!Ú]!ÌG\u00020Í\u0005\u0001ñÁ ìP#¶Oy\u0085ºÈ\n¯3o/\u0000Æ\u0016\u001b!\u0098\u0010=È»c¾\u008aQ9¶\u0082E\u0018\u0095%W¼:Fv*|\u0092\u008fTã!ÿ!Ð\u008fð\u0004CYx×\u0018\u0085ZÑÇÏCß\u009e\u0082Z\u000eø\u0016a\"#ò.\u001f\u008cz\u009b\"P\u0018'\u008a\u0081÷-£øÛÆù\u001b\u0080Òg4á»jËÐàì§~ \u001aÿ\u0094¹ù¤ßq¥Ç:\u0089+ r2¯*w\u0092qÌ\u0082ÿh)ì©\u0016W4\u001d";
         int var17 = "ÿ¼Ý4f°ØÞ9Åª\u008atÝ¿\u0085\u0010\u0088Ìk×TÖ1û÷R(]N{oø\u0018å~$·\u009f/D\\é'î)Scü¾*\u0097á2¾pÞý qIÅ\u0013TIÊ\u008b\u0085\u0083²þ_\u008cm·\u0001Ø³Äj[ðÙñ\u007f@¤\u0003\u009b2s\u0010¡À{\u0093\u0005eÈ.¯»\u00ad\u0089\u008e/\u0092!\u0010\u0005\u008fd\u0092ü\u009dÌ1\u0080\u0011W¨h¢Kñ8¢í¢¼\u0007ë\u001a3xÄínek%8\b:¤ ºÂÝ\u0019'\u0097WE'i&\u008d\u0005\u00ad\u0096\u009d´\u00042o\u0097ÖEË\u001aÜ\u008dõÉ*Ç*3ë?b\u0010>\u0005\u009cç>\u0000\f4è®\u00918\u000fôa\u009b\u0010¨\u001f«+CçÒ\u0096¿c\u0082\u0006î1¹¡\u0010{ÚmÃ\u008b{¾\u0000ó\u0010)\u0088}\u001fßÊ\u0010¬¶Wj\u0088XMÍ\u0091y\u001bV8 «Ö\u0010Ô\u001eØÑI #\u0087[L&/Ó\u0092ª\f º®ýy2'ûU*á\u00958lÛ\u0096}0=í\u0081Æ\u0010PI|\u0006 \u000bÃzÖÃ fñÇ\"ô«9\u001e1·\u0094xÈ\u0099\r(Ô|\u0080\rÁ*Y0çÚ¶MCÃrB\u0010\u0005Ë\u001eÙ0ÃOç<\u0086\u0005\u0019\u0092\u0007/Æ\u0010SQû!Ú]!ÌG\u00020Í\u0005\u0001ñÁ ìP#¶Oy\u0085ºÈ\n¯3o/\u0000Æ\u0016\u001b!\u0098\u0010=È»c¾\u008aQ9¶\u0082E\u0018\u0095%W¼:Fv*|\u0092\u008fTã!ÿ!Ð\u008fð\u0004CYx×\u0018\u0085ZÑÇÏCß\u009e\u0082Z\u000eø\u0016a\"#ò.\u001f\u008cz\u009b\"P\u0018'\u008a\u0081÷-£øÛÆù\u001b\u0080Òg4á»jËÐàì§~ \u001aÿ\u0094¹ù¤ßq¥Ç:\u0089+ r2¯*w\u0092qÌ\u0082ÿh)ì©\u0016W4\u001d"
            .length();
         char var14 = 16;
         int var28 = -1;

         label58:
         while (true) {
            String var29 = var15.substring(++var28, var28 + var14);
            int var10001 = -1;

            while (true) {
               byte[] var19 = var11.doFinal(var29.getBytes("ISO-8859-1"));
               String var40 = a(var19).intern();
               switch (var10001) {
                  case 0:
                     var18[var16++] = var40;
                     if ((var28 += var14) >= var17) {
                        c = var18;
                        d = new String[23];
                        i = new HashMap(13);
                        Cipher var0;
                        var10003 = new byte[]{(byte)(var20 >>> 56), 0, 0, 0, 0, 0, 0, 0};

                        for (int var1 = 1; var1 < 8; var1++) {
                           var10003[var1] = (byte)(var20 << var1 * 8 >>> 56);
                        }

                        (var0 = Cipher.getInstance("DES/CBC/NoPadding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
                        long[] var6 = new long[74];
                        int var3 = 0;
                        String var4 = "ºúÙ¥\u0010¹®\u001dÇô5\u009dh\u0005ù&O\u0083:3ê\u0010vc\u000b¨\r·\u0083YÙ¿Ft\u008f\u001dw\u0018»faåâÏ \u0097}I´ÿ.ìýU\u0002k²\u0087\u001f\u009f¦Ô;ÐT\u0005ãÑ£\u001bÞSµê¸\u00ad\u0011\u0012¢sçuíp~©%'Æb¼ôy\u0091B\u0097HÁ\t\u009f\u009e\n\u0091\u000e\u0004û\u008b[Ù3\u0080%¿\u001f_\u0098UnÍÐ¯c2\u00813»\u000fÌÅÿ\u0085m'Û\f\u0092Cî±6E\u0088\u000b\u000f_)ø\"\u0016\u0087\u0007\u0015úwÛÜP\u0096ç\u0098\u0081\u009c³Ð\u000buUAUZC½b\u0017£h\u0003\u008b\u0093Â\u0001\u009e:öÃÛgß\u001d\u008b\u007f¥¾$D,\u000f\u0010U\u0089\u001cí»vx²\u0091\u0097%bÊø\u0006Õ:Q&Z¦Ú{¤\u008eÜ$q\u001dË\u0095&2:\u0015Ph¿Fø÷¡\u0010ÕvþZ\u001f¯IH\u001b\u0014sG\u009b\u001f';?\u0084DQ\u0012\u008c|jFàç>ÈwLJ\u0099R¨£\u008bE!c\u009b\u001fT¸Ó1ø\u0082I{¼\u0095\u001dt*4W\u008aEË°^ãòÔ]ù\u0099(\u001e \\Ý¶ër²s\u0018ø@òãÒù`Ý°\u0019'_lì\u000fT³7(¨\u0090ât£2\u0098\u00adì\u0001\u0089ê\u0094ÈÆG \u008d½\u0084w1v©høx\r%\u0095ùø\bÊP\u008cjÌÇ[á!õ8¨V·\u008fÒ7\u000f\u0080\u0084f³\b\u0091ò6{\u0097àTl×9æCâ²l\u0004b6\u00ad\u0090PJ(p\u0088àbë\fâ\u000bw&OLawÆ«\u0005\u001f¼¬\u0090×ýL\u001d\u000b\u008f\u007f='\u009f¨j\u0093³æ¯Í\u0094í¦'´êëñU\ry \u0007Æ\nñ\u0088\u000bÉ\u0096 ·^\u000bBÃ\u0087m·l¡p>nÄÐ\u0086[\u0000!yüXPéÎ)õ£_-Óhµ³\u0083_Ä4\u00adî@\u0016\u009bÒ,NeÍ7\u0007|ÆaE£{w\u0093ý:¥=\u00ad½Às'Rçó\u0091\u0003=F·\u0092\u00948\u0003\u0002ç$²5ÀöþeXö@dõZu¥¾|i";
                        int var5 = "ºúÙ¥\u0010¹®\u001dÇô5\u009dh\u0005ù&O\u0083:3ê\u0010vc\u000b¨\r·\u0083YÙ¿Ft\u008f\u001dw\u0018»faåâÏ \u0097}I´ÿ.ìýU\u0002k²\u0087\u001f\u009f¦Ô;ÐT\u0005ãÑ£\u001bÞSµê¸\u00ad\u0011\u0012¢sçuíp~©%'Æb¼ôy\u0091B\u0097HÁ\t\u009f\u009e\n\u0091\u000e\u0004û\u008b[Ù3\u0080%¿\u001f_\u0098UnÍÐ¯c2\u00813»\u000fÌÅÿ\u0085m'Û\f\u0092Cî±6E\u0088\u000b\u000f_)ø\"\u0016\u0087\u0007\u0015úwÛÜP\u0096ç\u0098\u0081\u009c³Ð\u000buUAUZC½b\u0017£h\u0003\u008b\u0093Â\u0001\u009e:öÃÛgß\u001d\u008b\u007f¥¾$D,\u000f\u0010U\u0089\u001cí»vx²\u0091\u0097%bÊø\u0006Õ:Q&Z¦Ú{¤\u008eÜ$q\u001dË\u0095&2:\u0015Ph¿Fø÷¡\u0010ÕvþZ\u001f¯IH\u001b\u0014sG\u009b\u001f';?\u0084DQ\u0012\u008c|jFàç>ÈwLJ\u0099R¨£\u008bE!c\u009b\u001fT¸Ó1ø\u0082I{¼\u0095\u001dt*4W\u008aEË°^ãòÔ]ù\u0099(\u001e \\Ý¶ër²s\u0018ø@òãÒù`Ý°\u0019'_lì\u000fT³7(¨\u0090ât£2\u0098\u00adì\u0001\u0089ê\u0094ÈÆG \u008d½\u0084w1v©høx\r%\u0095ùø\bÊP\u008cjÌÇ[á!õ8¨V·\u008fÒ7\u000f\u0080\u0084f³\b\u0091ò6{\u0097àTl×9æCâ²l\u0004b6\u00ad\u0090PJ(p\u0088àbë\fâ\u000bw&OLawÆ«\u0005\u001f¼¬\u0090×ýL\u001d\u000b\u008f\u007f='\u009f¨j\u0093³æ¯Í\u0094í¦'´êëñU\ry \u0007Æ\nñ\u0088\u000bÉ\u0096 ·^\u000bBÃ\u0087m·l¡p>nÄÐ\u0086[\u0000!yüXPéÎ)õ£_-Óhµ³\u0083_Ä4\u00adî@\u0016\u009bÒ,NeÍ7\u0007|ÆaE£{w\u0093ý:¥=\u00ad½Às'Rçó\u0091\u0003=F·\u0092\u00948\u0003\u0002ç$²5ÀöþeXö@dõZu¥¾|i"
                           .length();
                        int var2 = 0;

                        label40:
                        while (true) {
                           var10001 = var2;
                           var2 += 8;
                           byte[] var7 = var4.substring(var10001, var2).getBytes("ISO-8859-1");
                           long[] var32 = var6;
                           var10001 = var3++;
                           long var44 = (var7[0] & 255L) << 56
                              | (var7[1] & 255L) << 48
                              | (var7[2] & 255L) << 40
                              | (var7[3] & 255L) << 32
                              | (var7[4] & 255L) << 24
                              | (var7[5] & 255L) << 16
                              | (var7[6] & 255L) << 8
                              | var7[7] & 255L;
                           int var47 = -1;

                           while (true) {
                              long var8 = var44;
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
                              long var49 = (var10[0] & 255L) << 56
                                 | (var10[1] & 255L) << 48
                                 | (var10[2] & 255L) << 40
                                 | (var10[3] & 255L) << 32
                                 | (var10[4] & 255L) << 24
                                 | (var10[5] & 255L) << 16
                                 | (var10[6] & 255L) << 8
                                 | var10[7] & 255L;
                              switch (var47) {
                                 case 0:
                                    var32[var10001] = var49;
                                    if (var2 >= var5) {
                                       g = var6;
                                       COLOR_CHAR = (char)167;
                                       zkm$v20 = var20;
                                       zkm$v22 = var22;
                                       zkm$v24 = var24;
                                       return;
                                    }
                                    break;
                                 default:
                                    var32[var10001] = var49;
                                    if (var2 < var5) {
                                       continue label40;
                                    }

                                    var4 = "÷Äh\u009bÛá\u001bxÉ9\u001eSâú½C";
                                    var5 = "÷Äh\u009bÛá\u001bxÉ9\u001eSâú½C".length();
                                    var2 = 0;
                              }

                              int var38 = var2;
                              var2 += 8;
                              var7 = var4.substring(var38, var2).getBytes("ISO-8859-1");
                              var32 = var6;
                              var10001 = var3++;
                              var44 = (var7[0] & 255L) << 56
                                 | (var7[1] & 255L) << 48
                                 | (var7[2] & 255L) << 40
                                 | (var7[3] & 255L) << 32
                                 | (var7[4] & 255L) << 24
                                 | (var7[5] & 255L) << 16
                                 | (var7[6] & 255L) << 8
                                 | var7[7] & 255L;
                              var47 = 0;
                           }
                        }
                     }

                     var14 = var15.charAt(var28);
                     break;
                  default:
                     var18[var16++] = var40;
                     if ((var28 += var14) < var17) {
                        var14 = var15.charAt(var28);
                        continue label58;
                     }

                     var15 = "\u001d=¨mW%Æ\u009dØtÉ|êp\u000bé\u0010\u0005A(¢M?ì'\u0097kä+Ô$¦C";
                     var17 = "\u001d=¨mW%Æ\u009dØtÉ|êp\u000bé\u0010\u0005A(¢M?ì'\u0097kä+Ô$¦C".length();
                     var14 = 16;
                     var28 = -1;
               }

               var29 = var15.substring(++var28, var28 + var14);
               var10001 = 0;
            }
         }
      } catch (UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException var25) {
         throw new RuntimeException(var25);
      }
   }


   private static long zkm$g22() {
      if (!zkm$done) {
         zkm$done = true;
         zkm$pre();
      }

      return zkm$v22;
   }

   private static int zkm$g24() {
      if (!zkm$done) {
         zkm$done = true;
         zkm$pre();
      }

      return zkm$v24;
   }

   // add code
   private static long zkm$ga() {
      if (!zkm$done) {
         zkm$done = true;
         zkm$pre();
      }

      return a;
   }
}
