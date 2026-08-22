package Expo.command.impl;

import Expo.command.Command;
import Expo.command.ExpoCommands;
import Expo.internal.jnic.StockCommandRegistry;
import Expo.internal.restore.ExpoModuleRegistry;
import Expo.module.Module;
import Expo.module.ModuleManager;
import java.util.ArrayList;
import java.util.List;











// add code
public final class ExpoCommandHelp extends Command {

   private static final String TITLE = "Here's a list of commands";
   private static final String LINE_PREFIX = "  §l.";
   private static final String ALIAS_SEP = "§r, §l.";
   private static final String FOOTER_HELP =
      "§bUse .help <command> to get usage of a specific command";
   private static final String FOOTER_MODULE =
      "§aUse .<module> to configure a module's setting";

   @Override
   public boolean J() {
      return false;
   }

   @Override
   public String[] e(long var1) {
      return new String[]{"help"};
   }

   @Override
   public void h(long var1) {
      int var3 = 0;
      int var4 = 0;
      int var5 = 0;

      ExpoCommands.chat(TITLE);

      for (Command var7 : StockCommandRegistry.L) {
         try {
            String[] var8;
            boolean var9;

            if (var7 instanceof ExpoCommandStub) {
               var8 = var7.e(0L);
               var9 = false;

               if (((ExpoCommandStub)var7).hasText()) {
                  var5++;
               }
            } else {
               var8 = var7.e(0L);
               var9 = true;
            }

            ExpoCommands.chat(aliasLine(var8));

            if (var9) {
               var3++;
            } else {
               var4++;
            }
         } catch (Throwable var10) {
         }
      }

      ExpoCommands.chat(FOOTER_HELP);
      ExpoCommands.chat(FOOTER_MODULE);

      // add code
      ExpoCommands.chat("§8[restored] " + var3 + " of " + (var3 + var4)
                        + " commands have a restored body"
                        + (var4 == 0 ? "." : "; the other " + var4 + " print the stock "
                                             + "client's own text only (" + var5
                                             + " with its real usage)."));
      ExpoCommands.chat("§8[restored] the .<module> line above is the stock text; that "
                        + "path is Expo/command/impl/StockCommandModuleSetting, which is native and unregistered, "
                        + "so it does NOT work here. " + namedModules()
                        + " modules are reachable by name.");
   }

   @Override
   public void j(String[] var1, long var2) {
      String var4 = var1[0];

      if (var4.length() > 0 && var4.charAt(0) == ExpoCommands.PREFIX) {
         var4 = var4.substring(1);
      }

      for (Command var6 : StockCommandRegistry.L) {
         try {
            String[] var7 = var6.e(0L);

            for (int var8 = 0; var8 < var7.length; var8++) {
               if (var7[var8] != null && var7[var8].equalsIgnoreCase(var4)) {
                  var6.h(var2);
                  return;
               }
            }
         } catch (Throwable var9) {
         }
      }

      ExpoCommands.chat("§cNo command named §f" + var4 + "§c.");
   }

   @Override
   public List g(String[] var1, int var2, long var3) {
      List var5 = new ArrayList();

      if (var2 <= 1) {
         for (Command var7 : StockCommandRegistry.L) {
            try {
               String[] var8 = var7.e(0L);

               if (var8 != null && var8.length > 0) {
                  var5.add(var8[0]);
               }
            } catch (Throwable var9) {
            }
         }
      }

      return var5;
   }

   private static String aliasLine(String[] var0) {
      StringBuilder var1 = new StringBuilder(LINE_PREFIX);

      for (int var2 = 0; var2 < var0.length; var2++) {
         if (var2 > 0) {
            var1.append(ALIAS_SEP);
         }

         var1.append(var0[var2]);
      }

      return var1.toString();
   }

   private static int namedModules() {
      int var0 = 0;

      try {
      if (ModuleManager.S != null) {
         for (Module var2 : ModuleManager.S) {
            if (var2 != null && var2.b() != null
                && !var2.b().startsWith(ExpoModuleRegistry.PLACEHOLDER_PREFIX)) {
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
