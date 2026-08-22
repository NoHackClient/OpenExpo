package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PreUpdateEvent;
import Expo.module.impl.visual_utility.BedPlates;











public final class BedPlatesPreUpdateInvoker implements EventInvoker {
   final BedPlates p;

   public BedPlatesPreUpdateInvoker(BedPlates var1) {
      this.p = var1;
   }

   public void c(long var1, Object var3) {
      this.p.onPreUpdate((PreUpdateEvent)var3);
   }
}
