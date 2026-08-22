package Expo.internal.restore;

import java.io.File;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;

// add code
/**
 * Records what each zkm$unresolved bootstrap actually resolved to.
 *
 * The bootstrap picks its target by running the class's own reflection-name
 * decoder over the two carrier longs at the call site, then binds it with
 * findVirtual / findStatic / findSpecial / find(Static)Getter / find(Static)Setter
 * according to the first character of the name argument. Offline that needs the
 * carrier to be a literal, which it is at only one of the 24 sites -- but the
 * client resolves every one it executes, so read the answer there.
 *
 * Inert unless -Dexpo.handleprobe=<file>. Logs one line per distinct
 * (owner, kind, v0, v1) and never changes what the bootstrap returns.
 */
public final class ExpoHandleProbe {

   private static final String OUT = System.getProperty("expo.handleprobe");

   private static final Map<String, String> SEEN = new TreeMap<String, String>();

   private static boolean hooked;

   private ExpoHandleProbe() {
   }

   public static Member log(String owner, char kind, long v0, long v1, Member m) {
      if (OUT == null || m == null) {
         return m;
      }

      String key = owner + "\t" + kind + "\t" + v0 + "\t" + v1;

      synchronized (SEEN) {
         if (!SEEN.containsKey(key)) {
            String what;
            if (m instanceof Method) {
               Method g = (Method)m;
               StringBuilder sig = new StringBuilder();
               Class<?>[] ps = g.getParameterTypes();

               for (int i = 0; i < ps.length; i++) {
                  if (i > 0) {
                     sig.append(',');
                  }
                  sig.append(ps[i].getName());
               }

               what = "method\t" + g.getDeclaringClass().getName() + "\t" + g.getName()
                      + "\t(" + sig + ")" + g.getReturnType().getName();
            } else {
               Field g = (Field)m;
               what = "field\t" + g.getDeclaringClass().getName() + "\t" + g.getName()
                      + "\t" + g.getType().getName();
            }

            SEEN.put(key, what);
            arm();
         }
      }

      return m;
   }

   private static void arm() {
      if (hooked) {
         return;
      }

      hooked = true;
      Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
         public void run() {
            dump();
         }
      }, "expo-handle-probe"));

      // a killed client never runs its shutdown hooks, so also write on a timer
      Thread t = new Thread(new Runnable() {
         public void run() {
            while (true) {
               try {
                  Thread.sleep(15000L);
                  dump();
               } catch (InterruptedException stop) {
                  return;
               } catch (Throwable ignored) {
               }
            }
         }
      }, "expo-handle-probe-timer");
      t.setDaemon(true);
      t.start();
   }

   public static void dump() {
      if (OUT == null) {
         return;
      }

      try {
         File f = new File(OUT);

         if (f.getParentFile() != null) {
            f.getParentFile().mkdirs();
         }

         PrintWriter w = new PrintWriter(f, "UTF-8");
         w.println("# owner\tkind\tv0\tv1\tmemberKind\tdeclaringClass\tname\tsignature");

         synchronized (SEEN) {
            for (Map.Entry<String, String> e : SEEN.entrySet()) {
               w.println(e.getKey() + "\t" + e.getValue());
            }

            System.out.println("[EXPOHANDLE] wrote " + SEEN.size() + " resolution(s) -> "
                               + f.getAbsolutePath());
         }

         w.flush();
         w.close();
      } catch (Throwable t) {
         System.out.println("[EXPOHANDLE] dump failed: " + t);
      }
   }
}
