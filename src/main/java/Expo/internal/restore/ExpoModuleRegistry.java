package Expo.internal.restore;

import Expo.module.Module;
import Expo.module.ModuleManager;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public final class ExpoModuleRegistry {
   public static final String PLACEHOLDER_PREFIX = "?";

   public static Module PLAIN_LISTENER;

   public static final boolean PUBLISH_SOLVED_CARRIERS = true;

   public static final boolean PUBLISH_ANCHORED_CARRIERS = true;

   public static final List<String> PUBLISHED = new ArrayList<String>();

   public static final List<String> PENDING = new ArrayList<String>();

   private static final Set<Class<? extends Module>> PERSISTABLE =
      new HashSet<Class<? extends Module>>();

   private static final Set<String> PERSISTABLE_NAMES = new LinkedHashSet<String>();

   public static final int ORIGINAL_MODULE_COUNT = 112;

   private static boolean published;

   private ExpoModuleRegistry() {
   }

   public static void publish() {
      if (published) {
         return;
      }

      published = true;

      if (ModuleManager.S == null) {
         ModuleManager.S = new ArrayList<Module>();
      }

      if (ModuleManager.o == null) {
         ModuleManager.o = new HashMap<Class<? extends Module>, Module>();
      }

      reg(new Expo.module.impl.visual_utility.FKCounter(0L), Expo.module.impl.visual_utility.FKCounter.class, "FKCounter", true);
      reg(new Expo.module.impl.visual.HUD(0L), Expo.module.impl.visual.HUD.class, "HUD", true);
      reg(ModuleManager.r, Expo.module.impl.combat.HitBox.class, "HitBox", true);
      reg(new Expo.module.impl.macro.Macro1(0L), Expo.module.impl.macro.Macro1.class, "Macro1", true);
      reg(new Expo.module.impl.macro.Macro2(0, 0, (short)0), Expo.module.impl.macro.Macro2.class, "Macro2", true);
      reg(new Expo.module.impl.combat.AimAssist(0L), Expo.module.impl.combat.AimAssist.class, "AimAssist", true);
      reg(new Expo.module.impl.combat.AutoProjectiles(0L, (short)0), Expo.module.impl.combat.AutoProjectiles.class, "AutoProjectiles", true);
      reg(new Expo.module.impl.macro.Macro3(0L), Expo.module.impl.macro.Macro3.class, "Macro3", true);
      reg(new Expo.module.impl.macro.Macro4(0L), Expo.module.impl.macro.Macro4.class, "Macro4", true);
      reg(new Expo.module.impl.macro.Macro5(0L), Expo.module.impl.macro.Macro5.class, "Macro5", true);
      reg(new Expo.module.impl.visual_utility.ItemESP((short)0, (char)0, 0), Expo.module.impl.visual_utility.ItemESP.class, "ItemESP", true);
      reg(new Expo.module.impl.visual_utility.Indicators(0L), Expo.module.impl.visual_utility.Indicators.class, "Indicators", true);
      reg(new Expo.module.impl.visual_utility.ChestESP(0L), Expo.module.impl.visual_utility.ChestESP.class, "ChestESP", true);
      reg(new Expo.module.impl.visual_utility.NameTags(0L), Expo.module.impl.visual_utility.NameTags.class, "NameTags", true);
      reg(new Expo.module.impl.visual_utility.ESP(0L), Expo.module.impl.visual_utility.ESP.class, "ESP", true);
      reg(new Expo.module.impl.world.SpeedMine((short)0, 0L), Expo.module.impl.world.SpeedMine.class, "SpeedMine", true);
      reg(ModuleManager.h, Expo.module.impl.visual.ViewClip.class, "ViewClip", true);
      reg(new Expo.module.impl.visual_utility.BedESP(0L), Expo.module.impl.visual_utility.BedESP.class, "BedESP", true);
      reg(ModuleManager.y = new Expo.module.impl.visual.TeamInvisible((char)0, 0L), Expo.module.impl.visual.TeamInvisible.class, "TeamInvisible", true);
      reg(new Expo.module.impl.visual_utility.ItemTags(0L), Expo.module.impl.visual_utility.ItemTags.class, "ItemTags", true);
      reg(new Expo.module.impl.visual_utility.TargetHUD(0L), Expo.module.impl.visual_utility.TargetHUD.class, "TargetHUD", true);
      reg(new Expo.module.impl.visual_utility.Tracers(0L), Expo.module.impl.visual_utility.Tracers.class, "Tracers", true);
      reg(new Expo.module.impl.world.AutoTunnel(0L), Expo.module.impl.world.AutoTunnel.class, "AutoTunnel", true);
      reg(new Expo.module.impl.visual_utility.BlocksESP(0L), Expo.module.impl.visual_utility.BlocksESP.class, "BlocksESP", true);
      reg(new Expo.module.impl.visual_utility.MegaWallsDetector(0L), Expo.module.impl.visual_utility.MegaWallsDetector.class, "MegaWallsDetector", true);
      reg(new Expo.module.impl.visual_utility.FireBallPredict(0L), Expo.module.impl.visual_utility.FireBallPredict.class, "FireBallPredict", true);
      reg(new Expo.module.impl.visual_utility.Trajectories(0L), Expo.module.impl.visual_utility.Trajectories.class, "Trajectories", true);
      reg(new Expo.module.impl.world.FastPlace(0L), Expo.module.impl.world.FastPlace.class, "FastPlace", true);
      reg(new Expo.module.impl.visual_utility.ClosestPlayerHUD(0L), Expo.module.impl.visual_utility.ClosestPlayerHUD.class, "ClosestPlayerHUD", true);
      reg(new Expo.module.impl.configuration.ScoreBoard(0L), Expo.module.impl.configuration.ScoreBoard.class, "ScoreBoard", true);
      reg(new Expo.module.impl.configuration.Teams(0L, (short)0), Expo.module.impl.configuration.Teams.class, "Teams", true);
      reg(new Expo.module.impl.visual.Ambience(0L), Expo.module.impl.visual.Ambience.class, "Ambience", true);
      reg(ModuleManager.g, Expo.module.impl.visual.NoHurtCam.class, "NoHurtCam", true);
      reg(new Expo.module.impl.movement.FastFall(0L), Expo.module.impl.movement.FastFall.class, "FastFall", true);
      reg(ModuleManager.k = new Expo.module.impl.misc.NoObfuscation(0L), Expo.module.impl.misc.NoObfuscation.class, "NoObfuscation", true);
      reg(new Expo.module.impl.movement.Speed(0L), Expo.module.impl.movement.Speed.class, "Speed", true);
      reg(new Expo.module.impl.movement.NoJumpDelay(0L), Expo.module.impl.movement.NoJumpDelay.class, "NoJumpDelay", true);
      reg(new Expo.module.impl.configuration.Notifications(0L), Expo.module.impl.configuration.Notifications.class, "Notifications", true);
      reg(new Expo.module.impl.player.AutoWeapon(0L), Expo.module.impl.player.AutoWeapon.class, "AutoWeapon", true);
      PLAIN_LISTENER = new Expo.internal.CheaterDetector(0, (char)0, 0);
      ModuleManager.o.put(Expo.internal.CheaterDetector.class, PLAIN_LISTENER);
      reg(new Expo.module.impl.configuration.CustomCape((char)0, 0L), Expo.module.impl.configuration.CustomCape.class, "CustomCape", true);
      reg(new Expo.module.impl.configuration.Font(0L), Expo.module.impl.configuration.Font.class, "Font", true);
      reg(new Expo.module.impl.movement.NoSlow(0L), Expo.module.impl.movement.NoSlow.class, "NoSlow", true);
      reg(new Expo.module.impl.visual.KillEffect(0L), Expo.module.impl.visual.KillEffect.class, "KillEffect", true);
      reg(new Expo.module.impl.misc.RawInput(0L), Expo.module.impl.misc.RawInput.class, "RawInput", true);
      reg(ModuleManager.d, Expo.module.impl.visual.Animations.class, "Animations", true);
      reg(new Expo.module.impl.visual.BreakProgress(0L), Expo.module.impl.visual.BreakProgress.class, "BreakProgress", true);
      reg(new Expo.module.impl.visual.Freelook(0L), Expo.module.impl.visual.Freelook.class, "Freelook", true);
      reg(new Expo.module.impl.configuration.Theme(0, (char)0, 0), Expo.module.impl.configuration.Theme.class, "Theme", true);
      reg(ModuleManager.f = new Expo.module.impl.misc.AntiNick(0L), Expo.module.impl.misc.AntiNick.class, "AntiNick", true);
      reg(new Expo.module.impl.misc.InputFix((short)0, 0, 0), Expo.module.impl.misc.InputFix.class, "InputFix", true);
      reg(new Expo.module.impl.visual.KeyStrokes((byte)0, 0, 0), Expo.module.impl.visual.KeyStrokes.class, "KeyStrokes", true);
      reg(ModuleManager.q, Expo.module.impl.player.ChestStealer.class, "ChestStealer", true);
      reg(new Expo.module.impl.movement.Sprint((short)0, 0, (short)0), Expo.module.impl.movement.Sprint.class, "Sprint", true);
      reg(new Expo.module.impl.misc.Denick(0L), Expo.module.impl.misc.Denick.class, "Denick", true);
      reg(new Expo.module.impl.movement.Fly(0L), Expo.module.impl.movement.Fly.class, "Fly", true);
      reg(new Expo.module.impl.visual.FullBright(0L), Expo.module.impl.visual.FullBright.class, "FullBright", true);
      reg(ModuleManager.c = new Expo.module.impl.misc.AntiBot(0, 0, (short)0), Expo.module.impl.misc.AntiBot.class, "AntiBot", true);
      reg(new Expo.module.impl.player.FreeCam(0L), Expo.module.impl.player.FreeCam.class, "FreeCam", true);
      reg(new Expo.module.impl.configuration.Language((char)0, 0, 0), Expo.module.impl.configuration.Language.class, "Language", true);
      reg(new Expo.module.impl.misc.CommandLine(0L), Expo.module.impl.misc.CommandLine.class, "CommandLine", true);
      reg(new Expo.module.impl.configuration.VisualSpoof((short)0, (short)0, 0), Expo.module.impl.configuration.VisualSpoof.class, "VisualSpoof", true);
      reg(new Expo.module.impl.player.NoInteract(0L), Expo.module.impl.player.NoInteract.class, "NoInteract", true);
      reg(ModuleManager.J = new Expo.module.impl.misc.NameHider(0L), Expo.module.impl.misc.NameHider.class, "NameHider", true);
      reg(new Expo.module.impl.visual.BindGUI(0L), Expo.module.impl.visual.BindGUI.class, "BindGUI", true);
      reg(new Expo.module.impl.configuration.Gadgets(0L), Expo.module.impl.configuration.Gadgets.class, "Gadgets", true);
      reg(new Expo.module.impl.configuration.ClickGUI(0L), Expo.module.impl.configuration.ClickGUI.class, "ClickGUI", true);
      reg(ModuleManager.v, Expo.module.impl.visual.ItemScale.class, "ItemScale", true);
      reg(ModuleManager.W, Expo.module.impl.visual.BarrierVisible.class, "BarrierVisible", true);
      reg(ModuleManager.Q, Expo.module.impl.player.GhostHand.class, "GhostHand", true);
      reg(ModuleManager.O, Expo.module.impl.visual.AntiDebuff.class, "AntiDebuff", true);
      reg(ModuleManager.m, Expo.module.impl.visual.CaveXray.class, "CaveXray", true);

      publishSolvedCarriers();
      publishAnchoredCarriers();
      publishCachePrepopulated();

      PENDING.add("ExpoModuleRegistry  DENOMINATOR: the original registers 112 modules"
                  + " (qux capture modules.count=112, name set identical to the 112 config"
                  + " blocks).  Expo/internal/CheaterDetector (twin Expo/xq) and Expo/module/unregistered/q8 (twin Expo/KI) are leaf"
                  + " Module classes that the original does NOT register, so they have no name"
                  + " and no config block; \"AntiCheat\" is absent from the shipped config too."
                  + "  This build registers zG_3, i.e. one module more than the original.");
      PENDING.add("ExpoModuleRegistry  THREE COUNTS, THREE MEANINGS -- do not gate on the wrong one."
                  + "  tD.S = 112 is the registration truth (= ORIGINAL_MODULE_COUNT, what the"
                  + " original publishes and what ExpoConfig.save walks).  tD.o = 113 is tD.S plus"
                  + " Expo/internal/CheaterDetector, which is put in the by-class map on purpose and"
                  + " held out of the list.  115 is the class census: every concrete leaf subclass of"
                  + " Expo/module/Module in the artifact, i.e. the 112 plus CheaterDetector plus"
                  + " Expo/module/unregistered/q8 (never instantiated) plus"
                  + " Expo/internal/restore/ExpoSweepCanary (added by this project).  The old"
                  + " LEAF_MODULE_CLASSES = 114 was that census taken before ExpoSweepCanary existed,"
                  + " and isConfigWritable() compared tD.S against it, so the predicate could never be"
                  + " true; it now compares against ORIGINAL_MODULE_COUNT.  Nothing called it, so no"
                  + " save was ever actually refused.");
      PENDING.add("Expo.module.impl.world.AutoDigPlace / Expo.module.impl.world.AutoTool / Expo.module.impl.misc.Timer / Expo.module.impl.visual.Chams / Expo.module.impl.movement.InvMove  no longer HELD:"
                  + " all five moved to ExpoCtorCache.plans() with values derived from"
                  + " well-formedness of their own code, not from the twin-instance matcher"
                  + " (which is NOMATCH for iK/ij_2/zU_2/zV_3 and a cheap 1-boolean match for"
                  + " zF_3).  Each plan comment names the two independent sites it rests on."
                  + "  Slot feasibility pre-flighted by work/last10-agent/tool/slotfeas.py:"
                  + " 4 of 4 must-fail inputs rejected, and it is VACUOUS for iK and zF_3.");
      PENDING.add("Expo.module.impl.world.BedNuker  ctor (SJ)V  carrier SOLVED (short)0,33171000103266 -- PUBLISHED."
                  + "  The upstream NullPointerException is neutralised by one DECLARED DEVIATION"
                  + " in Expo/ik_2.W: var12 now starts with !var10 (this.Do != null).  That edit"
                  + " is PROVABLY behaviour-preserving, not merely minimal: in the original"
                  + " bytecode var12 is stored at pc243 and loaded exactly once, at pc255, on the"
                  + " far side of pc245 'iload 10; ifne 317', so whenever Do == null the value of"
                  + " var12 is never consumed -- only the exception thrown while computing it is."
                  + "  Everything the skipped call would have run before throwing is pure:"
                  + " aG_3.Y -> aG_3.p -> aG_3.n(.., Af_2.D(Do)) evaluates aG_3.f()"
                  + " (mc.thePlayer.getPositionEyes) and then Af_2.c derefs the null BlockPos at"
                  + " its first offset.  Upstream verified verbatim in expo-plain.jar:"
                  + " Expo/ik.W(J,Expo/bo) pc196-243 computes var12 unconditionally and pc29-45"
                  + " computes var10 = (Do == null) first, and Expo/Af.c(BlockPos,Vec3) pc42-48"
                  + " has no guard.  The event only reaches ik_2 while BedNuker is subscribed,"
                  + " i.e. enabled, so the throw was only ever reachable from the sweep;"
                  + " EventBus.e caught it per-binding, so no other listener was affected.");
      PENDING.add("Expo.module.unregistered.q8  ctor (J)V  carrier LIVE: 29 invokestatic Expo/module/unregistered/q8.b(IJ)I"
                  + "  -- NOT A MODULE, so nothing to solve: the CONTRADICTION (twin Expo/KI's"
                  + " cache g[2] has 0 live entries) has a mechanism.  Expo/KI is absent from the"
                  + " 112 modules the qux capture enumerates, so the twin never constructs it and"
                  + " its ctor decryptor never ran.  q8's own two fields (y:Z idx 4351, N:Z idx"
                  + " 29439) are also absent from the capture for the same reason.  This class is"
                  + " outside the 112 and must not be published.");
      PENDING.add("Expo.module.impl.player.Blink  ctor (J)V  no longer HELD: the carrier is SOLVED, c = 8295, the"
                  + " only one of 32768 that satisfies 8 slot constraints across the class's"
                  + " three tables, and the 2.4.6 twin Expo/xe yields its own unique c = 32377"
                  + " under 10 constraints.  The conflict that held it is RESOLVED rather than"
                  + " broken: the twin's J=1 k=0 reading is post-construction state -- a Blink"
                  + " that was enabled and blinking in NORMAL mode -- corroborated by which twin"
                  + " slots are null, so it never contradicted the ctor being [J=0,k=1].  The"
                  + " step the old argument needed is still NOT established and is not used;"
                  + " what replaced it is rank-1 separability, lit(method,idx) = X(method) XOR"
                  + " D(idx), which holds in z3_2 (3 of 3 pairs) and fails in 2 of 25 groups"
                  + " tree-wide, so it is not vacuous.  Sites e(12421) and b(8718) remain"
                  + " unknown; both are PULSE-only and Mode ships NORMAL, so the shipped default"
                  + " is unaffected.  Evidence: work/seedharvest2-agent/out/Z3_2.md.");
      PENDING.add("Expo.module.impl.player.InvClicker  ctor (J)V  carrier LIVE -- no longer HELD: p:I = 0 is DERIVED and"
                  + " the module is in ExpoCtorCache.plans().  The candidate pair {0,42} is the"
                  + " two LIVE VALUES of the twin's 4-slot cache, and the generator only knew the"
                  + " ctor site, so it could not say which of the class's FOUR numeric sites owns"
                  + " which value.  Naming the owner of the live 0 by elimination settles it:"
                  + " site 10460 (the `p = p - <it>` decrement at zK_3.java:51) must be >= 1, or"
                  + " p never returns to <=0 and the only click path that reads p fires at most"
                  + " once per client; site 454 (a key code fed to Zv_2.V at :55) cannot be 0,"
                  + " because Zv_2.V(int,long) opens with `if (var5 == 0) return false` at"
                  + " Zv_2.java:124 and the whole trigger chain would be dead.  That leaves the"
                  + " ctor or A(long)=onDisable, and if onDisable owned the 0 the twin must have"
                  + " been enabled (so j(PreUpdateEvent) ran every tick) while only 2 slots are"
                  + " live, which forces the ctor to share a cell -- sharing onDisable's cell"
                  + " gives 0 again.  Independent check that needs no twin arithmetic: p is"
                  + " MILLISECONDS (`p = (int)(1000.0/k.L())` at :62, CPS ships 10.0 -> 100 ms ="
                  + " 2 ticks at 50 ms) and `p <= 0` IS the ready-to-click state, so a fresh"
                  + " clicker is ready; 42 meanwhile has a natural owner at site 454, since"
                  + " LWJGL2 Keyboard.KEY_LSHIFT is 42 and that site is the second trigger key in"
                  + " `attack down && <it> down && in a container` -- the shift-click idiom."
                  + "  STILL UNMEASURED: value(10460).  With Always-click=true (shipped) the"
                  + " rearm at :62 is unreachable, p stays at the ctor value and 10460 never"
                  + " decrypts; flip Always-click off with the module enabled in a container and"
                  + " slot(10460) fills.  50 would confirm the millisecond reading exactly.");

      PENDING.add("Expo.module.impl.player.NoHitDelay  ctor (ICC)V  carrier LIVE -- no longer HELD: S:I = 0 is now"
                  + " DERIVED and the module is in ExpoCtorCache.plans().  The conflict that"
                  + " held it is resolved because the third module-hook site in AZ.r (bytecode"
                  + " pc630-638 / AZ.java:458, inside the NOT-ENABLED branch and unconditional"
                  + " there) is now known to be Expo/iD.P(J)V, not A(J)V: ZKM's own runtime"
                  + " resolver picked it and ExpoSweepCanary recorded the choice as"
                  + " \"EXPO_SWEEP_CANARY:P(long) at zkm$unresolved$6...Expo_iD_A_OR_Expo_iD_P\"."
                  + "  The same canary pins the other two sites empirically: ENABLE ->"
                  + " zkm$unresolved$2 -> i(long), DISABLE -> zkm$unresolved$3 -> A(long).  So"
                  + " A(long) (51 module overrides) is onDisable, i(long) (15) is onEnable, and"
                  + " P(long) (2: i9 and zk_4) is the every-tick-while-disabled baseline reset."
                  + "  CORRECTION to the chain handed over: it argued the ctor value is dead"
                  + " because P(long) overwrites S every tick while the module is off -- but"
                  + " NoHitDelay ships status=true, so it never enters that branch and the ctor"
                  + " value IS live.  See the plan comment for the two judges that decide it.");

      PERSISTABLE_NAMES.addAll(namesOf(PERSISTABLE));
      markDisabledUpstream();
      countGate();
   }

   public static final List<String> MISSING = new ArrayList<String>();

   public static boolean countGateGreen;

   private static void countGate() {
      MISSING.clear();

      ExpoCtorCache.Plan[] var0 = ExpoCtorCache.plans();

      for (int var1 = 0; var1 < var0.length; var1++) {
         if (!ModuleManager.o.containsKey(var0[var1].cls)) {
            MISSING.add(var0[var1].cls.getName());
         }
      }

      ExpoCtorCache.SPlan[] var2 = ExpoCtorCache.splans();

      for (int var3 = 0; var3 < var2.length; var3++) {
         if (!ModuleManager.o.containsKey(var2[var3].cls)) {
            MISSING.add(var2[var3].cls.getName());
         }
      }

      int var4 = ModuleManager.S == null ? -1 : ModuleManager.S.size();
      countGateGreen = var4 == ORIGINAL_MODULE_COUNT && MISSING.isEmpty();

      if (!countGateGreen) {
         PENDING.add("ExpoModuleRegistry  MODULE COUNT REGRESSION: tD.S holds " + var4 + " of "
                     + ORIGINAL_MODULE_COUNT + " and " + MISSING.size()
                     + " pre-populated module(s) never reached tD.o" + report());
      }
   }

   private static String report() {
      if (MISSING.isEmpty()) {
         return "";
      }

      StringBuilder var0 = new StringBuilder(" -- ");

      for (int var1 = 0; var1 < MISSING.size(); var1++) {
         var0.append(var1 == 0 ? "" : ", ").append(MISSING.get(var1));
      }

      return var0.toString();
   }

   private static final String[] DISABLED_UPSTREAM = {"Expo.module.impl.combat.AutoProjectiles", "Expo.module.impl.player.NoFall", "Expo.module.impl.player.FreeCam"};

   private static void markDisabledUpstream() {
      for (int i = 0; i < DISABLED_UPSTREAM.length; i++) {
         String name = DISABLED_UPSTREAM[i];
         Module found = null;

         for (Module m : ModuleManager.S) {
            if (m != null && name.equals(m.getClass().getName())) {
               found = m;
               break;
            }
         }

         if (found == null) {
            PENDING.add(name + "  not in tD.S, so it could not be marked non-toggleable");
            continue;
         }

         found.M(false);

         if (found.I()) {
            PENDING.add(name + "  M(false) did not take -- it is still toggleable");
         } else {
            PENDING.add(name + "  marked non-toggleable (stock refuses to enable it)");
         }
      }
   }

   private static boolean settingsUsable(Class<?> owner) {
      int total = 0;
      int live = 0;

      for (java.lang.reflect.Field f : owner.getDeclaredFields()) {
         if (!java.lang.reflect.Modifier.isStatic(f.getModifiers())
             || !Expo.setting.Setting.class.isAssignableFrom(f.getType())) {
            continue;
         }

         if (!ExpoSettingStatics.buildable(f.getType())) {
            continue;
         }

         total++;

         try {
            f.setAccessible(true);

            if (f.get(null) != null) {
               live++;
            }
         } catch (Throwable t) {
         }
      }

      return total == 0 || live == total;
   }

   private static void publishSolvedCarriers() {
      if (!PUBLISH_SOLVED_CARRIERS) {
         PENDING.add("Expo.module.impl.world.Scaffold / Expo.module.impl.combat.AutoBlock / Expo.module.impl.combat.KeepSprint  solved carriers disabled by PUBLISH_SOLVED_CARRIERS");
         return;
      }

      Expo.module.impl.world.Scaffold var0 = null;
      try {
         var0 = new Expo.module.impl.world.Scaffold(74866151867512L);
      } catch (Throwable var4) {
         PENDING.add("Expo.module.impl.world.Scaffold  solved carrier 74866151867512 threw at publish(): " + var4);
      }

      if (var0 != null) {
         ExpoSettingStatics.fillFor(var0, ExpoConfig.read(), null);
      }

      if (var0 != null && !settingsUsable(Expo.module.impl.world.Scaffold.class)) {
         PENDING.add("Expo.module.impl.world.Scaffold  HELD: its Setting statics are null and its handlers deref them from AZ.r, which aborts the pump and the ClickGUI hotkey poll");
         var0 = null;
      }

      if (var0 != null) {
         reg(ModuleManager.I = var0, Expo.module.impl.world.Scaffold.class, "Scaffold", true);
      }

      Expo.module.impl.combat.AutoBlock var1 = null;
      try {
         var1 = new Expo.module.impl.combat.AutoBlock((byte)0, 43254310455398L);
      } catch (Throwable var5) {
         PENDING.add("Expo.module.impl.combat.AutoBlock  solved carrier (0,43254310455398) threw at publish(): " + var5);
      }

      if (var1 != null) {
         ExpoSettingStatics.fillFor(var1, ExpoConfig.read(), null);
      }

      if (var1 != null && !settingsUsable(Expo.module.impl.combat.AutoBlock.class)) {
         PENDING.add("Expo.module.impl.combat.AutoBlock  HELD: its buildable Setting statics are still null after fillFor");
         var1 = null;
      }

      if (var1 != null) {
         PENDING.add("Expo.module.impl.combat.AutoBlock  ctor (BJ)V  carrier SOLVED (byte)0,43254310455398 -- PUBLISHED."
                     + "  The NullPointerException that withheld it is upstream and is now"
                     + " neutralised by one DECLARED DEVIATION in Expo/PY.d(): a"
                     + " z.thePlayer != null guard.  Chain: Expo/AZ.t(J,bW) calls"
                     + " Module.L(bW,J) on every module whose o() is FALSE -- L is the"
                     + " reset-while-disabled hook (Expo/i6.L is a pure reset: T(false), H7=null,"
                     + " b=0, C=0, a=false, H6=null, x=false) -- and AutoBlock ships status=false,"
                     + " so iT.L -> n(true) -> B() -> PY.d() runs on the main menu, where"
                     + " mc.thePlayer is null.  Verified upstream at three levels: AZ.t pc100-123"
                     + " is ifne-skip on one boolean whose indy has exactly two candidates"
                     + " (Expo/iD.o()Z and java/util/Iterator.hasNext()Z) of which only o() is"
                     + " type-compatible with the checkcast Expo/iD at pc58; PY.d() pc0-39 in"
                     + " expo-plain.jar has no guard; and the bus is published from the original"
                     + " N.F call site MinecraftHooks.t()V pc44, reached from onStartGame() inside"
                     + " Minecraft.startGame and from onPreTick() every runTick, so no install"
                     + " timing exists in which bW does not fire on the main menu.  The deviation"
                     + " is observationally empty here: the branch PY.d()==false takes is"
                     + " iT.f(J), which sets iT.K=false and iT.D=false, the values they already"
                     + " hold on the main menu.");
         reg(var1, Expo.module.impl.combat.AutoBlock.class, "AutoBlock", true);
      }

      Expo.module.impl.combat.KeepSprint var2 = null;
      try {
         var2 = new Expo.module.impl.combat.KeepSprint(27765L);
      } catch (Throwable var6) {
         PENDING.add("Expo.module.impl.combat.KeepSprint  solved carrier 27765 threw at publish(): " + var6);
      }

      if (var2 != null) {
         reg(var2, Expo.module.impl.combat.KeepSprint.class, "KeepSprint", true);
      }
   }

   private static void publishAnchoredCarriers() {
      if (!PUBLISH_ANCHORED_CARRIERS) {
         PENDING.add("Expo.module.impl.combat.KillAura / Expo.module.impl.combat.FakeLag / Expo.module.impl.combat.BlockHit / Expo.module.impl.combat.AutoClicker / Expo.module.impl.world.BedNuker / Expo.module.impl.player.InvManager"
                     + "  anchored carriers disabled by PUBLISH_ANCHORED_CARRIERS");
         return;
      }

      JsonObject var0 = null;
      try {
         var0 = ExpoConfig.read();
      } catch (Throwable var7) {
         PENDING.add("ExpoModuleRegistry  config read failed for the anchored carriers: " + var7);
      }

      Expo.module.impl.combat.KillAura var1 = null;
      try {
         var1 = new Expo.module.impl.combat.KillAura(8510264096497L);
      } catch (Throwable var8) {
         PENDING.add("Expo.module.impl.combat.KillAura  anchored carrier 8510264096497 threw at publish(): " + var8);
      }
      anchored(var1, Expo.module.impl.combat.KillAura.class, "KillAura", var0);

      Expo.module.impl.combat.BlockHit var2 = null;
      try {
         var2 = new Expo.module.impl.combat.BlockHit(61052605171397L);
      } catch (Throwable var9) {
         PENDING.add("Expo.module.impl.combat.BlockHit  anchored carrier 61052605171397 threw at publish(): " + var9);
      }
      anchored(var2, Expo.module.impl.combat.BlockHit.class, "BlockHit", var0);

      Expo.module.impl.combat.AutoClicker var3 = null;
      try {
         var3 = new Expo.module.impl.combat.AutoClicker((short)0, (char)13625, 150955398);
      } catch (Throwable var10) {
         PENDING.add("Expo.module.impl.combat.AutoClicker  anchored carrier (0,13625,150955398) threw at publish(): " + var10);
      }
      anchored(var3, Expo.module.impl.combat.AutoClicker.class, "AutoClicker", var0);

      Expo.module.impl.player.InvManager var5 = null;
      try {
         var5 = new Expo.module.impl.player.InvManager(11682536634362L);
      } catch (Throwable var12) {
         PENDING.add("Expo.module.impl.player.InvManager  anchored carrier 11682536634362 threw at publish(): " + var12);
      }
      anchored(var5, Expo.module.impl.player.InvManager.class, "InvManager", var0);

      Expo.module.impl.combat.FakeLag var6 = null;
      try {
         var6 = new Expo.module.impl.combat.FakeLag(0, 0, (short)0);
      } catch (Throwable var13) {
         PENDING.add("Expo.module.impl.combat.FakeLag  carrier-free constructor threw at publish(): " + var13);
      }
      anchored(var6, Expo.module.impl.combat.FakeLag.class, "FakeLag", var0);

      Expo.module.impl.world.BedNuker var4 = null;
      try {
         var4 = new Expo.module.impl.world.BedNuker((short)0, 33171000103266L);
      } catch (Throwable var11) {
         PENDING.add("Expo.module.impl.world.BedNuker  anchored carrier (0,33171000103266) threw at publish(): " + var11);
      }
      anchored(var4, Expo.module.impl.world.BedNuker.class, "BedNuker", var0);
   }

   private static void publishCachePrepopulated() {
      JsonObject var0 = null;
      try {
         var0 = ExpoConfig.read();
      } catch (Throwable var2) {
         PENDING.add("ExpoModuleRegistry  config read failed for the pre-populated carriers: " + var2);
      }

      try {
         ExpoCtorCache.publish(var0);
      } catch (Throwable var1) {
         PENDING.add("ExpoCtorCache.publish threw: " + var1);
      }
   }

   static void publishPrepopulated(Module var0, Class<? extends Module> var1, String var2,
                                   JsonObject var3) {
      anchored(var0, var1, var2, var3);
   }

   private static void anchored(Module var0, Class<? extends Module> var1, String var2,
                                JsonObject var3) {
      if (var0 == null) {
         return;
      }

      try {
         var0.K(var2);
         ExpoSettingStatics.fillFor(var0, var3, null);
      } catch (Throwable var5) {
         PENDING.add(var1.getName() + "  Setting fill threw at publish(): " + var5);
         return;
      }

      if (!settingsUsable(var1)) {
         PENDING.add(var1.getName() + "  HELD: Setting statics still null after fillFor");
         return;
      }

      try {
         reg(var0, var1, var2, true);
      } catch (Throwable var4) {
         PENDING.add(var1.getName() + "  reg() threw at publish(): " + var4);
      }
   }

   private static void reg(Module var0, Class<? extends Module> var1,
                           String var2, boolean var3) {
      if (var0 == null) {
         throw new IllegalStateException("ExpoModuleRegistry: null instance for " + var1);
      }

      if (var3 == var2.startsWith(PLACEHOLDER_PREFIX)) {
         throw new IllegalStateException("ExpoModuleRegistry: name/persistable mismatch for " + var1 + " -> " + var2);
      }

      if (var0.name() == null) {
         var0.K(var2);
      }

      ModuleManager.o.put(var1, var0);
      ModuleManager.S.add(var0);
      PUBLISHED.add(var1.getName());
      if (var3) {
         PERSISTABLE.add(var1);
      }
   }

   private static Set<String> namesOf(Set<Class<? extends Module>> var0) {
      Set<String> r = new LinkedHashSet<String>();
      for (Class<? extends Module> c : var0) {
         Module m = ModuleManager.o.get(c);
         if (m != null) {
            r.add(m.b());
         }
      }

      return r;
   }

   public static boolean isConfigPersistable(Module var0) {
      return var0 != null && var0.b() != null
         && !var0.b().startsWith(PLACEHOLDER_PREFIX)
         && PERSISTABLE.contains(var0.getClass());
   }

   public static Set<String> persistableNames() {
      return Collections.unmodifiableSet(PERSISTABLE_NAMES);
   }

   public static boolean isConfigWritable() {
      return published && countGateGreen && MISSING.isEmpty()
         && ModuleManager.S != null && ModuleManager.S.size() == ORIGINAL_MODULE_COUNT
         && PERSISTABLE.size() == ModuleManager.S.size();
   }

   public static void assertConfigWritable() {
      if (!isConfigWritable()) {
         throw new IllegalStateException("Expo config write refused: " + writableNote());
      }
   }

   public static String writableNote() {
      int size = ModuleManager.S == null ? -1 : ModuleManager.S.size();
      return "writable=" + isConfigWritable()
             + " published=" + published
             + " tD.S=" + size + "/" + ORIGINAL_MODULE_COUNT
             + " tD.o=" + (ModuleManager.o == null ? -1 : ModuleManager.o.size())
             + " persistable=" + PERSISTABLE.size()
             + " missing=" + MISSING.size()
             + " pending=" + PENDING.size()
             + " countgate=" + (countGateGreen ? "OK" : "REGRESSION");
   }
}
