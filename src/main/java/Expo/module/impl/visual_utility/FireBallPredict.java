package Expo.module.impl.visual_utility;

import Expo.module.Category;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.FireBallPredictBinder;
import Expo.event.events.PostTickEvent;
import Expo.event.events.Render3DEvent;
import Expo.module.Module;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.NumberSetting;
import Expo.setting.settings.PercentageSetting;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import net.minecraft.block.Block;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class FireBallPredict extends Module implements EventSubscriber {
   private static String[] k;
   private static long[] c;
   public static NumberSetting renderRadius;
   private static Object[] h;
   public static PercentageSetting opacity;
   private int b;
   private static final double r = 48.0;
   private static Map g;
   private static long a;
   private static final double B = 24.0;
   private BlockPos o;
   public static NumberSetting predictRange;
   public static BooleanSetting heldFireCharges;
   private static final double n = 8.0;
   public static BooleanSetting realFireballs;

   public FireBallPredict(long var1) {
      super(((a ^ (var1)) ^ 75811301743755L));
      this.declare("FireBallPredict", Category.Visual_utility, "Predict and render fireball impact positions");
      var1 = a ^ var1;
   }

   private int I(long var1, double var3) {
      if (var3 <= 8.0) {
         return 16711680;
      } else if (var3 >= 48.0) {
         return 65280;
      } else if (var3 <= 24.0) {
         float var10 = (float)((var3 - 8.0) / 16.0);
         return this.J(255, Math.round(255.0F * var10), 0);
      } else {
         float var8 = (float)((var3 - 24.0) / 24.0);
         return this.J(Math.round(255.0F * (1.0F - var8)), 255, 0);
      }
   }

   private FireBallPredictImpact I(long var1) {
      WorldClient var3 = f.theWorld;
      List var4 = var3.playerEntities;
      Vec3 var5 = new Vec3(f.thePlayer.posX, f.thePlayer.posY, f.thePlayer.posZ);
      FireBallPredictImpact var6 = null;
      double var7 = Double.MAX_VALUE;

      for (int var9 = 0; var9 < var4.size(); var9++) {
         EntityPlayer var10 = (EntityPlayer)var4.get(var9);
         ItemStack var11 = var10.getHeldItem();
         if (var11 != null && var11.getItem() == Items.fire_charge) {
            Vec3 var12 = var10.getPositionEyes(1.0F);
            Vec3 var13 = var10.getLook(1.0F);
            Vec3 var14 = var12.addVector(var13.xCoord * predictRange.L(), var13.yCoord * predictRange.L(), var13.zCoord * predictRange.L());
            MovingObjectPosition var15 = var3.rayTraceBlocks(var12, var14, false, true, false);
            if (var15 != null && var15.typeOfHit == MovingObjectType.BLOCK) {
               BlockPos var16 = var15.getBlockPos();
               Vec3 var17 = new Vec3(var16.getX() + 0.5, var16.getY() + 0.5, var16.getZ() + 0.5);
               double var18 = var5.squareDistanceTo(var17);
               if (!(var18 >= var7)) {
                  var7 = var18;
                  var6 = new FireBallPredictImpact(var16, 16776960, null);
               }
            }
         }
      }

      return var6;
   }

   public final void x(long var1, EventBus var3) {
      FireBallPredictBinder.P(var3, this);
   }

   private void i(World var1, int var2, long var3, int var5, int var6, int var7, int var8, boolean var9) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      var3 = a ^ var3;
      long var10 = var3 ^ 54351198464086L;
      long var12 = var3 ^ 1376699286925L;

      for (int var14 = -var2; var14 <= var2; var14++) {
         for (int var15 = -var2; var15 <= var2; var15++) {
            for (int var16 = -var2; var16 <= var2; var16++) {
               BlockPos var17 = this.o.add(var14, var15, var16);
               if (this.e(var1, var17)) {
                  if (var9) {
                     Expo.util.render.RenderUtil.C(var17, 1.0, var5, var12, var6, var7, var8);
                  } else {
                     Expo.util.render.RenderUtil.n(var17, 1.0, var5, var6, var7, var10, var8, 1.5F);
                  }
               }
            }
         }
      }
   }

   private int j(int var1) {
      int var4 = var1 >> 16 & 255;
      int var5 = var1 >> 8 & 255;
      int var6 = var1 & 255;
      return var4 > 200 && var5 < 96 && var6 < 96
         ? 16756736
         : var1;
   }

   private FireBallPredictImpact G(long var1) {
      WorldClient var5 = f.theWorld;
      List var6 = var5.loadedEntityList;
      Vec3 var7 = new Vec3(f.thePlayer.posX, f.thePlayer.posY, f.thePlayer.posZ);
      FireBallPredictImpact var8 = null;
      double var9 = Double.MAX_VALUE;

      for (int var11 = 0; var11 < var6.size(); var11++) {
         Entity var12 = (Entity)var6.get(var11);
         if (var12 instanceof EntityFireball && !(var12 instanceof EntityWitherSkull)) {
            EntityFireball var13 = (EntityFireball)var12;
            double var14 = var13.motionX * var13.motionX + var13.motionY * var13.motionY + var13.motionZ * var13.motionZ;
            if (!(var14 < 1.0E-4)) {
               Vec3 var16 = new Vec3(var13.posX, var13.posY, var13.posZ);
               Vec3 var17 = new Vec3(var13.motionX, var13.motionY, var13.motionZ).normalize();
               Vec3 var18 = var16.addVector(var17.xCoord * predictRange.L(), var17.yCoord * predictRange.L(), var17.zCoord * predictRange.L());
               MovingObjectPosition var19 = var5.rayTraceBlocks(var16, var18, false, true, false);
               if (var19 != null && var19.typeOfHit == MovingObjectType.BLOCK) {
                  BlockPos var20 = var19.getBlockPos();
                  Vec3 var21 = new Vec3(var20.getX() + 0.5, var20.getY() + 0.5, var20.getZ() + 0.5);
                  double var22 = var7.squareDistanceTo(var21);
                  if (!(var22 >= var9)) {
                     var9 = var22;
                     var8 = new FireBallPredictImpact(var20, this.I(118876068591149L, var16.distanceTo(var19.hitVec)), null);
                  }
               }
            }
         }
      }

      return var8;
   }

   private int X() {
      return Math.max(1, Math.min(2, (int)renderRadius.L()));
   }

   private boolean e(World var1, BlockPos var2) {
      if (var1.isAirBlock(var2)) {
         return false;
      }

      Block var3 = var1.getBlockState(var2).getBlock();
      return var3.isFullCube();
   }

   public void onPostTick(long var1, PostTickEvent var3) {
      FireBallPredictImpact var11 = null;
      if (realFireballs.c()) {
         var11 = this.G(75118909547976L);
      }

      if (var11 == null && heldFireCharges.c()) {
         var11 = this.I(0L);
      }

      if (var11 == null) {
         this.C(18634, (char)16116, (char)19596);
      } else {
         this.o = FireBallPredictImpact.m(var11);
         this.b = FireBallPredictImpact.M(var11);
      }
   }

   public void A(long var1) {
      int var3 = (int)((var1 ^ 31933929361417L) >>> 32);
      int var4 = (int)((var1 ^ 31933929361417L) << 32 >>> 48);
      int var5 = (int)((var1 ^ 31933929361417L) << 48 >>> 48);
      this.C(var3, (char)var4, (char)var5);
   }

   public void onRender3D(long var1, Render3DEvent var3) throws Throwable {
      if (f.theWorld != null && f.thePlayer != null && this.o != null) {
         this.c((char)0, 204245502, (short)55223);
      }
   }

   static {
      a = 56662069471346L;
      h = new Object[7];
      k = new String[7];
      g = new HashMap(13);
      c = new long[]{-5489346428128618421L, -4314703374732682513L, -5745213282544904297L, -4334071078402109778L, 7788256901447205536L, 4233381784929318969L, -2978953989575751051L, -8183968391784814800L, 7468032717364496881L, -5448091144287510570L, -7426750949437619414L, -6623744853995561005L, -4660984849290241194L, -5690810572757300751L, -8861979802741692739L, -2723728853984722903L, -2324990256193890920L};
   }

   private int Y(int var1, boolean var2) {
      double var3 = opacity.k();
      if (var2 && var3 > 0.0) {
         var3 = Math.min(100.0, var3 * 1.4);
      }

      return Math.max(0, Math.min(var1, (int)(var1 * var3 / 100.0)));
   }

   private int J(int var1, int var2, int var3) {
      return var1 << 16 | var2 << 8 | var3;
   }

   private void C(int var1, char var2, char var3) {
      this.o = null;
      this.b = 0;
   }

   private void c(char var1, int var2, short var3) throws Throwable {
      long var4 = ((long)var1 << 48 | (long)var2 << 32 >>> 16 | (long)var3 << 48 >>> 48) ^ a;
      long var8 = var4 ^ 96407753710298L;
      long var12 = var4 ^ 93770285357822L;
      WorldClient var14 = f.theWorld;
      int var15 = this.X();
      int var16 = this.j(this.b);
      boolean var17 = var16 == 16756736;
      int var18 = var16 >> 16 & 255;
      int var19 = var16 >> 8 & 255;
      int var20 = var16 & 255;
      int var21 = this.Y(255, var17);
      int var22 = this.Y(var17 ? 70 : 42, var17);
      if (var21 > 0 || var22 > 0) {
         Expo.util.render.RenderUtil.L();

         try {
            if (var22 > 0) {
               this.i(var14, var15, var12, var18, var19, var20, var22, true);
            }

            if (var21 > 0) {
               this.i(var14, var15, var12, var18, var19, var20, var21, false);
               Expo.util.render.RenderUtil.n(
                  this.o,
                  1.0,
                  255,
                  255,
                  255,
                  var8,
                  var21,
                  2.2F
               );
            }
         } finally {
            Expo.util.render.RenderUtil.X();
            Expo.util.render.RenderUtil.w();
         }
      }
   }
   static {
      realFireballs = new BooleanSetting("Real-fireballs", true);
      renderRadius = new NumberSetting("Render-radius", 2.0F, 1.0F, 2.0F, 1.0F);
      heldFireCharges = new BooleanSetting("Held-fire-charges", true);
      predictRange = new NumberSetting("Predict-range", 100.0F, 16.0F, 200.0F, 1.0F);
      opacity = new PercentageSetting("Opacity", 50);
   }
}
