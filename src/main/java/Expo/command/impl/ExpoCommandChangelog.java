package Expo.command.impl;

import Expo.command.Command;
import Expo.command.ExpoCommands;
import Expo.util.BuildInfo;
import java.util.ArrayList;
import java.util.List;

public final class ExpoCommandChangelog extends Command {
   private static final String SEP = "§8-----------------------------";

   @Override
   public boolean J() {
      return false;
   }

   @Override
   public String[] e(long var1) {
      return new String[]{"changelog"};
   }

   @Override
   public void h(long var1) {
      this.j(new String[0],0L);
   }

   @Override
   public void j(String[] var1, long var2) {
      List<String> var4 = entries();

      ExpoCommands.chat(SEP);

      if (var4 == null || var4.isEmpty()) {
         ExpoCommands.chat("§7No changelog entry for this build.");
      } else {
         for (int var5 = 0; var5 < var4.size(); var5++) {
            ExpoCommands.chat("§7- §f" + var4.get(var5));
         }
      }

      ExpoCommands.chat(SEP);
   }

   @Override
   public List g(String[] var1, int var2, long var3) {
      return new ArrayList();
   }

   private static List<String> entries() {
      try {
         return BuildInfo.T;
      } catch (Throwable var0) {
         return null;
      }
   }
}
