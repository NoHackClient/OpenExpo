package Expo.module.impl.world;

import Expo.module.Category;

import Expo.enums.RotationMode;
import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.NukerBinder;
import Expo.event.events.HeldItemChangeEvent;
import Expo.event.events.PreMouseInputEvent;
import Expo.event.events.SendPacketEvent;
import Expo.module.PriorityModule;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.ModeSetting;
import Expo.setting.settings.NumberSetting;
import Expo.util.BlockUtil;
import Expo.util.CombatUtil;
import Expo.util.ItemUtil;
import Expo.util.MathUtil;
import Expo.util.RaytraceUtil;
import Expo.util.RotationManager;
import Expo.util.RotationUtil;
import Expo.util.TimerUtil;
import Expo.util.packet.PacketManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.client.C07PacketPlayerDigging.Action;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C0APacketAnimation;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

public class Nuker extends PriorityModule implements EventSubscriber {
   public static BooleanSetting swing;
   public static NumberSetting range;
   private int U;
   private boolean K;
   private static long[] h;
   private static Map m;
   private boolean g;
   private BlockPos o;
   private static Object[] n;
   private static String[] d;
   private final TimerUtil v;
   private static long b;
   public static BooleanSetting mineDown;
   public static ModeSetting moveFix;
   private static Map e;
   private static String[] p;
   private NukerScanState C;

   public void onSendPacket(SendPacketEvent var1, long var2, short var4) {
      long var5 = (var2 << 16 | (long)var4 << 48 >>> 48) ^ b;
      int var7 = (int)((var5 ^ 120731832820803L) >>> 32);
      long var8 = (var5 ^ 120731832820803L) << 32 >>> 32;
      if (var1.B instanceof C07PacketPlayerDigging && ((C07PacketPlayerDigging)var1.B).getStatus() == Action.ABORT_DESTROY_BLOCK) {
         var1.I(var7, var8);
      }
   }

   public void A(long var1) {
      long var3 = var1 ^ 35715991659646L;
      this.resetBlockRemoving(var3);
      this.C = null;
   }

   private List<Integer> x(int var1) {
      ArrayList var2 = new ArrayList();
      var2.add(0);

      for (int var3 = 1; var3 <= var1; var3++) {
         var2.add(var3);
      }

      if (mineDown.c()) {
         var2.add(-1);
      }

      return var2;
   }

   public String g(long var1) {
      return String.valueOf((int)range.L());
   }

   public Nuker(int var1, int var2, byte var3) {
      super((((((((long)((var1)) << 32) | (((long)((var2)) << 40) >>> 32)) | (((long)((var3)) << 56) >>> 56)) ^ b) ^ 62759715954982L) >>> 16), (char)((int)(((((((((long)((var1)) << 32) | (((long)((var2)) << 40) >>> 32)) | (((long)((var3)) << 56) >>> 56)) ^ b) ^ 62759715954982L) << 48) >>> 48))));
      this.declare("Nuker", Category.World, "Mine blocks around you");
      long var4 = ((long)var1 << 32 | (long)var2 << 40 >>> 32 | (long)var3 << 56 >>> 56) ^ b;
      this.v = new TimerUtil();
      this.g = ((0 & 1) != 0);
      this.K = ((0 & 1) != 0);
      this.U = -1;
      this.o = null;
      this.C = null;
   }

   private BlockPos e(long var1, NukerScanState var3) {
      long var4 = var1 ^ 47041921737952L;
      int var6 = (int)Math.ceil(range.L());

      for (int var7 = 0; var7 <= var6; var7++) {
         BlockPos var8 = NukerScanState.w(var3)
            ? new BlockPos(NukerScanState.m(var3) + NukerScanState.Y(var3) * var7, NukerScanState.x(var3), NukerScanState.n(var3))
            : new BlockPos(NukerScanState.n(var3), NukerScanState.x(var3), NukerScanState.m(var3) + NukerScanState.Y(var3) * var7);
         if (this.isBlockLoaded(var8, var4)) {
            return var8;
         }
      }

      return null;
   }

   public final void x(long var1, EventBus var3) {
      NukerBinder.p(var3, this);
   }

   public void onPreMouseInput(PreMouseInputEvent var1, int var2, byte var3, int var4) {
      long var5 = ((long)var2 << 32 | (long)var3 << 56 >>> 32 | (long)var4 << 40 >>> 40) ^ b;
      long var10001 = var5 ^ 106691279307049L;
      int var7 = (int)((var5 ^ 106691279307049L) >>> 32);
      int var8 = (int)((var5 ^ 106691279307049L) << 32 >>> 48);
      int var10 = (int)((var5 ^ 37035402874122L) >>> 32);
      long var12 = var5 ^ 55979881518364L;
      long var14 = var5 ^ 108141430221731L;
      int var20 = (int)((var5 ^ 28615657252750L) >>> 48);
      int var21 = (int)((var5 ^ 28615657252750L) << 16 >>> 32);
      int var22 = (int)((var5 ^ 28615657252750L) << 48 >>> 48);
      if (!this.Y()) {
         this.resetBlockRemoving(var12);
         this.o = null;
         this.C = null;
      } else {
         var1.q(var7, var8);
         BlockPos var23 = this.X((char)var20, var21, (short)var22);
         if (var23 == null) {
            this.resetBlockRemoving(var12);
            this.o = null;
            this.C = null;
         } else {
            this.T(true);
            this.o = var23;
            this.F( BlockUtil.a(var23));
            EnumFacing var24 = this.c(var23,0L);
            if (var24 != null && this.l(var23, var14, var24)) {
               if (swing.c()) {
                  f.thePlayer.swingItem();
               } else {
                  PacketManager.b(new C0APacketAnimation());
               }

               CombatUtil.G(var10, var23, var24);
            }
         }
      }
   }

   private NukerScanAxis f$r1() {
      float var1 = MathUtil.T(RotationManager.r, 0.0F, 360.0F);
      if (var1 >= 45.0F && var1 < 135.0F) {
         return new NukerScanAxis(-1, 0, 0, -1, null);
      } else if (var1 >= 135.0F && var1 < 225.0F) {
         return new NukerScanAxis(0, -1, -1, 0, null);
      } else {
         return var1 >= 225.0F && var1 < 315.0F ? new NukerScanAxis(1, 0, 0, 1, null) : new NukerScanAxis(0, 1, 1, 0, null);
      }
   }

   static {
      b = 59755270223288L;
      n = new Object[23];
      p = new String[23];
      e = new HashMap(13);
      d = new String[3];
      m = new HashMap(13);
      h = new long[]{-5908676961446516622L, 5372785210800559796L, 6375693055559332728L, -1275294834765481263L, 2914767951935686523L, -8291187651642895498L, -4312771241707091288L};
   }

   public void onHeldItemChange(int var1, int var2, char var3, HeldItemChangeEvent var4) {
      long var5 = ((long)var1 << 32 | (long)var2 << 48 >>> 32 | (long)var3 << 48 >>> 48) ^ b;
      int var7 = (int)((var5 ^ 139491535593073L) >>> 32);
      long var8 = (var5 ^ 139491535593073L) << 32 >>> 32;
      var4.I(var7, var8);
   }

   private void F( Block var3) {
      int var8 = ItemUtil.e(0L, var3);
      if (var8 != -1) {
         if (!this.K) {
            this.U = f.thePlayer.inventory.currentItem;
         }

         ItemUtil.P( var8);
         this.K = ((1 & 1) != 0);
      }
   }

   private NukerScanState Z(NukerScanAxis var1, int var2, int var3, int var4, int var5) {
      if (NukerScanAxis.T(var1) != 0) {
         int var7 = var4 + NukerScanAxis.y(var1) * var5;
         return new NukerScanState(true, var7, var3, var2, NukerScanAxis.T(var1), null);
      } else {
         int var6 = var2 + NukerScanAxis.a(var1) * var5;
         return new NukerScanState(false, var6, var3, var4, NukerScanAxis.Y(var1), null);
      }
   }

   private BlockPos X(char var1, int var2, short var3) {
      long var4 = ((long)var1 << 48 | (long)var2 << 32 >>> 16 | (long)var3 << 48 >>> 48) ^ b;
      long var6 = var4 ^ 137905600838031L;
      long var8 = var4 ^ 96363686747503L;
      long var10 = var4 ^ 43583266961699L;
      if (this.isBlockLoaded(this.o, var8)) {
         return this.o;
      }

      if (this.C != null) {
         BlockPos var12 = this.e(var6, this.C);
         if (var12 != null) {
            return var12;
         }
      }

      this.C = this.B(var10);
      return this.C == null ? null : this.e(var6, this.C);
   }

   private boolean isBlockLoaded(BlockPos var1, long var2) {
      long var4 = var2 ^ 88256121923458L;
      if (var1 != null && f.theWorld != null && f.theWorld.isBlockLoaded(var1)) {
         Block var8 = BlockUtil.a(var1);
         if (var8 != null && var8 != Blocks.air && !BlockUtil.a$r1(var1)) {
            return !RaytraceUtil.Y(var1, range.L() + 0.75, var4) ? false : this.c(var1,0L) != null;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   private List b(char var1, int var2, int var3) {
      long var4 = ((long)var1 << 48 | (long)var2 << 32 >>> 16 | (long)var3 << 48 >>> 48) ^ b;
      long var6 = var4 ^ 17004498919946L;
      ArrayList var8 = new ArrayList();
      int var9 = (int)Math.ceil(range.L());
      NukerScanAxis var10 = this.f$r1();
      List var11 = this.x(var9);
      int var12 = (int)Math.floor(f.thePlayer.posX);
      int var13 = (int)Math.floor(f.thePlayer.posY);
      int var14 = (int)Math.floor(f.thePlayer.posZ);

      for (int var16 : (Iterable<Integer>)(var11)) {
         int var17 = var13 + var16;

         for (int var18 = -var9; var18 <= var9; var18++) {
            boolean var19 = false;

            for (int var20 = 0; var20 <= var9; var20++) {
               BlockPos var21 = this.Y(var10, var12, var17, var14, var20, var18);
               if (this.isBlockLoaded(var21, var6)) {
                  var19 = true;
                  break;
               }
            }

            if (var19) {
               var8.add(this.Z(var10, var12, var17, var14, var18));
            }
         }
      }

      return var8;
   }

   private boolean l(BlockPos var1, long var2, EnumFacing var4) {
      var2 = b ^ var2;
      int var7 = (int)((var2 ^ 105869634813247L) >>> 48);
      int var8 = (int)((var2 ^ 105869634813247L) << 16 >>> 32);
      int var9 = (int)((var2 ^ 105869634813247L) << 48 >>> 48);
      long var10 = var2 ^ 2376421457708L;
      switch (moveFix.Y()) {
         case "SILENT":
            RotationManager.n(RotationMode.SILENT);
            break;
         case "STRICT":
            RotationManager.n(RotationMode.STRICT);
            break;
         case "NONE":
            RotationManager.n(RotationMode.NONE);
      }

      if (!this.v.L(1L, true)) {
         return false;
      }

      float[] var15 = RotationUtil.S((char)var7, var8, (char)var9, var1, var4);
      RotationManager.v(var15[0], 60.0F,0L, 1.0F);
      RotationManager.A(var10, var15[1]);
      this.g = ((1 & 1) != 0);
      MovingObjectPosition var16 = RaytraceUtil.f(RotationManager.r, RotationManager.G, f.playerController.getBlockReachDistance(), 1.0F);
      return var16 != null && var16.typeOfHit == MovingObjectType.BLOCK && var1.equals(var16.getBlockPos()) || Math.abs(MathUtil.M(RotationManager.r, var15[0])) <= 20.0F;
   }

   private EnumFacing c(BlockPos var1, long var2) {
      EnumFacing[] var10000 = new EnumFacing[6];
      var10000[0] = EnumFacing.UP;
      var10000[1] = EnumFacing.NORTH;
      var10000[2] = EnumFacing.SOUTH;
      var10000[3] = EnumFacing.EAST;
      var10000[4] = EnumFacing.WEST;
      var10000[5] = EnumFacing.DOWN;
      EnumFacing[] var4 = var10000;

      for (EnumFacing var8 : var4) {
         BlockPos var9 = var1.offset(var8);
         if (f.theWorld.isBlockLoaded(var9) && BlockUtil.a$r1(var9)) {
            Vec3 var10 = BlockUtil.f(var1, var8);
            MovingObjectPosition var11 = RaytraceUtil.H(var10);
            if (var11 == null) {
               return var8;
            }

            if (var11.typeOfHit == MovingObjectType.BLOCK && var1.equals(var11.getBlockPos())) {
               return var8;
            }
         }
      }

      return null;
   }

   private NukerScanState B(long var1) {
      var1 = b ^ var1;
      int var3 = (int)((var1 ^ 140122325141062L) >>> 48);
      int var4 = (int)((var1 ^ 140122325141062L) << 16 >>> 32);
      int var5 = (int)((var1 ^ 140122325141062L) << 48 >>> 48);
      List var6 = this.b((char)var3, var4, var5);
      return var6.isEmpty() ? null : (NukerScanState)var6.get(0);
   }

   private void resetBlockRemoving(long var1) {
      long var5 = var1 ^ 28878419756212L;
      this.T(false);
      if (this.g) {
         RotationManager.O(var5);
         f.playerController.resetBlockRemoving();
         this.g = ((0 & 1) != 0);
      }

      if (this.K) {
         if (this.U != -1) {
            ItemUtil.P( this.U);
         }

         this.U = -1;
         this.K = ((0 & 1) != 0);
      }

      this.o = null;
   }

   private BlockPos Y(NukerScanAxis var1, int var2, int var3, int var4, int var5, int var6) {
      int var7 = var2 + NukerScanAxis.T(var1) * var5 + NukerScanAxis.a(var1) * var6;
      int var8 = var4 + NukerScanAxis.Y(var1) * var5 + NukerScanAxis.y(var1) * var6;
      return new BlockPos(var7, var3, var8);
   }

   private BlockPos f(NukerScanAxis var1, int var2, int var3, int var4) {
      double var5 = f.thePlayer.posX;
      double var7 = f.thePlayer.posY;
      double var9 = f.thePlayer.posZ;
      int var11 = (int)Math.floor(var5) + NukerScanAxis.T(var1) * var2 + NukerScanAxis.a(var1) * var3;
      int var12 = (int)Math.floor(var7) + var4;
      int var13 = (int)Math.floor(var9) + NukerScanAxis.Y(var1) * var2 + NukerScanAxis.y(var1) * var3;
      return new BlockPos(var11, var12, var13);
   }
   static {
      mineDown = new BooleanSetting("Mine-down", true);
      swing = new BooleanSetting("Swing", true);
   }
   static {
      range = new NumberSetting("Range", 4.0F, 1.0F, 10.0F, 1.0F);
   }
   static {
      moveFix = new ModeSetting("Move-fix", "SILENT", "STRICT", "NONE");
   }
}
