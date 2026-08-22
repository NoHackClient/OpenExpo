package Expo.internal;

import Expo.util.MinecraftRef;
import Expo.util.MiningConstants;
import Expo.util.RotationManager;
import Expo.util.render.BoxRenderer;
import java.awt.Color;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.MovingObjectPosition;


public class MiningState {
   private static Color r;
   private final Set<BlockPos> p;
   private static long T;
   private static long[] b;
   private boolean m;
   private static long a;
   private BlockPos[] f;
   private static BlockPos B;
   private static Color Y;
   private final Set<BlockPos> P;
   private static BlockPos j;
   private BlockPos E;
   private static Map d;
   private static long e;
   private long S;
   private static Minecraft M;
   private static Color i;

   public MiningState(long var1) {
      var1 = a ^ var1;
      this.p = new HashSet<>();
      this.P = new HashSet<>();
      this.S = 0L;
      this.f = new BlockPos[0];
      this.m = false;
   }

   public boolean J() {
      return this.m;
   }

   public void v(long var1) {


      int var5 = (int)MiningConstants.H;
      int var6 = (int)M.thePlayer.posX;
      int var7 = (int)M.thePlayer.posY;
      int var8 = (int)M.thePlayer.posZ;

      for (int var9 = var6 - var5; var9 <= var6 + var5; var9++) {
         for (int var10 = var7 - 2; var10 <= var7 + 2; var10++) {
            for (int var11 = var8 - var5; var11 <= var8 + var5; var11++) {
               BlockPos var12 = new BlockPos(var9, var10, var11);
               Block var13 = M.theWorld.getBlockState(var12).getBlock();
               if ((var13 == Blocks.chest || var13 == Blocks.trapped_chest) && !this.p.contains(var12)) {
                  this.p.add(var12);
                  boolean var14 = false;
                  Block var15 = BrokenBlockTracker.m.x(var12);

                  for (BlockPos var17 : BrokenBlockTracker.m.q()) {
                     if (var17 != null && var17.equals(var12)) {
                        var14 = true;
                        break;
                     }
                  }

                  if (var14 && var15 != null && var15 != Blocks.air && var15 != Blocks.chest && var15 != Blocks.trapped_chest) {
                     this.P.add(var12);
                     BoxRenderer.p(var12, 99005023413082L, i);
                  } else {
                     BoxRenderer.p(var12, 99005023413082L, r);
                  }
               }
            }
         }
      }
   }

   public void C() {
      double var1 = M.thePlayer.getEntityBoundingBox().minY - 0.01;
      double var3 = M.thePlayer.posX;
      double var5 = M.thePlayer.posZ;
      int var7 = (int)Math.floor(var3);
      int var8 = (int)Math.floor(var5);
      int var9 = (int)Math.floor(var3 + 1.0E-4);
      int var10 = (int)Math.floor(var5 + 1.0E-4);
      int var11 = (int)Math.floor(var1);
      BlockPos var12 = new BlockPos(var7, var11, var8);
      BlockPos var13 = new BlockPos(var9, var11, var10);
      if (var7 == var9 && var8 == var10) {
         this.f = new BlockPos[]{var12};
      } else {
         float var14 = this.r(M);
         double var15 = Math.toRadians(var14);
         double var17 = -Math.sin(var15);
         double var19 = Math.cos(var15);
         double var21 = var3 + var17 * 0.2;
         double var23 = var5 + var19 * 0.2;
         BlockPos var25 = new BlockPos((int)Math.floor(var21), var11, (int)Math.floor(var23));
         BlockPos var26 = var25.equals(var12) ? var13 : var12;
         this.f = new BlockPos[]{var25, var26};
      }

      this.m = this.G();
   }

   public boolean G() {
      for (BlockPos var4 : this.f) {
         Block var5 = M.theWorld.getBlockState(var4).getBlock();
         if (var5 == Blocks.chest || var5 == Blocks.trapped_chest) {
            return true;
         }
      }

      return false;
   }

   public void T(long var1) {


      MovingObjectPosition var5 = M.objectMouseOver;
      if (var5 != null && var5.typeOfHit == MovingObjectType.BLOCK) {
         j = var5.getBlockPos();
         if (this.N()) {
            BoxRenderer.p(
               j,
               99005023413082L,
               new Color(
                  255,
                  255,
                  255,
                  40
               )
            );
         }
      } else {
         j = null;
      }
   }

   public void A() {
      this.S = System.currentTimeMillis();
   }


   private boolean N() {
      if (!MiningConstants.Z) {
         return false;
      } else {
         return MiningConstants.G ? MiningEngine.uq.h() : true;
      }
   }

   public BlockPos U() {
      return j;
   }

   public void s(long var1) {
      long var3 = 99005023413082L;
      if (this.N()) {
         Iterator var5 = this.p.iterator();

         while (var5.hasNext()) {
            BlockPos var6 = (BlockPos)var5.next();
            Block var7 = M.theWorld.getBlockState(var6).getBlock();
            if (var7 != Blocks.chest && var7 != Blocks.trapped_chest) {
               var5.remove();
               this.P.remove(var6);
            } else if (this.P.contains(var6)) {
               BoxRenderer.p(var6, var3, i);
            } else {
               BoxRenderer.p(var6, var3, r);
            }
         }
      }
   }

   public boolean I() {
      return this.d() != null;
   }

   public static boolean K(EntityPlayer var0, BlockPos var1) {
      AxisAlignedBB var2 = var0.getEntityBoundingBox();
      double var3 = 0.1;
      AxisAlignedBB var5 = new AxisAlignedBB(
         var1.getX() - var3,
         var1.getY() - var3,
         var1.getZ() - var3,
         var1.getX() + 1 + var3,
         var1.getY() + 1 + var3,
         var1.getZ() + 1 + var3
      );
      return var2.intersectsWith(var5);
   }

   private float r(Minecraft var1) {
      if (var1 == null || var1.thePlayer == null) {
         return 0.0F;
      } else {
         return MiningEngine.uq.h() ? RotationManager.r : var1.thePlayer.rotationYaw;
      }
   }

   public Set<BlockPos> g() {
      return this.P;
   }

   public BlockPos[] M() {
      return this.f;
   }

   public void w(long var1) {
      long var3 = 99005023413082L;
      if (this.N()) {
         if (this.f.length != 0) {
            Color var5 = new Color(
               255,
               255,
               255,
               40
            );

            for (BlockPos var9 : this.f) {
               if (M.theWorld.getBlockState(var9).getBlock() != Blocks.air) {
                  BoxRenderer.p(var9, var3, var5);
               }
            }
         }
      }
   }

   public void c(long var1) {
      long var3 = 99005023413082L;
      if (this.N()) {
         BlockPos var5 = BrokenBlockTracker.m.y();
         if (var5 != null) {
            if (System.currentTimeMillis() - this.S <= e) {
               Block var6 = M.theWorld.getBlockState(var5).getBlock();
               if (var6 == Blocks.chest || var6 == Blocks.trapped_chest) {
                  BoxRenderer.p(var5, var3, i);
               }
            }
         }
      }
   }

   public MiningProgress d() {
      if (this.E == null) {
         return null;
      } else if (K(M.thePlayer, this.E)) {
         Block var1 = M.theWorld.getBlockState(this.E).getBlock();
         long var2 = System.currentTimeMillis();
         return this.E.equals(B) && T > 0L ? new MiningProgress(this.E, var1, T, var2 - T) : new MiningProgress(this.E, var1, var2, 0L);
      } else {
         return null;
      }
   }

   static {
      a = 1478184032011L;
      Y = new Color(128, 0, 128, 180);
      r = new Color(
      255,
      200,
      100,
      80
      );
      i = new Color(80, 40, 0, 180);
      T = 0L;
      M = MinecraftRef.c((byte)0, 0L);
      d = new HashMap(13);
      b = new long[]{-9168688596242034574L, 5080095703702614037L, 6758059678884188576L, -8075456693886047355L, -3848150705027385449L, 5000693850285147339L, 2022900836229366566L, 8832399447815106992L, 2774359402930372078L, -1941175905964506652L, -769374932895943680L, -5736450801476679465L, -8253828617825589297L};
      e = 3000L;
   }

   public Set<BlockPos> d$r1() {
      return this.p;
   }

   public void V(long var1) {
      long var3 = 99005023413082L;
      if (this.N()) {
         if (this.E == null) {
            B = null;
            T = 0L;
         } else if (M.theWorld.getBlockState(this.E).getBlock() == Blocks.air) {
            this.E = null;
            B = null;
            T = 0L;
         } else {
            if (K(M.thePlayer, this.E)) {
               long var5 = System.currentTimeMillis();
               if (!this.E.equals(B)) {
                  B = this.E;
                  T = var5;
               }

               BoxRenderer.p(this.E, var3, Y);
            } else {
               B = null;
               T = 0L;
            }
         }
      }
   }

   public void b() {
      float var1 = this.r(M);
      double var2 = Math.toRadians(var1);
      int var4 = (int)Math.round(-Math.sin(var2));
      int var5 = (int)Math.round(Math.cos(var2));
      int var6 = (int)Math.floor(M.thePlayer.posX);
      int var7 = (int)Math.floor(M.thePlayer.posY);
      int var8 = (int)Math.floor(M.thePlayer.posZ);
      BlockPos var9 = new BlockPos(var6 + var4, var7, var8 + var5);
      BlockPos var10 = new BlockPos(var6 + var4, var7 + 1, var8 + var5);

      for (BlockPos var14 : new BlockPos[]{var9, var10}) {
         if (M.theWorld.getBlockState(var14).getBlock() != Blocks.air) {
            this.E = var14;
            return;
         }
      }

      this.E = null;
   }

}
