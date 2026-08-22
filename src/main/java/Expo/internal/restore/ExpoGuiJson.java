package Expo.internal.restore;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public final class ExpoGuiJson {

   private final String s;
   private int i;

   private ExpoGuiJson(String text) {
      this.s = text;
   }

   public static Map<String, Object> parseObject(String text) {
      if (text == null) {
         return null;
      }

      ExpoGuiJson p = new ExpoGuiJson(text);
      p.ws();
      Object v = p.value();
      p.ws();

      return v instanceof Map ? asMap(v) : null;
   }

   @SuppressWarnings("unchecked")
   public static Map<String, Object> asMap(Object o) {
      return o instanceof Map ? (Map<String, Object>)o : null;
   }

   public static boolean asBool(Object o, boolean def) {
      if (o instanceof Boolean) {
         return ((Boolean)o).booleanValue();
      }

      if (o instanceof Double) {
         return ((Double)o).doubleValue() != 0.0D;
      }

      if (o instanceof String) {
         return Boolean.parseBoolean((String)o);
      }

      return def;
   }

   public static double asNum(Object o, double def) {
      if (o instanceof Double) {
         return ((Double)o).doubleValue();
      }

      if (o instanceof Boolean) {
         return ((Boolean)o).booleanValue() ? 1.0D : 0.0D;
      }

      if (o instanceof String) {
         try {
            return Double.parseDouble((String)o);
         } catch (Throwable t) {
            return def;
         }
      }

      return def;
   }

   public static String write(Map<String, Object> root) {
      StringBuilder b = new StringBuilder();
      writeValue(root, b, 1);
      b.append('\n');
      return b.toString();
   }

   private static void writeValue(Object v, StringBuilder b, int depth) {
      if (v == null) {
         b.append("null");
      } else if (v instanceof Map) {
         Map<String, Object> m = asMap(v);
         b.append("{\n");

         for (Iterator<Map.Entry<String, Object>> it = m.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<String, Object> e = it.next();
            indent(b, depth);
            quote(e.getKey(), b);
            b.append(": ");
            writeValue(e.getValue(), b, depth + 1);

            if (it.hasNext()) {
               b.append(',');
            }

            b.append('\n');
         }

         indent(b, depth - 1);
         b.append('}');
      } else if (v instanceof List) {
         List<?> l = (List<?>)v;
         b.append('[');

         for (int k = 0; k < l.size(); k++) {
            if (k > 0) {
               b.append(", ");
            }

            writeValue(l.get(k), b, depth + 1);
         }

         b.append(']');
      } else if (v instanceof Boolean) {
         b.append(((Boolean)v).booleanValue() ? "true" : "false");
      } else if (v instanceof Float) {
         float f = ((Float)v).floatValue();
         b.append(Float.isNaN(f) || Float.isInfinite(f) ? "0.0" : Float.toString(f));
      } else if (v instanceof Number) {
         double d = ((Number)v).doubleValue();

         if (v instanceof Integer || v instanceof Long || d == Math.rint(d) && !Double.isInfinite(d)) {
            if (v instanceof Integer || v instanceof Long) {
               b.append(((Number)v).longValue());
            } else {
               b.append(d);
            }
         } else {
            b.append(d);
         }
      } else {
         quote(String.valueOf(v), b);
      }
   }

   private static void indent(StringBuilder b, int depth) {
      for (int k = 0; k < depth; k++) {
         b.append("  ");
      }
   }

   private static void quote(String v, StringBuilder b) {
      b.append('"');

      for (int k = 0; k < v.length(); k++) {
         char c = v.charAt(k);

         if (c == '"' || c == '\\') {
            b.append('\\').append(c);
         } else if (c == '\n') {
            b.append("\\n");
         } else if (c == '\r') {
            b.append("\\r");
         } else if (c == '\t') {
            b.append("\\t");
         } else if (c < 0x20) {
            b.append(String.format("\\u%04x", Integer.valueOf(c)));
         } else {
            b.append(c);
         }
      }

      b.append('"');
   }

   private void ws() {
      while (this.i < this.s.length() && Character.isWhitespace(this.s.charAt(this.i))) {
         this.i++;
      }
   }

   private Object value() {
      this.ws();

      if (this.i >= this.s.length()) {
         return null;
      }

      char c = this.s.charAt(this.i);

      if (c == '{') {
         return this.object();
      }

      if (c == '[') {
         return this.array();
      }

      if (c == '"') {
         return this.string();
      }

      if (this.s.startsWith("true", this.i)) {
         this.i += 4;
         return Boolean.TRUE;
      }

      if (this.s.startsWith("false", this.i)) {
         this.i += 5;
         return Boolean.FALSE;
      }

      if (this.s.startsWith("null", this.i)) {
         this.i += 4;
         return null;
      }

      return this.number();
   }

   private Map<String, Object> object() {
      Map<String, Object> m = new LinkedHashMap<String, Object>();
      this.i++;
      this.ws();

      if (this.i < this.s.length() && this.s.charAt(this.i) == '}') {
         this.i++;
         return m;
      }

      while (this.i < this.s.length()) {
         this.ws();
         String k = this.s.charAt(this.i) == '"' ? this.string() : null;

         if (k == null) {
            break;
         }

         this.ws();

         if (this.i < this.s.length() && this.s.charAt(this.i) == ':') {
            this.i++;
         }

         m.put(k, this.value());
         this.ws();

         if (this.i < this.s.length() && this.s.charAt(this.i) == ',') {
            this.i++;
            continue;
         }

         if (this.i < this.s.length() && this.s.charAt(this.i) == '}') {
            this.i++;
         }

         break;
      }

      return m;
   }

   private List<Object> array() {
      List<Object> l = new ArrayList<Object>();
      this.i++;
      this.ws();

      if (this.i < this.s.length() && this.s.charAt(this.i) == ']') {
         this.i++;
         return l;
      }

      while (this.i < this.s.length()) {
         l.add(this.value());
         this.ws();

         if (this.i < this.s.length() && this.s.charAt(this.i) == ',') {
            this.i++;
            continue;
         }

         if (this.i < this.s.length() && this.s.charAt(this.i) == ']') {
            this.i++;
         }

         break;
      }

      return l;
   }

   private String string() {
      StringBuilder b = new StringBuilder();
      this.i++;

      while (this.i < this.s.length()) {
         char c = this.s.charAt(this.i++);

         if (c == '"') {
            break;
         }

         if (c != '\\') {
            b.append(c);
            continue;
         }

         if (this.i >= this.s.length()) {
            break;
         }

         char e = this.s.charAt(this.i++);

         if (e == 'n') {
            b.append('\n');
         } else if (e == 'r') {
            b.append('\r');
         } else if (e == 't') {
            b.append('\t');
         } else if (e == 'b') {
            b.append('\b');
         } else if (e == 'f') {
            b.append('\f');
         } else if (e == 'u' && this.i + 4 <= this.s.length()) {
            b.append((char)Integer.parseInt(this.s.substring(this.i, this.i + 4), 16));
            this.i += 4;
         } else {
            b.append(e);
         }
      }

      return b.toString();
   }

   private Object number() {
      int start = this.i;

      while (this.i < this.s.length()) {
         char c = this.s.charAt(this.i);

         if (c == '-' || c == '+' || c == '.' || c == 'e' || c == 'E' || c >= '0' && c <= '9') {
            this.i++;
         } else {
            break;
         }
      }

      if (this.i == start) {
         this.i++;
         return null;
      }

      try {
         return Double.valueOf(Double.parseDouble(this.s.substring(start, this.i)));
      } catch (Throwable t) {
         return null;
      }
   }
}
