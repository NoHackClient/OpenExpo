package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PlayerRightClickEvent;
import Expo.module.impl.player.ChestAura;











public final class ChestAuraPlayerRightClickInvoker implements EventInvoker {
   final ChestAura l;

   public void c(long var1, Object var3) {
      int var4 = (int)((var1 ^ 52379999690820L) >>> 48);
      int var5 = (int)((var1 ^ 52379999690820L) << 16 >>> 32);
      int var6 = (int)((var1 ^ 52379999690820L) << 48 >>> 48);
      this.l.onPlayerRightClick((short)var4, var5, (short)var6, (PlayerRightClickEvent)var3);
   }

   public ChestAuraPlayerRightClickInvoker(ChestAura var1) {
      this.l = var1;
   }
}
