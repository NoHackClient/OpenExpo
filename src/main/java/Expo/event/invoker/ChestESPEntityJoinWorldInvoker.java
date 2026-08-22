package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.EntityJoinWorldEvent;
import Expo.module.impl.visual_utility.ChestESP;











public final class ChestESPEntityJoinWorldInvoker implements EventInvoker {
   final ChestESP z;

   public void c(long var1, Object var3) {
      this.z.onEntityJoinWorld((EntityJoinWorldEvent)var3);
   }

   public ChestESPEntityJoinWorldInvoker(ChestESP var1) {
      this.z = var1;
   }
}
