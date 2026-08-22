package Expo.internal.restore;

import java.io.File;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

// add code
/**
 * Records the actual value of every ZKM carrier parameter the client executes.
 *
 * Static analysis roots the carrier chain at the event bus and at constant call
 * sites, but thousands of parameter slots are only ever reached through a caller
 * whose own carrier is unknown, so the chain has no root there. Those values are
 * not secret -- the running client passes them. CarrierProbePass injects a call
 * to see() at the head of every method with a long parameter; a slot observed
 * with exactly one value across a whole session is a root with direct evidence.
 *
 * Inert unless -Dexpo.carrierlog=&lt;file&gt;. Never reads or changes program state.
 */
public final class ExpoCarrierLog {

   private static final String OUT = System.getProperty("expo.carrierlog");

   /** key -> the distinct values seen; capped so a hot slot cannot grow forever */
   private static final Map<String, Set<Long>> SEEN = new TreeMap<String, Set<Long>>();

   private static final int CAP = 8;

   private static boolean armed;

   private ExpoCarrierLog() {
   }

   public static void see(String key, long v) {
      if (OUT == null) {
         return;
      }

      synchronized (SEEN) {
         Set<Long> s = SEEN.get(key);

         if (s == null) {
            SEEN.put(key, s = new TreeSet<Long>());
            arm();
         }

         if (s.size() < CAP) {
            s.add(Long.valueOf(v));
         }
      }
   }

   private static void arm() {
      if (armed) {
         return;
      }

      armed = true;
      Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
         public void run() {
            dump();
         }
      }, "expo-carrier-log"));

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
      }, "expo-carrier-log-timer");
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
         w.println("# key\tdistinctValues");
         int single = 0;

         synchronized (SEEN) {
            for (Map.Entry<String, Set<Long>> e : SEEN.entrySet()) {
               StringBuilder b = new StringBuilder();

               for (Long v : e.getValue()) {
                  if (b.length() > 0) {
                     b.append(',');
                  }
                  b.append(v);
               }

               if (e.getValue().size() == 1) {
                  single++;
               }

               w.println(e.getKey() + "\t" + b);
            }

            System.out.println("[EXPOCARRIER] " + SEEN.size() + " slot(s), " + single
                               + " single-valued -> " + f.getAbsolutePath());
         }

         w.flush();
         w.close();
      } catch (Throwable t) {
      }
   }
}
