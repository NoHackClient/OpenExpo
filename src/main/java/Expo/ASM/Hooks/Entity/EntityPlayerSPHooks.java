package Expo.ASM.Hooks.Entity;

import Expo.ASM.Hooks.CallbackInfo;
import Expo.ExpoClient;
import Expo.event.events.CloseScreenEvent;
import Expo.event.events.PostUpdateEvent;
import Expo.event.events.PostUpdateWalkingPlayerEvent;
import Expo.event.events.PreLivingUpdateEvent;
import Expo.event.events.PreSuperLivingUpdateEvent;
import Expo.event.events.PreUpdateEvent;
import Expo.event.events.RedirectIsUsingItemEvent;
import Expo.event.events.UpdateWalkingPlayerEvent;
import Expo.internal.accessor.EntityPlayerSPAccessor;
import Expo.internal.jnic.StockCommandRegistry;
import Expo.module.Module;
import Expo.module.Modules;
import Expo.module.impl.misc.CommandLine;
import Expo.module.impl.world.Scaffold;
import Expo.util.MinecraftRef;
import Expo.util.RotationManager;
import Expo.util.packet.PacketManager;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.network.play.client.C01PacketChatMessage;
import net.minecraft.network.play.client.C03PacketPlayer.C04PacketPlayerPosition;
import net.minecraft.network.play.client.C03PacketPlayer.C05PacketPlayerLook;
import net.minecraft.network.play.client.C03PacketPlayer.C06PacketPlayerPosLook;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C0BPacketEntityAction.Action;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import net.minecraft.util.MovementInputFromOptions;

public class EntityPlayerSPHooks {
   private static long b;
   private static long a;
   private static Minecraft w;
   private static String[] d;
   private static Object[] c;

   static {
      a = 86858135230569L;
      w = MinecraftRef.c((byte)0, 0L);
      c = new Object[8];
      d = new String[8];
      b = 5738260080516661268L;
   }

   public static void onPreUpdate(CallbackInfo var0) {
      RotationManager.L = RotationManager.I;
      RotationManager.F = RotationManager.K;
      PreUpdateEvent var8 = new PreUpdateEvent(20258, 53049, 64041);
      ExpoClient.w.e(var8, 18670087776179L);
      if (var8.a()) {
         var0.cancel();
      }
   }

   public static void onSendChatMessage(String var0, CallbackInfo var1) {
      if (var0 != null) {
         Module var7 = Modules.J(CommandLine.class);
         if (var7 != null && var7.o()) {
            if (var0.startsWith(".")) {
               StockCommandRegistry.E(27284, (char)12607, (char)37714, var0);
            } else {
               PacketManager.b(new C01PacketChatMessage(var0));
            }

            var1.cancel();
         }
      }
   }

   public static void onPreSuperLivingUpdate() {
      PreSuperLivingUpdateEvent var7 = new PreSuperLivingUpdateEvent((char)0);
      ExpoClient.w.e(var7, 18670087776179L);
   }

   public static void onPreLivingUpdate(EntityPlayerSP var0, CallbackInfo var1) {
      Q(var0);
      PreLivingUpdateEvent var9 = new PreLivingUpdateEvent(4433, (byte)215, 4672537);
      ExpoClient.w.e(var9, 18670087776179L);
      if (var9.a()) {
         var1.cancel();
      }
   }

   private static void Q(EntityPlayerSP var0) {
      if (var0 != null && var0.movementInput == null) {
         Minecraft var6 = MinecraftRef.c((byte)0,0L);
         GameSettings var7 = var6 == null ? null : var6.gameSettings;
         if (var7 != null) {
            var0.movementInput = new MovementInputFromOptions(var7);
         }
      }
   }

   public static void redirectIsUsingItem(EntityPlayerSP var0) {
      if (var0 != null && var0.movementInput != null) {
         RedirectIsUsingItemEvent var7 = new RedirectIsUsingItemEvent(0.2F);
         ExpoClient.w.e(var7, 18670087776179L);
         if (!var7.v()) {
            var0.movementInput.moveStrafe = var0.movementInput.moveStrafe * var7.q();
            var0.movementInput.moveForward = var0.movementInput.moveForward * var7.q();
         }
      }
   }

   public static void onUpdateWalkingPlayer(EntityPlayerSP var0, CallbackInfo var1) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      int var31 = 32593;

      UpdateWalkingPlayerEvent var45 = new UpdateWalkingPlayerEvent(
         var0.posX,
         var0.posY,
         var0.posZ,
         RotationManager.p(),
         RotationManager.s(),
         var0.onGround,
         var0.isSprinting(),
         var0.isSneaking(),
         false
      );
      ExpoClient.w.e(var45, 18670087776179L);
      if (!var45.a()) {
         boolean var46 = var0.isSprinting();
         if (var46 != EntityPlayerSPAccessor.N( var0) && !var45.I()) {
            if (var46) {
               PacketManager.b(new C0BPacketEntityAction(var0, Action.START_SPRINTING));
            } else {
               PacketManager.b(new C0BPacketEntityAction(var0, Action.STOP_SPRINTING));
            }

            EntityPlayerSPAccessor.N(var0, var46, (short)0);
         }

         boolean var47 = var0.isSneaking();
         if (var47 != EntityPlayerSPAccessor.f( var0) && !var45.I()) {
            if (var47) {
               PacketManager.b(new C0BPacketEntityAction(var0, Action.START_SNEAKING));
            } else {
               PacketManager.b(new C0BPacketEntityAction(var0, Action.STOP_SNEAKING));
            }

            EntityPlayerSPAccessor.l( var0, var47);
         }

         if (w.getRenderViewEntity() == var0) {
            if (!Scaffold.Z()) {
               if (!RotationManager.U) {
                  var0.rotationYawHead = var45.O();
                  RotationManager.I = var45.O();
                  RotationManager.K = var45.P();
               } else {
                  var0.rotationYawHead = RotationManager.p();
                  RotationManager.I = RotationManager.p();
                  RotationManager.K = RotationManager.s();
               }
            }

            double var48 = var45.F() - EntityPlayerSPAccessor.I(0L, var0);
            double var50 = var45.s() - EntityPlayerSPAccessor.M( var0);
            double var52 = var45.U() - EntityPlayerSPAccessor.C(var0);
            double var54 = var45.O() - EntityPlayerSPAccessor.n(var0,0L);
            double var56 = var45.P() - EntityPlayerSPAccessor.Q(var0);
            boolean var58 = var48 * var48 + var50 * var50 + var52 * var52 > 9.0E-4 || EntityPlayerSPAccessor.L( var0) >= (int)b;
            boolean var59 = var54 != 0.0 || var56 != 0.0;
            if (var0.ridingEntity != null && !var45.I()) {
               PacketManager.b(new C06PacketPlayerPosLook(var0.motionX, -999.0, var0.motionZ, var45.O(), var45.P(), var45.f()));
               var58 = false;
            } else if (!var45.I()) {
               if (var58 && var59) {
                  PacketManager.b(
                     new C06PacketPlayerPosLook(var0.posX, var0.getEntityBoundingBox().minY, var0.posZ, var45.O(), var45.P(), var45.f())
                  );
               } else if (var58) {
                  PacketManager.b(new C04PacketPlayerPosition(var0.posX, var0.getEntityBoundingBox().minY, var0.posZ, var45.f()));
               } else if (var59) {
                  PacketManager.b(new C05PacketPlayerLook(var45.O(), var45.P(), var45.f()));
               } else {
                  PacketManager.b(new C03PacketPlayer(var45.f()));
               }
            }

            EntityPlayerSPAccessor.K(var0, EntityPlayerSPAccessor.L( var0) + 1);
            if (var58) {
               EntityPlayerSPAccessor.i(var0, var45.F(), 37110125974099L);
               EntityPlayerSPAccessor.z(64804680637181L, var0, var45.s());
               EntityPlayerSPAccessor.s((char)0, var0, var45.U(), 941714478, (char)var31);
               EntityPlayerSPAccessor.K(var0, 0);
            }

            if (var59) {
               EntityPlayerSPAccessor.q(var0, var45.O());
               EntityPlayerSPAccessor.S( var0, var45.P());
            }
         }

         ExpoClient.w.e(new PostUpdateWalkingPlayerEvent(7839), 18670087776179L);
         var1.cancel();
      }
   }

   public static void onPostUpdate() {
      RotationManager.r(8215146884547L);
      ExpoClient.w.e(new PostUpdateEvent(), 18670087776179L);
   }

   public static void onCloseScreen() {
      ExpoClient.w.e(new CloseScreenEvent(), 18670087776179L);
   }
}
