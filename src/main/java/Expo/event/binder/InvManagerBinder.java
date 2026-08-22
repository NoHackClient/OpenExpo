package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.DrawScreenEvent;
import Expo.event.events.PickUpItemEvent;
import Expo.event.events.PreUpdateEvent;
import Expo.event.invoker.InvManagerDrawScreenInvoker;
import Expo.event.invoker.InvManagerPickUpItemInvoker;
import Expo.event.invoker.InvManagerPreUpdateInvoker;
import Expo.module.impl.player.InvManager;

public final class InvManagerBinder {
   private static final long a = 22852846999536L;

   private InvManagerBinder() {
   }

   public static void L(EventBus var2, InvManager var3) {
      var2.R(var3, PreUpdateEvent.class, 3, new InvManagerPreUpdateInvoker(var3));
      var2.R(var3, DrawScreenEvent.class, 3, new InvManagerDrawScreenInvoker(var3));
      var2.R(var3, PickUpItemEvent.class, 3, new InvManagerPickUpItemInvoker(var3));
   }
}
