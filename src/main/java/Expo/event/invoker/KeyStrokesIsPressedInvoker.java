package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.IsPressedEvent;
import Expo.module.impl.visual.KeyStrokes;

public final class KeyStrokesIsPressedInvoker implements EventInvoker {
   final KeyStrokes S;

   public KeyStrokesIsPressedInvoker(KeyStrokes var1) {
      this.S = var1;
   }

   public void c(long var1, Object var3) {
      this.S.onIsPressed((IsPressedEvent)var3);
   }
}
