package Expo.command.impl;

import Expo.command.Command;
import Expo.command.ExpoCommands;
import Expo.internal.restore.ExpoCommandData;
import Expo.ui.screen.MainMenuTheme;
import java.util.ArrayList;
import java.util.List;











// add code
public final class ExpoCommandMenu extends Command {

   @Override
   public boolean J() {
      return false;
   }

   @Override
   public String[] e(long var1) {
      return new String[]{"menu"};
   }

   @Override
   public void h(long var1) {
      ExpoCommands.chat("§7Usage: §f.menu <" + join(options(), " | ") + ">");
      ExpoCommands.chat("§7Current: §f" + current() + "§7, music §f" + music());
      ExpoCommands.chat("§8Persisted to §7" + ExpoCommandData.MENU + "§8 and §7"
                        + ExpoCommandData.MENU_MUSIC + "§8 next to current.json.");
   }

   @Override
   public void j(String[] var1, long var2) {
      String var4 = var1[0].trim().toUpperCase();
      List<String> var5 = options();

      if (var5.isEmpty()) {
         ExpoCommands.chat("§cThe menu-background setting is not available in this build.");
         return;
      }

      if (!var5.contains(var4)) {
         ExpoCommands.chat("§cUnknown background §f" + var1[0] + "§c. Known: §f"
                           + join(var5, ", "));
         return;
      }

      try {
         MainMenuTheme.mode.i(var4);
      } catch (Throwable var8) {
         ExpoCommands.chat("§cThe setting refused the write: " + var8);
         return;
      }

      String var6 = current();

      if (!var4.equals(var6)) {
         ExpoCommands.chat("§cAsked for §f" + var4 + "§c but the setting now reads §f" + var6);
         return;
      }

      boolean var7 = ExpoCommandData.saveMenu() & ExpoCommandData.saveMenuMusic();
      ExpoCommands.chat("§aMenu background §f" + var6
                        + (var7 ? "§7, saved." : " §cbut the write to "
                                                 + ExpoCommandData.MENU + " FAILED."));
   }

   @Override
   public List g(String[] var1, int var2, long var3) {
      List var5 = new ArrayList();

      if (var2 <= 1) {
         var5.addAll(options());
      }

      return var5;
   }

   private static List<String> options() {
      try {
         List<String> var0 = MainMenuTheme.mode.S();
         return var0 == null ? new ArrayList<String>() : var0;
      } catch (Throwable var1) {
         return new ArrayList<String>();
      }
   }

   private static String current() {
      try {
         return MainMenuTheme.mode.Y();
      } catch (Throwable var0) {
         return "?";
      }
   }

   private static String music() {
      try {
         return String.valueOf(MainMenuTheme.music.c());
      } catch (Throwable var0) {
         return "?";
      }
   }

   private static String join(List<String> var0, String var1) {
      StringBuilder var2 = new StringBuilder();

      for (int var3 = 0; var3 < var0.size(); var3++) {
         if (var3 > 0) {
            var2.append(var1);
         }

         var2.append(var0.get(var3));
      }

      return var2.toString();
   }
}
