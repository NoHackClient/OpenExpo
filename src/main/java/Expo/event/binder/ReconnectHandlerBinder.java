package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.ActionPerformedEvent;
import Expo.event.events.DisconnectedInitEvent;
import Expo.event.events.GuiMouseEvent;
import Expo.event.events.InitGuiEvent;
import Expo.event.events.PreDrawScreenEvent;
import Expo.event.events.ServerJoinEvent;
import Expo.event.invoker.ReconnectHandlerActionPerformedInvoker;
import Expo.event.invoker.ReconnectHandlerDisconnectedInitInvoker;
import Expo.event.invoker.ReconnectHandlerGuiMouseInvoker;
import Expo.event.invoker.ReconnectHandlerInitGuiInvoker;
import Expo.event.invoker.ReconnectHandlerPreDrawScreenInvoker;
import Expo.event.invoker.ReconnectHandlerServerJoinInvoker;
import Expo.ui.screen.ReconnectHandler;











public final class ReconnectHandlerBinder {
   private static final long a = 65628309907104L;

   public static void v(EventBus var0, ReconnectHandler var3) {
      var0.R(var3, GuiMouseEvent.class, 3, new ReconnectHandlerGuiMouseInvoker(var3));
      var0.R(var3, InitGuiEvent.class, 3, new ReconnectHandlerInitGuiInvoker(var3));
      var0.R(var3, PreDrawScreenEvent.class, 3, new ReconnectHandlerPreDrawScreenInvoker(var3));
      var0.R(var3, ActionPerformedEvent.class, 3, new ReconnectHandlerActionPerformedInvoker(var3));
      var0.R(var3, DisconnectedInitEvent.class, 3, new ReconnectHandlerDisconnectedInitInvoker(var3));
      var0.R(var3, ServerJoinEvent.class, 3, new ReconnectHandlerServerJoinInvoker(var3));
   }

   private ReconnectHandlerBinder() {
   }
}
