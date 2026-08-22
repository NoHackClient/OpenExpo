package Expo.internal.restore;

import Expo.command.ExpoCommands;
import Expo.module.Category;
import Expo.module.Module;
import Expo.module.ModuleManager;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public final class ExpoCommandSelect {
   static final String ALL = "all";

   static final List<String> UNRESOLVED = new ArrayList<String>();

   private ExpoCommandSelect() {
   }

   public static List<Module> resolve(String[] var0, int var1, int var2) {
      UNRESOLVED.clear();
      Set<Module> var3 = new LinkedHashSet<Module>();

      for (int var4 = var1; var4 < var2; var4++) {
         String var5 = var0[var4];

         if (ALL.equalsIgnoreCase(var5)) {
            var3.addAll(all());
            continue;
         }

         Category var6 = category(var5);

         if (var6 != null) {
            for (Module var8 : all()) {
               if (var8.f() == var6) {
                  var3.add(var8);
               }
            }

            continue;
         }

         Module var9 = ExpoCommands.module(var5);

         if (var9 != null) {
            var3.add(var9);
         } else {
            UNRESOLVED.add(var5);
         }
      }

      return new ArrayList<Module>(var3);
   }

   public static List<Module> all() {
      List<Module> var0 = new ArrayList<Module>();

      if (ModuleManager.S != null) {
         for (Module var2 : ModuleManager.S) {
            if (var2 != null && var2.b() != null
                && !var2.b().startsWith(ExpoModuleRegistry.PLACEHOLDER_PREFIX)) {
               var0.add(var2);
            }
         }
      }

      return var0;
   }

   static Category category(String var0) {
      Category[] var1 = Category.values();

      for (int var2 = 0; var2 < var1.length; var2++) {
         if (var1[var2].c().equalsIgnoreCase(var0) || var1[var2].toString().equalsIgnoreCase(var0)) {
            return var1[var2];
         }
      }

      return null;
   }

   public static List<String> pool() {
      List<String> var0 = new ArrayList<String>();
      var0.add(ALL);
      Category[] var1 = Category.values();

      for (int var2 = 0; var2 < var1.length; var2++) {
         var0.add(var1[var2].c());
      }

      for (Module var4 : all()) {
         var0.add(var4.b());
      }

      return var0;
   }

   public static void reportUnresolved() {
      for (int var0 = 0; var0 < UNRESOLVED.size(); var0++) {
         ExpoCommands.chat("§cNo module or category named §f" + UNRESOLVED.get(var0) + "§c.");
      }
   }
}
