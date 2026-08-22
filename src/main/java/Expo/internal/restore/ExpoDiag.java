package Expo.internal.restore;

import java.util.HashSet;
import java.util.Set;

public final class ExpoDiag {

   private static final boolean ON = "1".equals(System.getProperty("expo.selftest"));

   private static final Set<String> SEEN = new HashSet<String>();

   private static final java.util.Map<String, int[]> COUNTS = new java.util.HashMap<String, int[]>();

   private static String LABEL;

   private static int WINDOW_N;

   private static final java.util.List<String> WINDOW = new java.util.ArrayList<String>();

   private static final java.util.Map<String, int[]> BY_LABEL = new java.util.LinkedHashMap<String, int[]>();

   private static final java.util.Map<String, java.util.List<String>> BY_LABEL_FIRST
      = new java.util.LinkedHashMap<String, java.util.List<String>>();

   private static final int WINDOW_CAP = 6;

   private ExpoDiag() {
   }

   public static void beginAttribution(String label) {
      synchronized (WINDOW) {
         LABEL = label;
         WINDOW_N = 0;
         WINDOW.clear();
      }
   }

   public static void endAttribution() {
      synchronized (WINDOW) {
         LABEL = null;
         WINDOW_N = 0;
         WINDOW.clear();
      }
   }

   public static String attribution() {
      synchronized (WINDOW) {
         return LABEL;
      }
   }

   public static int windowCount() {
      synchronized (WINDOW) {
         return WINDOW_N;
      }
   }

   public static java.util.List<String> windowFirstFrames() {
      synchronized (WINDOW) {
         return new java.util.ArrayList<String>(WINDOW);
      }
   }

   public static java.util.Map<String, int[]> attributionTotals() {
      synchronized (WINDOW) {
         return new java.util.LinkedHashMap<String, int[]>(BY_LABEL);
      }
   }

   public static java.util.List<String> attributionFrames(String label) {
      synchronized (WINDOW) {
         java.util.List<String> l = BY_LABEL_FIRST.get(label);
         return l == null ? new java.util.ArrayList<String>() : new java.util.ArrayList<String>(l);
      }
   }

   public static void attribute(Throwable t, String origin) {
      if (!ON || t == null) {
         return;
      }

      record((origin == null ? "" : origin + ' ') + describe(t));
   }

   private static void record(String line) {
      synchronized (WINDOW) {
         WINDOW_N++;

         if (WINDOW.size() < WINDOW_CAP) {
            WINDOW.add(line);
         }

         String k = LABEL == null ? "<unattributed>" : LABEL;
         int[] n = BY_LABEL.get(k);

         if (n == null) {
            n = new int[1];
            BY_LABEL.put(k, n);
         }

         n[0]++;
         java.util.List<String> f = BY_LABEL_FIRST.get(k);

         if (f == null) {
            f = new java.util.ArrayList<String>();
            BY_LABEL_FIRST.put(k, f);
         }

         if (f.size() < WINDOW_CAP) {
            f.add(line);
         }
      }
   }

   public static String describe(Throwable t) {
      if (t == null) {
         return "null";
      }

      StackTraceElement[] st = t.getStackTrace();
      String top = st.length > 0 ? String.valueOf(st[0]) : "<no frame>";
      String expo = "<no Expo frame>";

      for (StackTraceElement e : st) {
         String c = e.getClassName();

         if (c.startsWith("Expo.") && !c.startsWith("Expo.internal.restore.ExpoSweep")
             && !c.startsWith("Expo.internal.restore.ExpoSelfTest") && !c.startsWith("Expo.internal.restore.ExpoDiag")) {
            expo = String.valueOf(e);
            break;
         }
      }

      String msg = t.getMessage();
      return t.getClass().getName() + (msg == null ? "" : ": " + msg) + " @ " + top + " [expo=" + expo + ']';
   }

   public static boolean on() {
      return ON;
   }

   public static void swallowed(Object event, Object listener, Throwable t) {
      if (!ON) {
         return;
      }

      String ev = event == null ? "null" : event.getClass().getName();
      String li = listener == null ? "null" : String.valueOf(listener);
      record("bus[" + ev + "] " + describe(t));
      String key = ev + '|' + li + '|' + t.getClass().getName();

      synchronized (SEEN) {
         if (!SEEN.add(key)) {
            return;
         }
      }

      System.out.println("[EXPODIAG] SWALLOWED by EventBus: event=" + ev + " listener=" + li
                         + " threw " + t);
      t.printStackTrace();
   }

   public static void subscribed(Object listener, java.util.List<?> bindings) {
      if (!ON) {
         return;
      }

      System.out.println("[EXPODIAG] SUBSCRIBE " + name(listener) + " -> "
                         + (bindings == null ? "NO BINDING LIST" : bindings.size() + " binding(s)"));
   }

   public static void unsubscribed(Object listener, java.util.List<?> bindings) {
      if (!ON) {
         return;
      }

      System.out.println("[EXPODIAG] UNSUBSCRIBE " + name(listener) + " -> "
                         + (bindings == null ? 0 : bindings.size()) + " binding(s) disabled");
   }

   private static String name(Object o) {
      return o == null ? "null" : o.getClass().getName();
   }

   public static void probe(String key, String detail) {
      if (!ON) {
         return;
      }

      synchronized (SEEN) {
         if (!SEEN.add("probe|" + key)) {
            return;
         }
      }

      System.out.println("[EXPODIAG] PROBE " + key + ": " + detail);
   }

   public static void dispatch(Object event, java.util.List<?> bindings) {
      if (!ON || event == null) {
         return;
      }

      String ev = event.getClass().getName();
      int[] n;

      synchronized (COUNTS) {
         n = COUNTS.get(ev);

         if (n == null) {
            n = new int[2];
            COUNTS.put(ev, n);
         }
      }

      n[0]++;
      n[1] = bindings == null ? -1 : bindings.size();

      synchronized (SEEN) {
         if (!SEEN.add("dispatch|" + ev)) {
            return;
         }
      }

      System.out.println("[EXPODIAG] DISPATCH " + ev + " -> "
                         + (bindings == null ? "NO LISTENER LIST" : bindings.size() + " binding(s)"));
   }

   public static void dumpCounts() {
      if (!ON) {
         return;
      }

      StringBuilder b = new StringBuilder("\n[EXPODIAG] ==== event fire counts ====\n");
      java.util.List<String> keys;

      synchronized (COUNTS) {
         keys = new java.util.ArrayList<String>(COUNTS.keySet());
      }

      java.util.Collections.sort(keys);

      for (String k : keys) {
         int[] n = COUNTS.get(k);
         b.append("[EXPODIAG] ").append(String.format("%7d", n[0])).append("  fires, ")
          .append(n[1] < 0 ? "NO LISTENER LIST" : n[1] + " binding(s)")
          .append("  ").append(k).append('\n');
      }

      b.append("[EXPODIAG] ==== end ====");
      System.out.println(b);
   }

   public static void delivered(Object event, Object binding) {
      if (!ON || event == null) {
         return;
      }

      String key = event.getClass().getName() + '|' + binding;

      synchronized (SEEN) {
         if (!SEEN.add("deliver|" + key)) {
            return;
         }
      }

      System.out.println("[EXPODIAG] DELIVERED " + event.getClass().getName() + " -> " + invoker(binding));
   }

   private static String invoker(Object binding) {
      return invokerName(binding);
   }

   public static String invokerName(Object binding) {
      if (binding == null) {
         return "null";
      }

      java.util.List<java.lang.reflect.Field> hits = new java.util.ArrayList<java.lang.reflect.Field>();
      StringBuilder seen = new StringBuilder();

      for (Class<?> c = binding.getClass(); c != null && c != Object.class; c = c.getSuperclass()) {
         for (java.lang.reflect.Field f : c.getDeclaredFields()) {
            if (java.lang.reflect.Modifier.isStatic(f.getModifiers())) {
               continue;
            }

            if (seen.length() > 0) {
               seen.append(", ");
            }

            seen.append(f.getType().getSimpleName()).append(' ').append(f.getName());

            if (Expo.event.EventInvoker.class.isAssignableFrom(f.getType())) {
               hits.add(f);
            }
         }
      }

      if (hits.size() != 1) {
         String why = binding.getClass().getName() + " INVOKER FIELD UNRESOLVED: expected exactly 1 "
                      + "EventInvoker-typed instance field, found " + hits.size()
                      + " among [" + seen + ']';
         loud("invokerfield|" + binding.getClass().getName(), why);
         return why;
      }

      try {
         hits.get(0).setAccessible(true);
         Object inv = hits.get(0).get(binding);
         return inv == null ? binding.getClass().getName() + " <invoker field " + hits.get(0).getName()
                              + " is null>" : inv.getClass().getName();
      } catch (Throwable t) {
         String why = binding.getClass().getName() + " invoker field " + hits.get(0).getName()
                      + " unreadable: " + t;
         loud("invokerread|" + binding.getClass().getName(), why);
         return why;
      }
   }

   

   

   private static final Set<String> LOUD = new HashSet<String>();

   private static final int LOUD_CAP = 64;

   public static volatile int busFailures;

   public static volatile int structuralBusFailures;

   private static final java.util.Map<String, int[]> BUS_KINDS =
      new java.util.LinkedHashMap<String, int[]>();

   private static boolean loud(String key, String line) {
      synchronized (LOUD) {
         if (LOUD.size() >= LOUD_CAP || !LOUD.add(key)) {
            return false;
         }
      }

      System.err.println("[EXPO!] " + line);
      return true;
   }

   public static void busFailure(Object event, Object binding, Throwable t) {
      if (t == null) {
         return;
      }

      busFailures++;
      boolean structural = t instanceof LinkageError || t instanceof ClassNotFoundException
                           || t instanceof ExceptionInInitializerError;

      if (structural) {
         structuralBusFailures++;
      }

      String ev = event == null ? "null" : event.getClass().getName();
      String kind = (structural ? "STRUCTURAL " : "") + t.getClass().getName();

      synchronized (BUS_KINDS) {
         int[] n = BUS_KINDS.get(kind);

         if (n == null) {
            n = new int[1];
            BUS_KINDS.put(kind, n);
         }

         n[0]++;
      }

      if (loud("bus|" + ev + '|' + t.getClass().getName(),
               (structural ? "STRUCTURAL " : "") + "listener failure swallowed by EventBus: event="
               + ev + " listener=" + invokerName(binding) + " threw " + t
               + (structural ? "  <- a listener class is missing from the artifact; this is a "
                             + "build defect, not a gameplay error" : ""))) {
         t.printStackTrace();
      }
   }

   public static java.util.Map<String, int[]> busFailureKinds() {
      synchronized (BUS_KINDS) {
         return new java.util.LinkedHashMap<String, int[]>(BUS_KINDS);
      }
   }

   public static void resetLoud() {
      synchronized (LOUD) {
         LOUD.clear();
      }

      synchronized (BUS_KINDS) {
         BUS_KINDS.clear();
      }

      busFailures = 0;
      structuralBusFailures = 0;
   }

   public static void warn(String key, String line) {
      loud(key, line);
   }

   public static void renamed(String from, String to) {
      if (!ON) {
         return;
      }

      synchronized (SEEN) {
         if (!SEEN.add("rename|" + from)) {
            return;
         }
      }

      System.out.println("[EXPODIAG] REMAPPED reflective name " + from + " -> " + to);
   }
}

