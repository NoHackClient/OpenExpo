package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.EntityJoinWorldEvent;
import Expo.event.events.ReceivePacketEvent;
import Expo.event.events.Render3DEvent;
import Expo.event.invoker.BlocksESPEntityJoinWorldInvoker;
import Expo.event.invoker.BlocksESPReceivePacketInvoker;
import Expo.event.invoker.BlocksESPRender3DInvoker;
import Expo.module.impl.visual_utility.BlocksESP;











public final class BlocksESPBinder {
   private static final long a = 123756056164713L;

   private BlocksESPBinder() {
   }

   public static void b(EventBus var2, BlocksESP var3) {
      var2.R(var3, EntityJoinWorldEvent.class, 3, new BlocksESPEntityJoinWorldInvoker(var3));
      var2.R(var3, ReceivePacketEvent.class, 3, new BlocksESPReceivePacketInvoker(var3));
      var2.R(var3, Render3DEvent.class, 3, new BlocksESPRender3DInvoker(var3));
   }

}
