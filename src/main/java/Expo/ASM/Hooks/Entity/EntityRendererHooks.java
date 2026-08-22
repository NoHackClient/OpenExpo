package Expo.ASM.Hooks.Entity;

import Expo.ExpoClient;
import Expo.event.events.Render2DEvent;
import Expo.event.events.Render3DEvent;
import Expo.event.events.UpdateCameraAndRenderEvent;
import Expo.internal.accessor.EntityPlayerAccessor;
import Expo.internal.accessor.EntityRendererAccessor;
import Expo.module.ModuleManager;
import Expo.module.impl.configuration.Notifications;
import Expo.module.impl.player.GhostHand;
import Expo.module.impl.visual.NoHurtCam;
import Expo.util.BlockUtil;
import Expo.util.Box;
import Expo.util.ClientUtil;
import Expo.util.ItemUtil;
import Expo.util.MinecraftRef;
import Expo.util.RotationManager;
import Expo.util.RotationUtil;
import Expo.util.Sneaky;
import Expo.util.render.VisualSpoofRenderer;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class EntityRendererHooks {
   private static Box<Integer> s = null;
   private static final String[] c = new String[6];
   private static final long a = 80725206621423L;
   private static final Object[] b = new Object[6];
   private static Box<ItemStack> q = null;
   private static final Minecraft Y;
   private static boolean m = false;

   public static Vec3 redirectGetLook(float var0) {
      return RotationUtil.T(var0);
   }

   private static void announceLoaded() {
      if (!m && Boolean.parseBoolean(System.getProperty("expo.injection.loaded", "false")) && Y.thePlayer != null) {
         m = true;
         new Thread(() -> {
             try {long var0 = 85534997054632L;

            try {
               Thread.sleep(500L);
               String var9 = "Expo injected!";
               if (Notifications.graphic != null && Notifications.graphic.R("CHAT")) {
                  Notifications.G(58053444091952L, var9, true);
               } else {
                  ClientUtil.t(48081174263320L, var9);
                  Notifications.Z(350448395L, var9, (char)43883, true, 4000.0F);
               }
            } catch (InterruptedException var10) {
               Thread.currentThread().interrupt();
            }
         } catch (Throwable zkm$t) { throw Sneaky.rethrow(zkm$t); } }, "Expo announce").start();
      }
   }

   private static void a() {
      b[0] = "\u0013T\u00076~Z\u0018";
      b[1] = float.class;
      c[1] = "java/lang/Float";
      b[2] = long.class;
      c[2] = "java/lang/Long";
      b[3] = void.class;
      c[3] = "java/lang/Void";
      b[4] = "X3\fDj\u0014S<\u001d\u000b\u000b\u001aX7\u0019Q";
      b[5] = "':M\u007fAwqn\u0015\u001aN\u001dao\u001esGx%9\u0018\u001a\u0005`{+\u0018`Mqdnr \u0003x\u007f&\u001cs@t#T";
   }

   public static void postUpdateCameraAndRender() {
      if (q != null) {
         EntityPlayerAccessor.Z(Y.thePlayer, q.Z);
         q = null;
      }

      if (s != null) {
         EntityPlayerAccessor.e(0L, Y.thePlayer, s.Z);
         s = null;
      }
   }

   public static void onRender2D(float var0) throws Throwable {
      if (!VisualSpoofRenderer.x()) {
         if (!VisualSpoofRenderer.H()) {
            VisualSpoofRenderer.l(var0);
            announceLoaded();
            ScaledResolution var8 = new ScaledResolution(Y);
            ExpoClient.w.e(new Render2DEvent(10652, (short)65110, var0, (short)33414, var8), 18670087776179L);
         }
      }
   }

   public static boolean bypassConfusionIfNeeded(Potion var0, EntityLivingBase var1) {
      return var0 == Potion.confusion && ModuleManager.O.o() ? false : var1.getActivePotionEffect(var0) != null;
   }

   static {
      int var2 = 0;
      a();
      Y = MinecraftRef.c((byte)var2,0L);
   }

   public static void updateCameraAndRender() throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      int var10 = -1274601225;

      VisualSpoofRenderer.P();
      if (Y.thePlayer != null && ItemUtil.d()) {
         UpdateCameraAndRenderEvent var15 = new UpdateCameraAndRenderEvent(1652973691147L, false);
         ExpoClient.w.e(var15, 18670087776179L);
         if (var15.r()) {
            q = new Box<>(EntityPlayerAccessor.o((char)0, Y.thePlayer, (char)18093));
            EntityPlayerAccessor.Z(Y.thePlayer, Y.thePlayer.inventory.getCurrentItem());
            s = new Box<>(EntityPlayerAccessor.J(Y.thePlayer));
            EntityPlayerAccessor.e(0L, Y.thePlayer, 69000);
         }
      }
   }

   public static void onGetMouseOverList(Minecraft var0, List<Entity> var1) {
      if (ModuleManager.Q.o() && BlockUtil.a(3484929262775L, var0.playerController.getBlockReachDistance())) {
         GhostHand.T(var1);
      }
   }

   public static Boolean orientCamera(Minecraft var0, float var1, float var2, float var3) {
      if (!ModuleManager.h.o()) {
         return null;
      }

      Entity var4 = var0.getRenderViewEntity();
      float var5 = var4.getEyeHeight();
      if (var4 instanceof EntityLivingBase && ((EntityLivingBase)var4).isPlayerSleeping()) {
         var5++;
         GlStateManager.translate(0.0F, 0.3F, 0.0F);
         if (!var0.gameSettings.debugCamEnable) {
            GlStateManager.rotate(var4.prevRotationYaw + (var4.rotationYaw - var4.prevRotationYaw) * var1 + 180.0F, 0.0F, -1.0F, 0.0F);
            GlStateManager.rotate(var4.prevRotationPitch + (var4.rotationPitch - var4.prevRotationPitch) * var1, -1.0F, 0.0F, 0.0F);
         }
      } else if (var0.gameSettings.thirdPersonView > 0) {
         double var6 = var3 + (var2 - var3) * var1;
         if (var0.gameSettings.debugCamEnable) {
            GlStateManager.translate(0.0F, 0.0F, (float)(-var6));
         } else {
            float var8 = var4.rotationYaw;
            float var9 = var4.rotationPitch;
            if (var0.gameSettings.thirdPersonView == 2) {
               var9 += 180.0F;
            }

            if (var0.gameSettings.thirdPersonView == 2) {
               GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
            }

            GlStateManager.rotate(var4.rotationPitch - var9, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(var4.rotationYaw - var8, 0.0F, 1.0F, 0.0F);
            GlStateManager.translate(0.0F, 0.0F, (float)(-var6));
            GlStateManager.rotate(var8 - var4.rotationYaw, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(var9 - var4.rotationPitch, 1.0F, 0.0F, 0.0F);
         }
      } else {
         GlStateManager.translate(0.0F, 0.0F, -0.1F);
      }

      if (!var0.gameSettings.debugCamEnable) {
         float var10 = var4.prevRotationYaw + (var4.rotationYaw - var4.prevRotationYaw) * var1 + 180.0F;
         float var11 = var4.prevRotationPitch + (var4.rotationPitch - var4.prevRotationPitch) * var1;
         float var12 = 0.0F;
         if (var4 instanceof EntityAnimal) {
            EntityAnimal var18 = (EntityAnimal)var4;
            var10 = var18.prevRotationYawHead + (var18.rotationYawHead - var18.prevRotationYawHead) * var1 + 180.0F;
         }

         GlStateManager.rotate(var12, 0.0F, 0.0F, 1.0F);
         GlStateManager.rotate(var11, 1.0F, 0.0F, 0.0F);
         GlStateManager.rotate(var10, 0.0F, 1.0F, 0.0F);
      }

      GlStateManager.translate(0.0F, -var5, 0.0F);
      double var17 = var4.prevPosX + (var4.posX - var4.prevPosX) * var1;
      double var13 = var4.prevPosY + (var4.posY - var4.prevPosY) * var1 + var5;
      double var15 = var4.prevPosZ + (var4.posZ - var4.prevPosZ) * var1;
      return var0.renderGlobal.hasCloudFog(var17, var13, var15, var1);
   }

   public static boolean bypassBlindnessIfNeeded(Potion var0, EntityLivingBase var1) {
      return var0 == Potion.blindness && ModuleManager.O.o() ? false : var1.getActivePotionEffect(var0) != null;
   }

   public static boolean hurtCameraEffect(Minecraft var0, float var1) {
      if (!(var0.getRenderViewEntity() instanceof EntityLivingBase)) {
         return false;
      }

      EntityLivingBase var2 = (EntityLivingBase)var0.getRenderViewEntity();
      float var3 = var2.hurtTime - var1;
      if (var2.getHealth() <= 0.0F) {
         float var4 = var2.deathTime + var1;
         GlStateManager.rotate(40.0F - 8000.0F / (var4 + 200.0F), 0.0F, 0.0F, 1.0F);
      }

      if (var3 < 0.0F) {
         return true;
      }

      var3 /= var2.maxHurtTime;
      var3 = MathHelper.sin(var3 * var3 * var3 * var3 * (float) Math.PI);
      float var7 = var2.attackedAtYaw;
      if (ModuleManager.g.o()) {
         var3 *= NoHurtCam.effect.k() / 100.0F;
         var7 *= NoHurtCam.effect.k() / 100.0F;
      }

      GlStateManager.rotate(-var7, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(-var3 * 14.0F, 0.0F, 0.0F, 1.0F);
      GlStateManager.rotate(var7, 0.0F, 1.0F, 0.0F);
      return true;
   }

   public static void onRender3D(EntityRenderer var0, float var1) throws Throwable {
      if (!VisualSpoofRenderer.x()) {
         if (!VisualSpoofRenderer.H()) {
            VisualSpoofRenderer.f();
            ScaledResolution var10 = new ScaledResolution(Y);
            RotationManager.z(var1,0L);
            EntityRendererAccessor.k(var0, var1, 0);
            ExpoClient.w.e(new Render3DEvent(var1, var10), 18670087776179L);
         }
      }
   }
}
