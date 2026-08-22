package Expo.module.impl.world;

import Expo.module.Category;

import Expo.enums.RotationMode;
import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.AutoDigPlaceBinder;
import Expo.event.events.AttackEntityEvent;
import Expo.event.events.HeldItemChangeEvent;
import Expo.event.events.PreMouseInputEvent;
import Expo.event.events.SendPacketEvent;
import Expo.internal.accessor.EntityLivingBaseStateAccessor;
import Expo.module.PriorityModule;
import Expo.setting.settings.BooleanSetting;
import Expo.util.BlockUtil;
import Expo.util.CombatUtil;
import Expo.util.ItemUtil;
import Expo.util.KeyBindUtil;
import Expo.util.PlacementTarget;
import Expo.util.RotationManager;
import Expo.util.packet.OutgoingPacketState;
import Expo.util.packet.PacketManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.network.play.client.C07PacketPlayerDigging.Action;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C0APacketAnimation;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;


public class AutoDigPlace extends PriorityModule implements EventSubscriber {
   private boolean O;
   private int K;
   private boolean t;
   private BlockPos R;
   private boolean E;
   public static BooleanSetting rightClickDigDown;
   public static BooleanSetting swing;
   private boolean a;
   private boolean N;
   private BlockPos C;
   private boolean n;
   private static long b;

   static {
      b = 17293825422729L;
   }

   private boolean L() {


      if (this.R == null) {
         return false;
      }

      if (!this.O) {
         return false;
      }

      if (!BlockUtil.a$r1(this.R)) {
         this.O = false;
         this.R = null;
         this.C = null;
         return true;
      }

      if (this.n && !f.thePlayer.onGround) {
         KeyBindUtil.A(82009306480869L, f.gameSettings.keyBindJump.getKeyCode(), false);
         return false;
      }

      if (!this.N) {
         this.K = f.thePlayer.inventory.currentItem;
         this.N = true;
      }

      boolean var11 = false;

      for (int var12 = 0; var12 < InventoryPlayer.getHotbarSize(); var12++) {
         if (f.thePlayer.inventory.mainInventory[var12] != null
            && ItemUtil.u(f.thePlayer.inventory.mainInventory[var12])
            && f.thePlayer.inventory.mainInventory[var12].stackSize > 0
            && !var11) {
            ItemUtil.P( var12);
            var11 = true;
         }
      }

      if (!var11) {
         return false;
      }

      RotationManager.N(71285564916286L, RotationManager.r, this.n ? -90.0F : 90.0F);
      this.a = true;
      if (f.thePlayer.isUsingItem()) {
         return true;
      }

      PlacementTarget var14 = this.q(this.R);
      return var14 == null ? false : CombatUtil.u(var14.q, var14.Z, BlockUtil.f(var14.q, var14.Z), swing.c(), false);
   }

   public void onHeldItemChange(HeldItemChangeEvent var1, int var2, int var3, int var4) {
      long var5 = ((long)var2 << 32 | (long)var3 << 48 >>> 32 | (long)var4 << 48 >>> 48) ^ b;
      int var7 = (int)((var5 ^ 97632448067714L) >>> 32);
      long var8 = (var5 ^ 97632448067714L) << 32 >>> 32;
      var1.I(var7, var8);
   }


   public void onPreMouseInput(PreMouseInputEvent var1, long var2) {






      if (!this.Y()) {
         this.T(false);
      } else {
         this.T(true);
         RotationManager.n(RotationMode.SILENT);
         var1.q(9819, 57776);
         this.n = rightClickDigDown.c() && KeyBindUtil.V(f.gameSettings.keyBindUseItem.getKeyCode(), 64165991731362L);
         if (!this.n) {
            EntityLivingBaseStateAccessor.x(14848, f.thePlayer, 0);
            KeyBindUtil.A(82009306480869L, f.gameSettings.keyBindJump.getKeyCode(), true);
            this.t = true;
         } else {
            KeyBindUtil.A(82009306480869L, f.gameSettings.keyBindJump.getKeyCode(), false);
         }

         if (this.C == null) {
            if (this.L()) {
               this.E = false;
               return;
            }

            BlockPos var22 = null;
            double var23 = f.thePlayer.posX;
            double var25 = f.thePlayer.posY;
            double var27 = f.thePlayer.posZ;
            BlockPos var29 = BlockUtil.Z();
            BlockPos var30 = var29.add(0, 2, 0);
            if (this.n && this.E(var30,0L)) {
               this.C = null;
               this.R = var30;
               this.O = true;
               this.E = false;
               this.L();
               return;
            }

            if (this.n) {
               if (BlockUtil.l(var23, var25 - 1.0, var27) != null) {
                  var22 = new BlockPos(var23, var25 - 1.0, var27);
               } else if (BlockUtil.l(var23, var25 - 2.0, var27) != null) {
                  var22 = new BlockPos(var23, var25 - 2.0, var27);
               }
            } else if (BlockUtil.l(var23, var25 + 2.0, var27) != null) {
               var22 = new BlockPos(var23, var25 + 2.0, var27);
            } else if (BlockUtil.l(var23, var25 + 3.0, var27) != null) {
               var22 = new BlockPos(var23, var25 + 3.0, var27);
            }

            if (var22 != null) {
               if (f.theWorld.isAirBlock(var22)) {
                  this.C = null;
                  this.R = this.n ? var22 : BlockUtil.Z();
                  this.O = true;
                  this.E = false;
                  return;
               }

               RotationManager.N(71285564916286L, RotationManager.r, this.n ? 90.0F : -90.0F);
               this.a = true;
               this.C = var22;
               this.getKeyCode(0L);
            }
         } else {
            if (f.theWorld.isAirBlock(this.C)) {
               BlockPos var32 = this.C;
               this.C = null;
               this.R = this.n ? var32 : BlockUtil.Z();
               this.O = true;
               this.L();
               this.E = false;
               return;
            }

            this.getKeyCode(0L);
         }
      }
   }



   public AutoDigPlace(int var1, char var2, int var3) {
      super((((((((long)((var1)) << 32) | (((long)((var2)) << 48) >>> 32)) | (((long)((var3)) << 48) >>> 48)) ^ b) ^ 17472920490440L) >>> 16), (char)((int)(((((((((long)((var1)) << 32) | (((long)((var2)) << 48) >>> 32)) | (((long)((var3)) << 48) >>> 48)) ^ b) ^ 17472920490440L) << 48) >>> 48))));
      // add code
      this.declare("AutoDigPlace", Category.World, "Auto dig and place blocks beneath or above");
      this.C = null;
      this.O = false;
      this.R = null;
      this.a = false;
      this.N = false;
      this.t = false;
      this.E = false;
      this.n = false;
   }

   private void getKeyCode(long var1) {


      if (this.C != null) {
         this.E = true;
         KeyBindUtil.A(82009306480869L, f.gameSettings.keyBindJump.getKeyCode(), false);
         int var13 = ItemUtil.e(0L, BlockUtil.a(this.C));
         ItemUtil.P( var13 == -1 ? f.thePlayer.inventory.currentItem : var13);
         if (!OutgoingPacketState.P || !OutgoingPacketState.h) {
            EnumFacing var11 = this.n ? EnumFacing.UP : EnumFacing.DOWN;
            BlockPos var12 = this.C;
            CombatUtil.G(26365, var12, var11);
            this.swingItem();
         }
      }
   }

   private boolean E(BlockPos var1, long var2) {
      return !BlockUtil.a$r1(var1) ? false : this.q(var1) != null;
   }

   private void swingItem() {
      if (swing.c()) {
         f.thePlayer.swingItem();
      } else {
         PacketManager.b(new C0APacketAnimation());
      }
   }


   public void Z(long var1) {
      long var5 = var1 ^ 12894974052865L;
      long var7 = var1 ^ 68296509399185L;
      this.T(false);
      this.E = false;
      this.C = null;
      this.O = false;
      this.R = null;
      if (this.N && !OutgoingPacketState.P && !OutgoingPacketState.h) {
         ItemUtil.P( this.K);
         this.N = false;
      }

      if (this.a) {
         RotationManager.O(var5);
         this.a = false;
      }

      if (this.t) {
         KeyBindUtil.o(var7, f.gameSettings.keyBindJump.getKeyCode());
         this.t = false;
      }
   }


   public final void x(long var1, EventBus var3) {
      AutoDigPlaceBinder.n(var3, this);
   }

   public void onSendPacket(int var1, char var2, char var3, SendPacketEvent var4) {
      long var5 = ((long)var1 << 32 | (long)var2 << 48 >>> 32 | (long)var3 << 48 >>> 48) ^ b;
      int var7 = (int)((var5 ^ 34048752373701L) >>> 32);
      long var8 = (var5 ^ 34048752373701L) << 32 >>> 32;
      if (this.E && var4.B instanceof C07PacketPlayerDigging && ((C07PacketPlayerDigging)var4.B).getStatus() == Action.ABORT_DESTROY_BLOCK) {
         var4.I(var7, var8);
      }
   }

   private PlacementTarget q(BlockPos var1) {
      EnumFacing[] var10000 = new EnumFacing[6];
      var10000[0] = EnumFacing.DOWN;
      var10000[1] = EnumFacing.UP;
      var10000[2] = EnumFacing.NORTH;
      var10000[3] = EnumFacing.SOUTH;
      var10000[4] = EnumFacing.WEST;
      var10000[5] = EnumFacing.EAST;
      EnumFacing[] var4 = var10000;

      for (EnumFacing var8 : var4) {
         BlockPos var9 = var1.offset(var8.getOpposite());
         if (!BlockUtil.a$r1(var9)) {
            return new PlacementTarget(var9, var8, false);
         }
      }

      return null;
   }

   public void onAttackEntity(AttackEntityEvent var3) {


      var3.I(21307, 3074332907L);
   }

   static {
      // add code
      swing = new BooleanSetting("Swing", true);
      rightClickDigDown = new BooleanSetting("Right-click-dig-down", true);
   }
}
