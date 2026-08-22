package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PostTickEvent;
import Expo.module.impl.visual_utility.ItemESP;

public final class ItemESPPostTickInvoker implements EventInvoker {
   final ItemESP M;

   public ItemESPPostTickInvoker(ItemESP var1) {
      this.M = var1;
   }

   public void c(long var1, Object var3) {
      this.M.onPostTick((PostTickEvent)var3);
   }
}
