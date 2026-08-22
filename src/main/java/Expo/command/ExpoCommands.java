package Expo.command;

import Expo.command.impl.ExpoCommandBind;
import Expo.command.impl.ExpoCommandBindChat;
import Expo.command.impl.ExpoCommandChangelog;
import Expo.command.impl.ExpoCommandCheaters;
import Expo.command.impl.ExpoCommandConfig;
import Expo.command.impl.ExpoCommandDebug;
import Expo.command.impl.ExpoCommandHelp;
import Expo.command.impl.ExpoCommandIgn;
import Expo.command.impl.ExpoCommandInfo;
import Expo.command.impl.ExpoCommandList;
import Expo.command.impl.ExpoCommandMenu;
import Expo.command.impl.ExpoCommandNames;
import Expo.command.impl.ExpoCommandPartySpam;
import Expo.command.impl.ExpoCommandReset;
import Expo.command.impl.ExpoCommandStub;
import Expo.command.impl.ExpoCommandSuffix;
import Expo.command.impl.ExpoCommandToggle;
import Expo.command.impl.ExpoCommandVisible;
import Expo.internal.jnic.StockCommandRegistry;
import Expo.internal.restore.ExpoCommandData;
import Expo.internal.restore.ExpoModuleRegistry;
import Expo.module.Module;
import Expo.module.ModuleManager;
import Expo.ui.swing.ConfigManagerWindow;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;












// add code
public final class ExpoCommands {

   public static final char PREFIX = '.';


   private static boolean installed;

   private ExpoCommands() {
   }

   public static void install(List<String> var0) {
      if (installed) {
         return;
      }

      installed = true;

      if (StockCommandRegistry.L == null) {
         StockCommandRegistry.L = new java.util.LinkedHashSet<Command>();
      }

      StockCommandRegistry.L.add(new ExpoCommandBind());
      // add code
      StockCommandRegistry.L.add(new ExpoCommandBindChat());
      StockCommandRegistry.L.add(new ExpoCommandChangelog());
      StockCommandRegistry.L.add(new ExpoCommandCheaters());
      StockCommandRegistry.L.add(new ExpoCommandConfig());
      // add code
      StockCommandRegistry.L.add(new ExpoCommandDebug());
      StockCommandRegistry.L.add(new ExpoCommandNames(ExpoCommandNames.ENEMY,
                                    "§eManage your blacklisted players",
                                    new String[]{"  .enemy list",
                                                 "  .enemy <add | remove> <name...>",
                                                 "  .enemy clear"},
                                    "enemy", "e", "target", "bl", "blacklist"));
      StockCommandRegistry.L.add(new ExpoCommandNames(ExpoCommandNames.FRIEND,
                                    "§eManage your whitelisted players",
                                    new String[]{"  .friend <add | remove> <name...>",
                                                 "  .friend list",
                                                 "  .friend clear"},
                                    "friend", "f", "wl", "whitelist"));
      StockCommandRegistry.L.add(new ExpoCommandVisible(false, "§eHide module in the ArrayList", "hide", "h"));
      StockCommandRegistry.L.add(new ExpoCommandHelp());
      // add code
      StockCommandRegistry.L.add(new ExpoCommandInfo());
      StockCommandRegistry.L.add(new ExpoCommandIgn());
      StockCommandRegistry.L.add(new ExpoCommandList());
      StockCommandRegistry.L.add(new ExpoCommandMenu());
      StockCommandRegistry.L.add(new ExpoCommandPartySpam());
      StockCommandRegistry.L.add(new ExpoCommandReset());
      StockCommandRegistry.L.add(new ExpoCommandVisible(true, "§eShow module in the ArrayList", "show", "s"));
      StockCommandRegistry.L.add(new ExpoCommandSuffix());
      StockCommandRegistry.L.add(new ExpoCommandToggle());

      // add code
      try {
         ExpoCommandData.load();
      } catch (Throwable var13) {
      }

      int var10 = 0;
      int var11 = 0;

      for (Command var12 : StockCommandRegistry.L) {
         if (var12 instanceof ExpoCommandStub) {
            if (((ExpoCommandStub)var12).hasText()) {
               var11++;
            }
         } else {
            var10++;
         }
      }

      // add code
      // add code
      try {
         note(var0);
      } catch (Throwable var9) {
      }

      if (var0 != null) {
      }
   }

   private static void note(List<String> var0) {
      Module var1 = ModuleManager.o == null ? null : ModuleManager.o.get(Expo.module.impl.misc.CommandLine.class);
      String var2 = var1 == null ? null : var1.b();

      if (var2 == null) {
      } else if (var2.startsWith(ExpoModuleRegistry.PLACEHOLDER_PREFIX)) {
      } else {
      }
   }

   public static boolean dispatch(String var0) {
      if (var0 == null) {
         return false;
      }

      String var1 = var0.trim();

      if (var1.length() < 2 || var1.charAt(0) != PREFIX) {
         return false;
      }

      try {
         String var2 = var1.substring(1);
         String[] var3 = var2.split("\\s+");

         if (var3.length == 0 || var3[0].isEmpty()) {
            return false;
         }

         Command var4 = find(var3[0]);

         if (var4 == null) {
            chat("§cUnknown command §f" + var3[0] + "§c. Known: " + names());
            return true;
         }

         String[] var5 = new String[var3.length - 1];
         System.arraycopy(var3, 1, var5, 0, var5.length);

         if (var5.length == 0) {
            var4.h(0L);
         } else {
            var4.j(var5, 0L);
         }

         return true;
      } catch (Throwable var6) {
         chat("§cCommand failed: " + var6);
         var6.printStackTrace();
         return true;
      }
   }

   private static Command find(String var0) {
      if (StockCommandRegistry.L == null) {
         return null;
      }

      for (Command var2 : StockCommandRegistry.L) {
         try {
            String[] var3 = var2.e(0L);

            if (var3 != null) {
               for (int var4 = 0; var4 < var3.length; var4++) {
                  if (var3[var4] != null && var3[var4].equalsIgnoreCase(var0)) {
                     return var2;
                  }
               }
            }
         } catch (Throwable var5) {
         }
      }

      return null;
   }

   private static String names() {
      StringBuilder var0 = new StringBuilder();

      if (StockCommandRegistry.L != null) {
         for (Command var2 : StockCommandRegistry.L) {
            try {
               String[] var3 = var2.e(0L);

               if (var3 != null && var3.length > 0 && var3[0] != null) {
                  if (var0.length() > 0) {
                     var0.append(", ");
                  }

                  var0.append(PREFIX).append(var3[0]);
               }
            } catch (Throwable var4) {
            }
         }
      }

      return var0.toString();
   }

   public static void chat(String var0) {
      try {
         if (ConfigManagerWindow.D == null) {
            ConfigManagerWindow.D = new ArrayList<String>();
         }

         Minecraft var1 = Minecraft.getMinecraft();

         if (var1 != null && var1.ingameGUI != null) {
            var1.ingameGUI.getChatGUI().printChatMessage(new ChatComponentText(var0));
         }

         ConfigManagerWindow.D.add(var0);
      } catch (Throwable var2) {
         System.out.println("[EXPOCMD] " + var0);
      }
   }

   public static Module module(String var0) {
      if (ModuleManager.S == null || var0 == null) {
         return null;
      }

      for (Module var2 : ModuleManager.S) {
         if (var2 != null) {
            String var3 = var2.b();

            if (var3 != null
                && !var3.startsWith(ExpoModuleRegistry.PLACEHOLDER_PREFIX)
                && var3.equalsIgnoreCase(var0)) {
               return var2;
            }
         }
      }

      return null;
   }

   public static int placeholderCount() {
      int var0 = 0;

      try {
      if (ModuleManager.S != null) {
         for (Module var2 : ModuleManager.S) {
            if (var2 != null && var2.b() != null
                && var2.b().startsWith(ExpoModuleRegistry.PLACEHOLDER_PREFIX)) {
               var0++;
            }
         }
      }
      } catch (Throwable var3) {
         return -1;
      }

      return var0;
   }
}
