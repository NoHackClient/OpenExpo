package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.Render2DEvent;
import Expo.module.impl.combat.BackTrack;

public final class BackTrackRender2DInvoker implements EventInvoker {
   final BackTrack O;

   public BackTrackRender2DInvoker(BackTrack var1) {
      this.O = var1;
   }

   public void c(long var1, Object var3) {
      int var4 = (int)((var1 ^ 26532795795209L) >>> 48);
      int var5 = (int)((var1 ^ 26532795795209L) << 16 >>> 32);
      int var6 = (int)((var1 ^ 26532795795209L) << 48 >>> 48);
      this.O.onRender2D((char)var4, var5, (Render2DEvent)var3, (char)var6);
   }
}
