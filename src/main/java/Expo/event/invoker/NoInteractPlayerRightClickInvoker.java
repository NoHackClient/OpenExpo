package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PlayerRightClickEvent;
import Expo.module.impl.player.NoInteract;











public final class NoInteractPlayerRightClickInvoker implements EventInvoker {
   final NoInteract s;

   public void c(long var1, Object var3) {
      this.s.onPlayerRightClick((PlayerRightClickEvent)var3);
   }

   public NoInteractPlayerRightClickInvoker(NoInteract var1) {
      this.s = var1;
   }
}
