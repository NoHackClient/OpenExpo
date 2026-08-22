package Expo.internal.restore;

import Expo.module.Module;
import Expo.module.ModuleManager;
import Expo.setting.Setting;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.ModeSetting;
import Expo.setting.settings.NumberSetting;
import Expo.setting.settings.PercentageSetting;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;


public final class ExpoSettingStatics {

   private static final List<String> COMMON = new ArrayList<String>();

   static {
      COMMON.add("status");
      COMMON.add("keyBind");
      COMMON.add("visible");
      COMMON.add("suffix-visible");
   }

   private static int totalCalls;

   private static int totalBuilt;

   private static int totalSkipped;

   private static int totalFailed;

   private ExpoSettingStatics() {
   }

   public static String apply(List<String> pending) {
      int built = 0;
      int skipped = 0;
      int failed = 0;
      int modules = 0;
      List<String> notes = new ArrayList<String>();
      String note;

      try {
         JsonObject cfg = ExpoConfig.read();

         for (Module m : ModuleManager.S) {
            if (m == null) {
               continue;
            }

            modules++;
            int[] r = fillFor(m, cfg, notes);
            built += r[0];
            skipped += r[1];
            failed += r[2];
         }

         note = "Expo.statics built " + built + " Setting objects into null statics of "
                + modules + " modules (skipped " + skipped + " with no carrier-free "
                + "constructor -- the Expo/setting/settings/HeaderSetting group, 0 read sites and absent from every "
                + "config block; " + failed + " failed); ALL fillFor call sites: calls "
                + totalCalls + " built " + totalBuilt + " skipped " + totalSkipped
                + " failed " + totalFailed;
      } catch (Throwable t) {
         note = "Expo.statics FAILED (" + t + ") -- the modules that read a null Setting "
                + "static will still throw NullPointerException";
      }

      pending.add(note);
      pending.addAll(notes);
      return note;
   }

   public static int[] fillFor(Module m, JsonObject cfg, List<String> notes) {
      int built = 0;
      int skipped = 0;
      int failed = 0;
      totalCalls++;
      JsonObject block = configBlock(cfg, m);
      Envelope env = new Envelope(block);
      List<String> unvalued = new ArrayList<String>();

      for (Class<?> k = m.getClass();
           k != null && Module.class.isAssignableFrom(k);
           k = k.getSuperclass()) {
         for (Field f : k.getDeclaredFields()) {
            if (!Modifier.isStatic(f.getModifiers())
                || !Setting.class.isAssignableFrom(f.getType())) {
               continue;
            }

            try {
               f.setAccessible(true);

               if (f.get(null) != null) {
                  continue;
               }

               Setting s = build(f.getType(), f.getName(), env, unvalued);

               if (s == null) {
                  skipped++;
                  continue;
               }

               f.set(null, s);
               built++;
            } catch (Throwable t) {
               failed++;
            }
         }
      }

      if (!unvalued.isEmpty() && notes != null) {
         notes.add("Expo.statics " + m.b() + ": " + unvalued.size()
                   + " numeric setting(s) seeded with this module's own minimum "
                   + "config value " + env.describe() + ", no factory value being "
                   + "recoverable; relabel overwrites the forced ones: " + unvalued);
      }

      totalBuilt += built;
      totalSkipped += skipped;
      totalFailed += failed;
      return new int[] {built, skipped, failed};
   }

   public static boolean buildable(Class<?> type) {
      return BooleanSetting.class.isAssignableFrom(type)
         || PercentageSetting.class.isAssignableFrom(type)
         || NumberSetting.class.isAssignableFrom(type)
         || ModeSetting.class.isAssignableFrom(type)
         || Expo.setting.settings.ColorSetting.class.isAssignableFrom(type)
         || Expo.setting.settings.TextSetting.class.isAssignableFrom(type);
   }

   private static Setting build(Class<?> type, String fieldName,
                                Envelope env, List<String> unvalued) throws Exception {
      if (!buildable(type)) {
         return null;
      }

      if (BooleanSetting.class.isAssignableFrom(type)) {
         return (Setting)ctor(type, String.class, boolean.class)
            .newInstance(fieldName, Boolean.FALSE);
      }

      if (PercentageSetting.class.isAssignableFrom(type)) {
         int v = env.minInt();

         if (env.hasInts()) {
            unvalued.add(fieldName);
         }

         return (Setting)ctor(type, String.class, int.class).newInstance(fieldName, v);
      }

      if (NumberSetting.class.isAssignableFrom(type)) {
         return numberFor(type, fieldName, env, unvalued);
      }

      if (ModeSetting.class.isAssignableFrom(type)) {
         return (Setting)ctor(type, String.class, String[].class)
            .newInstance(fieldName, optionsFor(fieldName, env));
      }

      if (Expo.setting.settings.ColorSetting.class.isAssignableFrom(type)) {
         return (Setting)ctor(type, String.class, String.class)
            .newInstance(fieldName, env.colour());
      }

      if (Expo.setting.settings.TextSetting.class.isAssignableFrom(type)) {
         return (Setting)ctor(type, String.class, String.class)
            .newInstance(fieldName, "");
      }

      return null;
   }

   private static Setting numberFor(Class<?> type, String fieldName, Envelope env,
                                    List<String> unvalued) throws Exception {
      float v = env.minFloat();

      if (env.hasFloats()) {
         unvalued.add(fieldName);
      }

      float hi = env.high();
      float lo = env.low();
      float step = env.step();
      return (Setting)ctor(type, String.class, float.class, float.class, float.class,
                           float.class).newInstance(fieldName, v, lo, hi, step);
   }

   private static String[] optionsFor(String fieldName, Envelope env) {
      LinkedHashSet<String> out = new LinkedHashSet<String>();
      out.addAll(env.strings());

      if (out.isEmpty()) {
         out.add(fieldName);
      }

      return out.toArray(new String[out.size()]);
   }

   private static Constructor<?> ctor(Class<?> type, Class<?>... sig) throws Exception {
      Constructor<?> c = type.getDeclaredConstructor(sig);
      c.setAccessible(true);
      return c;
   }

   private static final class Envelope {

      private final List<Float> floats = new ArrayList<Float>();
      private final List<Integer> ints = new ArrayList<Integer>();
      private final List<String> strings = new ArrayList<String>();
      private String colour;

      Envelope(JsonObject block) {
         if (block == null) {
            return;
         }

         for (Map.Entry<String, JsonElement> en : block.entrySet()) {
            if (COMMON.contains(en.getKey()) || !en.getValue().isJsonPrimitive()) {
               continue;
            }

            JsonPrimitive p = en.getValue().getAsJsonPrimitive();

            if (p.isNumber()) {
               String raw = p.getAsString();

               if (raw.indexOf('.') >= 0 || raw.indexOf('e') >= 0 || raw.indexOf('E') >= 0) {
                  floats.add(Float.valueOf(p.getAsFloat()));
               } else {
                  ints.add(Integer.valueOf(p.getAsInt()));
               }
            } else if (p.isString()) {
               String s = p.getAsString();

               if (isHex6(s)) {
                  if (colour == null) {
                     colour = s;
                  }
               } else {
                  strings.add(s);
               }
            }
         }
      }

      boolean hasFloats() {
         return !floats.isEmpty();
      }

      boolean hasInts() {
         return !ints.isEmpty();
      }

      List<String> strings() {
         return strings;
      }

      String colour() {
         return colour == null ? "FFFFFF" : colour;
      }

      float minFloat() {
         if (floats.isEmpty()) {
            return 0.0F;
         }

         float r = floats.get(0).floatValue();

         for (Float f : floats) {
            if (f.floatValue() < r) {
               r = f.floatValue();
            }
         }

         return r;
      }

      int minInt() {
         if (ints.isEmpty()) {
            return 0;
         }

         int r = ints.get(0).intValue();

         for (Integer i : ints) {
            if (i.intValue() < r) {
               r = i.intValue();
            }
         }

         return r;
      }

      float high() {
         float a = 1.0F;

         for (Float f : floats) {
            float x = Math.abs(f.floatValue());

            if (x > a) {
               a = x;
            }
         }

         return 2.0F * a;
      }

      float low() {
         for (Float f : floats) {
            if (f.floatValue() < 0.0F) {
               return -high();
            }
         }

         return 0.0F;
      }

      float step() {
         float span = high() - low();
         float want = span / 200.0F;
         float smallest = 0.0F;

         for (Float f : floats) {
            float x = Math.abs(f.floatValue());

            if (x > 0.0F && (smallest == 0.0F || x < smallest)) {
               smallest = x;
            }
         }

         if (smallest > 0.0F && smallest / 10.0F < want) {
            want = smallest / 10.0F;
         }

         return nice(want);
      }

      private static float nice(float x) {
         if (x <= 0.001F) {
            return 0.001F;
         }

         float p = 0.001F;

         while (p * 10.0F <= x) {
            p *= 10.0F;
         }

         if (p * 5.0F <= x) {
            return p * 5.0F;
         }

         if (p * 2.0F <= x) {
            return p * 2.0F;
         }

         return p;
      }

      String describe() {
         return "(range " + low() + ".." + high() + " step " + step() + ")";
      }
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
}
