package Expo.util;

import Expo.ExpoClient;
import Expo.event.events.AttackEntityEvent;
import Expo.event.events.AttackTargetEntityEvent;
import Expo.internal.accessor.MethodAccessors;
import Expo.internal.accessor.MinecraftAccessor;
import Expo.internal.accessor.PlayerControllerAccessor;
import Expo.module.Modules;
import Expo.module.impl.combat.KeepSprint;
import Expo.module.impl.player.AutoWeapon;
import Expo.util.packet.PacketManager;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.network.play.client.C02PacketUseEntity.Action;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C0APacketAnimation;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.potion.Potion;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldSettings.GameType;

public class CombatUtil {
   private static String[] b;
   private static long[] e;
   private static long a;
   private static String[] c;
   private static Minecraft w;

   public static float D(EntityLivingBase var0) {
      return var0.getAbsorptionAmount();
   }

   public static float P(EntityLivingBase var0) {
      return var0.getHealth() + var0.getAbsorptionAmount();
   }

   public static boolean T(AxisAlignedBB var0) {
      if (!w.thePlayer.isInWater() && !w.thePlayer.isInLava()) {
         int var1 = MathHelper.floor_double(var0.minY);
         if (var1 < 0) {
            return true;
         }

         int var2 = MathHelper.floor_double(var0.minX);
         int var3 = MathHelper.floor_double(var0.maxX + 1.0);
         int var4 = MathHelper.floor_double(var0.minZ);
         int var5 = MathHelper.floor_double(var0.maxZ + 1.0);

         for (int var6 = var2; var6 < var3; var6++) {
            for (int var7 = var4; var7 < var5; var7++) {
               for (int var8 = var1; var8 >= 0; var8--) {
                  if (!BlockUtil.a$r1(new BlockPos(var6, var8, var7))) {
                     return false;
                  }
               }
            }
         }

         return true;
      } else {
         return false;
      }
   }

   public static float h(EntityLivingBase var0) {
      return var0.getHealth();
   }

   public static int G(long var0, EntityLivingBase var2) {
      var0 = a ^ var0;
      long var3 = var0 ^ 132181464488574L;
      long var5 = (var0 ^ 125783940562436L) >>> 16;
      int var7 = (int)((var0 ^ 125783940562436L) << 48 >>> 48);
      return x(var2, w.thePlayer.inventory.getStackInSlot(AutoWeapon.M(var3)), var5, (short)var7);
   }

   static {
      a = 90005592299191L;
      w = MinecraftRef.c((byte)0, 0L);
   }

   public static int x(EntityLivingBase var0, ItemStack var1, long var2, short var4) {
      long var5 = (var2 << 16 | (long)var4 << 48 >>> 48) ^ a;
      long var7 = var5 ^ 122959646357771L;
      return (int)Math.ceil(Y(var7, var0, var1));
   }

   public static boolean u() {
      return T(w.thePlayer.getEntityBoundingBox().expand(-1.0E-6, 0.0, -1.0E-6));
   }

   private static boolean isGetHeldItem(BlockPos var0, EnumFacing var1, Vec3 var2) {
      return w.playerController.onPlayerRightClick(w.thePlayer, w.theWorld, w.thePlayer.getHeldItem(), var0, var1, var2);
   }

   public static void G(int var0, BlockPos var1, EnumFacing var2) {
      if (w.thePlayer.capabilities.allowEdit) {
         MinecraftAccessor.c(w, 0,0L);
         w.playerController.onPlayerDamageBlock(var1, var2);
      }
   }

   public static double Y(long var0, EntityLivingBase var2, ItemStack var3) {
      var0 = a ^ var0;
      int var4 = (int)((var0 ^ 47436140358723L) >>> 48);
      int var5 = (int)((var0 ^ 47436140358723L) << 16 >>> 48);
      double var7 = 1.0;
      if (var3 != null && (var3.getItem() instanceof ItemSword || var3.getItem() instanceof ItemAxe)) {
         var7 += ItemUtil.p((short)var4, var3, (char)var5);
      }

      double var9 = 0.0;
      double var11 = 0.0;

      for (int var13 = 0; var13 < 4; var13++) {
         ItemStack var14 = var2.getCurrentArmor(var13);
         if (var14 != null && var14.getItem() instanceof ItemArmor) {
            var9 += ((ItemArmor)var14.getItem()).damageReduceAmount * 0.04;
            int var15 = EnchantmentHelper.getEnchantmentLevel(Enchantment.protection.effectId, var14);
            if (var15 != 0) {
               var11 += Math.floor(0.75 * (6 + var15 * var15) / 3.0);
            }
         }
      }

      return N((h(var2) + D(var2)) / (var7 * (1.0 - (var9 + 0.04 * Math.min(Math.ceil(Math.min(var11, 25.0) * 0.75), 20.0) * (1.0 - var9)))), 1);
   }

   public static boolean I(Entity var0, int var1, long var2) {
      long var4 = ((long)var1 << 32 | var2 << 32 >>> 32) ^ a;
      int var6 = (int)((var4 ^ 50004556285831L) >>> 48);
      int var7 = (int)((var4 ^ 50004556285831L) << 16 >>> 48);
      int var8 = (int)((var4 ^ 50004556285831L) << 32 >>> 32);
      long var9 = var4 ^ 116542063757799L;
      long var11 = var4 ^ 109822322819622L;
      AttackEntityEvent var13 = new AttackEntityEvent(var0, (char)var6, (short)var7, var8);
      ExpoClient.w.e(var13, var9);
      if (!var13.a()) {
         PlayerControllerAccessor.Q(w.playerController);
         PacketManager.b(new C02PacketUseEntity(var0, Action.ATTACK));
         if (w.playerController.getCurrentGameType() != GameType.SPECTATOR) {
            s(var0, var11);
         }

         return true;
      } else {
         return false;
      }
   }

   public static double N(double var0, int var2) {
      if (var2 == 0) {
         return Math.round(var0);
      }

      double var3 = Math.pow(10.0, var2);
      return Math.round(var0 * var3) / var3;
   }

   public static boolean s(long var0, EntityLivingBase var2) {
      long var3 = var0 ^ 69009294105050L;
      float var5 = w.thePlayer.getHealth() + w.thePlayer.getAbsorptionAmount();
      float var6 = var2.getHealth() + var2.getAbsorptionAmount();
      float var7 = w.thePlayer.getMaxHealth();
      float var8 = var2.getMaxHealth();
      int var9 = G(var3, var2);
      int var10 = G(var3, w.thePlayer);
      float var11 = var5 / var7;
      float var12 = var6 / var8;
      float var13 = 0.0F;
      if (var9 <= var10) {
         var13 += 60.0F;
      } else {
         var13 += 35.0F * ((float)var10 / var9);
      }

      var13 += (var11 - var12) * 40.0F;
      return var13 >= 55.0F;
   }

   public static boolean u(BlockPos var0, EnumFacing var1, Vec3 var2, boolean var3, boolean var4) {
      boolean var5 = isGetHeldItem(var0, var1, var2);
      if (var5) {
         if (var3) {
            w.thePlayer.swingItem();
         } else {
            PacketManager.b(new C0APacketAnimation());
         }

         if (var4) {
            w.entityRenderer.itemRenderer.resetEquippedProgress();
         }
      }

      return var5;
   }

   private static int k() {
      return w.thePlayer.isPotionActive(Potion.digSpeed)
         ? 6 - (1 + w.thePlayer.getActivePotionEffect(Potion.digSpeed).getAmplifier())
         : (
            w.thePlayer.isPotionActive(Potion.digSlowdown)
               ? 6 + (1 + w.thePlayer.getActivePotionEffect(Potion.digSlowdown).getAmplifier()) * 2
               : 6
         );
   }

   public static int q() {
      if (w.getNetHandler() == null) {
         return 0;
      }

      NetworkPlayerInfo var0 = w.getNetHandler().getPlayerInfo(w.thePlayer.getUniqueID());
      return var0 == null ? 0 : var0.getResponseTime();
   }

   public static String h(float var0, float var1, long var2) {
      if (var0 <= var1 / 8.0F) {
         return "§4";
      } else if (var0 > var1 / 8.0F && var0 <= var1 / 3.0F) {
         return "§c";
      } else if (var0 > var1 / 3.0F && var0 <= var1 / 1.5) {
         return "§e";
      } else {
         return var0 > var1 / 1.5 && var0 <= var1 ? "§a" : "§a";
      }
   }

   private static void s(Entity var0, long var1) {
      long var7 = var1 ^ 11117804227521L;
      if (var0.canAttackWithItem() && !var0.hitByEntity(w.thePlayer)) {
         float var9 = (float)w.thePlayer.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
         float var10 = Expo.internal.accessor.EnchantmentHelperAccessorImpl.P(
            w.thePlayer.getHeldItem(), var0 instanceof EntityLivingBase ? ((EntityLivingBase)var0).getCreatureAttribute() : EnumCreatureAttribute.UNDEFINED
         );
         int var11 = EnchantmentHelper.getKnockbackModifier(w.thePlayer);
         if (w.thePlayer.isSprinting()) {
            var11++;
         }

         if (var9 > 0.0F || var10 > 0.0F) {
            boolean var12 = w.thePlayer.fallDistance > 0.0F
               && !w.thePlayer.onGround
               && !w.thePlayer.isOnLadder()
               && !w.thePlayer.isInWater()
               && !w.thePlayer.isPotionActive(Potion.blindness)
               && w.thePlayer.ridingEntity == null;
            if (var12 && var9 > 0.0F) {
               var9 *= 1.5F;
            }

            var9 += var10;
            boolean var13 = false;
            int var14 = EnchantmentHelper.getFireAspectModifier(w.thePlayer);
            if (var0 instanceof EntityLivingBase && var14 > 0 && !var0.isBurning()) {
               var13 = true;
               var0.setFire(1);
            }

            double var15 = var0.motionX;
            double var17 = var0.motionY;
            double var19 = var0.motionZ;
            if (var0.attackEntityFrom(DamageSource.causePlayerDamage(w.thePlayer), var9)) {
               if (var11 > 0) {
                  var0.addVelocity(
                     -MathHelper.sin(w.thePlayer.rotationYaw * (float) Math.PI / 180.0F) * var11 * 0.5F,
                     0.1,
                     MathHelper.cos(w.thePlayer.rotationYaw * (float) Math.PI / 180.0F) * var11 * 0.5F
                  );
                  if (Modules.J(KeepSprint.class).o()) {
                     KeepSprint.k(0L);
                  } else {
                     w.thePlayer.motionX *= 0.6;
                     w.thePlayer.motionZ *= 0.6;
                     w.thePlayer.setSprinting(false);
                  }
               }

               if (var0 instanceof EntityPlayerMP && var0.velocityChanged) {
                  ((EntityPlayerMP)var0).playerNetServerHandler.sendPacket(new S12PacketEntityVelocity(var0));
                  var0.velocityChanged = false;
                  var0.motionX = var15;
                  var0.motionY = var17;
                  var0.motionZ = var19;
               }

               if (var12) {
                  w.thePlayer.onCriticalHit(var0);
               }

               if (var10 > 0.0F) {
                  w.thePlayer.onEnchantmentCritical(var0);
               }

               if (var9 >= 18.0F) {
                  w.thePlayer.triggerAchievement(AchievementList.overkill);
               }

               w.thePlayer.setLastAttacker(var0);
               if (var0 instanceof EntityLivingBase) {
                  EnchantmentHelper.applyThornEnchantments((EntityLivingBase)var0, w.thePlayer);
               }

               EnchantmentHelper.applyArthropodEnchantments(w.thePlayer, var0);
               if (var0 instanceof EntityLivingBase) {
                  w.thePlayer.addStat(StatList.damageDealtStat, Math.round(var9 * 10.0F));
                  if (var14 > 0) {
                     var0.setFire(var14 * 4);
                  }
               }

               w.thePlayer.addExhaustion(0.3F);
            } else if (var13) {
               var0.extinguish();
            }
         }

         ExpoClient.w.e(new AttackTargetEntityEvent(var0), var7);
      }
   }

   public static String h(EntityLivingBase var0, long var1, ItemStack var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var4 = var1 ^ 73665897172445L;
      int var6 = (int)Math.ceil(Y(var4, var0, var3));
      return "\u00a7b" + var6;
   }

   public static void X(int var0, int var1, char var2) {
      ItemStack var7 = w.thePlayer.getHeldItem();
      if (var7 == null || var7.getItem() == null || !MethodAccessors.V(var7.getItem(), w.thePlayer, var7)) {
         if (!w.thePlayer.isSwingInProgress || w.thePlayer.swingProgressInt >= k() / 2 || w.thePlayer.swingProgressInt < 0) {
            w.thePlayer.swingProgressInt = -1;
            w.thePlayer.isSwingInProgress = true;
         }
      }
   }
}
