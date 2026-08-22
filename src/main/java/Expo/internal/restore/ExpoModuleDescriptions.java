package Expo.internal.restore;

import Expo.module.Module;
import Expo.module.ModuleManager;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public final class ExpoModuleDescriptions {

   private static final String[] ROWS = {
      "AimAssist|Help you aim BETTER when you click",
      "Ambience|Change the environment rendering",
      "Animations|Some 1.7 item using animations",
      "AntiBot|Detect bots",
      "AntiDebuff|Remove debuff rendering",
      "AntiFireball|Hit fireballs back",
      "AntiNick|Allows you to see if any player is nicked",
      "AntiVoid|Prevent you from falling into the void",
      "ArrayList|Show a list of modules on screen",
      "AutoBlock|Allows you to combat while sword blocked",
      "AutoClicker|Automatically left click",
      "AutoDigPlace|Auto dig and place blocks beneath or above",
      "AutoGG|Send a \"GG\" message when a Hypixel game ends",
      "AutoProjectiles|This module is currently disabled",
      "AutoTool|Switch to the right tools when you are mining",
      "AutoTunnel|Automatically mine tunnel in MegaWalls",
      "AutoWeapon|Switch to the best weapon in hotbar during combat",
      "BackTrack|Simulate network lags to get advantage at reaching enemies",
      "BarrierVisible|Render barriers as glasses",
      "BedESP|Show ESP on beds",
      "BedNuker|Break the bed near around you",
      "BedPlates|Show surrounding blocks of beds",
      "BindGUI|Show binds of modules and their enabled status",
      "Blink|Stop outgoing packet and release them at one time",
      "BlockHit|Block the sword when needed to decrease damage received",
      "BlockIn|Automatically surrounds blocks when you are trying to break bed",
      "BlocksESP|Highlight some blocks",
      "BreakProgress|Display the current breaking percentage right on the current breaking block",
      "BridgeAssist|Sneak when you get close to the edge of the blocks",
      "CaveXray|Allows you to see structures underground (Only works with optifine)",
      "Chams|Allows you to see entities through blocks",
      "ChestAura|Automatically open chests in range",
      "ChestESP|ESP for chests",
      "ChestStealer|Steal items in the chest automatically",
      "ClickGUI|Manager ClickGUI settings",
      "ClosestPlayerHUD|Show the closest Mega Walls player in each team",
      "CommandLine|Configure the client setting by typing command in chat",
      "ContainerKeeper|Save a container to open later",
      "CustomCape|Get a fake better cape",
      "Denick|Resolve Hypixel tablist nicknames",
      "ESP|Aka \"Extra sensory perception\"",
      "FKCounter|Show MegaWalls deathmatch final kills per team",
      "FakeLag|Simulate network lags to get advantages during combat",
      "FallIndicator|Display the damage amount you might receive when looking at the ground",
      "FastCraft|Craft some MegaWalls items faster",
      "FastFall|Fall faster",
      "FastPlace|Change the block placing delay when holding RMB",
      "FireBallPredict|Predict and render fireball impact positions",
      "Fly|Allows you to fly without creative",
      "Font|Manage font rendering",
      "FreeCam|This module is currently disabled",
      "Freelook|Allows you to move your camera without moving your head",
      "FullBright|Let the game always be bright",
      "Gadgets|Some useful items",
      "GhostHand|Allows you to interact through entity",
      "HUD|Aka \"Heads up display\"",
      "HitBox|Modify entities hitbox to help reach target easier",
      "HitSelect|Modify your attacking strategy to get more hits in combat",
      "Indicators|Show projectiles that is going to hit you on screen",
      "InputFix|Fix some special characters typing",
      "InvClicker|Automatically click in inventory when you press shift",
      "InvManager|Clean and manage your inventory",
      "InvMove|Allows you to move around while opening a container",
      "InventoryHUD|Show your inventory contents on screen",
      "ItemESP|Render a box on items",
      "ItemScale|Scale the dropped items",
      "ItemTags|Render text bar on dropped items",
      "JumpReset|JumpReset and reduce knockback in combat",
      "KeepSprint|Modify the slowdown while attacking",
      "KeyStrokes|Show your keys interactions",
      "KillAura|Attack entities in range",
      "KillEffect|Play some effects after you killed your enemy",
      "LagRange|Perform network lag when entities in range",
      "Language|Configuration of language",
      "LeapModeHUD|Show the current Spider leap mode on screen",
      "Macro1|Macro slot 1 (Must be bound to use)",
      "Macro2|Macro slot 2 (Must be bound to use)",
      "Macro3|Macro slot 3 (Must be bound to use)",
      "Macro4|Macro slot 4 (Must be bound to use)",
      "Macro5|Macro slot 5 (Must be bound to use)",
      "MegaWallsDetector|Detect potion heals and phoenix resurrection from tab health",
      "NameHider|Replace all string that matches your name",
      "NameTags|Modify nametags rendering",
      "NoFall|This module is currently disabled",
      "NoHitDelay|Remove 10 ticks hit delay",
      "NoHurtCam|Change the hurt camera effect",
      "NoInteract|Prevent you from interacting with container blocks",
      "NoJumpDelay|Remove vanilla hold-space jump delay",
      "NoObfuscation|Remove the obfuscation minecraft chat code",
      "NoSlow|Change the slowdown when blocking sword, eating and pulling bow",
      "Notifications|Module toggle notifications settings",
      "Nuker|Mine blocks around you",
      "RawInput|Fix your mouse input",
      "Scaffold|Bridge automatically for you",
      "ScoreBoard|Manage vanilla scoreboard rendering",
      "Speed|Move faster",
      "SpeedMine|Increase your mining speed",
      "Sprint|Automatically sprint",
      "SprintReset|Reset sprint state during combat to give more knockback to opponent",
      "Stuck|Stuck you and disable movement",
      "TabGUI|Use tab and arrow keys to toggle modules",
      "TargetHUD|Show basic information about the current attacking target",
      "TeamInvisible|Let your teammates be \"Invisible\"",
      "Teams|Manage the teaming system",
      "Theme|The color theme of the client",
      "Timer|Modify your game running speed",
      "Tracers|Draw lines which traced to players",
      "Trajectories|Show trajectories of projectiles",
      "Velocity|Modify the velocity received",
      "ViewClip|Remove the camera blocking by blocks in 3rd person view",
      "VisualSpoof|Turn on or off visual spoofing",
      "WTap|Pause moving during combat to help combo",
   };

   private static final String[] FALLBACK = {
      "Expo.module.impl.combat.AutoProjectiles|AutoProjectiles",
      "Expo.module.impl.visual.ViewClip|ViewClip",
      "Expo.module.impl.misc.NoObfuscation|NoObfuscation",
      "Expo.module.impl.misc.InputFix|InputFix",
      "Expo.module.impl.visual.AntiDebuff|AntiDebuff",
   };

   private ExpoModuleDescriptions() {
   }

   public static final List<String> REFUSED = new ArrayList<String>();

   private static boolean applied;

   public static String apply(List<String> pending) {
      String note;
      int landed = 0;
      int already = 0;
      int conflicts = 0;
      int axisConflicts = 0;
      int unnamed = 0;
      int noRow = 0;
      int failed = 0;
      int seen = 0;
      int fallback = 0;
      int shadowed = 0;

      if (applied) {
         return null;
      }

      applied = true;
      REFUSED.clear();

      if (System.getProperty("expo.descriptions.off") != null) {
         note = "Expo.descriptions disabled by -Dexpo.descriptions.off";
         pending.add(note);
         return note;
      }

      try {
         Map<String, String> want = new HashMap<String, String>();

         for (String row : ROWS) {
            int i = row.indexOf('|');
            want.put(row.substring(0, i), row.substring(i + 1));
         }

         Map<String, String> byClass = new HashMap<String, String>();

         for (String row : FALLBACK) {
            int i = row.indexOf('|');
            String cls = row.substring(0, i);
            String nm = row.substring(i + 1);

            if (!want.containsKey(nm)) {
               REFUSED.add(cls + " -> " + nm
                           + ": fallback row names a module absent from the shipped set");
               continue;
            }

            byClass.put(cls, nm);
         }

         Set<String> usedFallback = new HashSet<String>();

         Field wf = Module.class.getDeclaredField("W");
         wf.setAccessible(true);

         List<Module> mods = ModuleManager.S;

         if (mods == null || mods.isEmpty()) {
            note = "Expo.descriptions: tD.S is empty, nothing written";
            pending.add(note);
            System.out.println("[EXPODIAG] " + note);
            return note;
         }

         for (int k = 0; k < mods.size(); k++) {
            Module m = mods.get(k);

            if (m == null) {
               continue;
            }

            seen++;

            ExpoModuleCategories.Row row = ExpoModuleCategories.table().get(m.getClass().getName());
            String a = row == null ? null : row.moduleName;

            String b = m.b();

            if (b != null && b.startsWith(ExpoModuleRegistry.PLACEHOLDER_PREFIX)) {
               b = null;
            }

            if (a != null && b != null && !a.equals(b)) {
               axisConflicts++;
               REFUSED.add(m.getClass().getName() + ": categories say " + a
                           + " but the registry says " + b + " -- no description written");
               continue;
            }

            String name = a != null ? a : b;

            String cls = m.getClass().getName();

            if (name == null) {
               name = byClass.get(cls);

               if (name != null) {
                  fallback++;
                  usedFallback.add(cls);
               }
            } else if (byClass.containsKey(cls)) {
               shadowed++;
               REFUSED.add(cls + " = " + name
                           + ": a stronger name axis already answers, fallback row is stale");
            }

            if (name == null) {
               unnamed++;
               continue;
            }

            String d = want.get(name);

            if (d == null) {
               noRow++;
               REFUSED.add(m.getClass().getName() + " = " + name
                           + ": no such module in the dump, no description");
               continue;
            }

            Object cur = wf.get(m);

            if (cur != null) {
               if (d.equals(cur)) {
                  already++;
               } else {
                  conflicts++;
                  REFUSED.add(m.getClass().getName() + " = " + name
                              + ": already carries a different description, left alone");
               }

               continue;
            }

            m.l(d);

            if (d.equals(wf.get(m))) {
               landed++;
            } else {
               failed++;
               REFUSED.add(m.getClass().getName() + " = " + name
                           + ": l(String) did not take");
            }
         }

         int resolved = landed + already + conflicts + failed;
         int unusedFallback = byClass.size() - usedFallback.size();

         note = "Expo.descriptions: " + landed + " of " + ROWS.length + " shipped descriptions"
                + " written onto " + seen + " published modules (" + already + " already correct, "
                + conflicts + " already different, " + axisConflicts + " name-axis conflicts, "
                + unnamed + " unnamed modules, " + noRow + " named but absent from the dump, "
                + failed + " writes that did not take)"
                + "; name axes: " + fallback + " of " + byClass.size()
                + " class-name fallback rows used, " + unusedFallback + " unused, "
                + shadowed + " shadowed by a stronger axis"
                + "; ceiling for this publish set = " + resolved + " of " + ROWS.length
                + ", so " + (ROWS.length - resolved)
                + " shipped descriptions have no published module to land on";
      } catch (Throwable t) {
         note = "Expo.descriptions FAILED (" + t + ") -- every description stays null";
         t.printStackTrace();
      }

      pending.add(note);
      System.out.println("[EXPODIAG] " + note);
      return note;
   }
}
