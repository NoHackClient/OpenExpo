package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.EntityJoinWorldEvent;
import Expo.module.impl.visual_utility.BlocksESP;

public final class BlocksESPEntityJoinWorldInvoker implements EventInvoker {
   final BlocksESP W;

   public BlocksESPEntityJoinWorldInvoker(BlocksESP var1) {
      this.W = var1;
   }

   public void c(long var1, Object var3) {
      this.W.onEntityJoinWorld((EntityJoinWorldEvent)var3);
   }
}
