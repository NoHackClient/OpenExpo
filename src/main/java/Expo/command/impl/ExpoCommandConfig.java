package Expo.command.impl;

import Expo.command.Command;
import Expo.command.ExpoCommands;
import Expo.internal.restore.ExpoCommandData;
import Expo.internal.restore.ExpoConfig;
import Expo.internal.restore.ExpoModuleRegistry;
import Expo.module.Module;
import Expo.module.ModuleManager;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraft.client.Minecraft;











// add code
public final class ExpoCommandConfig extends Command {

   private static final String SUFFIX = ".json";

   @Override
   public boolean J() {
      return false;
   }

   // add code
   @Override
   public String[] e(long var1) {
      return new String[]{"config", "c", "cfg"};
   }

   // add code
   @Override
   public void h(long var1) {
      ExpoCommands.chat("§7Usage:");
      ExpoCommands.chat("§f  .config folder");
      ExpoCommands.chat("§f  .config list");
      ExpoCommands.chat("§f  .config description [description of config]");
      ExpoCommands.chat("§f  .config save <name>");
      ExpoCommands.chat("§f  .config load <file>");
      File var3 = dir();
      ExpoCommands.chat("§8Directory: " + (var3 == null ? "not found" : var3.getPath()));
      ExpoCommands.chat("§8The stock save/load also take an optional "
                        + "[module... | category... | \"visibility\" | \"suffix\" | "
                        + "\"clickgui\"...] selector; that part is NOT restored -- save and "
                        + "load here always cover every named module.");
   }

   // add code
   private static void folder() {
      File var0 = dir();

      if (var0 == null || !var0.isDirectory()) {
         ExpoCommands.chat("§cThe Expo config directory does not exist yet.");
         return;
      }

      try {
         if (!java.awt.Desktop.isDesktopSupported()) {
            ExpoCommands.chat("§cThis JVM has no Desktop support. Path: §f" + var0.getPath());
            return;
         }

         java.awt.Desktop.getDesktop().open(var0);
         ExpoCommands.chat("§7Opened §f" + var0.getPath());
      } catch (Throwable var2) {
         ExpoCommands.chat("§cCould not open the folder (" + var2 + "). Path: §f"
                           + var0.getPath());
      }
   }

   // add code
   private static void description(String[] var0) {
      JsonObject var1 = ExpoCommandData.readJson(ExpoCommandData.CURRENT);

      if (var0.length < 2) {
         JsonElement var2 = var1 == null ? null : var1.get("description");
         ExpoCommands.chat("§7Description: §f"
                           + (var2 == null ? "(none)" : var2.getAsString()));
         return;
      }

      StringBuilder var3 = new StringBuilder();

      for (int var4 = 1; var4 < var0.length; var4++) {
         if (var4 > 1) {
            var3.append(' ');
         }

         var3.append(var0[var4]);
      }

      if (ExpoCommandData.patchCurrent("description",
                                       new com.google.gson.JsonPrimitive(var3.toString()))) {
         ExpoCommands.chat("§aDescription set to §f" + var3);
      } else {
         ExpoCommands.chat("§cCould not write current.json.");
      }
   }

   @Override
   public void j(String[] var1, long var2) {
      String var4 = var1[0];

      // add code
      if ("folder".equalsIgnoreCase(var4)) {
         folder();
      } else if ("description".equalsIgnoreCase(var4)) {
         description(var1);
      } else if ("list".equalsIgnoreCase(var4)) {
         list();
      } else if ("load".equalsIgnoreCase(var4)) {
         if (var1.length < 2) {
            ExpoCommands.chat("§cUsage: §f.config load <name>");
         } else {
            load(var1[1]);
         }
      } else if ("save".equalsIgnoreCase(var4)) {
         if (var1.length < 2) {
            ExpoCommands.chat("§cUsage: §f.config save <name>");
         } else {
            save(var1[1]);
         }
      } else {
         this.h(0L);
      }
   }

   @Override
   public List g(String[] var1, int var2, long var3) {
      List var5 = new ArrayList();

      if (var2 <= 1) {
         var5.addAll(Arrays.asList("folder", "list", "description", "save", "load"));
      } else if (var2 == 2) {
         var5.addAll(configNames());
      }

      return var5;
   }

   private static void list() {
      List<String> var0 = configNames();

      if (var0.isEmpty()) {
         ExpoCommands.chat("§7No config found in " + (dir() == null ? "?" : dir().getPath()));
         return;
      }

      ExpoCommands.chat("§7Configs: §f" + join(var0));
   }

   private static List<String> configNames() {
      List<String> var0 = new ArrayList<String>();
      File var1 = dir();

      if (var1 != null) {
         File[] var2 = var1.listFiles();

         if (var2 != null) {
            for (int var3 = 0; var3 < var2.length; var3++) {
               String var4 = var2[var3].getName();

               if (var2[var3].isFile() && var4.toLowerCase().endsWith(SUFFIX)) {
                  var0.add(var4.substring(0, var4.length() - SUFFIX.length()));
               }
            }
         }
      }

      return var0;
   }

   private static void load(String var0) {
      File var1 = resolve(var0);

      if (var1 == null || !var1.isFile()) {
         ExpoCommands.chat("§cNo config named §f" + var0 + "§c. Try §f.config list");
         return;
      }

      JsonObject var2 = read(var1);

      if (var2 == null) {
         ExpoCommands.chat("§cCould not parse " + var1.getName());
         return;
      }

      int var3 = 0;
      int var4 = 0;
      int var5 = 0;
      int var6 = 0;

      for (Module var8 : (ModuleManager.S == null ? new ArrayList<Module>() : ModuleManager.S)) {
         if (var8 == null || !ExpoModuleRegistry.isConfigPersistable(var8)) {
            var6++;
            continue;
         }

         JsonElement var9 = var2.get(var8.b());

         if (var9 == null || !var9.isJsonObject()) {
            var5++;
            continue;
         }

         JsonObject var10 = var9.getAsJsonObject();

         if (var10.has("status")) {
            var8.I(ExpoConfig.MODULE_I_CARRIER, var10.get("status").getAsBoolean());
            var3++;
         }

         if (var10.has("keyBind") && !var8.S() && gate()) {
            try {
               var8.z(ExpoCommandBind.MODULE_Z_CARRIER, var10.get("keyBind").getAsInt());
               var4++;
            } catch (Throwable var11) {
            }
         }
      }

      ExpoCommands.chat("§aLoaded §f" + var1.getName() + "§a: " + var3 + " status, "
                        + var4 + " keybind, " + var5 + " missing, " + var6 + " unnamed skipped.");
   }

   private static void save(String var0) {
      String var1 = mergeRefusal();

      if (var1 != null) {
         ExpoCommands.chat("§cRefusing to save: " + var1);
         return;
      }

      File var2 = resolve(var0);

      if (var2 == null) {
         ExpoCommands.chat("§cCould not locate the Expo config directory.");
         return;
      }

      File var3 = var2.isFile() ? var2 : new File(var2.getParentFile(), "current" + SUFFIX);

      if (!var3.isFile()) {
         ExpoCommands.chat("§cNo template to merge into (neither " + var2.getName()
                           + " nor current.json exists). Refusing to invent a config.");
         return;
      }

      JsonObject var4 = read(var3);

      if (var4 == null) {
         ExpoCommands.chat("§cCould not parse the template " + var3.getName() + "; refusing to overwrite.");
         return;
      }

      int var5 = 0;
      int var6 = 0;
      boolean var7 = gate();

      for (Module var9 : (ModuleManager.S == null ? new ArrayList<Module>() : ModuleManager.S)) {
         if (var9 == null || !ExpoModuleRegistry.isConfigPersistable(var9)) {
            continue;
         }

         JsonElement var10 = var4.get(var9.b());

         if (var10 == null || !var10.isJsonObject()) {
            var6++;
            continue;
         }

         JsonObject var11 = var10.getAsJsonObject();
         var11.addProperty("status", Boolean.valueOf(var9.o()));

         if (var7) {
            var11.addProperty("keyBind", Integer.valueOf(var9.h()));
         }

         var5++;
      }

      int var12 = var4.entrySet().size();

      if (!write(var2, var4)) {
         ExpoCommands.chat("§cWrite failed; " + var2.getName() + " was left untouched.");
         return;
      }

      ExpoCommands.chat("§aSaved §f" + var2.getName() + "§a: updated " + var5
                        + " module(s), preserved " + var12 + " top-level key(s)"
                        + (var6 > 0 ? ", " + var6 + " named module(s) had no entry and were skipped" : "")
                        + (var7 ? "" : " §c(keyBind NOT written: carrier gate refused)"));
   }

   public static String mergeRefusal() {
      if (ModuleManager.S == null || ModuleManager.S.isEmpty()) {
         return "no module is published (tD.S is empty), so a save would record nothing.";
      }

      if (ExpoModuleRegistry.persistableNames().isEmpty()) {
         return "no module has a confirmed name yet; every key would be a placeholder.";
      }

      for (Module var0 : ModuleManager.S) {
         if (var0 != null && ExpoModuleRegistry.isConfigPersistable(var0)
             && var0.b().startsWith(ExpoModuleRegistry.PLACEHOLDER_PREFIX)) {
            return "invariant broken: " + var0.b() + " is marked persistable but is a placeholder.";
         }
      }

      return null;
   }

   private static File dir() {
      String var0 = System.getProperty("expo.config");

      if (var0 != null) {
         File var1 = new File(var0);
         File var2 = var1.getParentFile();

         if (var2 != null && var2.isDirectory()) {
            return var2;
         }
      }

      try {
         File var3 = Minecraft.getMinecraft().mcDataDir;

         if (var3 != null) {
            File var4 = new File(var3, "Expo");

            if (var4.isDirectory()) {
               return var4;
            }
         }
      } catch (Throwable var5) {
      }

      File var6 = new File("Expo");
      return var6.isDirectory() ? var6 : null;
   }

   private static File resolve(String var0) {
      File var1 = dir();

      if (var1 == null || var0 == null || var0.isEmpty()) {
         return null;
      }

      if (var0.indexOf(47) >= 0 || var0.indexOf(92) >= 0 || var0.indexOf(58) >= 0
          || var0.contains("..")) {
         return null;
      }

      String var2 = var0.toLowerCase().endsWith(SUFFIX) ? var0 : var0 + SUFFIX;
      return new File(var1, var2);
   }

   private static JsonObject read(File var0) {
      Reader var1 = null;

      try {
         var1 = new InputStreamReader(new FileInputStream(var0), "UTF-8");
         JsonElement var2 = new JsonParser().parse(var1);
         return var2 != null && var2.isJsonObject() ? var2.getAsJsonObject() : null;
      } catch (Throwable var3) {
         return null;
      } finally {
         if (var1 != null) {
            try {
               var1.close();
            } catch (Throwable var4) {
            }
         }
      }
   }

   private static boolean write(File var0, JsonObject var1) {
      File var2 = new File(var0.getParentFile(), var0.getName() + ".tmp");
      Writer var3 = null;

      try {
         var3 = new OutputStreamWriter(new FileOutputStream(var2), "UTF-8");
         new GsonBuilder().setPrettyPrinting().create().toJson(var1, var3);
         var3.close();
         var3 = null;

         if (var0.isFile()) {
            File var4 = new File(var0.getParentFile(), var0.getName() + ".bak");

            if (var4.isFile()) {
               var4.delete();
            }

            var0.renameTo(var4);
         }

         return var2.renameTo(var0);
      } catch (Throwable var5) {
         return false;
      } finally {
         if (var3 != null) {
            try {
               var3.close();
            } catch (Throwable var6) {
            }
         }
      }
   }

   private static boolean gate() {
      return ExpoCommandBind.gateOk();
   }

   private static String join(List<String> var0) {
      StringBuilder var1 = new StringBuilder();

      for (int var2 = 0; var2 < var0.size(); var2++) {
         if (var2 > 0) {
            var1.append(", ");
         }

         var1.append(var0.get(var2));
      }

      return var1.toString();
   }
}
