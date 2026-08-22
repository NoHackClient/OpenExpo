package Expo.command.impl;

import Expo.ExpoClient;
import Expo.command.Command;
import Expo.command.ExpoCommands;
import Expo.internal.jnic.StockCommandRegistry;
import Expo.internal.restore.ExpoCommandData;
import Expo.module.ModuleManager;
import Expo.ui.ModuleTagRenderer;
import Expo.util.ScoreboardReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;













// add code
public final class ExpoCommandDebug extends Command {

   private static final String SCOREBOARD_DUMP = "debug_scoreboard.txt";
   private static final String DISPLAYNAME_DUMP = "debug_displayname.txt";

   private static final String[] USAGE = {
      "  .debug <username | nickname | token | userid>",
      "  .debug [true | false]",
      "  .debug <status>",
      "  .debug <dumpdisplayname> <player name>",
      "  .debug <dumpscoreboard>",
   };

   @Override
   public boolean J() {
      return false;
   }

   @Override
   public String[] e(long var1) {
      return new String[]{"debug", "dbg"};
   }

   @Override
   public void h(long var1) {
      ExpoCommands.chat("§7Usage:");

      for (int var3 = 0; var3 < USAGE.length; var3++) {
         ExpoCommands.chat("§f" + USAGE[var3]);
      }

      ExpoCommands.chat("§7Debug output is currently §f" + flag());
      ExpoCommands.chat("§8username/nickname/token/userid read the removed login layer and "
                        + "are NOT restored; the rest are.");
   }

   @Override
   public void j(String[] var1, long var2) {
      String var4 = var1[0].toLowerCase();

      if ("true".equals(var4) || "false".equals(var4)) {
         setFlag(Boolean.parseBoolean(var4));
      } else if ("status".equals(var4)) {
         status();
      } else if ("dumpscoreboard".equals(var4)) {
         dumpScoreboard();
      } else if ("dumpdisplayname".equals(var4)) {
         if (var1.length < 2) {
            ExpoCommands.chat("§cUsage: §f.debug dumpdisplayname <player name>");
         } else {
            dumpDisplayName(var1[1]);
         }
      } else if ("username".equals(var4) || "nickname".equals(var4)
                 || "token".equals(var4) || "userid".equals(var4)) {
         ExpoCommands.chat("§c." + "debug " + var4 + " reads the account session, which this "
                           + "build does not have: the login layer was removed and its class "
                           + "is still native.");
         ExpoCommands.chat("§8For the in-game name use §7.ign§8 instead.");
      } else {
         this.h(0L);
      }
   }

   @Override
   public List g(String[] var1, int var2, long var3) {
      List var5 = new ArrayList();

      if (var2 <= 1) {
         var5.addAll(Arrays.asList("true", "false", "status", "dumpscoreboard",
                                   "dumpdisplayname", "username", "nickname", "token",
                                   "userid"));
      } else if (var2 == 2 && var1.length > 0 && "dumpdisplayname".equalsIgnoreCase(var1[0])) {
         Minecraft var6 = Minecraft.getMinecraft();

         if (var6 != null && var6.theWorld != null) {
            for (EntityPlayer var8 : var6.theWorld.playerEntities) {
               var5.add(var8.getName());
            }
         }
      }

      return var5;
   }

   private static String flag() {
      try {
         return String.valueOf(ModuleTagRenderer.X);
      } catch (Throwable var0) {
         return "unavailable (" + var0 + ")";
      }
   }

   private static void setFlag(boolean var0) {
      try {
         ModuleTagRenderer.X = var0;
         ExpoCommands.chat("§7Debug output is now §f" + ModuleTagRenderer.X);
         ExpoCommands.chat("§8That is the EventBus handler-exception printer: with it on, a "
                           + "throw out of any subscriber is echoed to chat once per "
                           + "exception class per interval.");
      } catch (Throwable var2) {
         ExpoCommands.chat("§cCould not write the debug flag: " + var2);
      }
   }

   private static void status() {
      ExpoCommands.chat("§7--- Expo debug status ---");
      ExpoCommands.chat("§7debug output §f" + flag());
      ExpoCommands.chat("§7modules published §f" + moduleCount()
                        + "§7, unnamed §f" + ExpoCommands.placeholderCount());
      ExpoCommands.chat("§7commands registered §f" + (StockCommandRegistry.L == null ? 0 : StockCommandRegistry.L.size()));
      ExpoCommands.chat("§7chat binds §f" + (ExpoClient.H == null ? 0 : ExpoClient.H.size()));
      ExpoCommands.chat("§7data dir §f" + ExpoCommandData.dirFile().getPath());
      ExpoCommands.chat("§7" + ExpoCommandBind.gateNote());
   }

   private static int moduleCount() {
      try {
         return ModuleManager.S == null ? -1 : ModuleManager.S.size();
      } catch (Throwable var0) {
         return -1;
      }
   }

   private static void dumpScoreboard() {
      ArrayList<String> var0;

      try {
         var0 = ScoreboardReader.l();
      } catch (Throwable var4) {
         ExpoCommands.chat("§cThe scoreboard reader threw: " + var4);
         return;
      }

      if (var0 == null || var0.isEmpty()) {
         ExpoCommands.chat("§7No sidebar objective is showing; nothing to dump.");
         return;
      }

      Path var1 = ExpoCommandData.resolve(SCOREBOARD_DUMP);

      try {
         Files.write(var1, var0, StandardCharsets.UTF_8, StandardOpenOption.CREATE,
                     StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
      } catch (Throwable var3) {
         ExpoCommands.chat("§cWrite failed: " + var3);
         return;
      }

      for (int var2 = 0; var2 < var0.size(); var2++) {
         ExpoCommands.chat("§8" + var2 + " §r" + var0.get(var2));
      }

      ExpoCommands.chat("§a" + var0.size() + " line(s) -> §f" + var1.toAbsolutePath());
   }

   private static void dumpDisplayName(String var0) {
      Minecraft var1 = Minecraft.getMinecraft();

      if (var1 == null || var1.theWorld == null) {
         ExpoCommands.chat("§cNot in a world.");
         return;
      }

      for (EntityPlayer var3 : var1.theWorld.playerEntities) {
         if (var3 != null && var0.equalsIgnoreCase(var3.getName())) {
            String var4 = var3.getDisplayName() == null
                          ? "" : var3.getDisplayName().getFormattedText();
            String var5 = var3.getName() + "\n" + var4;
            Path var6 = ExpoCommandData.resolve(DISPLAYNAME_DUMP);
            boolean var7;

            try {
               Files.write(var6, var5.getBytes(StandardCharsets.UTF_8),
                           StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING,
                           StandardOpenOption.WRITE);
               var7 = true;
            } catch (Throwable var9) {
               var7 = false;
            }

            ExpoCommands.chat("§7name §f" + var3.getName());
            ExpoCommands.chat("§7display §r" + var4);
            ExpoCommands.chat("§7raw §f" + var4.replace('§', '&'));

            try {
               GuiScreen.setClipboardString(var4);
            } catch (Throwable var8) {
            }

            ExpoCommands.chat(var7 ? "§8copied to the clipboard and written to §7"
                                     + var6.toAbsolutePath()
                                   : "§8copied to the clipboard; the file write failed");
            return;
         }
      }

      ExpoCommands.chat("§cNo player named §f" + var0 + "§c is loaded.");
   }
}
