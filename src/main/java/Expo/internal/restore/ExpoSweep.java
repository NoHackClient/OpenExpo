package Expo.internal.restore;

import Expo.module.Module;
import Expo.setting.Setting;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public final class ExpoSweep {

   private static final int MODULE_TICKS = intProp("expo.selftest.ticks", 5);

   private static final int SETTING_TICKS = intProp("expo.selftest.setticks", 3);

   private static final int MAX_TICKS = intProp("expo.selftest.maxticks", 40000);

   private static final boolean VERBOSE = "1".equals(System.getProperty("expo.selftest.verbose"));

   private static final Set<String> PREVIOUSLY_NULL_STATIC = new LinkedHashSet<String>();

   static {
      PREVIOUSLY_NULL_STATIC.add("Expo.module.impl.world.Scaffold");
      PREVIOUSLY_NULL_STATIC.add("Expo.module.impl.combat.AutoBlock");
      PREVIOUSLY_NULL_STATIC.add("Expo.module.impl.combat.AimAssist");
      PREVIOUSLY_NULL_STATIC.add("Expo.module.impl.visual_utility.ESP");
      PREVIOUSLY_NULL_STATIC.add("Expo.module.impl.world.SpeedMine");
      PREVIOUSLY_NULL_STATIC.add("Expo.module.impl.visual_utility.ClosestPlayerHUD");
      PREVIOUSLY_NULL_STATIC.add("Expo.module.impl.configuration.ScoreBoard");
      PREVIOUSLY_NULL_STATIC.add("Expo.module.impl.visual.Ambience");
      PREVIOUSLY_NULL_STATIC.add("Expo.module.impl.player.ChestStealer");
      PREVIOUSLY_NULL_STATIC.add("Expo.module.impl.misc.Denick");
   }

   private static final int OP_ENABLE = 1;
   private static final int OP_DISABLE = 2;
   private static final int OP_SET = 3;
   private static final int OP_RESTORE_SETTING = 4;

   public static final String REACH_VANILLA = "VANILLA_REACHABLE";
   public static final String REACH_SWEEP = "SWEEP_INDUCED";
   public static final String REACH_UNDET = "UNDETERMINED";

   private static final class Op {
      int type;
      Module module;
      Setting setting;
      int settingIndex;
      Object value;
      Object snapshot;
      String label;
      int ticks;
   }

   private static final class Row {
      Module module;
      String name = "?";
      String cls = "?";
      boolean toggleable;
      boolean visible;
      boolean fixedKey;
      boolean wasEnabled;
      String reach = REACH_UNDET;
      String enableCol = "NOT_RUN";
      String disableCol = "NOT_RUN";
      int settings;
      int scanned;
      int skipped;
      int inert;
      int opsThrew;
      int knownHits;
      int newHits;
      int collateral;
      final List<String> bad = new ArrayList<String>();
      final List<String> skips = new ArrayList<String>();
      final List<String> notes = new ArrayList<String>();
   }

   private static boolean running;
   private static boolean done;
   private static int ticks;

   private static List<Module> live;
   private static final List<Module> order = new ArrayList<Module>();

   private static final List<Module> expected = new ArrayList<Module>();
   private static final List<Row> rows = new ArrayList<Row>();
   private static final List<String> notes = new ArrayList<String>();

   private static final Map<Module, Boolean> enabledBefore = new IdentityHashMap<Module, Boolean>();
   private static final Map<Setting, Object> valueBefore = new IdentityHashMap<Setting, Object>();
   private static final Map<Setting, String> settingOwner = new IdentityHashMap<Setting, String>();

   private static int moduleCursor = -1;
   private static Row row;
   private static final List<Op> ops = new ArrayList<Op>();
   private static int opCursor;
   private static Op pending;
   private static int waiting;

   private static int opsRun;

   private static ExpoSweepCanary canary;
   private static int canaryHits;
   private static final Set<String> canaryMarks = new LinkedHashSet<String>();
   private static String canaryEnableMarks = "-";
   private static String canaryDisableMarks = "-";

   private ExpoSweep() {
   }

   private static int intProp(String key, int fallback) {
      try {
         String v = System.getProperty(key);

         if (v == null) {
            return fallback;
         }

         return Integer.parseInt(v.trim());
      } catch (Throwable t) {
         return fallback;
      }
   }

   public static boolean running() {
      return running;
   }

   public static boolean done() {
      return done;
   }

   public static void note(String s) {
      notes.add(s);
      say(s);
   }

   public static String current() {
      if (row == null) {
         return "<none>";
      }

      return row.name + (pending == null ? "" : " :: " + pending.label);
   }

   public static void begin(List<Module> modules) {
      if (running || done) {
         return;
      }

      running = true;
      live = modules;

      if (modules == null) {
         note("SWEEP ABORTED -- module list is null");
         finish();
         return;
      }

      for (Module m : modules) {
         if (m == null) {
            continue;
         }

         order.add(m);
         expected.add(m);
         snapshotModule(m);
      }

      canary = null;

      try {
         canary = new ExpoSweepCanary();
         modules.add(canary);
         order.add(0, canary);
         snapshotModule(canary);
      } catch (Throwable t) {
         note("NEGATIVE CONTROL COULD NOT BE BUILT: " + ExpoDiag.describe(t)
              + " -- the table below is uninformative, because nothing proves attribution works");
      }

      say("sweep begin -- " + order.size() + " modules (" + (canary == null ? "no" : "1")
          + " canary), " + valueBefore.size() + " settings snapshotted, "
          + MODULE_TICKS + " tick(s) per module edge, " + SETTING_TICKS + " per setting write");
   }

   private static void snapshotModule(Module m) {
      try {
         enabledBefore.put(m, Boolean.valueOf(m.o()));
      } catch (Throwable t) {
      }

      List<Setting> sl = settingsOf(m);

      if (sl == null) {
         return;
      }

      for (Setting s : sl) {
         if (s == null || valueBefore.containsKey(s)) {
            continue;
         }

         try {
            valueBefore.put(s, ExpoSweepSettings.snapshot(s));
            settingOwner.put(s, nameOf(m));
         } catch (Throwable t) {
         }
      }
   }

   public static boolean step() {
      if (!running) {
         return false;
      }

      if (++ticks > MAX_TICKS) {
         note("SWEEP ABORTED -- tick budget " + MAX_TICKS + " exhausted at module "
              + (moduleCursor + 1) + '/' + order.size());
         finish();
         return false;
      }

      if (pending != null) {
         if (--waiting > 0) {
            return true;
         }

         closeOp();
         return true;
      }

      Op next = nextOp();

      if (next == null) {
         finish();
         return false;
      }

      openOp(next);
      return true;
   }

   private static Op nextOp() {
      while (true) {
         if (opCursor < ops.size()) {
            return ops.get(opCursor++);
         }

         if (row != null) {
            closeModule();
         }

         if (++moduleCursor >= order.size()) {
            return null;
         }

         buildModule(order.get(moduleCursor));
      }
   }

   private static void buildModule(Module m) {
      ops.clear();
      opCursor = 0;
      say("module " + (moduleCursor + 1) + '/' + order.size() + ' ' + nameOf(m));
      row = new Row();
      row.module = m;
      row.name = nameOf(m);
      row.cls = m == null ? "?" : m.getClass().getName();

      try {
         row.toggleable = m.I();
         row.visible = m.D();
         row.fixedKey = m.S();
         row.wasEnabled = m.o();
      } catch (Throwable t) {
         row.notes.add("flags unreadable: " + ExpoDiag.describe(t));
      }

      row.reach = classify(row);
      List<Setting> sl = settingsOf(m);
      row.settings = sl == null ? -1 : sl.size();

      if (!row.toggleable) {
         row.enableCol = "INERT";
         row.disableCol = "INERT";
         row.notes.add("Module.I(JZ) is a no-op here: the toggleable flag z is false, so "
                       + "neither edge can be produced at all");
      }

      List<Op> settingOps = new ArrayList<Op>();

      if (sl != null) {
         for (int i = 0; i < sl.size(); i++) {
            Setting s = sl.get(i);
            int k = ExpoSweepSettings.kind(s);

            if (!ExpoSweepSettings.scannable(k)) {
               row.skipped++;
               row.skips.add(ExpoSweepSettings.kindName(k) + " \"" + ExpoSweepSettings.label(s)
                             + "\" (" + (s == null ? "null" : s.getClass().getName()) + ')');
               continue;
            }

            Object snap;
            List<Object> pts;

            try {
               snap = ExpoSweepSettings.snapshot(s);
               pts = ExpoSweepSettings.points(s);
            } catch (Throwable t) {
               row.skipped++;
               row.skips.add("UNREADABLE \"" + ExpoSweepSettings.label(s) + "\" "
                             + ExpoDiag.describe(t));
               continue;
            }

            row.scanned++;

            if (pts.isEmpty()) {
               row.inert++;
               continue;
            }

            for (Object v : pts) {
               settingOps.add(op(OP_SET, m, s, i, v, snap, SETTING_TICKS));
            }

            settingOps.add(op(OP_RESTORE_SETTING, m, s, i, snap, snap, SETTING_TICKS));
         }
      }

      if (row.toggleable) {
         if (row.wasEnabled) {
            ops.addAll(settingOps);
            ops.add(op(OP_DISABLE, m, null, -1, null, null, MODULE_TICKS));
            ops.add(op(OP_ENABLE, m, null, -1, null, null, MODULE_TICKS));
         } else {
            ops.add(op(OP_ENABLE, m, null, -1, null, null, MODULE_TICKS));
            ops.addAll(settingOps);
            ops.add(op(OP_DISABLE, m, null, -1, null, null, MODULE_TICKS));
         }
      } else {
         ops.addAll(settingOps);
      }
   }

   private static Op op(int type, Module m, Setting s, int idx, Object v, Object snap, int t) {
      Op o = new Op();
      o.type = type;
      o.module = m;
      o.setting = s;
      o.settingIndex = idx;
      o.value = v;
      o.snapshot = snap;
      o.ticks = t;
      o.label = labelOf(o);
      return o;
   }

   private static String labelOf(Op o) {
      String n = nameOf(o.module);

      switch (o.type) {
         case OP_ENABLE:
            return n + " ENABLE";
         case OP_DISABLE:
            return n + " DISABLE";
         case OP_SET:
            return n + " SET #" + o.settingIndex + ' '
                   + ExpoSweepSettings.kindName(ExpoSweepSettings.kind(o.setting))
                   + " \"" + ExpoSweepSettings.label(o.setting) + "\" := "
                   + ExpoSweepSettings.render(o.value);
         default:
            return n + " RESTORE #" + o.settingIndex + " \""
                   + ExpoSweepSettings.label(o.setting) + "\" := "
                   + ExpoSweepSettings.render(o.snapshot);
      }
   }

   private static void openOp(Op o) {
      opsRun++;
      pending = o;
      waiting = o.ticks;
      ExpoDiag.beginAttribution(o.label);

      if (VERBOSE) {
         say("op " + o.label);
      }

      try {
         switch (o.type) {
            case OP_ENABLE:
               o.module.I(ExpoConfig.MODULE_I_CARRIER, true);
               break;
            case OP_DISABLE:
               o.module.I(ExpoConfig.MODULE_I_CARRIER, false);
               break;
            case OP_SET:
               ExpoSweepSettings.apply(o.setting, o.value);
               break;
            default:
               if (!ExpoSweepSettings.restore(o.setting, o.snapshot)) {
                  row.notes.add("restore of \"" + ExpoSweepSettings.label(o.setting)
                                + "\" did not land: wanted " + ExpoSweepSettings.render(o.snapshot)
                                + ", got " + ExpoSweepSettings.live(o.setting));
               }
         }
      } catch (Throwable t) {
         ExpoDiag.attribute(t, "driver");
      }
   }

   private static void closeOp() {
      Op o = pending;
      pending = null;
      int n = ExpoDiag.windowCount();
      List<String> frames = ExpoDiag.windowFirstFrames();
      ExpoDiag.beginAttribution(BETWEEN_OPS);

      if (o.module == canary) {
         canaryHits += n;

         for (String f : frames) {
            if (f.contains(ExpoSweepCanary.MARK)) {
               canaryMarks.add(mark(f));
            }
         }
      }

      if (n > 0) {
         record(o, n, frames);
      }

      if (o.type == OP_ENABLE || o.type == OP_DISABLE) {
         String col = n > 0 ? "THREW:" + n : "OK";

         if (o.type == OP_ENABLE) {
            row.enableCol = col;

            if (o.module == canary) {
               canaryEnableMarks = marksIn(frames);
            }
         } else {
            row.disableCol = col;

            if (o.module == canary) {
               canaryDisableMarks = marksIn(frames);
            }
         }

         if (n > 0) {
            row.notes.add("this edge threw inside AZ.r's tD.S loop, which has no per-module "
                          + "try/catch: the loop aborted, so every module after this one in "
                          + "tD.S went unpumped for that tick.  The sweep holds one change "
                          + "live at a time and clears the latch below, so the blast radius "
                          + "is bounded -- outside the sweep it is not");
         }

         clearStuckLatch(o, n);
      }
   }

   private static void clearStuckLatch(Op o, int n) {
      try {
         boolean rising = o.module.l();
         boolean falling = o.module.K();

         if (!rising && !falling) {
            return;
         }

         if (n > 0) {
            if (rising) {
               o.module.n(false);
               row.notes.add("rising-edge latch was still set after a throwing onEnable; "
                             + "AZ.r would have retried it every tick -- cleared by the harness");
            }

            if (falling) {
               o.module.E(false);
               row.notes.add("falling-edge latch was still set after a throwing onDisable; "
                             + "cleared by the harness");
            }
         } else {
            row.notes.add("edge latch still set after " + o.ticks
                          + " tick(s) with nothing thrown: the pump (AZ.r on PostTickEvent) "
                          + "did not consume it -- suspect the pump is not running");
         }
      } catch (Throwable t) {
         row.notes.add("latch check threw: " + ExpoDiag.describe(t));
      }
   }

   private static void record(Op o, int n, List<String> frames) {
      row.opsThrew++;
      String first = frames.isEmpty() ? "<counted but no description kept>" : frames.get(0);
      boolean known = isKnown(frames);

      if (known) {
         row.knownHits++;
      } else {
         row.newHits++;
      }

      String where = locus(first, row.cls);

      if (!"SELF".equals(where)) {
         row.collateral++;
      }

      row.bad.add((known ? "WAS-NULL-STATIC" : "NEW") + " | " + where + " | " + o.label
                  + " | n=" + n + " | " + first);
   }

   private static String locus(String frame, String cls) {
      String product = between(frame, "[expo=", "]");
      String top = between(frame, " @ ", " [expo=");

      if (cls != null && (owner(product).equals(cls) || owner(top).equals(cls))) {
         return "SELF";
      }

      String pick = owner(product).length() > 0 ? owner(product) : owner(top);
      return pick.length() > 0 ? "ELSEWHERE:" + pick : "NO-FRAME";
   }

   private static String owner(String element) {
      if (element == null || element.length() == 0 || element.startsWith("<")) {
         return "";
      }

      int paren = element.indexOf('(');
      String qualified = paren < 0 ? element : element.substring(0, paren);
      int dot = qualified.lastIndexOf('.');
      return dot < 0 ? "" : qualified.substring(0, dot);
   }

   private static String between(String s, String open, String close) {
      int i = s.indexOf(open);

      if (i < 0) {
         return "";
      }

      i += open.length();
      int j = close.equals("]") ? s.lastIndexOf(close) : s.indexOf(close, i);
      return j <= i ? s.substring(i) : s.substring(i, j);
   }

   private static boolean isKnown(List<String> frames) {
      for (String f : frames) {
         for (String c : PREVIOUSLY_NULL_STATIC) {
            if (f.contains(c + '.')) {
               return true;
            }
         }
      }

      return false;
   }

   private static String mark(String frame) {
      int i = frame.indexOf(ExpoSweepCanary.MARK + ':');

      if (i < 0) {
         return ExpoSweepCanary.MARK;
      }

      int j = i + ExpoSweepCanary.MARK.length() + 1;
      int k = frame.indexOf(' ', j);
      return k < 0 ? frame.substring(j) : frame.substring(j, k);
   }

   private static String marksIn(List<String> frames) {
      Set<String> s = new LinkedHashSet<String>();

      for (String f : frames) {
         if (f.contains(ExpoSweepCanary.MARK)) {
            s.add(mark(f));
         }
      }

      return s.isEmpty() ? "-" : s.toString();
   }

   private static void closeModule() {
      rows.add(row);

      if (row.module == canary) {
         removeCanary();
      }

      row = null;
   }

   private static void removeCanary() {
      if (canaryRemoved || canary == null) {
         return;
      }

      canaryRemoved = true;

      try {
         canary.I(ExpoConfig.MODULE_I_CARRIER, false);
         canary.n(false);
         canary.E(false);

         if (live != null) {
            live.remove(canary);
         }

         enabledBefore.remove(canary);
         say("canary taken back out of the live module list; " + order.size()
             + " entries still in the sweep order (unchanged on purpose)");
      } catch (Throwable t) {
         note("canary removal threw: " + ExpoDiag.describe(t));
      }
   }

   private static boolean canaryRemoved;

   private static String classify(Row r) {
      if (!r.toggleable) {
         return REACH_SWEEP;
      }

      if (r.visible && !r.fixedKey) {
         return REACH_VANILLA;
      }

      return REACH_UNDET;
   }

   private static void finish() {
      if (done) {
         return;
      }

      done = true;
      running = false;

      if (row != null) {
         rows.add(row);
         row = null;
      }

      removeCanary();
      restoreAll();
      report();
   }

   private static void restoreAll() {
      ExpoDiag.beginAttribution("<final restore>");
      int modOk = 0;
      int modBad = 0;
      int setOk = 0;
      int setBad = 0;
      List<String> bad = new ArrayList<String>();

      for (Map.Entry<Module, Boolean> e : enabledBefore.entrySet()) {
         Module m = e.getKey();
         boolean want = e.getValue().booleanValue();

         try {
            if (m.o() != want) {
               m.I(ExpoConfig.MODULE_I_CARRIER, want);
            }

            if (m.o() == want) {
               modOk++;
            } else {
               modBad++;
               bad.add("MODULE " + nameOf(m) + " wanted enabled=" + want + " got " + m.o());
            }
         } catch (Throwable t) {
            modBad++;
            bad.add("MODULE " + nameOf(m) + " restore threw " + ExpoDiag.describe(t));
         }
      }

      for (Map.Entry<Setting, Object> e : valueBefore.entrySet()) {
         Setting s = e.getKey();

         try {
            if (!ExpoSweepSettings.matches(s, e.getValue())) {
               ExpoSweepSettings.restore(s, e.getValue());
            }

            if (ExpoSweepSettings.matches(s, e.getValue())) {
               setOk++;
            } else {
               setBad++;
               bad.add("SETTING " + settingOwner.get(s) + " \"" + ExpoSweepSettings.label(s)
                       + "\" wanted " + ExpoSweepSettings.render(e.getValue())
                       + " got " + ExpoSweepSettings.live(s));
            }
         } catch (Throwable t) {
            setBad++;
            bad.add("SETTING " + settingOwner.get(s) + " \"" + ExpoSweepSettings.label(s)
                    + "\" restore threw " + ExpoDiag.describe(t));
         }
      }

      restoreLine = "restore audit: modules " + modOk + " ok / " + modBad + " MISMATCH, settings "
                    + setOk + " ok / " + setBad + " MISMATCH";
      restoreBad = bad;
      ExpoDiag.endAttribution();
   }

   private static String restoreLine = "restore audit: not run";

   private static List<String> restoreBad = new ArrayList<String>();

   private static void report() {
      StringBuilder b = new StringBuilder();
      b.append('\n').append(TAG).append(" ==== module and setting sweep ====\n");
      b.append(TAG).append(" PREMISE: a human may have been using this client while the"
                           + " sweep ran.  ROW lines are states the harness produced;"
                           + " EVENT lines say whether the harness caused the observation"
                           + " or only found it already true.\n");

      boolean control = canaryHits > 0 && !canaryMarks.isEmpty();
      b.append(TAG).append(" NEGATIVE CONTROL ")
       .append(control ? "PASS" : "FAIL")
       .append(" -- canary attributed ").append(canaryHits)
       .append(" throw(s), traces naming it: ").append(canaryMarks.isEmpty() ? "NONE" : canaryMarks.toString())
       .append('\n');

      if (!control) {
         b.append(TAG).append(" the attribution chain did not carry a signal it was given on")
          .append(" purpose, so every 'OK' below means 'not measured', not 'works'\n");
      }

      b.append(TAG).append(" canary edge markers: enable=").append(canaryEnableMarks)
       .append(" disable=").append(canaryDisableMarks)
       .append("   (which of Module.i/A/P is which edge, measured rather than assumed)\n");

      b.append(TAG).append(" columns: name | class | enable | disable | settings"
                           + " | scanned | skipped | inert | ops-threw | reach | flags\n");

      int modules = 0;
      int enableThrew = 0;
      int disableThrew = 0;
      int opsThrew = 0;
      int knownHits = 0;
      int newHits = 0;
      int collateral = 0;
      int vanilla = 0;
      int sweepOnly = 0;
      int undet = 0;
      int settingsTotal = 0;
      int scannedTotal = 0;
      int skippedTotal = 0;

      for (Row r : rows) {
         if (r.module == canary) {
            b.append(TAG).append(" CANARY | ").append(rowLine(r)).append('\n');
            continue;
         }

         modules++;
         settingsTotal += Math.max(r.settings, 0);
         scannedTotal += r.scanned;
         skippedTotal += r.skipped;
         opsThrew += r.opsThrew;
         knownHits += r.knownHits;
         newHits += r.newHits;
         collateral += r.collateral;

         if (r.enableCol.startsWith("THREW")) {
            enableThrew++;
         }

         if (r.disableCol.startsWith("THREW")) {
            disableThrew++;
         }

         if (REACH_VANILLA.equals(r.reach)) {
            vanilla++;
         } else if (REACH_SWEEP.equals(r.reach)) {
            sweepOnly++;
         } else {
            undet++;
         }

         b.append(TAG).append(" ROW | ").append(rowLine(r)).append('\n');
      }

      for (Row r : rows) {
         for (String s : r.bad) {
            b.append(TAG).append(" BAD | ").append(r.name).append(" | ").append(s).append('\n');
         }

         for (String s : r.notes) {
            b.append(TAG).append(" NOTE | ").append(r.name).append(" | ").append(s).append('\n');
         }

         for (String s : r.skips) {
            if (VERBOSE || s.startsWith("UNKNOWN") || s.startsWith("UNREADABLE")) {
               b.append(TAG).append(" SKIP | ").append(r.name).append(" | ").append(s).append('\n');
            }
         }
      }

      for (String s : notes) {
         b.append(TAG).append(" EVENT | ").append(s).append('\n');
      }

      Map<Module, Boolean> seen = new IdentityHashMap<Module, Boolean>();

      for (Row r : rows) {
         seen.put(r.module, Boolean.TRUE);
      }

      int covered = 0;
      List<String> missing = new ArrayList<String>();

      for (Module m : expected) {
         if (seen.containsKey(m)) {
            covered++;
         } else {
            missing.add(nameOf(m));
         }
      }

      b.append(TAG).append(" ---- summary ----\n");
      b.append(TAG).append(" coverage: ").append(covered).append(" of ").append(expected.size())
       .append(" modules produced a row")
       .append(missing.isEmpty() ? "" : "  MISSING " + missing).append('\n');
      b.append(TAG).append(" cost: ").append(opsRun).append(" operations over ")
       .append(ticks).append(" ticks").append('\n');
      b.append(TAG).append(" swept ").append(modules).append(" modules, ")
       .append(enableThrew).append(" threw while enabling, ")
       .append(disableThrew).append(" threw while disabling, ")
       .append(opsThrew).append(" operations threw in total\n");
      b.append(TAG).append(" settings: ").append(settingsTotal).append(" declared, ")
       .append(scannedTotal).append(" moved, ").append(skippedTotal)
       .append(" left alone (colour / key bind / text / unknown type)\n");
      b.append(TAG).append(" throws by novelty: ").append(newHits)
       .append(" are new information; ").append(knownHits)
       .append(" land in the ten classes whose null Setting statics ExpoSettingStatics has "
               + "since filled ")
       .append(PREVIOUSLY_NULL_STATIC.toString())
       .append(" -- those are REGRESSIONS against that repair, not old news\n");
      b.append(TAG).append(" throws by locus: ").append(collateral)
       .append(" of ").append(opsThrew)
       .append(" did not name the module under test in their top product frame"
               + " (see the ELSEWHERE tag on each BAD line; a shared helper reads the same"
               + " way as collateral damage, so this is a hint, not a verdict)\n");
      b.append(TAG).append(" reachability of the state each row was put into: ")
       .append(vanilla).append(' ').append(REACH_VANILLA).append(", ")
       .append(sweepOnly).append(' ').append(REACH_SWEEP).append(", ")
       .append(undet).append(' ').append(REACH_UNDET).append('\n');
      b.append(TAG).append(' ').append(restoreLine).append('\n');

      for (String s : restoreBad) {
         b.append(TAG).append(" RESTORE | ").append(s).append('\n');
      }

      b.append(TAG).append(" attribution ledger (label -> count, undeduplicated):\n");

      for (Map.Entry<String, int[]> e : ExpoDiag.attributionTotals().entrySet()) {
         b.append(TAG).append(String.format(" %7d", e.getValue()[0])).append("  ")
          .append(e.getKey()).append('\n');
      }

      b.append(TAG).append(" ==== end of sweep ====");
   }

   private static String rowLine(Row r) {
      return r.name + " | " + r.cls + " | " + r.enableCol + " | " + r.disableCol
             + " | " + r.settings + " | " + r.scanned + " | " + r.skipped + " | " + r.inert
             + " | " + r.opsThrew + " | " + r.reach
             + " | toggleable=" + r.toggleable + ",visible=" + r.visible
             + ",fixedKey=" + r.fixedKey + ",wasEnabled=" + r.wasEnabled;
   }

   private static List<Setting> settingsOf(Module m) {
      try {
         return m.w();
      } catch (Throwable t) {
         return null;
      }
   }

   private static String nameOf(Module m) {
      if (m == null) {
         return "<null>";
      }

      try {
         String n = m.b();
         return n == null ? "<unnamed:" + m.getClass().getName() + '>' : n;
      } catch (Throwable t) {
         return "<name threw:" + m.getClass().getName() + '>';
      }
   }

   private static final String TAG = "[EXPOSWEEP]";

   private static final String BETWEEN_OPS = "<between ops>";

   private static void say(String s) {
   }
}
