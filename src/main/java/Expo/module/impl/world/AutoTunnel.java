package Expo.module.impl.world;

import Expo.module.Category;

import Expo.internal.MiningEngine;
import Expo.module.Module;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.ModeSetting;
import Expo.setting.settings.NumberSetting;
import Expo.setting.settings.PercentageSetting;
import Expo.util.KeyBindUtil;
import Expo.util.RotationManager;
import Expo.util.TunnelEngine;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

public class AutoTunnel extends Module {
   public static NumberSetting stuckTimeout;
   public static NumberSetting noBreakTimeout;
   public static BooleanSetting autoBack;
   public static BooleanSetting autoTurn;
   public static BooleanSetting sideOffsetScan;
   private static final long a = 55471329010780L;
   public static ModeSetting mode;
   public static NumberSetting chestScanRadius;
   public static BooleanSetting ownedChestsOnly;
   public static PercentageSetting unsneakChance;
   public static BooleanSetting autoTool;
   public static NumberSetting turnSpeed;
   // update new version
   public static BooleanSetting userManualScreenMove;
   public static NumberSetting unsneakDuration;
   // update new version
   public static BooleanSetting gapAltOnlyStone;
   public static ModeSetting sneakMode;
   public static PercentageSetting rotationSmoothing;

   static {
      a();
   }

   public AutoTunnel(long var1) {
      super(((a ^ (var1)) ^ 40733195189558L));
      this.declare("AutoTunnel", Category.World, "Automatically mine tunnel in MegaWalls");
      var1 = a ^ var1;
   }

   public void A(long var1) {
      long var3 = var1 ^ 64012210233546L;
      long var5 = (var1 ^ 3287147575080L) >>> 32;
      int var7 = (int)((var1 ^ 3287147575080L) << 32 >>> 32);
      long var8 = var1 ^ 17179273251418L;
      MiningEngine.uq.B(var5, var7);
      RotationManager.O(var3);
      KeyBindUtil.o(var8, f.gameSettings.keyBindForward.getKeyCode());
      KeyBindUtil.o(var8, f.gameSettings.keyBindBack.getKeyCode());
      KeyBindUtil.o(var8, f.gameSettings.keyBindAttack.getKeyCode());
      KeyBindUtil.o(var8, f.gameSettings.keyBindJump.getKeyCode());
      KeyBindUtil.o(var8, f.gameSettings.keyBindSneak.getKeyCode());
   }

   private static void a() {
   }

   public void i(long var1) throws UnsupportedEncodingException, Throwable, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      int var3 = (int)((var1 ^ 121923845494971L) >>> 48);
      long var4 = (var1 ^ 121923845494971L) << 16 >>> 16;
      long var8 = var1 ^ 112617634305256L;
      TunnelEngine.V(0L);
      MiningEngine.uq.a(var8);
      if (!MiningEngine.uq.h()) {
         this.u((short)var3, var4);
      }
   }

   public String g(long var1) {
      return mode.Y();
   }
   static {
      stuckTimeout = new NumberSetting("Stuck-timeout", 5.0F, 1.0F, 20.0F, 0.1F);
      sideOffsetScan = new BooleanSetting("Side-offset-scan", false);
      noBreakTimeout = new NumberSetting("No-break-timeout", 5.0F, 1.0F, 20.0F, 0.1F);
      sneakMode = new ModeSetting("Sneak-mode", false, "NONE", "RANDOM", "KEEP", "RANDOM", "NONE");
      mode = new ModeSetting("Mode", "STAIRCASE", "NORMAL", "STAIRCASE", "GAP_ALT");
      unsneakChance = new PercentageSetting("Unsneak-chance", 60);
      chestScanRadius = new NumberSetting("Chest-scan-radius", 50.0F, 1.0F, 100.0F, 1.0F);
      autoTurn = new BooleanSetting("Auto-turn", true);
      autoTool = new BooleanSetting("Auto-tool", true);
      turnSpeed = new NumberSetting("Turn-speed", 80.0F, 1.0F, 180.0F, 1.0F);
      autoBack = new BooleanSetting("Auto-back", false);
      unsneakDuration = new NumberSetting("Unsneak-duration", 150.0F, 0.0F, 1000.0F, 1.0F);
      ownedChestsOnly = new BooleanSetting("Owned-chests-only", true);
   }
   static {
      rotationSmoothing = new PercentageSetting("Rotation-smoothing", 100);
   }
   static {
      // update new version
      gapAltOnlyStone = new BooleanSetting("GapAlt-only-stone", false);
      // update new version
      userManualScreenMove = new BooleanSetting("User-manual-screen-move", true);
   }
}
