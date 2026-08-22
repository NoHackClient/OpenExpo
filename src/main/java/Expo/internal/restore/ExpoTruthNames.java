package Expo.internal.restore;

import Expo.module.Module;
import Expo.module.ModuleManager;
import Expo.setting.Setting;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public final class ExpoTruthNames {

   private static final String[] ORDERS = {
      "AutoGG|Delay,Message",
      "FKCounter|Background-opacity,Offset-X,Offset-Y,Scale",
      "LeapModeHUD|Background-opacity,Offset-X,Offset-Y,Scale",
      "HUD|Info-mode,Scale,Watermark,Health,BPS,Coordinate,Release,Username,FPS,Time",
      "Nuker|Range,Mine-down,Swing,Move-fix",
      "HitBox|Expand,@F,Players,Mobs,Animals,Bosses,Friends,Enemies,Teammates,Bots",
      "KillAura|Mode,Sort,FOV,Min-APS,Max-APS,Attack-range,Swing-range,Switch-delay,Rotation,Rotation-smoothing,Angle-step,Move-fix,Show-target,Show-target-color,Custom-color,Show-target-damage-color,Show-target-opacity,Show-reach-ring,Legit,Require-click,Require-Sword,Screen-check,Through-wall,Players,Mobs,Animals,Bosses,Friends,Enemies,Teammates,Bots,Silverfishes,Golems",
      "Macro1|Mode,Projectiles-duration,Min-health,Swap-back,Chat-message",
      "HitSelect|Strategy,Min-pause-tick,Max-pause-tick,Chance",
      "AutoDigPlace|Right-click-dig-down,Swing",
      "FakeLag|Target-range,Disable-range,FOV,Min-duration,Max-duration,Min-interval,Max-interval,Allowed-targets-amount,Sword-only,@e,Players,Mobs,Animals,Bosses,Friends,Enemies,Teammates,Bots",
      "Scaffold|Mode,Normal-mode-rotation,Legit-mode-rotation,Keep-Y-mode-rotation,Offset-rotation-offset,Legit-mode-edge-offset,Legit-mode-unsneak-delay,Straight-jump-blocks,Diagonal-jump-blocks,Straight-air-delay,Diagonal-air-delay,Keep-Y-jump-forward-chance,Rotation-smoothing,Angle-step,Move-fix,Aim-check,Strict-aim-check,Swing,Auto-item,Keep-Y-on-right-click,Item-counter,Keep-Y-blink-rotation,Down-place,Dont-render-rotation,Fake-item,ESP-Color,Custom-color,Show-target-shade,Show-target-outline,Outline-fade-out",
      "Macro2|Mode,Projectiles-duration,Min-health,Swap-back,Chat-message",
      "AutoBlock|Mode,FOV,Target-range,APS-mode,Manual-left-click,Require-right-click,Require-KillAura,Smart-unblock,Smart-unblock-chance,Smart-unblock-ticks,Allow-no-slow,Visual-blocking,@v,Players,Mobs,Animals,Bosses,Friends,Enemies,Teammates,Bots,Silverfishes,Golems",
      "AimAssist|Sort,Range,FOV,Horizontal-speed,Vertical-speed,Lock,Break-blocks,Ignore-behind-wall,Sword-only,@O,Players,Mobs,Animals,Bosses,Friends,Enemies,Teammates,Bots",
      "AutoProjectiles|Mode,Disable-range,Range,Hold-item-delay,Throw-interval,Throw-amounts,Allow-autoblock,Only-use-packet-while-autoblocking",
      "BlockHit|Mode,Range,FOV,Predict-hurt-resist-ticks,Predict-early-ticks,Predict-block-ticks,Predict-random-early-ticks,Predict-max-ping-comp-ticks,Spam-BPS,Spam-block-time,Lag-after-block-mode,Lag-after-block-time,Require-left-click,Require-right-click,Only-AutoClicker,@M,Players,Mobs,Animals,Bosses,Friends,Enemies,Teammates,Bots",
      "AntiFireball|Range,FOV,Swing,Move-fix",
      "AutoClicker|MinCPS,MaxCPS,Sag-blocking-ticks,Sag-unblock-duration,Break-blocks,Sag",
      "AutoTool|Delay,Require-sneak,Switch-back,Switch-back-to-sword,Disable-when-holding-sword",
      "BedNuker|Range,FOV,Move-fix,Swing,Require-click,Whitelist-own-bed,Ignore-outside-layer,Auto-item,Keep-rotation,Legit,Delay-velocity-ticks,Delay-velocity-range,Blink-disable-range,Blink-range,Blink-duration,Show-target-color,Custom-color,Show-target-shade,Show-target-outline,Show-target-percentage,Show-target-bar",
      "ChestAura|Range,Rotation,Through-wall,Move-fix,Disable-when-players-in-range",
      "BlockIn|Range,Angle-step,Rotation-tolerance,Move-fix,Swing",
      "Macro3|Mode,Projectiles-duration,Min-health,Swap-back,Chat-message",
      "BackTrack|Min-delay,Max-delay,Min-interval,Max-interval,Min-range,Max-range,@R,Players,Mobs,Animals,Bosses,Friends,Enemies,Teammates,Bots",
      "Macro4|Mode,Projectiles-duration,Min-health,Swap-back,Chat-message",
      "InvManager|Mode,Silent-mode,Silent-key,Start-delay,Min-delay,Max-delay,Auto-armor,Throw-trash,Auto-close,Only-sort-once,Only-items-configured-are-trash,Projectiles-is-trash,Normal-food-is-trash,Bow-is-trash,Potion-is-trash,Tools-are-trash,Sword-slot,Projectiles-slot,Block-slot,Food-slot,Bow-slot,Pickaxe-slot,Axe-slot,Shovel-slot,Potion-slot,Fireball-slot,Ender-pearl-slot,Shears-slot,Max-block-slots,Max-arrow-slots,Max-trash-throws",
      "Macro5|Mode,Projectiles-duration,Min-health,Swap-back,Chat-message",
      "InventoryHUD|Offset-X,Offset-Y,Background-opacity",
      "ItemESP|Scale,Opacity,Outline,Diamonds,Emeralds,Golds,Irons",
      "Indicators|Render-arrows,Render-ender-pearls,Render-fireballs,Render-eggs,Render-snowballs,Circle-radius,Item-colors,Render-distance,Only-when-approaching,Render-only-offscreen",
      "ChestESP|Color,Custom-color,Opacity,Show-target-shade,Show-target-outline,Ignore-opened",
      "NameTags|Scale,Background-opacity,Auto-scale,Only-name,Show-self,Text-shadow,Show-health,Show-distance,Show-hits-to-kill,Show-items,Show-effects,Show-indicator,@b,Players,Mobs,Animals,Bosses,Friends,Enemies,Teammates,Bots",
      "ESP|Mode,Color,Custom-color,Health-bar,Offset,Show-self,Hide-teammates-health-bar,@y,Players,Mobs,Animals,Bosses,Friends,Enemies,Teammates,Bots",
      "SpeedMine|Mode,Delay,Delay-chance,Increase-speed,Speed-chance",
      "BedESP|Range,Background-opacity,Color,Custom-color,Outline",
      "TeamInvisible|Range,Opacity",
      "ItemTags|Scale,Background-opacity,Megawalls-items,Bedwars-resources,Render-swords-and-bows,Render-blocks,Render-golden-apples,Render-ALL,NBT-only",
      "TargetHUD|Target-mode,Style,Range,X,Y,Scale,Stay-time,Background-opacity,Color,Custom-color,Custom-health-color,Text-shadow,Outline,@r,Chat-preview,Indicators,Only-when-using-killaura,@y,Players,Mobs,@H,Bosses,Friends,Enemies,Teammates,Bots",
      "BridgeAssist|Silent-rotation,Edge-offset,Unsneak-delay,Sneak-on-jump-time,Require-sneak,Require-holding-blocks,Require-looking-down,Not-moving-forward",
      "Tracers|Mode,Color-mode,@b,Mobs-color,Animals-color,Bosses-color,Friends-color,Enemies-color,@o,@F,Players,Mobs,Animals,Bosses,Friends,Enemies,Teammates,Bots",
      "AntiVoid|Mode,Blink-fall-distance",
      "AutoTunnel|Mode,Sneak-mode,Auto-tool,Auto-turn,Auto-back,Owned-chests-only,GapAlt-only-stone,Side-offset-scan,User-manual-screen-move,Turn-speed,Rotation-smoothing,Stuck-timeout,No-break-timeout,Unsneak-chance,Unsneak-duration,Chest-scan-radius",
      "BlocksESP|Range,Opacity,Outline,Shade,Tracers,Caves-only,Caves-radius,@N,Diamond,Obsidian,Spawner,Gold,Iron,Coal,Lapis,Emerald,Redstone",
      "MegaWallsDetector|Potion-detector,Potion-counts-in-tab,Potion-chat-notify,Phoenix-detector,Phoenix-icons-in-tab,Phoenix-chat-notify",
      "FireBallPredict|Real-fireballs,Held-fire-charges,Predict-range,Render-radius,Opacity",
      "Trajectories|Base-color,Teammates-color,Friend-color,Enemy-color,Bot-color,None-players-color",
      "FastPlace|Block-Delay,Projectiles-Delay,Disable-when-bed-in-range",
      "BedPlates|Surrounding-range,Background-opacity,Color,Custom-color,Outline,Fill",
      "FallIndicator|Min-damage-percentage,Only-while-sneaking,Show-fall-distance",
      "ClosestPlayerHUD|Background-opacity,Offset-X,Offset-Y,Scale,Display-head,Display-name,Display-distance,Display-team-size,Display-arrow,Display-height,Display-health",
      "ScoreBoard|Hide-scoreboard,Scale,Offset-X,Offset-Y,Background-opacity,Disable-scores,Text-shadow,Rounded-rectangle",
      "Teams|Sort-mode,Custom-pattern-regex",
      "Ambience|Mode,Time,Speed",
      "Blink|Mode,Pulse-ticks,Show-delay,Turn-off-on-hit,Auto-disable,Auto-disable-ticks",
      "FastFall|Horizontal-speed-restriction,Require-scaffold",
      "ArrayList|Background-opacity,Mode,Gradient-mode,Bar-color,Custom-color,Text-color,Text-custom-color,Show-suffix,Split-suffix-and-name,Only-show-suffix-modules,Text-shadow,Module-name-lowercase,Suffix-name-lowercase,Bar,Icons,Offset-X,Offset-Y,Scale,Bar-width,Rectangle-Y-space,Rectangle-Y-edge,Custom-text",
      "TabGUI|Offset-X,Offset-Y,Color,Custom-color,Disable-tab-key",
      "Speed|Mode,Speed",
      "Notifications|Graphic,Sound,Custom-background-color,Strip-color,Customstrip-color,Text-shadow,Stay-time,Leave-time,Offset-X,Offset-Y",
      "AutoWeapon|Axe-is-weapon,Stick-is-weapon,FishingRod-is-weapon",
      "WTap|Min-pause-tick,Max-pause-tick,Interval,Chance,Require-target-damage,Require-on-ground,Use-block-instead",
      "InvMove|Container-mode,Inventory-mode,ClickGUI",
      "Font|ArrayList-font,ClickGUI-font,HUD-font,Notifications-font,Scoreboard-font,Others-font",
      "NoSlow|Sword-mode,Only-enable-when-autoblock,Other-mode,Slow-down",
      "InvClicker|CPS,Always-click",
      "KillEffect|Mode,Only-killed-by-self",
      "KeepSprint|Mode,Slowdown",
      "LagRange|Delay,Target-range,Disable-range,FOV,@S,Players,Mobs,Animals,Bosses,Friends,Enemies,Teammates,Bots",
      "JumpReset|Chance,FOV,Range,Require-moving,Reduce,@D,Players,Mobs,Animals,Bosses,Friends,Enemies,Teammates,Bots",
      "Animations|Mode,No-rotations-effect,No-equip-reset,Swing-speed,Offset-X,Offset-Y,Offset-Z,Scale-X,Scale-Y,Scale-Z,Rotation-X,Rotation-Y,Rotation-Z",
      "Stuck|Mode,Pulse-delay",
      "Chams|@p,Players,Mobs,Animals,Bosses,Friends,Enemies,Teammates,Bots",
      "Theme|Theme,Custom-theme,Offset,Timer-multiplier,Custom-color-1,Custom-color-2,Custom-color-3",
      "KeyStrokes|Offset-X,Offset-Y,Background-opacity",
      "ChestStealer|Start-delay,Min-delay,Max-delay,Auto-close,Ignore-trash,Chest-integrity-check,Silent,@a,Armor,Blocks,Bow,Food,Potions,Projectiles,Sword,Tools",
      "ContainerKeeper|Toggle-key,Require-shift-to-save",
      "Denick|Mode,Refresh-rate",
      "Fly|Horizontal-speed,Vertical-speed",
      "Velocity|Horizontal,Vertical,Modify-velocity,Reverse-velocity,Min-delay-ticks,Max-delay-ticks,Delay-velocity,Reduce-effect-ticks,Reduce-velocity,FOV,Chance,Disable-while-holding-S,Delay-release-on-ground,Delay-release-on-reduce,Require-moving,@e,Players,Mobs,Animals,Bosses,Friends,Enemies,Teammates,Bots",
      "NoFall|Mode,Fall-distance,Timer-speed,Ground-spoof-ticks,Always-ground-spoof",
      "Language|Language,Apply-for-category,Apply-for-descriptions,Apply-for-name,Apply-for-settings,Apply-for-arraylist",
      "CommandLine|Auto-fill,Auto-fill-prompt",
      "VisualSpoof|Enable-external-window,Enable-screenshot-bypass,Disable-render-visual,Keybind-toggle-render-visual",
      "BindGUI|Scale,Offset-X,Offset-Y",
      "SprintReset|Mode,Duration,Interval,Require-target-damage",
      "Gadgets|Better-world-swapping,No-screen-background,No-mining-particles",
      "ClickGUI|Mode,Scale,Keybind",
      "ItemScale|Scale,Megawalls-items,Bedwars-resources,Render-swords-and-bows,Render-golden-apples,Render-ALL,NBT-only",
      "GhostHand|Disable-while-holding-sword,Teammates-only,Players-only,Tools-only,Blacklist-enemy",
      "CaveXray|Opacity,Reload-renderer",
   };

   private ExpoTruthNames() {
   }

   public static String apply(List<String> pending) {
      int reordered = 0;
      String note;

      try {
         Map<String, String[]> order = new HashMap<String, String[]>();

         for (String row : ORDERS) {
            int i = row.indexOf('|');
            order.put(row.substring(0, i), row.substring(i + 1).split(","));
         }

         for (Module mod : ModuleManager.S) {
            if (mod == null) {
               continue;
            }

            String simple = mod.getClass().getSimpleName();

            Map<String, Setting> live = new LinkedHashMap<String, Setting>();
            collect(mod.getClass(), live);

            String[] seq = order.get(simple);

            if (seq != null && reorder(mod, seq, live)) {
               reordered++;
            }
         }

         ExpoModuleSettings.applyByName(pending);

         note = "Expo.truthnames display order: " + reordered
                + " module panels put back into the shipped display order";
      } catch (Throwable t) {
         note = "Expo.truthnames FAILED (" + t + ") -- labels stay as they were";
         t.printStackTrace();
      }

      pending.add(note);
      System.out.println("[EXPODIAG] " + note);
      return note;
   }

   private static void collect(Class<?> c, Map<String, Setting> live) {
      for (Class<?> k = c; k != null && Module.class.isAssignableFrom(k); k = k.getSuperclass()) {
         for (Field f : k.getDeclaredFields()) {
            if (!Modifier.isStatic(f.getModifiers())
                || !Setting.class.isAssignableFrom(f.getType())
                || live.containsKey(f.getName())) {
               continue;
            }

            try {
               f.setAccessible(true);
               Setting s = (Setting)f.get(null);

               if (s != null) {
                  live.put(f.getName(), s);
               }
            } catch (Throwable t) {
            }
         }
      }
   }

   private static boolean reorder(Module mod, String[] seq, Map<String, Setting> statics) {
      List<Setting> live = mod.w();

      if (live == null || live.size() < 2) {
         return false;
      }

      Map<String, Setting> byName = new LinkedHashMap<String, Setting>();

      for (Setting s : live) {
         String n = s.B();

         if (n != null && !byName.containsKey(n)) {
            byName.put(n, s);
         }
      }

      List<Setting> out = new ArrayList<Setting>(live.size());
      Map<Setting, Boolean> seen = new IdentityHashMap<Setting, Boolean>();

      for (String n : seq) {
         Setting s = n.length() > 1 && n.charAt(0) == '@'
            ? statics.get(n.substring(1))
            : byName.get(n);

         if (s != null && live.contains(s) && seen.put(s, Boolean.TRUE) == null) {
            out.add(s);
         }
      }

      for (Setting s : live) {
         if (seen.put(s, Boolean.TRUE) == null) {
            out.add(s);
         }
      }

      if (out.size() != live.size()) {
         return false;
      }

      live.clear();
      live.addAll(out);
      return true;
   }
}
