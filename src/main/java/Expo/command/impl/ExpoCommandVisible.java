package Expo.command.impl;

import Expo.command.Command;
import Expo.command.ExpoCommands;
import Expo.internal.restore.ExpoCommandSelect;
import Expo.module.Category;
import Expo.module.Module;
import java.util.ArrayList;
import java.util.List;

public final class ExpoCommandVisible extends Command {
   private final boolean visible;
   private final String desc;
   private final String[] aliases;

   public ExpoCommandVisible(boolean var1, String var2, String... var3) {
      this.visible = var1;
      this.desc = var2;
      this.aliases = var3;
   }

   @Override
   public boolean J() {
      return false;
   }

   @Override
   public String[] e(long var1) {
      return this.aliases;
   }

   @Override
   public void h(long var1) {
      ExpoCommands.chat(this.desc);
      ExpoCommands.chat("§7Usage:");
      ExpoCommands.chat("§f  ." + this.aliases[0] + " <module... | category... | \"all\">");
   }

   @Override
   public void j(String[] var1, long var2) {
      List<Module> var4 = ExpoCommandSelect.resolve(var1, 0, var1.length);
      ExpoCommandSelect.reportUnresolved();

      if (var4.isEmpty()) {
         return;
      }

      int var5 = 0;
      int var6 = 0;

      for (int var7 = 0; var7 < var4.size(); var7++) {
         Module var8 = var4.get(var7);

         if (var8.S() || var8.f() == Category.Macro) {
            var6++;
            continue;
         }

         try {
            var8.Y(0L, this.visible, (short)0);
         } catch (Throwable var10) {
            ExpoCommands.chat("§cModule.Y threw for " + var8.b() + ": " + var10);
            continue;
         }

         if (var8.D() == this.visible) {
            var5++;
         } else {
            ExpoCommands.chat("§c" + var8.b() + " did not take the value.");
         }
      }

      ExpoCommands.chat("§7" + var5 + " of " + var4.size() + " module(s) are now §f"
                        + (this.visible ? "shown" : "hidden") + "§7 in the ArrayList."
                        + (var6 > 0 ? " §8" + var6 + " skipped (fixed binding or Macro; the "
                                      + "stock writer ignores the argument for those)" : ""));

      if (var5 > 0) {
         ExpoCommands.chat("§8Not saved yet -- use §7.config save <name>§8 to persist it.");
      }
   }

   @Override
   public List g(String[] var1, int var2, long var3) {
      return new ArrayList(ExpoCommandSelect.pool());
   }
}
