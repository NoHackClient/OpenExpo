package Expo.event.invoker;

import Expo.ExpoClient;
import Expo.event.EventInvoker;
import Expo.event.events.PostTickEvent;











public final class ExpoClientPostTickInvoker implements EventInvoker {
   final ExpoClient p;

   public ExpoClientPostTickInvoker(ExpoClient var1) {
      this.p = var1;
   }

   public void c(long var1, Object var3) throws Throwable {
      long var4 = var1 ^ 100481986500924L;
      this.p.onPostTick((PostTickEvent)var3, var4);
   }
}
