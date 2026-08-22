package Expo.command.impl;

import Expo.command.Command;
import Expo.command.ExpoCommands;
import Expo.internal.restore.ExpoModuleRegistry;
import Expo.module.Category;
import Expo.module.Module;
import Expo.module.ModuleManager;
import java.util.ArrayList;
import java.util.List;
import org.lwjgl.input.Keyboard;

public final class ExpoCommandList extends Command {
   private static final String SEP = "§8-----------------------------";

   @Override
   public boolean J() {
      return false;
   }

   @Override
   public String[] e(long var1) {
      return new String[]{"list", "l"};
   }

   @Override
   public void h(long var1) {
      this.j(new String[0],0L);
   }

   @Override
   public void j(String[] var1, long var2) {
      if (ModuleManager.S == null || ModuleManager.S.isEmpty()) {
         ExpoCommands.chat("§cNo module is published.");
         return;
      }

      Category var4 = var1.length > 0 ? category(var1[0]) : null;

      ExpoCommands.chat(SEP);

      int var5 = 0;
      int var6 = 0;

      for (Module var8 : ModuleManager.S) {
         if (var8 == null || var8.b() == null) {
            continue;
         }

         if (var4 != null && var8.f() != var4) {
            continue;
         }

         if (var8.b().startsWith(ExpoModuleRegistry.PLACEHOLDER_PREFIX)) {
            var6++;
            continue;
         }

         ExpoCommands.chat((var8.o() ? "§a" : "§7") + var8.b()
                           + "§8 [" + var8.f() + "]"
                           + (var8.h() == 0 ? "" : " §8-> §f" + keyName(var8.h())));
         var5++;
      }

      ExpoCommands.chat(SEP);
      ExpoCommands.chat("§7" + var5 + " module(s)"
                        + (var6 > 0 ? "§8, " + var6 + " with no confirmed name were skipped"
                                    : ""));
   }

   @Override
   public List g(String[] var1, int var2, long var3) {
      List var5 = new ArrayList();

      if (var2 <= 1) {
         Category[] var6 = Category.values();

         for (int var7 = 0; var7 < var6.length; var7++) {
            var5.add(var6[var7].c());
         }
      }

      return var5;
   }

   private static Category category(String var0) {
      Category[] var1 = Category.values();

      for (int var2 = 0; var2 < var1.length; var2++) {
         if (var1[var2].c().equalsIgnoreCase(var0)) {
            return var1[var2];
         }
      }

      return null;
   }

   private static String keyName(int var0) {
      String var1 = Keyboard.getKeyName(var0);
      return (var1 == null ? String.valueOf(var0) : var1).toUpperCase();
   }
}
