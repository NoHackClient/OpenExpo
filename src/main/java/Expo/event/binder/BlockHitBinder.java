package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.PostUpdateEvent;
import Expo.event.events.PreMouseInputEvent;
import Expo.event.events.PreUpdateEvent;
import Expo.event.events.ReceivePacketEvent;
import Expo.event.events.UpdateCameraAndRenderEvent;
import Expo.event.invoker.BlockHitPostUpdateInvoker;
import Expo.event.invoker.BlockHitUpdateCameraAndRenderInvoker;
import Expo.event.invoker.BlockHitPreMouseInputInvoker;
import Expo.event.invoker.BlockHitPreUpdateInvoker;
import Expo.event.invoker.BlockHitReceivePacketInvoker;
import Expo.module.impl.combat.BlockHit;











public final class BlockHitBinder {
   private static final long a = 33634135460793L;

   private BlockHitBinder() {
   }

   public static void s(EventBus var2, BlockHit var3) {
      var2.R(var3, PreMouseInputEvent.class, 2, new BlockHitPreMouseInputInvoker(var3));
      var2.R(var3, PreUpdateEvent.class, 3, new BlockHitPreUpdateInvoker(var3));
      var2.R(var3, PostUpdateEvent.class, 3, new BlockHitPostUpdateInvoker(var3));
      var2.R(var3, ReceivePacketEvent.class, 3, new BlockHitReceivePacketInvoker(var3));
      // update new version
      var2.R(var3, UpdateCameraAndRenderEvent.class, 3, new BlockHitUpdateCameraAndRenderInvoker(var3));
   }

}
