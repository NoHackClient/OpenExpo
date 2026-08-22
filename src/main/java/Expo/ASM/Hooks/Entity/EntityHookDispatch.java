package Expo.ASM.Hooks.Entity;

import Expo.ASM.Hooks.CallbackInfo;
import Expo.ASM.Hooks.CallbackInfoReturnable;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.Vec3;











public class EntityHookDispatch {
   private static final long a = 120659725893006L;

   public static void EntityLivingBase$onMoveFlying(EntityLivingBase var0, float var1, float var2, float var3, EntityLivingBase var4) {
      EntityLivingBaseHooks.onMoveFlying(var0, var1, var2, var3, var4);
   }

   public static void Entity$getCollisionBorderSize(Entity var0, CallbackInfoReturnable<Float> var1) {
      EntityHooks.getCollisionBorderSize(var0, var1);
   }

   public static void Entity$onMoveFlying(Entity var0, float var1, float var2, float var3, CallbackInfo var4) {
      EntityHooks.onMoveFlying(var0, var1, var2, var3, var4);
   }

   public static void EntityPlayer$onPostItemUseFinish() {
      EntityPlayerHooks.onPostItemUseFinish();
   }

   public static void Entity$moveEntity(Entity var0, CallbackInfo var1) {
      EntityHooks.moveEntity(var0, var1);
   }

   static {
      a();
   }

   public static void EntityLivingBase$onLivingDeath(EntityLivingBase var0, DamageSource var1) {
      EntityLivingBaseHooks.onLivingDeath(var0, var1);
   }

   public static void EntityItem$onPickUpItem(EntityItem var0, EntityPlayer var1) {
      EntityItemHooks.onPickUpItem(var0, var1);
   }

   public static void EntityPlayerSP$onSendChatMessage(String var0, CallbackInfo var1) {
      EntityPlayerSPHooks.onSendChatMessage(var0, var1);
   }

   private static void a() {
   }

   public static boolean Entity$onSafeWalk(Entity var0, boolean var1) {
      return EntityHooks.onSafeWalk(var0, var1);
   }

   public static void EntityLivingBase$onGetArmSwingAnimationEnd(EntityLivingBase var0, CallbackInfoReturnable<Integer> var1) {
      EntityLivingBaseHooks.onGetArmSwingAnimationEnd(var0, var1);
   }

   public static void EntityPlayerSP$onCloseScreen() {
      EntityPlayerSPHooks.onCloseScreen();
   }

   public static void EntityLivingBase$onFunc_110146_f(EntityLivingBase var0, float var1, float var2, CallbackInfoReturnable<Float> var3) {
      EntityLivingBaseHooks.onFunc_110146_f(var0, var1, var2, var3);
   }

   public static void EntityLivingBase$onMoveEntityWithHeading(EntityLivingBase var0, CallbackInfo var1) {
      EntityLivingBaseHooks.onMoveEntityWithHeading(var0, var1);
   }

   public static void EntityPlayerSP$onPreSuperLivingUpdate() {
      EntityPlayerSPHooks.onPreSuperLivingUpdate();
   }

   public static void EntityPlayerSP$redirectIsUsingItem(EntityPlayerSP var0) {
      EntityPlayerSPHooks.redirectIsUsingItem(var0);
   }

   public static void Entity$setAngles(Entity var0, float var1, float var2, CallbackInfo var3) {
      EntityHooks.setAngles(var0, var1, var2, var3);
   }

   public static void EntityPlayer$onGetDisplayName(EntityPlayer var0, CallbackInfoReturnable<IChatComponent> var1) {
      EntityPlayerHooks.onGetDisplayName(var0, var1);
   }

   public static Vec3 Entity$onGetLook(float var0) {
      return EntityHooks.onGetLook(var0);
   }

   public static void EntityPlayer$onAttackTargetEntity(EntityPlayer var0, Entity var1, CallbackInfo var2) {
      EntityPlayerHooks.onAttackTargetEntity(var0, var1, var2);
   }

   public static void EntityPlayerSP$onPostUpdate() {
      EntityPlayerSPHooks.onPostUpdate();
   }

   public static void EntityPlayerSP$onPreLivingUpdate(EntityPlayerSP var0, CallbackInfo var1) {
      EntityPlayerSPHooks.onPreLivingUpdate(var0, var1);
   }

   public static void EntityPlayerSP$onUpdateWalkingPlayer(EntityPlayerSP var0, CallbackInfo var1) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      EntityPlayerSPHooks.onUpdateWalkingPlayer(var0, var1);
   }

   public static void EntityLivingBase$onJump(EntityLivingBase var0, CallbackInfo var1) {
      EntityLivingBaseHooks.onJump(var0, var1);
   }

   public static void EntityPlayerSP$onPreUpdate(CallbackInfo var0) {
      EntityPlayerSPHooks.onPreUpdate(var0);
   }
}
