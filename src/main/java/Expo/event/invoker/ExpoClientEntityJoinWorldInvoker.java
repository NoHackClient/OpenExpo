package Expo.event.invoker;

import Expo.ExpoClient;
import Expo.event.EventInvoker;
import Expo.event.events.EntityJoinWorldEvent;











public final class ExpoClientEntityJoinWorldInvoker implements EventInvoker {
   final ExpoClient y;

   public ExpoClientEntityJoinWorldInvoker(ExpoClient var1) {
      this.y = var1;
   }

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 58853336938877L;
      this.y.onEntityJoinWorld(var4, (EntityJoinWorldEvent)var3);
   }
}
