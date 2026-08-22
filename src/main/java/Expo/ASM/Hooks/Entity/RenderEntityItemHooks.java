package Expo.ASM.Hooks.Entity;

import Expo.ASM.Hooks.CallbackInfoReturnable;
import Expo.internal.accessor.RenderEntityItemAccessorImpl;
import Expo.module.ModuleManager;
import Expo.module.impl.visual.ItemScale;
import Expo.util.LunarClientDetector;
import Expo.util.MinecraftRef;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.entity.RenderEntityItem;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;

public class RenderEntityItemHooks {
   private static final Minecraft M;
   private static final long a = 83471637984391L;

   static {
      int var2 = 0;
      M = MinecraftRef.c((byte)var2,0L);
   }

   public static void onRenderEntityItem(
      RenderEntityItem var0, EntityItem var1, double var2, double var4, double var6, float var8, IBakedModel var9, CallbackInfoReturnable<Integer> var10
   ) {
      if (ModuleManager.v.o()) {
         ItemStack var18 = var1.getEntityItem();
         Item var19 = var18.getItem();
         if (var19 == null) {
            var10.setReturnValue(0);
            return;
         }

         boolean var20 = var9.isGui3d();
         int var21 = RenderEntityItemAccessorImpl.x(var0, var18);
         float var22 = MathHelper.sin((var1.getAge() + var8) / 10.0F + var1.hoverStart) * 0.1F + 0.1F;
         float var23 = var9.getItemCameraTransforms().getTransform(TransformType.GROUND).scale.y;
         GlStateManager.translate((float)var2, (float)var4 + var22 + 0.25F * var23, (float)var6);
         if (var20 || MinecraftRef.c((byte)0,0L).getRenderManager().options != null) {
            if (var9.isGui3d()) {
               float var24 = ((var1.getAge() + var8) / 20.0F + var1.hoverStart) * (180.0F / (float)Math.PI);
               GlStateManager.rotate(var24, 0.0F, 1.0F, 0.0F);
            } else {
               GlStateManager.rotate(180.0F - M.getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
               GlStateManager.rotate(getItemPitchRotation(), 1.0F, 0.0F, 0.0F);
            }
         }

         if (!var20) {
            float var27 = -0.0F * (var21 - 1) * 0.5F;
            float var25 = -0.0F * (var21 - 1) * 0.5F;
            float var26 = -0.046875F * (var21 - 1) * 0.5F;
            GlStateManager.translate(var27, var25, var26);
         }

         float var28 = -0.0F * (var21 - 1) * 0.5F;
         float var29 = -0.0F * (var21 - 1) * 0.5F;
         float var30 = -0.046875F * (var21 - 1) * 0.5F;
         if (ItemScale.c(var18)) {
            GlStateManager.translate(var28, var29 + ItemScale.scale.L() / 8.0F, var30);
            GlStateManager.scale(ItemScale.scale.L(), ItemScale.scale.L(), ItemScale.scale.L());
         }

         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         var10.setReturnValue(var21);
         var10.cancel();
      }
   }

   private static float getItemPitchRotation() {
      if (LunarClientDetector.q(0L)) {
         return -M.getRenderManager().playerViewX;
      } else {
         return M.gameSettings.thirdPersonView == 2 ? M.getRenderManager().playerViewX : -M.getRenderManager().playerViewX;
      }
   }
}
