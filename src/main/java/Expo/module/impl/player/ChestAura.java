package Expo.module.impl.player;

import Expo.module.Category;

import Expo.enums.RotationMode;
import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.ChestAuraBinder;
import Expo.event.events.CloseScreenEvent;
import Expo.event.events.EntityJoinWorldEvent;
import Expo.event.events.PlayerRightClickEvent;
import Expo.event.events.PreMouseInputEvent;
import Expo.module.PriorityModule;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.ModeSetting;
import Expo.setting.settings.NumberSetting;
import Expo.util.BlockUtil;
import Expo.util.CombatUtil;
import Expo.util.EntityUtil;
import Expo.util.RaytraceUtil;
import Expo.util.RotationManager;
import Expo.util.RotationUtil;
import Expo.util.TimerUtil;
import Expo.util.packet.OutgoingPacketState;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.BlockPos;

public class ChestAura extends PriorityModule implements EventSubscriber {
   private long g;
   private final TimerUtil O;
   private static long b;
   private boolean J;
   public static NumberSetting range;
   public static BooleanSetting throughWall;
   private static Map e;
   private BlockPos E;
   private BlockPos N;
   private static Map n;
   private long Y;
   private static long[] h;
   private static String[] B;
   public static NumberSetting disableWhenPlayersInRange;
   private static Object[] x;
   private static String[] d;
   private static Map u;
   private boolean p;
   public static BooleanSetting rotation;
   public static List<BlockPos> H;
   private final TimerUtil y;
   public static ModeSetting moveFix;
   private boolean s;

   private void l(BlockPos var1) {
      if (f.theWorld != null && f.theWorld.getBlockState(var1).getBlock() instanceof BlockChest) {
         this.T(var1);
      }
   }

   private BlockPos getDistanceSqToCenter(char var1, double var2, int var4, char var5) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var6 = ((long)var1 << 48 | (long)var4 << 32 >>> 16 | (long)var5 << 48 >>> 48) ^ b;
      int var8 = (int)((var6 ^ 59959097843146L) >>> 32);
      int var9 = (int)((var6 ^ 59959097843146L) << 32 >>> 32);
      int var10 = (int)Math.ceil(var2);
      BlockPos var11 = null;
      double var12 = Double.MAX_VALUE;
      double var14 = var2 * var2;
      BlockPos var16 = new BlockPos(f.thePlayer.posX, f.thePlayer.posY, f.thePlayer.posZ);

      for (int var17 = -var10; var17 <= var10; var17++) {
         for (int var18 = -var10; var18 <= var10; var18++) {
            for (int var19 = -var10; var19 <= var10; var19++) {
               BlockPos var20 = var16.add(var17, var18, var19);
               if (this.p(var8, var9, var20)) {
                  double var21 = f.thePlayer.getDistanceSqToCenter(var20);
                  if (var21 <= var14 && var21 < var12) {
                     var12 = var21;
                     var11 = var20;
                  }
               }
            }
         }
      }

      return var11;
   }

   private void b(BlockPos var1) {
      if (var1 != null) {
         this.T(var1);
         this.l(var1.north());
         this.l(var1.south());
         this.l(var1.east());
         this.l(var1.west());
      }
   }

   private void T(BlockPos var1) {
      if (var1 != null && !H.contains(var1)) {
         H.add(var1);
      }
   }

   private static String b(byte[] var0) {
      int var1 = 0;
      int var2;
      char[] var3 = new char[var2 = var0.length];

      for (int var4 = 0; var4 < var2; var4++) {
         int var5;
         if ((var5 = 255 & var0[var4]) < 192) {
            var3[var1++] = (char)var5;
         } else if (var5 < 224) {
            char var6 = (char)((char)(var5 & 31) << 6);
            int var8 = var0[++var4];
            var6 = (char)(var6 | (char)(var8 & 63));
            var3[var1++] = var6;
         } else if (var4 < var2 - 2) {
            char var12 = (char)((char)(var5 & 15) << '\f');
            int var9 = var0[++var4];
            var12 = (char)(var12 | (char)(var9 & 63) << 6);
            var9 = var0[++var4];
            var12 = (char)(var12 | (char)(var9 & 63));
            var3[var1++] = var12;
         }
      }

      return new String(var3, 0, var1);
   }

   private void p(byte var1, long var2) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var4 = ((long)var1 << 56 | 95676268004049L) ^ b;
      int var6 = (int)((var4 ^ 57217048798001L) >>> 56);
      int var7 = (int)((var4 ^ 57217048798001L) << 8 >>> 32);

      for (TileEntity var10 : f.theWorld.loadedTileEntityList) {
         if (var10 instanceof TileEntityChest) {
            TileEntityChest var11 = (TileEntityChest)var10;
            BlockPos var12 = var11.getPos();
            if (!H.contains(var12) && var11.numPlayersUsing > 0) {
               this.b(var12);
            }

            if (!H.contains(var12) && BlockUtil.Y((byte)var6, var12, var7)) {
               this.b(var12);
            }
         }
      }
   }

   public void onPlayerRightClick(short var1, int var2, short var3, PlayerRightClickEvent var4) {
      if (f.theWorld != null) {
         BlockPos var7 = var4.a$r2();
         if (f.theWorld.getBlockState(var7).getBlock() instanceof BlockChest) {
            this.N = var7;
            this.p = true;
            this.Y = System.currentTimeMillis();
         }
      }
   }

   public void onCloseScreen(long var1, CloseScreenEvent var3) {
      this.g = System.currentTimeMillis();
      if (this.p && this.N != null) {
         this.b(this.N);
      }

      this.p = false;
      this.N = null;
      this.s = false;
      this.E = null;
      this.o(0L);
   }

   public final void x(long var1, EventBus var3) {
      ChestAuraBinder.E(var3, this);
   }

   private void v(short var1, BlockPos var2, int var3, int var4) {
      long var5 = ((long)var1 << 48 | (long)var3 << 32 >>> 16 | (long)var4 << 48 >>> 48) ^ b;
      int var7 = (int)((var5 ^ 104313837449379L) >>> 48);
      int var8 = (int)((var5 ^ 104313837449379L) << 16 >>> 32);
      int var9 = (int)((var5 ^ 104313837449379L) << 48 >>> 48);
      long var10 = var5 ^ 119927639145566L;
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

      if (this.y.L(1L, true)) {
         float[] var14 = RotationUtil.S((char)var7, var8, (char)var9, var2, BlockUtil.D(var2));
         RotationManager.N(var10, var14[0], var14[1]);
         this.J = true;
      }
   }

   static {
      b = 34799082319422L;
      H = new ArrayList<>();
      x = new Object[30];
      B = new String[30];
      e = new HashMap(13);
      d = new String[3];
      n = new HashMap(13);
      h = new long[]{-8449384215971602076L, -8875789389776584978L, -8344971114425550018L, 2371771012195526087L};
      u = new HashMap(13);
   }

   public String g(long var1) {
      return String.valueOf(range.L());
   }

   public ChestAura(long var1) {
      super((((b ^ (var1)) ^ 47193562722394L) >>> 16), (char)((int)(((((b ^ (var1)) ^ 47193562722394L) << 48) >>> 48))));
      this.declare("ChestAura", Category.Player, "Automatically open chests in range");
      var1 = b ^ var1;
      this.y = new TimerUtil();
      this.O = new TimerUtil();
      this.g = System.currentTimeMillis();
      this.Y = 0L;
      this.J = false;
      this.s = false;
      this.p = false;
      this.E = null;
      this.N = null;
   }

   private void Y(int var1) {
      this.o(0L);
      this.s = false;
      this.E = null;
      if (!this.p) {
         this.N = null;
      }
   }

   public void onEntityJoinWorld(long var1, EntityJoinWorldEvent var3) {
      if (var3.H instanceof EntityPlayerSP) {
         H.clear();
         this.o(0L);
         this.s = false;
         this.E = null;
         this.N = null;
         this.p = false;
      }
   }

   private boolean R(long var1) {
      int var5 = 226;
      int var8 = 64121;
      if (!EntityUtil.J(EntityUtil.o(17403, (char)32677, (short)var8, disableWhenPlayersInRange.L()), false, true, false, 4469, 3915520, false, (byte)var5).isEmpty()) {
         return true;
      } else if (!this.Y()) {
         this.T(false);
         return true;
      } else {
         return !OutgoingPacketState.Y() ? true : System.currentTimeMillis() - this.g <= 100L;
      }
   }

   private void o(long var1) {
      if (this.J) {
         RotationManager.O(123115463851087L);
         this.J = false;
      }
   }

   private boolean p(int var1, int var2, BlockPos var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var4 = ((long)var1 << 32 | (long)var2 << 32 >>> 32) ^ b;
      int var6 = (int)((var4 ^ 126217328991961L) >>> 56);
      int var7 = (int)((var4 ^ 126217328991961L) << 8 >>> 32);
      int var9 = (int)((var4 ^ 70347148787340L) >>> 48);
      long var10 = (var4 ^ 70347148787340L) << 16 >>> 16;
      if (var3 == null || f.theWorld == null) {
         return false;
      } else if (H.contains(var3)) {
         return false;
      } else {
         Block var12 = f.theWorld.getBlockState(var3).getBlock();
         if (!(var12 instanceof BlockChest)) {
            return false;
         } else {
            return BlockUtil.Y((byte)var6, var3, var7) ? false : throughWall.c() || !RaytraceUtil.r((short)var9, var10, var3, BlockUtil.D(var3), range.L());
         }
      }
   }

   public void A(long var1) {
      this.o(0L);
      this.s = false;
      this.E = null;
      this.N = null;
      this.p = false;
   }

   public void onPreMouseInput(long var1, PreMouseInputEvent var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      int var8 = 19661;

      int var17 = 36958;

      if (f.theWorld != null && f.thePlayer != null) {
         this.p((byte)0, 95676268004049L);
         if (f.currentScreen instanceof GuiChest) {
            if (this.N != null) {
               this.b(this.N);
            }

            this.p = false;
            this.N = null;
            this.s = false;
            this.E = null;
            this.o(0L);
         } else if (this.p) {
            if (System.currentTimeMillis() - this.Y > 1200L) {
               this.p = false;
               this.N = null;
            }

            this.o(0L);
         } else if (this.R(32375407106993L)) {
            this.Y(16590);
         } else if (!this.s || this.E == null) {
            BlockPos var23 = this.getDistanceSqToCenter((char)0, range.L(), 954787180, (char)var8);
            if (var23 == null) {
               this.Y(16590);
            } else {
               if (rotation.c()) {
                  this.v((short)0, var23, 845277282, var17);
               }

               this.s = true;
               this.E = var23;
            }
         } else if (!this.p(4550, -14237895, this.E)) {
            this.Y(16590);
         } else {
            if (this.O.L(80L, true)) {
               BlockPos var21 = this.E;
               CombatUtil.u(var21, BlockUtil.D(var21), BlockUtil.f(var21, BlockUtil.D(var21)), true, false);
               this.N = var21;
               this.p = true;
               this.Y = System.currentTimeMillis();
               this.s = false;
               this.E = null;
            }
         }
      } else {
         this.Y(16590);
      }
   }
   static {
      throughWall = new BooleanSetting("Through-wall", false);
      moveFix = new ModeSetting("Move-fix", "SILENT", "STRICT", "NONE");
      range = new NumberSetting("Range", 3.0F, 0.0F, 10.0F, 0.1F);
      disableWhenPlayersInRange = new NumberSetting("Disable-when-players-in-range", 5.0F, 0.0F, 20.0F, 0.1F);
      rotation = new BooleanSetting("Rotation", true);
   }
}
