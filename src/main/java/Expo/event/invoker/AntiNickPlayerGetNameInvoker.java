package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PlayerGetNameEvent;
import Expo.module.impl.misc.AntiNick;











public final class AntiNickPlayerGetNameInvoker implements EventInvoker {
   final AntiNick G;

   public void c(long var1, Object var3) {
      this.G.onPlayerGetName((PlayerGetNameEvent)var3);
   }

   public AntiNickPlayerGetNameInvoker(AntiNick var1) {
      this.G = var1;
   }
}
