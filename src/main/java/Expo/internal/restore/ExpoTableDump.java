package Expo.internal.restore;

import java.io.File;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

// add code
/**
 * Dumps the ZKM runtime tables of every loaded Expo class.
 *
 * ZKM caches what it decrypts: string pools land in a static String[], numeric
 * pools in an Integer[]/long[], and the reflection layer leaves resolved
 * Class/Field/Method objects in an Object[] beside the decoded names in a
 * String[]. Reading those after the client has exercised itself yields the
 * plaintext of every slot the program actually touched, with no key recovery.
 *
 * Inert unless -Dexpo.tabledump=<file>. Reads fields only, never invokes.
 * Writes on a timer (-Dexpo.tabledump.every=<seconds>, 0 disables) and again at
 * shutdown, so a client that hangs or is closed by hand still produces data.
 */
public final class ExpoTableDump {

   private static final String OUT = System.getProperty("expo.tabledump");

   private ExpoTableDump() {
   }

   public static void install() {
      if (OUT == null || OUT.isEmpty()) {
         return;
      }
      Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
         public void run() {
            try {
               dump(new File(OUT));
            } catch (Throwable t) {
            }
         }
      }, "expo-table-dump"));

      int every = 45;
      try {
         every = Integer.parseInt(System.getProperty("expo.tabledump.every", "45"));
      } catch (Throwable ignored) {
      }
      if (every > 0) {
         final long ms = every * 1000L;
         Thread t = new Thread(new Runnable() {
            public void run() {
               while (true) {
                  try {
                     Thread.sleep(ms);
                     dump(new File(OUT));
                  } catch (InterruptedException stop) {
                     return;
                  } catch (Throwable ignored) {
                  }
               }
            }
         }, "expo-table-dump-timer");
         t.setDaemon(true);
         t.start();
      }
      System.out.println("[EXPODUMP] armed -> " + OUT + " (every " + every + "s)");

      // add code -- force-init runs on its own thread, not the dump timer: it
      // initialises a thousand classes and would otherwise stall every dump
      // behind it. The property value is printed either way, because a silent
      // no-op is exactly how this failed to run three times in a row.
      final String force = System.getProperty("expo.tabledump.force");
      if (force != null) {
         Thread ft = new Thread(new Runnable() {
            public void run() {
               try {
                  Thread.sleep(12000L);
               } catch (InterruptedException stop) {
                  return;
               }
               forceInit();
            }
         }, "expo-table-dump-force");
         ft.setDaemon(true);
         ft.start();
      }
   }

   /** classes this loader has actually defined */
   private static List<Class<?>> loaded() {
      List<Class<?>> out = new ArrayList<Class<?>>();
      ClassLoader cl = ExpoTableDump.class.getClassLoader();
      while (cl != null) {
         try {
            Field f = ClassLoader.class.getDeclaredField("classes");
            f.setAccessible(true);
            Object v = f.get(cl);
            if (v instanceof Vector) {
               for (Object o : new ArrayList<Object>((Vector<?>)v)) {
                  if (o instanceof Class && ((Class<?>)o).getName().startsWith("Expo")) {
                     out.add((Class<?>)o);
                  }
               }
            }
         } catch (Throwable ignored) {
         }
         cl = cl.getParent();
      }
      return out;
   }

   // add code
   /**
    * A class that never ran keeps its pools encrypted, so a passive dump only
    * ever sees the slots this session happened to reach. Walking the mod archive
    * and initialising every entry closes that gap; each one is attempted on its
    * own so a class whose static setup needs a world or a GL context only costs
    * its own row. Behind -Dexpo.tabledump.force so the default stays read-only.
    */
   private static int forceInit() {
      List<String> names = new ArrayList<String>();
      try {
         java.net.URL u = ExpoTableDump.class.getProtectionDomain()
                              .getCodeSource().getLocation();
         // Under Forge's loader this is not the archive but a jar: URL naming the
         // class inside it --
         //   jar:file:/D:/.../expo.jar!/Expo/internal/restore/ExpoTableDump.class
         // which is neither a file nor a directory, so the scan found zero
         // entries and reported no error at all. Take the part before "!/", and
         // fall back to the "/D:/..." leading-slash strip for a plain file: URL.
         String p = u.toString();
         if (p.startsWith("jar:")) {
            p = p.substring(4);
         }
         int bang = p.indexOf("!/");
         if (bang >= 0) {
            p = p.substring(0, bang);
         }
         if (p.startsWith("file:")) {
            p = p.substring(5);
         }
         p = java.net.URLDecoder.decode(p, "UTF-8");
         while (p.length() > 2 && p.charAt(0) == '/' && p.charAt(2) == ':') {
            p = p.substring(1);
         }
         File f = new File(p);
         System.out.println("[EXPODUMP] code source file = " + f + " dir="
                            + f.isDirectory() + " file=" + f.isFile());
         // dev runClient serves the mod from build/classes, a packaged run from a
         // jar; a directory-only reader silently found nothing under gradle
         if (f.isDirectory()) {
            collect(f, "", names);
         } else if (f.isFile()) {
            java.util.zip.ZipFile z = new java.util.zip.ZipFile(f);
            java.util.Enumeration<? extends java.util.zip.ZipEntry> e = z.entries();
            while (e.hasMoreElements()) {
               names.add(e.nextElement().getName());
            }
            z.close();
         }
      } catch (Throwable t) {
      }

      ClassLoader cl = ExpoTableDump.class.getClassLoader();
      int ok = 0;
      int bad = 0;
      for (String n : names) {
         if (!n.startsWith("Expo/") || !n.endsWith(".class")) {
            continue;
         }
         String cn = n.substring(0, n.length() - 6).replace('/', '.');
         if (cn.startsWith("Expo.internal.restore.")
             || cn.startsWith("Expo.internal.drm.")
             || cn.startsWith("Expo.internal.jnic.")) {
            continue;
         }
         try {
            Class.forName(cn, true, cl);
            ok++;
         } catch (Throwable t) {
            bad++;
         }
      }
      System.out.println("[EXPODUMP] force-init ok=" + ok + " failed=" + bad
                         + " of " + names.size() + " entry(ies)");
      return ok;
   }

   private static void collect(File dir, String prefix, List<String> out) {
      File[] fs = dir.listFiles();
      if (fs == null) {
         return;
      }
      for (File f : fs) {
         if (f.isDirectory()) {
            collect(f, prefix + f.getName() + "/", out);
         } else if (f.getName().endsWith(".class")) {
            out.add(prefix + f.getName());
         }
      }
   }

   private static String esc(String s) {
      StringBuilder b = new StringBuilder(s.length() + 8);
      for (int i = 0; i < s.length(); i++) {
         char c = s.charAt(i);
         if (c == '\\') {
            b.append("\\\\");
         } else if (c == '\t') {
            b.append("\\t");
         } else if (c == '\n') {
            b.append("\\n");
         } else if (c == '\r') {
            b.append("\\r");
         } else if (c < 32 || c > 126) {
            b.append(String.format("\\u%04x", (int)c));
         } else {
            b.append(c);
         }
      }
      return b.toString();
   }

   private static void dump(File out) throws Exception {
      File parent = out.getParentFile();
      if (parent != null) {
         parent.mkdirs();
      }
      PrintWriter w = new PrintWriter(out, "UTF-8");
      w.println("# class\tfield\ttype\tindex\tkind\tvalue");
      int classes = 0;
      int rows = 0;
      for (Class<?> c : loaded()) {
         Field[] fs;
         try {
            fs = c.getDeclaredFields();
         } catch (Throwable t) {
            continue;
         }
         boolean any = false;
         for (Field f : fs) {
            if (!Modifier.isStatic(f.getModifiers())) {
               continue;
            }
            // add code -- numeric pools decrypt into a plain static, not an array
            if (!f.getType().isArray()) {
               Class<?> t = f.getType();
               if (!t.isPrimitive() && t != String.class) {
                  continue;
               }
               Object sv;
               try {
                  f.setAccessible(true);
                  sv = f.get(null);
               } catch (Throwable t2) {
                  continue;
               }
               if (sv == null) {
                  continue;
               }
               w.println(c.getName() + "\t" + f.getName() + "\t" + t.getName()
                         + "\t-1\t" + (sv instanceof String ? "string" : "scalar")
                         + "\t" + (sv instanceof String ? esc((String)sv)
                                                        : String.valueOf(sv)));
               rows++;
               any = true;
               continue;
            }
            Object arr;
            try {
               f.setAccessible(true);
               arr = f.get(null);
            } catch (Throwable t) {
               continue;
            }
            if (arr == null) {
               continue;
            }
            String tn = f.getType().getComponentType().getName();
            int n;
            try {
               n = Array.getLength(arr);
            } catch (Throwable t) {
               continue;
            }
            for (int i = 0; i < n; i++) {
               Object v;
               try {
                  v = Array.get(arr, i);
               } catch (Throwable t) {
                  continue;
               }
               if (v == null) {
                  continue;
               }
               String kind;
               String text;
               if (v instanceof String) {
                  kind = "string";
                  text = esc((String)v);
               } else if (v instanceof Class) {
                  kind = "class";
                  text = ((Class<?>)v).getName();
               } else if (v instanceof Field) {
                  Field g = (Field)v;
                  kind = "field";
                  text = g.getDeclaringClass().getName() + "#" + g.getName()
                         + ":" + g.getType().getName();
               } else if (v instanceof Method) {
                  Method g = (Method)v;
                  StringBuilder sig = new StringBuilder();
                  for (Class<?> p : g.getParameterTypes()) {
                     if (sig.length() > 0) {
                        sig.append(',');
                     }
                     sig.append(p.getName());
                  }
                  kind = "method";
                  text = g.getDeclaringClass().getName() + "#" + g.getName()
                         + "(" + sig + ")" + g.getReturnType().getName();
               } else if (v instanceof Number || v instanceof Boolean
                          || v instanceof Character) {
                  kind = "number";
                  text = String.valueOf(v);
               } else {
                  continue;
               }
               w.println(c.getName() + "\t" + f.getName() + "\t" + tn + "\t" + i
                         + "\t" + kind + "\t" + text);
               rows++;
               any = true;
            }
         }
         if (any) {
            classes++;
         }
      }
      w.println("# classes=" + classes + " rows=" + rows);
      w.flush();
      w.close();
      System.out.println("[EXPODUMP] wrote " + rows + " rows from " + classes
                        + " classes -> " + out.getAbsolutePath());
   }
}
