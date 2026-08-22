package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PreUpdateEvent;
import Expo.module.impl.combat.BackTrack;

public final class BackTrackPreUpdateInvoker implements EventInvoker {
   final BackTrack O;

   public BackTrackPreUpdateInvoker(BackTrack var1) {
      this.O = var1;
   }

   public void c(long var1, Object var3) {
      int var4 = (int)((var1 ^ 140583694727781L) >>> 48);
      int var5 = (int)((var1 ^ 140583694727781L) << 16 >>> 32);
      int var6 = (int)((var1 ^ 140583694727781L) << 48 >>> 48);
      this.O.onPreUpdate((char)var4, var5, (PreUpdateEvent)var3, (short)var6);
   }
}
