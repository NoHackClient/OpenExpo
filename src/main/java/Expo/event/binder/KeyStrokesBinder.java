package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.IsPressedEvent;
import Expo.event.events.Render2DEvent;
import Expo.event.events.SetKeyBindStateEvent;
import Expo.event.invoker.KeyStrokesIsPressedInvoker;
import Expo.event.invoker.KeyStrokesMouseInvoker;
import Expo.event.invoker.KeyStrokesRender2DInvoker;
import Expo.event.invoker.KeyStrokesSetKeyBindStateInvoker;
import Expo.module.impl.visual.KeyStrokes;
import java.awt.event.MouseEvent;

public final class KeyStrokesBinder {
   private static final long a = 34608198279165L;

   public static void e(EventBus var0, KeyStrokes var3) {
      var0.R(var3, SetKeyBindStateEvent.class, 3, new KeyStrokesSetKeyBindStateInvoker(var3));
      var0.R(var3, MouseEvent.class, 3, new KeyStrokesMouseInvoker(var3));
      var0.R(var3, IsPressedEvent.class, 3, new KeyStrokesIsPressedInvoker(var3));
      var0.R(var3, Render2DEvent.class, 3, new KeyStrokesRender2DInvoker(var3));
   }

   private KeyStrokesBinder() {
   }
}
