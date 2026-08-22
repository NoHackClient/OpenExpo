package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PostTickEvent;
import Expo.module.impl.visual_utility.ESP;

public final class ESPPostTickInvoker implements EventInvoker {
   final ESP S;

   public ESPPostTickInvoker(ESP var1) {
      this.S = var1;
   }

   public void c(long var1, Object var3) {
      int var4 = (int)((var1 ^ 36571301621974L) >>> 32);
      int var5 = (int)((var1 ^ 36571301621974L) << 32 >>> 48);
      int var6 = (int)((var1 ^ 36571301621974L) << 48 >>> 48);
      this.S.onPostTick(var4, (char)var5, (PostTickEvent)var3, (short)var6);
   }
}
