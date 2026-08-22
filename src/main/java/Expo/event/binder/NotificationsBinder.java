package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.Render2DEvent;
import Expo.event.invoker.NotificationsRender2DInvoker;
import Expo.module.impl.configuration.Notifications;











public final class NotificationsBinder {
   private static final long a = 135996970215047L;

   public static void r(EventBus var0, Notifications var3) {
      var0.R(var3, Render2DEvent.class, 3, new NotificationsRender2DInvoker(var3));
   }

   private NotificationsBinder() {
   }
}
