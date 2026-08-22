package Expo.ASM.Hooks.Player;

import Expo.ASM.Hooks.CallbackInfo;
import Expo.ASM.Hooks.CallbackInfoReturnable;
import Expo.ASM.Hooks.MiscHooks;
import Expo.ExpoClient;
import Expo.event.events.AttackEntityEvent;
import Expo.event.events.ClickBlockReturnEvent;
import Expo.event.events.PlayerRightClickEvent;
import Expo.event.events.PostStoppedUsingItemEvent;
import Expo.event.events.PreStoppedUsingItemEvent;
import Expo.event.events.TryHarvestBlockHeadEvent;
import Expo.internal.accessor.PlayerControllerAccessor;
import Expo.internal.accessor.PlayerControllerStateAccessor;
import Expo.module.Modules;
import Expo.module.impl.visual.Animations;
import Expo.util.MinecraftRef;
import Expo.util.packet.PacketManager;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C07PacketPlayerDigging.Action;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;

public class PlayerControllerHooks {
   private static final Minecraft V;
   private static final long a = 66600080902543L;

   public static void onAttackEntity(Entity var0, CallbackInfo var1) {
      AttackEntityEvent var9 = new AttackEntityEvent(var0, (char)0, (short)21630, -261330477);
      ExpoClient.w.e(var9, 18670087776179L);
      if (var9.a()) {
         var1.cancel();
      }
   }

   public static void clickBlockReturn(BlockPos var0, CallbackInfoReturnable<Boolean> var1) {
      if ((Boolean)var1.getReturnValue()) {
         ExpoClient.w.e(new ClickBlockReturnEvent(var0), 18670087776179L);
      }
   }

   public static void onPreStoppedUsingItem(CallbackInfo var0) {
      if (MiscHooks.minecraftShouldCancelStoppedUsingItem()) {
         var0.cancel();
      } else {
         PreStoppedUsingItemEvent var7 = new PreStoppedUsingItemEvent();
         ExpoClient.w.e(var7, 18670087776179L);
         if (var7.a()) {
            var0.cancel();
         }
      }
   }

   public static void onPostStoppedUsingItem() {
      ExpoClient.w.e(new PostStoppedUsingItemEvent((char)0, (short)23334, 1287003355), 18670087776179L);
   }

   public static void onDamageBlock(BlockPos var0, EnumFacing var1, PlayerControllerMP var2, CallbackInfoReturnable<Boolean> var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      if (V.thePlayer.isUsingItem()) {
         var3.setReturnValue(false);
         var3.cancel();
      } else {
         PlayerControllerAccessor.Q(var2);
         if (PlayerControllerStateAccessor.W(var2) > 0) {
            PlayerControllerStateAccessor.w((byte)0, 7374982, 11824981, var2, PlayerControllerStateAccessor.W(var2) - 1);
            var3.setReturnValue(true);
            var3.cancel();
         } else if (var2.getCurrentGameType().isCreative() && V.theWorld.getWorldBorder().contains(var0)) {
            PlayerControllerStateAccessor.w((byte)0, 7374982, 11824981, var2, 5);
            PacketManager.b(new C07PacketPlayerDigging(Action.START_DESTROY_BLOCK, var0, var1));
            PlayerControllerMP.clickBlockCreative(V, var2, var0, var1);
            var3.setReturnValue(true);
            var3.cancel();
         } else if (PlayerControllerAccessor.E(var2, var0)) {
            Block var23 = V.theWorld.getBlockState(var0).getBlock();
            if (var23.getMaterial() == Material.air) {
               PlayerControllerStateAccessor.Q(0L, var2, false);
               var3.setReturnValue(false);
               var3.cancel();
            } else {
               float var24 = var23.getPlayerRelativeBlockHardness(V.thePlayer, V.thePlayer.worldObj, var0);
               PlayerControllerStateAccessor.e(0L, var2, PlayerControllerStateAccessor.s(0L, var2) + var24);
               if (PlayerControllerStateAccessor.v(var2,0L) % 4.0F == 0.0F) {
                  V.getSoundHandler()
                     .playSound(
                        new PositionedSoundRecord(
                           new ResourceLocation(var23.stepSound.getStepSound()),
                           (var23.stepSound.getVolume() + 1.0F) / 8.0F,
                           var23.stepSound.getFrequency() * 0.5F,
                           var0.getX() + 0.5F,
                           var0.getY() + 0.5F,
                           var0.getZ() + 0.5F
                        )
                     );
               }

               PlayerControllerStateAccessor.W(0L, var2, PlayerControllerStateAccessor.v(var2,0L) + 1.0F);
               if (PlayerControllerStateAccessor.s(0L, var2) >= 1.0F) {
                  PlayerControllerStateAccessor.Q(0L, var2, false);
                  PacketManager.b(new C07PacketPlayerDigging(Action.STOP_DESTROY_BLOCK, var0, var1));
                  var2.onPlayerDestroyBlock(var0, var1);
                  PlayerControllerStateAccessor.e(0L, var2, 0.0F);
                  PlayerControllerStateAccessor.W(0L, var2, 0.0F);
                  PlayerControllerStateAccessor.w((byte)0, 7374982, 11824981, var2, 5);
               }

               V.theWorld.sendBlockBreakProgress(V.thePlayer.getEntityId(), PlayerControllerStateAccessor.Z(var2), (int)(PlayerControllerStateAccessor.s(0L, var2) * 10.0F) - 1);
               var3.setReturnValue(true);
               var3.cancel();
            }
         } else {
            var3.setReturnValue(var2.clickBlock(var0, var1));
            var3.cancel();
         }
      }
   }

   public static void tryHarvestBlockHead(BlockPos var0) {
      ExpoClient.w.e(new TryHarvestBlockHeadEvent(var0), 18670087776179L);
   }

   public static void onPlayerRightClick(WorldClient var0, ItemStack var1, BlockPos var2, EnumFacing var3, Vec3 var4, CallbackInfoReturnable<Boolean> var5) {
      PlayerRightClickEvent var12 = new PlayerRightClickEvent(var0, var1, var2, var3, var4);
      ExpoClient.w.e(var12, 18670087776179L);
      if (var12.a()) {
         var5.setReturnValue(false);
         var5.cancel();
      }
   }

   static {
      int var2 = 0;
      V = MinecraftRef.c((byte)var2,0L);
   }

   public static void onDamageBlockAfterSync(CallbackInfoReturnable<Boolean> var0) {
      if (V.thePlayer.isUsingItem() && Modules.J(Animations.class).o()) {
         var0.setReturnValue(true);
         var0.cancel();
      }
   }
}
