package Expo.internal.restore;

import Expo.module.Module;
import Expo.module.ModuleManager;
import Expo.module.impl.configuration.ClickGUI;
import Expo.setting.Setting;
import Expo.setting.settings.HeaderSetting;
import Expo.util.RotationManager;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.WorldType;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;


public final class ExpoSelfTest {

   private static final int HOLD_TICKS = 8;

   private static final int REST_STREAK = 3;

   private static final int SETTLE_MAX = 60;

   private static final int CONTROL_TICKS = 4;

   private static final double REST_MOTION = 1.0E-3;

   private static final double CONTROL_DRIFT = 0.01;

   private static final double MIN_DIST = 0.05;

   private static final double YAW_TOL = 0.01;

   private static final double Y_TOL = 0.05;

   private static final double STRAFE_TOL = 1.0E-4;

   private static final int CLEAR_R = 3;

   private static final int ANCHOR_SEARCH = 8;

   private static final int ST_MENU = 0;
   private static final int ST_CREATE = 1;
   private static final int ST_LOAD = 2;
   private static final int ST_PROBE_AIM = 3;
   private static final int ST_PROBE_WINDOW = 4;
   private static final int ST_CLICKGUI = 5;
   private static final int ST_CLICKGUI_REPORT = 6;
   private static final int ST_SWEEP_BEGIN = 7;
   private static final int ST_SWEEP_STEP = 8;
   private static final int ST_FLUSH = 9;
   private static final int ST_PROBE_SETTLE = 10;
   private static final int ST_CONFIG_BEGIN = 11;
   private static final int ST_CONFIG_WAIT = 12;
   private static final int ST_DONE = 99;

   private static final long AG4_C_CARRIER = 32768000158890L;

   private static final int CONFIG_ROUNDS = 2;

   private static final int CONFIG_WAIT_TICKS = 200;

   private static final float[] PROBE_YAWS = {0.0F, 0.0F, 45.0F, 90.0F, 135.0F, 180.0F, -90.0F};

   private static final String WORLD = System.getProperty("expo.selftest.world", "expo-selftest");

   private static final boolean SWEEP = !"0".equals(System.getProperty("expo.selftest.sweep"));

   private static final List<String> LOG = new ArrayList<String>();

   private static ExpoSelfTest instance;

   private int state;
   private int wait;
   private int probe;
   private double x0;
   private double z0;
   private float heldYaw;
   private boolean pressTook;
   private int closedScreens;
   private String escVerdict = "not run";

   private static final String[] CGUI_MODES = {"STUDIO", "VESTIGE", "RAVEN"};
   private int cguiRound;
   private final String[] cguiVerdict = {"not run", "not run", "not run"};

   private double anchorX;
   private double anchorY;
   private double anchorZ;
   private boolean anchorOk;
   private String anchorNote = "not searched";

   private int settleTicks;
   private int restStreak;
   private int controlTicks;
   private double controlX;
   private double controlZ;
   private double controlDrift;
   private String settleNote;

   private double y0;
   private double lastX;
   private double lastZ;
   private double maxYawDev;
   private double maxStrafe;
   private double maxYDev;
   private double stepErrMax;
   private double anchorMiss;
   private int steps;
   private int stallTicks;
   private int screenTicks;
   private int collideTicks;
   private int airTicks;

   private int okCount;
   private int wrongCount;
   private int skipCount;
   private boolean sweepReached;

   private int configRound;
   private int configSeen;
   private int configPass;
   private int configFail;
   private Module configModule;
   private boolean configWant;
   private String configSha;
   private java.util.Set<String> configKeys;
   private String configVerdict = "not run";

   private ExpoSelfTest() {
   }

   public static void install() {
      if (!"1".equals(System.getProperty("expo.selftest"))) {
         return;
      }

      if (instance != null) {
         return;
      }

      instance = new ExpoSelfTest();
      MinecraftForge.EVENT_BUS.register(instance);
      ExpoSeedProbe.start();
      say("harness armed -- will create world '" + WORLD + "' and probe " + PROBE_YAWS.length + " yaws");
      say("PREMISE: this runs in a client window a human may be using at the same time."
          + "  Lines tagged DRIVER are things the harness did; lines tagged ENV are things it"
          + " found already true and did not cause.");
   }

   @SubscribeEvent
   public void onClientTick(TickEvent.ClientTickEvent event) {
      if (event.phase != TickEvent.Phase.END) {
         return;
      }

      try {
         step(Minecraft.getMinecraft());
      } catch (Throwable t) {
         ExpoDiag.attribute(t, "harness-tick");
         say("HARNESS TICK THREW in state " + this.state + ": " + ExpoDiag.describe(t));
         t.printStackTrace();

         if (!this.sweepReached) {
            say("ABORTED before the sweep could start");
            this.state = ST_DONE;
         }
      }
   }

   private void step(Minecraft mc) {
      switch (this.state) {
         case ST_MENU:
            if (mc.currentScreen instanceof GuiMainMenu) {
               reportBootstrap();
               this.state = ST_CREATE;
            }

            break;
         case ST_CREATE:
            wipeWorld(mc);
            say("creating world");
            WorldType flat = WorldType.FLAT;
            say("world type = " + (flat == null ? "null" : flat.getWorldTypeName()));
            WorldSettings settings = new WorldSettings(
               1234L, WorldSettings.GameType.CREATIVE, false, false, flat == null ? WorldType.DEFAULT : flat
            );
            settings.enableBonusChest();
            mc.launchIntegratedServer(WORLD, WORLD, settings);
            this.wait = 0;
            this.state = ST_LOAD;
            break;
         case ST_LOAD:
            if (mc.thePlayer != null && mc.theWorld != null && mc.thePlayer.onGround) {
               closeAnyScreen(mc, "before probe");

               if (++this.wait > 40) {
                  reportWorld(mc);
                  findAnchor(mc);
                  this.probe = 0;
                  this.state = ST_PROBE_AIM;
               }
            } else if (++this.wait > 1200) {
               say("TIMEOUT waiting for world; screen=" + screenName(mc.currentScreen)
                   + " player=" + (mc.thePlayer != null) + " world=" + (mc.theWorld != null));
               this.state = ST_CLICKGUI;
            }

            break;
         case ST_PROBE_AIM:
            closeAnyScreen(mc, "before probe");
            probeAim(mc);
            this.state = ST_PROBE_SETTLE;
            break;
         case ST_PROBE_SETTLE:
            if (probeSettle(mc)) {
               beginWindow(mc);
               this.state = ST_PROBE_WINDOW;
            }

            break;
         case ST_PROBE_WINDOW:
            sampleWindow(mc);

            if (++this.wait < HOLD_TICKS) {
               holdForward(mc);
            } else {
               endProbe(mc);
               this.state = ++this.probe < PROBE_YAWS.length ? ST_PROBE_AIM : ST_CLICKGUI;
            }

            break;
         case ST_CLICKGUI:
            probeClickGui(mc);
            this.state = ST_CLICKGUI_REPORT;
            break;
         case ST_CLICKGUI_REPORT:
            reportClickGui(mc);
            this.state = ++this.cguiRound < CGUI_MODES.length ? ST_CLICKGUI : ST_CONFIG_BEGIN;

            if (this.state == ST_CONFIG_BEGIN) {
               probeSortOrder();
            }

            break;
         case ST_CONFIG_BEGIN:
            closeAnyScreen(mc, "before config save probe");
            this.state = configBegin() ? ST_CONFIG_WAIT : ST_SWEEP_BEGIN;
            break;
         case ST_CONFIG_WAIT:
            if (ExpoConfig.saveCount > this.configSeen) {
               configReport();
               this.state = ++this.configRound < CONFIG_ROUNDS
                  ? ST_CONFIG_BEGIN : ST_SWEEP_BEGIN;
            } else if (++this.wait > CONFIG_WAIT_TICKS) {
               this.configFail++;
               this.configVerdict = "TIMEOUT";
               say("CONFIG round " + this.configRound + " FAIL -- ag_4.c fired but ExpoConfig.saveCount"
                   + " never moved in " + CONFIG_WAIT_TICKS + " ticks; saveCount=" + ExpoConfig.saveCount
                   + " configSaveUnavailable=" + Expo.module.Modules.gatesweep$configSaveUnavailable);
               this.state = ST_SWEEP_BEGIN;
            }

            break;
         case ST_SWEEP_BEGIN:
            this.sweepReached = true;

            if (!SWEEP) {
               say("module/setting sweep DISABLED by -Dexpo.selftest.sweep=0");
               this.state = ST_FLUSH;
               break;
            }

            closeAnyScreen(mc, "before sweep");
            ExpoSweep.begin(ModuleManager.S);
            this.state = ST_SWEEP_STEP;
            break;
         case ST_SWEEP_STEP:
            if (mc.currentScreen != null) {
               ExpoSweep.note("UNDETERMINED origin -- screen " + screenName(mc.currentScreen)
                              + " was open during " + ExpoSweep.current()
                              + "; closed so the next operation starts from the same state");
               safeCloseScreen(mc);
            }

            if (!ExpoSweep.step()) {
               this.state = ST_FLUSH;
            }

            break;
         case ST_FLUSH:
            flush();
            ExpoDiag.dumpCounts();
            this.state = ST_DONE;
            break;
         default:
      }
   }

   private void wipeWorld(Minecraft mc) {
      try {
         ISaveFormat saves = mc.getSaveLoader();

         if (saves == null) {
            say("DRIVER could not reach the save loader; world '" + WORLD + "' is whatever the last run left");
            return;
         }

         saves.flushCache();
         boolean existed = saves.getWorldInfo(WORLD) != null;

         if (!existed) {
            say("DRIVER world '" + WORLD + "' did not exist yet -- this run generates it from scratch");
            return;
         }

         boolean gone = saves.deleteWorldDirectory(WORLD);
         say("DRIVER deleted the previous world '" + WORLD + "' (deleteWorldDirectory=" + gone
             + ") so the probes walk on terrain this run generated, not on terrain the last"
             + " run's module sweep edited and not from the position the last run left the player in");
      } catch (Throwable t) {
         say("DRIVER deleting world '" + WORLD + "' THREW " + ExpoDiag.describe(t)
             + " -- probing continues on the old world; the clearance controls still apply");
         ExpoDiag.attribute(t, "driver-wipe-world");
      }
   }

   private void findAnchor(Minecraft mc) {
      EntityPlayerSP p = mc.thePlayer;
      double y = p.posY;
      int bx = (int)Math.floor(p.posX);
      int bz = (int)Math.floor(p.posZ);

      for (int r = 0; r <= ANCHOR_SEARCH; r++) {
         for (int dx = -r; dx <= r; dx++) {
            for (int dz = -r; dz <= r; dz++) {
               if (r > 0 && Math.abs(dx) != r && Math.abs(dz) != r) {
                  continue;
               }

               double cx = bx + dx + 0.5;
               double cz = bz + dz + 0.5;

               if (isClear(mc, p, cx, y, cz)) {
                  this.anchorX = cx;
                  this.anchorY = y;
                  this.anchorZ = cz;
                  this.anchorOk = true;
                  this.anchorNote = r == 0
                     ? "the block the world dropped the player on"
                     : ("the nearest clear block, " + r + " away from where the world dropped the player");
                  say("DRIVER anchor = " + fmt(cx) + "," + fmt(y) + "," + fmt(cz) + " (" + this.anchorNote
                      + "); every probe starts from it, so the six directions are measured under the"
                      + " same conditions and the run cannot drift out of the verified area");
                  return;
               }
            }
         }
      }

      this.anchorOk = false;
      this.anchorNote = "no spot within " + ANCHOR_SEARCH + " blocks had " + CLEAR_R
                        + " blocks of floor and free space in every direction";
      say("ENV " + this.anchorNote + " -- every probe will report SKIPPED rather than measure a"
          + " walk that terrain, not the product, decided");
   }

   private static boolean isClear(Minecraft mc, EntityPlayerSP p, double cx, double cy, double cz) {
      int fx = (int)Math.floor(cx);
      int fy = (int)Math.floor(cy);
      int fz = (int)Math.floor(cz);

      for (int x = -CLEAR_R; x <= CLEAR_R; x++) {
         for (int z = -CLEAR_R; z <= CLEAR_R; z++) {
            if (mc.theWorld.isAirBlock(new BlockPos(fx + x, fy - 1, fz + z))) {
               return false;
            }

            if (!mc.theWorld.isAirBlock(new BlockPos(fx + x, fy, fz + z))
                || !mc.theWorld.isAirBlock(new BlockPos(fx + x, fy + 1, fz + z))) {
               return false;
            }
         }
      }

      AxisAlignedBB box = p.getEntityBoundingBox()
         .offset(cx - p.posX, cy - p.posY, cz - p.posZ)
         .expand(CLEAR_R, 0.0, CLEAR_R);
      return mc.theWorld.getCollidingBoundingBoxes(p, box).isEmpty();
   }

   private void probeAim(Minecraft mc) {
      EntityPlayerSP p = mc.thePlayer;
      releaseAll(mc);

      if (this.anchorOk) {
         p.setPositionAndUpdate(this.anchorX, this.anchorY, this.anchorZ);
      }

      p.motionX = 0.0;
      p.motionY = 0.0;
      p.motionZ = 0.0;
      p.fallDistance = 0.0F;
      this.heldYaw = PROBE_YAWS[this.probe];
      p.rotationYaw = this.heldYaw;
      p.prevRotationYaw = this.heldYaw;
      p.rotationPitch = 0.0F;
      p.prevRotationPitch = 0.0F;
      this.settleTicks = 0;
      this.restStreak = 0;
      this.controlTicks = 0;
      this.controlDrift = 0.0;
      this.settleNote = null;
   }

   private boolean probeSettle(Minecraft mc) {
      EntityPlayerSP p = mc.thePlayer;
      releaseAll(mc);
      this.settleTicks++;

      if (this.controlTicks == 0) {
         boolean rest = Math.abs(p.motionX) < REST_MOTION
                        && Math.abs(p.motionZ) < REST_MOTION
                        && p.onGround;

         this.restStreak = rest ? this.restStreak + 1 : 0;

         if (this.restStreak >= REST_STREAK) {
            this.controlX = p.posX;
            this.controlZ = p.posZ;
            this.controlTicks = 1;
            return false;
         }

         if (this.settleTicks > SETTLE_MAX) {
            this.settleNote = "the player never came to rest in " + SETTLE_MAX
                              + " ticks with every key released (motionX=" + fmt(p.motionX)
                              + " motionZ=" + fmt(p.motionZ) + " onGround=" + p.onGround + ')';
            this.controlX = p.posX;
            this.controlZ = p.posZ;
            return true;
         }

         return false;
      }

      if (++this.controlTicks > CONTROL_TICKS) {
         double dx = p.posX - this.controlX;
         double dz = p.posZ - this.controlZ;
         this.controlDrift = Math.sqrt(dx * dx + dz * dz);
         return true;
      }

      return false;
   }

   private void beginWindow(Minecraft mc) {
      EntityPlayerSP p = mc.thePlayer;
      this.x0 = p.posX;
      this.y0 = p.posY;
      this.z0 = p.posZ;
      this.lastX = this.x0;
      this.lastZ = this.z0;
      this.wait = 0;
      this.pressTook = false;
      this.maxYawDev = 0.0;
      this.maxStrafe = 0.0;
      this.maxYDev = 0.0;
      this.stepErrMax = 0.0;
      this.steps = 0;
      this.stallTicks = 0;
      this.screenTicks = 0;
      this.collideTicks = 0;
      this.airTicks = 0;
      this.anchorMiss = this.anchorOk
         ? Math.sqrt((this.x0 - this.anchorX) * (this.x0 - this.anchorX)
                     + (this.z0 - this.anchorZ) * (this.z0 - this.anchorZ))
         : 0.0;
      holdForward(mc);
   }

   private void sampleWindow(Minecraft mc) {
      EntityPlayerSP p = mc.thePlayer;
      double dx = p.posX - this.lastX;
      double dz = p.posZ - this.lastZ;
      double step = Math.sqrt(dx * dx + dz * dz);

      if (step > 0.01) {
         double stepErr = Math.abs(wrap(Math.toDegrees(Math.atan2(-dx, dz)) - this.heldYaw));

         if (stepErr > this.stepErrMax) {
            this.stepErrMax = stepErr;
         }

         this.steps++;
      } else {
         this.stallTicks++;
      }

      this.lastX = p.posX;
      this.lastZ = p.posZ;

      double yawDev = Math.abs(wrap(p.rotationYaw - this.heldYaw));

      if (yawDev > this.maxYawDev) {
         this.maxYawDev = yawDev;
      }

      double yDev = Math.abs(p.posY - this.y0);

      if (yDev > this.maxYDev) {
         this.maxYDev = yDev;
      }

      if (mc.currentScreen != null) {
         this.screenTicks++;
      }

      if (p.isCollidedHorizontally) {
         this.collideTicks++;
      }

      if (!p.onGround) {
         this.airTicks++;
      }

      if (p.movementInput != null) {
         if (p.movementInput.moveForward != 0.0F) {
            this.pressTook = true;
         }

         double strafe = Math.abs(p.movementInput.moveStrafe);

         if (strafe > this.maxStrafe) {
            this.maxStrafe = strafe;
         }
      }
   }

   private void holdForward(Minecraft mc) {
      KeyBinding.setKeyBindState(mc.gameSettings.keyBindForward.getKeyCode(), true);
   }

   private void releaseAll(Minecraft mc) {
      KeyBinding.setKeyBindState(mc.gameSettings.keyBindForward.getKeyCode(), false);
      KeyBinding.unPressAllKeys();
   }

   private void endProbe(Minecraft mc) {
      EntityPlayerSP p = mc.thePlayer;
      releaseAll(mc);

      double dx = p.posX - this.x0;
      double dz = p.posZ - this.z0;
      double dist = Math.sqrt(dx * dx + dz * dz);

      StringBuilder b = new StringBuilder(this.probe == 0 ? "MOVE(warmup, not scored) yaw=" : "MOVE yaw=");
      b.append(fmt(this.heldYaw)).append(" key=W");

      String skip = skipReason(dist);

      if (skip != null) {
         this.skipCount += this.probe == 0 ? 0 : 1;
         b.append("  SKIPPED -- ").append(skip).append("; direction is NOT measured here").append(detail(mc, dist));
         say(b.toString());
         return;
      }

      double bearing = Math.toDegrees(Math.atan2(-dx, dz));
      double err = wrap(bearing - this.heldYaw);
      boolean ok = Math.abs(err) < 15.0;

      if (this.probe != 0) {
         if (ok) {
            this.okCount++;
         } else {
            this.wrongCount++;
         }
      }

      b.append("  moved=").append(fmt(dist))
       .append("  bearing=").append(fmt(bearing))
       .append("  err=").append(fmt(err))
       .append(ok ? "  OK" : "  WRONG");

      if (dist < 1.0) {
         b.append("  SHORT -- ").append(HOLD_TICKS)
          .append(" ticks of free walking is ~1.76 blocks and no control fired");
      }

      b.append(detail(mc, dist));
      say(b.toString());
   }

   private String skipReason(double dist) {
      if (!this.anchorOk) {
         return "no anchor with " + CLEAR_R + " blocks of clearance was found, so the walk would"
                + " have been shaped by terrain instead of by the yaw";
      }

      if (this.settleNote != null) {
         return this.settleNote;
      }

      if (this.anchorMiss > 0.05) {
         return "the player was " + fmt(this.anchorMiss) + " off the anchor when the window opened,"
                + " so the clearance that was verified is not the clearance he walked through";
      }

      if (this.controlDrift > CONTROL_DRIFT) {
         return "the negative control failed: the player moved " + fmt(this.controlDrift)
                + " during " + CONTROL_TICKS + " ticks with every key released, so displacement"
                + " under the key press cannot be attributed to the key press";
      }

      if (this.screenTicks > 0) {
         return "a GuiScreen was open for " + this.screenTicks + " of " + HOLD_TICKS
                + " window ticks and singleplayer stops ticking the world while one is open";
      }

      if (!this.pressTook) {
         return "the synthetic press never reached MovementInput (moveForward stayed 0)";
      }

      if (this.airTicks > 0 || this.maxYDev > Y_TOL) {
         return "the player left the ground: y changed by " + fmt(this.maxYDev) + " and he was"
                + " airborne for " + this.airTicks + " of " + HOLD_TICKS + " window ticks, so the"
                + " walk was clipped by a fall and not by the yaw";
      }

      if (this.collideTicks > 0) {
         return "the player collided horizontally on " + this.collideTicks + " of " + HOLD_TICKS
                + " window ticks, so a block clipped the walk and the surviving displacement is"
                + " the wall's direction, not the player's";
      }

      if (this.maxYawDev > YAW_TOL) {
         return "yaw moved " + fmt(this.maxYawDev) + " degrees inside the window, so the start and"
                + " end points were not taken at the same heading";
      }

      if (this.maxStrafe > STRAFE_TOL) {
         return "moveStrafing reached " + fmt(this.maxStrafe) + " although only the forward key was"
                + " pressed, so the walk direction is the sum of two inputs and not the yaw alone";
      }

      if (dist < MIN_DIST) {
         return "press arrived and every control was clean but the player moved only " + fmt(dist);
      }

      return null;
   }

   private String detail(Minecraft mc, double dist) {
      EntityPlayerSP p = mc.thePlayer;
      return new StringBuilder("  [dist=").append(fmt(dist))
         .append(" steps=").append(this.steps).append('/').append(HOLD_TICKS)
         .append(" stalled=").append(this.stallTicks)
         .append(" stepErrMax=").append(fmt(this.stepErrMax))
         .append(" yawDev=").append(fmt(this.maxYawDev))
         .append(" yDev=").append(fmt(this.maxYDev))
         .append(" air=").append(this.airTicks)
         .append(" collided=").append(this.collideTicks)
         .append(" screen=").append(this.screenTicks)
         .append(" strafeMax=").append(fmt(this.maxStrafe))
         .append(" ctrlDrift=").append(fmt(this.controlDrift))
         .append(" settle=").append(this.settleTicks)
         .append(" anchorMiss=").append(fmt(this.anchorMiss))
         .append(" at=").append(fmt(p.posX)).append(',').append(fmt(p.posY))
         .append(',').append(fmt(p.posZ))
         .append(" zT_3.G=").append(Expo.module.impl.visual.Freelook.G).append(" zT_3.N=").append(fmt(Expo.module.impl.visual.Freelook.N))
         .append(" oN.o=").append(RotationManager.o == null ? "null" : RotationManager.o.name())
         .append(" oN.V=").append(fmt(RotationManager.V))
         .append(" yaw=").append(fmt(p.rotationYaw))
         .append(" oN.I=").append(fmt(RotationManager.I))
         .append(" yawHead=").append(fmt(p.rotationYawHead))
         .append(" bodyYaw=").append(fmt(p.renderYawOffset))
         .append(" moveF=").append(p.movementInput == null ? "-" : fmt(p.movementInput.moveForward))
         .append(" moveS=").append(p.movementInput == null ? "-" : fmt(p.movementInput.moveStrafe))
         .append(']')
         .toString();
   }

   private boolean configBegin() {
      this.wait = 0;
      this.configSeen = ExpoConfig.saveCount;
      java.io.File f = ExpoConfig.target("current");

      if (this.configRound == 0 && this.configSeen != 0) {
         say("CONFIG ENV ExpoConfig.saveCount was already " + this.configSeen
             + " before the probe fired, so 'this trigger wrote the file' covers only the delta");
      }

      this.configSha = sha256(f);
      this.configKeys = topKeys(f);
      this.configModule = pickConfigModule(f);

      if (this.configModule == null) {
         this.configVerdict = "SKIPPED (no persistable module has a block in " + f.getPath() + ')';
         say("CONFIG SKIPPED -- " + this.configVerdict);
         return false;
      }

      this.configWant = !this.configModule.o();
      this.configModule.I(ExpoConfig.MODULE_I_CARRIER, this.configWant);
      say("CONFIG round " + this.configRound + " DRIVER set " + this.configModule.b()
          + ".status=" + this.configWant + " in memory; file=" + f.getPath()
          + " exists=" + f.isFile() + " sha256=" + this.configSha
          + " topLevelKeys=" + this.configKeys.size() + " saveCount=" + this.configSeen);

      try {
         Expo.module.Modules.c(AG4_C_CARRIER);
      } catch (Throwable t) {
         this.configFail++;
         this.configVerdict = "ag_4.c THREW " + ExpoDiag.describe(t);
         say("CONFIG round " + this.configRound + " FAIL -- " + this.configVerdict);
         ExpoDiag.attribute(t, "config-save-trigger");
         return false;
      }

      return true;
   }

   private void configReport() {
      java.io.File f = ExpoConfig.target("current");
      ExpoConfig.SaveResult r = ExpoConfig.lastResult;
      List<String> problems = new ArrayList<String>();

      if (r == null) {
         problems.add("ExpoConfig.lastResult is null although saveCount moved");
      } else {
         if (!r.ok) {
            problems.add("save reported not ok: " + r.note);
         }

         if (r.topLevelBefore + r.created != r.topLevelAfter) {
            problems.add("top level count " + r.topLevelBefore + '+' + r.created
                         + " != " + r.topLevelAfter);
         }
      }

      String shaAfter = sha256(f);

      if (shaAfter.equals(this.configSha)) {
         problems.add("file sha256 did not change: " + shaAfter);
      }

      if (sha256Flipped(f).equals(shaAfter)) {
         problems.add("the sha256 comparator cannot tell a one byte difference apart");
      }

      java.util.Set<String> keysAfter = topKeys(f);

      if (!keysAfter.containsAll(this.configKeys)) {
         java.util.Set<String> lost = new java.util.LinkedHashSet<String>(this.configKeys);
         lost.removeAll(keysAfter);
         problems.add("top level keys DELETED by the save: " + lost);
      }

      int clean = -1;
      int corrupted = -1;
      String first = null;

      if (r != null) {
         List<String> bad = ExpoConfig.verify(r, null);
         clean = bad.size();

         if (clean != 0) {
            problems.add("round trip mismatches: " + bad);
         }

         if (!r.written.isEmpty()) {
            first = r.written.get(0)[0] + "/" + r.written.get(0)[1];
            corrupted = ExpoConfig.verify(r, first).size();
         }

         if (corrupted != 1) {
            problems.add("negative control: a deliberately wrong expectation for " + first
                         + " produced " + corrupted + " mismatch(es) instead of 1, so the "
                         + clean + " above is not evidence");
         }
      }

      String onDisk = statusOnDisk(f, this.configModule.b());

      if (!String.valueOf(this.configWant).equals(onDisk)) {
         problems.add(this.configModule.b() + ".status on disk = " + onDisk
                      + ", the memory value written was " + this.configWant);
      }

      if (problems.isEmpty()) {
         this.configPass++;
         this.configVerdict = "PASS";
      } else {
         this.configFail++;
         this.configVerdict = "FAIL " + problems;
      }

      say("CONFIG round " + this.configRound + ' ' + (problems.isEmpty() ? "PASS" : "FAIL")
          + " -- " + (r == null ? "no SaveResult" : r.toString())
          + "  sha256 " + head16(this.configSha) + " -> " + head16(shaAfter)
          + "  roundTripMismatches=" + clean + " negativeControl=" + corrupted + "(must be 1)"
          + "  topLevelKeys " + this.configKeys.size() + " -> " + keysAfter.size()
          + "  " + this.configModule.b() + ".status onDisk=" + onDisk
          + "  configSaveUnavailable=" + Expo.module.Modules.gatesweep$configSaveUnavailable);

      if (r != null && r.loadGapsPreserved > 0) {
         say("CONFIG round " + this.configRound + " kept " + r.loadGapsPreserved
             + " file value(s) whose in-memory setting is still exactly what the boot"
             + " snapshot recorded, i.e. the load never applied them and nobody has touched"
             + " them since, so overwriting would destroy the user's value: " + r.loadGaps);
      }

      if (r != null && r.modeValuesPreserved > 0) {
         say("CONFIG round " + this.configRound + " kept " + r.modeValuesPreserved
             + " shipped mode value(s) the reconstructed option list cannot represent, rather"
             + " than overwriting them with the fallback the reader landed on: " + r.preserved);
      }

      if (r != null && r.settingsOutsideSchema > 0) {
         say("CONFIG round " + this.configRound + " left " + r.settingsOutsideSchema
             + " setting(s) unwritten because their name is not a key of the shipped block: "
             + r.outside);
      }

      for (String p : problems) {
         say("CONFIG problem: " + p);
      }
   }

   private static Module pickConfigModule(java.io.File f) {
      com.google.gson.JsonObject root = readJson(f);

      if (root == null || ModuleManager.S == null) {
         return null;
      }

      for (Module m : ModuleManager.S) {
         if (m == null || !ExpoModuleRegistry.isConfigPersistable(m)) {
            continue;
         }

         com.google.gson.JsonElement e = root.get(m.b());

         if (e != null && e.isJsonObject() && e.getAsJsonObject().has("status")) {
            return m;
         }
      }

      return null;
   }

   private static String statusOnDisk(java.io.File f, String module) {
      com.google.gson.JsonObject root = readJson(f);

      if (root == null) {
         return "<no file>";
      }

      com.google.gson.JsonElement e = root.get(module);

      if (e == null || !e.isJsonObject() || !e.getAsJsonObject().has("status")) {
         return "<absent>";
      }

      return String.valueOf(e.getAsJsonObject().get("status").getAsBoolean());
   }

   private static java.util.Set<String> topKeys(java.io.File f) {
      java.util.Set<String> out = new java.util.LinkedHashSet<String>();
      com.google.gson.JsonObject root = readJson(f);

      if (root != null) {
         for (java.util.Map.Entry<String, com.google.gson.JsonElement> e : root.entrySet()) {
            out.add(e.getKey());
         }
      }

      return out;
   }

   private static com.google.gson.JsonObject readJson(java.io.File f) {
      if (f == null || !f.isFile()) {
         return null;
      }

      java.io.Reader r = null;

      try {
         r = new java.io.InputStreamReader(new java.io.FileInputStream(f), "UTF-8");
         com.google.gson.JsonElement e = new com.google.gson.JsonParser().parse(r);
         return e != null && e.isJsonObject() ? e.getAsJsonObject() : null;
      } catch (Throwable t) {
         return null;
      } finally {
         if (r != null) {
            try {
               r.close();
            } catch (Throwable t) {
            }
         }
      }
   }

   private static byte[] bytes(java.io.File f) {
      if (f == null || !f.isFile()) {
         return new byte[0];
      }

      java.io.InputStream in = null;

      try {
         in = new java.io.FileInputStream(f);
         java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
         byte[] buf = new byte[8192];
         int k;

         while ((k = in.read(buf)) > 0) {
            out.write(buf, 0, k);
         }

         return out.toByteArray();
      } catch (Throwable t) {
         return new byte[0];
      } finally {
         if (in != null) {
            try {
               in.close();
            } catch (Throwable t) {
            }
         }
      }
   }

   private static String sha256(java.io.File f) {
      return sha256(bytes(f));
   }

   private static String sha256Flipped(java.io.File f) {
      byte[] b = bytes(f);

      if (b.length == 0) {
         return "empty";
      }

      b[b.length / 2] = (byte)(b[b.length / 2] ^ 1);
      return sha256(b);
   }

   private static String sha256(byte[] b) {
      try {
         byte[] d = java.security.MessageDigest.getInstance("SHA-256").digest(b);
         StringBuilder s = new StringBuilder();

         for (int i = 0; i < d.length; i++) {
            s.append(Character.forDigit((d[i] >> 4) & 15, 16));
            s.append(Character.forDigit(d[i] & 15, 16));
         }

         return s.toString();
      } catch (Throwable t) {
         return "unavailable";
      }
   }

   private static String head16(String s) {
      return s == null ? "null" : s.length() <= 16 ? s : s.substring(0, 16);
   }

   private void probeClickGui(Minecraft mc) {
      boolean world = mc.theWorld != null;
      boolean player = mc.thePlayer != null;

      if (mc.currentScreen != null) {
         closeAnyScreen(mc, "before ClickGUI probe");
      }

      boolean clear = mc.currentScreen == null;
      String mode = CGUI_MODES[this.cguiRound];
      say("CLICKGUI[" + mode + "] preconditions (the ones AZ.r itself satisfies): theWorld=" + world
          + " thePlayer=" + player + " currentScreen==null=" + clear
          + " (config Mode=" + (ClickGUI.mode == null ? "<null>" : ClickGUI.mode.Y()) + ')');

      if (!world || !player || !clear) {
         say("CLICKGUI SKIPPED -- the state AZ.r opens it from could not be established;"
             + " opening it anyway would measure a state the product never reaches");
         this.escVerdict = "SKIPPED (preconditions)";
         this.cguiVerdict[this.cguiRound] = "SKIPPED (preconditions)";
         return;
      }

      GuiScreen target = "RAVEN".equals(mode) ? ClickGUI.F : "VESTIGE".equals(mode) ? ClickGUI.B : ClickGUI.Y;
      say("CLICKGUI mode=" + mode + " dispatches to " + (target == null ? "NULL -- will close silently" : target.getClass().getName()));

      if (target == null) {
         this.escVerdict = "SKIPPED (no screen for mode " + mode + ')';
         this.cguiVerdict[this.cguiRound] = "NULL SCREEN";
         return;
      }

      mc.displayGuiScreen(target);

      if ("RAVEN".equals(mode)) {
         boolean primed = ExpoRavenGui.primeFirstFrame((Expo.ui.raven.RavenClickGuiScreen)target);
         say("CLICKGUI[RAVEN] primed Ad_2.w (what Ad_2.P(J)V does before the first frame): "
             + primed + "; panels=" + (Expo.ui.raven.RavenClickGuiScreen.P == null ? -1 : Expo.ui.raven.RavenClickGuiScreen.P.size())
             + " drawOrder=" + (Expo.ui.raven.RavenClickGuiScreen.h == null ? -1 : Expo.ui.raven.RavenClickGuiScreen.h.size())
             + " executor=" + (Expo.ui.raven.RavenClickGuiScreen.A == null ? "NULL" : "ok")
             + " moduleRows=" + ExpoRavenGui.rows + " settingRows=" + ExpoRavenGui.settingRows
             + " [" + ExpoRavenGui.rowTally + ']');

         int expanded = 0;

         for (Expo.ui.raven.RavenCategoryPanel p : Expo.ui.raven.RavenClickGuiScreen.P.values()) {
            try {
               p.l(true);
               expanded++;
            } catch (Throwable t) {
               say("CLICKGUI[RAVEN] could not expand a panel: " + ExpoDiag.describe(t));
            }
         }

         say("CLICKGUI[RAVEN] expanded " + expanded + " panel(s) so the frame renders their rows");
      }
   }

   private String sortVerdict = "not run";

   private static String sortedOrBad(String theme, java.util.Map<String, List<String>> byCat) {
      if (byCat.isEmpty()) {
         return theme + "=NO DATA";
      }

      int cats = 0;
      int names = 0;
      StringBuilder bad = new StringBuilder();

      for (java.util.Map.Entry<String, List<String>> e : byCat.entrySet()) {
         List<String> v = e.getValue();
         cats++;
         names += v.size();

         for (int i = 1; i < v.size(); i++) {
            if (v.get(i - 1).compareToIgnoreCase(v.get(i)) > 0) {
               bad.append(bad.length() == 0 ? "" : ", ").append(e.getKey()).append(':')
                  .append(v.get(i - 1)).append(" > ").append(v.get(i));
               break;
            }
         }
      }

      return theme + (bad.length() == 0
                      ? "=SORTED (" + cats + " categories, " + names + " names)"
                      : "=UNSORTED [" + bad + ']');
   }

   private static Object peek(Object owner, Class<?> cls, String field) throws Exception {
      java.lang.reflect.Field f = cls.getDeclaredField(field);
      f.setAccessible(true);
      return f.get(owner);
   }

   @SuppressWarnings("unchecked")
   private void probeSortOrder() {
      StringBuilder out = new StringBuilder();

      try {
         java.util.Map<String, List<String>> m = new java.util.LinkedHashMap<String, List<String>>();

         for (Expo.module.Category c : Expo.module.Category.values()) {
            Expo.ui.raven.RavenCategoryPanel p = Expo.ui.raven.RavenClickGuiScreen.P == null ? null : Expo.ui.raven.RavenClickGuiScreen.P.get(c);

            if (p != null && p.s() != null) {
               List<String> n = new ArrayList<String>();

               for (Expo.ui.raven.RavenModuleRow row : p.s()) {
                  n.add(row.R == null || row.R.b() == null ? "?" : row.R.b());
               }

               m.put(c.name(), n);
            }
         }

         out.append(sortedOrBad("RAVEN", m));
      } catch (Throwable t) {
         out.append("RAVEN=FAIL ").append(ExpoDiag.describe(t));
      }

      try {
         Object e = peek(null, Expo.ui.vestige.VestigeClickGuiScreen.class, "E");
         java.util.Map<String, List<String>> m = new java.util.LinkedHashMap<String, List<String>>();

         for (java.util.Map.Entry<?, ?> en : ((java.util.Map<?, ?>)e).entrySet()) {
            List<String> n = new ArrayList<String>();

            for (Object o : (List<Object>)en.getValue()) {
               Expo.module.Module mod = (Expo.module.Module)o;
               n.add(mod == null || mod.b() == null ? "?" : mod.b());
            }

            m.put(String.valueOf(en.getKey()), n);
         }

         out.append("  ").append(sortedOrBad("VESTIGE", m));
      } catch (Throwable t) {
         out.append("  VESTIGE=FAIL ").append(ExpoDiag.describe(t));
      }

      try {
         if (ClickGUI.Y == null) {
            out.append("  STUDIO=FAIL zu_3.Y is null");
         } else {
            List<Object> panels = (List<Object>)peek(ClickGUI.Y, Expo.ui.studio.StudioClickGuiScreen.class, "k");
            java.util.Map<String, List<String>> m = new java.util.LinkedHashMap<String, List<String>>();

            for (Object panel : panels) {
               Object cat = peek(panel, Expo.ui.studio.StudioNotification.class, "s");
               List<Object> rows = (List<Object>)peek(panel, Expo.ui.studio.StudioNotification.class, "J");
               List<String> n = new ArrayList<String>();

               for (Object row : rows) {
                  Expo.module.Module mod = (Expo.module.Module)peek(row, Expo.ui.studio.StudioModuleFrame.class, "M");
                  n.add(mod == null || mod.b() == null ? "?" : mod.b());
               }

               m.put(String.valueOf(cat), n);
            }

            out.append("  ").append(sortedOrBad("STUDIO", m));
         }
      } catch (Throwable t) {
         out.append("  STUDIO=FAIL ").append(ExpoDiag.describe(t));
      }

      this.sortVerdict = out.toString();
      say("CLICKGUI ORDER " + this.sortVerdict);
      say("CLICKGUI ORDER note: tD.S is left in registration order on purpose (ExpoConfig.save"
          + " walks it); the sort is a window around each theme's read only.  tD.S size="
          + (ModuleManager.S == null ? -1 : ModuleManager.S.size()) + " windows=" + ExpoClickGui.displaySortWindows
          + " clash=" + ExpoClickGui.displaySortClash);
   }

   private void reportClickGui(Minecraft mc) {
      GuiScreen open = mc.currentScreen;
      String mode = CGUI_MODES[this.cguiRound];
      say("CLICKGUI[" + mode + "] after open: screen=" + screenName(open));

      if (open == null) {
         if ("not run".equals(this.escVerdict)) {
            say("CLICKGUI FAIL -- the screen closed itself; nothing is rendered");
            this.escVerdict = "SKIPPED (screen closed itself)";
         }

         if ("not run".equals(this.cguiVerdict[this.cguiRound])) {
            this.cguiVerdict[this.cguiRound] = "SCREEN CLOSED ITSELF";
         }

         return;
      }

      boolean drew;
      try {
         open.drawScreen(open.width / 2, open.height / 2, 0.0F);
         say("CLICKGUI[" + mode + "] drawScreen OK -- one frame rendered without throwing");
         drew = true;
      } catch (Throwable t) {
         say("CLICKGUI[" + mode + "] drawScreen THREW " + ExpoDiag.describe(t));
         ExpoDiag.attribute(t, "clickgui-draw-" + mode);
         drew = false;
      }

      this.cguiVerdict[this.cguiRound] = (drew ? "DREW" : "DRAW THREW") + " (" + screenName(open) + ')';

      boolean mouseHelperNull = isNullField(mc, "mouseHelper");
      boolean playerNull = mc.thePlayer == null;
      boolean worldNull = mc.theWorld == null;
      String health = playerNull ? "-" : fmt(mc.thePlayer.getHealth());
      say("CLICKGUI close preconditions: mouseHelper=" + (mouseHelperNull ? "MOUSEHELPER_NULL" : "ok")
          + " thePlayer=" + (playerNull ? "PLAYER_NULL" : "ok")
          + " theWorld=" + (worldNull ? "WORLD_NULL" : "ok")
          + " inGameHasFocus=" + readBool(mc, "inGameHasFocus")
          + " health=" + health);
      say("CLICKGUI ESC is a regression watch, not an open defect: the two keyTyped bodies"
          + " are instruction-identical and pass a constant null, so a throw here is vanilla"
          + " displayGuiScreen reacting to this launch's state -- which is what the four"
          + " values above are for.  Do not re-file it without reading them.");

      try {
         keyTyped(open, ' ', 1);
         boolean closed = mc.currentScreen == null;
         this.escVerdict = closed ? "CLOSED" : "STAYED OPEN (" + screenName(mc.currentScreen) + ')';
         say("CLICKGUI[" + mode + "] ESC -> " + this.escVerdict);
         this.cguiVerdict[this.cguiRound] += " ESC=" + (closed ? "CLOSED" : "STAYED OPEN");
      } catch (Throwable t) {
         String cause;

         if (mouseHelperNull) {
            cause = "mouseHelper was null BEFORE the key was sent -- that is a property of this"
                    + " launch, not of the product; a client that reached the main menu normally"
                    + " has one, so ESC is not shown to be broken by this";
         } else if (playerNull) {
            cause = "thePlayer was null with theWorld non-null -- vanilla displayGuiScreen(null)"
                    + " dereferences thePlayer.getHealth() on exactly that combination, so this"
                    + " IS reachable for a user who presses ESC before the player exists";
         } else {
            cause = "UNDETERMINED -- all four preconditions were satisfied, so this is neither"
                    + " explained by the launch environment nor by the known null-player path";
         }

         this.escVerdict = "THREW: " + ExpoDiag.describe(t);
         this.cguiVerdict[this.cguiRound] += " ESC=THREW " + ExpoDiag.describe(t);
         say("CLICKGUI[" + mode + "] ESC THREW " + ExpoDiag.describe(t));
         say("CLICKGUI ESC verdict: " + cause);
         ExpoDiag.attribute(t, "clickgui-esc");
      }

      if (mc.currentScreen != null) {
         safeCloseScreen(mc);
      }
   }

   private void closeAnyScreen(Minecraft mc, String when) {
      GuiScreen s = mc.currentScreen;

      if (s == null) {
         return;
      }

      this.closedScreens++;
      say("ENV closed screen " + when + ": " + s.getClass().getName());
      safeCloseScreen(mc);
   }

   private static void keyTyped(GuiScreen screen, char c, int keyCode) throws Throwable {
      java.lang.reflect.Method m = GuiScreen.class.getDeclaredMethod("keyTyped", char.class, int.class);
      m.setAccessible(true);

      try {
         m.invoke(screen, Character.valueOf(c), Integer.valueOf(keyCode));
      } catch (java.lang.reflect.InvocationTargetException e) {
         throw e.getCause() == null ? e : e.getCause();
      }
   }

   private void safeCloseScreen(Minecraft mc) {
      try {
         mc.displayGuiScreen(null);
      } catch (Throwable t) {
         say("DRIVER displayGuiScreen(null) THREW " + ExpoDiag.describe(t));
         ExpoDiag.attribute(t, "driver-close-screen");
      }
   }

   private void reportBootstrap() {
      say("main menu reached; modules=" + (ModuleManager.S == null ? -1 : ModuleManager.S.size())
          + " enabled=" + enabledCount()
          + " zu_3.Y=" + (ClickGUI.Y == null ? "null" : "live")
          + " zu_3.B=" + (ClickGUI.B == null ? "null" : "live")
          + " zu_3.F=" + (ClickGUI.F == null ? "null" : "live"));
      reportSettingProbes();
   }

   private void reportWorld(Minecraft mc) {
      say("in world: pos=" + fmt(mc.thePlayer.posX) + "," + fmt(mc.thePlayer.posY)
          + "," + fmt(mc.thePlayer.posZ) + " enabled=" + enabledCount());
   }

   private static final List<String> PROBE_COMMON =
      Arrays.asList("status", "keyBind", "visible", "suffix-visible");

   private static void collectStatics(Class<?> c, Map<String, Setting> out) {
      for (Class<?> k = c; k != null && Module.class.isAssignableFrom(k); k = k.getSuperclass()) {
         for (Field f : k.getDeclaredFields()) {
            if (!Modifier.isStatic(f.getModifiers())
                || !Setting.class.isAssignableFrom(f.getType())
                || out.containsKey(f.getName())) {
               continue;
            }

            try {
               f.setAccessible(true);
               out.put(f.getName(), (Setting)f.get(null));
            } catch (Throwable t) {
               out.put(f.getName(), null);
            }
         }
      }
   }

   private static String labelOf(Setting s) {
      try {
         return s == null ? null : s.B();
      } catch (Throwable t) {
         return null;
      }
   }

   private static String sha256(String s) {
      try {
         byte[] d = MessageDigest.getInstance("SHA-256").digest(s.getBytes("UTF-8"));
         StringBuilder b = new StringBuilder(d.length * 2);

         for (byte x : d) {
            b.append(Character.forDigit((x >> 4) & 0xF, 16));
            b.append(Character.forDigit(x & 0xF, 16));
         }

         return b.toString();
      } catch (Throwable t) {
         return "sha256-unavailable";
      }
   }

   private static JsonObject probeBlock(JsonObject cfg, Module m) {
      if (cfg == null) {
         return null;
      }

      String n;

      try {
         n = m.b();
      } catch (Throwable t) {
         return null;
      }

      if (n == null || n.startsWith(ExpoModuleRegistry.PLACEHOLDER_PREFIX)) {
         return null;
      }

      JsonElement e = cfg.get(n);
      return e != null && e.isJsonObject() ? e.getAsJsonObject() : null;
   }

   private static void reportSettingProbes() {
      JsonObject cfg;

      try {
         cfg = ExpoConfig.read();
      } catch (Throwable t) {
         cfg = null;
      }

      int classes = 0;
      int fields = 0;
      int header = 0;
      int iskey = 0;
      int notkey = 0;
      int noblock = 0;
      int nullField = 0;
      int panels = 0;
      int rows = 0;
      int unnamedRow = 0;
      List<String> notkeys = new ArrayList<String>();
      StringBuilder agg = new StringBuilder();
      List<String> perModule = new ArrayList<String>();

      if (ModuleManager.S != null) {
         for (Module m : ModuleManager.S) {
            if (m == null) {
               continue;
            }

            Map<String, Setting> declared = new LinkedHashMap<String, Setting>();
            collectStatics(m.getClass(), declared);

            if (!declared.isEmpty()) {
               classes++;
            }

            String mod;

            try {
               mod = m.b();
            } catch (Throwable t) {
               mod = m.getClass().getSimpleName();
            }

            JsonObject block = probeBlock(cfg, m);

            for (Map.Entry<String, Setting> en : declared.entrySet()) {
               fields++;
               Setting s = en.getValue();

               if (s == null) {
                  nullField++;
                  continue;
               }

               String label = labelOf(s);

               if (s instanceof HeaderSetting || label == null || label.length() == 0) {
                  header++;
                  continue;
               }

               if (block == null) {
                  noblock++;
                  continue;
               }

               if (block.has(label) && !PROBE_COMMON.contains(label)) {
                  iskey++;
               } else {
                  notkey++;
                  notkeys.add(mod + "." + en.getKey());
               }
            }

            Map<Setting, String> fieldOf = new IdentityHashMap<Setting, String>();

            for (Map.Entry<String, Setting> en : declared.entrySet()) {
               if (en.getValue() != null && !fieldOf.containsKey(en.getValue())) {
                  fieldOf.put(en.getValue(), en.getKey());
               }
            }

            List<Setting> live;

            try {
               live = m.w();
            } catch (Throwable t) {
               live = null;
            }

            if (live == null) {
               continue;
            }

            panels++;
            StringBuilder per = new StringBuilder();

            for (int i = 0; i < live.size(); i++) {
               Setting s = live.get(i);
               String f = s == null ? null : fieldOf.get(s);

               if (f == null) {
                  unnamedRow++;
                  f = "?";
               }

               rows++;
               per.append(i).append(':').append(f).append(':').append(labelOf(s)).append('\n');
            }

            String h = sha256(per.toString());
            perModule.add(mod + " " + h.substring(0, 12) + " n=" + live.size());
            agg.append(mod).append('=').append(h).append('\n');
         }
      }

      java.util.Collections.sort(notkeys);
      java.util.Collections.sort(perModule);

      say("G2 label-key join: classes=" + classes + " fields=" + fields + " header=" + header
          + " iskey=" + iskey + " notkey=" + notkey + " noblock=" + noblock
          + " nullField=" + nullField + " accounted="
          + (header + iskey + notkey + noblock + nullField));
      say("G2 notkey SET = " + notkeys);
      say("G3 order fingerprint: panels=" + panels + " rows=" + rows
          + " unnamedRow=" + unnamedRow + " AGGREGATE=" + sha256(agg.toString()));

      for (String line : perModule) {
         say("G3 ORDER " + line);
      }
   }

   private static int enabledCount() {
      if (ModuleManager.S == null) {
         return -1;
      }

      int n = 0;

      for (Module m : ModuleManager.S) {
         if (m != null && m.o()) {
            n++;
         }
      }

      return n;
   }

   private static boolean isNullField(Object owner, String srg) {
      try {
         Field f = owner.getClass().getDeclaredField(srg);
         f.setAccessible(true);
         return f.get(owner) == null;
      } catch (Throwable t) {
         return false;
      }
   }

   private static String readBool(Object owner, String srg) {
      try {
         Field f = owner.getClass().getDeclaredField(srg);
         f.setAccessible(true);
         return String.valueOf(f.get(owner));
      } catch (Throwable t) {
         return "<unreadable>";
      }
   }

   private static String screenName(GuiScreen s) {
      return s == null ? "null" : s.getClass().getName();
   }

   private static double wrap(double deg) {
      while (deg <= -180.0) {
         deg += 360.0;
      }

      while (deg > 180.0) {
         deg -= 360.0;
      }

      return deg;
   }

   private static String fmt(double v) {
      return String.format("%.3f", v);
   }

   private static void say(String line) {
      LOG.add(line);
      System.out.println("[EXPOTEST] " + line);
   }






   private void ok(String label, Object detail) {
      say("SEEDTRIG " + label + " OK " + detail);
   }

   private void bad(String label, Throwable t) {
      say("SEEDTRIG " + label + " THREW " + ExpoDiag.describe(t)
          + " -- the class initialiser runs before the body, so a throw here does not by itself"
          + " mean the seed was missed; the coverage line below is the measurement");
   }

   private void siteTapControl() {
      say("SITECTL a run that records nothing is indistinguishable from a tap that is not wired,"
          + " so four of the instrumented sites are now driven on purpose with a carrier the"
          + " harness invented.  every row they produce is prefixed FORCED! and its value is NOT"
          + " the product value -- it is only evidence that the wiring records what it reaches."
          + "  the other twenty two instrumented sites are deliberately left alone: if they stay"
          + " absent while these appear, absence means unreached");

      try {
         Expo.util.MoveUtil.v(0L);
         say("SITECTL MoveUtil.v(0L) returned");
      } catch (Throwable t) {
         say("SITECTL MoveUtil.v(0L) THREW " + ExpoDiag.describe(t));
      }

      try {
         Expo.util.MoveUtil.V(0, (char)0, 0, true);
         say("SITECTL MoveUtil.V(0,0,0,true) returned");
      } catch (Throwable t) {
         say("SITECTL MoveUtil.V(0,0,0,true) THREW " + ExpoDiag.describe(t));
      }

      try {
         say("SITECTL ClickGUI.r(0L) returned " + ClickGUI.r(0L));
      } catch (Throwable t) {
         say("SITECTL ClickGUI.r(0L) THREW " + ExpoDiag.describe(t));
      }

      try {
         Expo.internal.MiningBlockScanner.F(0, 0, 0);
         say("SITECTL MiningBlockScanner.F(0,0,0) returned");
      } catch (Throwable t) {
         say("SITECTL MiningBlockScanner.F(0,0,0) THREW " + ExpoDiag.describe(t));
      }

   }

   private static final int CLICK_STEP = 5;

   private void clickEveryScreen(Minecraft mc) {
      GuiScreen[] guis = {ClickGUI.Y, ClickGUI.B, ClickGUI.F};
      String[] names = {"STUDIO", "VESTIGE", "RAVEN"};

      for (int g = 0; g < guis.length; g++) {
         if (guis[g] == null) {
            say("SEEDTRIG click " + names[g] + " SKIPPED -- screen is null");
            continue;
         }

         int clicks = 0;
         int threw = 0;

         try {
            mc.displayGuiScreen(guis[g]);
            guis[g].drawScreen(0, 0, 0.0F);
         } catch (Throwable t) {
            say("SEEDTRIG click " + names[g] + " could not draw a first frame: " + ExpoDiag.describe(t));
         }

         int w = guis[g].width;
         int h = guis[g].height;

         for (int y = 0; y < h; y += CLICK_STEP) {
            for (int x = 0; x < w; x += CLICK_STEP) {
               try {
                  mouseClicked(guis[g], x, y, 0);
                  clicks++;
               } catch (Throwable t) {
                  threw++;
               }

               try {
                  mouseReleased(guis[g], x, y, 0);
               } catch (Throwable t) {
               }
            }
         }

         say("SEEDTRIG click " + names[g] + " sent " + clicks + " left clicks over a " + w + 'x' + h
             + " grid at step " + CLICK_STEP + "; " + threw + " threw."
             + "  this is the only path that reaches the mouse handlers, and those handlers are where"
             + " the remaining carrier rooted sites live");
         safeCloseScreen(mc);
      }
   }

   private static void mouseClicked(GuiScreen screen, int x, int y, int button) throws Throwable {
      java.lang.reflect.Method m = GuiScreen.class.getDeclaredMethod("mouseClicked", int.class, int.class, int.class);
      m.setAccessible(true);

      try {
         m.invoke(screen, Integer.valueOf(x), Integer.valueOf(y), Integer.valueOf(button));
      } catch (java.lang.reflect.InvocationTargetException e) {
         throw e.getCause() == null ? e : e.getCause();
      }
   }

   private static void mouseReleased(GuiScreen screen, int x, int y, int button) throws Throwable {
      java.lang.reflect.Method m = GuiScreen.class.getDeclaredMethod("mouseReleased", int.class, int.class, int.class);
      m.setAccessible(true);

      try {
         m.invoke(screen, Integer.valueOf(x), Integer.valueOf(y), Integer.valueOf(button));
      } catch (java.lang.reflect.InvocationTargetException e) {
         throw e.getCause() == null ? e : e.getCause();
      }
   }


   private void flush() {
      StringBuilder b = new StringBuilder("\n[EXPOTEST] ==== summary ====\n");

      for (String line : LOG) {
         b.append("[EXPOTEST] ").append(line).append('\n');
      }

      b.append("[EXPOTEST] MOVE tally: ").append(this.okCount).append(" OK / ")
       .append(this.wrongCount).append(" WRONG / ").append(this.skipCount).append(" SKIPPED of ")
       .append(PROBE_YAWS.length - 1).append(" scored probes (the warmup is not counted);")
       .append(" anchor=").append(this.anchorOk ? this.anchorNote : "NONE -- " + this.anchorNote)
       .append('\n');
      b.append("[EXPOTEST] screens closed by the harness: ").append(this.closedScreens)
       .append("   ClickGUI ESC: ").append(this.escVerdict).append('\n');
      for (int i = 0; i < CGUI_MODES.length; i++) {
         b.append("[EXPOTEST] CLICKGUI ").append(CGUI_MODES[i]).append(": ")
          .append(this.cguiVerdict[i]).append('\n');
      }

      b.append("[EXPOTEST] CLICKGUI RAVEN detail: zu_3.F=")
       .append(Expo.module.impl.configuration.ClickGUI.F == null ? "NULL" : "Ad_2")
       .append(" panels=").append(Expo.ui.raven.RavenClickGuiScreen.P == null ? -1 : Expo.ui.raven.RavenClickGuiScreen.P.size())
       .append(" drawOrder=").append(Expo.ui.raven.RavenClickGuiScreen.h == null ? -1 : Expo.ui.raven.RavenClickGuiScreen.h.size())
       .append(" executor=").append(Expo.ui.raven.RavenClickGuiScreen.A == null ? "NULL" : "ok")
       .append(" moduleRows=").append(ExpoRavenGui.rows)
       .append(" settingRows=").append(ExpoRavenGui.settingRows)
       .append(" [").append(ExpoRavenGui.rowTally).append("]\n");

      for (String line : ExpoRavenGui.degraded()) {
         b.append("[EXPOTEST] RAVEN DEGRADED: ").append(line).append('\n');
      }

      b.append("[EXPOTEST] CLICKGUI ORDER: ").append(this.sortVerdict).append('\n');
      b.append("[EXPOTEST] CONFIG save round trip: ").append(this.configPass).append(" PASS / ")
       .append(this.configFail).append(" FAIL of ").append(CONFIG_ROUNDS)
       .append(" rounds; last verdict = ").append(this.configVerdict)
       .append("; ExpoConfig.saveCount=").append(ExpoConfig.saveCount)
       .append(' ').append(ExpoConfig.lastSaveNote).append('\n');
      b.append("[EXPOTEST] ==== end ====");
      System.out.println(b);
      exitAfterReport();
   }

   private static void exitAfterReport() {
      if ("0".equals(System.getProperty("expo.selftest.exit"))) {
         System.out.println("[EXPOTEST] staying up: -Dexpo.selftest.exit=0");
         return;
      }

      try {
         System.out.println("[EXPOTEST] selftest complete -- shutting the client down");
         System.out.flush();
         Minecraft.getMinecraft().shutdown();
      } catch (Throwable t) {
         System.out.println("[EXPOTEST] shutdown threw " + ExpoDiag.describe(t)
                            + " -- close the window by hand");
      }
   }
}
