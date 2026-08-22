package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.DisconnectedInitEvent;
import Expo.ui.screen.ReconnectHandler;











public final class ReconnectHandlerDisconnectedInitInvoker implements EventInvoker {
   final ReconnectHandler b;

   public ReconnectHandlerDisconnectedInitInvoker(ReconnectHandler var1) {
      this.b = var1;
   }

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 96782036112568L;
      this.b.onDisconnectedInit(var4, (DisconnectedInitEvent)var3);
   }
}
