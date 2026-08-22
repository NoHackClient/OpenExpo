package Expo.module.impl.world;

import Expo.module.Category;

import Expo.enums.RotationMode;
import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.ScaffoldBinder;
import Expo.event.events.HeldItemChangeEvent;
import Expo.event.events.MoveInputEvent;
import Expo.event.events.PreMouseInputEvent;
import Expo.event.events.Render2DEvent;
import Expo.event.events.Render3DEvent;
import Expo.internal.accessor.EntityLivingBaseStateAccessor;
import Expo.module.PriorityModule;
import Expo.module.impl.configuration.Font;
import Expo.module.impl.configuration.Theme;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.ColorSetting;
import Expo.setting.settings.ModeSetting;
import Expo.setting.settings.NumberSetting;
import Expo.setting.settings.PercentageSetting;
import Expo.util.BlockUtil;
import Expo.util.CombatUtil;
import Expo.util.ItemUtil;
import Expo.util.KeyBindUtil;
import Expo.util.MathUtil;
import Expo.util.MoveUtil;
import Expo.util.Pair;
import Expo.util.PlacementTarget;
import Expo.util.RaytraceUtil;
import Expo.util.RotationManager;
import Expo.util.RotationUtil;
import Expo.util.TimerUtil;
import Expo.util.packet.OutgoingPacketState;
import Expo.util.packet.PacketManager;
import Expo.util.render.CustomFont;
import java.awt.Color;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldSettings.GameType;


public class Scaffold extends PriorityModule implements EventSubscriber {
   public static PercentageSetting keepYJumpForwardChance;
   private boolean e;
   private static boolean dk;
   public static BooleanSetting aimCheck;
   private boolean G;
   public static ColorSetting customColor;
   public static NumberSetting diagonalAirDelay;
   private int F;
   private boolean d2;
   private boolean b;
   public static PercentageSetting rotationSmoothing;
   private boolean dw;
   private final TimerUtil dS;
   private int de;
   private boolean k;
   private boolean N;
   private long s;
   public static ModeSetting espColor;
   private final TimerUtil dW;
   public static BooleanSetting keepYOnRightClick;
   private static ItemStack c;
   private boolean u;
   public static ModeSetting normalModeRotation;
   private static int dO;
   public static BooleanSetting strictAimCheck;
   public static NumberSetting straightAirDelay;
   private boolean dZ;
   private int dl;
   public static NumberSetting straightJumpBlocks;
   private boolean L;
   private static long bb;
   public static NumberSetting offsetRotationOffset;
   private boolean dt;
   public static NumberSetting legitModeUnsneakDelay;
   private boolean m;
   public static NumberSetting diagonalJumpBlocks;
   public static ModeSetting moveFix;
   public static BooleanSetting outlineFadeOut;
   public static BooleanSetting downPlace;
   public static ModeSetting keepYModeRotation;
   public static BooleanSetting itemCounter;
   private boolean H;
   public static BooleanSetting autoItem;
   public static NumberSetting legitModeEdgeOffset;
   private int K;
   private boolean y;
   public static BooleanSetting showTargetOutline;
   private int v;
   private int T;
   private final TimerUtil S;
   public static ModeSetting mode;
   public static BooleanSetting keepYBlinkRotation;
   public static BooleanSetting fakeItem;
   private boolean h;
   private double R;
   private boolean d;
   public static BooleanSetting swing;
   private final List<Pair<BlockPos, Long>> dV;
   public static ModeSetting legitModeRotation;
   public static BooleanSetting dontRenderRotation;
   private boolean g;
   private int x;
   public static BooleanSetting showTargetShade;
   public static NumberSetting angleStep;

   private BlockPos Y$r1() {
      return !this.dV.isEmpty() ? this.dV.get(this.dV.size() - 1).a() : null;
   }



   private void U() {

      if (!this.dw) {
         KeyBindUtil.o(99363263780575L, f.gameSettings.keyBindJump.getKeyCode());
         this.dw = true;
      }

      PlacementTarget var11 = BlockUtil.x(f.thePlayer.posY - 1.0, this.Y$r1(), downPlace.c());
      if (var11 != null) {
         this.v(var11,angleStep.L());
      }

      if (var11 != null
         && (
            ((BlockPos)var11.q).getY()
                  <= f.thePlayer.posY - 1.0
               || downPlace.c()
         )) {
         this.M(var11);
      }
   }


   private void i(long var1, MoveInputEvent var3) {
      int var8 = f.thePlayer.ticksExisted;
      if (this.T == -1) {
         this.T = var8;
         this.de = this.o(Math.max(0.0, legitModeUnsneakDelay.L() - 50.0));
      }

      if (var8 - this.T < this.de) {
         this.t(var3);
      } else {
         var3.x(false);
         this.a();
      }
   }

   private void D(long var1) {
      PlacementTarget var9 = BlockUtil.x(f.thePlayer.posY - 1.0, this.Y$r1(), downPlace.c());
      if (var9 != null && (var9.q.getY() <= f.thePlayer.posY - 1.0 || downPlace.c())) {
         this.v(var9,angleStep.L());
         this.M(var9);
      }
   }

   private int o(double var1) {
      double var3 = var1 / 50.0;
      int var5 = (int)var3;
      return var5 + (Math.random() < var3 - var5 ? 1 : 0);
   }

   private float a(int var1, BlockPos var2, int var3, char var4, EnumFacing var5) {
      long var6 = ((long)var1 << 32 | (long)var3 << 48 >>> 32 | (long)var4 << 48 >>> 48) ^ bb;
      int var8 = (int)((var6 ^ 42946444559601L) >>> 48);
      int var9 = (int)((var6 ^ 42946444559601L) << 16 >>> 32);
      int var10 = (int)((var6 ^ 42946444559601L) << 48 >>> 48);
      return RotationUtil.S((char)var8, var9, (char)var10, var2, var5)[0];
   }

   private void O(long var1, MoveInputEvent var3, short var4) {
      long var5 = (124398202454016L | (long)var4 << 48 >>> 48) ^ bb;
      long var9 = var5 ^ 63121703317035L;
      if (this.d && !KeyBindUtil.V(f.gameSettings.keyBindSneak.getKeyCode(), var9)) {
         var3.x(false);
      }

      this.a();
   }

   public void Z(long var1) {
      if (this.b) {
         ItemUtil.P( this.dl);
         this.b = false;
      }
   }

   public final void x(long var1, EventBus var3) {
      int var4 = (int)((var1 ^ 48625662123860L) >>> 56);
      ScaffoldBinder.h(var3, (byte)var4, this);
   }

   private void Q(float var1) {

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

      if (this.dW.L(1L, true)) {
         if (dontRenderRotation.c()) {
            RotationManager.w(true);
         }

         float[] var11 = this.T(
            new BlockPos(f.thePlayer.posX, f.thePlayer.posY - 1.0, f.thePlayer.posZ), EnumFacing.UP, true, 10744777957284L
         );
         RotationManager.L( var11[0], var11[1], var1, rotationSmoothing.k() / 100.0F);
         this.d2 = true;
      }
   }

   private void v(PlacementTarget var1,float var4) {

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

      if (this.dW.L(1L, true)) {
         if (this.y && mode.R("KEEP_Y") && keepYBlinkRotation.c() && dk && this.u) {
            PacketManager.j();
            PacketManager.M(false);
            dk = false;
         }

         if (this.u) {
            var4 = 39.0F;
         }

         if (dontRenderRotation.c()) {
            RotationManager.w(true);
         }

         float[] var14 = this.J(var1.q, 19296727618367L, var1.Z);
         float var15 = rotationSmoothing.k() / 100.0F;
         RotationManager.v(var14[0], var4,0L, var15);
         RotationManager.f(var14[1], 39.0F, var15,0L);
         this.d2 = true;
      }
   }


   private void H() {
      if (autoItem.c() && !OutgoingPacketState.P && !OutgoingPacketState.h) {
         if (!this.b) {
            this.dl = f.thePlayer.inventory.currentItem;
            this.b = true;
         }

         ItemStack var5 = f.thePlayer.getHeldItem();
         int var6 = ItemUtil.u(var5) ? var5.stackSize : 0;
         this.v = Math.min(this.v, var6);
         if (this.v <= 0) {
            int var7 = f.thePlayer.inventory.currentItem;
            if (this.v == 0) {
               var7--;
            }

            for (int var8 = var7; var8 > var7 - 9; var8--) {
               int var9 = (var8 % 9 + 9) % 9;
               ItemStack var10 = f.thePlayer.inventory.getStackInSlot(var9);
               if (ItemUtil.u(var10)) {
                  ItemUtil.P( var9);
                  this.v = var10.stackSize;
                  break;
               }
            }
         }
      }
   }

   private float[] atan2(long var1, String var3, BlockPos var4, EnumFacing var5, boolean var6) {

      long var10001 = 41812496666049L;


      int var9 = (int)(var10001 << 48 >>> 48);
      var10001 = 48056310104396L;


      int var15 = 6279;

      float var20 = this.q(var4, var5, 16973084796600L);
      float[] var21 = new float[]{RotationManager.r, var20};
      float var22 = MoveUtil.X(11188, (short)64331);
      PlacementTarget var23 = new PlacementTarget(var4, var5, false);
      switch (var3) {
         case "NORMAL":
            var21[0] = this.a(9735, var4, 15106, (char)var9, var5);
            if (this.isGetBlockPos(var23, var22 - 180.0F, var20)) {
               var21[0] = var22 - 180.0F;
            } else if (this.isGetBlockPos(var23, var22 - 135.0F, var20)) {
               var21[0] = var22 - 135.0F;
            } else if (this.isGetBlockPos(var23, var22 + 135.0F, var20)) {
               var21[0] = var22 + 135.0F;
            }
            break;
         case "BACK":
            var21[0] = var22 - 180.0F;
            var21[1] = var20;
            if (!var6) {
               boolean var38 = this.isGetBlockPos(var23, var22 - 135.0F, var20) || this.isGetBlockPos(var23, var22 + 135.0F, var20);
               if (!this.isGetBlockPos(var23, var22 - 180.0F, var20) && (strictAimCheck.c() || !var38)) {
                  var21[0] = this.a(9735, var4, 15106, (char)var9, var5);
               } else {
                  var21[0] = var22 - 180.0F;
               }
            }
            break;
         case "OFFSET":
            Vec3 var37 = RotationUtil.h(var4, var5, offsetRotationOffset.L());
            var21[0] = RotationUtil.L( var37)[0];
            var21[1] = RotationUtil.L( var37)[1];
            if (strictAimCheck.c() && !this.isGetBlockPos(var23, var21[0], var21[1])) {
               var21[0] = this.a(9735, var4, 15106, (char)var9, var5);
            }
            break;
         case "DIAGONAL":
            var21[1] = this.q(var4, var5, 16973084796600L);
            boolean var26 = this.isGetBlockPos(var23, var22 - 135.0F, var21[1]) || this.isGetBlockPos(var23, var22 + 135.0F, var21[1]);
            boolean var27 = this.isGetBlockPos(var23, var22 - 180.0F, var21[1]);
            if (!var6 && !var26 && !var27) {
               var21[0] = this.a(9735, var4, 15106, (char)var9, var5);
            } else if (H(true, (char)0, 558459959, var15) && !var6) {
               if (var27) {
                  var21[0] = var22 - 180.0F;
               } else {
                  var21[0] = this.a(9735, var4, 15106, (char)var9, var5);
               }
            } else if (!var26 && !var6) {
               var21[0] = var22 - 180.0F;
            } else {
               BlockPos var28 = new BlockPos(
                  Math.floor(f.thePlayer.posX), Math.floor(f.thePlayer.posY) - 1.0, Math.floor(f.thePlayer.posZ)
               );
               double var29 = ((BlockPos)var28).getX()
                  + 0.5
                  - f.thePlayer.posX;
               double var31 = ((BlockPos)var28).getZ()
                  + 0.5
                  - f.thePlayer.posZ;
               float var33 = (float)(Math.toDegrees(Math.atan2(var31, var29)) - 90.0);
               float var34 = MathHelper.wrapAngleTo180_float(var33 - var22);
               float var35;
               if (var34 > 0.0F) {
                  var35 = var22 + 135.0F;
                  if (strictAimCheck.c() && !this.isGetBlockPos(var23, var35, var21[1])) {
                     var35 = var22 - 135.0F;
                  }
               } else {
                  var35 = var22 - 135.0F;
                  if (strictAimCheck.c() && !this.isGetBlockPos(var23, var35, var21[1])) {
                     var35 = var22 + 135.0F;
                  }
               }

               var21[0] = var35;
               this.e = true;
            }
      }

      return var21;
   }

   private void v(long var1, MoveInputEvent var3) {





      if (mode.R("LEGIT") && f.currentScreen == null && !f.thePlayer.capabilities.isFlying && ItemUtil.u(f.thePlayer.getHeldItem()) && this.Y()) {
         if (KeyBindUtil.V(f.gameSettings.keyBindSneak.getKeyCode(), 64165991731362L)) {
            this.a();
         } else {
            AxisAlignedBB var17 = this.K(20403440901494L);
            double var18 = this.q(var17);
            if (Double.isNaN(var18)) {
               if (var3.d()) {
                  if (this.d) {
                     this.i(0L, var3);
                  }
               } else if (f.thePlayer.onGround) {
                  this.t(var3);
               } else if (this.d) {
                  this.i(0L, var3);
               }
            } else {
               if (var18 > legitModeEdgeOffset.L()) {
                  this.t(var3);
               } else if (this.d) {
                  this.i(0L, var3);
               }
            }
         }
      } else {
         this.O(1898165931L, var3, (short)31098);
      }
   }

   static {
      bb = 125416588937203L;
      dO = new Color(0, 0, 0, 100).getRGB();
      c = new ItemStack(Item.getItemFromBlock(Blocks.barrier));
      dk = false;
   }

   private void a() {
      this.d = false;
      this.T = -1;
      this.de = -1;
   }

   public static boolean H(boolean var0, char var1, int var2, int var3) {
      long var4 = ((long)var1 << 48 | (long)var2 << 32 >>> 16 | (long)var3 << 48 >>> 48) ^ bb;
      int var6 = (int)((var4 ^ 132960448126520L) >>> 32);
      int var7 = (int)((var4 ^ 132960448126520L) << 32 >>> 48);
      float var9 = MoveUtil.X(var6, (short)var7);
      var9 = MathUtil.T(var9, 0.0F, 360.0F);
      float var10 = var9 % 90.0F;
      return var10 > (var0 ? 10 : 20)
         && var10 < (var0 ? 80 : 70);
   }

   public void onMoveInput(long var1, MoveInputEvent var3) {

      if (this.h) {
         var3.O(true);
         this.h = false;
      }

      this.v(46907066512003L, var3);
   }



   private void t(MoveInputEvent var1) {
      var1.x(true);
      this.d = true;
      this.T = -1;
   }



   public void onRender3D(int var1, short var2, Render3DEvent var3, int var4) {
      long var5 = ((long)var1 << 32 | (long)var2 << 48 >>> 32 | (long)var4 << 48 >>> 48) ^ bb;
      long var7 = var5 ^ 38571284666010L;
      long var9 = var5 ^ 35916030951452L;
      long var11 = var5 ^ 61931055991908L;
      long var13 = var5 ^ 93441625432387L;
      long var15 = var5 ^ 5266323222053L;
      this.dV.removeIf(var1x -> {
         return System.currentTimeMillis() - var1x.p() > 700L && this.dV.size() > 1;
      });
      int var17;
      switch (espColor.Y()) {
         case "THEME":
            var17 = Theme.S(0.0, var7);
            break;
         case "THEME_CUSTOM":
            var17 = Theme.X(var11, 0.0);
            break;
         default:
            var17 = customColor.k(var13);
      }

      if (!this.dV.isEmpty()) {
         if (showTargetShade.c()) {
            Expo.util.render.RenderUtil.M(this.dV.get(this.dV.size() - 1).a(), var9, var17, 64, showTargetOutline.c(), showTargetShade.c());
         }

         if (showTargetOutline.c()) {
            if (outlineFadeOut.c() && this.dV.size() >= 2) {
               ArrayList<Pair> var24 = new ArrayList<>(this.dV);
               var24.removeIf(var0 -> {
                  return System.currentTimeMillis() - (Long)var0.p() > 700L;
               });

               for (Pair var20 : (Iterable<Pair>)(var24)) {
                  double var21 = (double)System.currentTimeMillis() - ((Long)var20.p()).longValue();
                  int var23 = var21 < 250.0
                     ? 255
                     : MathUtil.k(
                        (int)(0.5666666666666667 * (450.0 - var21)) + 60,
                        100,
                        255
                     );
                  Expo.util.render.RenderUtil.V((BlockPos)var20.a(), var15, var17, var23);
               }
            } else {
               Expo.util.render.RenderUtil.V(this.dV.get(this.dV.size() - 1).a(), var15, var17, 255);
            }
         }
      }
   }


   private float[] J(BlockPos var1, long var2, EnumFacing var4) {


      return this.T(var1, var4, false, 10744777957284L);
   }

   private void Y(long var1) {






      if (f.thePlayer.onGround && this.G) {
         if (!this.N) {
            EntityLivingBaseStateAccessor.x(14848, f.thePlayer, 0);
            KeyBindUtil.A(82009306480869L, f.gameSettings.keyBindJump.getKeyCode(), true);
            this.G = false;
         } else {
            if (keepYBlinkRotation.c() && straightAirDelay.L() >= 1.0F && diagonalAirDelay.L() >= 1.0F) {
               if (dk) {
                  PacketManager.j();
               }

               PacketManager.M(true);
               dk = true;
            }

            if (MathUtil.Q(keepYJumpForwardChance.k(),0L)) {
               RotationManager.N(71285564916286L, MoveUtil.X(11188, (short)64331), MathUtil.h(60.0F, 70.0F));
               RotationManager.V = MoveUtil.X(11188, (short)64331);
               this.d2 = true;
            }

            this.h = true;
            this.y = false;
            this.dS.W();
            this.S.W();
            this.G = false;
         }
      }
   }


   private boolean isGetBlockPos(PlacementTarget var1, float var2, float var3) {
      MovingObjectPosition var4 = BlockUtil.F(new float[]{var2, var3}, 4.0);
      return var4.typeOfHit != MovingObjectType.BLOCK ? false : BlockUtil.p(var4.getBlockPos(), var1.q) && (!strictAimCheck.c() || var4.sideHit == var1.Z);
   }


   public void A(long var1) {
      long var5 = var1 ^ 138156628231589L;
      long var7 = var1 ^ 64012210233546L;
      long var9 = var1 ^ 34459795889248L;
      long var11 = var1 ^ 17179273251418L;
      long var13 = var1 ^ 122710492744743L;
      this.e = false;
      this.T(false);
      this.u = false;
      this.v = -1;
      this.y = false;
      this.k = false;
      this.g = false;
      this.h = false;
      this.F = x$r1(var5) ? (int)diagonalJumpBlocks.L() : (int)straightJumpBlocks.L();
      this.dt = false;
      this.K = 0;
      this.N = false;
      this.m = false;
      this.a();
      this.L = true;
      this.G = false;
      this.x = 0;
      this.dV.clear();
      this.s = System.currentTimeMillis() - 600L;
      if (dk) {
         PacketManager.j();
         PacketManager.M(false);
         dk = false;
      }

      if (!this.dw) {
         KeyBindUtil.o(var11, f.gameSettings.keyBindJump.getKeyCode());
         this.dw = true;
      }

      KeyBindUtil.o(var11, f.gameSettings.keyBindSneak.getKeyCode());
      if (this.d2) {
         RotationManager.O(var7);
         this.d2 = false;
      }

      if (this.H) {
         this.H = false;
         KeyBindUtil.A(var9, f.gameSettings.keyBindUseItem.getKeyCode(), KeyBindUtil.V(f.gameSettings.keyBindUseItem.getKeyCode(), var13));
      }
   }

   private float[] T(BlockPos var1, EnumFacing var2, boolean var3, long var4) {



      return this.atan2(54672650222099L, this.d(39148151720929L), var1, var2, var3);
   }


   public String g(long var1) {
      long var3 = var1 ^ 44713956543479L;
      return mode.R("KEEP_Y") && keepYOnRightClick.c() && !KeyBindUtil.V(f.gameSettings.keyBindUseItem.getKeyCode(), var3) ? "NORMAL" : mode.Y();
   }


   private float q(BlockPos var1, EnumFacing var2, long var3) {





      return RotationUtil.S((char)0, 1931007915, (char)52931, var1, var2)[1];
   }


   private void N() {





      if (this.k && !f.thePlayer.onGround) {
         this.K = -1;
         this.dt = true;
      }

      if ((!keepYOnRightClick.c() || KeyBindUtil.V(f.gameSettings.keyBindUseItem.getKeyCode(), 64165991731362L)) && !f.thePlayer.isPotionActive(Potion.jump)) {
         if (KeyBindUtil.V(f.gameSettings.keyBindJump.getKeyCode(), 64165991731362L)) {
            this.R = (int)f.thePlayer.posY - 1;
            this.L = true;
         } else if (this.L || f.thePlayer.onGround) {
            this.R = (int)f.thePlayer.posY - 1;
            this.L = false;
         }

         PlacementTarget var23 = BlockUtil.x(this.R, this.Y$r1(), downPlace.c());
         this.isKeyDown();
         this.Y(68163567833154L);
         if (!this.G && this.S.Q(10L)) {
            if (var23 != null && !this.y) {
               this.dt = true;
               this.K = 0;
               this.y = true;
            }

            if (var23 == null || !(var23.q.getY() <= f.thePlayer.posY - 1.0) && !downPlace.c()) {
               if (!f.gameSettings.keyBindForward.isKeyDown()
                  && !f.gameSettings.keyBindLeft.isKeyDown()
                  && !f.gameSettings.keyBindRight.isKeyDown()
                  && !f.gameSettings.keyBindBack.isKeyDown()) {
                  KeyBindUtil.o(99363263780575L, f.gameSettings.keyBindJump.getKeyCode());
               } else if (this.x < this.F) {
                  KeyBindUtil.A(82009306480869L, f.gameSettings.keyBindJump.getKeyCode(), false);
               }
            } else {
               this.v(var23,MathUtil.h(84.0F, 99.0F));
               this.u = true;
            }
         }

         if (var23 != null
            && (var23.q.getY() <= f.thePlayer.posY - 1.0 || downPlace.c())
            && (this.K >= (x$r1(44418900924704L) ? diagonalAirDelay.L() : straightAirDelay.L()) || this.x < this.F)) {
            this.dt = false;
            this.M(var23);
         }
      } else {
         this.k = false;
         this.g = false;
         this.R = (int)f.thePlayer.posY - 1;
         this.U();
      }
   }

   public void onHeldItemChange(HeldItemChangeEvent var1, long var2) {


      var1.I(21307, 3074332907L);
   }



   private double q(AxisAlignedBB var1) {
      AxisAlignedBB var2 = new AxisAlignedBB(
         var1.minX, var1.minY - 0.01, var1.minZ, var1.maxX, var1.minY, var1.maxZ
      );
      List var3 = f.theWorld.getCollidingBoundingBoxes(f.thePlayer, var2);
      if (var3.isEmpty()) {
         return Double.NaN;
      }

      double var4 = (var1.minX + var1.maxX) * 0.5;
      double var6 = (var1.minZ + var1.maxZ) * 0.5;
      double var8 = Double.MAX_VALUE;

      for (AxisAlignedBB var11 : (Iterable<AxisAlignedBB>)(var3)) {
         double var12 = Math.max(var11.minX, Math.min(var4, var11.maxX));
         double var14 = Math.max(var11.minZ, Math.min(var6, var11.maxZ));
         double var16 = Math.abs(var4 - var12);
         double var18 = Math.abs(var6 - var14);
         var8 = Math.min(var8, Math.max(var16, var18));
      }

      return var8;
   }


   public void onPreMouseInput(long var1, PreMouseInputEvent var3) {

      this.e = false;
      if (!this.Y()) {
         this.T(false);
      } else {
         this.T(true);
         if (this.u && f.thePlayer.onGround) {
            this.u = false;
         }

         if (!this.k && !this.g) {
            this.k = true;
         } else if (this.k) {
            this.g = true;
            this.k = false;
         }

         if (this.dt) {
            this.K++;
         }

         this.H();
         if (ItemUtil.u(f.thePlayer.getHeldItem()) && f.currentScreen == null) {
            if (System.currentTimeMillis() - this.s > CombatUtil.q() + 500) {
               this.Q(angleStep.L());
            }

            this.H = true;
            this.dw = false;
            switch (mode.Y()) {
               case "NORMAL":
                  this.U();
                  break;
               case "LEGIT":
                  this.D(0L);
                  break;
               case "KEEP_Y":
                  this.N();
            }

            var3.T(true);
         } else {
            if (this.d2) {
               RotationManager.O(123115463851087L);
               this.d2 = false;
            }
         }
      }
   }

   private void isKeyDown() {

      this.F = x$r1(44418900924704L) ? (int)diagonalJumpBlocks.L() : (int)straightJumpBlocks.L();
      if (!f.gameSettings.keyBindForward.isKeyDown()
         && !f.gameSettings.keyBindLeft.isKeyDown()
         && !f.gameSettings.keyBindRight.isKeyDown()
         && !f.gameSettings.keyBindBack.isKeyDown()) {
         this.L = true;
         this.N = false;
         this.m = false;
      } else {
         if (!this.m) {
            this.m = true;
            this.N = false;
         }

         if (f.thePlayer.onGround && !this.G && this.x >= this.F) {
            this.L = true;
            this.G = true;
         }
      }

      if (this.m) {
         this.N = true;
      }
   }

   public static boolean Z() {
      return dk;
   }

   public void onRender2D(long var1, Render2DEvent var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {






      if (itemCounter.c()) {
         int var20;
         switch (espColor.Y()) {
            case "THEME":
               var20 = Theme.S(0.0, 35338930340239L);
               break;
            case "THEME_CUSTOM":
               var20 = Theme.X(65301174328177L, 0.0);
               break;
            default:
               var20 = customColor.k(96531491288662L);
         }

         ItemStack var38 = null;
         int var39 = f.thePlayer.inventory.currentItem;
         if (this.v == 0) {
            var39--;
         }

         for (int var23 = var39; var23 > var39 - 9; var23--) {
            int var24 = (var23 % 9 + 9) % 9;
            ItemStack var25 = f.thePlayer.inventory.getStackInSlot(var24);
            if (ItemUtil.u(var25)) {
               var38 = var25;
               break;
            }
         }

         float var40 = 0.8F;
         CustomFont var41 = Font.s(0L);
         int var42 = 0;

         for (int var26 = 0; var26 < InventoryPlayer.getHotbarSize(); var26++) {
            if (f.thePlayer.inventory.getStackInSlot(var26) != null && ItemUtil.u(f.thePlayer.inventory.getStackInSlot(var26))) {
               var42 += f.thePlayer.inventory.getStackInSlot(var26).stackSize;
            }
         }

         ScaledResolution var43 = var3.C;
         String var27 = var42 + " blocks left";
         int var28 = var42 > 32 ? -1 : -43691;
         float var29 = 3.0F * var40;
         float var30 = var41.R(var27, 52019766876817L) * var40;
         float var31 = var41.o(60714858652844L) * var40;
         float var32 = 16.5F * var40;
         float var33 = var29 * 3.0F + var32 + var30;
         float var34 = var29 * 2.0F + var32;
         float var35 = var43.getScaledWidth() / 2.0F - var33 / 2.0F;
         float var36 = var43.getScaledHeight() / 2.0F + var43.getScaledHeight() / 4.0F;
         GlStateManager.pushMatrix();
         GlStateManager.scale(var40, var40, var40);
         Expo.util.render.RenderUtil.J( var35 / var40, (var36 - var34 / 2.0F) / var40, (var35 + var33) / var40, (var36 + var34 / 2.0F) / var40, 3.0F, dO, var20, var20);
         Expo.util.render.RenderUtil.m(var38 != null ? var38 : c, (int)((var35 + var29) / var40), (int)((var36 - var32 / 2.0F) / var40));
         var41.v(var27, (var35 + var29 * 2.0F + var32) / var40, (var36 - var31 / 2.0F) / var40, var28, 88827598794260L, true);
         GlStateManager.popMatrix();
      }
   }

   public int q() {
      if (f.thePlayer == null) {
         return -1;
      } else {
         return this.b ? this.dl : f.thePlayer.inventory.currentItem;
      }
   }

   public static boolean x$r1(long var0) {
      var0 = bb ^ var0;
      int var2 = (int)((var0 ^ 135513394841172L) >>> 48);
      int var3 = (int)((var0 ^ 135513394841172L) << 16 >>> 32);
      int var4 = (int)((var0 ^ 135513394841172L) << 48 >>> 48);
      return H(false, (char)var2, var3, var4);
   }


   public Scaffold(long var1) {
      super((((bb ^ (var1)) ^ 25905238840540L) >>> 16), (char)((int)(((((bb ^ (var1)) ^ 25905238840540L) << 48) >>> 48))));
      // add code
      this.declare("Scaffold", Category.World, "Bridge automatically for you");
      var1 = bb ^ var1;
      this.dW = new TimerUtil();
      this.S = new TimerUtil();
      this.dS = new TimerUtil();
      this.dV = new ArrayList<>();
      this.e = false;
      this.d2 = false;
      this.L = true;
      this.b = false;
      this.d = false;
      this.T = -1;
      this.de = -1;
      this.G = false;
      this.x = 0;
      this.F = 0;
      this.dZ = false;
      this.dw = false;
      this.N = false;
      this.m = false;
      this.H = false;
      this.y = false;
      this.s = System.currentTimeMillis();
      this.h = false;
      this.K = 0;
      this.dt = false;
      this.k = false;
      this.g = false;
      this.v = 0;
      this.u = false;
   }

   private String d(long var1) {



      String var7;
      switch (mode.Y()) {
         case "LEGIT":
            var7 = legitModeRotation.Y();
            break;
         case "KEEP_Y":
            if (keepYOnRightClick.c() && !KeyBindUtil.V(f.gameSettings.keyBindUseItem.getKeyCode(), 64165991731362L)) {
               var7 = normalModeRotation.Y();
            } else if (x$r1(44418900924704L)) {
               var7 = "NORMAL";
            } else {
               var7 = keepYModeRotation.Y();
            }
            break;
         default:
            var7 = normalModeRotation.Y();
      }

      return var7;
   }

   private boolean M(PlacementTarget var1) {

      if (!OutgoingPacketState.Y()) {
         return false;
      }

      this.s = System.currentTimeMillis();
      float[] var6 = this.J(var1.q, 19296727618367L, var1.Z);
      MovingObjectPosition var7 = RaytraceUtil.M();
      boolean var8 = var7 != null;
      float var9 = var1.o ? 1.0F : (!strictAimCheck.c() && !this.e ? 30.0F : 1.0F);
      boolean var10 = Math.abs(MathUtil.M(RotationManager.r, var6[0])) <= var9;
      boolean var11 = Math.abs(MathUtil.M(RotationManager.G, var6[1])) <= var9;
      boolean var12 = var8 && var7.typeOfHit == MovingObjectType.BLOCK && BlockUtil.p(var7.getBlockPos(), var1.q);
      boolean var13 = var12 && var7.sideHit == var1.Z;
      boolean var14 = this.dZ;
      this.dZ = var10 && var11;
      if ((!aimCheck.c() || (var1.o ? var10 && var11 && var14 : (strictAimCheck.c() ? var12 && var13 : var10 && var11 || var12))) && this.v > 0) {
         Vec3 var15 = BlockUtil.f(var1.q, var1.Z);
         if (var12) {
            var15 = var7.hitVec;
         }

         if (CombatUtil.u(
            var1.q,
            var1.Z,
            var15,
            swing.c(),
            !fakeItem.c()
               || f.thePlayer.inventory.getStackInSlot(this.dl) != null
                  && f.thePlayer.inventory.getStackInSlot(this.dl).getItem() instanceof ItemBlock
         )) {
            if (f.playerController.getCurrentGameType() != GameType.CREATIVE) {
               this.v--;
            }

            this.dV.add(new Pair<>(var1.q.offset(var1.Z), System.currentTimeMillis()));
            if (f.thePlayer.onGround) {
               this.x++;
            } else {
               this.x = 0;
            }

            return true;
         }
      }

      return false;
   }

   private AxisAlignedBB K(long var1) {



      AxisAlignedBB var6 = f.thePlayer.getEntityBoundingBox();
      if (MoveUtil.f() == 0 && MoveUtil.K() == 0) {
         return var6.offset(f.thePlayer.motionX, 0.0, f.thePlayer.motionZ);
      }

      double var7 = f.thePlayer.isSprinting() ? 0.2873 : 0.221;
      float var9 = MoveUtil.X(11188, (short)64331);
      float var10 = MathHelper.sin(var9 * (float) Math.PI / 180.0F);
      float var11 = MathHelper.cos(var9 * (float) Math.PI / 180.0F);
      double var12 = -var10 * var7;
      double var14 = var11 * var7;
      return var6.offset(var12, 0.0, var14);
   }



   static {
      // add code
      keepYJumpForwardChance = new PercentageSetting("Keep-Y-jump-forward-chance", 100);
      rotationSmoothing = new PercentageSetting("Rotation-smoothing", 0);
      customColor = new ColorSetting("Custom-color", "FFFFFF");
   }
   static {
      // add code
      aimCheck = new BooleanSetting("Aim-check", true);
      strictAimCheck = new BooleanSetting("Strict-aim-check", true);
      swing = new BooleanSetting("Swing", true);
      autoItem = new BooleanSetting("Auto-item", true);
      keepYOnRightClick = new BooleanSetting("Keep-Y-on-right-click", false);
      itemCounter = new BooleanSetting("Item-counter", true);
      keepYBlinkRotation = new BooleanSetting("Keep-Y-blink-rotation", false);
      downPlace = new BooleanSetting("Down-place", false);
      dontRenderRotation = new BooleanSetting("Dont-render-rotation", false);
      showTargetShade = new BooleanSetting("Show-target-shade", false);
      showTargetOutline = new BooleanSetting("Show-target-outline", true);
      outlineFadeOut = new BooleanSetting("Outline-fade-out", true);
   }
   static {
      // add code
      offsetRotationOffset = new NumberSetting("Offset-rotation-offset", 0.15F, 0.0F, 1.0F, 0.01F);
      legitModeEdgeOffset = new NumberSetting("Legit-mode-edge-offset", 0.0F, 0.0F, 0.3F, 0.01F);
      legitModeUnsneakDelay = new NumberSetting("Legit-mode-unsneak-delay", 50.0F, 50.0F, 300.0F, 5.0F);
      straightJumpBlocks = new NumberSetting("Straight-jump-blocks", 0.0F, 0.0F, 3.0F, 1.0F);
      diagonalJumpBlocks = new NumberSetting("Diagonal-jump-blocks", 0.0F, 0.0F, 3.0F, 1.0F);
      straightAirDelay = new NumberSetting("Straight-air-delay", 1.0F, 0.0F, 4.0F, 1.0F);
      diagonalAirDelay = new NumberSetting("Diagonal-air-delay", 1.0F, 0.0F, 4.0F, 1.0F);
      angleStep = new NumberSetting("Angle-step", 90.0F, 1.0F, 180.0F, 1.0F);
   }
   static {
      // add code
      mode = new ModeSetting("Mode", "NORMAL", "LEGIT", "KEEP_Y");
      normalModeRotation = new ModeSetting("Normal-mode-rotation", false, "DIAGONAL", "BACK", "NORMAL", "OFFSET", "DIAGONAL");
      legitModeRotation = new ModeSetting("Legit-mode-rotation", false, "DIAGONAL", "BACK", "NORMAL", "OFFSET", "DIAGONAL");
      keepYModeRotation = new ModeSetting("Keep-Y-mode-rotation", false, "DIAGONAL", "BACK", "NORMAL", "OFFSET", "DIAGONAL");
      moveFix = new ModeSetting("Move-fix", "SILENT", "STRICT", "NONE");
      espColor = new ModeSetting("ESP-Color", "THEME", "THEME_CUSTOM", "CUSTOM");
   }
}
