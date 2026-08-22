package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.HeldItemChangeEvent;
import Expo.event.events.PreMouseInputEvent;
import Expo.event.invoker.BlockInHeldItemChangeInvoker;
import Expo.event.invoker.BlockInPreMouseInputInvoker;
import Expo.module.impl.world.BlockIn;











public final class BlockInBinder {
   private static final long a = 83024006212756L;

   public static void s(EventBus var0, BlockIn var3) {
      var0.R(var3, HeldItemChangeEvent.class, 3, new BlockInHeldItemChangeInvoker(var3));
      var0.R(var3, PreMouseInputEvent.class, 3, new BlockInPreMouseInputInvoker(var3));
   }

   private BlockInBinder() {
   }
}
