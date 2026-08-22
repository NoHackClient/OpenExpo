package Expo.enums;

import Expo.util.MinecraftRef;
import Expo.util.TeamPrefixUtil;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import net.minecraft.client.Minecraft;
import net.minecraft.scoreboard.ScorePlayerTeam;


public enum MegaWallsClass {
   ANGEL("ANG", (int)zkm$g0()[8]),
   ARCANIST("ARC", (int)zkm$g0()[27]),
   ASSASSIN("ASN", 0),
   AUTOMATON("ATN", (int)zkm$g0()[28]),
   BLAZE("BLA", (int)zkm$g0()[27]),
   COW("COW", (int)zkm$g0()[0]),
   CREEPER("CRE", (int)zkm$g0()[27]),
   DRAGON("DRG", (int)zkm$g0()[27]),
   DREADLORD("DRE", (int)zkm$g0()[27]),
   ENDERMAN("END", (int)zkm$g0()[27]),
   GOLEM("GOL", 0),
   HEROBRINE("HBR", (int)zkm$g0()[7]),
   HUNTER("HUN", 0),
   MOLEMAN("MOL", (int)zkm$g0()[27]),
   PHOENIX("PHX", (int)zkm$g0()[27]),
   PIGMAN("PIG", (int)zkm$g0()[14]),
   PIRATE("PIR", 0),
   RENEGADE("REN", (int)zkm$g0()[27]),
   SHAMAN("SHA", (int)zkm$g0()[27]),
   SHARK("SRK", (int)zkm$g0()[27]),
   SHEEP("SHP", (int)zkm$g0()[27]),
   SKELETON("SKE", (int)zkm$g0()[27]),
   SNOWMAN("SNO", (int)zkm$g0()[27]),
   SPIDER("SPI", (int)zkm$g0()[27]),
   SQUID("SQU", (int)zkm$g0()[7]),
   WEREWOLF("WER", (int)zkm$g0()[14]),
   ZOMBIE("ZOM", (int)zkm$g0()[14]);

   private static long a;
   public final int healthPotionAmount;
   private static final Minecraft K = MinecraftRef.c((byte)zkm$g22(), zkm$g23());
   public final String tag;
   private static Map d;
   private static final Map<String, MegaWallsClass> v = new HashMap<>();
   public final String className;
   private static String[] b;
   private static String[] c;
   private static boolean zkm$done;
   private static long[] zkm$v0;
   private static int zkm$v22;
   private static long zkm$v23;

   public boolean U() {
      return this.healthPotionAmount > 0;
   }

   static {
      MegaWallsClass[] var10000 = new MegaWallsClass[(int)zkm$g0()[39]];
      var10000[0] = ANGEL;
      var10000[1] = ARCANIST;
      var10000[2] = ASSASSIN;
      var10000[3] = AUTOMATON;
      var10000[4] = BLAZE;
      var10000[5] = COW;
      var10000[(int)zkm$g0()[7]] = CREEPER;
      var10000[(int)zkm$g0()[13]] = DRAGON;
      var10000[(int)zkm$g0()[27]] = DREADLORD;
      var10000[(int)zkm$g0()[40]] = ENDERMAN;
      var10000[(int)zkm$g0()[14]] = GOLEM;
      var10000[(int)zkm$g0()[5]] = HEROBRINE;
      var10000[(int)zkm$g0()[2]] = HUNTER;
      var10000[(int)zkm$g0()[6]] = MOLEMAN;
      var10000[(int)zkm$g0()[9]] = PHOENIX;
      var10000[(int)zkm$g0()[33]] = PIGMAN;
      var10000[(int)zkm$g0()[37]] = PIRATE;
      var10000[(int)zkm$g0()[16]] = RENEGADE;
      var10000[(int)zkm$g0()[26]] = SHAMAN;
      var10000[(int)zkm$g0()[12]] = SHARK;
      var10000[(int)zkm$g0()[32]] = SHEEP;
      var10000[(int)zkm$g0()[30]] = SKELETON;
      var10000[(int)zkm$g0()[38]] = SNOWMAN;
      var10000[(int)zkm$g0()[22]] = SPIDER;
      var10000[(int)zkm$g0()[34]] = SQUID;
      var10000[(int)zkm$g0()[17]] = WEREWOLF;
      var10000[(int)zkm$g0()[10]] = ZOMBIE;

      for (MegaWallsClass var28 : values()) {
         v.put(var28.tag, var28);
      }
   }

   public static MegaWallsClass q(char var0, char var1, String var3) {
      return var3 == null ? null : o(TeamPrefixUtil.u(var3).replaceAll("[\\[\\]\\s]", ""));
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

   public static MegaWallsClass s(String var0, long var1) {
      var1 = a ^ var1;
      int var3 = (int)((var1 ^ 115970042848177L) >>> 48);
      int var4 = (int)((var1 ^ 115970042848177L) << 16 >>> 48);
      if (K.theWorld == null) {
         return null;
      }

      ScorePlayerTeam var6 = K.theWorld.getScoreboard().getPlayersTeam(var0);
      return var6 == null ? null : q((char)var3, (char)var4, var6.getColorSuffix());
   }


   public static MegaWallsClass o(String var0) {
      return v.get(var0);
   }

   public static MegaWallsClass a(String var0) {
      for (MegaWallsClass var4 : values()) {
         if (var0.equalsIgnoreCase(var4.tag) || var0.equalsIgnoreCase(var4.className)) {
            return var4;
         }
      }

      return null;
   }

   MegaWallsClass(String var3, int var4) {
      this.tag = var3;
      String var5 = this.name().toLowerCase(Locale.ROOT);
      this.className = Character.toUpperCase(var5.charAt(0)) + var5.substring(1);
      this.healthPotionAmount = var4;
   }

   private static void zkm$pre() {
      try {
         a = 10052156246884L;
         long var20 = a ^ 118287392274870L;
         int var22 = (int)((var20 ^ 48108953139998L) >>> 56);
         long var23 = (var20 ^ 48108953139998L) << 8 >>> 8;
         d = new HashMap(13);
         Cipher var11;
         byte[] var10003 = new byte[]{(byte)(var20 >>> 56), 0, 0, 0, 0, 0, 0, 0};

         for (int var12 = 1; var12 < 8; var12++) {
            var10003[var12] = (byte)(var20 << var12 * 8 >>> 56);
         }

         (var11 = Cipher.getInstance("DES/CBC/PKCS5Padding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
         String[] var18 = new String[55];
         int var16 = 0;
         String var15 = "st¡ÖÈ¶\u0093\u001b #Nr_ê\u00890i\u0093$µ]\u0015ÄH àP¥&0\u0088VÕ´¯Ö^ÉCÔ·\u0098ÍÛ3Ï¾2v+tÔe\u0012Cþ|\u0010\u001c/Z¹\u008eÊ°\u009eá&E±õ\u0014Âý\u0018l\u000eÍ\u000f\rªÿäX¢ªÏ\u0005\u000f\u009bSÃõ\u0003y\u001e9bG\u00107\u001a¯Ôñ\u008b5äÀ$5$\u008dGf\"\u0010\u000f\u0094\u0006\"«»9\u000bïpìL[î\fX\u0010d\u0092¥Vß\u0097ñ>r^\u009cG\bÕ\u001cÒ ÿV\u0089³ïÂ·r\u001cå#7Æ¾Vü0(Ìê½È\u009c\u0006\u001b\u0080r'C\u0017_Õ\u0010\u0004\u0089\u0003Hi/â\f-Ó¥47\u0000U`\u0010\u0002¦\u0094\u0096\u0084Væ0s4 õ\u009a\u0087\u0007\u008a\u0010°\u009a\u0016\u0006\u001c\u009aKLJô\u009b\u0017L\u0085Ö\u008f\u0010m\n\u008b^¾bT×øAh\u0017G^+Ü\u0010`ù\u0007³\u001fó\u0001\u0014§ò[\u0007\u001cÌ%\u0006\u0010U#\u0082¥Û)Ç´ðíÝÜdjtð\u0010½\u0099%~ZÄ'á:9<\u0005\u0000\u0087Ü\u008d\u0010\u0017Ü\u009fÑ9±Ü«Âx\u0007±\u001bëâ\u0083\u0010qw\u008b»\u0089ÓÞ´P\u00ad\u0003Ë\u0097?Ýk\u0010º&2\u0089L\u0000ÆFÓ|®$0\u0083\u0003{\u0010¯úèN\u001fm +,\u009bÇ\u007f\u0091ÉÑz\u0010\u0081DäE¤ÐæÊ\u008b¡ÔÚ[àp\u0080\u0010é\u0098×\u008d\u00adg8\u0002Ç(/0\u008d±BÄ\u0010Ãao¨&'\u008b¸\u00849¦D\u0085ë²G\u0010\u0085V\u0096\u0091\u0081Üs`ò\u0080\u0094\u0005+5\u007f=\u0010Í\u0093S=\tTz0E[Ù¹,ö\u0083Ë \"Ög«\u008cqï®¤¥\u009b\u00adòv¤#û^õê¿\u001dÕ;ÜÁ\u0099'r|¦À\u0010æ5\t\u0090àë\b|1¡¿¼\u001erý\u0094\u0010Ì$)Ü\u009f9_§áh\u0095tÇR×P\u0010Ài\u008cD§*\u0013\u0018~v\u0094OÅ\u0000{®\u0010ÏE¸¦.&\u0095ýÒR\u0004û\u009at\u001a3\u0010\u001bdÊ\u0097<è_D¾ûûnè\u00ad«\u0086\u0010ÆÀCFå]\u0018B\u0083.\u0012]ïÙ&\u0002\u00104\u0003\u009b\u009b\u0088ýÿAAîÑ\u0090Hó\b\r JaH1BC\u0094è;Ò83×£\t<Ç\u0082\u008b×FFÕK\u001b?,ÎÀ\fô\u0007\u0010PÀ,¬\u009b ÓAè¬\u00077D\u0004\\}\u0010i0\u0012\u0005²öAZ\u0099ÉUàk9¯ù C\u0092+¤\u0097\u001b\"\u000f`v\u0095§hA¬c!éiÏ\u0089\u0018=Uê\u001aó¥m>³Ã\u0010\u001cÜFM\u001b\u001aÏzJ¹ñ|.±\u0092\f\u0010rÿ9Úö<\u000f®%Å³w±\u001d\u0015É\u0010ÚÖU\u001aùÍêªÃMßjÆê\n\u0005\u0010\u0014ÄmaÏUú\u0082ÝbÌ\u0097£ÞÊ+\u0010\"\u0087\u0014G\u000b%ÀÞ0BçÐ:Q\u0080ô nE\u0094ó\"\u001bý?\u009a\u000ez½\"¥\u0083&\u0007óÕ\\N¦\u001f,¬w3¯\u008f\t¡}\u0018÷ âò»í0zó ~b±3\u0098hÜpuaZØ±6\u0010áPèü\u0017ë\u001cf5±Â\u0094\u009f\u0019\u001aH\u0010L\u0092\u0099t«ÕºàÑJ\u001aM\u0000Ì°{\u0018á\u009dºp\u0018©¢\u0019Ú\u0016Yò,Àù~\u001c\u0083/O\u0005Öo\u0014\u0010ï¤Ôôö\u0087ÃÓN¾Á\u0090?;à$\u0010Ô\u008fÑ\u0084GSÒ\u0096\u0005\u0006_l\u0095Fî\u0004\u0010¨ë±å\"\rqÛ*±\u0007«\u007fÚ\u001eô\u0010\u0089Ô´¬\u0001h\u00ad\u0098\u0010\u0005-Îä\u0099\u0091j\u0010y\u0088`.D1¶\u001a(ãúAû\u0011a\u0001\u0010\u0013Yh,ï+Ñ\u0018rÇ\u009c\u0086ÅD\u007fN\u0010!¸£ä\u009fD\u0093^3Q\u00141ü;'¥";
         int var17 = "st¡ÖÈ¶\u0093\u001b #Nr_ê\u00890i\u0093$µ]\u0015ÄH àP¥&0\u0088VÕ´¯Ö^ÉCÔ·\u0098ÍÛ3Ï¾2v+tÔe\u0012Cþ|\u0010\u001c/Z¹\u008eÊ°\u009eá&E±õ\u0014Âý\u0018l\u000eÍ\u000f\rªÿäX¢ªÏ\u0005\u000f\u009bSÃõ\u0003y\u001e9bG\u00107\u001a¯Ôñ\u008b5äÀ$5$\u008dGf\"\u0010\u000f\u0094\u0006\"«»9\u000bïpìL[î\fX\u0010d\u0092¥Vß\u0097ñ>r^\u009cG\bÕ\u001cÒ ÿV\u0089³ïÂ·r\u001cå#7Æ¾Vü0(Ìê½È\u009c\u0006\u001b\u0080r'C\u0017_Õ\u0010\u0004\u0089\u0003Hi/â\f-Ó¥47\u0000U`\u0010\u0002¦\u0094\u0096\u0084Væ0s4 õ\u009a\u0087\u0007\u008a\u0010°\u009a\u0016\u0006\u001c\u009aKLJô\u009b\u0017L\u0085Ö\u008f\u0010m\n\u008b^¾bT×øAh\u0017G^+Ü\u0010`ù\u0007³\u001fó\u0001\u0014§ò[\u0007\u001cÌ%\u0006\u0010U#\u0082¥Û)Ç´ðíÝÜdjtð\u0010½\u0099%~ZÄ'á:9<\u0005\u0000\u0087Ü\u008d\u0010\u0017Ü\u009fÑ9±Ü«Âx\u0007±\u001bëâ\u0083\u0010qw\u008b»\u0089ÓÞ´P\u00ad\u0003Ë\u0097?Ýk\u0010º&2\u0089L\u0000ÆFÓ|®$0\u0083\u0003{\u0010¯úèN\u001fm +,\u009bÇ\u007f\u0091ÉÑz\u0010\u0081DäE¤ÐæÊ\u008b¡ÔÚ[àp\u0080\u0010é\u0098×\u008d\u00adg8\u0002Ç(/0\u008d±BÄ\u0010Ãao¨&'\u008b¸\u00849¦D\u0085ë²G\u0010\u0085V\u0096\u0091\u0081Üs`ò\u0080\u0094\u0005+5\u007f=\u0010Í\u0093S=\tTz0E[Ù¹,ö\u0083Ë \"Ög«\u008cqï®¤¥\u009b\u00adòv¤#û^õê¿\u001dÕ;ÜÁ\u0099'r|¦À\u0010æ5\t\u0090àë\b|1¡¿¼\u001erý\u0094\u0010Ì$)Ü\u009f9_§áh\u0095tÇR×P\u0010Ài\u008cD§*\u0013\u0018~v\u0094OÅ\u0000{®\u0010ÏE¸¦.&\u0095ýÒR\u0004û\u009at\u001a3\u0010\u001bdÊ\u0097<è_D¾ûûnè\u00ad«\u0086\u0010ÆÀCFå]\u0018B\u0083.\u0012]ïÙ&\u0002\u00104\u0003\u009b\u009b\u0088ýÿAAîÑ\u0090Hó\b\r JaH1BC\u0094è;Ò83×£\t<Ç\u0082\u008b×FFÕK\u001b?,ÎÀ\fô\u0007\u0010PÀ,¬\u009b ÓAè¬\u00077D\u0004\\}\u0010i0\u0012\u0005²öAZ\u0099ÉUàk9¯ù C\u0092+¤\u0097\u001b\"\u000f`v\u0095§hA¬c!éiÏ\u0089\u0018=Uê\u001aó¥m>³Ã\u0010\u001cÜFM\u001b\u001aÏzJ¹ñ|.±\u0092\f\u0010rÿ9Úö<\u000f®%Å³w±\u001d\u0015É\u0010ÚÖU\u001aùÍêªÃMßjÆê\n\u0005\u0010\u0014ÄmaÏUú\u0082ÝbÌ\u0097£ÞÊ+\u0010\"\u0087\u0014G\u000b%ÀÞ0BçÐ:Q\u0080ô nE\u0094ó\"\u001bý?\u009a\u000ez½\"¥\u0083&\u0007óÕ\\N¦\u001f,¬w3¯\u008f\t¡}\u0018÷ âò»í0zó ~b±3\u0098hÜpuaZØ±6\u0010áPèü\u0017ë\u001cf5±Â\u0094\u009f\u0019\u001aH\u0010L\u0092\u0099t«ÕºàÑJ\u001aM\u0000Ì°{\u0018á\u009dºp\u0018©¢\u0019Ú\u0016Yò,Àù~\u001c\u0083/O\u0005Öo\u0014\u0010ï¤Ôôö\u0087ÃÓN¾Á\u0090?;à$\u0010Ô\u008fÑ\u0084GSÒ\u0096\u0005\u0006_l\u0095Fî\u0004\u0010¨ë±å\"\rqÛ*±\u0007«\u007fÚ\u001eô\u0010\u0089Ô´¬\u0001h\u00ad\u0098\u0010\u0005-Îä\u0099\u0091j\u0010y\u0088`.D1¶\u001a(ãúAû\u0011a\u0001\u0010\u0013Yh,ï+Ñ\u0018rÇ\u009c\u0086ÅD\u007fN\u0010!¸£ä\u009fD\u0093^3Q\u00141ü;'¥"
            .length();
         char var14 = 24;
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
                        b = var18;
                        c = new String[55];
                        Cipher var1;
                        var10003 = new byte[]{(byte)(var20 >>> 56), 0, 0, 0, 0, 0, 0, 0};

                        for (int var2 = 1; var2 < 8; var2++) {
                           var10003[var2] = (byte)(var20 << var2 * 8 >>> 56);
                        }

                        (var1 = Cipher.getInstance("DES/CBC/NoPadding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
                        long[] var0 = new long[43];
                        int var4 = 0;
                        String var5 = "\u001e/®\u008eiH¿Çv\u009dvñöTås¬\u0000\u0003cÝ_ê-\u009b_>\u009bËk\u001dÿ\u001c\u001c\u009d.u\u009d®\u0088û¥º£`M\u0015ýF#Ðd1ÁHW\u009d\róZ-\u0080¶ÅgvÑ\u0013Â`Q\u0005I@ç²Z¥ç\u008fF\u0007}ÙÇßlâ]À\u0094Ý\u0098By9DWÆrr\u0006ËWKÌè,ìL\u000bQ©ËlJ?ðL'À|.RÐyºÀ\u009e\u0011Y\u001dõRz9}\u0083×&Ìïd£\u0091Æ\u0080ç\u0093ù{è:\\GY±áû·ÌÖ\u0085|·\u000eñ\u0017ówpG\u0007¦ûO<L³Ú@~Vdpña'a\"Ü_7ÃÊ`¬\u0010\u0002Ö,ÿOäØ°à¶û¾VCWR\u0010Mª×9û\u0004\u009f4b\u0080WaÆ*\u0013 a¬ó2ýs\u008dqÜq\u0099-U\u000fôÅ\u0003¸mÃkÊ&\u0088\u000bë\u008a$2\u00882\u0096\u001c¬óÃ3\u009e÷\u008e½5o\u000eå\u0090ê)a¼Ô>\nÄ^\u0013©A\u009c:\u0092n¢\u0017ö¿5aÇu\ró¯G\u0095»Ø³®\u0006ãiFoI\u0086ùw\u008fn\u008bÌE¹&j¥";
                        int var6 = "\u001e/®\u008eiH¿Çv\u009dvñöTås¬\u0000\u0003cÝ_ê-\u009b_>\u009bËk\u001dÿ\u001c\u001c\u009d.u\u009d®\u0088û¥º£`M\u0015ýF#Ðd1ÁHW\u009d\róZ-\u0080¶ÅgvÑ\u0013Â`Q\u0005I@ç²Z¥ç\u008fF\u0007}ÙÇßlâ]À\u0094Ý\u0098By9DWÆrr\u0006ËWKÌè,ìL\u000bQ©ËlJ?ðL'À|.RÐyºÀ\u009e\u0011Y\u001dõRz9}\u0083×&Ìïd£\u0091Æ\u0080ç\u0093ù{è:\\GY±áû·ÌÖ\u0085|·\u000eñ\u0017ówpG\u0007¦ûO<L³Ú@~Vdpña'a\"Ü_7ÃÊ`¬\u0010\u0002Ö,ÿOäØ°à¶û¾VCWR\u0010Mª×9û\u0004\u009f4b\u0080WaÆ*\u0013 a¬ó2ýs\u008dqÜq\u0099-U\u000fôÅ\u0003¸mÃkÊ&\u0088\u000bë\u008a$2\u00882\u0096\u001c¬óÃ3\u009e÷\u008e½5o\u000eå\u0090ê)a¼Ô>\nÄ^\u0013©A\u009c:\u0092n¢\u0017ö¿5aÇu\ró¯G\u0095»Ø³®\u0006ãiFoI\u0086ùw\u008fn\u008bÌE¹&j¥"
                           .length();
                        int var3 = 0;

                        label40:
                        while (true) {
                           var10001 = var3;
                           var3 += 8;
                           byte[] var7 = var5.substring(var10001, var3).getBytes("ISO-8859-1");
                           long[] var32 = var0;
                           var10001 = var4++;
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
                              byte[] var10 = var1.doFinal(
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
                                    if (var3 >= var6) {
                                       zkm$v0 = var0;
                                       zkm$v22 = var22;
                                       zkm$v23 = var23;
                                       return;
                                    }
                                    break;
                                 default:
                                    var32[var10001] = var49;
                                    if (var3 < var6) {
                                       continue label40;
                                    }

                                    var5 = "B\u001eÙ<(0\u007ffáµ^Tè]¸:";
                                    var6 = "B\u001eÙ<(0\u007ffáµ^Tè]¸:".length();
                                    var3 = 0;
                              }

                              int var38 = var3;
                              var3 += 8;
                              var7 = var5.substring(var38, var3).getBytes("ISO-8859-1");
                              var32 = var0;
                              var10001 = var4++;
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

                     var15 = "\u0013T\u0090Ô©ätfÈè\u008eÛR¸È3\u0010úÍÉ¸VµoR\u0019«º6]\u0016\u007fM";
                     var17 = "\u0013T\u0090Ô©ätfÈè\u008eÛR¸È3\u0010úÍÉ¸VµoR\u0019«º6]\u0016\u007fM".length();
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

   private static long[] zkm$g0() {
      if (!zkm$done) {
         zkm$done = true;
         zkm$pre();
      }

      return zkm$v0;
   }

   private static int zkm$g22() {
      if (!zkm$done) {
         zkm$done = true;
         zkm$pre();
      }

      return zkm$v22;
   }

   private static long zkm$g23() {
      if (!zkm$done) {
         zkm$done = true;
         zkm$pre();
      }

      return zkm$v23;
   }
}
