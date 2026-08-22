package Expo.internal.restore;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;















































public final class ExpoSeedProbe {

   private static final String VERSION = "seedprobe-1";

   private static final String IMPOSSIBLE_FIELD = "zzNoSuchSeedField$$";

   private static int runs;

   private static boolean started;

   private ExpoSeedProbe() {
   }











   public static synchronized void start() {
      if (started) {
         return;
      }
      started = true;
      final long period = getLong("expo.seedprobe.period", 20000L);
      final int max = (int) getLong("expo.seedprobe.max", 40L);
      try {
         Runtime.getRuntime().addShutdownHook(new Thread("expo-seedprobe-exit") {
            public void run() {
               harvest("at-exit");
               forcePhase();
            }
         });
      } catch (Throwable t) {
         say("could not install the at-exit harvest: " + describe(t));
      }
      if (period <= 0L) {
         say("periodic harvest disabled by -Dexpo.seedprobe.period=" + period);
         return;
      }
      Thread t = new Thread("expo-seedprobe") {
         public void run() {
            for (int i = 0; i < max; i++) {
               try {
                  Thread.sleep(period);
               } catch (InterruptedException ie) {
                  return;
               }
               try {
                  harvest("t" + ((i + 1) * period / 1000L) + "s");
               } catch (Throwable th) {
                  say("periodic harvest threw: " + describe(th));
               }
            }
         }
      };
      t.setDaemon(true);
      t.setPriority(Thread.MIN_PRIORITY);
      t.start();
      say("armed: period=" + period + "ms max=" + max + " plus one at-exit harvest");
   }



























   static void forcePhase() {
      String dir = System.getProperty("expo.seedprobe.out", "expo-seeds");
      final File listFile =
         new File(new File(dir), System.getProperty("expo.seedprobe.forcelist",
                                                   "forcelist.txt"));
      if (!listFile.isFile()) {
         say("force phase SKIPPED: no " + listFile.getAbsolutePath());
         return;
      }
      final List<String> names = readLines(listFile);
      if (names.isEmpty()) {
         say("force phase SKIPPED: " + listFile.getAbsolutePath() + " is empty");
         return;
      }
      final ClassLoader loader = launchLoader();
      final long budget = getLong("expo.seedprobe.forcebudget", 180000L);
      final File out = new File(new File(dir), "seedprobe-force.tsv");
      say("force phase: " + names.size() + " names from " + listFile.getAbsolutePath()
          + " loader=" + (loader == null ? "NONE" : loader.getClass().getName())
          + " budget=" + budget + "ms -> " + out.getAbsolutePath());
      if (loader == null) {
         say("force phase ABORTED: the launchwrapper loader is not reachable, and the"
             + " app loader would define a DIFFERENT, untransformed class");
         return;
      }

      Thread worker = new Thread("expo-seedprobe-force") {
         public void run() {
            Writer w = null;
            int okCount = 0;
            int threwCount = 0;
            int absentCount = 0;
            int alreadyCount = 0;
            try {
               File d = new File(listFile.getParent() == null ? "." : listFile.getParent());
               if (!d.isDirectory()) {
                  d.mkdirs();
               }
               w = new OutputStreamWriter(new FileOutputStream(out), "UTF-8");
               w.write("# force phase, at exit, after the last honest snapshot\n");
               w.write("# loader=" + loader.getClass().getName() + "\n");
               w.write("# kind\tclass\tA\tB\tC\tD\n");
               long t0 = System.currentTimeMillis();
               for (int i = 0; i < names.size(); i++) {
                  String n = names.get(i);
                  if (System.currentTimeMillis() - t0 > budget) {
                     w.write("BUDGET\t" + n + "\tstopped after " + i + " of "
                             + names.size() + "\t\t\t\n");
                     break;
                  }
                  long c0 = System.currentTimeMillis();
                  Class<?> c = null;
                  String status;
                  String detail = "";
                  boolean wasInit = false;
                  try {
                     c = Class.forName(n, false, loader);
                     wasInit = isInitialised(c);
                  } catch (Throwable t) {
                     status = "NOT-FOUND";
                     detail = describe(t);
                     absentCount++;
                     w.write("CLASS\t" + n + "\t" + status + "\t"
                             + (System.currentTimeMillis() - c0) + "\t" + detail + "\t\n");
                     w.flush();
                     continue;
                  }
                  try {
                     Class.forName(n, true, loader);
                     status = wasInit ? "ALREADY-INITIALISED" : "CLINIT-OK";
                     if (wasInit) {
                        alreadyCount++;
                     } else {
                        okCount++;
                     }
                  } catch (Throwable t) {
                     status = "CLINIT-THREW";
                     detail = deepDescribe(t);
                     threwCount++;
                  }
                  w.write("CLASS\t" + n + "\t" + status + "\t"
                          + (System.currentTimeMillis() - c0) + "\t" + detail + "\t\n");
                  dumpMembers(w, n, c);
                  w.flush();
                  if (i == 0) {
                     harvest("mid-force");
                  }
               }
               w.write("# summary clinitOk=" + okCount + " clinitThrew=" + threwCount
                       + " notFound=" + absentCount + " alreadyInitialised=" + alreadyCount
                       + "\n");
            } catch (Throwable t) {
               say("force phase threw: " + describe(t));
            } finally {
               if (w != null) {
                  try {
                     w.flush();
                     w.close();
                  } catch (Throwable ignored) {
                  }
               }
            }
            say("force phase done: clinitOk=" + okCount + " clinitThrew=" + threwCount
                + " notFound=" + absentCount + " alreadyInitialised=" + alreadyCount);
         }
      };
      worker.setDaemon(true);
      worker.start();
      try {
         worker.join(budget + 30000L);
      } catch (InterruptedException ie) {
         say("force phase join interrupted");
      }
      if (worker.isAlive()) {
         say("force phase DID NOT FINISH inside the budget; the file is partial and the"
             + " after-force snapshot is taken anyway");
      }
      harvest("after-force");
   }









   private static void dumpMembers(Writer w, String name, Class<?> c) throws Exception {
      Field[] fs;
      try {
         fs = c.getDeclaredFields();
      } catch (Throwable t) {
         w.write("FIELDS-UNREADABLE\t" + name + "\t" + describe(t) + "\t\t\t\n");
         return;
      }
      for (int j = 0; j < fs.length; j++) {
         Field f = fs[j];
         if (!Modifier.isStatic(f.getModifiers())) {
            continue;
         }
         Class<?> ty = f.getType();
         Object v;
         try {
            f.setAccessible(true);
            v = f.get(null);
         } catch (Throwable t) {
            w.write("UNREADABLE\t" + name + "\t" + f.getName() + "\t" + describe(t)
                    + "\t\t\n");
            continue;
         }
         if (ty == long.class) {
            w.write("LONG\t" + name + "\t" + f.getName() + "\t" + v + "\t"
                    + (Modifier.isFinal(f.getModifiers()) ? "final" : "mutable") + "\t\n");
         } else if (ty == long[].class) {
            long[] a = (long[]) v;
            w.write("ARRAY_J\t" + name + "\t" + f.getName() + "\t"
                    + (a == null ? "null" : String.valueOf(a.length)) + "\t"
                    + joinLongs(a) + "\t\n");
         } else if (ty == Long[].class || ty == Integer[].class) {
            Object[] a = (Object[]) v;
            w.write("ARRAY_BOX\t" + name + "\t" + f.getName() + "\t"
                    + (a == null ? "null" : String.valueOf(a.length)) + "\t"
                    + joinObjects(a) + "\t" + ty.getName() + "\n");
         } else if (ty == String[].class) {
            String[] a = (String[]) v;
            w.write("ARRAY_STR\t" + name + "\t" + f.getName() + "\t"
                    + (a == null ? "null" : String.valueOf(a.length)) + "\t"
                    + joinStrings(a) + "\t\n");
         }
      }
   }

   private static String joinLongs(long[] a) {
      if (a == null) {
         return "";
      }
      StringBuilder b = new StringBuilder();
      for (int i = 0; i < a.length; i++) {
         if (i > 0) {
            b.append(',');
         }
         b.append(a[i]);
      }
      return b.toString();
   }

   private static String joinObjects(Object[] a) {
      if (a == null) {
         return "";
      }
      StringBuilder b = new StringBuilder();
      for (int i = 0; i < a.length; i++) {
         if (i > 0) {
            b.append(',');
         }
         b.append(a[i] == null ? "null" : String.valueOf(a[i]));
      }
      return b.toString();
   }


   private static String joinStrings(String[] a) {
      if (a == null) {
         return "";
      }
      StringBuilder b = new StringBuilder();
      for (int i = 0; i < a.length; i++) {
         if (i > 0) {
            b.append(',');
         }
         if (a[i] == null) {
            b.append("null");
            continue;
         }
         for (int k = 0; k < a[i].length(); k++) {
            String h = Integer.toHexString(a[i].charAt(k));
            for (int p = h.length(); p < 4; p++) {
               b.append('0');
            }
            b.append(h);
         }
      }
      return b.toString();
   }

   private static boolean isInitialised(Class<?> c) {
      try {
         Class<?> u = Class.forName("sun.misc.Unsafe");
         Field tu = u.getDeclaredField("theUnsafe");
         tu.setAccessible(true);
         Object unsafe = tu.get(null);
         Method m = u.getMethod("shouldBeInitialized", Class.class);
         return !((Boolean) m.invoke(unsafe, c)).booleanValue();
      } catch (Throwable t) {
         return false;
      }
   }

   private static ClassLoader launchLoader() {
      try {
         Class<?> launch = Class.forName("net.minecraft.launchwrapper.Launch", false,
                                         ExpoSeedProbe.class.getClassLoader());
         Field cl = launch.getDeclaredField("classLoader");
         cl.setAccessible(true);
         return (ClassLoader) cl.get(null);
      } catch (Throwable t) {
         say("launchwrapper loader unreachable: " + describe(t));
         return null;
      }
   }

   private static List<String> readLines(File f) {
      List<String> out = new ArrayList<String>();
      java.io.BufferedReader r = null;
      try {
         r = new java.io.BufferedReader(new java.io.InputStreamReader(
                new java.io.FileInputStream(f), "UTF-8"));
         String line;
         while ((line = r.readLine()) != null) {
            line = line.trim();
            if (line.length() != 0 && line.charAt(0) != '#') {
               out.add(line);
            }
         }
      } catch (Throwable t) {
         say("cannot read " + f.getAbsolutePath() + ": " + describe(t));
      } finally {
         if (r != null) {
            try {
               r.close();
            } catch (Throwable ignored) {
            }
         }
      }
      return out;
   }


   private static String deepDescribe(Throwable t) {
      StringBuilder b = new StringBuilder();
      int guard = 0;
      while (t != null && guard++ < 8) {
         if (b.length() > 0) {
            b.append(" <- ");
         }
         b.append(describe(t));
         StackTraceElement[] st = t.getStackTrace();
         if (st != null && st.length > 0) {
            b.append(" @").append(st[0].toString());
         }
         t = t.getCause() == t ? null : t.getCause();
      }
      return b.toString().replace('\t', ' ').replace('\n', ' ');
   }

   private static long getLong(String key, long dflt) {
      try {
         String v = System.getProperty(key);
         return v == null ? dflt : Long.parseLong(v.trim());
      } catch (Throwable t) {
         return dflt;
      }
   }


   public static void harvest() {
      harvest("explicit");
   }

   public static synchronized void harvest(String reason) {
      try {
         harvest0(reason);
      } catch (Throwable t) {
         say("harvest(" + reason + ") threw and was contained: " + describe(t));
      }
   }

   private static void harvest0(String reason) {
      int run = ++runs;
      long t0 = System.currentTimeMillis();
      List<String> log = new ArrayList<String>();
      List<String> problems = new ArrayList<String>();
      StringBuilder classes = new StringBuilder();
      StringBuilder statics = new StringBuilder();

      Object unsafe = null;
      Method shouldBeInitialized = null;
      String initCheck = "unavailable";
      boolean fakeMissing = "1".equals(System.getProperty("expo.seedprobe.noinitcheck"));
      if (fakeMissing) {
         initCheck = "DISABLED by -Dexpo.seedprobe.noinitcheck=1 (negative control)";
      } else {
         try {
            Class<?> u = Class.forName("sun.misc.Unsafe");
            Field tu = u.getDeclaredField("theUnsafe");
            tu.setAccessible(true);
            unsafe = tu.get(null);
            shouldBeInitialized = u.getMethod("shouldBeInitialized", Class.class);
            shouldBeInitialized.invoke(unsafe, ExpoSeedProbe.class);
            initCheck = "sun.misc.Unsafe.shouldBeInitialized";
         } catch (Throwable t) {
            unsafe = null;
            shouldBeInitialized = null;
            initCheck = "unavailable: " + describe(t);
         }
      }

      List<Class<?>> seen = enumerateDefined(log, problems);
      String prefix = System.getProperty("expo.seedprobe.prefix", "Expo.");
      List<Class<?>> matched = new ArrayList<Class<?>>();
      for (int i = 0; i < seen.size(); i++) {
         Class<?> c = seen.get(i);
         String n;
         try {
            n = c.getName();
         } catch (Throwable t) {
            continue;
         }
         if (n.startsWith(prefix)) {
            matched.add(c);
         }
      }
      Collections.sort(matched, new Comparator<Class<?>>() {
         public int compare(Class<?> a, Class<?> b) {
            return a.getName().compareTo(b.getName());
         }
      });

      int nInit = 0;
      int nUninit = 0;
      int nRead = 0;
      int nUnreadable = 0;
      int impossibleHits = 0;
      List<Class<?>> uninit = new ArrayList<Class<?>>();

      if (shouldBeInitialized == null) {
         for (int i = 0; i < matched.size(); i++) {
            Class<?> c = matched.get(i);
            classes.append(c.getName()).append('\t').append("REFUSED").append('\t')
                   .append(0).append('\t').append(loaderOf(c)).append('\n');
         }
         problems.add("REFUSING to read any static: without shouldBeInitialized an"
                      + " already-initialised class cannot be told apart from one whose"
                      + " <clinit> this read would TRIGGER, and a triggered seed is the"
                      + " wrong seed (measured: 19/170 correct in the wrong order).");
      } else {
         for (int i = 0; i < matched.size(); i++) {
            Class<?> c = matched.get(i);
            boolean init;
            try {
               init = !((Boolean) shouldBeInitialized.invoke(unsafe, c)).booleanValue();
            } catch (Throwable t) {
               classes.append(c.getName()).append('\t').append("INITCHECK-FAILED").append('\t')
                      .append(0).append('\t').append(loaderOf(c)).append('\n');
               problems.add("init check threw for " + c.getName() + ": " + describe(t));
               continue;
            }
            if (!init) {
               nUninit++;
               uninit.add(c);
               classes.append(c.getName()).append('\t').append("NOT-INITIALISED").append('\t')
                      .append(0).append('\t').append(loaderOf(c)).append('\n');
               continue;
            }
            nInit++;
            Field[] fs;
            try {
               fs = c.getDeclaredFields();
            } catch (Throwable t) {
               classes.append(c.getName()).append('\t').append("FIELDS-UNREADABLE").append('\t')
                      .append(0).append('\t').append(loaderOf(c)).append('\n');
               problems.add("getDeclaredFields threw for " + c.getName() + ": " + describe(t));
               continue;
            }
            int emitted = 0;
            for (int j = 0; j < fs.length; j++) {
               Field f = fs[j];
               if (!Modifier.isStatic(f.getModifiers()) || f.getType() != long.class) {
                  continue;
               }
               if (IMPOSSIBLE_FIELD.equals(f.getName())) {
                  impossibleHits++;
               }
               try {
                  f.setAccessible(true);
                  long v = f.getLong(null);
                  statics.append(c.getName()).append('\t').append(f.getName()).append('\t')
                         .append(v).append('\t')
                         .append(Modifier.isFinal(f.getModifiers()) ? "final" : "mutable")
                         .append('\n');
                  emitted++;
                  nRead++;
               } catch (Throwable t) {
                  statics.append(c.getName()).append('\t').append(f.getName()).append('\t')
                         .append("UNREADABLE").append('\t').append(describe(t)).append('\n');
                  nUnreadable++;
               }
            }
            classes.append(c.getName()).append('\t').append("INITIALISED").append('\t')
                   .append(emitted).append('\t').append(loaderOf(c)).append('\n');
         }
      }

      int flipped = 0;
      List<String> flippedNames = new ArrayList<String>();
      if (shouldBeInitialized != null) {
         for (int i = 0; i < uninit.size(); i++) {
            Class<?> c = uninit.get(i);
            try {
               if (!((Boolean) shouldBeInitialized.invoke(unsafe, c)).booleanValue()) {
                  flipped++;
                  if (flippedNames.size() < 40) {
                     flippedNames.add(c.getName());
                  }
               }
            } catch (Throwable ignored) {
            }
         }
      }
      String contamination = flipped == 0 ? "CLEAN" : "CONTAMINATED";

      StringBuilder head = new StringBuilder();
      head.append("# ").append(VERSION).append(" run=").append(run)
          .append(" reason=").append(reason)
          .append(" thread=").append(Thread.currentThread().getName())
          .append(" millis=").append(System.currentTimeMillis() - t0).append('\n');
      head.append("# initCheck=").append(initCheck).append('\n');
      head.append("# prefix=").append(prefix)
          .append(" classesSeen=").append(seen.size())
          .append(" matched=").append(matched.size())
          .append(" initialised=").append(nInit)
          .append(" uninitialised=").append(nUninit).append('\n');
      head.append("# staticLongsRead=").append(nRead)
          .append(" staticLongsUnreadable=").append(nUnreadable)
          .append(" impossibleFieldHits=").append(impossibleHits)
          .append(" (must be 0)").append('\n');
      head.append("# clinitTriggeredByUs=").append(flipped)
          .append(" verdict=").append(contamination).append('\n');
      for (int i = 0; i < flippedNames.size(); i++) {
         head.append("# FLIPPED\t").append(flippedNames.get(i)).append('\n');
      }
      for (int i = 0; i < log.size(); i++) {
         head.append("# log\t").append(log.get(i)).append('\n');
      }
      for (int i = 0; i < problems.size(); i++) {
         head.append("# problem\t").append(problems.get(i)).append('\n');
      }

      String dir = System.getProperty("expo.seedprobe.out", "expo-seeds");
      String stamp = String.valueOf(run) + "-" + reason;
      boolean ok = write(dir, "seedprobe-" + stamp + "-classes.tsv",
                         head.toString() + "class\tstate\tstaticLongs\tloader\n" + classes);
      ok &= write(dir, "seedprobe-" + stamp + "-statics.tsv",
                  head.toString() + "class\tfield\tvalue\tmodifier\n" + statics);

      say("run=" + run + " reason=" + reason + " initCheck=" + initCheck
          + " matched=" + matched.size() + " initialised=" + nInit
          + " uninitialised=" + nUninit + " staticLongsRead=" + nRead
          + " impossibleFieldHits=" + impossibleHits
          + " clinitTriggeredByUs=" + flipped + " verdict=" + contamination
          + " written=" + ok + " dir=" + new File(dir).getAbsolutePath());
   }

   @SuppressWarnings("unchecked")
   private static List<Class<?>> enumerateDefined(List<String> log, List<String> problems) {
      IdentityHashMap<Class<?>, Boolean> all = new IdentityHashMap<Class<?>, Boolean>();
      List<ClassLoader> loaders = new ArrayList<ClassLoader>();
      addLoader(loaders, ExpoSeedProbe.class.getClassLoader());
      try {
         addLoader(loaders, Thread.currentThread().getContextClassLoader());
      } catch (Throwable ignored) {
      }
      try {
         Class<?> launch = Class.forName("net.minecraft.launchwrapper.Launch", false,
                                         ExpoSeedProbe.class.getClassLoader());
         Field cl = launch.getDeclaredField("classLoader");
         cl.setAccessible(true);
         Object lcl = cl.get(null);
         addLoader(loaders, (ClassLoader) lcl);
         try {
            Field cc = lcl.getClass().getDeclaredField("cachedClasses");
            cc.setAccessible(true);
            Map<String, Class<?>> m = (Map<String, Class<?>>) cc.get(lcl);
            if (m != null) {
               List<Class<?>> snap = new ArrayList<Class<?>>(m.values());
               for (int i = 0; i < snap.size(); i++) {
                  if (snap.get(i) != null) {
                     all.put(snap.get(i), Boolean.TRUE);
                  }
               }
               log.add("source LaunchClassLoader.cachedClasses: " + snap.size());
            }
         } catch (Throwable t) {
            log.add("source LaunchClassLoader.cachedClasses: unreadable: " + describe(t));
         }
      } catch (Throwable t) {
         log.add("source Launch.classLoader: unreadable: " + describe(t));
      }

      Field classesField = null;
      try {
         classesField = ClassLoader.class.getDeclaredField("classes");
         classesField.setAccessible(true);
      } catch (Throwable t) {
         problems.add("ClassLoader.classes unreadable: " + describe(t));
      }
      if (classesField != null) {
         for (int i = 0; i < loaders.size(); i++) {
            ClassLoader l = loaders.get(i);
            try {
               Vector<Class<?>> v = (Vector<Class<?>>) classesField.get(l);
               if (v == null) {
                  continue;
               }
               List<Class<?>> snap;
               synchronized (v) {
                  snap = new ArrayList<Class<?>>(v);
               }
               for (int j = 0; j < snap.size(); j++) {
                  if (snap.get(j) != null) {
                     all.put(snap.get(j), Boolean.TRUE);
                  }
               }
               log.add("source " + l.getClass().getName() + "#classes: " + snap.size());
            } catch (Throwable t) {
               log.add("source " + l.getClass().getName() + "#classes: unreadable: "
                       + describe(t));
            }
         }
      }
      return new ArrayList<Class<?>>(all.keySet());
   }

   private static void addLoader(List<ClassLoader> into, ClassLoader l) {
      while (l != null) {
         if (!into.contains(l)) {
            into.add(l);
         }
         l = l.getParent();
      }
   }

   private static String loaderOf(Class<?> c) {
      try {
         ClassLoader l = c.getClassLoader();
         return l == null ? "bootstrap" : l.getClass().getName();
      } catch (Throwable t) {
         return "unknown";
      }
   }

   private static boolean write(String dir, String name, String body) {
      Writer w = null;
      try {
         File d = new File(dir);
         if (!d.isDirectory() && !d.mkdirs()) {
            say("cannot create " + d.getAbsolutePath());
            return false;
         }
         w = new OutputStreamWriter(new FileOutputStream(new File(d, name)), "UTF-8");
         w.write(body);
         w.flush();
         return true;
      } catch (Throwable t) {
         say("write " + name + " failed: " + describe(t));
         return false;
      } finally {
         if (w != null) {
            try {
               w.close();
            } catch (Throwable ignored) {
            }
         }
      }
   }

   private static String describe(Throwable t) {
      if (t == null) {
         return "null";
      }
      String m = t.getMessage();
      return t.getClass().getName() + (m == null ? "" : ": " + m);
   }

   private static void say(String s) {
      System.out.println("[EXPOSEED] " + s);
   }
}
