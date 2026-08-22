package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.ReceivePacketEvent;
import Expo.event.events.Render2DEvent;
import Expo.event.events.UpdateWalkingPlayerEvent;
import Expo.event.invoker.AmbienceReceivePacketInvoker;
import Expo.event.invoker.AmbienceRender2DInvoker;
import Expo.event.invoker.AmbienceUpdateWalkingPlayerInvoker;
import Expo.module.impl.visual.Ambience;











public final class AmbienceBinder {
   private static final long a = 23521424907434L;

   public static void K(EventBus var2, Ambience var3) {
      var2.R(var3, UpdateWalkingPlayerEvent.class, 3, new AmbienceUpdateWalkingPlayerInvoker(var3));
      var2.R(var3, ReceivePacketEvent.class, 3, new AmbienceReceivePacketInvoker(var3));
      var2.R(var3, Render2DEvent.class, 3, new AmbienceRender2DInvoker(var3));
   }

   private AmbienceBinder() {
   }

}
