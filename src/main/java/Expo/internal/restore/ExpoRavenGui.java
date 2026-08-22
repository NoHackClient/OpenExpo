package Expo.internal.restore;

import Expo.module.Category;
import Expo.module.ModuleManager;
import Expo.module.impl.configuration.ClickGUI;
import Expo.ui.raven.RavenAnimation;
import Expo.ui.raven.RavenCategoryPanel;
import Expo.ui.raven.RavenClickGuiScreen;
import Expo.ui.raven.RavenFramebuffer;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public final class ExpoRavenGui {
   public static final long AD2_SEED_A = 52686871891298L;

   public static final long AI_SEED_B = 7843458566225L;

   public static final long AD2_CTOR_LOW15 = 23731L;

   public static final long AD2_INITMAIN_LOW15 = 7275L;

   public static final int AD2_CTOR_SLOT = 7;
   public static final int ZA2_CTOR_SLOT = 2;
   public static final long ZA2_SEED_LOW15 = 2270L;

   public static final long AI_K_SEED = 63404359328282L;

   public static final long AI_CTOR_CARRIER = AI_K_SEED ^ 68863845366281L;

   public static final int AI_CTOR_V1 = (int)(AI_CTOR_CARRIER >>> 32);
   public static final char AI_CTOR_V3 = (char)(AI_CTOR_CARRIER >>> 16);
   public static final int AI_CTOR_V4 = (int)(AI_CTOR_CARRIER & 0xFFFFL);

   public static final int EXECUTOR_CORE = 8;

   public static final int PANEL_X = 5;
   public static final int PANEL_Y0 = 5;
   public static final int PANEL_DY = 20;

   public static final int EXPECT_WIDTH = 92;
   public static final int EXPECT_HEADER = 13;
   public static final long EXPECT_ANIM_MILLIS = 600L;
   public static final int EXPECT_TITLE_X_OFF = 12;
   public static final int EXPECT_SIGN_X_OFF = 80;

   public static final long DES_PARITY_BITS = 0x0101010101010101L;

   public static final long GATE_POISON = 0x0000000200000000L;

   public static RavenClickGuiScreen RAVEN;

   public static final List<String> DEGRADED = new ArrayList<String>();

   private static boolean attempted;

   private static ScheduledThreadPoolExecutor EXECUTOR;

   public static int rows;

   public static int settingRows;

   public static String rowTally = "not built";

   private static int modulesIn(Category c) {
      if (ModuleManager.S == null) {
         return -1;
      }

      int n = 0;

      for (int i = 0; i < ModuleManager.S.size(); i++) {
         Expo.module.Module m = ModuleManager.S.get(i);

         if (m != null && c.equals(m.f())) {
            n++;
         }
      }

      return n;
   }

   private ExpoRavenGui() {
   }

   public static void installPanels() {
      if (RavenClickGuiScreen.A == null) {
         RavenClickGuiScreen.A = EXECUTOR = new ScheduledThreadPoolExecutor(EXECUTOR_CORE, new RavenThreads());
      }

      Map<Category, RavenCategoryPanel> panels = new HashMap<Category, RavenCategoryPanel>();
      List<Category> order = new ArrayList<Category>();

      Category[] cats = Category.values();

      for (int i = 0; i < cats.length; i++) {
         int y = PANEL_Y0 + PANEL_DY * i;
         RavenCategoryPanel panel;

         try {
            panel = new RavenCategoryPanel(AI_CTOR_V1, cats[i], AI_CTOR_V3, AI_CTOR_V4);
            panel.k(y);
         } catch (Throwable t) {
            panel = new RavenCategoryPanel(cats[i], y);
            DEGRADED.add("Expo/ui/raven/RavenCategoryPanel(" + cats[i] + ") fell back to the row-less constructor: the ZKM "
                         + "one threw " + String.valueOf(t) + " -- that panel has 0 module rows");
         }

         panels.put(cats[i], panel);
         order.add(cats[i]);
      }

      RavenClickGuiScreen.P = panels;
      RavenClickGuiScreen.h = order;
   }

   public static RavenClickGuiScreen installRaven(List<String> pending) {
      if (attempted) {
         return RAVEN;
      }

      attempted = true;

      if ("0".equals(System.getProperty("expo.raven"))
          || "0".equals(System.getenv("EXPO_RAVEN"))) {
         return refuse(pending, "disabled by expo.raven=0 / EXPO_RAVEN=0 (attribution run)");
      }

      long ad2a;
      long aib;
      long za2d;
      try {
         ad2a = readLong(RavenClickGuiScreen.class, "a");
         aib = readLong(RavenCategoryPanel.class, "b");
         za2d = readLong(RavenFramebuffer.class, "d");
      } catch (Throwable t) {
         return refuse(pending, "cannot read the ZKM class seeds (" + t + ')');
      }

      if (ad2a != AD2_SEED_A) {
         return refuse(pending, "Expo/Ad_2.a is " + ad2a + ", the derivation used " + AD2_SEED_A
                                + " -- every slot index below would be wrong");
      }

      if (aib != AI_SEED_B) {
         return refuse(pending, "Expo/AI.b is " + aib + ", the derivation used " + AI_SEED_B);
      }

      if ((za2d & 32767L) != ZA2_SEED_LOW15) {
         return refuse(pending, "Expo/ui/raven/RavenFramebuffer.d & 32767 is " + (za2d & 32767L) + ", solved "
                                + ZA2_SEED_LOW15);
      }

      long seedT = AI_SEED_B ^ AD2_SEED_A ^ 119244037176950L;
      long chainT = 3301566471831211452L ^ seedT;
      long chainTitle = 5790399078481539381L ^ seedT;
      long chainSign = 8131522771472482745L ^ seedT;

      if (numericLayerInlined()) {
         note(pending, "Expo/ui/raven/RavenCategoryPanel numeric layer is inlined "
                       + "(width/titleX/signX are literals); gate 2 has nothing to decrypt");
      } else {
      Integer[] cache = aiCache();

      if (cache == null || cache.length != 21) {
         return refuse(pending, "Expo/AI.f is " + (cache == null ? "null" : "length "
                                                   + cache.length) + ", expected 21");
      }

      long poison = chainT ^ GATE_POISON;

      if ((GATE_POISON & 32767L) != 0L) {
         return refuse(pending, "the negative control moves the table slot, so it would read a "
                                + "different cell instead of testing the key");
      }

      if ((GATE_POISON & DES_PARITY_BITS) != 0L) {
         return refuse(pending, "the negative control only flips DES parity bits, which "
                                + "DESKeySpec discards -- the 'corrupted' key is the same key");
      }

      int slotW = 3825 ^ (int)(chainT & 32767L) ^ 14360;
      cache[slotW] = null;
      Integer poisoned = aiConst(3825, poison);
      cache[slotW] = null;

      if (poisoned != null && poisoned.intValue() == EXPECT_WIDTH) {
         return refuse(pending, "gate 2 is dead: a corrupted chain on the same table cell still "
                                + "decrypted to " + EXPECT_WIDTH);
      }

      Integer width = aiConst(3825, chainT);
      Integer titleX = aiConst(29295, chainTitle);
      Integer signX = aiConst(17129, chainSign);

      if (width == null || width.intValue() != EXPECT_WIDTH
          || titleX == null || titleX.intValue() != EXPECT_TITLE_X_OFF
          || signX == null || signX.intValue() != EXPECT_SIGN_X_OFF) {
         return refuse(pending, "Expo/ui/raven/RavenCategoryPanel numeric layer disagrees with the 2.4.6 dump: width="
                                + width + " titleX=" + titleX + " signX=" + signX
                                + ", expected " + EXPECT_WIDTH + '/' + EXPECT_TITLE_X_OFF + '/'
                                + EXPECT_SIGN_X_OFF + " (negative control returned " + poisoned
                                + ')');
      }
      }

      if (RavenCategoryPanel.ravenAnimationMillis() != EXPECT_ANIM_MILLIS) {
         return refuse(pending, "Expo/AI.i is " + RavenCategoryPanel.ravenAnimationMillis() + ", the 2.4.6 dump "
                                + "recorded " + EXPECT_ANIM_MILLIS);
      }

      if (!seed(pending, RavenClickGuiScreen.class, "c", 14, AD2_CTOR_SLOT)
          || !seed(pending, RavenFramebuffer.class, "k", 12, ZA2_CTOR_SLOT)) {
         return null;
      }

      RavenClickGuiScreen screen;
      try {
         screen = new RavenClickGuiScreen(AD2_SEED_A ^ AD2_CTOR_LOW15);
      } catch (Throwable t) {
         return refuse(pending, "Expo/ui/raven/RavenClickGuiScreen constructor threw " + String.valueOf(t));
      }

      if (RavenClickGuiScreen.P == null || RavenClickGuiScreen.P.size() != 9 || RavenClickGuiScreen.h == null || RavenClickGuiScreen.h.size() != 9) {
         return refuse(pending, "N.P replacement published " + size(RavenClickGuiScreen.P) + " panels and "
                                + size(RavenClickGuiScreen.h) + " draw-order entries, expected 9 and 9");
      }

      if (RavenClickGuiScreen.A == null) {
         return refuse(pending, "Expo/Ad_2.A is still null; Ad_2.P(J)V would NPE on open");
      }

      if (screen.Z == null) {
         return refuse(pending, "Expo/Ad_2.Z (the Za_2 blur) is null");
      }

      Category[] cats = Category.values();

      for (int i = 0; i < cats.length; i++) {
         RavenCategoryPanel p = RavenClickGuiScreen.P.get(cats[i]);

         if (p == null) {
            return refuse(pending, "no RAVEN panel for " + cats[i]);
         }

         if (p.ravenWidth() != EXPECT_WIDTH || p.ravenHeader() != EXPECT_HEADER
             || p.X() != PANEL_X || p.T() != PANEL_Y0 + PANEL_DY * i) {
            return refuse(pending, "RAVEN panel " + cats[i] + " is " + p.X() + ',' + p.T() + ' '
                                   + p.ravenWidth() + 'x' + p.ravenHeader() + ", the 2.4.6 dump "
                                   + "had " + PANEL_X + ',' + (PANEL_Y0 + PANEL_DY * i) + ' '
                                   + EXPECT_WIDTH + 'x' + EXPECT_HEADER);
         }

         if (cats[i] != RavenClickGuiScreen.h.get(i)) {
            return refuse(pending, "draw order slot " + i + " is " + RavenClickGuiScreen.h.get(i) + ", the "
                                   + "2.4.6 dump had declaration order");
         }
      }

      rows = 0;
      settingRows = 0;
      StringBuilder tally = new StringBuilder();

      for (int i = 0; i < cats.length; i++) {
         RavenCategoryPanel p = RavenClickGuiScreen.P.get(cats[i]);
         int got = p.s() == null ? -1 : p.s().size();
         int expect = modulesIn(cats[i]);

         for (int r = 0; got > 0 && r < got; r++) {
            List<?> sub = p.s().get(r).H;
            settingRows += sub == null ? 0 : sub.size();
         }

         rows += got < 0 ? 0 : got;
         tally.append(i == 0 ? "" : " ").append(cats[i]).append('=').append(got);

         if (got != expect) {
            return refuse(pending, "RAVEN panel " + cats[i] + " built " + got + " module row(s), "
                                   + "tD.S holds " + expect + " module(s) in that category");
         }
      }

      if (rows == 0) {
         return refuse(pending, "every RAVEN panel came up with 0 module rows even though tD.S "
                                + "holds " + (ModuleManager.S == null ? -1 : ModuleManager.S.size()) + " module(s)");
      }

      rowTally = tally.toString();

      int y = readInt(screen, "y");

      if (y != 0) {
         return refuse(pending, "Expo/Ad_2.y came out " + y + ", not 0 -- the pre-seeded slot "
                                + AD2_CTOR_SLOT + " was not the one the constructor read");
      }

      ClickGUI.F = screen;
      RAVEN = screen;

      note(pending, "Expo.ui.raven.RavenClickGuiScreen (RAVEN theme) published into zu_3.F: 9 panels at x=" + PANEL_X
                    + " y=" + PANEL_Y0 + "+" + PANEL_DY + "*ordinal, " + EXPECT_WIDTH + 'x'
                    + EXPECT_HEADER + ", " + rows + " module rows (" + rowTally + "), "
                    + settingRows + " setting rows");
      return screen;
   }

   public static boolean primeFirstFrame(RavenClickGuiScreen screen) {
      if (screen == null) {
         return false;
      }

      try {
         (screen.w = new RavenAnimation(500.0F)).y();
         return true;
      } catch (Throwable t) {
         return false;
      }
   }

   public static void shutdownExecutor() {
      ScheduledThreadPoolExecutor e = EXECUTOR;

      if (e != null) {
         e.shutdownNow();
      }
   }

   public static List<String> degraded() {
      return DEGRADED;
   }

   private static final class RavenThreads implements ThreadFactory {
      private final AtomicInteger n = new AtomicInteger(1);

      public Thread newThread(Runnable r) {
         Thread t = new Thread(r, "Expo-RAVEN-anim-" + this.n.getAndIncrement());
         t.setDaemon(true);
         return t;
      }
   }

   private static Integer[] aiCache() {
      try {
         Field f = RavenCategoryPanel.class.getDeclaredField("f");
         f.setAccessible(true);
         return (Integer[])f.get(null);
      } catch (Throwable t) {
         return null;
      }
   }

   private static boolean numericLayerInlined() {
      return numericLayerInlined(RavenCategoryPanel.class);
   }

   /** true once deobfuscation removed the class's numeric decryptor */
   private static boolean numericLayerInlined(Class<?> cls) {
      try {
         cls.getDeclaredMethod("a", int.class, long.class);
         return false;
      } catch (NoSuchMethodException gone) {
         return true;
      } catch (Throwable t) {
         return false;
      }
   }

   private static Integer aiConst(int n, long chain) {
      int slot = n ^ (int)(chain & 32767L) ^ 14360;

      if (slot < 0 || slot >= 21) {
         return null;
      }

      try {
         Method m = RavenCategoryPanel.class.getDeclaredMethod("a", int.class, long.class);
         m.setAccessible(true);
         return (Integer)m.invoke(null, Integer.valueOf(n), Long.valueOf(chain));
      } catch (Throwable t) {
         return null;
      }
   }

   private static boolean seed(List<String> pending, Class<?> cls, String name, int len, int slot) {
      if (numericLayerInlined(cls)) {
         note(pending, cls.getName() + '.' + name + " is a vestigial ZKM ctor-slot cache: the "
                       + "class has no numeric decryptor left, so there is no slot to reserve");
         return true;
      }

      try {
         Field f = cls.getDeclaredField(name);
         f.setAccessible(true);
         Integer[] tab = (Integer[])f.get(null);

         if (tab == null) {
            refuse(pending, cls.getName() + '.' + name + " is null; the class did not run its "
                            + "ZKM clinit");
            return false;
         }

         if (tab.length != len) {
            refuse(pending, cls.getName() + '.' + name + " has length " + tab.length + ", the "
                            + "slot bijection assumed " + len);
            return false;
         }

         if (tab[slot] != null && tab[slot].intValue() != 0) {
            refuse(pending, cls.getName() + '.' + name + '[' + slot + "] is already "
                            + tab[slot] + " -- some in-jar site reads this slot, so the "
                            + "bijection is wrong and seeding it would corrupt that site");
            return false;
         }

         tab[slot] = Integer.valueOf(0);
         return true;
      } catch (Throwable t) {
         refuse(pending, "cannot seed " + cls.getName() + '.' + name + '[' + slot + "] (" + t + ')');
         return false;
      }
   }

   private static long readLong(Class<?> cls, String name) throws Exception {
      Field f = cls.getDeclaredField(name);
      f.setAccessible(true);
      return f.getLong(null);
   }

   private static int readInt(Object o, String name) {
      try {
         Field f = o.getClass().getDeclaredField(name);
         f.setAccessible(true);
         return f.getInt(o);
      } catch (Throwable t) {
         return Integer.MIN_VALUE;
      }
   }

   private static int size(Object o) {
      if (o instanceof Map) {
         return ((Map<?, ?>)o).size();
      }

      if (o instanceof List) {
         return ((List<?>)o).size();
      }

      return -1;
   }

   private static RavenClickGuiScreen refuse(List<String> pending, String why) {
      DEGRADED.add("Expo.module.impl.configuration.ClickGUI.F : Expo/ui/raven/RavenClickGuiScreen REFUSED -- " + why);
      note(pending, "Expo.ui.raven.RavenClickGuiScreen (RAVEN theme) REFUSED -- " + why);
      return null;
   }

   private static void note(List<String> pending, String s) {
      if (pending != null) {
         pending.add(s);
      }
   }
}
