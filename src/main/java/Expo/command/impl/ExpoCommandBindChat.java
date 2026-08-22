package Expo.command.impl;

import Expo.ExpoClient;
import Expo.command.Command;
import Expo.command.ExpoCommands;
import Expo.internal.restore.ExpoCommandData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.lwjgl.input.Keyboard;











// add code
public final class ExpoCommandBindChat extends Command {

   private static final String DESC =
      "§eBind a chat message to a specific key to send it later (Support \\n)";
   private static final String USAGE_HEADER = "Usage: ";
   private static final String[] USAGE = {
      "  .bindchat clear",
      "  .bindchat <chat message> <key>",
      "  .bindchat remove <key...>",
      "  .bindchat list",
   };

   @Override
   public boolean J() {
      return false;
   }

   @Override
   public String[] e(long var1) {
      return new String[]{"bindchat", "bc"};
   }

   @Override
   public void h(long var1) {
      ExpoCommands.chat(DESC);
      ExpoCommands.chat("§7" + USAGE_HEADER);

      for (int var3 = 0; var3 < USAGE.length; var3++) {
         ExpoCommands.chat("§f" + USAGE[var3]);
      }

      ExpoCommands.chat("§8" + map().size() + " bind(s). Key names are LWJGL names, e.g. R, "
                        + "LSHIFT, NUMPAD0.");
   }

   @Override
   public void j(String[] var1, long var2) {
      if (var1.length == 1 && "list".equalsIgnoreCase(var1[0])) {
         list();
         return;
      }

      if (var1.length == 1 && "clear".equalsIgnoreCase(var1[0])) {
         clear();
         return;
      }

      if (var1.length >= 1 && "remove".equalsIgnoreCase(var1[0])) {
         if (var1.length < 2) {
            ExpoCommands.chat("§cUsage: §f.bindchat remove <key...>");
         } else {
            remove(var1);
         }

         return;
      }

      if (var1.length < 2) {
         this.h(0L);
         return;
      }

      add(var1);
   }

   @Override
   public List g(String[] var1, int var2, long var3) {
      List var5 = new ArrayList();

      if (var2 <= 1) {
         var5.addAll(Arrays.asList("list", "clear", "remove"));
      }

      return var5;
   }

   private static Map<Integer, String> map() {
      if (ExpoClient.H == null) {
         ExpoClient.H = new LinkedHashMap<Integer, String>();
      }

      return ExpoClient.H;
   }

   private static void add(String[] var0) {
      int var1 = parseKey(var0[var0.length - 1]);

      if (var1 <= 0) {
         ExpoCommands.chat("§cUnknown key §f" + var0[var0.length - 1]
                           + "§c. Use an LWJGL key name.");
         return;
      }

      String[] var2 = Arrays.copyOf(var0, var0.length - 1);
      StringBuilder var3 = new StringBuilder();

      for (int var4 = 0; var4 < var2.length; var4++) {
         if (var4 > 0) {
            var3.append(' ');
         }

         var3.append(var2[var4]);
      }

      String var5 = var3.toString();

      if (var5.trim().isEmpty()) {
         ExpoCommands.chat("§cThe message is empty.");
         return;
      }

      String var6 = map().put(Integer.valueOf(var1), var5);
      ExpoCommands.chat((var6 == null ? "§aBound " : "§aRebound ") + "§f" + keyName(var1)
                        + "§a -> §r" + var5);
      save();
   }

   private static void remove(String[] var0) {
      int var1 = 0;

      for (int var2 = 1; var2 < var0.length; var2++) {
         int var3 = parseKey(var0[var2]);

         if (var3 <= 0) {
            ExpoCommands.chat("§cUnknown key §f" + var0[var2] + "§c.");
            continue;
         }

         if (map().remove(Integer.valueOf(var3)) == null) {
            ExpoCommands.chat("§7Nothing was bound to §f" + keyName(var3));
         } else {
            ExpoCommands.chat("§c- §f" + keyName(var3));
            var1++;
         }
      }

      if (var1 > 0) {
         save();
      }
   }

   private static void clear() {
      int var0 = map().size();

      if (var0 == 0) {
         ExpoCommands.chat("§7There is no chat bind.");
         return;
      }

      map().clear();
      ExpoCommands.chat("§7" + var0 + " chat bind(s) cleared.");
      save();
   }

   private static void list() {
      if (map().isEmpty()) {
         ExpoCommands.chat("§7There is no chat bind.");
         return;
      }

      for (Map.Entry<Integer, String> var1 : map().entrySet()) {
         ExpoCommands.chat("§f" + keyName(var1.getKey().intValue()) + " §8-> §r"
                           + var1.getValue());
      }

      ExpoCommands.chat("§7" + map().size() + " chat bind(s).");
   }

   private static void save() {
      ExpoCommands.chat(ExpoCommandData.saveChatBinds()
                        ? "§8Saved to the \"chatBinds\" key of current.json."
                        : "§cThe write to current.json FAILED; this bind is memory-only.");
   }

   private static int parseKey(String var0) {
      int var1 = Keyboard.getKeyIndex(var0.toUpperCase());

      if (var1 != Keyboard.KEY_NONE) {
         return var1;
      }

      try {
         return Integer.parseInt(var0);
      } catch (NumberFormatException var2) {
         return -1;
      }
   }

   private static String keyName(int var0) {
      String var1 = Keyboard.getKeyName(var0);
      return (var1 == null ? String.valueOf(var0) : var1).toUpperCase();
   }
}
