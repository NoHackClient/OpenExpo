package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.EntityJoinWorldEvent;
import Expo.module.impl.player.ChestAura;











public final class ChestAuraEntityJoinWorldInvoker implements EventInvoker {
   final ChestAura f;

   public ChestAuraEntityJoinWorldInvoker(ChestAura var1) {
      this.f = var1;
   }

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 28648772489744L;
      this.f.onEntityJoinWorld(var4, (EntityJoinWorldEvent)var3);
   }
}
