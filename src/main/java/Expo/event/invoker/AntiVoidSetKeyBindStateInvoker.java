package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.SetKeyBindStateEvent;
import Expo.module.impl.world.AntiVoid;











public final class AntiVoidSetKeyBindStateInvoker implements EventInvoker {
   final AntiVoid p;

   public void c(long var1, Object var3) {
      long var4 = (var1 ^ 84049854566377L) >>> 16;
      int var6 = (int)((var1 ^ 84049854566377L) << 48 >>> 48);
      this.p.onSetKeyBindState((SetKeyBindStateEvent)var3, var4, (char)var6);
   }

   public AntiVoidSetKeyBindStateInvoker(AntiVoid var1) {
      this.p = var1;
   }
}
