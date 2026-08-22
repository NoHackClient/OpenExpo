package Expo.ASM.Hooks.Render;

import Expo.ExpoClient;
import Expo.event.events.PostRenderModelBipedEvent;
import Expo.event.events.PreRenderModelBipedEvent;
import Expo.util.ClientUtil;
import Expo.util.MathUtil;
import Expo.util.RotationManager;
import Expo.util.render.VisualSpoofRenderer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;











public class ModelBipedHooks {
   private static final long a = 93194583118106L;

   public static void onRenderPre(Entity var0) {

      if (!VisualSpoofRenderer.H()) {
         ExpoClient.w.e(new PreRenderModelBipedEvent(var0), 18670087776179L);
      }
   }

   public static void onRenderPost(Entity var0) {

      if (!VisualSpoofRenderer.H()) {
         ExpoClient.w.e(new PostRenderModelBipedEvent(var0), 18670087776179L);
      }
   }

   public static float modifyHeadPitch(float var0, Entity var1) {

      return var1 instanceof EntityPlayerSP ? MathUtil.r(RotationManager.F, RotationManager.K, ClientUtil.b(75703014522979L).renderPartialTicks) : var0;
   }
}
