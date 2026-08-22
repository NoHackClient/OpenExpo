package Expo.command.impl;

import Expo.command.Command;
import Expo.command.ExpoCommands;
import Expo.internal.restore.ExpoCommandSelect;
import Expo.internal.restore.ExpoConfig;
import Expo.module.Module;
import java.util.ArrayList;
import java.util.List;

public final class ExpoCommandToggle extends Command {
   @Override
   public boolean J() {
      return false;
   }

   @Override
   public String[] e(long var1) {
      return new String[]{"toggle", "t"};
   }

   @Override
   public void h(long var1) {
      ExpoCommands.chat("§eToggle a module");
      ExpoCommands.chat("§7Usage:");
      ExpoCommands.chat("§f  .toggle <module...>");
   }

   @Override
   public void j(String[] var1, long var2) {
      int var4 = 0;

      for (int var5 = 0; var5 < var1.length; var5++) {
         Module var6 = ExpoCommands.module(var1[var5]);

         if (var6 == null) {
            ExpoCommands.chat("§cNo module named §f" + var1[var5] + "§c.");
            continue;
         }

         if (!var6.I()) {
            ExpoCommands.chat("§c" + var6.b() + " is not a toggleable module.");
            continue;
         }

         if (var6.S()) {
            ExpoCommands.chat("§c" + var6.b() + " has a fixed binding and refuses .toggle.");
            continue;
         }

         boolean var7 = var6.o();

         try {
            var6.I(ExpoConfig.MODULE_I_CARRIER, !var7);
         } catch (Throwable var9) {
            ExpoCommands.chat("§cModule.I threw for " + var6.b() + ": " + var9);
            continue;
         }

         if (var6.o() == var7) {
            ExpoCommands.chat("§c" + var6.b() + " did not change state.");
            continue;
         }

         ExpoCommands.chat((var6.o() ? "§a" : "§c") + var6.b() + "§7 is now §f"
                           + (var6.o() ? "enabled" : "disabled"));
         var4++;
      }

      if (var4 > 0) {
         ExpoCommands.chat("§8Not saved yet -- use §7.config save <name>§8 to persist it.");
      }
   }

   @Override
   public List g(String[] var1, int var2, long var3) {
      List var5 = new ArrayList();

      for (Module var7 : ExpoCommandSelect.all()) {
         var5.add(var7.b());
      }

      return var5;
   }
}
