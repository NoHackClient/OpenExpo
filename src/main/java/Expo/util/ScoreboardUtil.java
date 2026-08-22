package Expo.util;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import net.minecraft.client.Minecraft;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.StringUtils;

public class ScoreboardUtil {
   private static Map h;
   private static long a;
   private static String[] c;
   private static Minecraft f;
   private static String[] b;

   public static String h(String var0, long var1) {
      if (var0 == null) {
         return "";
      }

      for (int var4 = var0.length() - 1; var4 >= 0; var4--) {
         if (var0.charAt(var4) == 167 && var4 + 1 < var0.length()) {
            char var5 = var0.charAt(var4 + 1);
            int var6 = "0123456789abcdefABCDEF".indexOf(var5);
            if (var6 != -1) {
               return String.valueOf(
                  var6 < 16 ? var5 : "0123456789abcdefABCDEF".charAt(var6 - 6)
               );
            }
         }
      }

      return "";
   }

   public static String Z( short var2, String var3, String var4) {
      int var9 = var3 == null ? -1 : var3.indexOf(var4);
      return var9 == -1 ? "" : h(var3.substring(0, var9),0L);
   }

   private static List v(long var0) {
      ArrayList var2 = new ArrayList();
      if (f.theWorld == null) {
         return var2;
      }

      Scoreboard var3 = f.theWorld.getScoreboard();
      if (var3 == null) {
         return var2;
      }

      ScoreObjective var4 = var3.getObjectiveInDisplaySlot(1);
      if (var4 == null) {
         return var2;
      }

      ArrayList var5 = (ArrayList)(var3.getSortedScores(var4));
      ArrayList var6 = new ArrayList();

      for (Score var8 : (Iterable<Score>)(var5)) {
         if (var8 != null && var8.getPlayerName() != null && !var8.getPlayerName().startsWith("#")) {
            var6.add(var8);
         }
      }

      if (var6.size() > 15) {
         var5 = new ArrayList(Lists.newArrayList(Iterables.skip(var6, var6.size() - 15)));
      } else {
         var5 = var6;
      }

      int var13 = 0;

      for (Score var9 : (Iterable<Score>)(var5)) {
         var13++;
         ScorePlayerTeam var10 = var3.getPlayersTeam(var9.getPlayerName());
         var2.add(ScorePlayerTeam.formatPlayerName(var10, var9.getPlayerName()));
         if (var13 == var5.size()) {
            var2.add(var4.getDisplayName());
         }
      }

      Collections.reverse(var2);
      return var2;
   }

   public static List w(Scoreboard var0, long var1) {
      return L(b( var0));
   }

   public static String u( String var2) {
      if (var2.isEmpty()) {
         return var2;
      }

      char[] var3 = StringUtils.stripControlCodes(var2).toCharArray();
      StringBuilder var4 = new StringBuilder();

      for (char var8 : var3) {
         if (var8 < 127 && var8 > 20) {
            var4.append(var8);
         }
      }

      return var4.toString();
   }

   public static List b(long var0) {
      ArrayList var4 = new ArrayList();
      if (f.theWorld == null) {
         return var4;
      }

      Scoreboard var5 = f.theWorld.getScoreboard();
      return var5 == null ? var4 : b( var5);
   }

   public static String w(Scoreboard var0) {
      if (var0 == null) {
         return "";
      }

      ScoreObjective var1 = var0.getObjectiveInDisplaySlot(1);
      return var1 == null ? "" : var1.getDisplayName();
   }

   public static String E(Scoreboard var0) {
      return r(w(var0));
   }

   public static String r(String var0) {
      return var0 == null ? null : StringUtils.stripControlCodes(var0);
   }

   public static List<String> L(List<String> var0) {
      ArrayList var1 = new ArrayList(var0.size());
      int var2 = 0;

      for (int var3 = var0.size(); var2 < var3; var2++) {
         String var4 = (String)var0.get(var2);
         var1.add(r(var4));
      }

      return var1;
   }

   private ScoreboardUtil() {
   }

   public static boolean y(long var0) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      if (!ClientUtil.I()) {
         return false;
      }

      Scoreboard var6 = f.theWorld.getScoreboard();
      if (var6 == null) {
         return false;
      }

      ScoreObjective var7 = var6.getObjectiveInDisplaySlot(1);
      if (var7 != null && v(0L, var7.getDisplayName()).contains("BED WARS")) {
         for (String var9 : (Iterable<String>)(v(0L))) {
            var9 = v(0L, var9);
            String[] var10 = var9.split("  ");
            if (var10.length <= 1) {
               if (!var9.equals("Waiting...") && !var9.startsWith("Starting in")) {
                  if (!var9.startsWith("R Red:") && !var9.startsWith("B Blue:")) {
                     continue;
                  }

                  return true;
               }

               return false;
            } else if (var10[1].startsWith("L")) {
               return false;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   public static List U() {
      return L(b(0L));
   }

   public static List b( Scoreboard var2) {
      ScoreObjective var3 = var2.getObjectiveInDisplaySlot(1);
      if (var3 == null) {
         return new ArrayList();
      }

      Collection var4 = var2.getSortedScores(var3);
      ArrayList var5 = new ArrayList();

      for (Score var7 : (Iterable<Score>)(var4)) {
         if (var7 != null && var7.getPlayerName() != null && !var7.getPlayerName().startsWith("#")) {
            var5.add(var7);
         }
      }

      if (var5.size() > 15) {
         var5 = Lists.newArrayList(Iterables.skip(var5, var4.size() - 15));
      }

      ArrayList var11 = new ArrayList();
      int var12 = 0;

      for (int var8 = var5.size(); var12 < var8; var12++) {
         Score var9 = (Score)var5.get(var12);
         var11.add(ScorePlayerTeam.formatPlayerName(var2.getPlayersTeam(var9.getPlayerName()), ""));
      }

      Collections.reverse(var11);
      return var11;
   }

   public static String D() {
      return f.theWorld == null ? null : E(f.theWorld.getScoreboard());
   }

   public static boolean x(long var0) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      if (ScoreboardReader.v(0L)) {
         List var8 = v(0L);
         if (!var8.isEmpty()) {
            String[] var9 = u( (String)var8.get(1)).split("  ");
            if (var9.length > 1 && var9[1].charAt(0) == 76) {
               return true;
            }
         }
      }

      return false;
   }

   public static int z(long var0) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      List var6 = v(0L);
      if (var6.isEmpty()) {
         return -1;
      }

      if (!u( (String)var6.get(0)).startsWith("SKYWARS")) {
         return -1;
      }

      for (String var8 : (Iterable<String>)(var6)) {
         var8 = u( var8);
         if (var8.equals("Waiting...") || var8.startsWith("Starting in ")) {
            return 1;
         }

         if (var8.startsWith("Players left: ")) {
            return 2;
         }
      }

      return 0;
   }

   public static String l(long var0) {
      var0 = a ^ var0;
      List var4 = U();
      if (var4.isEmpty()) {
         return null;
      }

      int var5 = 0;

      for (int var6 = var4.size(); var5 < var6; var5++) {
         String var7 = (String)var4.get(var5);
         Matcher var8 = HypixelScoreboardParser.S.matcher(var7);
         if (var8.find()) {
            return var8.group(1);
         }
      }

      return null;
   }

   private static String v(long var0, String var2) {
      char[] var3 = StringUtils.stripControlCodes(var2).toCharArray();
      StringBuilder var4 = new StringBuilder();

      for (char var8 : var3) {
         if (var8 < 127 && var8 > 20) {
            var4.append(var8);
         }
      }

      return var4.toString();
   }

   static {
      a = 23291616283485L;
      f = MinecraftRef.c((byte)0, 0L);
   }
}
