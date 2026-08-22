package Expo.internal.restore;

import Expo.module.Module;
import Expo.module.ModuleManager;
import Expo.module.impl.configuration.ClickGUI;
import Expo.ui.studio.StudioClickGuiScreen;
import Expo.ui.vestige.VestigeClickGuiScreen;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public final class ExpoClickGui {
   public static final long CTOR_VAR1 = 135029260739073L;

   public static final long MEASURED_SEED_A = 14637767574010L;

   public static final int EXPECT_D = 20;
   public static final int EXPECT_M = 18;
   public static final int EXPECT_O = 14;
   public static final int EXPECT_Y = 0;

   public static final long TS2_CARRIER = 42173082507915L;

   public static StudioClickGuiScreen STUDIO;

   public static VestigeClickGuiScreen INSTANCE;

   public static final List<String> DEGRADED = new ArrayList<String>();

   private static boolean attempted;

   private ExpoClickGui() {
   }

   private static final Comparator<Module> DISPLAY_NAME = new Comparator<Module>() {
      public int compare(Module a, Module b) {
         String x = a == null || a.b() == null ? "" : a.b();
         String y = b == null || b.b() == null ? "" : b.b();
         int c = x.compareToIgnoreCase(y);
         return c != 0 ? c : x.compareTo(y);
      }
   };

   private static List<Module> unsortedS;

   private static int sortDepth;

   public static boolean displaySortClash;

   public static int displaySortWindows;

   public static void beginDisplaySort() {
      if (sortDepth++ > 0) {
         return;
      }

      displaySortWindows++;
      List<Module> cur = ModuleManager.S;

      if (cur == null || cur.size() < 2) {
         unsortedS = null;
         return;
      }

      List<Module> copy = new ArrayList<Module>(cur);
      Collections.sort(copy, DISPLAY_NAME);
      unsortedS = cur;
      ModuleManager.S = copy;
   }

   public static void endDisplaySort() {
      if (sortDepth > 0) {
         sortDepth--;
      }

      if (sortDepth > 0) {
         return;
      }

      List<Module> orig = unsortedS;
      unsortedS = null;

      if (orig == null) {
         return;
      }

      if (ModuleManager.S != null && ModuleManager.S != orig && ModuleManager.S.size() != orig.size()) {
         displaySortClash = true;
         return;
      }

      ModuleManager.S = orig;
   }

   public static VestigeClickGuiScreen install(List<String> pending) {
      beginDisplaySort();
      try {
         return install0(pending);
      } finally {
         endDisplaySort();
      }
   }

   private static VestigeClickGuiScreen install0(List<String> pending) {
      if (attempted) {
         return INSTANCE;
      }

      attempted = true;
      DEGRADED.clear();

      long seed;
      try {
         Field fa = VestigeClickGuiScreen.class.getDeclaredField("a");
         fa.setAccessible(true);
         seed = fa.getLong(null);
         if (seed != MEASURED_SEED_A) {
            DEGRADED.add("Expo.ui.vestige.VestigeClickGuiScreen.a moved: measured " + MEASURED_SEED_A
                         + ", runtime " + seed + " -- carrier follows the runtime value");
         }
      } catch (Throwable t) {
         DEGRADED.add("Expo.ui.vestige.VestigeClickGuiScreen.a not readable (" + t.getClass().getName()
                      + "); falling back to the measured seed");
      }

      if (ModuleManager.S == null || ModuleManager.S.isEmpty()) {
         note(pending, "Expo.ui.vestige.VestigeClickGuiScreen built with an EMPTY tD.S -- the "
                       + "category->module map is filled once and never refreshed, "
                       + "so the ClickGUI will show no modules");
      }

      clearCategoryCache();

      VestigeClickGuiScreen screen;
      try {
         screen = new VestigeClickGuiScreen();
      } catch (Throwable t) {
         note(pending, "Expo.ui.vestige.VestigeClickGuiScreen ctor threw " + t.getClass().getName()
                       + ": " + t.getMessage());
         return null;
      }

      if (screen.D != EXPECT_D || screen.M != EXPECT_M
          || screen.O != EXPECT_O || screen.Y != EXPECT_Y) {
         note(pending, "Expo.ui.vestige.VestigeClickGuiScreen REFUSED -- decrypted " + screen.D + "/"
                       + screen.M + "/" + screen.O + "/" + screen.Y + ", expected "
                       + EXPECT_D + "/" + EXPECT_M + "/" + EXPECT_O + "/" + EXPECT_Y);
         return null;
      }

      ClickGUI.B = screen;
      INSTANCE = screen;

      installStudio(pending);

      ExpoRavenGui.installRaven(pending);
      DEGRADED.addAll(ExpoRavenGui.degraded());

      DEGRADED.add("Expo.module.impl.configuration.ClickGUI.U default: config says Mode=STUDIO, so zu_3.O takes the "
                   + "default branch and this screen (VESTIGE) is not the one shown");
      DEGRADED.add("Expo.module.impl.configuration.ClickGUI.I default: config says Scale=1.0; a 0.0 placeholder is a "
                   + "division by zero at ClickGuiScreen.mouseClicked@127");
      DEGRADED.add("Expo.module.impl.configuration.ClickGUI.K default: config says Keybind=RSHIFT");
      note(pending, "Expo.ui.vestige.VestigeClickGuiScreen published into zu_3.B (D/M/O/Y = "
                    + screen.D + "/" + screen.M + "/" + screen.O + "/" + screen.Y
                    + "); zu_3.Y=" + (ClickGUI.Y == null ? "null" : "Ts_2")
                    + " zu_3.F=" + (ClickGUI.F == null ? "null" : "Ad_2")
                    + " -- see ExpoClickGui.DEGRADED");
      return screen;
   }

   private static void installStudio(List<String> pending) {
      try {
         StudioClickGuiScreen studio = new StudioClickGuiScreen(TS2_CARRIER);
         ClickGUI.Y = studio;
         STUDIO = studio;
         note(pending, "Expo.ui.studio.StudioClickGuiScreen (STUDIO theme) published into zu_3.Y");
      } catch (Throwable t) {
         note(pending, "Expo.ui.studio.StudioClickGuiScreen REFUSED -- ctor threw " + t.getClass().getName()
                       + ": " + t.getMessage()
                       + " (a wrong carrier makes java.awt.Color reject a component)");
         DEGRADED.add("Expo.module.impl.configuration.ClickGUI.Y : Expo/ui/studio/StudioClickGuiScreen still null -- the STUDIO theme screen, "
                      + "and STUDIO is what the shipped ClickGUI config selects");
      }
   }

   public static List<String> degraded() {
      return Collections.unmodifiableList(DEGRADED);
   }

   private static void clearCategoryCache() {
      try {
         Field fe = VestigeClickGuiScreen.class.getDeclaredField("E");
         fe.setAccessible(true);
         Object m = fe.get(null);
         if (m instanceof Map) {
            ((Map<?, ?>)m).clear();
         }
      } catch (Throwable t) {
         DEGRADED.add("ClickGuiScreen.E not clearable (" + t.getClass().getName()
                      + "); the category map may be stale");
      }
   }

   private static void note(List<String> pending, String s) {
      if (pending != null) {
         pending.add(s);
      }
   }
}
