package Expo.internal.restore;

import Expo.setting.Setting;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.ModeSetting;
import Expo.setting.settings.NumberSetting;
import Expo.setting.settings.PercentageSetting;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;


public final class ExpoSweepSettings {

   public static final int KIND_UNKNOWN = 0;
   public static final int KIND_BOOL = 1;
   public static final int KIND_MODE = 2;
   public static final int KIND_NUMBER = 3;
   public static final int KIND_PERCENT = 4;
   public static final int KIND_COLOR = 5;
   public static final int KIND_KEY = 6;
   public static final int KIND_TEXT = 7;

   private static final int MODE_CAP = 16;

   private static Field NUMBER_FIELD;

   private static boolean numberFieldResolved;

   private ExpoSweepSettings() {
   }

   public static int kind(Setting s) {
      if (s == null) {
         return KIND_UNKNOWN;
      }

      if (s instanceof BooleanSetting) {
         return KIND_BOOL;
      }

      if (s instanceof ModeSetting) {
         return KIND_MODE;
      }

      if (s instanceof NumberSetting) {
         return KIND_NUMBER;
      }

      if (s instanceof PercentageSetting) {
         return KIND_PERCENT;
      }

      if (s instanceof Expo.setting.settings.ColorSetting) {
         return KIND_COLOR;
      }

      if (s instanceof Expo.setting.settings.TextSetting) {
         return KIND_KEY;
      }

      if (s instanceof Expo.setting.settings.HeaderSetting) {
         return KIND_TEXT;
      }

      return KIND_UNKNOWN;
   }

   public static String kindName(int k) {
      switch (k) {
         case KIND_BOOL:
            return "BOOL";
         case KIND_MODE:
            return "MODE";
         case KIND_NUMBER:
            return "NUMBER";
         case KIND_PERCENT:
            return "PERCENT";
         case KIND_COLOR:
            return "COLOR";
         case KIND_KEY:
            return "KEYBIND";
         case KIND_TEXT:
            return "TEXT";
         default:
            return "UNKNOWN";
      }
   }

   public static boolean scannable(int k) {
      return k == KIND_BOOL || k == KIND_MODE || k == KIND_NUMBER || k == KIND_PERCENT;
   }

   public static String label(Setting s) {
      if (s == null) {
         return "<null>";
      }

      try {
         String n = s.B();
         return n == null ? "<unnamed>" : n;
      } catch (Throwable t) {
         return "<label threw " + t.getClass().getName() + '>';
      }
   }

   public static Object snapshot(Setting s) {
      switch (kind(s)) {
         case KIND_BOOL:
            return Boolean.valueOf(((BooleanSetting)s).c());
         case KIND_MODE:
            ModeSetting m = (ModeSetting)s;
            return new Object[]{m.Y(), Integer.valueOf(m.G())};
         case KIND_NUMBER:
            return Float.valueOf(((NumberSetting)s).L());
         case KIND_PERCENT:
            return Integer.valueOf(((PercentageSetting)s).k());
         default:
            return null;
      }
   }

   public static boolean matches(Setting s, Object snap) {
      switch (kind(s)) {
         case KIND_BOOL:
            return snap instanceof Boolean && ((Boolean)snap).booleanValue() == ((BooleanSetting)s).c();
         case KIND_MODE:
            if (!(snap instanceof Object[])) {
               return false;
            }

            Object[] a = (Object[])snap;
            ModeSetting m = (ModeSetting)s;
            boolean sameText = a[0] == null ? m.Y() == null : a[0].equals(m.Y());
            return sameText && ((Integer)a[1]).intValue() == m.G();
         case KIND_NUMBER:
            return snap instanceof Float
                   && Float.floatToIntBits(((Float)snap).floatValue())
                      == Float.floatToIntBits(((NumberSetting)s).L());
         case KIND_PERCENT:
            return snap instanceof Integer && ((Integer)snap).intValue() == ((PercentageSetting)s).k();
         default:
            return true;
      }
   }

   public static List<Object> points(Setting s) {
      List<Object> out = new ArrayList<Object>();

      switch (kind(s)) {
         case KIND_BOOL:
            out.add(Boolean.valueOf(!((BooleanSetting)s).c()));
            break;
         case KIND_MODE:
            ModeSetting m = (ModeSetting)s;
            List<String> opts = m.S();

            if (opts != null) {
               String cur = m.Y();

               for (int i = 0; i < opts.size() && out.size() < MODE_CAP; i++) {
                  String o = opts.get(i);

                  if (o != null && !o.equals(cur)) {
                     out.add(o);
                  }
               }
            }

            break;
         case KIND_NUMBER:
            NumberSetting n = (NumberSetting)s;
            addFloat(out, n.i(), n.L());
            addFloat(out, n.F(), n.L());
            break;
         case KIND_PERCENT:
            PercentageSetting p = (PercentageSetting)s;
            addInt(out, 0, p.k());
            addInt(out, 100, p.k());
            break;
         default:
      }

      return out;
   }

   private static void addFloat(List<Object> out, float v, float cur) {
      if (Float.floatToIntBits(v) != Float.floatToIntBits(cur)) {
         out.add(Float.valueOf(v));
      }
   }

   private static void addInt(List<Object> out, int v, int cur) {
      if (v != cur) {
         out.add(Integer.valueOf(v));
      }
   }

   public static void apply(Setting s, Object v) throws Throwable {
      switch (kind(s)) {
         case KIND_BOOL:
            ((BooleanSetting)s).v(((Boolean)v).booleanValue(), 0L);
            break;
         case KIND_MODE:
            ((ModeSetting)s).i((String)v);
            break;
         case KIND_NUMBER:
            if (!writeFloat((NumberSetting)s, ((Float)v).floatValue())) {
               throw new IllegalStateException(
                  "NumberSetting has no unique non-final float field; value not written");
            }

            break;
         case KIND_PERCENT:
            ((PercentageSetting)s).d(((Integer)v).intValue());
            break;
         default:
            throw new IllegalStateException("not scannable: " + kindName(kind(s)));
      }
   }

   public static boolean restore(Setting s, Object snap) throws Throwable {
      switch (kind(s)) {
         case KIND_BOOL:
            ((BooleanSetting)s).v(((Boolean)snap).booleanValue(), 0L);
            break;
         case KIND_MODE:
            Object[] a = (Object[])snap;
            ModeSetting m = (ModeSetting)s;
            m.i((String)a[0]);

            if (!matches(s, snap)) {
               forceMode(m, (String)a[0], ((Integer)a[1]).intValue());
            }

            break;
         case KIND_NUMBER:
            writeFloat((NumberSetting)s, ((Float)snap).floatValue());
            break;
         case KIND_PERCENT:
            ((PercentageSetting)s).d(((Integer)snap).intValue());
            break;
         default:
            return true;
      }

      return matches(s, snap);
   }

   private static void forceMode(ModeSetting m, String text, int index) {
      try {
         Field y = ModeSetting.class.getDeclaredField("Y");
         y.setAccessible(true);
         y.set(m, text);
      } catch (Throwable t) {
      }

      try {
         Field p = ModeSetting.class.getDeclaredField("p");
         p.setAccessible(true);
         p.setInt(m, index);
      } catch (Throwable t) {
      }
   }

   private static boolean writeFloat(NumberSetting s, float v) {
      Field target = numberField();

      if (target == null) {
         return false;
      }

      try {
         target.setFloat(s, v);
         return true;
      } catch (Throwable t) {
         return false;
      }
   }

   private static Field numberField() {
      if (numberFieldResolved) {
         return NUMBER_FIELD;
      }

      numberFieldResolved = true;
      Field found = null;

      for (Field f : NumberSetting.class.getDeclaredFields()) {
         if (f.getType() == float.class && !Modifier.isStatic(f.getModifiers())
             && !Modifier.isFinal(f.getModifiers())) {
            if (found != null) {
               return null;
            }

            found = f;
         }
      }

      if (found != null) {
         try {
            found.setAccessible(true);
         } catch (Throwable t) {
            return null;
         }
      }

      NUMBER_FIELD = found;
      return found;
   }

   public static String render(Object v) {
      if (v == null) {
         return "-";
      }

      if (v instanceof Object[]) {
         Object[] a = (Object[])v;
         return String.valueOf(a[0]) + '#' + a[1];
      }

      return String.valueOf(v);
   }

   public static String live(Setting s) {
      try {
         return render(snapshot(s));
      } catch (Throwable t) {
         return "<read threw " + t.getClass().getName() + '>';
      }
   }
}
