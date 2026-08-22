package Expo.module.impl.world;

import Expo.module.Category;

import Expo.enums.RotationMode;
import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.BedNukerBinder;
import Expo.event.events.ClickMouseEvent;
import Expo.event.events.IsPressedEvent;
import Expo.event.events.PreMouseInputEvent;
import Expo.event.events.PreTickEvent;
import Expo.event.events.ReceivePacketEvent;
import Expo.event.events.Render2DEvent;
import Expo.event.events.Render3DEvent;
import Expo.event.events.SendPacketEvent;
import Expo.internal.accessor.PlayerControllerStateAccessor;
import Expo.module.PriorityModule;
import Expo.module.impl.configuration.Font;
import Expo.module.impl.configuration.Theme;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.ColorSetting;
import Expo.setting.settings.ModeSetting;
import Expo.setting.settings.NumberSetting;
import Expo.util.BlockUtil;
import Expo.util.CombatUtil;
import Expo.util.ItemUtil;
import Expo.util.KeyBindUtil;
import Expo.util.MinecraftRef;
import Expo.util.Pair;
import Expo.util.RaytraceUtil;
import Expo.util.RotationManager;
import Expo.util.RotationUtil;
import Expo.util.TimerUtil;
import Expo.util.packet.IncomingPacketHold;
import Expo.util.packet.PacketManager;
import Expo.util.render.CustomFont;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBed.EnumPartType;
import net.minecraft.block.BlockBed;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.network.play.client.C07PacketPlayerDigging.Action;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C0APacketAnimation;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.potion.Potion;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;


public class BedNuker extends PriorityModule implements EventSubscriber {
   private final TimerUtil b;
   public static NumberSetting delayVelocityTicks;
   public static BooleanSetting autoItem;
   private final TimerUtil o;
   private static String[] gb;
   private boolean T;
   private static Map ib;
   private boolean C;
   private static String[] hb;
   private BlockPos Do;
   public static NumberSetting delayVelocityRange;
   public static BooleanSetting showTargetShade;
   private static long bb;
   private boolean O;
   private int x;
   public static boolean y;
   private boolean M;
   public static BooleanSetting whitelistOwnBed;
   public static NumberSetting blinkRange;
   public static BooleanSetting legit;
   public static BooleanSetting keepRotation;
   public static boolean DJ;
   public static NumberSetting fov;
   public static int D6;
   private int K;
   public static boolean B;
   public static ModeSetting moveFix;
   private static long[] mb;
   private static long pb;
   public static BooleanSetting requireClick;
   private boolean v;
   private boolean c;
   public static BooleanSetting showTargetOutline;
   private static Map ob;
   public static BooleanSetting ignoreOutsideLayer;
   private boolean p;
   public static NumberSetting range;
   public static List<BlockPos> D;
   private BlockPos t;
   private boolean e;
   public static BooleanSetting showTargetPercentage;
   private TimerUtil E;
   public static ModeSetting showTargetColor;
   public static NumberSetting blinkDisableRange;
   public static BooleanSetting swing;
   public static NumberSetting blinkDuration;
   public static ColorSetting customColor;
   private BlockPos Dy;
   public static BooleanSetting showTargetBar;
   private int L;
   private List<Pair<BlockPos, EnumFacing>> Y;

   private BlockPos f(long var1, BlockPos var3) {


      IBlockState var6 = f.theWorld.getBlockState(var3);
      if (var6.getBlock() instanceof BlockBed) {
         ArrayList<BlockPos> var7 = new ArrayList<>();
         EnumPartType var8 = (EnumPartType)var6.getValue(BlockBed.PART);
         EnumFacing var9 = (EnumFacing)var6.getValue(BlockBed.FACING);

         for (BlockPos var11 : Arrays.asList(new BlockPos[]{var3, var3.offset(var8 == EnumPartType.HEAD ? var9.getOpposite() : var9)})) {
            for (EnumFacing var13 : Arrays.asList(new EnumFacing[]{EnumFacing.UP, EnumFacing.NORTH, EnumFacing.EAST, EnumFacing.SOUTH, EnumFacing.WEST})) {
               Block var14 = f.theWorld.getBlockState(var11.offset(var13)).getBlock();
               if (BlockUtil.f(var14)) {
                  return null;
               }

               if (!(var14 instanceof BlockBed) && RaytraceUtil.Y(var11.offset(var13), range.L(), 119767551018300L)) {
                  var7.add(var11.offset(var13));
               }
            }
         }

         if (!var7.isEmpty()) {
            var7.sort((var1x, var2) -> {

               long var7x = 68314340999508L;
               int var9x = Float.compare(this.w(var2, var7x), this.w(var1x, var7x));
               return var9x != 0 ? var9x : Double.compare(RaytraceUtil.p(var1x, 12489541448578L), RaytraceUtil.p(var2, 12489541448578L));
            });
            return (BlockPos)var7.get(0);
         }
      }

      return null;
   }

   private void S(boolean var1, int var2, char var3, int var4) {
      long var5 = ((long)var2 << 32 | (long)var3 << 48 >>> 32 | (long)var4 << 48 >>> 48) ^ bb;
      long var13 = var5 ^ 64956863061563L;
      long var15 = var5 ^ 16167058551979L;
      this.T(false);
      this.r(0L);
      this.x$r2();
      D6 = -1;
      this.x = 0;
      this.Do = null;
      y = false;
      this.M = false;
      this.v = false;
      this.Dy = null;
      this.Y.clear();
      if (DJ) {
         f.playerController.resetBlockRemoving();
         DJ = false;
      }

      if (this.C && !var1) {
         if (this.L != -1) {
            ItemUtil.P( this.L);
         }

         this.L = -1;
         this.C = false;
      }

      if (this.e) {
         RotationManager.O(var13);
         this.e = false;
      }

      if (this.O) {
         KeyBindUtil.o(var15, f.gameSettings.keyBindAttack.getKeyCode());
         this.O = false;
      }
   }

   public void onClickMouse(long var1, ClickMouseEvent var3) {


      if (DJ) {
         var3.I(21307, 3074332907L);
      }
   }

   public void A(long var1) {
      int var3 = (int)((var1 ^ 107252723788892L) >>> 32);
      int var4 = (int)((var1 ^ 107252723788892L) << 32 >>> 48);
      int var5 = (int)((var1 ^ 107252723788892L) << 48 >>> 48);
      this.S(true, var3, (char)var4, var5);
   }

   private float w(BlockPos var1, long var2) {
      long var6 = 79882628846095L;
      int var10 = ItemUtil.e(0L, BlockUtil.a(var1));
      IBlockState var11 = f.theWorld.getBlockState(var1);
      int var12 = this.Y(var10 != -1 ? var10 : f.thePlayer.inventory.currentItem, var11.getBlock());
      return w(var11, var1, var12, f.thePlayer.onGround, var6);
   }

   public void onRender3D(long var1, Render3DEvent var3) {




      if (this.Do != null) {
         int var12;
         switch (showTargetColor.Y()) {
            case "THEME":
               var12 = Theme.S(0.0, 35338930340239L);
               break;
            case "THEME_CUSTOM":
               var12 = Theme.X(65301174328177L, 0.0);
               break;
            default:
               var12 = customColor.k(96531491288662L);
         }

         Expo.util.render.RenderUtil.c(this.Do, 45570328859791L, var12, showTargetOutline.c(), showTargetShade.c());
      }
   }

   private static float U(IBlockState var0, int var1, boolean var2) {
      ItemStack var3 = f.thePlayer.inventory.getStackInSlot(var1);
      float var4 = var3 != null ? 1.0F : ItemUtil.l(var3, var0.getBlock());
      if (var4 > 1.0F) {
         int var5 = EnchantmentHelper.getEnchantmentLevel(Enchantment.efficiency.effectId, var3);
         if (var5 > 0) {
            var4 += var5 * var5 + 1;
         }
      }

      if (f.thePlayer.isPotionActive(Potion.digSpeed)) {
         var4 *= 1.0F + (f.thePlayer.getActivePotionEffect(Potion.digSpeed).getAmplifier() + 1) * 0.2F;
      }

      if (f.thePlayer.isPotionActive(Potion.digSlowdown)) {
         switch (f.thePlayer.getActivePotionEffect(Potion.digSlowdown).getAmplifier()) {
            case 0:
               var4 *= 0.3F;
               break;
            case 1:
               var4 *= 0.09F;
               break;
            case 2:
               var4 *= 0.0027F;
               break;
            default:
               var4 *= 8.1E-4F;
         }
      }

      if (f.thePlayer.isInsideOfMaterial(Material.water) && !EnchantmentHelper.getAquaAffinityModifier(f.thePlayer)) {
         var4 /= 5.0F;
      }

      if (!var2) {
         var4 /= 5.0F;
      }

      return var4;
   }

   static {
      bb = 106241572623021L;
      D = new ArrayList<>();
      DJ = false;
      y = false;
      B = false;
      D6 = -1;
      ib = new HashMap(13);
      gb = new String[]{"Y\u00c1\u00a3<\u0008c\u00c0J", "\u00bb\u00b5\u0013u\u00e6X@=", "y,>fZn+h>\u000f\u00c6O\u00f8\u0097\u0089\u00e0", "T\u0095ED1\u00d3+\u00af", "\u00db\u00d5\u00ba-\u000e\u008d\u00ad\u00bb", "=[\u00ff'%Q\u0090\u0004", "\u000f\u00fdu\u00aeV\u007f\u00ce^", "\r\u00d47{(I\u00ad<:\u00f4\u00c9\u00dd\u0091\u0004\u001d\u00a3"};
      hb = new String[8];
      ob = new HashMap(13);
      mb = new long[]{6875542412366896481L, 6068477483277048441L, 3263470159249675114L, -8886131664387034906L, -485829224368947263L, 2483614103015192913L, -4396291901987456104L, -2617343952690244247L, 3204346084064542728L, -825404432479657210L};
      pb = 10L;
   }

   private boolean D(List<BlockPos> var1, BlockPos var2) {
      if (var1 != null && var2 != null) {
         for (BlockPos var4 : var1) {
            if (BlockUtil.p(var4, var2)) {
               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   private void r(long var1) {
      if (this.T) {
         IncomingPacketHold.m();
         IncomingPacketHold.X(false);
         this.T = false;
      }

      this.K = -1;
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
            byte var8 = var0[++var4];
            var6 = (char)(var6 | (char)(var8 & 63));
            var3[var1++] = var6;
         } else if (var4 < var2 - 2) {
            char var12 = (char)((char)(var5 & 15) << '\f');
            byte var9 = var0[++var4];
            var12 = (char)(var12 | (char)(var9 & 63) << 6);
            var9 = var0[++var4];
            var12 = (char)(var12 | (char)(var9 & 63));
            var3[var1++] = var12;
         }
      }

      return new String(var3, 0, var1);
   }

   private void x$r2() {
      if (this.c) {
         PacketManager.j();
         PacketManager.M(false);
         this.c = false;
      }
   }

   private static void a() {
   }

   private void e(int var1, int var2, short var3) {
      if (!this.Y.isEmpty()) {
         int var10 = ItemUtil.e(0L, BlockUtil.a(this.Y.get(0).a()));
         if (var10 != -1 && autoItem.c()) {
            if (!this.C) {
               this.L = f.thePlayer.inventory.currentItem;
            }

            D6 = var10;
            ItemUtil.P( var10);
            this.C = true;
         }
      }
   }

   private BlockPos e(double var1, long var3) {


      BlockPos var7 = BlockUtil.F(new float[]{0.0F, 90.0F}, 1.5).getBlockPos();
      BlockPos var8 = null;

      for (double var9 = -var1; var9 <= var1; var9 += 0.1) {
         for (double var11 = -var1; var11 <= var1; var11++) {
            for (double var13 = -var1; var13 <= var1; var13 += 0.1) {
               BlockPos var15 = var7.add(var9, var11, var13);
               if (f.theWorld.getBlockState(var15).getBlock() instanceof BlockBed
                  && (var8 == null || RaytraceUtil.p(var15, 12489541448578L) < RaytraceUtil.p(var8, 12489541448578L))
                  && this.p(var15)) {
                  var8 = var15;
               }
            }
         }
      }

      return whitelistOwnBed.c() && this.D(D, var8) ? null : var8;
   }

   private List f(long var1, BlockPos var3, double var4) {



      if (!RaytraceUtil.Y(var3, var4, 119767551018300L)) {
         return new ArrayList();
      }

      if (BlockUtil.a$r1(var3)) {
         return new ArrayList();
      }

      BlockPos var10 = this.f(42120104531354L, var3);
      if (var10 == null) {
         return new ArrayList();
      }

      if (!legit.c()) {
         ArrayList var31 = new ArrayList();
         var31.add(new Pair<>(var10, BlockUtil.D(var10)));
         return var31;
      }

      BlockPos var11 = null;
      double var12 = Double.MAX_VALUE;
      int var14 = (int)Math.min(var4, 8.0);
      BlockPos var15 = Expo.util.ClientUtil.p();

      for (int var16 = var3.getX() - var14; var16 <= var3.getX() + var14; var16++) {
         for (int var17 = var3.getY(); var17 <= var3.getY() + var14; var17++) {
            for (int var18 = var3.getZ() - var14; var18 <= var3.getZ() + var14; var18++) {
               BlockPos var19 = new BlockPos(var16, var17, var18);
               if (BlockUtil.a$r1(var19) && BlockUtil.a(var19) != Blocks.bed) {
                  double var20 = BlockUtil.g(var19, var3);
                  double var22 = BlockUtil.g(var19, var15);
                  double var24 = var20 + var22 * 0.1;
                  if (var11 == null || var24 < var12) {
                     var11 = var19;
                  }
               }
            }
         }
      }

      if (var11 == null) {
         return new ArrayList();
      }

      ArrayList<Pair> var32 = new ArrayList<>();
      int var33 = var11.getX();
      int var34 = var11.getY();
      int var35 = var11.getZ();
      int var36 = var3.getX();
      int var21 = var3.getY();
      int var37 = var3.getZ();
      ArrayList var23 = new ArrayList();
      int var38 = (int)(var4 * 3.0);
      int var25 = 0;

      while ((var33 != var36 || var34 != var21 || var35 != var37) && var25++ <= var38) {
         BlockPos var26 = new BlockPos(var33, var34, var35);
         if (var23.contains(var26)) {
            break;
         }

         var23.add(var26);
         if (!BlockUtil.a$r1(var26)) {
            EnumFacing var27 = BlockUtil.D(var26);
            var32.add(new Pair<>(var26, var27));
         }

         int var40 = var36 - var33;
         int var28 = var21 - var34;
         int var29 = var37 - var35;
         if (Math.abs(var40) >= Math.abs(var28) && Math.abs(var40) >= Math.abs(var29)) {
            var33 += var40 > 0 ? 1 : -1;
         } else if (Math.abs(var29) >= Math.abs(var40) && Math.abs(var29) >= Math.abs(var28)) {
            var35 += var29 > 0 ? 1 : -1;
         } else {
            var34 += var28 > 0 ? 1 : -1;
         }

         if (!RaytraceUtil.Y(new BlockPos(var33, var34, var35), var4, 119767551018300L)) {
            break;
         }
      }

      EnumFacing var39 = BlockUtil.D(var3);
      var32.add(new Pair<>(var3, var39));
      if (ignoreOutsideLayer.c() && var32.size() >= 3) {
         var32.remove(0);
      }

      var32.removeIf(var0 -> BlockUtil.a$r1((BlockPos)var0.a()));
      return var32;
   }

   public void onPreTick(long var1, PreTickEvent var3) {

      boolean var10 = this.Do == null;
      if (this.K >= 0) {
         this.K++;
         boolean var11 = var10 || delayVelocityRange.L() != -1.0F && !RaytraceUtil.Y(this.Do, delayVelocityRange.L(), 119767551018300L);
         if (this.K >= delayVelocityTicks.L() || var11) {
            this.r(0L);
         }
      }

      boolean var14 = blinkDisableRange.L() != -1.0F && blinkRange.L() != -1.0F && blinkDuration.L() != -1.0F;
      // add code
      boolean var12 = !var10 && RaytraceUtil.Y(this.Do, blinkRange.L(), 119767551018300L) && !RaytraceUtil.Y(this.Do, blinkDisableRange.L(), 119767551018300L);
      if (!var10 && var14 && var12) {
         if (this.E.L((long)blinkDuration.L(), true)) {
            if (this.c) {
               PacketManager.j();
            }
         } else {
            PacketManager.M(true);
            this.c = true;
         }
      } else {
         this.x$r2();
      }
   }

   private void Y(ReceivePacketEvent var1, long var2) {


      S12PacketEntityVelocity var7 = (S12PacketEntityVelocity)var1.d;
      if (this.T) {
         IncomingPacketHold.p().add(var7);
         var1.I(21307, 3074332907L);
      } else {
         IncomingPacketHold.p().add(var7);
         IncomingPacketHold.X(true);
         this.T = true;
         this.K = 0;
         var1.I(21307, 3074332907L);
      }
   }

   public void onPreMouseInput(PreMouseInputEvent var1, long var2) {

      int var6 = 51853;
      int var9 = 49506;


      int var16 = 61657;

      if (!this.Y()) {
         this.S(false, 13386, (char)46320, var16);
      } else if (requireClick.c() && !KeyBindUtil.V(f.gameSettings.keyBindAttack.getKeyCode(), 64165991731362L)) {
         this.S(false, 13386, (char)46320, var16);
      } else if (this.Dy != null && !RotationUtil.B((char)0, 219718502, this.Dy, (short)var6, BlockUtil.D(this.Dy), fov.L())) {
         this.S(false, 13386, (char)46320, var16);
      } else {
         if (!this.M) {
            this.Y = new ArrayList<>();
            BlockPos var19 = this.e(range.L(), 81661479257458L);
            if (var19 == null) {
               this.S(false, 13386, (char)46320, var16);
               return;
            }

            this.Dy = var19;
            this.v = false;
            this.M = true;
         } else if (!this.v && this.Dy != null && !BlockUtil.a$r1(this.Dy)) {
            this.Y = this.f(25595029107709L, this.Dy, range.L());
            this.x = this.Y.size() + 1;
            this.v = true;
         }

         if (this.v) {
            this.T(true);
            this.O = true;
            if (BlockUtil.a$r1(this.Dy)) {
               this.S(false, 13386, (char)46320, var16);
               return;
            }

            if (!this.Y.isEmpty()) {
               if (BlockUtil.a$r1(this.Y.get(0).a())) {
                  this.Y.remove(0);
               } else {
                  this.a(25672, this.Y.get(0).a(), 10551, this.Y.get(0).p(), (char)var9);
               }
            } else if (this.Dy != null) {
               this.a(25672, this.Dy, 10551, BlockUtil.D(this.Dy), (char)var9);
            } else {
               this.S(false, 13386, (char)46320, var16);
            }
         }
      }
   }


   public final void x(long var1, EventBus var3) {
      BedNukerBinder.d(var3, this);
   }

   public void onRender2D(long var1, Render2DEvent var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {







      if (this.Do != null) {
         int var22;
         switch (showTargetColor.Y()) {
            case "THEME":
               var22 = Theme.S(0.0, 35338930340239L);
               break;
            case "THEME_CUSTOM":
               var22 = Theme.X(65301174328177L, 0.0);
               break;
            default:
               var22 = customColor.k(96531491288662L);
         }

         CustomFont var30 = Font.s(0L);
         if (showTargetBar.c()) {
            ScaledResolution var31 = var3.C;
            float var25 = PlayerControllerStateAccessor.s(0L, f.playerController);
            if (!this.Y.isEmpty() && var25 == 0.0F && BlockUtil.a$r1(this.Y.get(0).a())) {
               var25 = 1.0F;
            }

            float var26 = (this.x - 1 - this.Y.size() + var25) / this.x;
            if (this.Dy != null && var25 == 0.0F && BlockUtil.a$r1(this.Dy)) {
               var26 = 1.0F;
            }

            int var27 = (int)(var31.getScaledWidth() / 2.0F - 50.0F + 100.0F * var26);
            int var28 = var31.getScaledHeight() / 2 + 63;
            Expo.util.render.RenderUtil.c(125644905353792L, var31.getScaledWidth() / 2.0F - 50.0F - 2.0F, var28, var27 + 2, var28 + var30.o(60714858652844L) + 2.0F, var22);
         }

         if (showTargetPercentage.c()) {
            ScaledResolution var32 = var3.C;
            float var33 = PlayerControllerStateAccessor.s(0L, f.playerController);
            if (!this.Y.isEmpty() && var33 == 0.0F && BlockUtil.a$r1(this.Y.get(0).a())) {
               var33 = 1.0F;
            }

            int var34 = (int)((this.x - 1 - this.Y.size() + var33) / this.x * 100.0F);
            if (this.Dy != null && var33 == 0.0F && BlockUtil.a$r1(this.Dy)) {
               var34 = 100;
            }

            String var35 = var34 + "%";
            var30.T(
               37697014677608L,
               var35,
               var32.getScaledWidth() / 2.0F - var30.R(var35, 52019766876817L) / 2.0F,
               var32.getScaledHeight() / 2.0F + 65.0F,
               16777215
            );
         }
      }
   }

   public void onReceivePacket(ReceivePacketEvent var1, long var2) {


      if (this.Do != null && var1.d instanceof S12PacketEntityVelocity) {
         S12PacketEntityVelocity var8 = (S12PacketEntityVelocity)var1.d;
         if (var8.getEntityID() == f.thePlayer.getEntityId() && delayVelocityRange.L() != -1.0F && RaytraceUtil.Y(this.Do, delayVelocityRange.L(), 119767551018300L)) {
            this.Y(var1, 66084869442411L);
         }
      }
   }

   private void H(long var1, BlockPos var3, EnumFacing var4) {
      var1 = bb ^ var1;
      int var5 = (int)((var1 ^ 7766062394023L) >>> 48);
      int var6 = (int)((var1 ^ 7766062394023L) << 16 >>> 32);
      int var7 = (int)((var1 ^ 7766062394023L) << 48 >>> 48);
      long var8 = var1 ^ 58126142170714L;
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

      if (this.o.L(1L, true)) {
         float[] var13 = RotationUtil.S((char)var5, var6, (char)var7, var3, var4);
         RotationManager.N(var8, var13[0], var13[1]);
         this.e = true;
         y = true;
      }
   }

   private int Y(int var1, Block var2) {
      ItemStack var5 = f.thePlayer.inventory.getStackInSlot(var1);
      int var6 = var1;
      float var7 = this.t(var5, var2);

      for (int var8 = 0; var8 < 9; var8++) {
         ItemStack var9 = f.thePlayer.inventory.getStackInSlot(var8);
         if (var9 != null) {
            float var10 = this.t(var9, var2);
            if (var10 > var7) {
               var6 = var8;
            }
         }
      }

      return var6;
   }

   private static boolean b(Block var0, int var1, long var2) {
      var2 = bb ^ var2;
      int var4 = (int)((var2 ^ 24499496563909L) >>> 56);
      if (var0.getMaterial().isToolNotRequired()) {
         return true;
      }

      ItemStack var7 = MinecraftRef.c((byte)var4,0L).thePlayer.inventory.getStackInSlot(var1);
      return var7 != null && var7.canHarvestBlock(var0);
   }

   private void a(int var1, BlockPos var2, int var3, EnumFacing var4, char var5) {
      long var6 = ((long)var1 << 32 | (long)var3 << 48 >>> 32 | (long)var5 << 48 >>> 48) ^ bb;
      int var8 = (int)((var6 ^ 77336583517815L) >>> 32);
      int var9 = (int)((var6 ^ 77336583517815L) << 32 >>> 48);
      int var10 = (int)((var6 ^ 77336583517815L) << 48 >>> 48);
      int var11 = (int)((var6 ^ 107844589368098L) >>> 32);
      long var13 = var6 ^ 114374648796915L;
      int var15 = (int)((var6 ^ 53475949241110L) >>> 32);
      int var16 = (int)((var6 ^ 53475949241110L) << 32 >>> 48);
      int var17 = (int)((var6 ^ 53475949241110L) << 48 >>> 48);
      long var18 = var6 ^ 17870620714246L;
      long var20 = var6 ^ 117722628796288L;
      if (!RaytraceUtil.Y(var2, range.L(), var13)) {
         this.S(false, var15, (char)var16, var17);
      } else {
         if (!keepRotation.c() && var2 != this.t) {
            this.H(var18, var2, var4);
            this.b.W();
            this.p = true;
         } else if (keepRotation.c()) {
            this.H(var18, var2, var4);
         }

         if (this.p && this.b.Q(pb)) {
            RotationManager.O(var20);
            y = false;
            this.p = false;
         }

         this.e(var8, var9, (short)var10);
         if (!f.thePlayer.isUsingItem()) {
            this.o$r3();
            DJ = true;
            CombatUtil.G(var11, var2, var4);
         }

         this.t = var2;
         this.Do = var2;
      }
   }

   private boolean p(BlockPos var1) {
      return var1 != null
         && (
            f.theWorld.getBlockState(var1.add(1, 0, 0)).getBlock() instanceof BlockBed
               || f.theWorld.getBlockState(var1.add(-1, 0, 0)).getBlock() instanceof BlockBed
               || f.theWorld.getBlockState(var1.add(0, 0, 1)).getBlock() instanceof BlockBed
               || f.theWorld.getBlockState(var1.add(0, 0, -1)).getBlock() instanceof BlockBed
         );
   }

   private void o$r3() {
      if (swing.c()) {
         f.thePlayer.swingItem();
      } else {
         PacketManager.b(new C0APacketAnimation());
      }
   }

   private float t(ItemStack var1, Block var2) {
      float var3 = 1.0F;
      if (var1 != null) {
         var3 = !var1.canHarvestBlock(var2) && var1.getItem() instanceof ItemPickaxe ? 1.0F : var1.getStrVsBlock(var2);
         int var4;
         if (var1.getItem() instanceof ItemTool
            && var3 > 1.0F
            && (var4 = EnchantmentHelper.getEnchantmentLevel(Enchantment.efficiency.effectId, var1)) > 0) {
            var3 += var4 * var4 + 1;
         }
      }

      return var3;
   }

   public static float w(IBlockState var0, BlockPos var1, int var2, boolean var3, long var4) {
      var4 = bb ^ var4;
      long var6 = var4 ^ 25969649659654L;
      int var8 = (int)((var4 ^ 107163582613870L) >>> 56);
      Block var11 = var0.getBlock();
      float var12 = var11.getBlockHardness(MinecraftRef.c((byte)var8,0L).theWorld, var1);
      float var13 = b(var11, var2, var6) ? 30.0F : 100.0F;
      return var12 < 0.0F ? 0.0F : U(var0, var2, var3) / var12 / var13;
   }

   public void onSendPacket(SendPacketEvent var1, long var2) {


      if (DJ && var1.B instanceof C07PacketPlayerDigging && ((C07PacketPlayerDigging)var1.B).getStatus() == Action.ABORT_DESTROY_BLOCK) {
         var1.I(21307, 3074332907L);
      }
   }

   public void onIsPressed(long var1, IsPressedEvent var3) {


      if (DJ && var3.o == f.gameSettings.keyBindAttack.getKeyCode()) {
         var3.I(21307, 3074332907L);
      }
   }

   public String g(long var1) {
      return legit.c() ? "LEGIT" : String.valueOf(range.L());
   }

   public BedNuker(short var1, long var2) {
      super(((((((long)((var1)) << 48) | 33171000103266L) ^ bb) ^ 104466678813848L) >>> 16), (char)((int)((((((((long)((var1)) << 48) | 33171000103266L) ^ bb) ^ 104466678813848L) << 48) >>> 48))));
      // add code
      this.declare("BedNuker", Category.World, "Break the bed near around you");
      this.b = new TimerUtil();
      this.o = new TimerUtil();
      this.Dy = null;
      this.Y = new ArrayList<>();
      this.M = false;
      this.v = false;
      this.C = false;
      this.L = -1;
      this.e = false;
      this.p = false;
      this.O = false;
      this.t = null;
      this.Do = null;
      this.x = 0;
      this.T = false;
      this.K = -1;
      this.c = false;
      this.E = new TimerUtil();
   }

   public void Z(long var1) {
      int var3 = (int)((var1 ^ 88003373745815L) >>> 32);
      int var4 = (int)((var1 ^ 88003373745815L) << 32 >>> 48);
      int var5 = (int)((var1 ^ 88003373745815L) << 48 >>> 48);
      this.S(false, var3, (char)var4, var5);
   }
   static {
      // add code
      customColor = new ColorSetting("Custom-color", "FFFFFF");
   }
   static {
      // add code
      swing = new BooleanSetting("Swing", true);
      requireClick = new BooleanSetting("Require-click", false);
      whitelistOwnBed = new BooleanSetting("Whitelist-own-bed", true);
      ignoreOutsideLayer = new BooleanSetting("Ignore-outside-layer", true);
      autoItem = new BooleanSetting("Auto-item", true);
      keepRotation = new BooleanSetting("Keep-rotation", false);
      legit = new BooleanSetting("Legit", false);
      showTargetShade = new BooleanSetting("Show-target-shade", false);
      showTargetOutline = new BooleanSetting("Show-target-outline", true);
      showTargetPercentage = new BooleanSetting("Show-target-percentage", false);
      showTargetBar = new BooleanSetting("Show-target-bar", false);
   }
   static {
      // add code
      range = new NumberSetting("Range", 4.5F, 0.0F, 8.0F, 0.01F);
      fov = new NumberSetting("FOV", 360.0F, 0.0F, 360.0F, 1.0F);
      delayVelocityTicks = new NumberSetting("Delay-velocity-ticks", -1.0F, -1.0F, 20.0F, 1.0F);
      delayVelocityRange = new NumberSetting("Delay-velocity-range", 7.0F, -1.0F, 20.0F, 1.0F);
      blinkDisableRange = new NumberSetting("Blink-disable-range", 4.0F, -1.0F, 8.0F, 1.0F);
      blinkRange = new NumberSetting("Blink-range", 8.0F, -1.0F, 20.0F, 1.0F);
      blinkDuration = new NumberSetting("Blink-duration", 499.0F, -1.0F, 2000.0F, 10.0F);
   }
   static {
      // add code
      moveFix = new ModeSetting("Move-fix", "SILENT", "STRICT", "NONE");
      showTargetColor = new ModeSetting("Show-target-color", "THEME", "THEME_CUSTOM", "CUSTOM");
   }
}
