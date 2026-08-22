package Expo.internal.restore;

import Expo.command.ExpoCommands;
import Expo.module.Category;
import Expo.module.Module;
import Expo.module.ModuleManager;
import Expo.setting.Setting;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.ColorSetting;
import Expo.setting.settings.ModeSetting;
import Expo.setting.settings.NumberSetting;
import Expo.setting.settings.PercentageSetting;
import Expo.ui.swing.ConfigManagerWindow;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class ExpoGuiData {
   public static final String DESCRIPTION_KEY = "description";
   public static final String STATUS_KEY = "status";
   public static final String KEYBIND_KEY = "keyBind";
   public static final String VISIBLE_KEY = "visible";
   public static final String SUFFIX_KEY = "suffix-visible";

   public static final String DEFAULT_DESCRIPTION = "Description of your config here";

   private ExpoGuiData() {
   }

   public static List<Module> modules() {
      List<Module> out = new ArrayList<Module>();

      try {
         if (ModuleManager.S == null) {
            return out;
         }

         for (Module m : ModuleManager.S) {
            if (m == null) {
               continue;
            }

            String n = name(m);

            if (n == null || n.length() == 0
                || n.startsWith(ExpoModuleRegistry.PLACEHOLDER_PREFIX)) {
               continue;
            }

            out.add(m);
         }
      } catch (Throwable t) {
      }

      return out;
   }

   public static String name(Module m) {
      try {
         return m.b();
      } catch (Throwable t) {
         return null;
      }
   }

   public static String category(Module m) {
      try {
         Category c = m.f();

         if (c != null) {
            return c.c();
         }
      } catch (Throwable t) {
      }

      return "Misc";
   }

   public static String[] categories() {
      try {
         Category[] v = Category.values();
         String[] out = new String[v.length];

         for (int i = 0; i < v.length; i++) {
            out[i] = v[i].c();
         }

         return out;
      } catch (Throwable t) {
         return new String[0];
      }
   }

   public static String description(Module m) {
      String s = (String)readField(m, Module.class, "W");
      return s == null ? "" : s;
   }

   public static boolean enabled(Module m) {
      try {
         return m.o();
      } catch (Throwable t) {
         return false;
      }
   }

   public static boolean setEnabled(Module m, boolean on) {
      try {
         m.I(ExpoConfig.MODULE_I_CARRIER, on);
      } catch (Throwable t) {
      }

      return enabled(m);
   }

   public static int keyBind(Module m) {
      try {
         return m.h();
      } catch (Throwable t) {
         return 0;
      }
   }

   public static boolean setKeyBind(Module m, int code) {
      return writeInt(m, Module.class, "j", code);
   }

   public static boolean visible(Module m) {
      try {
         return m.D();
      } catch (Throwable t) {
         return false;
      }
   }

   public static boolean suffixVisible(Module m) {
      try {
         return m.r();
      } catch (Throwable t) {
         return false;
      }
   }

   public static List<Setting> settings(Module m) {
      try {
         List<Setting> l = m.w();

         if (l != null) {
            return l;
         }
      } catch (Throwable t) {
      }

      return new ArrayList<Setting>();
   }

   public static String settingName(Setting s) {
      try {
         String n = s.B();
         return n == null ? "" : n;
      } catch (Throwable t) {
         return "";
      }
   }

   public static String configKey(Map<String, Object> block, Setting s) {
      String label = settingName(s);

      if (block != null && block.containsKey(label)) {
         return label;
      }

      String k = ExpoConfig.settingKey(label);
      return k == null ? "" : k;
   }

   public static Object settingValue(Setting s) {
      try {
         if (s instanceof BooleanSetting) {
            return Boolean.valueOf(((BooleanSetting)s).c());
         }

         if (s instanceof ModeSetting) {
            return ((ModeSetting)s).Y();
         }

         if (s instanceof NumberSetting) {
            return Float.valueOf(((NumberSetting)s).L());
         }

         if (s instanceof PercentageSetting) {
            return Integer.valueOf(((PercentageSetting)s).k());
         }

         if (s instanceof ColorSetting) {
            return ((ColorSetting)s).Q();
         }

         if (s instanceof Expo.setting.settings.TextSetting) {
            return ((Expo.setting.settings.TextSetting)s).X();
         }
      } catch (Throwable t) {
      }

      return null;
   }

   public static boolean setBoolean(BooleanSetting s, boolean v) {
      try {
         s.v(v, 0L);
         return s.c() == v;
      } catch (Throwable t) {
         return writeBoolField(s, BooleanSetting.class, "h", v);
      }
   }

   public static boolean setMode(ModeSetting s, String option) {
      try {
         s.i(option);

         if (option.equals(s.Y())) {
            return true;
         }
      } catch (Throwable t) {
      }

      List<String> opts = modeOptions(s);
      int idx = opts.indexOf(option);

      if (idx < 0) {
         return false;
      }

      boolean ok = writeField(s, ModeSetting.class, "Y", option);
      ok &= writeInt(s, ModeSetting.class, "p", idx);
      return ok;
   }

   public static List<String> modeOptions(ModeSetting s) {
      try {
         List<String> l = s.S();

         if (l != null) {
            return l;
         }
      } catch (Throwable t) {
      }

      return new ArrayList<String>();
   }

   public static boolean setNumber(NumberSetting s, float v) {
      float lo = numberMin(s);
      float hi = numberMax(s);

      if (v < lo) {
         v = lo;
      }

      if (v > hi) {
         v = hi;
      }

      try {
         Field f = NumberSetting.class.getDeclaredField("z");
         f.setAccessible(true);
         f.setFloat(s, v);
         return true;
      } catch (Throwable t) {
         return false;
      }
   }

   public static float numberValue(NumberSetting s) {
      try {
         return s.L();
      } catch (Throwable t) {
         return 0.0F;
      }
   }

   public static float numberMin(NumberSetting s) {
      try {
         return s.i();
      } catch (Throwable t) {
         return 0.0F;
      }
   }

   public static float numberMax(NumberSetting s) {
      try {
         return s.F();
      } catch (Throwable t) {
         return 1.0F;
      }
   }

   public static float numberStep(NumberSetting s) {
      try {
         float st = s.U();
         return st > 0.0F ? st : 0.1F;
      } catch (Throwable t) {
         return 0.1F;
      }
   }

   public static boolean setPercentage(PercentageSetting s, int v) {
      if (v < 0) {
         v = 0;
      }

      if (v > 100) {
         v = 100;
      }

      try {
         s.d(v);
         return s.k() == v;
      } catch (Throwable t) {
         return false;
      }
   }

   public static int percentageValue(PercentageSetting s) {
      try {
         return s.k();
      } catch (Throwable t) {
         return 0;
      }
   }

   public static String colorValue(ColorSetting s) {
      try {
         String v = s.Q();
         return v == null ? "FFFFFF" : v;
      } catch (Throwable t) {
         return "FFFFFF";
      }
   }

   public static boolean setColor(ColorSetting s, String hex) {
      try {
         s.e(hex);
         return hex.equals(s.Q());
      } catch (Throwable t) {
         return false;
      }
   }

   public static String textValue(Expo.setting.settings.TextSetting s) {
      try {
         String v = s.X();

         return v == null ? "" : v;
      } catch (Throwable t) {
         return "";
      }
   }

   public static boolean setText(Expo.setting.settings.TextSetting s, String v) {
      try {
         s.O(v);
         return v.equals(s.X());
      } catch (Throwable t) {
         return false;
      }
   }

   public static String keyName(int code) {
      if (code == 0) {
         return ExpoGuiText.BIND_NONE;
      }

      try {
         Class<?> k = Class.forName("org.lwjgl.input.Keyboard");
         Method m = k.getMethod("getKeyName", int.class);
         Object n = m.invoke(null, Integer.valueOf(code));

         if (n != null) {
            return String.valueOf(n);
         }
      } catch (Throwable t) {
      }

      return "#" + code;
   }

   public static int keyIndex(String lwjglName) {
      if (lwjglName == null) {
         return 0;
      }

      try {
         Class<?> k = Class.forName("org.lwjgl.input.Keyboard");
         Method m = k.getMethod("getKeyIndex", String.class);
         Object n = m.invoke(null, lwjglName);

         if (n instanceof Integer) {
            return ((Integer)n).intValue();
         }
      } catch (Throwable t) {
      }

      return 0;
   }

   public static String sessionName() {
      try {
         Class<?> mc = Class.forName("net.minecraft.client.Minecraft");
         Object inst = mc.getMethod("getMinecraft").invoke(null);

         if (inst != null) {
            Object session = mc.getMethod("getSession").invoke(inst);

            if (session != null) {
               Object n = session.getClass().getMethod("getUsername").invoke(session);

               if (n != null && String.valueOf(n).length() > 0) {
                  return String.valueOf(n);
               }
            }
         }
      } catch (Throwable t) {
      }

      return null;
   }

   public static String userName() {
      String s = sessionName();

      return s != null ? s : "b";
   }

   public static File configDir() {
      String over = System.getProperty("expo.gui.dir");

      if (over != null) {
         File f = new File(over);
         f.mkdirs();
         return f;
      }

      File base = null;

      try {
         Class<?> mc = Class.forName("net.minecraft.client.Minecraft");
         Object inst = mc.getMethod("getMinecraft").invoke(null);

         if (inst != null) {
            Object d = mc.getField("mcDataDir").get(inst);

            if (d instanceof File) {
               base = (File)d;
            }
         }
      } catch (Throwable t) {
      }

      File dir = base == null ? new File("Expo") : new File(base, "Expo");

      try {
         dir.mkdirs();
      } catch (Throwable t) {
      }

      return dir;
   }

   public static List<File> configFiles() {
      List<File> out = new ArrayList<File>();

      try {
         File[] fs = configDir().listFiles();

         if (fs != null) {
            for (int i = 0; i < fs.length; i++) {
               if (fs[i].isFile() && fs[i].getName().toLowerCase().endsWith(ExpoGuiText.JSON_SUFFIX)) {
                  out.add(fs[i]);
               }
            }
         }
      } catch (Throwable t) {
      }

      java.util.Collections.sort(out, new java.util.Comparator<File>() {
         public int compare(File a, File b) {
            return a.getName().compareToIgnoreCase(b.getName());
         }
      });

      return out;
   }

   public static String read(File f) {
      Reader r = null;

      try {
         r = new InputStreamReader(new FileInputStream(f), "UTF-8");
         StringBuilder b = new StringBuilder();
         char[] buf = new char[4096];
         int k;

         while ((k = r.read(buf)) > 0) {
            b.append(buf, 0, k);
         }

         return b.toString();
      } catch (Throwable t) {
         return null;
      } finally {
         if (r != null) {
            try {
               r.close();
            } catch (Throwable t) {
            }
         }
      }
   }

   public static boolean write(File f, String text) {
      Writer w = null;

      try {
         w = new OutputStreamWriter(new FileOutputStream(f), "UTF-8");
         w.write(text);
         return true;
      } catch (Throwable t) {
         return false;
      } finally {
         if (w != null) {
            try {
               w.close();
            } catch (Throwable t) {
            }
         }
      }
   }

   public static Map<String, Object> merged(File target) {
      Map<String, Object> root = null;

      try {
         if (target != null && target.isFile()) {
            root = ExpoGuiJson.parseObject(read(target));
         }
      } catch (Throwable t) {
      }

      if (root == null) {
         root = new LinkedHashMap<String, Object>();
      }

      if (!(root.get(DESCRIPTION_KEY) instanceof String)) {
         root.put(DESCRIPTION_KEY, DEFAULT_DESCRIPTION);
      }

      for (Module m : modules()) {
         String n = name(m);
         Map<String, Object> block = ExpoGuiJson.asMap(root.get(n));

         if (block == null) {
            block = new LinkedHashMap<String, Object>();
            root.put(n, block);
         }

         block.put(STATUS_KEY, Boolean.valueOf(enabled(m)));
         block.put(KEYBIND_KEY, Integer.valueOf(keyBind(m)));
         block.put(VISIBLE_KEY, Boolean.valueOf(visible(m)));
         block.put(SUFFIX_KEY, Boolean.valueOf(suffixVisible(m)));

         for (Setting s : settings(m)) {
            if (s == null) {
               continue;
            }

            String sn = configKey(block, s);
            Object v = settingValue(s);

            if (sn.length() > 0 && v != null) {
               block.put(sn, v);
            }
         }
      }

      return root;
   }

   public static Map<String, Object> snapshot(String description) {
      Map<String, Object> root = new LinkedHashMap<String, Object>();
      root.put(DESCRIPTION_KEY, description == null ? DEFAULT_DESCRIPTION : description);

      for (Module m : modules()) {
         Map<String, Object> block = new LinkedHashMap<String, Object>();
         block.put(STATUS_KEY, Boolean.valueOf(enabled(m)));
         block.put(KEYBIND_KEY, Integer.valueOf(keyBind(m)));
         block.put(VISIBLE_KEY, Boolean.valueOf(visible(m)));
         block.put(SUFFIX_KEY, Boolean.valueOf(suffixVisible(m)));

         for (Setting s : settings(m)) {
            if (s == null) {
               continue;
            }

            String n = ExpoConfig.settingKey(settingName(s));
            Object v = settingValue(s);

            if (n.length() > 0 && v != null && !block.containsKey(n)) {
               block.put(n, v);
            }
         }

         root.put(name(m), block);
      }

      return root;
   }

   public static int[] applyFile(File f) {
      int[] counts = new int[3];
      String text = read(f);

      if (text == null) {
         return counts;
      }

      Map<String, Object> root = ExpoGuiJson.parseObject(text);

      if (root == null) {
         return counts;
      }

      for (Module m : modules()) {
         Map<String, Object> block = ExpoGuiJson.asMap(root.get(name(m)));

         if (block == null) {
            continue;
         }

         counts[0]++;

         if (block.containsKey(STATUS_KEY)) {
            setEnabled(m, ExpoGuiJson.asBool(block.get(STATUS_KEY), enabled(m)));
            counts[1]++;
         }

         if (block.containsKey(KEYBIND_KEY)
             && setKeyBind(m, (int)ExpoGuiJson.asNum(block.get(KEYBIND_KEY), keyBind(m)))) {
            counts[1]++;
         }

         if (block.containsKey(VISIBLE_KEY)
             && writeBoolField(m, Module.class, "w", ExpoGuiJson.asBool(block.get(VISIBLE_KEY), visible(m)))) {
            counts[1]++;
         }

         if (block.containsKey(SUFFIX_KEY)
             && writeBoolField(m, Module.class, "q", ExpoGuiJson.asBool(block.get(SUFFIX_KEY), suffixVisible(m)))) {
            counts[1]++;
         }

         for (Setting s : settings(m)) {
            if (s == null) {
               continue;
            }

            String n = configKey(block, s);

            if (n.length() == 0 || !block.containsKey(n)) {
               continue;
            }

            if (applySetting(s, block.get(n))) {
               counts[2]++;
            }
         }
      }

      return counts;
   }

   public static boolean applySetting(Setting s, Object raw) {
      try {
         if (s instanceof BooleanSetting) {
            return setBoolean((BooleanSetting)s, ExpoGuiJson.asBool(raw, ((BooleanSetting)s).c()));
         }

         if (s instanceof ModeSetting) {
            return raw != null && setMode((ModeSetting)s, String.valueOf(raw));
         }

         if (s instanceof NumberSetting) {
            return setNumber((NumberSetting)s,
                             (float)ExpoGuiJson.asNum(raw, numberValue((NumberSetting)s)));
         }

         if (s instanceof PercentageSetting) {
            return setPercentage((PercentageSetting)s,
                                 (int)ExpoGuiJson.asNum(raw, percentageValue((PercentageSetting)s)));
         }

         if (s instanceof ColorSetting) {
            return raw != null && setColor((ColorSetting)s, String.valueOf(raw));
         }

         if (s instanceof Expo.setting.settings.TextSetting) {
            if (raw == null) {
               return false;
            }

            ((Expo.setting.settings.TextSetting)s).O(String.valueOf(raw));
            return String.valueOf(raw).equals(((Expo.setting.settings.TextSetting)s).X());
         }
      } catch (Throwable t) {
      }

      return false;
   }

   public static List<String> transcript() {
      try {
         if (ConfigManagerWindow.D != null) {
            return ConfigManagerWindow.D;
         }
      } catch (Throwable t) {
      }

      return null;
   }

   public static List<String> bootNotes() {
      try {
         return ExpoBootstrap.PENDING;
      } catch (Throwable t) {
         return new ArrayList<String>();
      }
   }

   public static boolean dispatch(String line) {
      try {
         return ExpoCommands.dispatch(line);
      } catch (Throwable t) {
         return false;
      }
   }

   private static Object readField(Object owner, Class<?> decl, String name) {
      try {
         Field f = decl.getDeclaredField(name);
         f.setAccessible(true);
         return f.get(owner);
      } catch (Throwable t) {
         return null;
      }
   }

   private static boolean writeField(Object owner, Class<?> decl, String name, Object value) {
      try {
         Field f = decl.getDeclaredField(name);
         f.setAccessible(true);
         f.set(owner, value);
         return true;
      } catch (Throwable t) {
         return false;
      }
   }

   private static boolean writeInt(Object owner, Class<?> decl, String name, int value) {
      try {
         Field f = decl.getDeclaredField(name);

         if (f.getType() != int.class) {
            return false;
         }

         f.setAccessible(true);
         f.setInt(owner, value);
         return f.getInt(owner) == value;
      } catch (Throwable t) {
         return false;
      }
   }

   private static boolean writeBoolField(Object owner, Class<?> decl, String name, boolean value) {
      try {
         Field f = decl.getDeclaredField(name);

         if (f.getType() != boolean.class) {
            return false;
         }

         f.setAccessible(true);
         f.setBoolean(owner, value);
         return f.getBoolean(owner) == value;
      } catch (Throwable t) {
         return false;
      }
   }
}
