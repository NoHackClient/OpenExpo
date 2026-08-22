package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.Render2DEvent;
import Expo.event.events.SetKeyBindStateEvent;
import Expo.event.invoker.TabGUIRender2DInvoker;
import Expo.event.invoker.TabGUISetKeyBindStateInvoker;
import Expo.module.impl.visual.TabGUI;











public final class TabGUIBinder {
   private static final long a = 15057733837494L;

   public static void z(EventBus var0, TabGUI var1) {
      var0.R(var1, Render2DEvent.class, 3, new TabGUIRender2DInvoker(var1));
      var0.R(var1, SetKeyBindStateEvent.class, 3, new TabGUISetKeyBindStateInvoker(var1));
   }

   private TabGUIBinder() {
   }
}
