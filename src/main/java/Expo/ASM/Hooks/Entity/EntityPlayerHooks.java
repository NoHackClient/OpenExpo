package Expo.ASM.Hooks.Entity;

import Expo.ASM.Hooks.CallbackInfo;
import Expo.ASM.Hooks.CallbackInfoReturnable;
import Expo.ExpoClient;
import Expo.event.events.AttackTargetEntityEvent;
import Expo.event.events.GetDisplayNameEvent;
import Expo.event.events.PostItemUseFinishEvent;
import Expo.internal.accessor.EnchantmentHelperAccessorImpl;
import Expo.module.Modules;
import Expo.module.impl.combat.KeepSprint;
import Expo.util.MinecraftRef;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.potion.Potion;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;

public class EntityPlayerHooks {
   private static final Minecraft D;
   private static final long a = 52432132721974L;

   public static void onGetDisplayName(EntityPlayer var0, CallbackInfoReturnable<IChatComponent> var1) {
      GetDisplayNameEvent var8 = new GetDisplayNameEvent(var0, (IChatComponent)var1.getReturnValue());
      ExpoClient.w.e(var8, 18670087776179L);
      var1.setReturnValue(var8.c());
   }

   static {
      int var2 = 0;
      D = MinecraftRef.c((byte)var2,0L);
   }

   public static void onPostItemUseFinish() {
      ExpoClient.w.e(new PostItemUseFinishEvent(), 18670087776179L);
   }

   public static void onAttackTargetEntity(EntityPlayer var0, Entity var1, CallbackInfo var2) {
      if (var1.canAttackWithItem() && !var1.hitByEntity(var0)) {
         float var11 = (float)var0.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
         int var12 = 0;
         float var13;
         if (var1 instanceof EntityLivingBase) {
            var13 = EnchantmentHelperAccessorImpl.P(var0.getHeldItem(), ((EntityLivingBase)var1).getCreatureAttribute());
         } else {
            var13 = EnchantmentHelperAccessorImpl.P(var0.getHeldItem(), EnumCreatureAttribute.UNDEFINED);
         }

         var12 += EnchantmentHelper.getKnockbackModifier(var0);
         if (var0.isSprinting()) {
            var12++;
         }

         if (var11 > 0.0F || var13 > 0.0F) {
            boolean var14 = var0.fallDistance > 0.0F
               && !var0.onGround
               && !var0.isOnLadder()
               && !var0.isInWater()
               && !var0.isPotionActive(Potion.blindness)
               && var0.ridingEntity == null
               && var1 instanceof EntityLivingBase;
            if (var14 && var11 > 0.0F) {
               var11 *= 1.5F;
            }

            var11 += var13;
            boolean var15 = false;
            int var16 = EnchantmentHelper.getFireAspectModifier(var0);
            if (var1 instanceof EntityLivingBase && var16 > 0 && !var1.isBurning()) {
               var15 = true;
               var1.setFire(1);
            }

            double var17 = var1.motionX;
            double var19 = var1.motionY;
            double var21 = var1.motionZ;
            boolean var23 = var1.attackEntityFrom(DamageSource.causePlayerDamage(var0), var11);
            if (var23) {
               if (var12 > 0) {
                  var1.addVelocity(
                     -MathHelper.sin(var0.rotationYaw * (float) Math.PI / 180.0F) * var12 * 0.5F,
                     0.1,
                     MathHelper.cos(var0.rotationYaw * (float) Math.PI / 180.0F) * var12 * 0.5F
                  );
                  if (Modules.J(KeepSprint.class).o()) {
                     KeepSprint.k(0L);
                  } else {
                     var0.motionX *= 0.6;
                     var0.motionZ *= 0.6;
                     var0.setSprinting(false);
                  }
               }

               if (var1 instanceof EntityPlayerMP && var1.velocityChanged) {
                  ((EntityPlayerMP)var1).playerNetServerHandler.sendPacket(new S12PacketEntityVelocity(var1));
                  var1.velocityChanged = false;
                  var1.motionX = var17;
                  var1.motionY = var19;
                  var1.motionZ = var21;
               }

               if (var11 >= 18.0F) {
                  D.thePlayer.triggerAchievement(AchievementList.overkill);
               }

               var0.setLastAttacker(var1);
               if (var1 instanceof EntityLivingBase) {
                  EnchantmentHelper.applyThornEnchantments((EntityLivingBase)var1, var0);
               }

               EnchantmentHelper.applyArthropodEnchantments(var0, var1);
               ItemStack var24 = D.thePlayer.getCurrentEquippedItem();
               Object var25 = var1;
               if (var1 instanceof EntityDragonPart) {
                  IEntityMultiPart var26 = ((EntityDragonPart)var1).entityDragonObj;
                  if (var26 instanceof EntityLivingBase) {
                     var25 = (EntityLivingBase)var26;
                  }
               }

               Object var29 = var25;
               if (var24 != null && var29 instanceof EntityLivingBase) {
                  var24.hitEntity((EntityLivingBase)var29, var0);
                  if (var24.stackSize <= 0) {
                     D.thePlayer.destroyCurrentEquippedItem();
                  }
               }

               if (var1 instanceof EntityLivingBase) {
                  D.thePlayer.addStat(StatList.damageDealtStat, Math.round(var11 * 10.0F));
                  if (var16 > 0) {
                     var1.setFire(var16 * 4);
                  }
               }

               D.thePlayer.addExhaustion(0.3F);
            } else if (var15) {
               var1.extinguish();
            }
         }

         ExpoClient.w.e(new AttackTargetEntityEvent(var1), 18670087776179L);
      }

      var2.cancel();
   }
}
