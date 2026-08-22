package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.HeldItemChangeEvent;
import Expo.module.impl.world.BlockIn;

public final class BlockInHeldItemChangeInvoker implements EventInvoker {
   final BlockIn v;

   public BlockInHeldItemChangeInvoker(BlockIn var1) {
      this.v = var1;
   }

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 50492147403308L;
      this.v.onHeldItemChange(var4, (HeldItemChangeEvent)var3);
   }
}
