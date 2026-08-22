package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.PostTickEvent;
import Expo.event.events.Render2DEvent;
import Expo.event.invoker.BindGUIPostTickInvoker;
import Expo.event.invoker.BindGUIRender2DInvoker;
import Expo.module.impl.visual.BindGUI;

public final class BindGUIBinder {
   private static final long a = 37771993463129L;

   public static void J(EventBus var2, BindGUI var3) {
      var2.R(var3, PostTickEvent.class, 3, new BindGUIPostTickInvoker(var3));
      var2.R(var3, Render2DEvent.class, 3, new BindGUIRender2DInvoker(var3));
   }

   private BindGUIBinder() {
   }
}
