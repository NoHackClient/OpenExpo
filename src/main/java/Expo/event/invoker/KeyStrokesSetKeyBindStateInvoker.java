package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.SetKeyBindStateEvent;
import Expo.module.impl.visual.KeyStrokes;

public final class KeyStrokesSetKeyBindStateInvoker implements EventInvoker {
   final KeyStrokes S;

   public void c(long var1, Object var3) {
      this.S.onSetKeyBindState((SetKeyBindStateEvent)var3);
   }

   public KeyStrokesSetKeyBindStateInvoker(KeyStrokes var1) {
      this.S = var1;
   }
}
