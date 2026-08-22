package Expo.util;

import Expo.enums.MegaWallsClass;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.regex.Pattern;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Team;
import net.minecraft.util.StringUtils;











public class TeamPrefixUtil {
   private static Pattern j;
   private static long a;
   private static Minecraft A;

   private static String g(String var0) {
      return var0 == null ? "" : j.matcher(var0).replaceAll("");
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

   public static MegaWallsClass F(long var0, String var2) {


      return MegaWallsClass.s(var2, 126433336288858L);
   }

   static {
      // add code
      try {
         a = 33284923163101L;
         long var4 = a ^ 74951454775908L;
         int var6 = (int)((var4 ^ 21022205055605L) >>> 56);
         long var7 = (var4 ^ 21022205055605L) << 8 >>> 8;
         Cipher var1;
         byte[] var10003 = new byte[]{(byte)(var4 >>> 56), 0, 0, 0, 0, 0, 0, 0};

         for (int var2 = 1; var2 < 8; var2++) {
            var10003[var2] = (byte)(var4 << var2 * 8 >>> 56);
         }

         (var1 = Cipher.getInstance("DES/CBC/PKCS5Padding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
         byte[] var3 = var1.doFinal("\u001fµ\u0016°Ê\u0090ðj\u0097>7Éÿ\u0012´¸".getBytes("ISO-8859-1"));
         String var10 = a(var3).intern();
         byte var10001 = -1;
         String var0 = var10;
         A = MinecraftRef.c((byte)var6,0L);
         j = Pattern.compile(var0);
      } catch (UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException var9) {
         throw new RuntimeException(var9);
      }
   }

   public static boolean e() {
      return HypixelGameState.F();
   }

   public static String n(String var0) {
      if (A != null && A.getNetHandler() != null) {
         NetworkPlayerInfo var1 = A.getNetHandler().getPlayerInfo(var0);
         return var1 == null ? var0 : s(var1.getPlayerTeam(), var1.getGameProfile().getName());
      } else {
         return var0;
      }
   }

   public static boolean J() {
      return HypixelGameState.P();
   }

   public static boolean i() {
      return HypixelGameState.p();
   }

   private static String s(Team var0, String var1) {
      if (var0 == null) {
         return var1;
      } else {
         return var0 instanceof ScorePlayerTeam
            ? g(((ScorePlayerTeam)var0).getColorPrefix()) + var1 + ((ScorePlayerTeam)var0).getColorSuffix()
            : g(var0.formatString(var1));
      }
   }

   private TeamPrefixUtil() {
   }

   public static boolean z() {
      return HypixelGameState.L().O();
   }

   public static String u(String var0) {
      return var0 == null ? "" : StringUtils.stripControlCodes(var0);
   }

}
