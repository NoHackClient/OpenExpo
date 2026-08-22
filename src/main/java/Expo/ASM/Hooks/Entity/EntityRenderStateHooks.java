package Expo.ASM.Hooks.Entity;

import Expo.ExpoClient;
import Expo.event.events.EntityRenderStateEvent;
import Expo.util.render.VisualSpoofRenderer;
import net.minecraft.entity.Entity;

public class EntityRenderStateHooks {
   private static final long a = 48235926059284L;

   public static void onRenderPre(Entity var0) {
      if (!VisualSpoofRenderer.H()) {
         ExpoClient.w.e(new EntityRenderStateEvent((short)0, (char)26096, 2592973, var0), 18670087776179L);
      }
   }

   public static void onRenderPost(Entity var0) {
      if (!VisualSpoofRenderer.H()) {
         ExpoClient.w.e(new EntityRenderStateEvent((short)0, (char)26096, 2592973, var0), 18670087776179L);
      }
   }
}
