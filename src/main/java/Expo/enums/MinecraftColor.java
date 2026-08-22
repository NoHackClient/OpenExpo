package Expo.enums;

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

public enum MinecraftColor {
   BLACK((char)48, -16777216),
   DARK_BLUE((char)49, -16777046),
   DARK_GREEN((char)50, -16733696),
   DARK_AQUA((char)51, -16733526),
   DARK_RED((char)52, -5636096),
   DARK_PURPLE((char)53, -5635926),
   GOLD((char)54, -22016),
   GRAY((char)55, -5592406),
   DARK_GRAY((char)56, -11184811),
   BLUE((char)57, -11184641),
   GREEN((char)97, -11141291),
   AQUA((char)98, -11141121),
   RED((char)99, -43691),
   LIGHT_PURPLE((char)100, -43521),
   YELLOW((char)101, -171),
   WHITE((char)102, -1),
   MAGIC((char)107, 0),
   BOLD((char)108, 0),
   STRIKETHROUGH((char)109, 0),
   UNDERLINE((char)110, 0),
   ITALIC((char)111, 0),
   RESET((char)114, 0);

   public static char COLOR_CHAR;
   private static long[] e;
   private static Map g;
   private static String[] c;
   private final char G;
   private final int I;
   private static long a;
   private static Map d;
   private static String[] b;
   private static long zkm$v20;

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

   static {
      MinecraftColor[] var10000 = new MinecraftColor[22];
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

   public String S() {
      return new String(new char[]{(char)167, this.G});
   }

   public static String C(String var0) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      char[] var3 = var0.toCharArray();

      for (int var4 = 0; var4 < var3.length - 1; var4++) {
         if (var3[var4] == 38 && "0123456789AaBbCcDdEeFfKkLlMmNnOoRr".indexOf(var3[var4 + 1]) > -1) {
            var3[var4] = (char)167;
            var3[var4 + 1] = Character.toLowerCase(var3[var4 + 1]);
         }
      }

      return new String(var3);
   }

   public String toString() {
      return this.S();
   }

   MinecraftColor(char var3, int var4) {
      this.G = var3;
      this.I = var4;
   }

   public int U() {
      return this.I;
   }

   private static void zkm$pre() {
      try {
         a = 41151624044926L;
         long var20 = a ^ 23324507870019L;
         d = new HashMap(13);
         Cipher var11;
         byte[] var10003 = new byte[]{(byte)(var20 >>> 56), 0, 0, 0, 0, 0, 0, 0};

         for (int var12 = 1; var12 < 8; var12++) {
            var10003[var12] = (byte)(var20 << var12 * 8 >>> 56);
         }

         (var11 = Cipher.getInstance("DES/CBC/PKCS5Padding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
         String[] var18 = new String[23];
         int var16 = 0;
         String var15 = "¾\u0086ö? qI§°\u0018ò+Ñà\u0089® Jé\u009d\u0012X_'\u0013ê§\u00988\u001fÖã\u008cf\u0005É}Ï\u008fzÖ«æ\u0080\tä\u008dWA \u00ad[ÅC5¢\r\u008a\u0019¬º\u001e\u0010»öåöÒûâ~U-µ²éO7&Ý\u0083V\u0010¡^@\u0018îì\u008d/.Iü\u0098\u009cfÖm\u0018d\u000f\u00ad\\\u0007\u0084\u0014\u0094Èr@lx\u008f¶±PìÚ!O52\u000b@J\u0087A'ÆéêæN¶]ë\u0003¶æÄp\u0088i;yñc¤Î\u0082hsÙ%õ\u001dâ\u0012ó\u001b\n\\ÌI!ËÉ\rè\u001c¤t\u0010*\b\u000f\u0091\u0019ÎÂB\u0005 µH\u0018)U\u0010:áÕF\u0095Ð v\u00050c\u0095Ñ±Æ\\\u0010)§Ýö7)ã\u00958\u0099\u0001Éå+ª¥\u0010¨º\u0099\u0017Ð\u0095\u0019ñOí-\u009c_\u009bÏ(\u0010ù¤v;\u008c><\nÐOûtÉ\u009d@® 4\u0097\u0000\u0013p\u008e'Ã¤ª\u0095d\u0085x\u009c\u009b+qìÍZÐèY\u0014P¯ûý\u000f\u009eë\u00185_\u009fª*^ªÇè6Óö\"SÏÂÚh`ël(\u0086¡ ;³\u0011\u0097E\u000fÄ\u007f\n\u001c\u009exÒE¹\u0000i1.\u009dc\u0005\u0083\u000ey2ìÅ\u0010¾ùy\u0018\u0014û6%ª\u0099æ#UHÎ©\u0013´gXÔÍ\u0099\u0007$#G=\u0010\u00046\u0016\u0090p\u001cãÄp6\u0012ÃýD¥Ä\u0010ZcxÝF\u0007\u001a<¡V\u00918\u0097Ù I\u00104\"U@÷\u0096ða±6vÙlè\u0007\u008c \u0018[AKU8cæW»\u009aB´]Ù^Ol\u0094°\u009d\u008f7ÏèíðXö&¨¡\u0010c&A«Ña¡\u008e\u001e\u0015\u001b)X\u009dE\u0003\u0010dò9>x\u008ai\u009dR\u0098âu´Âÿr ôË î\u0007\u008dÜ|^\u0092@uÇ\u001fã\u0096\u0015ÐâÎ\t·\u001c^F\u0095a6\u008aÞR\u0010";
         int var17 = "¾\u0086ö? qI§°\u0018ò+Ñà\u0089® Jé\u009d\u0012X_'\u0013ê§\u00988\u001fÖã\u008cf\u0005É}Ï\u008fzÖ«æ\u0080\tä\u008dWA \u00ad[ÅC5¢\r\u008a\u0019¬º\u001e\u0010»öåöÒûâ~U-µ²éO7&Ý\u0083V\u0010¡^@\u0018îì\u008d/.Iü\u0098\u009cfÖm\u0018d\u000f\u00ad\\\u0007\u0084\u0014\u0094Èr@lx\u008f¶±PìÚ!O52\u000b@J\u0087A'ÆéêæN¶]ë\u0003¶æÄp\u0088i;yñc¤Î\u0082hsÙ%õ\u001dâ\u0012ó\u001b\n\\ÌI!ËÉ\rè\u001c¤t\u0010*\b\u000f\u0091\u0019ÎÂB\u0005 µH\u0018)U\u0010:áÕF\u0095Ð v\u00050c\u0095Ñ±Æ\\\u0010)§Ýö7)ã\u00958\u0099\u0001Éå+ª¥\u0010¨º\u0099\u0017Ð\u0095\u0019ñOí-\u009c_\u009bÏ(\u0010ù¤v;\u008c><\nÐOûtÉ\u009d@® 4\u0097\u0000\u0013p\u008e'Ã¤ª\u0095d\u0085x\u009c\u009b+qìÍZÐèY\u0014P¯ûý\u000f\u009eë\u00185_\u009fª*^ªÇè6Óö\"SÏÂÚh`ël(\u0086¡ ;³\u0011\u0097E\u000fÄ\u007f\n\u001c\u009exÒE¹\u0000i1.\u009dc\u0005\u0083\u000ey2ìÅ\u0010¾ùy\u0018\u0014û6%ª\u0099æ#UHÎ©\u0013´gXÔÍ\u0099\u0007$#G=\u0010\u00046\u0016\u0090p\u001cãÄp6\u0012ÃýD¥Ä\u0010ZcxÝF\u0007\u001a<¡V\u00918\u0097Ù I\u00104\"U@÷\u0096ða±6vÙlè\u0007\u008c \u0018[AKU8cæW»\u009aB´]Ù^Ol\u0094°\u009d\u008f7ÏèíðXö&¨¡\u0010c&A«Ña¡\u008e\u001e\u0015\u001b)X\u009dE\u0003\u0010dò9>x\u008ai\u009dR\u0098âu´Âÿr ôË î\u0007\u008dÜ|^\u0092@uÇ\u001fã\u0096\u0015ÐâÎ\t·\u001c^F\u0095a6\u008aÞR\u0010"
            .length();
         char var14 = 16;
         int var25 = -1;

         label58:
         while (true) {
            String var26 = var15.substring(++var25, var25 + var14);
            int var10001 = -1;

            while (true) {
               byte[] var19 = var11.doFinal(var26.getBytes("ISO-8859-1"));
               String var37 = a(var19).intern();
               switch (var10001) {
                  case 0:
                     var18[var16++] = var37;
                     if ((var25 += var14) >= var17) {
                        b = var18;
                        c = new String[23];
                        g = new HashMap(13);
                        Cipher var0;
                        var10003 = new byte[]{(byte)(var20 >>> 56), 0, 0, 0, 0, 0, 0, 0};

                        for (int var1 = 1; var1 < 8; var1++) {
                           var10003[var1] = (byte)(var20 << var1 * 8 >>> 56);
                        }

                        (var0 = Cipher.getInstance("DES/CBC/NoPadding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
                        long[] var6 = new long[74];
                        int var3 = 0;
                        String var4 = "zÙÿ\u008eáû¢¨Ð\r5\"À\u0091¦\u009auK\u0089´^ÅØ»x*\u001a\u000flÌ\u009e#68\u001fÉ@\u009eTqÅ\u0014-*§4ãk;öÄ8c\u0081µ@ó\n \u008ceñ`Ù¦³\u008dv\u001b²Xïü)m¢\u0094\u008b¶òW<aü\u008eÀ:¤R$\u008f\u0000¢4/7²äºaSpÈj?\u008ahNCP\u008fWçë3«\r#ú~\u0090+\u0086\u001a\u00960½Ã\u0099½¦øá\u009c\u009b\u008e2^^\u0084\u0087Ë\u0097Ùm¼\u009dº3úe\u0017AXßÍìl\u00999îp\u0019Î\u000fk×\u0087\fá\bÆT\u0092\u0082=¹1\u008fÒV't:yl\u0092\u009e\u0092©å}¨\u0006×\u008fq=\u0012Ã0â\\ýEøÊå©\u0086\u009døÏí.\nøøâÁ¯\u0014yÜõ\u0080È}ÊM\u0011åF-`EA±Ø\u0014\\*ýYÞÁQ2W\u001b\u0097\u0087\u001cëXý¯Ê\u0081\u0000}\u009a\u001dmTÝAY2ï\u009eI\u008f´\u0018®è\u008eådû\u001d'\"òuÝ\u0084Ø?0Mph\u0089>&T\u0084\u000f\u001dk\u0080h'yüI\u0005ßüñ\u0089õP\u0003ñÐßh;væ ä\u008d\u001b\u0088Q£\u001c\u0002\u0011`r.~¯\u0016ßF\b¯i8OYÑÕh\u009f\u009bNí\u0091A/¤¶=â\u0003\u0086äÂ\u0097\u00846Å\u00ad°ê\u0086\u0010¦À\u000f:§:q7vþÀ%\u0090²¥\u0016\u0006Z\u0099ÏL®'|ÿ¹\n\u0096x(@ý\u0089n×çýÍÑlìxMh\u00ad¥I±\u008aá±È\u009bÕãS³°Y\u0094Ò·ê6\u0096s\u0081Îí\rRlh\u0002\u001eVµÃ\u0084\u007fãhûÔö1Î!fÎ\u0095\u0015ú\u0011¾\u0018\"ùË4t¡C>JÎýo´!\\#t½º²\u0019\u0083^\u008c\u0017SÖ-\u0094¦ÌÃÐg&\u0086p\"PÅ~\u001f¼\u0091~´n»R\u001fÏ\u0002£À±G\u0099\u0090\t\u009e0ñ\u009f,\u008eê\u0000ëRQxÊF\u0018^o¢å2mÖ¼ý\u00153µ>H\u0083Ñ\u001b\u009c.t¾3VÍ\u0083r\u0093\u0083Àq>æ";
                        int var5 = "zÙÿ\u008eáû¢¨Ð\r5\"À\u0091¦\u009auK\u0089´^ÅØ»x*\u001a\u000flÌ\u009e#68\u001fÉ@\u009eTqÅ\u0014-*§4ãk;öÄ8c\u0081µ@ó\n \u008ceñ`Ù¦³\u008dv\u001b²Xïü)m¢\u0094\u008b¶òW<aü\u008eÀ:¤R$\u008f\u0000¢4/7²äºaSpÈj?\u008ahNCP\u008fWçë3«\r#ú~\u0090+\u0086\u001a\u00960½Ã\u0099½¦øá\u009c\u009b\u008e2^^\u0084\u0087Ë\u0097Ùm¼\u009dº3úe\u0017AXßÍìl\u00999îp\u0019Î\u000fk×\u0087\fá\bÆT\u0092\u0082=¹1\u008fÒV't:yl\u0092\u009e\u0092©å}¨\u0006×\u008fq=\u0012Ã0â\\ýEøÊå©\u0086\u009døÏí.\nøøâÁ¯\u0014yÜõ\u0080È}ÊM\u0011åF-`EA±Ø\u0014\\*ýYÞÁQ2W\u001b\u0097\u0087\u001cëXý¯Ê\u0081\u0000}\u009a\u001dmTÝAY2ï\u009eI\u008f´\u0018®è\u008eådû\u001d'\"òuÝ\u0084Ø?0Mph\u0089>&T\u0084\u000f\u001dk\u0080h'yüI\u0005ßüñ\u0089õP\u0003ñÐßh;væ ä\u008d\u001b\u0088Q£\u001c\u0002\u0011`r.~¯\u0016ßF\b¯i8OYÑÕh\u009f\u009bNí\u0091A/¤¶=â\u0003\u0086äÂ\u0097\u00846Å\u00ad°ê\u0086\u0010¦À\u000f:§:q7vþÀ%\u0090²¥\u0016\u0006Z\u0099ÏL®'|ÿ¹\n\u0096x(@ý\u0089n×çýÍÑlìxMh\u00ad¥I±\u008aá±È\u009bÕãS³°Y\u0094Ò·ê6\u0096s\u0081Îí\rRlh\u0002\u001eVµÃ\u0084\u007fãhûÔö1Î!fÎ\u0095\u0015ú\u0011¾\u0018\"ùË4t¡C>JÎýo´!\\#t½º²\u0019\u0083^\u008c\u0017SÖ-\u0094¦ÌÃÐg&\u0086p\"PÅ~\u001f¼\u0091~´n»R\u001fÏ\u0002£À±G\u0099\u0090\t\u009e0ñ\u009f,\u008eê\u0000ëRQxÊF\u0018^o¢å2mÖ¼ý\u00153µ>H\u0083Ñ\u001b\u009c.t¾3VÍ\u0083r\u0093\u0083Àq>æ"
                           .length();
                        int var2 = 0;

                        label40:
                        while (true) {
                           var10001 = var2;
                           var2 += 8;
                           byte[] var7 = var4.substring(var10001, var2).getBytes("ISO-8859-1");
                           long[] var29 = var6;
                           var10001 = var3++;
                           long var41 = (var7[0] & 255L) << 56
                              | (var7[1] & 255L) << 48
                              | (var7[2] & 255L) << 40
                              | (var7[3] & 255L) << 32
                              | (var7[4] & 255L) << 24
                              | (var7[5] & 255L) << 16
                              | (var7[6] & 255L) << 8
                              | var7[7] & 255L;
                           int var44 = -1;

                           while (true) {
                              long var8 = var41;
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
                              long var46 = (var10[0] & 255L) << 56
                                 | (var10[1] & 255L) << 48
                                 | (var10[2] & 255L) << 40
                                 | (var10[3] & 255L) << 32
                                 | (var10[4] & 255L) << 24
                                 | (var10[5] & 255L) << 16
                                 | (var10[6] & 255L) << 8
                                 | var10[7] & 255L;
                              switch (var44) {
                                 case 0:
                                    var29[var10001] = var46;
                                    if (var2 >= var5) {
                                       e = var6;
                                       COLOR_CHAR = (char)167;
                                       zkm$v20 = var20;
                                       return;
                                    }
                                    break;
                                 default:
                                    var29[var10001] = var46;
                                    if (var2 < var5) {
                                       continue label40;
                                    }

                                    var4 = "Ìñ\u0002\u0087\u0085Wí\u009b,\u0011$ÃË\u0094e\u0011";
                                    var5 = "Ìñ\u0002\u0087\u0085Wí\u009b,\u0011$ÃË\u0094e\u0011".length();
                                    var2 = 0;
                              }

                              int var35 = var2;
                              var2 += 8;
                              var7 = var4.substring(var35, var2).getBytes("ISO-8859-1");
                              var29 = var6;
                              var10001 = var3++;
                              var41 = (var7[0] & 255L) << 56
                                 | (var7[1] & 255L) << 48
                                 | (var7[2] & 255L) << 40
                                 | (var7[3] & 255L) << 32
                                 | (var7[4] & 255L) << 24
                                 | (var7[5] & 255L) << 16
                                 | (var7[6] & 255L) << 8
                                 | var7[7] & 255L;
                              var44 = 0;
                           }
                        }
                     }

                     var14 = var15.charAt(var25);
                     break;
                  default:
                     var18[var16++] = var37;
                     if ((var25 += var14) < var17) {
                        var14 = var15.charAt(var25);
                        continue label58;
                     }

                     var15 = "ç,ÑÆCKn\u008b\u009d\u00ad(\t#\u0010JR\u0010\u0098)ÚÕn\u0085\n\u0092ý\u001bäh[Â\u0016h";
                     var17 = "ç,ÑÆCKn\u008b\u009d\u00ad(\t#\u0010JR\u0010\u0098)ÚÕn\u0085\n\u0092ý\u001bäh[Â\u0016h".length();
                     var14 = 16;
                     var25 = -1;
               }

               var26 = var15.substring(++var25, var25 + var14);
               var10001 = 0;
            }
         }
      } catch (UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException var22) {
         throw new RuntimeException(var22);
      }
   }
}
