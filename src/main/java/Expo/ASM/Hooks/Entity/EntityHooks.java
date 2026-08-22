package Expo.ASM.Hooks.Entity;

import Expo.ASM.Hooks.CallbackInfo;
import Expo.ASM.Hooks.CallbackInfoReturnable;
import Expo.ExpoClient;
import Expo.event.events.MoveEntityEvent;
import Expo.event.events.SafeWalkEvent;
import Expo.event.events.SetAnglesEvent;
import Expo.module.ModuleManager;
import Expo.module.impl.combat.HitBox;
import Expo.util.MinecraftRef;
import Expo.util.RotationManager;
import Expo.util.RotationUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;


public class EntityHooks {
   private static final Minecraft V;
   private static final long a = 26549936100656L;


   public static void onMoveFlying(Entity var0, float var1, float var2, float var3, CallbackInfo var4) {
      float var5 = var0.rotationYaw;
      if (var0 instanceof EntityPlayerSP) {
         var5 = RotationManager.V;
      }

      float var6 = var1 * var1 + var2 * var2;
      if (var6 >= 1.0E-4F) {
         var6 = MathHelper.sqrt_float(var6);
         if (var6 < 1.0F) {
            var6 = 1.0F;
         }

         var6 = var3 / var6;
         var1 *= var6;
         var2 *= var6;
         float var7 = MathHelper.sin(var5 * (float) Math.PI / 180.0F);
         float var8 = MathHelper.cos(var5 * (float) Math.PI / 180.0F);
         var0.motionX += var1 * var8 - var2 * var7;
         var0.motionZ += var2 * var8 + var1 * var7;
      }

      var4.cancel();
   }

   public static void moveEntity(Entity var0, CallbackInfo var1) {




      MoveEntityEvent var9 = new MoveEntityEvent((short)0, 507233767, (short)63688, var0);
      ExpoClient.w.e(var9, 18670087776179L);
      if (var9.a()) {
         var1.cancel();
      }
   }


   public static Vec3 onGetLook(float var0) {
      return RotationUtil.T(var0);
   }


   public static void setAngles(Entity var0, float var1, float var2, CallbackInfo var3) {

      if (var0 instanceof EntityPlayerSP) {
         SetAnglesEvent var10 = new SetAnglesEvent(var1, var2);
         ExpoClient.w.e(var10, 18670087776179L);
         if (var10.l()) {
            float var11 = var0.rotationPitch;
            float var12 = var0.rotationYaw;
            var0.rotationYaw = var10.x();
            var0.rotationPitch = var10.s();
            var0.prevRotationPitch = var0.prevRotationPitch + (var0.rotationPitch - var11);
            var0.prevRotationYaw = var0.prevRotationYaw + (var0.rotationYaw - var12);
            var3.cancel();
            return;
         }

         if (var10.a()) {
            var3.cancel();
            return;
         }

         if (RotationManager.G()) {
            var3.cancel();
         }
      }
   }

   public static boolean onSafeWalk(Entity var0, boolean var1) {


      if (!(var0 instanceof EntityPlayerSP)) {
         return var1;
      }

      SafeWalkEvent var8 = new SafeWalkEvent(93108306243249L);
      var8.z(var1);
      ExpoClient.w.e(var8, 18670087776179L);
      return var8.O();
   }


   public static void getCollisionBorderSize(Entity var0, CallbackInfoReturnable<Float> var1) {


      if (ModuleManager.r.o()) {
         if (var0 != null) {
            if (var0.worldObj != null) {
               if (var0 != V.thePlayer && var0 instanceof EntityLivingBase && HitBox.k((byte)0, (EntityLivingBase)var0, 95546070903943L)) {
                  var1.setReturnValue((Float)var1.getReturnValue() + HitBox.expand.L());
                  var1.cancel();
               }
            }
         }
      }
   }

   static {
      int var2 = 0;
      V = MinecraftRef.c((byte)var2,0L);
   }


}
