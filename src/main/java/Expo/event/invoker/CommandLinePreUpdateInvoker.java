package Expo.event.invoker;

import Expo.event.EventInvoker;
import Expo.event.events.PreUpdateEvent;
import Expo.module.impl.misc.CommandLine;











public final class CommandLinePreUpdateInvoker implements EventInvoker {
   final CommandLine w;

   public void c(long var1, Object var3) {
      long var4 = var1 ^ 78485258384733L;
      this.w.onPreUpdate((PreUpdateEvent)var3, var4);
   }

   public CommandLinePreUpdateInvoker(CommandLine var1) {
      this.w = var1;
   }
}
