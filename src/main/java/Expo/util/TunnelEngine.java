package Expo.util;

import Expo.ExpoClient;
import Expo.internal.BrokenBlockTracker;
import Expo.internal.MiningEngine;
import Expo.internal.MiningRenderSubscriber;
import Expo.module.impl.world.AutoTunnel;











public class TunnelEngine {
   private static long a;
   private static long[] e;
   private static boolean j;
   private static String[] c;

   public static void z(long var0) {
      long var2 = var0 ^ 2562026071604L;
      if (!j) {
         j = true;
         ExpoClient.w.s(MiningEngine.uq, var2);
         ExpoClient.w.s(AutoToolService.K, var2);
         ExpoClient.w.s(BrokenBlockTracker.m, var2);
         ExpoClient.w.s(new MiningRenderSubscriber(), var2);
      }
   }



   public static void V(long var0) {
      if (AutoTunnel.mode != null && AutoTunnel.sneakMode != null) {
         switch (AutoTunnel.mode.Y()) {
            case "NORMAL":
               MiningConstants.J = 0;
               MiningConstants.v = false;
               break;
            case "GAP_ALT":
               MiningConstants.J = 2;
               MiningConstants.v = false;
               break;
            case "STAIRCASE":
            default:
               MiningConstants.J = 1;
               MiningConstants.v = true;
         }

         switch (AutoTunnel.sneakMode.Y()) {
            case "KEEP":
               MiningConstants.w = 0;
               break;
            case "NONE":
               MiningConstants.w = 2;
               break;
            case "RANDOM":
            default:
               MiningConstants.w = 1;
         }

         MiningConstants.r = AutoTunnel.autoTool != null && AutoTunnel.autoTool.c();
         MiningConstants.A = AutoTunnel.autoTurn != null && AutoTunnel.autoTurn.c();
         MiningConstants.x = AutoTunnel.autoBack != null && AutoTunnel.autoBack.c();
         MiningConstants.k = AutoTunnel.ownedChestsOnly != null && AutoTunnel.ownedChestsOnly.c();
         MiningConstants.q = AutoTunnel.sideOffsetScan != null && AutoTunnel.sideOffsetScan.c();
         MiningConstants.C = AutoTunnel.turnSpeed == null ? MiningConstants.C : AutoTunnel.turnSpeed.L();
         MiningConstants.X = AutoTunnel.stuckTimeout == null ? MiningConstants.X : AutoTunnel.stuckTimeout.L();
         MiningConstants.s = AutoTunnel.noBreakTimeout == null ? MiningConstants.s : AutoTunnel.noBreakTimeout.L();
         MiningConstants.Q = AutoTunnel.unsneakChance == null ? MiningConstants.Q : AutoTunnel.unsneakChance.k();
         MiningConstants.e = AutoTunnel.unsneakDuration == null ? MiningConstants.e : AutoTunnel.unsneakDuration.L();
         MiningConstants.H = AutoTunnel.chestScanRadius == null ? MiningConstants.H : AutoTunnel.chestScanRadius.L();
      }
   }

   static {
      a = 41608055129119L;
      j = false;
   }


}
