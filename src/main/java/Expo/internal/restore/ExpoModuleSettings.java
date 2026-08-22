package Expo.internal.restore;

import Expo.module.Module;
import Expo.module.ModuleManager;
import Expo.setting.Setting;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public final class ExpoModuleSettings {

   private static final List<String> COMMON = new ArrayList<String>();

   static {
      COMMON.add("status");
      COMMON.add("keyBind");
      COMMON.add("visible");
      COMMON.add("suffix-visible");
   }

   private ExpoModuleSettings() {
   }

   public static String apply(List<String> pending) {
      int modules = 0;
      int filled = 0;
      int added = 0;
      int nullStatics = 0;
      int orderedByConfig = 0;
      int renamed = 0;
      int revalued = 0;
      int unresolved = 0;
      List<String> deficits = new ArrayList<String>();
      String note;

      try {
         JsonObject cfg = ExpoConfig.read();

         relabelForced = 0;
         relabelJudged = 0;
         relabelSkippedEmpty = 0;
         relabelWouldOverwrite = 0;
         relabelOverwrites.clear();

         for (Module m : ModuleManager.S) {
            if (m == null) {
               continue;
            }

            modules++;
            List<Setting> live = m.w();

            if (live == null || !live.isEmpty()) {
               continue;
            }

            Map<String, Setting> byName = new LinkedHashMap<String, Setting>();
            List<Setting> declared = new ArrayList<Setting>();
            nullStatics += collect(m.getClass(), byName, declared);

            JsonObject block = configBlock(cfg, m);

            if (block != null) {
               relabelModule = m.b();
               int[] r = relabel(block, declared);
               renamed += r[0];
               revalued += r[1];
               unresolved += r[2];
               byName.clear();

               for (Setting s : declared) {
                  String n = name(s);

                  if (n != null && !byName.containsKey(n)) {
                     byName.put(n, s);
                  }
               }
            }

            List<String> order = configOrder(cfg, m);
            List<Setting> out = new ArrayList<Setting>();
            Map<Setting, Boolean> seen = new IdentityHashMap<Setting, Boolean>();

            if (order != null) {
               orderedByConfig++;

               for (String key : order) {
                  Setting s = byName.get(key);

                  if (s != null && seen.put(s, Boolean.TRUE) == null) {
                     out.add(s);
                  }
               }
            }

            for (Setting s : declared) {
               if (seen.put(s, Boolean.TRUE) == null) {
                  out.add(s);
               }
            }

            if (!out.isEmpty()) {
               live.addAll(out);
               filled++;
               added += out.size();
            }

            if (order != null && out.size() < order.size()) {
               deficits.add(m.b() + ": " + out.size() + "/" + order.size());
            }
         }

         if (!deficits.isEmpty()) {
            System.out.println("[EXPODIAG] settings DEFICIT (module: got/expected) = " + deficits);
         }

         note = "Expo.settings filled " + filled + "/" + modules + " modules with "
                + added + " settings (" + orderedByConfig + " ordered from config, rest in "
                + "declaration order); skipped " + nullStatics + " null statics; "
                + "forced matches " + renamed + " (already correct in source, relabel no "
                + "longer writes), values restored " + revalued
                + ", " + unresolved + " left obfuscated because their Setting type group holds "
                + "more than one candidate";

         String g1 = "Expo.settings relabel G1 forced " + relabelForced + " / judged "
                     + relabelJudged + " / empty " + relabelSkippedEmpty
                     + " / wouldOverwrite " + relabelWouldOverwrite + " (must be 0)"
                     + (relabelWouldOverwrite == 0 ? "" : " " + relabelOverwrites);
         pending.add(g1);
         System.out.println("[EXPODIAG] " + g1);
      } catch (Throwable t) {
         note = "Expo.settings FAILED (" + t + ") -- the ClickGUI will show no settings";
         t.printStackTrace();
      }

      pending.add(note);
      System.out.println("[EXPODIAG] " + note);
      return note;
   }

   public static String applyByName(List<String> pending) {
      int[] real = byName(false);
      String note = "Expo.settings by-name applied " + real[0] + " config value(s) onto "
                    + "settings whose label is a key of their own block (" + real[1]
                    + " labels matched a key; refused " + real[2] + " mode value(s) outside the "
                    + "recovered option table, " + real[3] + " number(s) outside the shipped "
                    + "range, " + real[4] + " type mismatch(es), " + real[5] + " duplicate "
                    + "key(s); " + real[6] + " label(s) are not a key of their block; "
                    + real[7] + " write(s) did not read back)";

      if (System.getProperty("expo.byname.control") != null) {
         int[] ctl = byName(true);
         note = note + "; NEGATIVE CONTROL (labels rotated by one, dry run) would have matched "
                + ctl[1] + " and applied " + ctl[0]
                + (ctl[0] < real[0] ? " -- control is weaker, as it must be"
                                    : " -- CONTROL IS NOT WEAKER, this gate proves nothing");
      }

      pending.add(note);
      System.out.println("[EXPODIAG] " + note);
      return note;
   }

   private static int[] byName(boolean control) {
      int[] r = new int[8];

      try {
         JsonObject cfg = ExpoConfig.read();

         if (cfg == null) {
            return r;
         }

         for (Module m : ModuleManager.S) {
            if (m == null) {
               continue;
            }

            JsonObject block = configBlock(cfg, m);

            if (block == null) {
               continue;
            }

            List<Setting> live;

            try {
               live = m.w();
            } catch (Throwable t) {
               continue;
            }

            if (live == null || live.isEmpty()) {
               continue;
            }

            Map<String, Boolean> used = new HashMap<String, Boolean>();

            for (int i = 0; i < live.size(); i++) {
               Setting s = live.get(i);

               if (s == null) {
                  continue;
               }

               Setting labelFrom = control ? live.get((i + 1) % live.size()) : s;
               String label = name(labelFrom);

               if (label == null || label.length() == 0 || bucketOf(s) == T_NONE) {
                  continue;
               }

               String key = block.has(label) ? label : ExpoConfig.settingKey(label);

               if (key == null || !block.has(key)) {
                  r[6]++;
                  continue;
               }

               JsonElement v = block.get(key);
               int bs = bucketOf(s);
               int bv = bucketOf(v);

               if (bv == T_NONE || (bs == T_MODE || bs == T_TEXT ? bv != T_MODE : bs != bv)) {
                  r[4]++;
                  continue;
               }

               if (used.put(key, Boolean.TRUE) != null) {
                  r[5]++;
                  continue;
               }

               r[1]++;
               int verdict = write(s, v.getAsJsonPrimitive(), control);

               if (verdict == 0) {
                  r[0]++;
               } else {
                  r[verdict + 1]++;
               }
            }
         }
      } catch (Throwable t) {
         t.printStackTrace();
      }

      return r;
   }

   private static int write(Setting s, JsonPrimitive p, boolean dryRun) {
      try {
         if (s instanceof Expo.setting.settings.BooleanSetting) {
            boolean v = p.getAsBoolean();

            if (dryRun) {
               return 0;
            }

            ((Expo.setting.settings.BooleanSetting)s).v(v, 0L);
            return ((Expo.setting.settings.BooleanSetting)s).c() == v ? 0 : 6;
         }

         if (s instanceof Expo.setting.settings.PercentageSetting) {
            int v = p.getAsInt();

            if (dryRun) {
               return 0;
            }

            ((Expo.setting.settings.PercentageSetting)s).d(v);
            return ((Expo.setting.settings.PercentageSetting)s).k() == v ? 0 : 6;
         }

         if (s instanceof Expo.setting.settings.NumberSetting) {
            Expo.setting.settings.NumberSetting ns = (Expo.setting.settings.NumberSetting)s;
            float v = p.getAsFloat();

            if (Float.isNaN(v) || Float.isInfinite(v) || v < ns.i() || v > ns.F()) {
               return 2;
            }

            if (dryRun) {
               return 0;
            }

            return setFloat(ns, v) && ns.L() == v ? 0 : 6;
         }

         if (s instanceof Expo.setting.settings.ColorSetting) {
            String v = p.getAsString();

            if (!isHex6(v)) {
               return 2;
            }

            if (dryRun) {
               return 0;
            }

            ((Expo.setting.settings.ColorSetting)s).e(v);
            return v.equals(((Expo.setting.settings.ColorSetting)s).Q()) ? 0 : 6;
         }

         if (s instanceof Expo.setting.settings.ModeSetting) {
            Expo.setting.settings.ModeSetting ms = (Expo.setting.settings.ModeSetting)s;
            String v = p.getAsString();
            List<String> opts = ms.S();

            if (opts == null || !opts.contains(v)) {
               return 1;
            }

            if (dryRun) {
               return 0;
            }

            ms.i(v);
            return v.equals(ms.Y()) ? 0 : 6;
         }

         if (s instanceof Expo.setting.settings.TextSetting) {
            String v = p.getAsString();

            if (dryRun) {
               return 0;
            }

            ((Expo.setting.settings.TextSetting)s).O(v);
            return v.equals(((Expo.setting.settings.TextSetting)s).X()) ? 0 : 6;
         }
      } catch (Throwable t) {
         return 6;
      }

      return 6;
   }

   private static int relabelForced;

   private static int relabelJudged;

   private static int relabelSkippedEmpty;

   private static int relabelWouldOverwrite;

   private static String relabelModule = "?";

   private static final List<String> relabelOverwrites = new ArrayList<String>();

   private static int collect(Class<?> c, Map<String, Setting> byName, List<Setting> declared) {
      int nulls = 0;

      for (Class<?> k = c; k != null && Module.class.isAssignableFrom(k); k = k.getSuperclass()) {
         for (Field f : k.getDeclaredFields()) {
            if (!Modifier.isStatic(f.getModifiers()) || !Setting.class.isAssignableFrom(f.getType())) {
               continue;
            }

            try {
               f.setAccessible(true);
               Setting s = (Setting)f.get(null);

               if (s == null) {
                  nulls++;
                  continue;
               }

               declared.add(s);
               String n = name(s);

               if (n != null && !byName.containsKey(n)) {
                  byName.put(n, s);
               }
            } catch (Throwable t) {
            }
         }
      }

      return nulls;
   }

   private static final int T_BOOL = 0;
   private static final int T_PCT = 1;
   private static final int T_NUM = 2;
   private static final int T_COLOR = 3;
   private static final int T_MODE = 4;
   private static final int T_TEXT = 5;
   private static final int T_NONE = -1;

   private static int bucketOf(Setting s) {
      if (s instanceof Expo.setting.settings.BooleanSetting) {
         return T_BOOL;
      } else if (s instanceof Expo.setting.settings.PercentageSetting) {
         return T_PCT;
      } else if (s instanceof Expo.setting.settings.NumberSetting) {
         return T_NUM;
      } else if (s instanceof Expo.setting.settings.ColorSetting) {
         return T_COLOR;
      } else if (s instanceof Expo.setting.settings.ModeSetting) {
         return T_MODE;
      } else if (s instanceof Expo.setting.settings.TextSetting) {
         return T_TEXT;
      } else {
         return T_NONE;
      }
   }

   private static int bucketOf(JsonElement e) {
      if (e == null || !e.isJsonPrimitive()) {
         return T_NONE;
      }

      JsonPrimitive p = e.getAsJsonPrimitive();

      if (p.isBoolean()) {
         return T_BOOL;
      }

      if (p.isNumber()) {
         String raw = p.getAsString();
         return raw.indexOf('.') >= 0 || raw.indexOf('e') >= 0 || raw.indexOf('E') >= 0
                ? T_NUM : T_PCT;
      }

      if (p.isString()) {
         return isHex6(p.getAsString()) ? T_COLOR : T_MODE;
      }

      return T_NONE;
   }

   private static boolean isHex6(String s) {
      if (s == null || s.length() != 6) {
         return false;
      }

      for (int i = 0; i < 6; i++) {
         char c = s.charAt(i);

         if (!((c >= '0' && c <= '9') || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F'))) {
            return false;
         }
      }

      return true;
   }

   private static boolean isUpper(String s) {
      if (s == null || s.length() == 0) {
         return false;
      }

      for (int i = 0; i < s.length(); i++) {
         char c = s.charAt(i);

         if (!((c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9') || c == '_')) {
            return false;
         }
      }

      return true;
   }

   private static int[] relabel(JsonObject block, List<Setting> declared) {
      List<Setting> ss = new ArrayList<Setting>();

      for (Setting s : declared) {
         if (bucketOf(s) != T_NONE) {
            ss.add(s);
         }
      }

      List<String> keys = new ArrayList<String>();
      List<JsonElement> vals = new ArrayList<JsonElement>();

      for (Map.Entry<String, JsonElement> en : block.entrySet()) {
         if (!COMMON.contains(en.getKey()) && bucketOf(en.getValue()) != T_NONE) {
            keys.add(en.getKey());
            vals.add(en.getValue());
         }
      }

      int n = ss.size();
      int m = keys.size();

      if (n == 0 || m == 0) {
         return new int[]{0, 0, n};
      }

      int cMode = 0;
      int cText = 0;

      for (Setting s : ss) {
         if (bucketOf(s) == T_MODE) {
            cMode++;
         } else if (bucketOf(s) == T_TEXT) {
            cText++;
         }
      }

      int kUpper = 0;
      int kOther = 0;

      for (JsonElement v : vals) {
         if (bucketOf(v) == T_MODE) {
            if (isUpper(v.getAsString())) {
               kUpper++;
            } else {
               kOther++;
            }
         }
      }

      boolean splitStrings = cMode == kUpper && cText == kOther;

      boolean[][] adj = new boolean[n][m];

      for (int i = 0; i < n; i++) {
         int bs = bucketOf(ss.get(i));

         for (int j = 0; j < m; j++) {
            int bv = bucketOf(vals.get(j));

            if (bs == T_MODE || bs == T_TEXT) {
               if (bv != T_MODE) {
                  continue;
               }

               adj[i][j] = !splitStrings
                           || (bs == T_MODE) == isUpper(vals.get(j).getAsString());

               if (adj[i][j]) {
                  adj[i][j] = optionAllows(ss.get(i), vals.get(j).getAsString());
               }
            } else {
               adj[i][j] = bs == bv;
            }
         }
      }

      int[] base = new int[n];
      int size = match(adj, n, m, base, -1, -1);
      int forced = 0;
      int revalued = 0;
      boolean[] doneS = new boolean[n];
      boolean[] doneK = new boolean[m];

      for (int i = 0; i < n; i++) {
         if (base[i] < 0) {
            continue;
         }

         int[] probe = new int[n];

         if (match(adj, n, m, probe, i, base[i]) >= size) {
            continue;
         }

         Setting s = ss.get(i);
         String key = keys.get(base[i]);
         relabelForced++;

         try {
            String before = name(s);
            forced++;

            if (before == null || before.length() == 0) {
               relabelSkippedEmpty++;
            } else {
               relabelJudged++;

               if (!before.equals(key)) {
                  relabelWouldOverwrite++;

                  if (relabelOverwrites.size() < 40) {
                     relabelOverwrites.add(relabelModule + "." + before + " -> " + key);
                  }
               }
            }

            doneS[i] = true;
            doneK[base[i]] = true;

            if (applyValue(s, vals.get(base[i]))) {
               revalued++;
            }
         } catch (Throwable t) {
         }
      }

      for (int g = T_BOOL; g <= T_TEXT; g++) {
         int nS = 0;
         int nK = 0;
         String only = null;
         boolean uniform = true;

         for (int j = 0; j < m; j++) {
            if (!doneK[j] && groupOf(bucketOf(vals.get(j)), vals.get(j), splitStrings) == g) {
               nK++;
               String raw = vals.get(j).toString();

               if (only == null) {
                  only = raw;
               } else if (!only.equals(raw)) {
                  uniform = false;
               }
            }
         }

         for (int i = 0; i < n; i++) {
            if (!doneS[i] && groupOf(bucketOf(ss.get(i)), null, splitStrings) == g) {
               nS++;
            }
         }

         if (!uniform || nK == 0 || nS == 0 || nS > nK) {
            continue;
         }

         for (int i = 0; i < n; i++) {
            if (!doneS[i] && groupOf(bucketOf(ss.get(i)), null, splitStrings) == g) {
               for (int j = 0; j < m; j++) {
                  if (!doneK[j] && groupOf(bucketOf(vals.get(j)), vals.get(j), splitStrings) == g) {
                     if (applyValue(ss.get(i), vals.get(j))) {
                        revalued++;
                     }

                     break;
                  }
               }
            }
         }
      }

      return new int[]{forced, revalued, n - forced};
   }

   private static boolean optionAllows(Setting s, String v) {
      if (!(s instanceof Expo.setting.settings.ModeSetting)) {
         return true;
      }

      try {
         List<String> o = ((Expo.setting.settings.ModeSetting)s).S();

         if (o == null || o.isEmpty() || o.contains("UNSET")) {
            return true;
         }

         return o.contains(v);
      } catch (Throwable t) {
         return true;
      }
   }

   private static int groupOf(int bucket, JsonElement v, boolean splitStrings) {
      if (bucket != T_MODE && bucket != T_TEXT) {
         return bucket;
      }

      if (!splitStrings) {
         return T_MODE;
      }

      if (v == null) {
         return bucket;
      }

      return isUpper(v.getAsString()) ? T_MODE : T_TEXT;
   }

   private static int match(boolean[][] adj, int n, int m, int[] out, int banI, int banJ) {
      int[] keyOwner = new int[m];

      for (int j = 0; j < m; j++) {
         keyOwner[j] = -1;
      }

      for (int i = 0; i < n; i++) {
         out[i] = -1;
      }

      int size = 0;

      for (int i = 0; i < n; i++) {
         if (augment(adj, i, new boolean[m], keyOwner, m, banI, banJ)) {
            size++;
         }
      }

      for (int j = 0; j < m; j++) {
         if (keyOwner[j] >= 0) {
            out[keyOwner[j]] = j;
         }
      }

      return size;
   }

   private static boolean augment(boolean[][] adj, int i, boolean[] visited,
                                  int[] keyOwner, int m, int banI, int banJ) {
      for (int j = 0; j < m; j++) {
         if (!adj[i][j] || visited[j] || (i == banI && j == banJ)) {
            continue;
         }

         visited[j] = true;

         if (keyOwner[j] < 0 || augment(adj, keyOwner[j], visited, keyOwner, m, banI, banJ)) {
            keyOwner[j] = i;
            return true;
         }
      }

      return false;
   }

   private static boolean applyValue(Setting s, JsonElement e) {
      try {
         JsonPrimitive p = e.getAsJsonPrimitive();

         if (s instanceof Expo.setting.settings.BooleanSetting) {
            ((Expo.setting.settings.BooleanSetting)s).v(p.getAsBoolean(), 0L);
            return true;
         }

         if (s instanceof Expo.setting.settings.PercentageSetting) {
            ((Expo.setting.settings.PercentageSetting)s).d(p.getAsInt());
            return true;
         }

         if (s instanceof Expo.setting.settings.NumberSetting) {
            return setFloat((Expo.setting.settings.NumberSetting)s, p.getAsFloat());
         }

         if (s instanceof Expo.setting.settings.ColorSetting) {
            ((Expo.setting.settings.ColorSetting)s).e(p.getAsString());
            return true;
         }

         if (s instanceof Expo.setting.settings.ModeSetting) {
            Expo.setting.settings.ModeSetting ms = (Expo.setting.settings.ModeSetting)s;
            String v = p.getAsString();
            List<String> opts = ms.S();

            if (opts != null && !opts.contains(v)) {
               if (opts.size() != 1 || !"UNSET".equals(opts.get(0))) {
                  return false;
               }

               opts.add(v);
               opts.remove("UNSET");
            }

            ms.i(v);
            return v.equals(ms.Y());
         }

         if (s instanceof Expo.setting.settings.TextSetting) {
            ((Expo.setting.settings.TextSetting)s).O(p.getAsString());
            return true;
         }
      } catch (Throwable t) {
      }

      return false;
   }

   private static boolean setFloat(Expo.setting.settings.NumberSetting s, float v) {
      Field target = null;

      for (Field f : Expo.setting.settings.NumberSetting.class.getDeclaredFields()) {
         if (f.getType() == float.class && !Modifier.isStatic(f.getModifiers())
             && !Modifier.isFinal(f.getModifiers())) {
            if (target != null) {
               return false;
            }

            target = f;
         }
      }

      if (target == null) {
         return false;
      }

      try {
         target.setAccessible(true);
         target.setFloat(s, v);
         return true;
      } catch (Throwable t) {
         return false;
      }
   }

   private static JsonObject configBlock(JsonObject cfg, Module m) {
      if (cfg == null) {
         return null;
      }

      String moduleName = m.b();

      if (moduleName == null || moduleName.startsWith(ExpoModuleRegistry.PLACEHOLDER_PREFIX)) {
         return null;
      }

      JsonElement e = cfg.get(moduleName);
      return e != null && e.isJsonObject() ? e.getAsJsonObject() : null;
   }

   private static String name(Setting s) {
      try {
         return s.B();
      } catch (Throwable t) {
         return null;
      }
   }

   private static List<String> configOrder(JsonObject cfg, Module m) {
      if (cfg == null) {
         return null;
      }

      String moduleName = m.b();

      if (moduleName == null || moduleName.startsWith(ExpoModuleRegistry.PLACEHOLDER_PREFIX)) {
         return null;
      }

      JsonElement e = cfg.get(moduleName);

      if (e == null || !e.isJsonObject()) {
         return null;
      }

      List<String> order = new ArrayList<String>();

      for (Map.Entry<String, JsonElement> entry : e.getAsJsonObject().entrySet()) {
         if (!COMMON.contains(entry.getKey())) {
            order.add(entry.getKey());
         }
      }

      return order.isEmpty() ? null : order;
   }
}
