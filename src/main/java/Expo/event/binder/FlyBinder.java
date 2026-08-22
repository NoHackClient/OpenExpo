package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.MoveFlyingEvent;
import Expo.event.events.PreUpdateEvent;
import Expo.event.invoker.FlyMoveFlyingInvoker;
import Expo.event.invoker.FlyPreUpdateInvoker;
import Expo.module.impl.movement.Fly;

public final class FlyBinder {
   private static final long a = 23171281344504L;

   private FlyBinder() {
   }

   public static void x(EventBus var2, Fly var3) {
      var2.R(var3, MoveFlyingEvent.class, 3, new FlyMoveFlyingInvoker(var3));
      var2.R(var3, PreUpdateEvent.class, 3, new FlyPreUpdateInvoker(var3));
   }
}
