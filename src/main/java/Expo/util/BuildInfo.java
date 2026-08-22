package Expo.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuildInfo {
   private static String[] b;
   public static String q;
   public static String K;
   public static String W;
   public static String H;
   public static String Z;
   public static String B;
   private static List<String> A;
   private static String[] c;
   public static String g;
   private static long a;
   public static String L;
   public static List<String> T;
   public static String D;
   private static Map d;

   public static String y(long var0) {
      var0 = a ^ var0;
      int var2 = (int)((var0 ^ 27749185436719L) >>> 48);
      return "§d§r[" + z((short)var2) + "§d§r]§r ";
   }

   static {
      D = "User";
      K = "Expo";
      Z = "Release";
      g = "NoHackClient";
      L = "OpenSource";
      q = "OpenSource";
      B = "© 2026 NoHackClient. All rights reserved.";
      T = Arrays.asList("Improve Scaffold LEGIT sneak timing", "Made Scaffold bypass with Strict-aim-check includes placement facing check");
      A = Arrays.asList("RIP, TimewastersXD for autoblock bug detecting T-T", "RIP, RlDDLE_JOKER for testing out telly scaffold");
      H = "";
      W = "Player";
      a = 29972715278727L;
      d = new HashMap(13);
      b = new String[]{"\u00f2\u0082\u0010l\u00833\u0094\u0000L\u00c7\u00e2\u00ee\u00eeC\u00c8PL1I^5\u00b5r\u00c4Q\u00aaG+W\u0011\u00ee\u00de\u00cdY/\u0099vK_\u00f8\u00fdH\u00ff\t\u0012>\u0015\u00ed\u0093xLO[A\u00a3f", "\u00858\u001c\u00e4\u00d6\u00d1\u00ca\u00d9wV\u00bfom6\u0011\u00a4", "6w\u00889\u00efer\u0010", "L\u00e2p=\u00d0\u00d1\u0000O\u00e0\u00a0\u009dx!N\u00f8\u00ff\u00a8\u00a9z\u0094V+\u00d7[\u009c_:\u00823w#\u0082\u000f\u0018\u001c\u009c%\u00cbmV", "S\u00ff|f\u0015\u00ef\u00be?", "O\u001b\u009f\u00c1T\u0002\u00b2H", "6q\u00a6\u0015\u00eby\u00cd\u00df&w\u0019\u0082C\u00ac\u00ed\u000f", "\u00b9\u0005\u00ee\u00c4\u00a3\u0005W2", "\u0088[\u000bL#\u0094\u00a1\u00cb", "\u00b9\u00d0@\u00c2\u0006\u00f6\u001e\u00d1", "!\u00ce\u00a6{[>+\u0089GJX\u00eb3\u00f1\u00b5\u00b9(\r\u00e8Y\"\u00f6`\u00ed\u000b\u00abT\u00ca\u00d4\u009a\u009fW$i\u00d0DJ\u00e6V\u0099\u00e7\u000c\u0096,\n\u0097\u00d9\u00cb\u00db\u00b7\u009aD\u0086\u0008#\u00cf\u0089\u00c7\u00cb\u00bfHs\u007f\u00b5\u00e3\u00cb\u00af'\u0000\u0005\u000by\u0095\u000c\u00b2\u00ddm4\u00a9\u00f3", "<\u00a3\u00e9TC\u00cas\u0081", "\u00c4\u00fcE\u00ca\u00cb\u0019\u00adW\u0012\u007f\u009b\u00cc\u0001RqtDw\u00d9\u0007E\u00af7\u00ce\u00be~\u009a\u00f4\u00bf'\u00f7u\u00f4\u00d6S\u00c8\u00cfs\u00ac\u00d6\u001c@\u00f2B\u00ed\u008aH\u00a1n\u00fer\u00b2\u0095\u001a#\u00c7", "]C\u00fbO&\u0084\u00b5\u00c2", "\u00b1\u0005?\u0018\u0014\u0089\u00a7\u007f", "m\u00d2\u00d1Q\u00e7\u001e\u0006\u0093", "\u00bdK\u00bbE.\u0016h\u0087@\u00f3\u009f\u00f7\u009dk!)\u00bb\u00c6\u001b=\u00f2\u00d5\u00fa\u0094VI\u000fM\u00e1Lg\u00b3\u0081M(J\u00d8\u00f5\u00c4\u00ac", "/OnY\u008d\u00e5}\u00b4", "$\u009bs\u00d7\u00ac.0x"};
      c = new String[19];
   }

   public static String l() {
      return "Expo".charAt(0) + "§r§f" + "Expo".substring(1);
   }

   private static String z(short var0) {
      StringBuilder var5 = new StringBuilder();
      int var6 = 0;

      for (char var10 : "Expo".toCharArray()) {
         var6++;
         switch (var6) {
            case 1:
               var5.append("§b§l").append(var10);
               break;
            case 2:
               var5.append("§9§l").append(var10);
               break;
            case 3:
               var5.append("§3§l").append(var10);
         }
      }

      return var5.toString();
   }
}
