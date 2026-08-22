package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PostUpdateEvent;
import Expo.module.impl.combat.AutoBlock;











public final class AutoBlockPostUpdateInvoker implements EventInvoker {
   final AutoBlock Z;

   public void c(long var1, Object var3) {
      long var4 = (var1 ^ 136797310811898L) >>> 16;
      int var6 = (int)((var1 ^ 136797310811898L) << 48 >>> 48);
      this.Z.onPostUpdate(var4, (short)var6, (PostUpdateEvent)var3);
   }

   public AutoBlockPostUpdateInvoker(AutoBlock var1) {
      this.Z = var1;
   }
}
