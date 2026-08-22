package Expo.internal.restore;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.util.HashSet;
import java.util.Set;

public final class ExpoGuiText {
   public static final String TITLE = "Expo";

   public static final String TAB_INFORMATION = "Information";
   public static final String TAB_CONFIG = "Config";
   public static final String TAB_MODULES = "Modules";
   public static final String TAB_TERMINAL = "Terminal";
   public static final String TAB_CHANGELOG = "Changelog";

   public static final String RELEASE = System.getProperty("expo.gui.release", "OpenSource");
   public static final String BUILD = System.getProperty("expo.gui.build", "");
   public static final String CHANGELOG_HEADER =
      BUILD.isEmpty() ? "#ChangeLog" : "#ChangeLog " + BUILD;
   public static final String CHANGE_PREFIX = "[=] ";

   public static final String USER_PREFIX = "User: ";
   public static final String TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
   public static final String BLURB =
      "Fully enjoy the features of this software by exploring commands and this window!";

   public static final String BTN_REFRESH = "Refresh";
   public static final String BTN_IMPORT = "Import";
   public static final String BTN_LOAD = "Load";
   public static final String BTN_SAVE = "Save";
   public static final String BTN_DELETE = "Delete";
   public static final String BTN_RESET = "Reset to default";
   public static final String BTN_OPEN_FOLDER = "Open folder";

   public static final String BTN_ENABLED = "ENABLED";
   public static final String BTN_DISABLED = "DISABLED";
   public static final String BIND_PREFIX = "Bind: ";
   public static final String BIND_NONE = "NONE";
   public static final String BTN_REFRESH_SAVE = "Refresh/save";
   public static final String SETTINGS_SUFFIX = " Settings";
   public static final String NO_SETTINGS = "There is no available settings for this module";

   public static final String SAVE_TITLE = "Save Config";
   public static final String SAVE_PROMPT = "Enter the config name to save:";
   public static final String SAVE_CANCELLED = "Save cancelled or invalid name.";
   public static final String CFG_QUOTE = "The config \"";
   public static final String CFG_SAVED = "\" has been saved successfully.";
   public static final String CFG_LOADED = "\" has been loaded";
   public static final String CFG_DELETED = "\" has been deleted";
   public static final String CFG_DEFAULTED = "The config has been set to default";
   public static final String CANCELLED = "Cancelled";
   public static final String SAVED = "Saved";
   public static final String CHOOSE_FILE = "Choose a file";
   public static final String CHOOSE_FILE_PROMPT = "Please choose a file";
   public static final String JSON_FILTER = "JSON file";
   public static final String JSON_EXT = "json";
   public static final String JSON_SUFFIX = ".json";
   public static final String DEFAULT_NAME = "default";

   public static final String TERMINAL_INPUT_BORDER = "Command Input";

   public static final String ICON = "/icons/riddlejoker.png";

   public static final int FRAME_W = 900;
   public static final int FRAME_H = 650;
   public static final int PAD = 10;
   public static final int ROW_HGAP = 15;
   public static final int ROW_VGAP = 5;
   public static final int LIST_W = 200;

   public static final java.awt.Color CATEGORY_BG = new java.awt.Color(0x2d2d32);
   public static final java.awt.Color STATE_OFF = new java.awt.Color(0xcb4848);
   public static final java.awt.Color STATE_ON = new java.awt.Color(0x40bf50);
   public static final int CATEGORY_SIZE = size("expo.gui.categorySize", 15);
   public static final int MODULE_SIZE = size("expo.gui.moduleSize", 15);

   private static int size(String key, int fallback) {
      try {
         String v = System.getProperty(key);

         if (v != null) {
            int n = Integer.parseInt(v.trim());

            if (n >= 6 && n <= 48) {
               return n;
            }
         }
      } catch (Throwable t) {
      }

      return fallback;
   }

   private static final String[] FAMILIES = {
      "Microsoft YaHei UI", "Noto Sans CJK", "PingFang SC", "SimHei", "SansSerif"
   };

   private static String family;

   private ExpoGuiText() {
   }

   public static Font font(int style, int size) {
      return new Font(family(), style, size);
   }

   private static String family() {
      if (family != null) {
         return family;
      }

      Set<String> have = new HashSet<String>();

      try {
         String[] all = GraphicsEnvironment.getLocalGraphicsEnvironment()
                                           .getAvailableFontFamilyNames();

         for (int i = 0; i < all.length; i++) {
            have.add(all[i]);
         }
      } catch (Throwable t) {
      }

      for (int i = 0; i < FAMILIES.length; i++) {
         if (have.contains(FAMILIES[i])) {
            family = FAMILIES[i];
            return family;
         }
      }

      family = Font.SANS_SERIF;
      return family;
   }
}
