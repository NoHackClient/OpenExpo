package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.CloseScreenEvent;
import Expo.module.impl.player.ChestAura;

public final class ChestAuraCloseScreenInvoker implements EventInvoker {
   final ChestAura G;

   public ChestAuraCloseScreenInvoker(ChestAura var1) {
      this.G = var1;
   }

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 54772455397387L;
      this.G.onCloseScreen(var4, (CloseScreenEvent)var3);
   }
}
