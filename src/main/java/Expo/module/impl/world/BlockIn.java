package Expo.module.impl.world;

import Expo.module.Category;

import Expo.enums.RotationMode;
import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.BlockInBinder;
import Expo.event.events.HeldItemChangeEvent;
import Expo.event.events.PreMouseInputEvent;
import Expo.module.PriorityModule;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.ModeSetting;
import Expo.setting.settings.NumberSetting;
import Expo.util.BlockUtil;
import Expo.util.CombatUtil;
import Expo.util.ItemUtil;
import Expo.util.MathUtil;
import Expo.util.RotationManager;
import Expo.util.RotationUtil;
import Expo.util.Sneaky;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockWall;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

public class BlockIn extends PriorityModule implements EventSubscriber {
   private boolean e;
   private BlockPos v;
   public static NumberSetting rotationTolerance;
   private float G;
   public static NumberSetting angleStep;
   private int O;
   private Vec3 T;
   private EnumFacing n;
   public static NumberSetting range;
   private float B;
   private boolean I;
   private static String[] c;
   private static long[] o;
   private static int d;
   private static BlockInFaceOffset[] K;
   private static final double S = 0.2;
   private static final double a = 0.05;
   private BlockPos R;
   private static EnumFacing[] J;
   private boolean Y;
   private static String[] g;
   private boolean y;
   private static Map r;
   private static long b;
   public static BooleanSetting swing;
   public static ModeSetting moveFix;
   private int M;
   private EnumFacing s;
   private static Map h;

   private BlockInPlacement E(long var1) {
      BlockPos var5 = new BlockPos(
         MathHelper.floor_double(f.thePlayer.posX),
         MathHelper.floor_double(f.thePlayer.posY),
         MathHelper.floor_double(f.thePlayer.posZ)
      );
      BlockPos var6 = var5.up();
      double var7 = range.L();
      Vec3 var9 = f.thePlayer.getPositionEyes(1.0F);
      ArrayList<BlockPos> var10 = new ArrayList<BlockPos>(8);

      for (EnumFacing var14 : J) {
         var10.add(var5.offset(var14));
         var10.add(var6.offset(var14));
      }

      ArrayList var26 = new ArrayList(var10.size());

      for (BlockPos var29 : (Iterable<BlockPos>)(var10)) {
         if (BlockUtil.a$r1(var29) && this.j(var29, var5, var6)) {
            var26.add(var29);
         }
      }

      if (var26.isEmpty()) {
         return null;
      }

      Vec3 var28 = this.r(100.0);
      if (var28 != null) {
         var10.sort(
            (var1x, var2) -> {
               double var3x = H(var1x.getX() + 0.5 - var28.xCoord)
                  + H(var1x.getY() + 0.5 - var28.yCoord)
                  + H(var1x.getZ() + 0.5 - var28.zCoord);
               double var5x = H(var2.getX() + 0.5 - var28.xCoord)
                  + H(var2.getY() + 0.5 - var28.yCoord)
                  + H(var2.getZ() + 0.5 - var28.zCoord);
               return Double.compare(var3x, var5x);
            }
         );
         int var30 = 0;

         for (BlockPos var15 : (Iterable<BlockPos>)(var10)) {
            if (var30 >= 3) {
               break;
            }

            if (BlockUtil.a$r1(var15) && this.j(var15, var5, var6)) {
               BlockInPlacement var16 = this.g(Collections.singletonList(var15),0L, var7, var9);
               if (var16 != null) {
                  return var16;
               }

               var30++;
            }
         }
      }

      BlockInPlacement var31 = this.g(var26,0L, var7, var9);
      if (var31 != null) {
         return var31;
      }

      ArrayList var33 = new ArrayList(var26);
      HashSet var34 = new HashSet(var33.size() * 8);

      for (BlockPos var17 : (Iterable<BlockPos>)(var33)) {
         var34.add(var17.toLong());
      }

      for (int var36 = 0; var36 < 5 && !var33.isEmpty(); var36++) {
         ArrayList var37 = new ArrayList(var33.size() * 3);

         for (BlockPos var19 : (Iterable<BlockPos>)(var33)) {
            for (EnumFacing var23 : EnumFacing.values()) {
               BlockPos var24 = var19.offset(var23);
               if (BlockUtil.a$r1(var24) && var34.add(var24.toLong())) {
                  var37.add(var24);
               }
            }
         }

         if (!var37.isEmpty()) {
            BlockInPlacement var38 = this.g(var37,0L, var7, var9);
            if (var38 != null) {
               return var38;
            }
         }

         var33 = var37;
      }

      return null;
   }

   private boolean j(BlockPos var1, BlockPos... var2) {
      for (EnumFacing var6 : EnumFacing.values()) {
         BlockPos var7 = var1.offset(var6);
         if (BlockUtil.a$r1(var7)) {
            boolean var8 = false;

            for (BlockPos var12 : var2) {
               if (var7.equals(var12)) {
                  var8 = true;
                  break;
               }
            }

            if (!var8) {
               return true;
            }
         }
      }

      return false;
   }

   private BlockInPlacement g(List var1, long var2, double var4, Vec3 var6) {
      if (var1 != null && !var1.isEmpty() && this.M >= 0 && this.M <= 8) {
         ItemStack var7 = f.thePlayer.inventory.mainInventory[this.M];
         float var8 = RotationManager.r;
         float var9 = RotationManager.G;
         MovingObjectPosition var10 = this.I(var4, var8, var9);
         if (var10.typeOfHit == MovingObjectType.BLOCK) {
            BlockPos var11 = var10.getBlockPos();
            EnumFacing var12 = var10.sideHit;
            if (!BlockUtil.a$r1(var11) && this.I(var7, var11, var12)) {
               for (BlockPos var14 : (Iterable<BlockPos>)(var1)) {
                  BlockInPlacement var15 = this.j(var4, var8, var9, var11, var12, var14);
                  if (var15 != null) {
                     return var15;
                  }
               }
            }
         }

         double var50 = 0.020000000000000004;
         double var51 = 0.949;
         double var52 = 0.051000000000000004;
         ArrayList<BlockInPlacementCandidate> var17 = new ArrayList<BlockInPlacementCandidate>(
            Math.max(16, var1.size() * 6 * (d + 1) * (d + 1))
         );

         for (BlockPos var19 : (Iterable<BlockPos>)(var1)) {
            for (BlockInFaceOffset var23 : K) {
               BlockPos var24 = new BlockPos(var19.getX() + var23.C, var19.getY() + var23.m, var19.getZ() + var23.I);
               if (!BlockUtil.a$r1(var24) && this.I(var7, var24, var23.z)) {
                  double var25 = var24.getX();
                  double var27 = var24.getY();
                  double var29 = var24.getZ();

                  for (int var31 = 0; var31 <= d; var31++) {
                     boolean var32 = (var31 & 1) == 0;
                     double var33 = B(var31 * 0.2 + i(var50));

                     for (int var35 = 0; var35 <= d; var35++) {
                        double var36 = B(var35 * 0.2 + i(var50));
                        double var38 = var32 ? var36 : 1.0 - var36;
                        double var40;
                        double var42;
                        double var44;
                        if (var23.m != 0) {
                           var40 = var25 + var38;
                           var44 = var29 + var33;
                           var42 = var27 + (var23.m < 0 ? var51 : var52);
                        } else if (var23.I != 0) {
                           var40 = var25 + var38;
                           var42 = var27 + var33;
                           var44 = var29 + (var23.I < 0 ? var51 : var52);
                        } else {
                           var44 = var29 + var38;
                           var42 = var27 + var33;
                           var40 = var25 + (var23.C < 0 ? var51 : var52);
                        }

                        float[] var46 = RotationUtil.W(new Vec3(var40, var42, var44), var6);
                        float var47 = Math.abs(MathHelper.wrapAngleTo180_float(var46[0] - var8));
                        float var48 = Math.abs(var46[1] - var9);
                        if (!(var47 < 0.1F) || !(var48 < 0.1F)) {
                           var17.add(new BlockInPlacementCandidate(var47 + var48, var46[0], var46[1], var24, var23.z, var19));
                        }
                     }
                  }
               }
            }
         }

         var17.sort((var0, var1x) -> Double.compare(var0.M, var1x.M));

         for (BlockInPlacementCandidate var54 : (Iterable<BlockInPlacementCandidate>)(var17)) {
            BlockInPlacement var55 = this.j(var4, var54.W, var54.w, var54.z, var54.B, var54.Q);
            if (var55 != null) {
               return var55;
            }
         }

         return null;
      } else {
         return null;
      }
   }

   private static double i(double var0) {
      return var0 > 0.0 ? (Math.random() * 2.0 - 1.0) * var0 : 0.0;
   }

   public void onHeldItemChange(long var1, HeldItemChangeEvent var3) {
      if (this.Y) {
         var3.I(21307, 3074332907L);
      }
   }

   private static double B(double var0) {
      return var0 < 0.0 ? 0.0 : Math.min(var0, 1.0);
   }

   private float H(Block var1) {
      float var2 = var1.getBlockHardness(f.theWorld, null);
      if (var2 < 0.0F) {
         return Float.MAX_VALUE;
      } else {
         return var2 == 0.0F ? 0.0F : var2 * (var1.getMaterial().isToolNotRequired() ? 30.0F : 100.0F);
      }
   }

   private void x$r3(long var1) {
      var1 = b ^ var1;
      int var5 = (int)((var1 ^ 120954304007671L) >>> 32);
      int var6 = (int)((var1 ^ 120954304007671L) << 32 >>> 32);
      int var9 = (int)((var1 ^ 89591778020186L) >>> 32);
      int var10 = (int)((var1 ^ 89591778020186L) << 32 >>> 48);
      int var11 = (int)((var1 ^ 89591778020186L) << 48 >>> 48);
      long var12 = var1 ^ 17922327596401L;
      this.E();
      if (f.currentScreen != null) {
         this.s(0L);
      } else {
         int var14 = this.S( true);
         int var15 = this.S( false);
         if (var14 == -1 && var15 == -1) {
            this.s(0L);
         } else {
            this.M = var14 != -1 ? var14 : var15;
            if (!this.isOffset(var12)) {
               this.s(0L);
            } else {
               this.M = this.I ? (var14 != -1 ? var14 : var15) : (var15 != -1 ? var15 : var14);
               if (!this.Y) {
                  this.c(var9, (char)var10, (char)var11);
               }

               this.v(var5, var6);
            }
         }
      }
   }

   static void $jnicClinit() throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException {
      b = 26605011805357L;
      long var20 = b ^ 120046551633852L;
      h = new HashMap(13);
      Cipher var11;
      byte[] var10003 = new byte[]{(byte)(var20 >>> 56), 0, 0, 0, 0, 0, 0, 0};

      for (int var12 = 1; var12 < 8; var12++) {
         var10003[var12] = (byte)(var20 << var12 * 8 >>> 56);
      }

      (var11 = Cipher.getInstance("DES/CBC/PKCS5Padding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
      String[] var18 = new String[3];
      int var16 = 0;
      String var15 = "P\u00802DÃQQÎªm\\\u009d\u000e³\u001dW\u0010\u0000Q(X0x_©\u0086\u008cú¡õãÂù\u0010èÆÍÏµì\u009c\u001f\u009dô]Íxº\u0091ï";
      int var17 = "P\u00802DÃQQÎªm\\\u009d\u000e³\u001dW\u0010\u0000Q(X0x_©\u0086\u008cú¡õãÂù\u0010èÆÍÏµì\u009c\u001f\u009dô]Íxº\u0091ï".length();
      char var14 = 16;
      int var13 = -1;

      while (true) {
         byte[] var19 = var11.doFinal(var15.substring(++var13, var13 + var14).getBytes("ISO-8859-1"));
         String var31 = b(var19).intern();
         int var10001 = -1;
         var18[var16++] = var31;
         if ((var13 += var14) >= var17) {
            c = var18;
            g = new String[3];
            r = new HashMap(13);
            Cipher var0;
            var10003 = new byte[]{(byte)(var20 >>> 56), 0, 0, 0, 0, 0, 0, 0};

            for (int var1 = 1; var1 < 8; var1++) {
               var10003[var1] = (byte)(var20 << var1 * 8 >>> 56);
            }

            (var0 = Cipher.getInstance("DES/CBC/NoPadding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
            long[] var6 = new long[11];
            int var3 = 0;
            String var4 = "]Íª\u000b;\u0099)\u0006X?]1¬\fÝö1¬\u0016\u0011&¿¤!\u008crâÝ\u0097Ê\u000e«µå÷Æ:pAûówh4\u0019êg¨4õ\u0088\u0002\u0081ã\u0013°LE\u0007æ(ö§ ¦\u0081\u0002Ì9±©\u001b";
            int var5 = "]Íª\u000b;\u0099)\u0006X?]1¬\fÝö1¬\u0016\u0011&¿¤!\u008crâÝ\u0097Ê\u000e«µå÷Æ:pAûówh4\u0019êg¨4õ\u0088\u0002\u0081ã\u0013°LE\u0007æ(ö§ ¦\u0081\u0002Ì9±©\u001b"
               .length();
            int var2 = 0;

            label32:
            while (true) {
               var10001 = var2;
               var2 += 8;
               byte[] var7 = var4.substring(var10001, var2).getBytes("ISO-8859-1");
               long[] var25 = var6;
               var10001 = var3++;
               long var34 = (var7[0] & 255L) << 56
                  | (var7[1] & 255L) << 48
                  | (var7[2] & 255L) << 40
                  | (var7[3] & 255L) << 32
                  | (var7[4] & 255L) << 24
                  | (var7[5] & 255L) << 16
                  | (var7[6] & 255L) << 8
                  | var7[7] & 255L;
               int var37 = -1;

               while (true) {
                  long var8 = var34;
                  byte[] var10 = var0.doFinal(
                     new byte[]{
                        (byte)(var8 >>> 56),
                        (byte)(var8 >>> 48),
                        (byte)(var8 >>> 40),
                        (byte)(var8 >>> 32),
                        (byte)(var8 >>> 24),
                        (byte)(var8 >>> 16),
                        (byte)(var8 >>> 8),
                        (byte)var8
                     }
                  );
                  long var39 = (var10[0] & 255L) << 56
                     | (var10[1] & 255L) << 48
                     | (var10[2] & 255L) << 40
                     | (var10[3] & 255L) << 32
                     | (var10[4] & 255L) << 24
                     | (var10[5] & 255L) << 16
                     | (var10[6] & 255L) << 8
                     | var10[7] & 255L;
                  switch (var37) {
                     case 0:
                        var25[var10001] = var39;
                        if (var2 >= var5) {
                           o = var6;
                           J = new EnumFacing[]{EnumFacing.EAST, EnumFacing.SOUTH, EnumFacing.WEST, EnumFacing.NORTH};
                           BlockInFaceOffset[] var26 = new BlockInFaceOffset[6];
                           var26[0] = new BlockInFaceOffset(0, 1, 0, EnumFacing.DOWN);
                           var26[1] = new BlockInFaceOffset(0, -1, 0, EnumFacing.UP);
                           var26[2] = new BlockInFaceOffset(0, 0, -1, EnumFacing.NORTH);
                           var26[3] = new BlockInFaceOffset(0, 0, 1, EnumFacing.SOUTH);
                           var26[4] = new BlockInFaceOffset(1, 0, 0, EnumFacing.EAST);
                           var26[5] = new BlockInFaceOffset(-1, 0, 0, EnumFacing.WEST);
                           K = var26;
                           return;
                        }
                        break;
                     default:
                        var25[var10001] = var39;
                        if (var2 < var5) {
                           continue label32;
                        }

                        var4 = "kþ #ÇêîÖëJ\n?+®)\u0090";
                        var5 = "kþ #ÇêîÖëJ\n?+®)\u0090".length();
                        var2 = 0;
                  }

                  int var30 = var2;
                  var2 += 8;
                  var7 = var4.substring(var30, var2).getBytes("ISO-8859-1");
                  var25 = var6;
                  var10001 = var3++;
                  var34 = (var7[0] & 255L) << 56
                     | (var7[1] & 255L) << 48
                     | (var7[2] & 255L) << 40
                     | (var7[3] & 255L) << 32
                     | (var7[4] & 255L) << 24
                     | (var7[5] & 255L) << 16
                     | (var7[6] & 255L) << 8
                     | var7[7] & 255L;
                  var37 = 0;
               }
            }
         }

         var14 = var15.charAt(var13);
      }
   }

   private static double H(double var0) {
      return var0 * var0;
   }

   private boolean C(BlockPos var1) {
      BlockPos var2 = new BlockPos(
         MathHelper.floor_double(f.thePlayer.posX),
         MathHelper.floor_double(f.thePlayer.posY),
         MathHelper.floor_double(f.thePlayer.posZ)
      );
      int var3 = var1.getX() - var2.getX();
      int var4 = var1.getY() - var2.getY();
      int var5 = var1.getZ() - var2.getZ();
      return var3 == 0 && var5 == 0 && var4 == 2 ? true : (var4 == 0 || var4 == 1) && (Math.abs(var3) == 1 && var5 == 0 || Math.abs(var5) == 1 && var3 == 0);
   }

   private void s(long var1) {
      this.T(false);
      if (this.Y) {
         if (this.y && this.O != -1 && this.O != f.thePlayer.inventory.currentItem) {
            ItemUtil.P( this.O);
         }

         this.Y = false;
         this.y = false;
         this.O = -1;
         this.M = -1;
         this.e = false;
         this.E();
      }
   }

   private void l(List var1, Vec3 var2, double var3, double var5, double var7,float var11, float var12) {
      float[] var13 = RotationUtil.W(new Vec3(var3, var5, var7), var2);
      var1.add(new BlockInRotationCandidate(Math.abs(MathHelper.wrapAngleTo180_float(var13[0] - var11)) + Math.abs(var13[1] - var12), var13[0], var13[1]));
   }

   public void onPreMouseInput(PreMouseInputEvent var1, long var2) {
       try {var2 = b ^ var2;
      long var10001 = var2 ^ 107754582317858L;
      int var4 = (int)((var2 ^ 107754582317858L) >>> 32);
      int var5 = (int)((var2 ^ 107754582317858L) << 32 >>> 48);
      int var9 = (int)((var2 ^ 116794250315602L) >>> 32);
      int var10 = (int)((var2 ^ 116794250315602L) << 32 >>> 48);
      int var11 = (int)((var2 ^ 116794250315602L) << 48 >>> 48);
      int var12 = (int)((var2 ^ 64761190847175L) >>> 48);
      long var13 = (var2 ^ 64761190847175L) << 16 >>> 16;
      long var15 = var2 ^ 47858439708191L;
      long var17 = var2 ^ 47976887585699L;
      this.x$r3(var15);
      if (this.Y() && this.Y && this.R != null && this.n != null) {
         this.T(true);
         this.T(var9, (short)var10, (short)var11);
         if (this.e) {
            this.e = false;
            if (this.v != null && this.s != null && this.T != null) {
               CombatUtil.u(this.v, this.s, this.T, swing.c(), false);
            }
         }

         if (this.Y) {
            var1.q(var4, var5);
         }
      } else {
         this.u((short)var12, var13);
         this.s(0L);
         this.e = false;
         this.E();
         RotationManager.O(var17);
      }
   } catch (Throwable zkm$t) { throw Sneaky.rethrow(zkm$t); } }

   private boolean isOffset(long var1) {
      BlockInPlacement var7 = this.N(0L);
      if (var7 == null) {
         var7 = this.E(0L);
      }

      if (var7 == null) {
         return false;
      }

      BlockPos var8 = var7.h.offset(var7.C);
      this.I = this.C(var8);
      this.R = var7.h;
      this.n = var7.C;
      this.G = var7.j;
      this.B = var7.S;
      return true;
   }

   private MovingObjectPosition I(double var1, float var3, float var4) {
      return BlockUtil.F(new float[]{var3, var4}, var1);
   }

   private boolean I(ItemStack var1, BlockPos var2, EnumFacing var3) {
      return var1 != null && var1.getItem() instanceof ItemBlock
         ? ((ItemBlock)var1.getItem()).canPlaceBlockOnSide(f.theWorld, var2, var3, f.thePlayer, var1)
         : false;
   }

   private void c(int var1, char var2, char var3) {
      if (!this.Y) {
         this.Y = true;
         this.y = false;
         this.O = f.thePlayer.inventory.currentItem;
      }
   }

   private double e(Vec3 var1, BlockPos var2) {
      double var3 = Math.max(var2.getX(), Math.min(var2.getX() + 1, var1.xCoord));
      double var5 = Math.max(var2.getY(), Math.min(var2.getY() + 1, var1.yCoord));
      double var7 = Math.max(var2.getZ(), Math.min(var2.getZ() + 1, var1.zCoord));
      double var9 = var1.xCoord - var3;
      double var11 = var1.yCoord - var5;
      double var13 = var1.zCoord - var7;
      return var9 * var9 + var11 * var11 + var13 * var13;
   }

   public void A(long var1) {
      long var5 = var1 ^ 64012210233546L;
      this.s(0L);
      this.e = false;
      this.E();
      RotationManager.O(var5);
   }

   private BlockInPlacement N(long var1) {
      Vec3 var5 = new Vec3(f.thePlayer.posX, f.thePlayer.posY, f.thePlayer.posZ);
      BlockPos var6 = new BlockPos(
         MathHelper.floor_double(var5.xCoord), MathHelper.floor_double(var5.yCoord) + 2, MathHelper.floor_double(var5.zCoord)
      );
      if (BlockUtil.a$r1(var6) && this.M >= 0 && this.M <= 8) {
         ItemStack var7 = f.thePlayer.inventory.mainInventory[this.M];
         double var8 = range.L();
         Vec3 var10 = f.thePlayer.getPositionEyes(1.0F);
         double var11 = var8 * var8;
         double var13 = (var8 + 1.0) * (var8 + 1.0);
         int var15 = MathHelper.floor_double(var10.yCoord) + 1;
         int var16 = MathHelper.floor_double(var10.yCoord + var8);
         int var17 = MathHelper.floor_double(var10.xCoord - var8);
         int var18 = MathHelper.floor_double(var10.xCoord + var8);
         int var19 = MathHelper.floor_double(var10.zCoord - var8);
         int var20 = MathHelper.floor_double(var10.zCoord + var8);
         ArrayList<BlockInScoredBlockPos> var21 = new ArrayList<>();

         for (int var22 = var15; var22 <= var16; var22++) {
            for (int var23 = var17; var23 <= var18; var23++) {
               for (int var24 = var19; var24 <= var20; var24++) {
                  double var25 = var23 + 0.5 - var10.xCoord;
                  double var27 = var22 + 0.5 - var10.yCoord;
                  double var29 = var24 + 0.5 - var10.zCoord;
                  if (!(var25 * var25 + var27 * var27 + var29 * var29 > var13)) {
                     BlockPos var31 = new BlockPos(var23, var22, var24);
                     if (!BlockUtil.a$r1(var31)) {
                        Block var32 = BlockUtil.a(var31);
                        if (!BlockUtil.p(var32) && !(var32 instanceof BlockFence) && !(var32 instanceof BlockWall)) {
                           double var33 = this.e(var10, var31);
                           if (!(var33 > var11)) {
                              var21.add(new BlockInScoredBlockPos(var33, var31));
                           }
                        }
                     }
                  }
               }
            }
         }

         var21.sort((var0, var1x) -> Double.compare(var0.k, var1x.k));

         for (BlockInScoredBlockPos var37 : (Iterable<BlockInScoredBlockPos>)(var21)) {
            BlockInPlacement var38 = this.f(var7, var37.o, var10, var8, var15);
            if (var38 != null) {
               return var38;
            }
         }

         return null;
      } else {
         return null;
      }
   }

   private boolean T() {
      double var1 = rotationTolerance.L();
      return Math.abs(MathUtil.M(RotationManager.r, this.G)) <= var1 && Math.abs(MathUtil.M(RotationManager.G, this.B)) <= var1;
   }

   private void v(int var1, int var2) {
      int var7 = f.thePlayer.inventory.currentItem;
      if (this.M != -1 && this.M != var7) {
         ItemUtil.P( this.M);
         this.y = true;
      }
   }

   private int S( boolean var3) {
      int var4 = -1;
      float var5 = var3 ? -1.0F : Float.MAX_VALUE;

      for (int var6 = 8; var6 >= 0; var6--) {
         ItemStack var7 = f.thePlayer.inventory.mainInventory[var6];
         if (ItemUtil.u(var7)) {
            Block var8 = ((ItemBlock)var7.getItem()).getBlock();
            float var9 = this.H(var8);
            if (var3 ? var9 > var5 : var9 < var5) {
               var4 = var6;
            }
         }
      }

      return var4;
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

   private void T(int var1, short var2, short var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var4 = ((long)var1 << 32 | (long)var2 << 48 >>> 32 | (long)var3 << 48 >>> 48) ^ b;
      long var8 = var4 ^ 43054533908675L;
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

      float[] var14 = this.a(RotationManager.r, RotationManager.G, this.G, this.B, angleStep.L());
      RotationManager.I(var14[0],0L);
      RotationManager.A(var8, var14[1]);
      MovingObjectPosition var15 = this.I(range.L(), RotationManager.r, RotationManager.G);
      if (var15.typeOfHit == MovingObjectType.BLOCK) {
         BlockPos var12 = var15.getBlockPos();
         EnumFacing var13 = var15.sideHit;
         if (var12.equals(this.R) && var13 == this.n && this.T()) {
            this.v = var12;
            this.s = var13;
            this.T = var15.hitVec;
            this.e = true;
         }
      }
   }

   private BlockInPlacement f(ItemStack var1, BlockPos var2, Vec3 var3, double var4, int var6) {
      float var11 = RotationManager.r;
      float var12 = RotationManager.G;
      boolean var13 = Math.abs(var3.yCoord - (var2.getY() + 1)) < Math.abs(var3.yCoord - var2.getY());
      boolean var14 = Math.abs(var3.zCoord - (var2.getZ() + 1)) < Math.abs(var3.zCoord - var2.getZ());
      boolean var15 = Math.abs(var3.xCoord - (var2.getX() + 1)) < Math.abs(var3.xCoord - var2.getX());
      double var16 = var2.getX();
      double var18 = var2.getY();
      double var20 = var2.getZ();
      double var22 = 0.020000000000000004;
      ArrayList<BlockInRotationCandidate> var24 = new ArrayList<BlockInRotationCandidate>((d + 1) * (d + 1) * 3 + 1);
      var24.add(new BlockInRotationCandidate(0.0, var11, var12));

      for (int var25 = 0; var25 <= d; var25++) {
         double var26 = B(var25 * 0.2 + i(var22));

         for (int var28 = 0; var28 <= d; var28++) {
            double var29 = B(var28 * 0.2 + i(var22));
            this.l(var24, var3, var16 + var29, var13 ? var18 + 1.0 - 0.05 : var18 + 0.05, var20 + var26,var11, var12);
            this.l(var24, var3, var16 + var29, var18 + var26, var14 ? var20 + 1.0 - 0.05 : var20 + 0.05,var11, var12);
            this.l(var24, var3, var15 ? var16 + 1.0 - 0.05 : var16 + 0.05, var18 + var26, var20 + var29,var11, var12);
         }
      }

      var24.sort((var0, var1x) -> Double.compare(var0.Y, var1x.Y));
      int var32 = var2.getY();

      for (BlockInRotationCandidate var27 : (Iterable<BlockInRotationCandidate>)(var24)) {
         MovingObjectPosition var34 = this.I(var4, var27.j, var27.N);
         if (var34.typeOfHit == MovingObjectType.BLOCK) {
            BlockPos var35 = var34.getBlockPos();
            EnumFacing var30 = var34.sideHit;
            if (var35.equals(var2) && var35.getY() >= var6 && (var30 != EnumFacing.DOWN || var32 != var6) && this.I(var1, var35, var30)) {
               return new BlockInPlacement(var35, var30, var27.j, var27.N);
            }
         }
      }

      return null;
   }

   public BlockIn(long var1) {
      super((((b ^ (var1)) ^ 121332785966042L) >>> 16), (char)((int)(((((b ^ (var1)) ^ 121332785966042L) << 48) >>> 48))));
      this.declare("BlockIn", Category.World, "Automatically surrounds blocks when you are trying to break bed");
      var1 = b ^ var1;
      this.O = -1;
      this.M = -1;
   }

   private BlockInPlacement j(double var1, float var3, float var4, BlockPos var5, EnumFacing var6, BlockPos var7) {
      MovingObjectPosition var8 = this.I(var1, var3, var4);
      if (var8.typeOfHit != MovingObjectType.BLOCK) {
         return null;
      } else {
         BlockPos var9 = var8.getBlockPos();
         EnumFacing var10 = var8.sideHit;
         if (var9.equals(var5) && var10 == var6) {
            BlockPos var11 = var9.offset(var10);
            return !var11.equals(var7) ? null : new BlockInPlacement(var9, var10, var3, var4);
         } else {
            return null;
         }
      }
   }

   private void E() {
      this.R = null;
      this.n = null;
      this.v = null;
      this.s = null;
      this.T = null;
   }

   public final void x(long var1, EventBus var3) {
      BlockInBinder.s(var3, this);
   }

   private Vec3 r(double var1) {
      if (f.theWorld != null && f.thePlayer != null) {
         Vec3 var3 = null;
         double var4 = var1;

         for (Object var7 : f.theWorld.playerEntities) {
            if (var7 instanceof EntityPlayer) {
               EntityPlayer var8 = (EntityPlayer)var7;
               if (var8 != f.thePlayer && f.getNetHandler() != null && f.getNetHandler().getPlayerInfo(var8.getUniqueID()) != null) {
                  double var9 = var8.posX - f.thePlayer.posX;
                  double var11 = var8.posY - f.thePlayer.posY;
                  double var13 = var8.posZ - f.thePlayer.posZ;
                  double var15 = var9 * var9 + var11 * var11 + var13 * var13;
                  if (var15 < var4) {
                     var4 = var15;
                     var3 = new Vec3(var8.posX, var8.posY, var8.posZ);
                  }
               }
            }
         }

         return var3;
      } else {
         return null;
      }
   }

   private float[] a(float var1, float var2, float var3, float var4, double var5) {
      float var7 = (float)var5;
      var7 = MathHelper.clamp_float(var7, 1.0F, var7 * 2.0F);
      float var8 = MathHelper.wrapAngleTo180_float(var3 - var1);
      float var9 = var4 - var2;
      float var10 = var1 + MathHelper.clamp_float(var8, -var7, var7);
      float var11 = MathHelper.clamp_float(var2 + MathHelper.clamp_float(var9, -var7, var7), -90.0F, 90.0F);
      return new float[]{var10, var11};
   }

   static {
      try {
         $jnicClinit();
         d = (int)Math.round(5.0);
      } catch (UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException var0) {
         throw new RuntimeException(var0);
      }
   }
   static {
      swing = new BooleanSetting("Swing", true);
   }
   static {
      range = new NumberSetting("Range", 4.5F, 0.0F, 8.0F, 0.1F);
      angleStep = new NumberSetting("Angle-step", 60.0F, 1.0F, 180.0F, 1.0F);
      rotationTolerance = new NumberSetting("Rotation-tolerance", 25.0F, 20.0F, 100.0F, 1.0F);
   }
   static {
      moveFix = new ModeSetting("Move-fix", "SILENT", "STRICT", "NONE");
   }
}
