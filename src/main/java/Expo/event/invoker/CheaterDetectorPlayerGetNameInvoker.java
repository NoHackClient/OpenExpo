package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PlayerGetNameEvent;
import Expo.internal.CheaterDetector;











public final class CheaterDetectorPlayerGetNameInvoker implements EventInvoker {
   final CheaterDetector G;

   public void c(long var1, Object var3) {
      int var4 = (int)((var1 ^ 136087114850804L) >>> 32);
      int var5 = (int)((var1 ^ 136087114850804L) << 32 >>> 48);
      int var6 = (int)((var1 ^ 136087114850804L) << 48 >>> 48);
      this.G.onPlayerGetName(var4, (PlayerGetNameEvent)var3, (short)var5, var6);
   }

   public CheaterDetectorPlayerGetNameInvoker(CheaterDetector var1) {
      this.G = var1;
   }
}
