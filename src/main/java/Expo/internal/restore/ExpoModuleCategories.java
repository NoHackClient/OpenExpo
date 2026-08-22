package Expo.internal.restore;

import Expo.module.Category;
import Expo.module.Module;
import Expo.module.ModuleManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public final class ExpoModuleCategories {

   public enum Prov {
      EVIDENCED,
      STRUCTURAL,
      CONVENTION
   }

   public static final class Row {
      public final String moduleName;
      public final Category category;
      public final Prov provenance;

      Row(String moduleName, Category category, Prov provenance) {
         this.moduleName = moduleName;
         this.category = category;
         this.provenance = provenance;
      }

      public String toString() {
         return moduleName + "=" + category + "(" + provenance + ")";
      }
   }

   public static final Category PLACEHOLDER_CATEGORY = Category.Misc;

   private static final Map<String, Row> TABLE = new LinkedHashMap<String, Row>();

   public static final List<String> ASSIGNED = new ArrayList<String>();
   public static final List<String> PLACEHOLDERED = new ArrayList<String>();

   private static boolean applied;

   private ExpoModuleCategories() {
   }

   private static void evid(String cls, String name, Category c) {
      TABLE.put(cls, new Row(name, c, Prov.EVIDENCED));
   }

   private static void structural(String cls, String name, Category c) {
      TABLE.put(cls, new Row(name, c, Prov.STRUCTURAL));
   }

   private static void conv(String cls, String name, Category c) {
      TABLE.put(cls, new Row(name, c, Prov.CONVENTION));
   }

   static {
      evid("Expo.module.impl.visual.TabGUI", "TabGUI", Category.Visual);

      evid("Expo.module.impl.world.Scaffold", "Scaffold", Category.World);

      structural("Expo.module.impl.macro.Macro1",   null, Category.Macro);
      structural("Expo.module.impl.macro.Macro2",   null, Category.Macro);
      structural("Expo.module.impl.macro.Macro3", null, Category.Macro);
      structural("Expo.module.impl.macro.Macro4", null, Category.Macro);
      structural("Expo.module.impl.macro.Macro5", null, Category.Macro);

      evid("Expo.module.impl.combat.AimAssist", "AimAssist", Category.Combat);
      evid("Expo.module.impl.combat.AutoBlock", "AutoBlock", Category.Combat);
      evid("Expo.module.impl.combat.HitBox", "HitBox", Category.Combat);
      evid("Expo.module.impl.combat.KeepSprint", "KeepSprint", Category.Combat);
      evid("Expo.module.impl.movement.FastFall", "FastFall", Category.Movement);
      evid("Expo.module.impl.movement.Fly", "Fly", Category.Movement);
      evid("Expo.module.impl.movement.NoJumpDelay", "NoJumpDelay", Category.Movement);
      evid("Expo.module.impl.movement.NoSlow", "NoSlow", Category.Movement);
      evid("Expo.module.impl.movement.Speed", "Speed", Category.Movement);
      evid("Expo.module.impl.movement.Sprint", "Sprint", Category.Movement);
      evid("Expo.module.impl.player.AutoWeapon", "AutoWeapon", Category.Player);
      evid("Expo.module.impl.player.ChestStealer", "ChestStealer", Category.Player);
      evid("Expo.module.impl.player.GhostHand", "GhostHand", Category.Player);
      evid("Expo.module.impl.world.AutoTunnel", "AutoTunnel", Category.World);
      evid("Expo.module.impl.world.FastPlace", "FastPlace", Category.World);
      evid("Expo.module.impl.world.SpeedMine", "SpeedMine", Category.World);
      evid("Expo.module.impl.visual.Ambience", "Ambience", Category.Visual);
      evid("Expo.module.impl.visual.Animations", "Animations", Category.Visual);
      evid("Expo.module.impl.visual.BarrierVisible", "BarrierVisible", Category.Visual);
      evid("Expo.module.impl.visual.BindGUI", "BindGUI", Category.Visual);
      evid("Expo.module.impl.visual.BreakProgress", "BreakProgress", Category.Visual);
      evid("Expo.module.impl.visual.CaveXray", "CaveXray", Category.Visual);
      evid("Expo.module.impl.visual.Freelook", "Freelook", Category.Visual);
      evid("Expo.module.impl.visual.HUD", "HUD", Category.Visual);
      evid("Expo.module.impl.visual.ItemScale", "ItemScale", Category.Visual);
      evid("Expo.module.impl.visual.KeyStrokes", "KeyStrokes", Category.Visual);
      evid("Expo.module.impl.visual.KillEffect", "KillEffect", Category.Visual);
      evid("Expo.module.impl.visual.NoHurtCam", "NoHurtCam", Category.Visual);
      evid("Expo.module.impl.visual.TeamInvisible", "TeamInvisible", Category.Visual);
      evid("Expo.module.impl.visual_utility.BedESP", "BedESP", Category.Visual_utility);
      evid("Expo.module.impl.visual_utility.BlocksESP", "BlocksESP", Category.Visual_utility);
      evid("Expo.module.impl.visual_utility.ChestESP", "ChestESP", Category.Visual_utility);
      evid("Expo.module.impl.visual_utility.ClosestPlayerHUD", "ClosestPlayerHUD", Category.Visual_utility);
      evid("Expo.module.impl.visual_utility.ESP", "ESP", Category.Visual_utility);
      evid("Expo.module.impl.visual_utility.FireBallPredict", "FireBallPredict", Category.Visual_utility);
      evid("Expo.module.impl.visual_utility.FKCounter", "FKCounter", Category.Visual_utility);
      evid("Expo.module.impl.visual_utility.Indicators", "Indicators", Category.Visual_utility);
      evid("Expo.module.impl.visual_utility.ItemESP", "ItemESP", Category.Visual_utility);
      evid("Expo.module.impl.visual_utility.ItemTags", "ItemTags", Category.Visual_utility);
      evid("Expo.module.impl.visual_utility.MegaWallsDetector", "MegaWallsDetector", Category.Visual_utility);
      evid("Expo.module.impl.visual_utility.NameTags", "NameTags", Category.Visual_utility);
      evid("Expo.module.impl.visual_utility.TargetHUD", "TargetHUD", Category.Visual_utility);
      evid("Expo.module.impl.visual_utility.Tracers", "Tracers", Category.Visual_utility);
      evid("Expo.module.impl.visual_utility.Trajectories", "Trajectories", Category.Visual_utility);
      evid("Expo.module.impl.misc.AntiBot", "AntiBot", Category.Misc);
      evid("Expo.module.impl.misc.AntiNick", "AntiNick", Category.Misc);
      evid("Expo.module.impl.misc.CommandLine", "CommandLine", Category.Misc);
      evid("Expo.module.impl.misc.Denick", "Denick", Category.Misc);
      evid("Expo.module.impl.misc.NameHider", "NameHider", Category.Misc);
      evid("Expo.module.impl.misc.RawInput", "RawInput", Category.Misc);

      evid("Expo.module.impl.configuration.ClickGUI", "ClickGUI", Category.Configuration);
      evid("Expo.module.impl.configuration.CustomCape", "CustomCape", Category.Configuration);
      evid("Expo.module.impl.configuration.Font", "Font", Category.Configuration);
      evid("Expo.module.impl.configuration.Gadgets", "Gadgets", Category.Configuration);
      evid("Expo.module.impl.configuration.Language", "Language", Category.Configuration);
      evid("Expo.module.impl.configuration.Notifications", "Notifications", Category.Configuration);
      evid("Expo.module.impl.configuration.ScoreBoard", "ScoreBoard", Category.Configuration);
      evid("Expo.module.impl.configuration.Teams", "Teams", Category.Configuration);
      evid("Expo.module.impl.configuration.Theme", "Theme", Category.Configuration);
      evid("Expo.module.impl.configuration.VisualSpoof", "VisualSpoof", Category.Configuration);

      evid("Expo.module.impl.combat.AntiFireball", "AntiFireball", Category.Combat);
      evid("Expo.module.impl.combat.AutoClicker", "AutoClicker", Category.Combat);
      evid("Expo.module.impl.combat.BackTrack", "BackTrack", Category.Combat);
      evid("Expo.module.impl.combat.BlockHit", "BlockHit", Category.Combat);
      evid("Expo.module.impl.combat.FakeLag", "FakeLag", Category.Combat);
      evid("Expo.module.impl.combat.HitSelect", "HitSelect", Category.Combat);
      evid("Expo.module.impl.combat.JumpReset", "JumpReset", Category.Combat);
      evid("Expo.module.impl.combat.KillAura", "KillAura", Category.Combat);
      evid("Expo.module.impl.combat.LagRange", "LagRange", Category.Combat);
      evid("Expo.module.impl.combat.SprintReset", "SprintReset", Category.Combat);
      evid("Expo.module.impl.combat.Velocity", "Velocity", Category.Combat);
      evid("Expo.module.impl.combat.WTap", "WTap", Category.Combat);
      evid("Expo.module.impl.misc.AutoGG", "AutoGG", Category.Misc);
      evid("Expo.module.impl.misc.ContainerKeeper", "ContainerKeeper", Category.Misc);
      evid("Expo.module.impl.misc.Timer", "Timer", Category.Misc);
      evid("Expo.module.impl.movement.InvMove", "InvMove", Category.Movement);
      evid("Expo.module.impl.movement.Stuck", "Stuck", Category.Movement);
      evid("Expo.module.impl.player.Blink", "Blink", Category.Player);
      evid("Expo.module.impl.player.ChestAura", "ChestAura", Category.Player);
      evid("Expo.module.impl.player.FastCraft", "FastCraft", Category.Player);
      evid("Expo.module.impl.player.FreeCam", "FreeCam", Category.Player);
      evid("Expo.module.impl.player.InvClicker", "InvClicker", Category.Player);
      evid("Expo.module.impl.player.InvManager", "InvManager", Category.Player);
      evid("Expo.module.impl.player.NoHitDelay", "NoHitDelay", Category.Player);
      evid("Expo.module.impl.player.NoInteract", "NoInteract", Category.Player);
      evid("Expo.module.impl.visual.ArrayList", "ArrayList", Category.Visual);
      evid("Expo.module.impl.visual.Chams", "Chams", Category.Visual);
      evid("Expo.module.impl.visual.FullBright", "FullBright", Category.Visual);
      evid("Expo.module.impl.visual_utility.BedPlates", "BedPlates", Category.Visual_utility);
      evid("Expo.module.impl.visual_utility.FallIndicator", "FallIndicator", Category.Visual_utility);
      evid("Expo.module.impl.visual_utility.InventoryHUD", "InventoryHUD", Category.Visual_utility);
      evid("Expo.module.impl.visual_utility.LeapModeHUD", "LeapModeHUD", Category.Visual_utility);
      evid("Expo.module.impl.world.AntiVoid", "AntiVoid", Category.World);
      evid("Expo.module.impl.world.AutoDigPlace", "AutoDigPlace", Category.World);
      evid("Expo.module.impl.world.AutoTool", "AutoTool", Category.World);
      evid("Expo.module.impl.world.BedNuker", "BedNuker", Category.World);
      evid("Expo.module.impl.world.BlockIn", "BlockIn", Category.World);
      evid("Expo.module.impl.world.BridgeAssist", "BridgeAssist", Category.World);
      evid("Expo.module.impl.world.Nuker", "Nuker", Category.World);

      evid("Expo.module.impl.player.NoFall", "NoFall", Category.Player);
      evid("Expo.module.impl.combat.AutoProjectiles", "AutoProjectiles", Category.Combat);
      evid("Expo.module.impl.visual.ViewClip", "ViewClip", Category.Visual);
      evid("Expo.module.impl.misc.NoObfuscation", "NoObfuscation", Category.Misc);
      evid("Expo.module.impl.misc.InputFix", "InputFix", Category.Misc);
      evid("Expo.module.impl.visual.AntiDebuff", "AntiDebuff", Category.Visual);
   }

   private static final String[] CONFIG_MODULE_NAMES = {
      "AimAssist", "Ambience", "Animations", "AntiBot",
      "AntiDebuff", "AntiFireball", "AntiNick",
      "AntiVoid", "ArrayList", "AutoBlock", "AutoClicker",
      "AutoDigPlace", "AutoGG", "AutoProjectiles", "AutoTool",
      "AutoTunnel", "AutoWeapon", "BackTrack", "BarrierVisible",
      "BedESP", "BedNuker", "BedPlates", "BindGUI",
      "Blink", "BlockHit", "BlockIn", "BlocksESP",
      "BreakProgress", "BridgeAssist", "CaveXray", "Chams",
      "ChestAura", "ChestESP", "ChestStealer", "ClickGUI",
      "ClosestPlayerHUD", "CommandLine", "ContainerKeeper", "CustomCape",
      "Denick", "ESP", "FKCounter", "FakeLag",
      "FallIndicator", "FastCraft", "FastFall", "FastPlace",
      "FireBallPredict", "Fly", "Font", "FreeCam",
      "Freelook", "FullBright", "Gadgets", "GhostHand",
      "HUD", "HitBox", "HitSelect", "Indicators",
      "InputFix", "InvClicker", "InvManager", "InvMove",
      "InventoryHUD", "ItemESP", "ItemScale", "ItemTags",
      "JumpReset", "KeepSprint", "KeyStrokes", "KillAura",
      "KillEffect", "LagRange", "Language", "LeapModeHUD",
      "Macro1", "Macro2", "Macro3", "Macro4",
      "Macro5", "MegaWallsDetector", "NameHider", "NameTags",
      "NoFall", "NoHitDelay", "NoHurtCam", "NoInteract",
      "NoJumpDelay", "NoObfuscation", "NoSlow", "Notifications",
      "Nuker", "RawInput", "Scaffold", "ScoreBoard",
      "Speed", "SpeedMine", "Sprint", "SprintReset",
      "Stuck", "TabGUI", "TargetHUD", "TeamInvisible",
      "Teams", "Theme", "Timer", "Tracers",
      "Trajectories", "Velocity", "ViewClip", "VisualSpoof",
      "WTap"
   };

   public static final List<String> NAME_CHECK_FAILURES = new ArrayList<String>();

   static {
      Set<String> closed = new HashSet<String>(Arrays.asList(CONFIG_MODULE_NAMES));
      for (Map.Entry<String, Row> e : TABLE.entrySet()) {
         String n = e.getValue().moduleName;
         if (n != null && !closed.contains(n)) {
            NAME_CHECK_FAILURES.add(e.getKey() + " -> " + n);
         }
      }
   }

   public static Map<String, Row> table() {
      return Collections.unmodifiableMap(TABLE);
   }

   public static int apply(List<String> pending) {
      if (applied) {
         return 0;
      }

      applied = true;
      ASSIGNED.clear();
      PLACEHOLDERED.clear();

      if (!NAME_CHECK_FAILURES.isEmpty()) {
         note(pending, "Expo category table SELF-CHECK FAILED: " + NAME_CHECK_FAILURES.size()
                       + " row(s) name a module absent from current.json -- "
                       + NAME_CHECK_FAILURES);
      }

      List<Module> mods = ModuleManager.S;
      if (mods == null || mods.isEmpty()) {
         note(pending, "Expo category table: tD.S is empty, nothing categorised -- "
                       + "both ClickGUI screens will be unusable");
         return 0;
      }

      int set = 0;
      int evidenced = 0;
      int structural = 0;
      int convention = 0;
      for (int k = 0; k < mods.size(); k++) {
         Module m = mods.get(k);
         if (m == null || m.f() != null) {
            continue;
         }

         String cn = m.getClass().getName();
         Row r = TABLE.get(cn);
         Category c;
         if (r != null) {
            c = r.category;
            ASSIGNED.add(cn + "=" + c + "(" + r.provenance + ")");
            if (r.provenance == Prov.EVIDENCED) {
               evidenced++;
            } else if (r.provenance == Prov.STRUCTURAL) {
               structural++;
            } else {
               convention++;
            }
         } else {
            c = PLACEHOLDER_CATEGORY;
            PLACEHOLDERED.add(cn);
         }

         m.B(c);
         set++;
      }

      note(pending, "Expo categories: set=" + set + " evidenced=" + evidenced
                    + " structural=" + structural + " convention=" + convention
                    + " unnamed->" + PLACEHOLDER_CATEGORY + "=" + PLACEHOLDERED.size()
                    + " (CONVENTION rows are inferred from the module name, "
                    + "not recovered from the product)");
      return set;
   }

   private static void note(List<String> pending, String s) {
      if (pending != null) {
         pending.add(s);
      }
   }
}
