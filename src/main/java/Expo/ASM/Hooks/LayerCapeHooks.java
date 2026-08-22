package Expo.ASM.Hooks;

import Expo.ExpoClient;
import Expo.event.events.PostRenderCapeEvent;
import Expo.event.events.PreRenderCapeEvent;
import Expo.util.render.VisualSpoofRenderer;
import net.minecraft.client.entity.AbstractClientPlayer;











public class LayerCapeHooks {
   private static final long a = 48183266809161L;

   public static void onRenderPost(AbstractClientPlayer var0) {

      if (!VisualSpoofRenderer.H()) {
         ExpoClient.w.e(new PostRenderCapeEvent(var0), 18670087776179L);
      }
   }

   public static void onRenderPre(AbstractClientPlayer var0) {




      if (!VisualSpoofRenderer.H()) {
         ExpoClient.w.e(new PreRenderCapeEvent((short)0, 413183256, (short)697, var0), 18670087776179L);
      }
   }
}
