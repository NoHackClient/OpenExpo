package Expo.util;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import net.minecraft.client.Minecraft;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;











public class ScoreboardReader {
   private static Minecraft U;
   private static String[] c;
   private static long a;
   public static AtomicBoolean X;

   public static boolean v(long var0) {
      return !BuildInfo.W.equalsIgnoreCase("Development") && !BuildInfo.W.equalsIgnoreCase("NoHackClient") ? !X.get() : true;
   }

   private static void a() {
   }

   static {
      a = 89492807931649L;
      X = new AtomicBoolean(false);
      U = MinecraftRef.c((byte)0, 0L);
   }

   public static ArrayList<String> l() {
      if (U.theWorld == null) {
         return new ArrayList<>();
      }

      Scoreboard var0 = U.theWorld.getScoreboard();
      if (var0 == null) {
         return new ArrayList<>();
      }

      ScoreObjective var1 = var0.getObjectiveInDisplaySlot(1);
      if (var1 == null) {
         return new ArrayList<>();
      }

      ArrayList var2 = new ArrayList();

      for (Score var4 : var0.getSortedScores(var1)) {
         String var5 = ScorePlayerTeam.formatPlayerName(var0.getPlayersTeam(var4.getPlayerName()), var4.getPlayerName());
         var2.add(var5);
      }

      return var2;
   }




}
