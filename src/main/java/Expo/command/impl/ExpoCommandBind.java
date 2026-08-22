package Expo.command.impl;

import Expo.command.Command;
import Expo.command.ExpoCommands;
import Expo.internal.restore.ExpoModuleRegistry;
import Expo.module.Module;
import Expo.module.ModuleManager;
import java.util.ArrayList;
import java.util.List;
import org.lwjgl.input.Keyboard;











// add code
public final class ExpoCommandBind extends Command {

   public static final long MODULE_Z_CARRIER = 118276941480361L;

   private static final int[] PROBE_CODES = {1, 48, 57};

   private static final int MOUSE_BASE = 1000;
   private static final int MOUSE_KEYCODE_BASE = -100;

   private static Boolean trusted;
   private static String gateNote = "not run";

   @Override
   public boolean J() {
      return false;
   }

   @Override
   public String[] e(long var1) {
      return new String[]{"bind", "b"};
   }

   private static final String DESC =
      "§eBind module with a specific key to toggle it later";
   private static final String USAGE_HEADER = "Usage: ";
   private static final String[] USAGE = {
      "  .bind remove <key...>",
      "  .bind <module...> <key>",
      "  .bind clear",
      "  .bind list",
   };

   @Override
   public void h(long var1) {
      ExpoCommands.chat(DESC);
      ExpoCommands.chat("§7" + USAGE_HEADER);

      for (int var4 = 0; var4 < USAGE.length; var4++) {
         ExpoCommands.chat("§f" + USAGE[var4]);
      }

      ExpoCommands.chat("§8Key names are LWJGL names, e.g. R, LSHIFT, NUMPAD0.");

      int var3 = ExpoCommands.placeholderCount();

      if (var3 > 0) {
         ExpoCommands.chat("§8" + var3 + " module(s) have no confirmed name yet and cannot be bound.");
      }
   }

   @Override
   public void j(String[] var1, long var2) {
      if (var1.length >= 1 && "list".equalsIgnoreCase(var1[0])) {
         list();
         return;
      }

      if (var1.length >= 1 && "clear".equalsIgnoreCase(var1[0])) {
         clear();
         return;
      }

      if (var1.length >= 1 && "remove".equalsIgnoreCase(var1[0])) {
         if (var1.length < 2) {
            ExpoCommands.chat("§cUsage: §f.bind remove <key...>");
         } else {
            removeKeys(var1);
         }

         return;
      }

      if (var1.length < 2) {
         this.h(0L);
         return;
      }

      if (var1.length > 2) {
         bindMany(var1);
         return;
      }

      Module var4 = ExpoCommands.module(var1[0]);

      if (var4 == null) {
         ExpoCommands.chat("§cNo module named §f" + var1[0] + "§c.");
         return;
      }

      int var5 = parseKey(var1[1]);

      if (var5 < 0) {
         ExpoCommands.chat("§cUnknown key §f" + var1[1] + "§c. Use an LWJGL key name, or §fnone§c.");
         return;
      }

      if (var4.S()) {
         ExpoCommands.chat("§c" + var4.b() + " has a fixed key binding; it cannot be changed.");
         return;
      }

      if (!gateOk()) {
         ExpoCommands.chat("§cRefusing to write a key binding: the Module.z carrier gate did not pass.");
         ExpoCommands.chat("§8" + gateNote);
         return;
      }

      try {
         var4.z(MODULE_Z_CARRIER, var5);
      } catch (Throwable var7) {
         ExpoCommands.chat("§cModule.z threw: " + var7);
         return;
      }

      int var6 = var4.h();

      if (var6 == 0) {
         ExpoCommands.chat("§a" + var4.b() + "§7 is now unbound.");
      } else if (var6 == var5) {
         ExpoCommands.chat("§a" + var4.b() + "§7 bound to §f" + keyName(var6) + "§7.");
      } else {
         ExpoCommands.chat("§a" + var4.b() + "§7 bound; stored code §f" + var6
                           + "§7 (§f" + keyName(var6) + "§7), remapped from " + var5 + ".");
      }

      ExpoCommands.chat("§8Not saved yet -- use §7.config save <name>§8 to persist it.");
   }

   @Override
   public List g(String[] var1, int var2, long var3) {
      List var5 = new ArrayList();

      if (var2 <= 1) {
         if (ModuleManager.S != null) {
            for (Module var7 : ModuleManager.S) {
               if (var7 != null && var7.b() != null
                   && !var7.b().startsWith(ExpoModuleRegistry.PLACEHOLDER_PREFIX)) {
                  var5.add(var7.b());
               }
            }
         }

         var5.add("list");
         var5.add("clear");
         var5.add("remove");
      } else if (var2 == 2) {
         var5.add("none");

         for (int var6 = 1; var6 < 256; var6++) {
            String var8 = Keyboard.getKeyName(var6);

            if (var8 != null) {
               var5.add(var8);
            }
         }
      }

      return var5;
   }

   private static void bindMany(String[] var0) {
      int var1 = parseKey(var0[var0.length - 1]);

      if (var1 < 0) {
         ExpoCommands.chat("§cUnknown key §f" + var0[var0.length - 1]
                           + "§c. Use an LWJGL key name, or §fnone§c.");
         return;
      }

      if (!gateOk()) {
         ExpoCommands.chat("§cRefusing to write a key binding: the Module.z carrier gate did not pass.");
         ExpoCommands.chat("§8" + gateNote);
         return;
      }

      int var2 = 0;

      for (int var3 = 0; var3 < var0.length - 1; var3++) {
         Module var4 = ExpoCommands.module(var0[var3]);

         if (var4 == null) {
            ExpoCommands.chat("§cNo module named §f" + var0[var3] + "§c.");
         } else if (var4.S()) {
            ExpoCommands.chat("§c" + var4.b() + " has a fixed key binding; skipped.");
         } else if (write(var4, var1)) {
            var2++;
         }
      }

      ExpoCommands.chat("§7" + var2 + " module(s) bound to §f" + keyName(var1) + "§7.");

      if (var2 > 0) {
         ExpoCommands.chat("§8Not saved yet -- use §7.config save <name>§8 to persist it.");
      }
   }

   private static void removeKeys(String[] var0) {
      if (!gateOk()) {
         ExpoCommands.chat("§cRefusing to write a key binding: the Module.z carrier gate did not pass.");
         ExpoCommands.chat("§8" + gateNote);
         return;
      }

      int var1 = 0;

      for (int var2 = 1; var2 < var0.length; var2++) {
         int var3 = parseKey(var0[var2]);

         if (var3 <= 0) {
            ExpoCommands.chat("§cUnknown key §f" + var0[var2] + "§c.");
            continue;
         }

         if (ModuleManager.S != null) {
            for (Module var5 : ModuleManager.S) {
               if (var5 != null && !var5.S() && var5.h() == var3 && write(var5, 0)) {
                  ExpoCommands.chat("§7" + var5.b() + " §8unbound from §f" + keyName(var3));
                  var1++;
               }
            }
         }
      }

      ExpoCommands.chat("§7" + var1 + " binding(s) removed.");

      if (var1 > 0) {
         ExpoCommands.chat("§8Not saved yet -- use §7.config save <name>§8 to persist it.");
      }
   }

   private static void clear() {
      if (!gateOk()) {
         ExpoCommands.chat("§cRefusing to write a key binding: the Module.z carrier gate did not pass.");
         ExpoCommands.chat("§8" + gateNote);
         return;
      }

      int var0 = 0;

      if (ModuleManager.S != null) {
         for (Module var2 : ModuleManager.S) {
            if (var2 != null && !var2.S() && var2.h() != 0 && write(var2, 0)) {
               var0++;
            }
         }
      }

      ExpoCommands.chat("§7" + var0 + " binding(s) cleared.");

      if (var0 > 0) {
         ExpoCommands.chat("§8Not saved yet -- use §7.config save <name>§8 to persist it.");
      }
   }

   private static boolean write(Module var0, int var1) {
      try {
         var0.z(MODULE_Z_CARRIER, var1);
         return true;
      } catch (Throwable var3) {
         ExpoCommands.chat("§cModule.z threw for " + var0.b() + ": " + var3);
         return false;
      }
   }

   private static void list() {
      int var0 = 0;

      if (ModuleManager.S != null) {
         for (Module var2 : ModuleManager.S) {
            if (var2 != null && var2.b() != null
                && !var2.b().startsWith(ExpoModuleRegistry.PLACEHOLDER_PREFIX)
                && var2.h() != 0) {
               ExpoCommands.chat("§7" + var2.b() + " §8-> §f" + keyName(var2.h()));
               var0++;
            }
         }
      }

      if (var0 == 0) {
         ExpoCommands.chat("§7No module is bound.");
      }
   }

   private static int parseKey(String var0) {
      if ("none".equalsIgnoreCase(var0) || "null".equalsIgnoreCase(var0) || "0".equals(var0)) {
         return 0;
      }

      int var1 = Keyboard.getKeyIndex(var0.toUpperCase());

      if (var1 != Keyboard.KEY_NONE) {
         return var1;
      }

      try {
         int var2 = Integer.parseInt(var0);
         return var2 >= 0 ? var2 : -1;
      } catch (NumberFormatException var3) {
         return -1;
      }
   }

   private static String keyName(int var0) {
      if (var0 == 0) {
         return "NONE";
      }

      String var1 = Keyboard.getKeyName(var0);
      return var1 == null ? String.valueOf(var0) : var1;
   }

   static synchronized boolean gateOk() {
      if (trusted != null) {
         return trusted.booleanValue();
      }

      boolean var0 = false;
      String var1;

      try {
         Module var2 = new Module(0L);
         var1 = "";

         for (int var3 = 0; var3 < PROBE_CODES.length; var3++) {
            var2.z(MODULE_Z_CARRIER, PROBE_CODES[var3]);

            if (var2.h() != PROBE_CODES[var3]) {
               var1 = "keyboard code " + PROBE_CODES[var3] + " stored as " + var2.h();
               break;
            }
         }

         if (var1.isEmpty()) {
            var2.z(MODULE_Z_CARRIER, MOUSE_BASE);
            int var4 = var2.h();
            var2.z(MODULE_Z_CARRIER, MOUSE_BASE + 1);
            int var5 = var2.h();

            if (var4 == MOUSE_KEYCODE_BASE && var5 == MOUSE_KEYCODE_BASE + 1) {
               var0 = true;
               var1 = "canonical (identity for keyboard codes; " + MOUSE_BASE + "->" + var4
                      + ", " + (MOUSE_BASE + 1) + "->" + var5 + ")";
            } else {
               var1 = "mouse fold is " + MOUSE_BASE + "->" + var4 + ", " + (MOUSE_BASE + 1)
                      + "->" + var5 + "; expected " + MOUSE_KEYCODE_BASE + ", "
                      + (MOUSE_KEYCODE_BASE + 1);
            }
         }
      } catch (Throwable var6) {
         var1 = "threw " + var6;
      }

      gateNote = "Module.z carrier gate: " + (var0 ? "TRUSTED" : "REFUSED") + " -- " + var1;
      System.out.println("[EXPOCMD] " + gateNote);
      trusted = Boolean.valueOf(var0);
      return var0;
   }

   static String gateNote() {
      return gateNote;
   }
}
