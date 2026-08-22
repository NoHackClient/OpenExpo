package Expo.ASM.Hooks.Entity;

import Expo.ASM.Hooks.CallbackInfoReturnable;
import Expo.ExpoClient;
import Expo.event.events.PostRenderEvent;
import Expo.event.events.PreRenderEvent;
import Expo.module.impl.visual_utility.NameTags;
import Expo.util.render.VisualSpoofRenderer;
import net.minecraft.entity.EntityLivingBase;

public class RendererLivingEntityHooks {
   private static final long a = 107669601928322L;

   public static void canRenderName(EntityLivingBase var0, CallbackInfoReturnable<Boolean> var1) {
      if (NameTags.x.contains(var0)) {
         var1.setReturnValue(false);
         var1.cancel();
      }
   }

   public static void onPostRender(EntityLivingBase var0) {
      if (!VisualSpoofRenderer.H()) {
         ExpoClient.w.e(new PostRenderEvent(var0), 18670087776179L);
      }
   }

   public static void onPreRender(EntityLivingBase var0) {
      if (!VisualSpoofRenderer.H()) {
         ExpoClient.w.e(new PreRenderEvent(var0), 18670087776179L);
      }
   }
}
