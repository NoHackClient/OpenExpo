package Expo.internal.restore;

import Expo.module.Module;
import Expo.module.ModuleManager;
import Expo.setting.Setting;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.ColorSetting;
import Expo.setting.settings.ModeSetting;
import Expo.setting.settings.NumberSetting;
import Expo.setting.settings.PercentageSetting;
import Expo.setting.settings.TextSetting;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.client.Minecraft;

public final class ExpoConfig {

   public static final long MODULE_I_CARRIER = 20724619369162L;

   private static final String FILE = "current.json";

   private ExpoConfig() {
   }

   public static String apply(List<String> pending) {
      int configured = 0;
      int matched = 0;
      int meta = 0;
      int applied = 0;
      int skippedPlaceholder = 0;
      int unmatched = 0;
      String note;

      try {
         File f = locate();

         if (f == null) {
            note = "Expo.config NOT APPLIED -- no " + FILE + " found; every module keeps its factory state";
            pending.add(note);
            return note;
         }

         JsonObject root;
         Reader r = new InputStreamReader(new FileInputStream(f), "UTF-8");

         try {
            root = new JsonParser().parse(r).getAsJsonObject();
         } finally {
            r.close();
         }

         for (Map.Entry<String, JsonElement> e : root.entrySet()) {
            if (e.getValue().isJsonObject() && e.getValue().getAsJsonObject().has("status")) {
               configured++;
            }
         }

         List<String> on = new ArrayList<String>();

         for (Module m : ModuleManager.S) {
            if (m == null) {
               continue;
            }

            String name = m.b();

            if (name == null || name.startsWith(ExpoModuleRegistry.PLACEHOLDER_PREFIX)) {
               skippedPlaceholder++;
               continue;
            }

            JsonElement entry = root.get(name);

            if (entry == null || !entry.isJsonObject()) {
               unmatched++;
               continue;
            }

            JsonObject o = entry.getAsJsonObject();

            if (!o.has("status")) {
               unmatched++;
               continue;
            }

            matched++;
            boolean status = o.get("status").getAsBoolean();

            m.I(MODULE_I_CARRIER, status);

            if (status) {
               applied++;
               on.add(name);
            }

            meta += writeInt(m, "j", o, "keyBind");
            meta += writeBool(m, "w", o, "visible");
            meta += writeBool(m, "q", o, "suffix-visible");
         }

         note = "Expo.config applied from " + f.getPath()
                + " -- configured=" + configured + " matched=" + matched
                + " enabled=" + applied + " skipped_placeholder=" + skippedPlaceholder
                + " unmatched=" + unmatched + " meta=" + meta
                + " field_failures=" + fieldFailures + " on=" + on;

         if (fieldFailures > 0) {
            synchronized (fieldFailureNotes) {
               for (String bad : fieldFailureNotes) {
                  pending.add(bad);
               }
            }
         }
      } catch (Throwable t) {
         note = "Expo.config FAILED (" + t + ") -- every module keeps its factory state";
      }

      pending.add(note);
      return note;
   }

   public static JsonObject read() {
      try {
         File f = locate();

         if (f == null) {
            return null;
         }

         Reader r = new InputStreamReader(new FileInputStream(f), "UTF-8");

         try {
            return new JsonParser().parse(r).getAsJsonObject();
         } finally {
            r.close();
         }
      } catch (Throwable t) {
         return null;
      }
   }

   public static volatile int fieldFailures;

   public static final List<String> fieldFailureNotes = new ArrayList<String>();

   private static void fieldFailure(String field, Class<?> want, String key, Throwable t, String detail) {
      fieldFailures++;
      String note = "ExpoConfig cannot persist '" + key + "': Module." + field + " ("
                    + want.getSimpleName() + ") " + (t != null ? "threw " + t : detail)
                    + " -- this key is silently dropped from every save AND load; "
                    + "if the field was renamed, Expo/internal/restore/ExpoNameMap must be "
                    + "updated in the same edit";

      synchronized (fieldFailureNotes) {
         if (fieldFailureNotes.size() < 32) {
            fieldFailureNotes.add(note);
         }
      }

      ExpoDiag.warn("cfgfield|" + field + '|' + key, note);
   }

   private static int writeBool(Module m, String field, JsonObject o, String key) {
      if (!o.has(key)) {
         return 0;
      }

      try {
         java.lang.reflect.Field f = Module.class.getDeclaredField(field);

         if (f.getType() != boolean.class) {
            fieldFailure(field, boolean.class, key, null,
                         "is a " + f.getType().getName() + ", not boolean");
            return 0;
         }

         f.setAccessible(true);
         f.setBoolean(m, o.get(key).getAsBoolean());

         if (f.getBoolean(m) != o.get(key).getAsBoolean()) {
            fieldFailure(field, boolean.class, key, null, "did not keep the value written to it");
            return 0;
         }

         return 1;
      } catch (Throwable t) {
         fieldFailure(field, boolean.class, key, t, null);
         return 0;
      }
   }

   private static int writeInt(Module m, String field, JsonObject o, String key) {
      if (!o.has(key)) {
         return 0;
      }

      try {
         java.lang.reflect.Field f = Module.class.getDeclaredField(field);

         if (f.getType() != int.class) {
            fieldFailure(field, int.class, key, null,
                         "is a " + f.getType().getName() + ", not int");
            return 0;
         }

         f.setAccessible(true);
         f.setInt(m, o.get(key).getAsInt());

         if (f.getInt(m) != o.get(key).getAsInt()) {
            fieldFailure(field, int.class, key, null, "did not keep the value written to it");
            return 0;
         }

         return 1;
      } catch (Throwable t) {
         fieldFailure(field, int.class, key, t, null);
         return 0;
      }
   }

   public static final String DESCRIPTION_KEY = "description";

   public static final String DEFAULT_DESCRIPTION = "Description of your config here";

   private static final String[] COMMON = {"status", "keyBind", "visible", "suffix-visible"};

   private static final int K_BOOL = 0;
   private static final int K_INT = 1;
   private static final int K_FLOAT = 2;
   private static final int K_STR = 3;

   private static final Object SAVE_LOCK = new Object();

   public static volatile int saveCount;

   public static volatile String lastSaveNote = "no save attempted";

   public static volatile SaveResult lastResult;

   private static volatile Map<Setting, String> boot;

   public static volatile int bootSnapshot = -1;

   public static int snapshotBoot() {
      Map<Setting, String> snap = new IdentityHashMap<Setting, String>();

      try {
         List<Module> all = ModuleManager.S == null ? new ArrayList<Module>() : ModuleManager.S;

         for (Module m : all) {
            if (m == null) {
               continue;
            }

            List<Setting> live;

            try {
               live = m.w();
            } catch (Throwable t) {
               continue;
            }

            if (live == null) {
               continue;
            }

            for (Setting s : live) {
               if (s == null) {
                  continue;
               }

               int kind = kindOf(s);
               JsonPrimitive v = kind < 0 ? null : value(s);

               if (v != null) {
                  snap.put(s, canon(kind, v));
               }
            }
         }
      } catch (Throwable t) {
      }

      boot = snap;
      bootSnapshot = snap.size();
      return bootSnapshot;
   }

   public static final class SaveResult {

      public boolean ok;
      public String path = "?";
      public int modules;
      public int blocks;
      public int created;
      public int skippedUnnamed;
      public int commonKeys;
      public int settingKeys;
      public int settingsOutsideSchema;
      public int modeValuesPreserved;
      public int loadGapsPreserved;
      public int topLevelBefore;
      public int topLevelAfter;
      public final List<Object[]> written = new ArrayList<Object[]>();
      public final List<String> outside = new ArrayList<String>();
      public final List<String> preserved = new ArrayList<String>();
      public final List<String> loadGaps = new ArrayList<String>();
      public String note = "not run";

      @Override
      public String toString() {
         return "ok=" + this.ok + " file=" + this.path + " modules=" + this.modules
                + " blocks=" + this.blocks + " created=" + this.created
                + " skipped_unnamed=" + this.skippedUnnamed
                + " common=" + this.commonKeys + " settings=" + this.settingKeys
                + " settings_outside_schema=" + this.settingsOutsideSchema
                + " mode_values_preserved=" + this.modeValuesPreserved
                + " load_gaps_preserved=" + this.loadGapsPreserved
                + " top_level=" + this.topLevelBefore + "+" + this.created + "->"
                + this.topLevelAfter + " note=" + this.note;
      }
   }

   public static SaveResult save(String name) {
      SaveResult r = new SaveResult();

      synchronized (SAVE_LOCK) {
         try {
            File f = target(name);
            r.path = f.getPath();
            JsonObject root = parse(f);

            if (root == null) {
               root = parse(sibling(f, ".bak"));
            }

            if (root == null) {
               root = new JsonObject();
            }

            if (!root.has(DESCRIPTION_KEY) || !root.get(DESCRIPTION_KEY).isJsonPrimitive()) {
               root.addProperty(DESCRIPTION_KEY, DEFAULT_DESCRIPTION);
            }

            r.topLevelBefore = root.entrySet().size();

            List<Module> all = ModuleManager.S == null ? new ArrayList<Module>() : ModuleManager.S;

            for (Module m : all) {
               if (m == null) {
                  continue;
               }

               r.modules++;

               if (!ExpoModuleRegistry.isConfigPersistable(m)) {
                  r.skippedUnnamed++;
                  continue;
               }

               String n = m.b();
               JsonElement e = root.get(n);
               JsonObject block;

               if (e != null && e.isJsonObject()) {
                  block = e.getAsJsonObject();
               } else {
                  block = new JsonObject();
                  root.add(n, block);
                  r.created++;
               }

               r.blocks++;
               put(r, block, n, COMMON[0], K_BOOL, new JsonPrimitive(Boolean.valueOf(m.o())));
               put(r, block, n, COMMON[1], K_INT, new JsonPrimitive(Integer.valueOf(m.h())));
               put(r, block, n, COMMON[2], K_BOOL, new JsonPrimitive(Boolean.valueOf(m.D())));
               put(r, block, n, COMMON[3], K_BOOL, new JsonPrimitive(Boolean.valueOf(m.r())));
               r.commonKeys += 4;
               settings(r, block, n, m);
            }

            r.topLevelAfter = root.entrySet().size();
            r.ok = write(f, root);
            r.note = r.ok ? "written" : "write failed";
         } catch (Throwable t) {
            r.ok = false;
            r.note = "THREW " + t;
         }

         lastResult = r;
         lastSaveNote = r.toString();
         saveCount++;
      }

      return r;
   }

   private static void settings(SaveResult r, JsonObject block, String module, Module m) {
      List<Setting> live;

      try {
         live = m.w();
      } catch (Throwable t) {
         return;
      }

      if (live == null) {
         return;
      }

      Set<String> done = new LinkedHashSet<String>();

      for (Setting s : live) {
         if (s == null) {
            continue;
         }

         String label = null;

         try {
            label = s.B();
         } catch (Throwable t) {
         }

         if (label == null || label.length() == 0) {
            continue;
         }

         int kind = kindOf(s);
         JsonPrimitive v = kind < 0 ? null : value(s);

         if (v == null) {
            continue;
         }

         String key = block.has(label) ? label : settingKey(label);

         if (!block.has(key)) {
            r.settingsOutsideSchema++;
            r.outside.add(module + '.' + label);
            continue;
         }

         if (!done.add(key)) {
            r.settingsOutsideSchema++;
            r.outside.add(module + '.' + label + " (key " + key + " already taken)");
            continue;
         }

         JsonElement cur = block.get(key);

         if (unrepresentable(s, cur)) {
            r.modeValuesPreserved++;
            r.preserved.add(module + '.' + key + '=' + cur.getAsString());
            continue;
         }

         Map<Setting, String> snap = boot;
         String was = snap == null ? null : snap.get(s);
         String now = canon(kind, v);

         if (was != null && was.equals(now) && !canon(kind, cur).equals(now)) {
            r.loadGapsPreserved++;
            r.loadGaps.add(module + '.' + key + " file=" + canon(kind, cur) + " memory=" + now);
            continue;
         }

         put(r, block, module, key, kind, v);
         r.settingKeys++;
      }
   }

   private static void put(SaveResult r, JsonObject block, String module, String key,
                           int kind, JsonPrimitive v) {
      block.add(key, v);
      r.written.add(new Object[]{module, key, Integer.valueOf(kind), canon(kind, v)});
   }

   private static boolean unrepresentable(Setting s, JsonElement cur) {
      if (!(s instanceof ModeSetting) || cur == null || !cur.isJsonPrimitive()
          || !cur.getAsJsonPrimitive().isString()) {
         return false;
      }

      try {
         List<String> opts = ((ModeSetting)s).S();
         return opts != null && !opts.isEmpty() && !opts.contains(cur.getAsString());
      } catch (Throwable t) {
         return false;
      }
   }

   public static String settingKey(String label) {
      if (label == null) {
         return null;
      }

      int i = label.lastIndexOf('|');
      return i < 0 ? label : label.substring(i + 1);
   }

   private static int kindOf(Setting s) {
      if (s instanceof BooleanSetting) {
         return K_BOOL;
      }

      if (s instanceof PercentageSetting) {
         return K_INT;
      }

      if (s instanceof NumberSetting) {
         return K_FLOAT;
      }

      if (s instanceof ModeSetting || s instanceof ColorSetting || s instanceof TextSetting) {
         return K_STR;
      }

      return -1;
   }

   private static JsonPrimitive value(Setting s) {
      try {
         if (s instanceof BooleanSetting) {
            return new JsonPrimitive(Boolean.valueOf(((BooleanSetting)s).c()));
         }

         if (s instanceof PercentageSetting) {
            return new JsonPrimitive(Integer.valueOf(((PercentageSetting)s).k()));
         }

         if (s instanceof NumberSetting) {
            float v = ((NumberSetting)s).L();
            return Float.isNaN(v) || Float.isInfinite(v) ? null : new JsonPrimitive(Float.valueOf(v));
         }

         String t = s instanceof ModeSetting
                    ? ((ModeSetting)s).Y()
                    : s instanceof ColorSetting ? ((ColorSetting)s).Q() : ((TextSetting)s).X();
         return t == null ? null : new JsonPrimitive(t);
      } catch (Throwable t) {
         return null;
      }
   }

   private static String canon(int kind, JsonElement e) {
      if (e == null || !e.isJsonPrimitive()) {
         return "<absent>";
      }

      JsonPrimitive p = e.getAsJsonPrimitive();

      try {
         if (kind == K_BOOL) {
            return String.valueOf(p.getAsBoolean());
         }

         if (kind == K_INT) {
            return String.valueOf(p.getAsInt());
         }

         if (kind == K_FLOAT) {
            return String.valueOf(p.getAsFloat());
         }

         return p.getAsString();
      } catch (Throwable t) {
         return "<unreadable " + p + '>';
      }
   }

   public static List<String> verify(SaveResult r, String corrupt) {
      List<String> bad = new ArrayList<String>();
      JsonObject root = parse(new File(r.path));

      if (root == null) {
         bad.add("file unreadable: " + r.path);
         return bad;
      }

      for (Object[] rec : r.written) {
         String module = (String)rec[0];
         String key = (String)rec[1];
         int kind = ((Integer)rec[2]).intValue();
         JsonElement be = root.get(module);
         String got = "<no block>";

         if (be != null && be.isJsonObject()) {
            got = canon(kind, be.getAsJsonObject().get(key));
         }

         String want = (String)rec[3];

         if (corrupt != null && corrupt.equals(module + '/' + key)) {
            want = want + "#CORRUPT";
         }

         if (!want.equals(got)) {
            bad.add(module + '.' + key + " expected " + want + " got " + got);
         }
      }

      return bad;
   }

   public static File target(String name) {
      String override = System.getProperty("expo.config");
      String n = name == null || name.length() == 0 ? FILE : name;

      if (n.indexOf('/') >= 0 || n.indexOf('\\') >= 0 || n.indexOf(':') >= 0
          || n.contains("..")) {
         n = FILE;
      }

      if (!n.toLowerCase().endsWith(".json")) {
         n = n + ".json";
      }

      if (override != null) {
         File f = new File(override);

         if (FILE.equals(n) || f.getName().equalsIgnoreCase(n)) {
            return f;
         }

         File p = f.getParentFile();

         if (p != null) {
            return new File(p, n);
         }
      }

      File dir = null;

      try {
         dir = Minecraft.getMinecraft().mcDataDir;
      } catch (Throwable t) {
      }

      File d = dir == null ? new File("Expo") : new File(dir, "Expo");

      try {
         d.mkdirs();
      } catch (Throwable t) {
      }

      return new File(d, n);
   }

   private static File sibling(File f, String suffix) {
      File a = f.getAbsoluteFile();
      return new File(a.getParentFile(), a.getName() + suffix);
   }

   private static JsonObject parse(File f) {
      if (f == null || !f.isFile()) {
         return null;
      }

      Reader r = null;

      try {
         r = new InputStreamReader(new FileInputStream(f), "UTF-8");
         JsonElement e = new JsonParser().parse(r);
         return e != null && e.isJsonObject() ? e.getAsJsonObject() : null;
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

   private static boolean write(File f, JsonObject root) {
      File tmp = sibling(f, ".tmp");
      Writer w = null;

      try {
         w = new OutputStreamWriter(new FileOutputStream(tmp), "UTF-8");
         new GsonBuilder().setPrettyPrinting().create().toJson(root, w);
         w.close();
         w = null;

         if (f.isFile()) {
            File bak = sibling(f, ".bak");

            if (bak.isFile()) {
               bak.delete();
            }

            f.renameTo(bak);
         }

         return tmp.renameTo(f);
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

   private static File locate() {
      String override = System.getProperty("expo.config");

      if (override != null) {
         File f = new File(override);
         return f.isFile() ? f : null;
      }

      File dir = null;

      try {
         dir = Minecraft.getMinecraft().mcDataDir;
      } catch (Throwable t) {
      }

      File[] candidates = {
         dir == null ? null : new File(new File(dir, "Expo"), FILE),
         new File(new File("Expo"), FILE),
         new File(FILE),
      };

      for (File c : candidates) {
         if (c != null && c.isFile()) {
            return c;
         }
      }

      return null;
   }
}
