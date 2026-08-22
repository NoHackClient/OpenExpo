package Expo.command.impl;

import Expo.command.Command;
import Expo.command.ExpoCommands;
import Expo.internal.restore.ExpoCommandSelect;
import Expo.module.Module;
import java.util.ArrayList;
import java.util.List;

public final class ExpoCommandSuffix extends Command {
   @Override
   public boolean J() {
      return false;
   }

   @Override
   public String[] e(long var1) {
      return new String[]{"suffix", "sfx"};
   }

   @Override
   public void h(long var1) {
      ExpoCommands.chat("§eEdit modules suffix visibility");
      ExpoCommands.chat("§7Usage:");
      ExpoCommands.chat("§f  .suffix <module... | category... | \"all\"> [\"true\" | \"false\"]");
   }

   @Override
   public void j(String[] var1, long var2) {
      int var4 = var1.length;
      Boolean var5 = null;
      String var6 = var1[var4 - 1];

      if ("true".equalsIgnoreCase(var6) || "false".equalsIgnoreCase(var6)) {
         var5 = Boolean.valueOf(Boolean.parseBoolean(var6.toLowerCase()));
         var4--;
      }

      if (var4 == 0) {
         this.h(0L);
         return;
      }

      List<Module> var7 = ExpoCommandSelect.resolve(var1, 0, var4);
      ExpoCommandSelect.reportUnresolved();

      if (var7.isEmpty()) {
         return;
      }

      int var8 = 0;

      for (int var9 = 0; var9 < var7.size(); var9++) {
         Module var10 = var7.get(var9);
         boolean var11 = var5 == null ? !var10.r() : var5.booleanValue();

         try {
            var10.C(var11);
         } catch (Throwable var13) {
            ExpoCommands.chat("§cModule.C threw for " + var10.b() + ": " + var13);
            continue;
         }

         if (var10.r() == var11) {
            var8++;
         } else {
            ExpoCommands.chat("§c" + var10.b() + " did not take the value.");
         }
      }

      if (var7.size() == 1 && var8 == 1) {
         ExpoCommands.chat("§7" + var7.get(0).b() + " suffix is now §f" + var7.get(0).r());
      } else {
         ExpoCommands.chat("§7" + var8 + " of " + var7.size() + " module(s) set to §f"
                           + (var5 == null ? "the opposite of what they were" : var5));
      }

      if (var8 > 0) {
         ExpoCommands.chat("§8Not saved yet -- use §7.config save <name>§8 to persist it.");
      }
   }

   @Override
   public List g(String[] var1, int var2, long var3) {
      List var5 = new ArrayList();

      if (var2 <= 1) {
         var5.addAll(ExpoCommandSelect.pool());
      } else {
         var5.add("true");
         var5.add("false");
         var5.addAll(ExpoCommandSelect.pool());
      }

      return var5;
   }
}
