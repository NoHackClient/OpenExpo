package Expo.internal.restore;

import Expo.ExpoClient;
import Expo.module.impl.configuration.Teams;
import Expo.ui.screen.MainMenuTheme;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import net.minecraft.client.Minecraft;


public final class ExpoCommandData {

   public static final String MENU = "menu.txt";
   public static final String MENU_MUSIC = "menu_music.txt";
   public static final String FRIENDS = "friends.txt";
   public static final String ENEMIES = "enemies.txt";
   public static final String CURRENT = "current.json";
   static final String CHAT_BINDS = "chatBinds";


   private ExpoCommandData() {
   }

   public static File dirFile() {
      String var0 = System.getProperty("expo.config");

      if (var0 != null) {
         File var1 = new File(var0).getParentFile();

         if (var1 != null) {
            return var1;
         }
      }

      try {
         Minecraft var2 = Minecraft.getMinecraft();

         if (var2 != null && var2.mcDataDir != null) {
            return new File(var2.mcDataDir, "Expo");
         }
      } catch (Throwable var3) {
      }

      return new File("Expo");
   }

   static Path dir() {
      Path var0 = dirFile().toPath();

      try {
         Files.createDirectories(var0);
      } catch (Throwable var2) {
      }

      return var0;
   }

   public static Path resolve(String var0) {
      return dir().resolve(var0);
   }

   public static boolean exists(String var0) {
      try {
         return Files.isRegularFile(resolve(var0));
      } catch (Throwable var2) {
         return false;
      }
   }

   static String readText(String var0) {
      try {
         Path var1 = resolve(var0);

         if (!Files.isRegularFile(var1)) {
            return null;
         }

         return new String(Files.readAllBytes(var1), StandardCharsets.UTF_8);
      } catch (Throwable var2) {
         return null;
      }
   }

   static boolean writeText(String var0, String var1) {
      try {
         Files.write(resolve(var0), (var1 == null ? "" : var1).getBytes(StandardCharsets.UTF_8),
                     StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING,
                     StandardOpenOption.WRITE);
         return true;
      } catch (Throwable var3) {
         return false;
      }
   }

   static List<String> readLines(String var0) {
      List<String> var1 = new ArrayList<String>();
      String var2 = readText(var0);

      if (var2 != null) {
         String[] var3 = var2.split("\r\n|\r|\n");

         for (int var4 = 0; var4 < var3.length; var4++) {
            String var5 = var3[var4].trim();

            if (!var5.isEmpty()) {
               var1.add(var5);
            }
         }
      }

      return var1;
   }

   static boolean writeLines(String var0, Collection<String> var1) {
      StringBuilder var2 = new StringBuilder();

      if (var1 != null) {
         for (String var4 : var1) {
            if (var4 != null && !var4.trim().isEmpty()) {
               if (var2.length() > 0) {
                  var2.append('\n');
               }

               var2.append(var4.trim());
            }
         }
      }

      return writeText(var0, var2.toString());
   }

   public static JsonObject readJson(String var0) {
      try {
         Path var1 = resolve(var0);

         if (!Files.isRegularFile(var1)) {
            return null;
         }

         String var2 = new String(Files.readAllBytes(var1), StandardCharsets.UTF_8);
         JsonElement var3 = new JsonParser().parse(var2);
         return var3 != null && var3.isJsonObject() ? var3.getAsJsonObject() : null;
      } catch (Throwable var4) {
         return null;
      }
   }

   static boolean writeJson(String var0, JsonObject var1) {
      try {
         Path var2 = resolve(var0);
         Path var3 = var2.resolveSibling(var0 + ".tmp");
         String var4 = new GsonBuilder().setPrettyPrinting().create().toJson(var1);
         Files.write(var3, var4.getBytes(StandardCharsets.UTF_8),
                     StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING,
                     StandardOpenOption.WRITE);

         if (Files.isRegularFile(var2)) {
            Path var5 = var2.resolveSibling(var0 + ".bak");
            Files.deleteIfExists(var5);
            Files.move(var2, var5);
         }

         Files.move(var3, var2);
         return true;
      } catch (Throwable var6) {
         return false;
      }
   }

   public static boolean patchCurrent(String var0, JsonElement var1) {
      JsonObject var2 = readJson(CURRENT);

      if (var2 == null) {
         var2 = new JsonObject();
      }

      var2.add(var0, var1);
      return writeJson(CURRENT, var2);
   }

   static JsonObject currentChild(String var0) {
      JsonObject var1 = readJson(CURRENT);

      if (var1 == null) {
         return null;
      }

      JsonElement var2 = var1.get(var0);
      return var2 != null && var2.isJsonObject() ? var2.getAsJsonObject() : null;
   }


   public static boolean saveFriends() {
      try {
         return writeLines(FRIENDS, Teams.a());
      } catch (Throwable var1) {
         return false;
      }
   }

   public static boolean saveEnemies() {
      try {
         return writeLines(ENEMIES, Teams.B());
      } catch (Throwable var1) {
         return false;
      }
   }

   public static boolean saveMenu() {
      try {
         return writeText(MENU, MainMenuTheme.mode.Y());
      } catch (Throwable var1) {
         return false;
      }
   }

   public static boolean saveMenuMusic() {
      try {
         return writeText(MENU_MUSIC, String.valueOf(MainMenuTheme.music.c()));
      } catch (Throwable var1) {
         return false;
      }
   }

   public static boolean saveChatBinds() {
      try {
         Map<Integer, String> var0 = ExpoClient.H;
         JsonObject var1 = new JsonObject();

         if (var0 != null) {
            for (Map.Entry<Integer, String> var3 : var0.entrySet()) {
               if (var3.getKey() != null) {
                  var1.addProperty(String.valueOf(var3.getKey()), var3.getValue());
               }
            }
         }

         return patchCurrent(CHAT_BINDS, var1);
      } catch (Throwable var4) {
         return false;
      }
   }


   public static void load() {
   }

   private static String loadNames() {
      int var0 = 0;
      int var1 = 0;

      try {
         List<String> var2 = readLines(FRIENDS);

         for (int var3 = 0; var3 < var2.size(); var3++) {
            Teams.E(var2.get(var3));
            var0++;
         }
      } catch (Throwable var4) {
         return "friends.txt FAILED (" + var4 + ")";
      }

      try {
         List<String> var5 = readLines(ENEMIES);

         for (int var6 = 0; var6 < var5.size(); var6++) {
            Teams.C(var5.get(var6));
            var1++;
         }
      } catch (Throwable var7) {
         return "friends.txt=" + var0 + " enemies.txt FAILED (" + var7 + ")";
      }

      return "friends.txt=" + var0 + " enemies.txt=" + var1;
   }

   private static String loadMenu() {
      String var0 = readText(MENU);
      String var1 = readText(MENU_MUSIC);
      StringBuilder var2 = new StringBuilder();

      try {
         if (var0 != null && !var0.trim().isEmpty()) {
            MainMenuTheme.mode.i(var0.trim().toUpperCase());
            var2.append("menu.txt=").append(MainMenuTheme.mode.Y());
         } else {
            var2.append("menu.txt absent");
         }
      } catch (Throwable var4) {
         var2.append("menu.txt FAILED (").append(var4).append(')');
      }

      try {
         if (var1 != null && !var1.trim().isEmpty()) {
            MainMenuTheme.music.v(Boolean.parseBoolean(var1.trim()), 0L);
            var2.append(" menu_music.txt=").append(MainMenuTheme.music.c());
         } else {
            var2.append(" menu_music.txt absent");
         }
      } catch (Throwable var5) {
         var2.append(" menu_music.txt FAILED (").append(var5).append(')');
      }

      return var2.toString();
   }

   private static String loadChatBinds() {
      try {
         if (ExpoClient.H == null) {
            ExpoClient.H = new java.util.LinkedHashMap<Integer, String>();
         }

         JsonObject var0 = currentChild(CHAT_BINDS);

         if (var0 == null) {
            return "chatBinds absent";
         }

         int var1 = 0;

         for (Map.Entry<String, JsonElement> var3 : var0.entrySet()) {
            try {
               ExpoClient.H.put(Integer.valueOf(Integer.parseInt(var3.getKey())),
                        var3.getValue().getAsString());
               var1++;
            } catch (Throwable var4) {
            }
         }

         return "chatBinds=" + var1;
      } catch (Throwable var5) {
         return "chatBinds FAILED (" + var5 + ")";
      }
   }
}
