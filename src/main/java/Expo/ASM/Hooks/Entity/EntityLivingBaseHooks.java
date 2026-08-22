package Expo.ASM.Hooks.Entity;

import Expo.ASM.Hooks.CallbackInfo;
import Expo.ASM.Hooks.CallbackInfoReturnable;
import Expo.ExpoClient;
import Expo.event.events.GetArmSwingAnimationEndEvent;
import Expo.event.events.JumpEvent;
import Expo.event.events.LivingDeathEvent;
import Expo.event.events.MoveEntityWithHeadingEvent;
import Expo.event.events.MoveFlyingEvent;
import Expo.internal.accessor.EntityLivingBaseAccessor;
import Expo.util.ClientUtil;
import Expo.util.MinecraftRef;
import Expo.util.RotationManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;

public class EntityLivingBaseHooks {
   private static final Minecraft Z;
   private static final long a = 29521831753297L;

   public static void onMoveFlying(EntityLivingBase var0, float var1, float var2, float var3, EntityLivingBase var4) {
      if (var4 instanceof EntityPlayerSP) {
         MoveFlyingEvent var11 = new MoveFlyingEvent(var1, var2, var3);
         ExpoClient.w.e(var11, 18670087776179L);
         var1 = var11.a$r3();
         var2 = var11.b();
         var3 = var11.p();
         var0.moveFlying(var1, var2, var3);
      } else {
         var0.moveFlying(var1, var2, var3);
      }
   }

   public static void onJump(EntityLivingBase var0, CallbackInfo var1) {
      JumpEvent var8 = new JumpEvent(EntityLivingBaseAccessor.e(var0), RotationManager.r);
      ExpoClient.w.e(var8, 18670087776179L);
      if (!var8.a()) {
         var0.motionY = var8.o();
         if (var0.isPotionActive(Potion.jump)) {
            var0.motionY = var0.motionY + (var0.getActivePotionEffect(Potion.jump).getAmplifier() + 1) * 0.1F;
         }

         if (var0.isSprinting()) {
            float var9 = var8.j() * (float) (Math.PI / 180.0);
            var0.motionX = var0.motionX - MathHelper.sin(var9) * 0.2F;
            var0.motionZ = var0.motionZ + MathHelper.cos(var9) * 0.2F;
         }

         var0.isAirBorne = true;
         var1.cancel();
      }
   }

   public static void onFunc_110146_f(EntityLivingBase var0, float var1, float var2, CallbackInfoReturnable<Float> var3) {
      float var4 = var0.rotationYaw;
      if (var0 instanceof EntityPlayerSP) {
         if (Z.thePlayer.swingProgress > 0.0F) {
            var1 = RotationManager.I;
         }

         var4 = RotationManager.I;
         Z.thePlayer.rotationYawHead = RotationManager.I;
      }

      float var5 = MathHelper.wrapAngleTo180_float(var1 - var0.renderYawOffset);
      var0.renderYawOffset += var5 * 0.3F;
      float var6 = MathHelper.wrapAngleTo180_float(var4 - var0.renderYawOffset);
      boolean var7 = var6 < 90.0F && var6 > -90.0F;
      if (var6 < -75.0F) {
         var6 = -75.0F;
      }

      if (var6 > 75.0F) {
         var6 = 75.0F;
      }

      var0.renderYawOffset = var4 - var6;
      if (var6 * var6 > 2500.0F) {
         var0.renderYawOffset += var6 * 0.2F;
      }

      if (!var7) {
         var2 *= -1.0F;
      }

      var3.setReturnValue(var2);
      var3.cancel();
   }

   public static void onGetArmSwingAnimationEnd(EntityLivingBase var0, CallbackInfoReturnable<Integer> var1) {
      if (var0 instanceof EntityPlayerSP) {
         GetArmSwingAnimationEndEvent var11 = new GetArmSwingAnimationEndEvent(1236, (Integer)var1.getReturnValue());
         ExpoClient.w.e(var11, 18670087776179L);
         var1.setReturnValue((int)(var11.N() * ClientUtil.b(75703014522979L).timerSpeed));
      }
   }

   static {
      int var2 = 0;
      Z = MinecraftRef.c((byte)var2,0L);
   }

   public static void onLivingDeath(EntityLivingBase var0, DamageSource var1) {
      LivingDeathEvent var9 = new LivingDeathEvent(23778, var1, (char)20225, (short)22806, var0);
      ExpoClient.w.e(var9, 18670087776179L);
   }

   public static void onMoveEntityWithHeading(EntityLivingBase var0, CallbackInfo var1) {
      MoveEntityWithHeadingEvent var9 = new MoveEntityWithHeadingEvent((short)0, var0, (short)11036, -1066395815);
      ExpoClient.w.e(var9, 18670087776179L);
      if (var9.a()) {
         var1.cancel();
      }
   }
}
