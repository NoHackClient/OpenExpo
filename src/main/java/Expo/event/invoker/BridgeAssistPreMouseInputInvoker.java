package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PreMouseInputEvent;
import Expo.module.impl.world.BridgeAssist;

public final class BridgeAssistPreMouseInputInvoker implements EventInvoker {
   final BridgeAssist w;

   public void c(long var1, Object var3) {
      int var4 = (int)((var1 ^ 72114354959481L) >>> 32);
      int var5 = (int)((var1 ^ 72114354959481L) << 32 >>> 48);
      int var6 = (int)((var1 ^ 72114354959481L) << 48 >>> 48);
      this.w.onPreMouseInput(var4, var5, (PreMouseInputEvent)var3, (char)var6);
   }

   public BridgeAssistPreMouseInputInvoker(BridgeAssist var1) {
      this.w = var1;
   }
}
