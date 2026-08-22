package Expo.command.impl;

import Expo.command.Command;
import Expo.command.ExpoCommands;
import Expo.internal.restore.ExpoCommandData;
import Expo.internal.restore.ExpoCommandSelect;
import Expo.internal.restore.ExpoConfig;
import Expo.module.Category;
import Expo.module.Module;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;

public final class ExpoCommandReset extends Command {
   private static final String DEFAULTS = "default.json";
   private static final String VISIBILITY = "visibility";
   private static final String SUFFIX = "suffix";

   @Override
   public boolean J() {
      return false;
   }

   @Override
   public String[] e(long var1) {
      return new String[]{"reset", "restet", "r"};
   }

   @Override
   public void h(long var1) {
      ExpoCommands.chat("§eReset module settings to default");
      ExpoCommands.chat("§7Usage:");
      ExpoCommands.chat("§f  .reset <modules... | category... | \"all\" | \"visibility\" "
                        + "| \"suffix\">");
      ExpoCommands.chat("§8Source: §7" + ExpoCommandData.dirFile().getPath() + "\\" + DEFAULTS
                        + "§8 " + (ExpoCommandData.exists(DEFAULTS) ? "(present)" : "(MISSING)"));
   }

   @Override
   public void j(String[] var1, long var2) {
      JsonObject var4 = ExpoCommandData.readJson(DEFAULTS);

      if (var4 == null) {
         ExpoCommands.chat("§cNo §f" + DEFAULTS + "§c in " + ExpoCommandData.dirFile().getPath()
                           + ". The stock reset reads a stored config; nothing is invented "
                           + "here, so there is nothing to reset to.");
         return;
      }

      boolean var5 = false;
      boolean var6 = false;
      List<Module> var7;

      if (var1.length == 1 && VISIBILITY.equalsIgnoreCase(var1[0])) {
         var5 = true;
         var7 = ExpoCommandSelect.all();
      } else if (var1.length == 1 && SUFFIX.equalsIgnoreCase(var1[0])) {
         var6 = true;
         var7 = ExpoCommandSelect.all();
      } else {
         var7 = ExpoCommandSelect.resolve(var1, 0, var1.length);
         ExpoCommandSelect.reportUnresolved();
      }

      if (var7.isEmpty()) {
         return;
      }

      int var8 = 0;
      int var9 = 0;
      int var10 = 0;
      boolean var11 = ExpoCommandBind.gateOk();

      for (int var12 = 0; var12 < var7.size(); var12++) {
         Module var13 = var7.get(var12);
         JsonElement var14 = var4.get(var13.b());

         if (var14 == null || !var14.isJsonObject()) {
            var10++;
            continue;
         }

         JsonObject var15 = var14.getAsJsonObject();
         boolean var16 = false;

         if (!var5 && !var6) {
            var16 |= status(var13, var15);
            var16 |= keyBind(var13, var15, var11);
         }

         if (!var6) {
            var16 |= visible(var13, var15);
         }

         if (!var5) {
            var16 |= suffix(var13, var15);
         }

         if (var16) {
            var8++;
         } else {
            var9++;
         }
      }

      ExpoCommands.chat("§aReset from §f" + DEFAULTS + "§a: " + var8 + " module(s) changed, "
                        + var9 + " already matched, " + var10 + " had no entry.");
      ExpoCommands.chat("§8Only status / keyBind / visible / suffix-visible are applied -- "
                        + "the per-module setting values have no proven writer here and are "
                        + "left alone.");

      if (var8 > 0) {
         ExpoCommands.chat("§8Not saved yet -- use §7.config save <name>§8 to persist it.");
      }
   }

   @Override
   public List g(String[] var1, int var2, long var3) {
      List var5 = new ArrayList(ExpoCommandSelect.pool());
      var5.add(VISIBILITY);
      var5.add(SUFFIX);
      return var5;
   }

   private static boolean status(Module var0, JsonObject var1) {
      if (!var1.has("status") || !var0.I()) {
         return false;
      }

      boolean var2 = var1.get("status").getAsBoolean();

      if (var0.o() == var2) {
         return false;
      }

      try {
         var0.I(ExpoConfig.MODULE_I_CARRIER, var2);
      } catch (Throwable var4) {
         return false;
      }

      return var0.o() == var2;
   }

   private static boolean keyBind(Module var0, JsonObject var1, boolean var2) {
      if (!var1.has("keyBind") || !var2 || var0.S()) {
         return false;
      }

      int var3 = var1.get("keyBind").getAsInt();

      if (var0.h() == var3) {
         return false;
      }

      try {
         var0.z(ExpoCommandBind.MODULE_Z_CARRIER, var3);
      } catch (Throwable var5) {
         return false;
      }

      return var0.h() == var3;
   }

   private static boolean visible(Module var0, JsonObject var1) {
      if (!var1.has("visible") || var0.S() || var0.f() == Category.Macro) {
         return false;
      }

      boolean var2 = var1.get("visible").getAsBoolean();

      if (var0.D() == var2) {
         return false;
      }

      try {
         var0.Y(0L, var2, (short)0);
      } catch (Throwable var4) {
         return false;
      }

      return var0.D() == var2;
   }

   private static boolean suffix(Module var0, JsonObject var1) {
      if (!var1.has("suffix-visible")) {
         return false;
      }

      boolean var2 = var1.get("suffix-visible").getAsBoolean();

      if (var0.r() == var2) {
         return false;
      }

      try {
         var0.C(var2);
      } catch (Throwable var4) {
         return false;
      }

      return var0.r() == var2;
   }
}
