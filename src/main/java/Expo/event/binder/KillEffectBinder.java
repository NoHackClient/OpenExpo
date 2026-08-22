package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.EntityJoinWorldEvent;
import Expo.event.events.HandleChatEvent;
import Expo.event.events.LivingDeathEvent;
import Expo.event.events.PostRenderEvent;
import Expo.event.invoker.KillEffectEntityJoinWorldInvoker;
import Expo.event.invoker.KillEffectHandleChatInvoker;
import Expo.event.invoker.KillEffectPostRenderInvoker;
import Expo.event.invoker.KillEffectLivingDeathInvoker;
import Expo.event.invoker.Zc_2;
import Expo.module.impl.visual.KillEffect;











public final class KillEffectBinder {
   private static final long a = 21480002052700L;

   public static void P(EventBus var0, KillEffect var1) {
      var0.R(var1, LivingDeathEvent.class, 3, new Zc_2(var1));
      var0.R(var1, LivingDeathEvent.class, 3, new KillEffectLivingDeathInvoker(var1));
      var0.R(var1, HandleChatEvent.class, 3, new KillEffectHandleChatInvoker(var1));
      var0.R(var1, PostRenderEvent.class, 3, new KillEffectPostRenderInvoker(var1));
      var0.R(var1, EntityJoinWorldEvent.class, 3, new KillEffectEntityJoinWorldInvoker(var1));
   }

   private KillEffectBinder() {
   }

}
