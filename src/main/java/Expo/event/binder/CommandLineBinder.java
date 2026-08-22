package Expo.event.binder;

import Expo.event.EventBus;
import Expo.event.events.PreUpdateEvent;
import Expo.event.events.Render2DEvent;
import Expo.event.invoker.CommandLinePreUpdateInvoker;
import Expo.event.invoker.CommandLineRender2DInvoker;
import Expo.module.impl.misc.CommandLine;

public final class CommandLineBinder {
   private static final long a = 67733885639777L;

   private CommandLineBinder() {
   }

   public static void s(EventBus var0, CommandLine var3) {
      var0.R(var3, Render2DEvent.class, 3, new CommandLineRender2DInvoker(var3));
      var0.R(var3, PreUpdateEvent.class, 3, new CommandLinePreUpdateInvoker(var3));
   }
}
